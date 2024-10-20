package adminManagmentPackage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
//complaint id and student id and status and complainDescription
public class ManageComplaints {
   public static void manage_complaints(Scanner Sc) {
	   Connection conn = null;
	   try 
	   {
		   conn = DriverManager.getConnection(ConnDs.url, ConnDs.userName,ConnDs.password);
		   String query = "select * from complaints where Status = 'Pending';";
		   Statement stmt = conn.createStatement();
		   ResultSet rs = stmt.executeQuery(query);
		   System.out.println("Showing The Complaints Table Where Status is pending");
		   System.out.println("ComplaintID" + "\t" + "StudentID" + "\t" + "Status" + "\t" + "ComplaintDescription");
		   while(rs.next()) 
		   {
			    Object CId = rs.getObject(1),SID = rs.getObject(2),Status = rs.getObject(3),ComplaintDes = rs.getObject(4);
			    System.out.println(CId + "\t" + SID + "\t" + Status + "\t" + ComplaintDes);
		   }
		   System.out.println("Choose The Complaint Which You Want To Resolve");
		   int choice = Sc.nextInt();  // Get the complaint ID from user input
		   String query2 = "UPDATE Complaints SET Status = 'Resolved' WHERE ComplaintID = ?;";
		   PreparedStatement pstmt = conn.prepareStatement(query2);
		   pstmt.setInt(1, choice);
		   int rowsAffected = pstmt.executeUpdate();
		   if (rowsAffected > 0)
		   {
			   System.out.println("Complaint resolved successfully!");
		   } 
		   else
		   {
		       System.out.println("No complaint found with the given ID.");
		   }
	   }
	   catch(SQLException ex) {
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
			   System.out.println("You Have An Sql Exception");
			   ex.printStackTrace();
		   }
	   }
   }
}
