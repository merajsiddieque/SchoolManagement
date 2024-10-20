package SqlDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import MainPackage.Main;

public class DBConnect 
{

		public static void StudentDB(String username,int x)
	    {
		try
		{
			String url = "jdbc:mysql://localhost:3306/ums";
			String userName = "root";
			String password = "a!am";
			
			
			Connection conn = DriverManager.getConnection(url,userName,password);
			
			Scanner sc = new Scanner(System.in);
			
			switch (x) 
			{
            case 1:
                String query = "SELECT C.Title, C.Timings FROM Enrollments E JOIN Courses C ON E.CourseID = C.CourseID WHERE E.StudentID = ?";
			PreparedStatement pstm = conn.prepareStatement(query);
			pstm.setString(1, username);
			
			ResultSet rs = pstm.executeQuery();
			System.out.println("      Timings \t\t\t\t Courses\n");
			while(rs.next())
			{
				
				System.out.println("----"+rs.getString(2)+"-----------------"+rs.getString(1));
			}
                break;
            case 2:
            	String query1 = "SELECT CourseID, CourseCode, ProfessorID, Credits, Prerequisites, Timings, Title FROM Courses WHERE Semester = (SELECT CurrentSemester FROM Students WHERE StudentID = ?)";
				PreparedStatement pstm1 = conn.prepareStatement(query1);
				pstm1.setString(1, username);
				
				ResultSet rs1 = pstm1.executeQuery();
				System.out.println("   CourseID \t CourseCode \t ProfessorID \t Credits \t Prerequisites \t    Timings \t\t\t Title\n");
				while(rs1.next())
				{
					
					System.out.println("------"+rs1.getInt(1)+"-------------"+rs1.getString(2)+"-------------"+rs1.getInt(3)+"--------------"+rs1.getInt(4)+"---------------"+rs1.getString(5)+"--------"+rs1.getString(6)+"---------"+rs1.getString(7));
				}
                break;
            case 3:
//                registerCourses(username);
                break;
            case 4:
            	String query3 = "SELECT C.Title, E.Grade FROM Enrollments E JOIN Courses C ON E.CourseID = C.CourseID WHERE E.StudentID = ?";
				PreparedStatement pstm3 = conn.prepareStatement(query3);
				pstm3.setString(1, username);
				
				ResultSet rs3 = pstm3.executeQuery();
				System.out.println("   Grades \t\tCourses\n");
				while(rs3.next())
				{
					
					System.out.println("-----"+rs3.getString(2)+"----------"+rs3.getString(1));
				}
                break;
                
            case 5:
            	System.out.println("Enter the Course ID you want to Drop: ");
            	int CourseID = sc.nextInt();
            	String query4 = "DELETE FROM Enrollments WHERE StudentID = ? AND CourseID = ?";
				PreparedStatement pstm4 = conn.prepareStatement(query4);
				pstm4.setString(1, username);
				pstm4.setInt(2, CourseID);
				pstm4.executeUpdate();
				System.out.println("Course dropped Successfully!");
                break;
                
            case 6:
            	System.out.println("Enter your complaint description!");
            	String complaintDescription = sc.nextLine();
            	
            	String query5 = "insert into complaints (StudentID,Status,ComplaintDescription) values (?,\"pending\",?)";
				PreparedStatement pstm5 = conn.prepareStatement(query5);
				pstm5.setString(1, username);
				pstm5.setString(2, complaintDescription);
				
				pstm5.executeUpdate();
				
				System.out.println("Complaint submitted Successfully!");
                break;
                default:
                	System.out.println("Student Functionality not Found!");
                	break;
			
			}
			conn.close();
			
		 }
		
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
	   }
		
		
		public static void professorDB(int x)
		{
			try
			{
				String url = "jdbc:mysql://localhost:3306/ums";
				String userName = "root";
				String password = "a!am";
				
				Scanner sc = new Scanner(System.in);
				
				Connection conn = DriverManager.getConnection(url,userName,password);
				
				switch (x) 
				{
	            case 1:
	            	System.out.println("Enter your Professor ID");
	       		 int professorID = sc.nextInt();
	       		 
				String query = "SELECT CourseID, CourseCode, Title, Timings FROM Courses WHERE ProfessorID = ?";
				PreparedStatement pstm = conn.prepareStatement(query);
				
				pstm.setInt(1, professorID);
				
				ResultSet rs = pstm.executeQuery();
				System.out.println("     CourseID \t\tCourseCode \t\t Timing \t\t Title \n");
				while(rs.next())
				{
					
					System.out.println("-------"+rs.getInt(1)+"-----------------"+rs.getString(2)+"----------------"+rs.getString(4)+"-----------------"+rs.getString(3));
				}
				break;
				
	            case 2:
//	            	condition
	            	
	            	break;
				
	            case 3:
	            	System.out.println("Enter Course ID ");
	       		 int CourseID = sc.nextInt();
	       		 
				String query1 = "SELECT S.StudentID, S.Name FROM Enrollments E JOIN Students S ON E.StudentID = S.StudentID WHERE E.CourseID = ?";
				PreparedStatement pstm1 = conn.prepareStatement(query1);
				
				pstm1.setInt(1, CourseID);
				
				ResultSet rs1 = pstm1.executeQuery();
				System.out.println("     StudentID \t\tStudentName\n");
				while(rs1.next())
				{
					
					System.out.println("-------"+rs1.getString(1)+"----------"+rs1.getString(2));
				}
				break;
				
				default:
					System.out.println("Professor Functionality doesn't exist!");
				
				
				}
				conn.close();
				
			}
			
			catch(SQLException e)
			{
				e.printStackTrace();
			}
			
		}
		

		
		
		public static void main(String[] args) {
			professorDB(3);
		}
		
		
	
}