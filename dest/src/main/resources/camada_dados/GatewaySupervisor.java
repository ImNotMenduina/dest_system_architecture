package camada_dados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entidades.PedidoDTO;
import entidades.SupervisorDTO;
import exception.EstagioJaSupervisionadoEx;

public class GatewaySupervisor {

	private Connection connection = null;

	public void inserir(String nome, String email, String senha, String telefone, String nomeEmpresa, String cnpj,
			int numeroPedidoEstagio, String funcao) {
		String sql = "INSERT INTO supervisor (nome, email, senha, funcao, telefone, nomeEmpresa, cnpj, numeroEstagio) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			connection = Database.getInstance().getConnection();

			try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
				// First supervisor
				pstmt.setString(1, nome);
				pstmt.setString(2, email);
				pstmt.setString(3, senha);
				pstmt.setString(4, funcao);
				pstmt.setString(5, telefone);
				pstmt.setString(6, nomeEmpresa);
				pstmt.setString(7, cnpj);
				pstmt.setInt(8, numeroPedidoEstagio);
				pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			System.err.println("Erro ao buscar pedido (gtSupervisor): " + e.getMessage());
			e.printStackTrace();
		}

	}

	public Integer buscarId(String email) {
		String sql = "SELECT id FROM supervisor WHERE email = ?";

		try {
			connection = Database.getInstance().getConnection();

			try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
				pstmt.setString(1, email);

				try (ResultSet rs = pstmt.executeQuery()) {
					if (rs.next()) {
						Integer id = (Integer) rs.getObject("id");
						// VERIFICA SE SUPERVISORID E NULO
						return id;
					}
				}
			}
		} catch (SQLException e) {
			System.err.println("Erro ao buscar pedido: " + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
	
	public SupervisorDTO buscarPorId(int id) {
		String sql = "SELECT nome, email FROM supervisor WHERE id = ?";

		try {
			connection = Database.getInstance().getConnection();

			try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
				pstmt.setInt(1, id);

				try (ResultSet rs = pstmt.executeQuery()) {
					if (rs.next()) {
						String email = (String) rs.getObject("email");
						String nome = (String) rs.getObject("nome");
						return new SupervisorDTO(nome, email);
					}
					return null;
				}
			}
		} catch (SQLException e) {
			System.err.println("Erro ao buscar pedido: " + e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
}
