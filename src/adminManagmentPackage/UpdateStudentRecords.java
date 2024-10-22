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
		    String viewing = "SELECT E.ENROLLMENTID, S.studentID, C.semester, C.Title FROM Enrollments E JOIN Courses C ON E.CourseID = \r\n"
		    		+ "C.CourseID JOIN Students S ON E.StudentID = S.StudentID Where E.Grade is  NULL";
		    Statement stmt = con.createStatement();
		    ResultSet rs = stmt.executeQuery(viewing);
		    System.out.println("E_ID" + "\t" + "S_Code" + "\t\t" + "Sem     " + "  " + "Title");
		    	while(rs.next())
		    	{
		    		Object StudentId = rs.getObject(1),Name = rs.getObject(2),Grade = rs.getObject(3),Title = rs.getObject(4);
		    		System.out.println(StudentId + "\t" + Name + "\t" + Grade + "\t" + Title);
		    	}
		    String Updating = " UPDATE Enrollments SET Grade = ? WHERE EnrollmentID = ?";
		    System.out.println("Choose The E_Id Which You want To Update");
		    int choice = Sc.nextInt();
		    Sc.nextLine();
		    System.out.println("Give Grade:");
		    String grade = Sc.nextLine();
		    PreparedStatement pstmt = con.prepareStatement(Updating);
		    pstmt.setString(1,grade);
		    pstmt.setInt(2,choice);
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
