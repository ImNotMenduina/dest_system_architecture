package camada_dados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

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
			try {
				Class.forName("org.sqlite.JDBC");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			connection = DriverManager.getConnection("jdbc:sqlite:" + TestDB.class.getResource("/res/sample.db"));

			createTables();
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

	private void createTables() throws SQLException {
		try (Statement stmt = connection.createStatement()) {
			// Create person table

			stmt.execute("DROP TABLE IF EXISTS supervisor");
			stmt.execute("DROP TABLE IF EXISTS pedido");

			stmt.execute("CREATE TABLE IF NOT EXISTS supervisor (" + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ "nome TEXT NOT NULL, " + "email TEXT UNIQUE," + "senha TEXT," + "funcao TEXT," + "telefone TEXT,"
					+ "nomeEmpresa TEXT," + "cnpj TEXT UNIQUE,"
					+ "numeroEstagio INTEGER NULL, FOREIGN KEY(numeroEstagio) REFERENCES pedido(id)" + ")");

			// Create another example table
			stmt.execute("CREATE TABLE IF NOT EXISTS pedido (" + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ "nome TEXT, " + "matricula TEXT, " + "ira TEXT, " + "cargaHora TEXT," + "endereco TEXT,"
					+ "infoPrimeiro TEXT," + "nomeEmpresa TEXT," + "endEmpresa TEXT," + "modalidade TEXT,"
					+ "cargaHoraSem TEXT," + "valorBolsa TEXT," + "resumo TEXT," + "supervisorId INTEGER DEFAULT NULL,"
					+ "FOREIGN KEY(supervisorId) REFERENCES supervisor(id)" + ")");

			// inserir supervisor

		}
		String sqlsup = "INSERT INTO supervisor (nome, email, senha, funcao, telefone, nomeEmpresa, cnpj, numeroEstagio) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

		try (PreparedStatement pstmt = connection.prepareStatement(sqlsup)) {
			// First supervisor
			pstmt.setString(1, "Carlos Silva");
			pstmt.setString(2, "carlos.silva@empresa.com");
			pstmt.setString(3, "senhaSegura123");
			pstmt.setString(4, "Recursos Humanos");
			pstmt.setString(5, "(11) 98765-4321");
			pstmt.setString(6, "Empresa Tech Solutions");
			pstmt.setString(7, "12.345.678/0001-90");
			pstmt.setInt(8, 1);
			pstmt.executeUpdate();
			// "FOREIGN KEY(person_id) REFERENCES person(id)" +
		} catch (SQLException e) {
			e.printStackTrace();
		}

		String sqlped = "INSERT INTO pedido (nome, matricula, ira, cargaHora, endereco, infoPrimeiro, "
				+ "nomeEmpresa, endEmpresa, modalidade, cargaHoraSem, valorBolsa, resumo, supervisorId)"
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try (PreparedStatement pstmt = connection.prepareStatement(sqlped)) {
			pstmt.setString(1, "Ana Beatriz Souza");
			pstmt.setString(2, "2023001");
			pstmt.setString(3, "8.5");
			pstmt.setString(4, "6 horas/dia");
			pstmt.setString(5, "Rua Principal, 123 - São Paulo");
			pstmt.setString(6, "Primeiro estágio");
			pstmt.setString(7, "Tech Innovations");
			pstmt.setString(8, "Av. Paulista, 1000 - São Paulo");
			pstmt.setString(9, "Remoto");
			pstmt.setString(10, "30 horas/semana");
			pstmt.setString(11, "R$ 1.200,00");
			pstmt.setString(12, "Estudante de Ciência da Computação buscando primeira experiência");
			pstmt.setInt(13, 1);
			pstmt.executeUpdate();

			pstmt.setString(1, "João Pedro Oliveira");
			pstmt.setString(2, "2024002");
			pstmt.setString(3, "9.0");
			pstmt.setString(4, "8 horas/dia");
			pstmt.setString(5, "Av. Brasil, 456 - Rio de Janeiro");
			pstmt.setString(6, "Estágio em Desenvolvimento Web");
			pstmt.setString(7, "WebSolutions Tecnologia");
			pstmt.setString(8, "Rua da Conceição, 250 - Rio de Janeiro");
			pstmt.setString(9, "Híbrido");
			pstmt.setString(10, "40 horas/semana");
			pstmt.setString(11, "R$ 1.500,00");
			pstmt.setString(12, "Estudante de Engenharia de Software com conhecimentos em Java e React");
			// PEDIDO SEM SUPERVISOR
			pstmt.setNull(13, java.sql.Types.INTEGER);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
