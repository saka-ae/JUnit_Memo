package chap02.リスト2_09;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

	public static void main(String[] args) {
        String jdbcUrl = "jdbc:derby:memory:sample;create=true";

        try (
        		Connection	conn = DriverManager.getConnection(jdbcUrl);
        		Statement	stmt = conn.createStatement();
        ) {
        	initDb(stmt);

        	String newLine = System.lineSeparator();
    		String sql =	"UPDATE	家計簿" + newLine +
    						"SET	入金額 = 99" + newLine +
    						"WHERE	日付 = '2013-02-03'";
    		stmt.executeUpdate(sql);

    		printDB(stmt);
        } catch (SQLException e) {
        	e.printStackTrace();
        }
    }


	private static void initDb(Statement stmt) throws SQLException {
		createTable(stmt);
		inputDatas(stmt);
	}

	private static void createTable(Statement stmt) throws SQLException {
		String sql = "CREATE TABLE 家計簿"
				+ "("
		    		+ "日付		VARCHAR(20),"
		    		+ "費目		VARCHAR(20),"
		    		+ "メモ		VARCHAR(20),"
		    		+ "入金額	INTEGER,"
		    		+ "出金額	INTEGER"
				+ ")";

		stmt.executeUpdate(sql);
	}

	private static void inputDatas(Statement stmt) throws SQLException {
		String sql1 = "INSERT INTO 家計簿 VALUES('2013-02-03', '食費',			'コーヒーを購入',	0,		380)";
		String sql2 = "INSERT INTO 家計簿 VALUES('2013-02-10', '給料',			'1月の給料',		280000,	0)";
		String sql3 = "INSERT INTO 家計簿 VALUES('2013-02-11', '教養娯楽費',	'書籍を購入',		0,		2800)";
		String sql4 = "INSERT INTO 家計簿 VALUES('2013-02-14', '交際費',		'同期会の会費',		0,		5000)";
		String sql5 = "INSERT INTO 家計簿 VALUES('2013-02-18', '水道光熱費',	'1月の電気代',		0,		7560)";

		String[] sqlArray = { sql1, sql2, sql3, sql4, sql5 };
		for (String sql : sqlArray)
			stmt.executeUpdate(sql);
	}

	private static void printDB(Statement stmt) throws SQLException {
		String sql = "SELECT * FROM 家計簿";

		try (ResultSet rs = stmt.executeQuery(sql)) {
			while (rs.next()) {
				String date	= rs.getString("日付");
				String item	= rs.getString("費目");
				String memo	= rs.getString("メモ");
				int income	= rs.getInt("入金額");
				int cost	= rs.getInt("出金額");

				System.out.printf("%s %s %s %d %d\n", date, item, memo, income, cost);
			}
		}
	}

}
