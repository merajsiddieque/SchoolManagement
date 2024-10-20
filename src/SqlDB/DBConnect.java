package SqlDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnect 
{

		public static void DBviewSchedule(String y)
	{
		try
		{
			String url = "jdbc:mysql://localhost:3306/ums";
			String userName = "root";
			String password = "a!am";
			
			
			Connection conn = DriverManager.getConnection(url,userName,password);
			
			String query = "SELECT C.Title, C.Timings FROM Enrollments E JOIN Courses C ON E.CourseID = C.CourseID WHERE E.StudentID = ?";
			PreparedStatement pstm = conn.prepareStatement(query);
			pstm.setString(1, y);
			
			ResultSet rs = pstm.executeQuery();
			System.out.println("          Courses \t\t\t\t Timings\n");
			while(rs.next())
			{
				
				System.out.println(rs.getString(1)+"-----------------"+rs.getString(2));
			}
			conn.close();
			
		}
		
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
	}
	
}
