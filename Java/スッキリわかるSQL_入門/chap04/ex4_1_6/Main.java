package chap04.ex4_1_6;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {

	public static void main(String[] args) {
		String jdbcUrl	= "jdbc:h2:~/sukkiriSQL";
		String sql		= 	"SELECT DISTINCT 分類, 商品名, サイズ, 単価"	+
							" FROM 注文履歴"								+
							" WHERE 分類 = '1'"								+

							" UNION"										+

							" SELECT DISTINCT 分類, 商品名, NULL, 単価"		+
							" FROM 注文履歴"								+
							" WHERE 分類 <> '1'"							+
							" ORDER BY 分類, 商品名";

		try (
				Connection conn = DriverManager.getConnection(jdbcUrl);
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();
		) {
			while (rs.next()) {
				String type			= rs.getString("分類");
				String item			= rs.getString("商品名");
				String size			= rs.getString("サイズ");
				int unitPrice		= rs.getInt("単価");

				System.out.printf("%s %s %s %d\n",
									type, item, size, unitPrice);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
