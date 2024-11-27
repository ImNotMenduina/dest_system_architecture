package camada_dados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entidades.PedidoDTO;

public class GatewayPedido {
	private Connection connection = null;
	
	//BUSCAR PEDIDO E CRIAR PEDIDODTO
	public PedidoDTO buscar(int numeroPedidoEstagio) {
		String sql = "SELECT " + "p.nome as nome_aluno, " + "p.nomeEmpresa as nome_empresa, "
				+ "s.nome as nome_supervisor " + "FROM pedido p " + "INNER JOIN supervisor s ON p.supervisorId = s.id "
				+ "WHERE p.id = ?";

		try {
			connection = Database.getInstance().getConnection();

			try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
				pstmt.setInt(1, numeroPedidoEstagio);

				try (ResultSet rs = pstmt.executeQuery()) {
					if (rs.next()) {
						return new PedidoDTO(rs.getString("nome_supervisor"), rs.getString("nome_aluno"),
								rs.getString("nome_empresa"));
					}
				}
			}
		} catch (SQLException e) {
			System.err.println("Erro ao buscar pedido: " + e.getMessage());
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					System.err.println("Erro ao fechar conexão: " + e.getMessage());
				}
			}
		}

		return null;
	}
}
