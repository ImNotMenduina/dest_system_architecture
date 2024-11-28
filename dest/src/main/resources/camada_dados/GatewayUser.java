package camada_dados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entidades.UsuarioDTO;

public class GatewayUser {
	private Connection connection = null;

	public UsuarioDTO buscar(String email, String senha) {
		String sql = "SELECT email FROM aluno WHERE email = ? AND senha = ?";

		try {
			connection = Database.getInstance().getConnection();

			try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
				pstmt.setString(1, email);
				pstmt.setString(2, senha);

				try (ResultSet rs = pstmt.executeQuery()) {
					if (rs.next()) {
						return new UsuarioDTO(rs.getString("email"), true);
					}
				}
			}
		} catch (SQLException e) {
			System.err.println("Erro ao buscar usuário: " + e.getMessage());
			e.printStackTrace();
		}

		return null;
	}
}
