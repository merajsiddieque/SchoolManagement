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
			System.out.println("      Timings \t\t\t\t Courses\n");
			while(rs.next())
			{
				
				System.out.println("----"+rs.getString(2)+"-----------------"+rs.getString(1));
			}
			conn.close();
			
		}
		
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
	}
		public static void DBviewAvailableCourses(String y)
		{
			try
			{
				String url = "jdbc:mysql://localhost:3306/ums";
				String userName = "root";
				String password = "a!am";
				
				
				Connection conn = DriverManager.getConnection(url,userName,password);
				
				String query = "SELECT CourseID, CourseCode, ProfessorID, Credits, Prerequisites, Timings, Title FROM Courses WHERE Semester = (SELECT CurrentSemester FROM Students WHERE StudentID = ?)";
				PreparedStatement pstm = conn.prepareStatement(query);
				pstm.setString(1, y);
				
				ResultSet rs = pstm.executeQuery();
				System.out.println("   CourseID \t CourseCode \t ProfessorID \t Credits \t Prerequisites \t    Timings \t\t\t Title\n");
				while(rs.next())
				{
					
					System.out.println("------"+rs.getInt(1)+"-------------"+rs.getString(2)+"-------------"+rs.getInt(3)+"--------------"+rs.getInt(4)+"---------------"+rs.getString(5)+"--------"+rs.getString(6)+"---------"+rs.getString(7));
				}
				conn.close();
				
			}
			
			catch(SQLException e)
			{
				e.printStackTrace();
			}
			
		}
		
		
	
}
//Introduction to Artificial Intelligence