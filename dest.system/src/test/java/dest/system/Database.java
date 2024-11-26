package dest.system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

	private Connection connection = null;
	private static Database instancia = null;

	// Singleton
	public static Database getInstance() {
		if (instancia == null) {
			instancia = new Database();
		}
		return instancia;
	}

	private Database() {
		try {
			connection = DriverManager
					.getConnection("jdbc:sqlite::resource:" + TestDB.class.getResource("/res/sample.db"));
		} catch (SQLException e) {
			System.out.println("Houve um erro ao criar o arquivo do banco");
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		return this.connection;
	}

	public void closeConnection() {
		try {
			this.connection.close();
		} catch (SQLException e) {
			System.out.println("Houve um erro ao fechar a conexao com o banco");
			e.printStackTrace();
		}
	}
}
