package unit13_jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConnMySql {

	public static void main(String[] args) throws Exception{
		// 1.加载驱动，使用反射知识
		Class.forName("com.mysql.cj.jdbc.Driver");
		try(
			// 2.使用 DriverManager 获取数据库连接
			Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/select_test?useSSL=false&serverTimezone=Hongkong", "root", "root");
			// 3.使用 Connection 来创建一个 Statement 对象
			Statement stmt = conn.createStatement();
			// 4.执行 SQL 语句
			ResultSet rs = stmt.executeQuery("select s.* from student_table s"))
		{
			while (rs.next())
			{
				System.out.println(rs.getInt(1) + "\t" + rs.getString(2) + "\t" +rs.getInt(3));
			}
		}
	}

}
