package businessLogics;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CSDL {
	
	private static Connection ketNoi;
	private static final String url="jdbc:mysql://localhost:3306/phuong_perfume?useUnicode=true&characterEncoding=UTF-8&useSSL=false";
	private static String user="root";
	private static String password="";
	
	public static Connection getKetNoi() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			ketNoi= DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return ketNoi;
	}
}
