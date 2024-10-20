package adminManagmentPackage;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
/*viewing SELECT S.StudentID, S.Name, C.Title, E.Grade FROM Enrollments E JOIN Courses C ON E.CourseID = 
C.CourseID JOIN Students S ON E.StudentID = S.StudentID;*/
public class UpdateStudentRecords {
   public static void UpdateStuReco(Scanner Sc) {
	   Connection con = null;
	   try
	   {
		   con = DriverManager.getConnection(ConnDs.url, ConnDs.userName, ConnDs.password);
		   //query for viewing the table where the grades are not assigned
		    String viewing = "SELECT S.StudentID, S.Name,E.Grade , C.Title FROM Enrollments E JOIN Courses C ON E.CourseID = C.CourseID JOIN Students S ON E.StudentID = S.StudentID "
		    		+ "where E.Grade is null;";
		    Statement stmt = con.createStatement();
		    ResultSet rs = stmt.executeQuery(viewing);
		    System.out.println("StudentID" + "\t" + "Name" + "\t" + "Grade" + "\t" + "Title");
		    	while(rs.next())
		    	{
		    		Object StudentId = rs.getObject(1),Name = rs.getObject(2),Grade = rs.getObject(3),Title = rs.getObject(4);
		    		System.out.println(StudentId + "\t" + Name + "\t" + Grade + "\t" + Title);
		    	}
		    String Updating = " UPDATE Enrollments SET Grade = 'A' WHERE EnrollmentID = ?";
		    System.out.println("Choose The Enrollment Id Which You want To Update");
		    int choice = Sc.nextInt();
		    PreparedStatement pstmt = con.prepareStatement(Updating);
		    pstmt.setInt(1,choice);
		    int rowsAffected = pstmt.executeUpdate();
            	if (rowsAffected > 0)
            	{
            		System.out.println("Update successful! " + rowsAffected + " row(s) affected.");
            	} 
            	else
            	{
            		System.out.println("No rows affected.");
            	}
	   }
	   catch(SQLException ex)
	   {
		   System.out.println("You have an Sql exception");
		   ex.printStackTrace();
	   }
	   finally
	   {
		   try
		   {
			   if(con != null)
			   {
				   con.close();
			   }
		   }
		   catch(SQLException ex)
		   {
			   ex.printStackTrace();
		   }
	   }
   }
}
