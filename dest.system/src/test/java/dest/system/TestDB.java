package dest.system;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestDB {
	// NOTE: Connection and Statement are AutoCloseable.
	// Don't forget to close them both in order to avoid leaks.
	public static void main(String[] args) {

		Connection con = Database.getInstance().getConnection();

		try (
			Statement statement = con.createStatement();) {
			statement.setQueryTimeout(30); // set timeout to 30 sec.

			statement.executeUpdate("drop table if exists person");
			statement.executeUpdate("create table person (id integer, name string)");
			statement.executeUpdate("insert into person values(1, 'leo')");
			statement.executeUpdate("insert into person values(2, 'yui')");
			statement.executeUpdate("insert into person values(3, 'aa')");

			ResultSet rs = statement.executeQuery("select * from person");
			while (rs.next()) {
				// read the result set
				System.out.println("name = " + rs.getString("name"));
				System.out.println("id = " + rs.getInt("id"));
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
