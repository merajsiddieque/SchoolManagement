//sql queries for view and add and delete
//
package adminManagmentPackage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
//import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Scanner;

import Exceptional_Handling.InvalidCoursIdException;
//delete courses and 
import Interface.view_courses;
//view courses is also not completed
public class ManageCourses implements view_courses{
    public void view_course() 
    {
		Connection conn = null;
			try 
			{
				conn = DriverManager.getConnection(ConnDs.url,ConnDs.userName,ConnDs.password);
				String query = "SELECT CourseID,CourseCode,Semester,ProfessorID,Credits,Prerequisites,Timings,Title FROM Courses;";
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(query);
//				System.out.println("CourseID CourseCode Semester ProfessorID Credits  Prerequisites Timings Title");
//				while(rs.next())
//				{
//			        for (int i = 1; i <= 8; i++)
//			        {
//			        	Object value = rs.getObject(i);
//			        	System.out.print(value + "\t");
//			        }
//			        System.out.println();
//				}
				ResultSetMetaData rsmd = rs.getMetaData();
	            int columnCount = rsmd.getColumnCount();

				
				   System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		            for (int i = 1; i <= columnCount; i++) {
		                System.out.printf("|    "+"%-20s", rsmd.getColumnName(i));  // Adjust width based on column size
		            }
		            System.out.println();
		            System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

		            // Print rows
		            while (rs.next()) {
		                for (int i = 1; i <= columnCount; i++) {
		                    System.out.printf("|    "+"%-20s", rs.getObject(i));
		                }
		                System.out.println();
		            }

		            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            
				
			}
			catch(SQLException ex)
			{
				System.out.println("You have an sql Exception");
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
    //delete_course is not fullfilled still input errors
    public void delete_course(Scanner Sc)
    {
    	
    	Connection conn = null;
    		try 
    		{
    		    conn = DriverManager.getConnection(ConnDs.url, ConnDs.userName, ConnDs.password);
    		    String query = "DELETE FROM Courses WHERE CourseID = ?;";
    		    //What should be the input for the delete courses like course_id or
    		    view_course();
    		    System.out.println("Select The Course ID Which You Want To Delete");
    		    int course_id = Sc.nextInt();
    		    System.out.println("Successfully Deleted The Course");
    		}
    		catch(SQLException ex)
    		{
    			System.out.println("You have an Sql Exception ");
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
    //Need to check about the Timings like using date or day name check that things    
    public void add_course(Scanner Sc)
    {
    	Connection conn = null;
            try {
                conn = DriverManager.getConnection(ConnDs.url, ConnDs.userName, ConnDs.password);
                // SQL query with placeholders for dynamic values
                String sql = "INSERT INTO Courses (CourseID, CourseCode, Title, ProfessorID, Credits, Prerequisites, Timings, "
                           + "EnrollmentLimit, Syllabus) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
                // Prepare the statement
                PreparedStatement pstmt = conn.prepareStatement(sql);
                System.out.println("Enter Course ID: ");
                int courseId = Sc.nextInt();
                Sc.nextLine();
                boolean bool = true;
                try {
                	String query = "select * from Courses where CourseID = ?";
                	PreparedStatement pstmtin = conn.prepareStatement(query);
                	pstmtin.setInt(1, courseId);
                	ResultSet rs3 = pstmtin.executeQuery();
                	int cnt = 0;
                	while(rs3.next())
                	{
                		cnt++;
                	}
                	pstmtin.close();
                	if(cnt == 1)
                	{
                		bool = false;
                		throw new InvalidCoursIdException(courseId);
                	}
                }
                catch(InvalidCoursIdException ex)
                {
                	System.out.println(ex);
                	System.out.println("Do you Want To Try Again ! Y / N");
                	String yesorNo = Sc.nextLine();
                	if(yesorNo.equalsIgnoreCase("Y"))
                	{
                	   	add_course(Sc);
                	}
                	else 
                	{
                		Admin_Managment.admin_manage(Sc);
                	}
                	conn.close();
                }
               if(bool)
               {
            	   Sc.nextLine();  // Consume newline

            	   System.out.println("Enter Course Code: ");
            	   String courseCode = Sc.nextLine();

            	   System.out.println("Enter Course Title: ");
            	   String title = Sc.nextLine();

            	   System.out.println("Enter Professor ID (or 0 for NULL): ");
            	   int professorId = Sc.nextInt();
            	   Sc.nextLine();  // Consume newline
            	   Integer profId = professorId == 0 ? null : professorId;  // Handle NULL case

            	   System.out.println("Enter Course Credits: ");
            	   int credits = Sc.nextInt();
            	   Sc.nextLine();  // Consume newline

            	   System.out.println("Enter Prerequisites: ");
            	   String prerequisites = Sc.nextLine();

            	   System.out.println("Enter Timings (format: 'yyyy-[m]m-[d]d hh:mm:ss'): ");
            	   String timingInput = Sc.nextLine();
            	   Timestamp timings = null;
                try 
                {
                    timings = Timestamp.valueOf(timingInput);
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid timestamp format.");
                    return;
                }

                System.out.println("Enter Enrollment Limit: ");
                int enrollmentLimit = Sc.nextInt();
                Sc.nextLine();  // Consume newline

                System.out.println("Enter Course Syllabus: ");
                String syllabus = Sc.nextLine();

                // Set the values to the PreparedStatement using the index of the placeholders
                pstmt.setInt(1, courseId);
                pstmt.setString(2, courseCode);
                pstmt.setString(3, title);

                if (profId == null) {
                    pstmt.setNull(4, java.sql.Types.INTEGER);  // Set NULL for professor ID
                } else {
                    pstmt.setInt(4, profId);  // Set professor ID
                }
                pstmt.setInt(5, credits);
                pstmt.setString(6, prerequisites);
                pstmt.setTimestamp(7, timings);
                pstmt.setInt(8, enrollmentLimit);
                pstmt.setString(9, syllabus);

                // Execute the statement
                int rowsAffected = pstmt.executeUpdate();
                System.out.println("Insert successful. Rows affected: " + rowsAffected);
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