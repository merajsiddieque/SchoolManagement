package SqlDB;

import adminManagmentPackage.ConnDs;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import Exceptional_Handling.ExceedCreditLimit;
public class DBConnect 
{

		public static void StudentDB(String username,int x,Scanner sc)
	    {
		try
		{
			Connection conn = DriverManager.getConnection(ConnDs.url,ConnDs.userName,ConnDs.password);
			switch (x) 
			{
            case 1:
			//viewSchedule
            String query = "SELECT C.Timings,C.Title FROM Enrollments E JOIN Courses C ON E.CourseID = C.CourseID WHERE E.StudentID = ?";
			PreparedStatement pstm = conn.prepareStatement(query);
			pstm.setString(1, username);
			ResultSet rs = pstm.executeQuery();
//			System.out.println("      Timings \t\t\t\t Courses\n");
//			while(rs.next())
//			{
//				
//				System.out.println("----"+rs.getString(2)+"-----------------"+rs.getString(1));
//			}
			ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();

			
			   System.out.println("---------------------------------------------------------------------");
	            for (int i = 1; i <= columnCount; i++) {
	                System.out.printf("|    "+"%-20s", rsmd.getColumnName(i));  // Adjust width based on column size
	            }
	            System.out.println();
	            System.out.println("---------------------------------------------------------------------");
	            
	            // Print rows
	            while (rs.next()) {
	                for (int i = 1; i <= columnCount; i++) {
	                    System.out.printf("|    "+"%-20s", rs.getObject(i));
	                }
	                System.out.println();
	            }

	            System.out.println("---------------------------------------------------------------------");        
			
			
			
                break;
            case 2:
			//courseview
            	String query1 = "SELECT CourseID, CourseCode, ProfessorID, Credits, Prerequisites, Timings, Title FROM Courses WHERE Semester = (SELECT CurrentSemester FROM Students WHERE StudentID = ?)";
				PreparedStatement pstm1 = conn.prepareStatement(query1);
				pstm1.setString(1, username);
				
				ResultSet rs1 = pstm1.executeQuery();
//				System.out.println("   CourseID \t CourseCode \t ProfessorID \t Credits \t Prerequisites \t    Timings \t\t\t Title\n");
//				while(rs1.next())
//				{
//					System.out.println("     +"+rs1.getInt(1)+"      +      "+rs1.getString(2)+"      +       "+rs1.getInt(3)+"      +       "+rs1.getInt(4)+"        +      "+rs1.getString(5)+"   +    "+rs1.getString(6)+"    +    "+rs1.getString(7)+"+\n");
//					System.out.println("-----------------------------------------------------------------------------------------------------------------------");
//				}
				
				ResultSetMetaData rsmd1 = rs1.getMetaData();
	            int columnCount1 = rsmd1.getColumnCount();

				
				   System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		            for (int i = 1; i <= columnCount1; i++) {
		                System.out.printf("|    "+"%-20s", rsmd1.getColumnName(i));  // Adjust width based on column size
		            }
		            System.out.println();
		            System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

		            // Print rows
		            while (rs1.next()) {
		                for (int i = 1; i <= columnCount1; i++) {
		                    System.out.printf("|    "+"%-20s", rs1.getObject(i));
		                }
		                System.out.println();
		            }

		            System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                break;
            case 3:
            	String query21 = "select currentSemester from students where studentId = ?";
            	PreparedStatement pstmte = conn.prepareStatement(query21);
            	pstmte.setString(1, username);  // Assuming 'username' holds the student's ID
            	ResultSet ress = pstmte.executeQuery();

            	if (ress.next())
            	{
            	    int semester = ress.getInt(1) + 1;
                    System.out.println(semester);
                    String query279 = "SELECT SUM(Credits) AS TotalCredits\r\n"
                    		+ "FROM Enrollments e\r\n"
                    		+ "JOIN Courses c ON e.CourseID = c.CourseID\r\n"
                    		+ "WHERE e.StudentID = ?" + " AND c.Semester = ?;";
                    PreparedStatement pstmt699 = conn.prepareStatement(query279);
                    pstmt699.setString(1,username);
                    pstmt699.setInt(2, semester);
                    ResultSet rst699 = pstmt699.executeQuery();
                    rst699.next();
                    int credits = rst699.getInt(1);
            	    String query22 = "SELECT CourseID,CourseCode \r\n"
            	    		+ "FROM Courses \r\n"
            	    		+ "WHERE Semester = ? "
            	    		+ "  AND CourseID NOT IN (\r\n"
            	    		+ "    SELECT CourseID \r\n"
            	    		+ "    FROM Enrollments \r\n"
            	    		+ "    WHERE StudentID = ? \r\n"
            	    		+ "      AND Semester = ?\r\n"
            	    		+ "  );\r\n"
            	    		+ "";
            	    PreparedStatement stmt32 = conn.prepareStatement(query22);
            	    stmt32.setInt(1, semester);
            	    stmt32.setString(2,username);
            	    stmt32.setInt(3, semester);
            	    ResultSet rt = stmt32.executeQuery();

            	    System.out.println("courseId" + "\t" + "courseCode");
            	    while (rt.next()) {
            	        Object cid = rt.getObject(1), ccode = rt.getObject(2);
            	        System.out.println(cid + "\t" + "       " + ccode);
            	    }
            	    System.out.println("You have " + (16 - credits) + " credits left");
            	    System.out.println("Enter The Course Id You Want To Register For");
            	    int crs_id = sc.nextInt();
            	    sc.nextLine();
            	    String creditssq = "select credits from courses where courseid = " + crs_id;
            	    Statement stmt678 = conn.createStatement();
            	    ResultSet rst678 = stmt678.executeQuery(creditssq);
            	    rst678.next();
            	    int credits_regcors = rst678.getInt(1);
            	    if(credits + credits_regcors <= 16) {
            	    	String query69 = "Select Prerequisites FROM Courses WHERE CourseID ="+String.valueOf(crs_id);
            	    	Statement stmt69 = conn.createStatement();
            	    	ResultSet rst69 = stmt69.executeQuery(query69);
            	    	rst69.next();
            	    	String value69 = rst69.getString(1);
//            	    	System.out.println(value69);
            	    	String query23 = "SELECT Grade\r\n"
            	    			+ "FROM Enrollments\r\n"
            	    			+ "WHERE StudentID = ? \r\n"
            	    			+ "  AND CourseID IN (SELECT CourseID FROM Courses WHERE CourseCode = ?)\r\n"
            	    			+ "  AND Grade IN ('A','B','C','D');";
            	    	PreparedStatement psgdijng = conn.prepareStatement(query23);
            	    	psgdijng.setString(1, username);
            	    	psgdijng.setString(2, value69);
            	    	ResultSet rst = psgdijng.executeQuery();

            	    	int cnt = 0;
            	    	while (rst.next()) {
            	    		cnt++;
            	    	}
            	    	if (cnt == 0 )
            	    	{
            	    		System.out.println("You Are Not Eligible For Registration");
            	    	}
            	    	else
            	    	{
            	    		String sem = "SELECT semester FROM courses WHERE courseID = ?";
            	    		PreparedStatement psdff = conn.prepareStatement(sem);
            	    		psdff.setInt(1, crs_id);
            	    		ResultSet rstt = psdff.executeQuery();

            	    		if (rstt.next())
            	    		{
//            	            	int value = rstt.getInt(1);
            	    			String njf = "INSERT INTO Enrollments (StudentId, CourseID) VALUES (?, ?)";
            	    			PreparedStatement njfpstmt = conn.prepareStatement(njf);
            	    			njfpstmt.setString(1, username);
            	    			njfpstmt.setInt(2, crs_id);
//            	            	njfpstmt.setInt(3, value);
      
            	    			int rows = njfpstmt.executeUpdate();
            	    			if (rows > 0)
            	    			{
            	    				System.out.println("You have registered successfully");
            	    			}
            	    			else
            	    			{
            	    				System.out.println("Operation is not successful");
            	    			}
            	    		} 
            	    		else
            	    		{
            	    			System.out.println("Semester not found for the course.");
            	    		}
            	    	}
            	    	
            	    }
            	    else
            	    {
            	       //credits exceed limit
            	    	try
            	    	{
            	    	   throw new ExceedCreditLimit();
            	    	}
            	    	catch(ExceedCreditLimit ex)
            	    	{
            	    		System.out.println(ex);
            	    	}
            	    	System.out.println("Do you want to Try again ! Y/N");
            	    	String YorNo = sc.nextLine();
            	    	if(YorNo.equalsIgnoreCase("y"))
            	    	{
            	    		StudentDB(username,3,sc);
            	    	}
            	    }
            	} 
            	else
            	{
            	    System.out.println("No current semester found for the student.");
            	}
                break;
            case 4:
            	String query3 = "SELECT  E.Grade,C.Title FROM Enrollments E JOIN Courses C ON E.CourseID = C.CourseID WHERE E.StudentID = ?";
				PreparedStatement pstm3 = conn.prepareStatement(query3);
				pstm3.setString(1, username);
				
				ResultSet rs3 = pstm3.executeQuery();
//				System.out.println("   Grades \t\tCourses\n");
//				while(rs3.next())
//				{
//					
//					System.out.println("-----"+rs3.getString(2)+"----------"+rs3.getString(1));
//				}
//               
				ResultSetMetaData rsmd3 = rs3.getMetaData();
	            int columnCount3 = rsmd3.getColumnCount();

				
				   System.out.println("-----------------------------------------------------------------------");
		            for (int i = 1; i <= columnCount3; i++) {
		                System.out.printf("|    "+"%-20s", rsmd3.getColumnName(i));  // Adjust width based on column size
		            }
		            System.out.println();
					   System.out.println("-----------------------------------------------------------------------");

		            // Print rows
		            while (rs3.next()) {
		                for (int i = 1; i <= columnCount3; i++) {
		                    System.out.printf("|    "+"%-20s", rs3.getObject(i));
		                }
		                System.out.println();
		            }

					   System.out.println("-----------------------------------------------------------------------");
            
				
				break;
                
            case 5:
			//drop course this also incomplete this should ask which course you want to drop and also you should include the deadline 
            //inside the drop course            
            	String query51 = "SELECT * FROM enrollments WHERE studentid = ?";
            	PreparedStatement stmt51 = conn.prepareStatement(query51);
            	stmt51.setString(1, username);  // Use a PreparedStatement for safety
            	ResultSet rst51 = stmt51.executeQuery();
            	System.out.println("E_Id \tStudentId     CourseId     Grade");
            	while (rst51.next()) {
            	    int eid = rst51.getInt(1);
            	    String stsid = rst51.getString(2);
            	    int ccid = rst51.getInt(3);
            	    String GGradee = rst51.getString(4);
            	    System.out.println(eid + "\t" + stsid + "\t" + ccid + "\t     " + GGradee);
            	}

            	System.out.println("Enter the Course ID you want to Drop: ");
            	int CourseID = sc.nextInt();

            	String query4 = "DELETE FROM Enrollments WHERE StudentID = ? AND CourseID = ?";
            	PreparedStatement pstm4 = conn.prepareStatement(query4);
            	pstm4.setString(1, username);  // Assuming username is String
            	pstm4.setInt(2, CourseID);
            	int rowsAffected = pstm4.executeUpdate();

            	if (rowsAffected > 0) {
            	    System.out.println("Course dropped successfully!");
            	} else {
            	    System.out.println("No course found with the provided CourseID for this student.");
            	}

            	// Closing resources
            	try {
            	    if (rst51 != null) rst51.close();
            	    if (stmt51 != null) stmt51.close();
            	    if (pstm4 != null) pstm4.close();
            	} catch (SQLException ex) {
            	    ex.printStackTrace();
            	}
            	break;
            case 6:
			//complaint
            	sc.nextLine();
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
		public static void professorDB(String username,int x,Scanner sc,int professorId)
		{
			try
			{			
				Connection conn = DriverManager.getConnection(ConnDs.url,ConnDs.userName,ConnDs.password);
				switch (x) 
				{
	            case 1:
	            //view schedule
				String query = "SELECT CourseID, CourseCode, Timings,Title  FROM Courses WHERE ProfessorID = ?";
				PreparedStatement pstm = conn.prepareStatement(query);
				pstm.setInt(1, professorId);
				ResultSet rs = pstm.executeQuery();
//				System.out.println("     CourseID \t\tCourseCode \t\t Timing \t\t Title \n");
//				while(rs.next())
//				{
//					
//					System.out.println("-------"+rs.getInt(1)+"-----------------"+rs.getString(2)+"----------------"+rs.getString(4)+"-----------------"+rs.getString(3));
//				}
				
				
				ResultSetMetaData rsmd = rs.getMetaData();
	            int columnCount = rsmd.getColumnCount();

				
				   System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		            for (int i = 1; i <= columnCount; i++) {
		                System.out.printf("|    "+"%-20s", rsmd.getColumnName(i));  // Adjust width based on column size
		            }
		            System.out.println();
		            System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

		            // Print rows
		            while (rs.next()) {
		                for (int i = 1; i <= columnCount; i++) {
		                    System.out.printf("|    "+"%-20s", rs.getObject(i));
		                }
		                System.out.println();
		            }

		            System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            
				break;
	            case 2:
//	            	UPDATE Courses SET grade = ? WHERE CourseID = ? AND ProfessorID = ?;
		            professorDB(username, 1, sc,professorId);
	            	System.out.println(" 1.Update Timing\n 2.Update Grade");
	            	int updating = sc.nextInt();
		       		 System.out.println("Enter your Course ID");
		       		 int CourseID1 = sc.nextInt();
	            	if(updating==1) 
	            	{
	            		sc.nextLine();
	            		System.out.println("Enter New Timing");
			       		 String timing = sc.nextLine();
		       		
		       		 
					String query2 = "UPDATE Courses SET Timings = ? WHERE CourseID = ? AND ProfessorID = ?";
					PreparedStatement pstm2 = conn.prepareStatement(query2);
					
					pstm2.setString(1, timing);
					pstm2.setInt(2, CourseID1);
					pstm2.setInt(3, professorId);
					
					pstm2.executeUpdate();
					
					System.out.println("Timing updated successfully!");
					
					conn.close();
					
					
	            	}
	            	else if(updating==2)
	            	{
	            		sc.nextLine();
	            		System.out.println("Enter Student ID");
			       		 String StudentID = sc.nextLine();
			       		 
	            		System.out.println("Enter New Grade");
			       		 String grade= sc.nextLine();
		       		
		       		 
					String query2 = "UPDATE Enrollments e JOIN Courses c ON e.CourseID = c.CourseID SET e.grade = ? WHERE c.CourseID = ? AND c.ProfessorID = ? AND e.StudentID = ?";
					PreparedStatement pstm2 = conn.prepareStatement(query2);
					
					pstm2.setString(1, grade);
					pstm2.setInt(2, CourseID1);
					pstm2.setInt(3, professorId);
					pstm2.setString(4, StudentID);
					
					pstm2.executeUpdate();
					
					System.out.println("Grade updated successfully!");
					
					conn.close();
					
	            	}
	            	else
	            	{
	            		System.out.println("Invalid choice");
	            	}

	            	break;
	            case 3:
	            professorDB(username, 1, sc,professorId);
	            System.out.println("Enter Course ID");
	       		 int CourseID = sc.nextInt();
				String query1 = "SELECT S.StudentID, S.Name FROM Enrollments E JOIN Students S ON E.StudentID = S.StudentID WHERE E.CourseID = ?";
				PreparedStatement pstm1 = conn.prepareStatement(query1);
				
				pstm1.setInt(1, CourseID);
				
				ResultSet rs1 = pstm1.executeQuery();
//				System.out.println("     StudentID \t\tStudentName\n");
//				while(rs1.next())
//				{
//					
//					System.out.println("-------"+rs1.getString(1)+"----------"+rs1.getString(2));
//				}
//				
				ResultSetMetaData rsmd1 = rs1.getMetaData();
	            int columnCount1 = rsmd1.getColumnCount();

				
				   System.out.println("----------------------------------------------------");
		            for (int i = 1; i <= columnCount1; i++) {
		                System.out.printf("|    "+"%-20s", rsmd1.getColumnName(i));  // Adjust width based on column size
		            }
		            System.out.println();
					   System.out.println("----------------------------------------------------");

		            // Print rows
		            while (rs1.next()) {
		                for (int i = 1; i <= columnCount1; i++) {
		                    System.out.printf("|    "+"%-20s", rs1.getObject(i));
		                }
		                System.out.println();
		            }

					   System.out.println("----------------------------------------------------");
            
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
}