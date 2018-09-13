package sichuan.ytf.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnMysql8 {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection(
				"jdbc:mysql://9.186.54.96:3306/springboot_test?characterEncoding=utf8", "root", "MySqlPass1!");
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("select * from u_user");
		while (rs.next()) {
			for (int i = 1; i <= 4; i++) {
				System.out.print(rs.getString(i) + "\t");
			}
			System.out.println();
		}
	}
}
