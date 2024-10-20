package adminManagmentPackage;
//Assigning professors is also not completed
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
/*3. Assign Professors to Courses  -- Viewing courses without assigned professors 
SELECT * FROM Courses WHERE ProfessorID IS NULL;UPDATE Courses SET ProfessorID = 1 WHERE CourseID = 1;*/

public class AssignProfessors {
   public static void Assign_Professors(Scanner Sc){
	   Connection conn = null;
	   try
	   {
		   conn = DriverManager.getConnection(ConnDs.url, ConnDs.userName, ConnDs.password);
		   System.out.println("Showing All The Courses Without Assigned Professor");
		   String query = "SELECT * FROM Courses WHERE ProfessorID IS NULL;";
		   Statement stmt = conn.createStatement();
		   ResultSet rs = stmt.executeQuery(query);
		   while(rs.next())
		   {
		        for (int i = 1; i <= 8; i++)
		        {
		        	Object value = rs.getObject(i);
		        	System.out.print(value  + "\t");
		        }
		        System.out.println();
		   }
		   System.out.println("Select The Course For Which You Want To Assign The Professor");
		   int courseId = Sc.nextInt();
		   Sc.nextLine();
		   //updating the 
		   String updating = "UPDATE Courses SET ProfessorID = ? WHERE CourseID = ?;";
		   PreparedStatement pstmt = conn.prepareStatement(updating);
		   String prof = "Select * from professors";
		   ResultSet prrs = stmt.executeQuery(prof);
		   System.out.println("Choose The professor You want To Assign The Course");
		   System.out.println("ProfessorID" + "\t" + "Name");
		   while(prrs.next())
		   {
			   Object ID = prrs.getObject(1);
			   Object Name = prrs.getObject(2);
			   System.out.println(ID + "\t" + Name);
		   }
		   int proffId = Sc.nextInt();
		   pstmt.setInt(1,courseId);
		   pstmt.setInt(2, proffId);
		   int rows = pstmt.executeUpdate();
		   if(rows > 0)
		   {
			   System.out.println("Professor Id For The Course Is updated Successfully");
		   }
		   else
		   {
			   System.out.println("Operation Was Not Succesful");
		   }
	   }
	   catch(SQLException ex)
	   {
		   System.out.println("You have an Sql Exception");
		   ex.printStackTrace();
	   }
	   finally 
	   {
		   try 
		   	{
			   	if(conn != null)
			   	{
			   		conn.close();
			   	}
		   	}
		   catch(SQLException ex)
		   	{
			   	System.out.println("You have an Sql Exception");
			   	ex.printStackTrace();
		   	}
	   }
   }
}
