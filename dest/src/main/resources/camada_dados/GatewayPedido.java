package camada_dados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entidades.PedidoDTO;
import exception.EstagioJaSupervisionadoEx;
import exception.PedidoEstagioNExistenteEx;

public class GatewayPedido {
	private Connection connection = null;

	public PedidoDTO buscarPedidoSupervisor(int numeroPedidoEstagio) throws EstagioJaSupervisionadoEx {
		String sql = "SELECT " + "nome as nome_aluno, nomeEmpresa as nome_empresa, supervisorId as supervisor_id "
				+ "FROM pedido " + "WHERE id = ?";

		try {
			connection = Database.getInstance().getConnection();

			try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
				pstmt.setInt(1, numeroPedidoEstagio);

				try (ResultSet rs = pstmt.executeQuery()) {
					if (rs.next()) {
						Integer supervisorId = (Integer) rs.getObject("supervisor_id");
						// VERIFICA SE SUPERVISORID E NULO
						if (rs.wasNull()) {
							return new PedidoDTO(rs.getString("nome_aluno"), rs.getString("nome_empresa"));
						} else {
							throw new EstagioJaSupervisionadoEx("Estágio já supervisionado");
						}
					}
				}
			}
		} catch (SQLException e) {
			System.err.println("Erro ao buscar pedido: " + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	// BUSCAR PEDIDO E CRIAR PEDIDODTO
	public PedidoDTO buscarPedido(int numeroPedidoEstagio) throws PedidoEstagioNExistenteEx {
		String sql = "SELECT " + "nome as nome_aluno, nomeEmpresa as nome_empresa " + "FROM pedido " + "WHERE id = ?";

		try {
			connection = Database.getInstance().getConnection();

			try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
				pstmt.setInt(1, numeroPedidoEstagio);

				try (ResultSet rs = pstmt.executeQuery()) {
					// VERIFICA SE PEDIDO EXISTE
					if (rs.next()) {
						return new PedidoDTO(rs.getString("nome_aluno"), rs.getString("nome_empresa"));
					} else {
						throw new PedidoEstagioNExistenteEx("Pedido de estágio não encontrado");
					}
				}
			}
		} catch (SQLException e) {
			System.err.println("Erro ao buscar pedido: " + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
}
