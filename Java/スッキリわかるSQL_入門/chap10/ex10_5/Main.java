package chap10.ex10_5;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

	public static void main(String[] args) {
		String jdbcUrl	= "jdbc:h2:~/sukkiriSQL";

		String sql1		= "UPDATE 学生 SET 学部ID = 'K' WHERE 学部ID = 'R'";
		String sql2		= "DELETE FROM 学部 WHERE ID = 'R'";

		try (
				Connection conn = DriverManager.getConnection(jdbcUrl);
				Statement stmt = conn.createStatement();
		) {
			conn.setAutoCommit(false);

			stmt.addBatch(sql1);
			stmt.addBatch(sql2);

			stmt.executeBatch();

			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
