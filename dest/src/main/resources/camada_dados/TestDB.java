package camada_dados;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import camada_dominio.Command;
import camada_dominio.ContCriarSupervisor;
import camada_dominio.ContCriarSupervisor.Tipos;
import camada_dominio.VerificarNumeroEstagioRTC;

public class TestDB {
	// NOTE: Connection and Statement are AutoCloseable.
	// Don't forget to close them both in order to avoid leaks.
	public static void main(String[] args) {

		Database db = Database.getInstance();
		Connection con = db.getConnection();

		try (Statement statement = con.createStatement();) {
			statement.setQueryTimeout(30); // set timeout to 30 sec.

			ResultSet rs = statement.executeQuery("select * from supervisor");
			while (rs.next()) {
				//read the result set
				System.out.println("id = " + rs.getInt("id"));
				System.out.println("name = " + rs.getString("nome"));
				System.out.println("email = " + rs.getString("email"));
				System.out.println("senha = " + rs.getString("senha"));
				System.out.println("funcao = " + rs.getString("funcao"));
				System.out.println("nomeEmpresa = " + rs.getString("nomeEmpresa"));
				System.out.println("cnpj = " + rs.getString("cnpj"));
				System.out.println("numEst = " + rs.getInt("numeroEstagio"));
			}
			
			rs = statement.executeQuery("select * from pedido");
			while (rs.next()) {	
				// read the result set
				System.out.println("pdId = " + rs.getString("id"));
			}
			
			ContCriarSupervisor ct = new ContCriarSupervisor();
			
			ct.servico(Tipos.VERIFICAR_NUMERO_ESTAGIO, 2);
			
			ct.servico(Tipos.CRIAR_SUPERVISOR, "LUCAS", "lucas@gmailc.om", "123", "219999999", "xxxx", "1231231231", 2, "SEXO");
			
			rs = statement.executeQuery("select * from supervisor");
			while (rs.next()) {
				//read the result set
				System.out.println("id = " + rs.getInt("id"));
				System.out.println("name = " + rs.getString("nome"));
				System.out.println("email = " + rs.getString("email"));
				System.out.println("senha = " + rs.getString("senha"));
				System.out.println("funcao = " + rs.getString("funcao"));
				System.out.println("nomeEmpresa = " + rs.getString("nomeEmpresa"));
				System.out.println("cnpj = " + rs.getString("cnpj"));
				System.out.println("numEst = " + rs.getInt("numeroEstagio"));
			}
			
		} catch (SQLException e) {
			// if the error message is "out of memory",
			// it probably means no database file is found
			e.printStackTrace(System.err);
		} finally {
			try {
				if (con != null) {
					con.close();
				}
			} catch (Exception e2) {
				// TODO: handle exception
				System.err.println(e2);
			}
		}
	}
}
