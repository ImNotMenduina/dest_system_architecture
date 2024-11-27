package camada_dados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entidades.SupervisorDTO;

public class GatewaySupervisor {

	public SupervisorDTO buscar(String numeroPedidoEstagio, Connection connection) {
		String sql = "SELECT nome, email FROM supervisor WHERE numeroPedidoEstagio LIKE ?";

		try (PreparedStatement pstmt = connection.prepareStatement(sql)) {

			ResultSet rs = pstmt.executeQuery();

			SupervisorDTO sup = null;

			while (rs.next()) {
				sup = new SupervisorDTO(rs.getString("nome"), rs.getString("email"));
			}

			return sup;

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;
	}

	public void inserir(String nome, String email, String senha, String telefone, String nomeEmpresa, String cnpj,
			String numeroPedidoEstagio, String funcao) {
		// inserido
	}

}
