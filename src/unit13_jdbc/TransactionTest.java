package unit13_jdbc;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;

public class TransactionTest {

	private static String driver;
	private static String url;
	private static String user;
	private static String pass;
	public void initParam(String paramFile) throws Exception
	{
		Properties props = new Properties();
		props.load(new FileInputStream(paramFile));
		driver = props.getProperty("driver");
		url = props.getProperty("url");
		user = props.getProperty("user");
		pass = props.getProperty("pass");
		Class.forName(driver);
	}
	
	public void insertInTransaction(String[] sqls) throws Exception
	{
		try(
			Connection conn = DriverManager.getConnection(url, user, pass))
		{
			// 关闭自动提交，开启事务
			conn.setAutoCommit(false);
			try(
				Statement stmt = conn.createStatement())
			{
				for (String sql : sqls)
				{
					stmt.executeUpdate(sql);
				}
			}
			conn.commit();
		}
	}
	
	public static void main(String[] args) throws Exception {
		TransactionTest tt = new TransactionTest();
		tt.initParam("./src/unit13_jdbc/mysql.ini");
		String[] sqls = new String[]{
				"insert into student_table values(3,'aaa',1)",
				"insert into student_table values(4,'bbb',1)",
				"insert into student_table values(5,'ccc','c')"
		};
		tt.insertInTransaction(sqls);
	}

}
