package SqlDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DBConnect 
{
	public static void createDataBase()
	{
		try
		{
			String url = "jdbc:mysql://localhost:3306/";
			String userName = "root";
			String password = "a!am";
			
			
			Connection conn = DriverManager.getConnection(url,userName,password);
			Statement stm = conn.createStatement();
			
			String query = "create database abcxyz";
			boolean b1 = stm.execute(query);

			System.out.println("Database created Successfully!" + b1);
			conn.close();
			
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) 
	{
		DBConnect con = new DBConnect();
		
		con.createDataBase();
		
	}
}
