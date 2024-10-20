//sql queries for view and add and delete
package adminManagmentPackage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Scanner;
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
				System.out.println("     CourseID \t      CourseCode     Semester \t  ProfessorID        Credits          Prerequisites \tTimings   \t\t  \t Title\n");
				while(rs.next())
				{
			        for (int i = 1; i <= 8; i++)
			        {
			        	Object value = rs.getObject(i);
			        	System.out.print("\t"+value + "\t");
			        }
			        
			        System.out.println();
				}
				try {
					if(rs != null)
					{
						rs.close();
					}
					if(stmt != null)
					{
						stmt.close();
					}
				}
				catch(SQLException ex)
				{
					ex.printStackTrace();
				}
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
    		    PreparedStatement pstmt = conn.prepareStatement(query);
    		    pstmt.setInt(1,course_id);
    		    int rows = pstmt.executeUpdate();
    		    if(rows > 0)
    		    {
    		    	System.out.println("Successfully Deleted The Course");
    		    }
    		    else
    		    {
    		    	System.out.println("The operation was not successful");
    		    }
    		    pstmt.close();
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
    public void add_course(Scanner Sc)
    {
    	Connection conn = null;
            try {
            	conn = DriverManager.getConnection(ConnDs.url, ConnDs.userName, ConnDs.password);

                // SQL query to insert a course
                String sql = "INSERT INTO Courses (CourseID, CourseCode, Title, ProfessorID, Credits, Prerequisites, Timings) "
                           + "VALUES (?, ?, ?, ?, ?, ?, ?)";
                // Prepare the statement
                PreparedStatement pstmt = conn.prepareStatement(sql);
                System.out.println("Enter Course ID: ");
                int courseId = Sc.nextInt();
                Sc.nextLine();  // Consume newline

                System.out.println("Enter Course Code: ");
                String courseCode = Sc.nextLine();

                System.out.println("Enter Course Title: ");
                String title = Sc.nextLine();

                System.out.println("Enter Professor ID (or 0 for NULL): ");
                int professorId = Sc.nextInt();
                Sc.nextLine();
                Integer profId = professorId == 0 ? null : professorId;  // Handle NULL case

                System.out.println("Enter Course Credits: ");
                int credits = Sc.nextInt();
                Sc.nextLine();

                System.out.println("Enter Prerequisites (or leave blank for NULL): ");
                String prerequisites = Sc.nextLine();
                String prereq = prerequisites.trim().isEmpty() ? null : prerequisites;  // Handle NULL for Prerequisites

                System.out.println("Enter Timings (format: 'yyyy-[m]m-[d]d hh:mm'): ");
                String timingInput = Sc.nextLine();

                // Parse date and time without seconds
                Timestamp timings = null;
                String dayAndTime = null;  // String to store day and time (hh:mm) format

                try {
                    // Convert input to timestamp
                    timings = Timestamp.valueOf(timingInput + ":00");  // Adding ":00" for seconds

                    // Format the date-time to get day name and hh:mm
                    SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE");  // Day name, e.g., Monday
                    SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm"); // Time in hh:mm format

                    // Convert timestamp to date and format it
                    java.util.Date date = new java.util.Date(timings.getTime());
                    dayAndTime = dayFormat.format(date) + " " + timeFormat.format(date);  // Combine day name and time
                    System.out.println("Day and Time: " + dayAndTime);
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid timestamp format.");
                    return;
                }
                pstmt.setInt(1, courseId);
                pstmt.setString(2, courseCode);
                pstmt.setString(3, title);

                if (profId == null) {
                    pstmt.setNull(4, java.sql.Types.INTEGER);  // Set NULL for ProfessorID
                } else {
                    pstmt.setInt(4, profId);  // Set ProfessorID
                }

                pstmt.setInt(5, credits);

                if (prereq == null) {
                    pstmt.setNull(6, java.sql.Types.VARCHAR);  // Set NULL for Prerequisites
                } else {
                    pstmt.setString(6, prereq);  // Set Prerequisites
                }
                pstmt.setString(7, dayAndTime);  // Set Timings (parsed with hh:mm precision)
                int rowsAffected = pstmt.executeUpdate();
                System.out.println("Insert successful. Rows affected: " + rowsAffected);
                pstmt.close();
                conn.close();
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