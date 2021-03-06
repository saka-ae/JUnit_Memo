package practice.subjectA.LEVEL3.ex25;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {

	public static void main(String[] args) {
		String jdbcUrl	= "jdbc:h2:~/sukkiriSQL";
		String sql		= 	"SELECT DISTINCT 名義"		+
							" FROM 口座"	+
							" ORDER BY 名義";

		try (
				Connection conn = DriverManager.getConnection(jdbcUrl);
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();
		) {
			while (rs.next()) {
				String name		= rs.getString("名義");

				System.out.println(name);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
