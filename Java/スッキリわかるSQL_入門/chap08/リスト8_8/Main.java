package chap08.リスト8_8;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {

	public static void main(String[] args) {
		String jdbcUrl	= "jdbc:h2:~/sukkiriSQL";

		String sql =	"SELECT	日付, 費目.名前, 経費区分.名称"	+
						" FROM 家計簿"							+
						" JOIN 費目"							+
						" ON 家計簿.費目ID = 費目.ID"			+
						" JOIN 経費区分"						+
						" ON 費目.経費区分ID = 経費区分.ID";

		try (
				Connection conn = DriverManager.getConnection(jdbcUrl);
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();
		) {
			while (rs.next()) {
				Date date		= rs.getDate(1);
				String name		= rs.getString(2);
				String type		= rs.getString(3);

				System.out.printf("%s %s %s\n", date, name, type);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
