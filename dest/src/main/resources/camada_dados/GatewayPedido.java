package camada_dados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entidades.PedidoDTO;
import entidades.SupervisorDTO;
import exception.EstagioJaSupervisionadoEx;
import exception.PedidoEstagioNExistenteEx;

public class GatewayPedido {
	private Connection connection = null;
	private GatewaySupervisor dadosSupervisor = new GatewaySupervisor();

	public SupervisorDTO buscarPedidoSupervisor(int numeroPedidoEstagio) throws EstagioJaSupervisionadoEx {
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
							return null;
						}
						throw new EstagioJaSupervisionadoEx("Estágio já supervisionado");
					}
				}
			}
		} catch (SQLException e) {
			System.err.println("Erro ao buscar pedido (gtPedido): " + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	// BUSCAR PEDIDO E CRIAR PEDIDODTO
	public PedidoDTO buscarPedido(int numeroPedidoEstagio) throws PedidoEstagioNExistenteEx {
		String sql = "SELECT " + "nome as nome_aluno, nomeEmpresa as nome_empresa, supervisorId as supervisor_id "
				+ "FROM pedido " + "WHERE id = ?";
		try {
			connection = Database.getInstance().getConnection();

			try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
				pstmt.setInt(1, numeroPedidoEstagio);

				try (ResultSet rs = pstmt.executeQuery()) {
					// VERIFICA SE PEDIDO EXISTE
					if (rs.next()) {
						Integer supervisorId = (Integer) rs.getObject("supervisor_id");
						// VERIFICA SE SUPERVISORID E NULO
						if (rs.wasNull()) {
							//CRIA PEDIDODTO SEM SUPERVISOR
							return new PedidoDTO(rs.getString("nome_aluno"), rs.getString("nome_empresa"));
						}
						SupervisorDTO supervisor = dadosSupervisor.buscarPorId(supervisorId);
						//CARREGA EM PEDIDODTO O SUPERVISORDTO
						return new PedidoDTO(rs.getString("nome_aluno"), rs.getString("nome_empresa"), supervisor);
					}
					return null;
					//throw new PedidoEstagioNExistenteEx("Pedido de Estágio Inexistente");
				}
			}
		} catch (SQLException e) {
			System.err.println("Erro ao buscar pedido: " + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	public void atribuirSupervisor(int pedidoId, int supervisorId) {
		// Use UPDATE em vez de INSERT
		String sql = "UPDATE pedido SET supervisorId = ? WHERE id = ?";
		Connection connection = Database.getInstance().getConnection();

		try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
			pstmt.setInt(1, supervisorId);
			pstmt.setInt(2, pedidoId);
			int rowsAffected = pstmt.executeUpdate();
			if (rowsAffected > 0) {
				System.out.println("Supervisor atribuído com sucesso ao pedido.");
			} else {
				System.out.println("Nenhum pedido encontrado com o id especificado.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void armazenarPedidoEstagio(String nome, String matricula, String ira, String ch, String endereco,
			String infoPrimeiro, String nomeEmpresa, String endEmpresa, String modalidade, String chSemanal,
			String valorBolsa, String resumo) {
		String sql = "INSERT INTO pedido (nome, matricula, ira, cargaHora, endereco, infoPrimeiro, "
				+ "nomeEmpresa, endEmpresa, modalidade, cargaHoraSem, valorBolsa, resumo, supervisorId) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		Connection connection = Database.getInstance().getConnection();

		try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
			pstmt.setString(1, nome);
			pstmt.setString(2, matricula);
			pstmt.setString(3, ira);
			pstmt.setString(4, ch);
			pstmt.setString(5, endereco);
			pstmt.setString(6, infoPrimeiro);
			pstmt.setString(7, nomeEmpresa);
			pstmt.setString(8, endEmpresa);
			pstmt.setString(9, modalidade);
			pstmt.setString(10, chSemanal);
			pstmt.setString(11, valorBolsa);
			pstmt.setString(12, resumo);
			pstmt.setNull(13, java.sql.Types.INTEGER);
			pstmt.executeUpdate();

			System.out.println("PEDIDO INSERIDO");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
