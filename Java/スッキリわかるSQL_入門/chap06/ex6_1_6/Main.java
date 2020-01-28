package chap06.ex6_1_6;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {

	public static void main(String[] args) {
		String jdbcUrl	= "jdbc:h2:~/sukkiriSQL";

		String sql =	"SELECT	都市名,"						+
						"		MIN(最低気温)	AS 最低気温"	+
						" FROM 都市別気象観測"					+
						" GROUP BY 都市名"						+
						" HAVING MIN(最低気温) <= -10";

		try (
				Connection conn				= DriverManager.getConnection(jdbcUrl);
				PreparedStatement pstmt		= conn.prepareStatement(sql);
				ResultSet rs				= pstmt.executeQuery();
		) {
			while (rs.next()) {
				String name		= rs.getString(1);
				int 最低気温	= rs.getInt(2);

				System.out.printf("%s %d\n", name, 最低気温);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
