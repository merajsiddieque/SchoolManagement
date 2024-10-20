package StudentPackage;
import MainPackage.Main;
import SqlDB.DBConnect;
import java.util.Scanner;
import passwordPackage.passwords;

public class student extends DBConnect{
    public static void StudentLogin(Scanner sc) 
    {
        String username, pass;

        System.out.println("Enter your username:");
        username = sc.nextLine();
        System.out.println("Enter your password:");
        pass = sc.nextLine();
        if (passwords.signin(username, pass))
        { 
            System.out.println("You have successfully entered the Student Panel");
            System.out.println("Welcome " + username + "!");
            System.out.println("\n 1. View Schedule \n 2. View Available Courses \n 3. Register For Courses");
            System.out.println(" 4. Track Academic Progress \n 5. Drop Courses \n 6. Submit Complaints \n 7. Exit \n 8. Logout");

            int funcChoice = sc.nextInt();
            while(funcChoice < 1 || funcChoice > 8)
            {
            	System.out.println("Invalid choice try again");
                System.out.println("\n 1. View Schedule \n 2. View Available Courses \n 3. Register For Courses");
                System.out.println(" 4. Track Academic Progress \n 5. Drop Courses \n 6. Submit Complaints \n 7. Exit \n 8. Logout");
            	int temp_choice = sc.nextInt();
            	funcChoice = temp_choice;
            }
            switch (funcChoice) 
            {
                case 1:
                    viewSchedule(sc);
                    break;
                case 2:
                    viewAvailableCourses(username);
                    break;
                case 3:
                    registerCourses(username);
                    break;
                case 4:
                    trackAcademicProgress(username);
                    break;
                case 5:
                    dropCourses(username,sc);
                    break;
                case 6:
                    submitComplaints(username,sc);
                    break;
                case 7:
                    System.out.println("Have a nice day! You have successfully exited the program.");
                    System.exit(0);
                    return;
                case 8:
                	Main.main(null);
                	break;
            }
        }
        else
        {
            while (true) 
            {
                System.out.println("Invalid login! Do you want to try again? (Y/N)");
                String retryChoice = sc.nextLine();

                if (retryChoice.equalsIgnoreCase("Y")) 
                {
                    StudentLogin(sc);
                    return;
                }
                else if (retryChoice.equalsIgnoreCase("N"))
                {
                    System.out.println("1. Main page \n 2. Exit");
                    int choice = sc.nextInt();
                    if (choice == 1) 
                    {
                    	Main.main(null);
                    }
                    else if (choice == 2)
                    {
                        System.out.println("Exiting... Goodbye!");
                        System.exit(0);
                    }
                    return;
                }
                else 
                {
                    System.out.println("Invalid choice! Please enter Y or N.");
                }
            }
        }
    }
    
     public static void viewSchedule(String username) 
    {
    	
    	StudentDB(username,1);
    }

     
    public static void viewAvailableCourses(String username)
    {

    	
    	DBviewAvailableCourses(username);
    }

    public static void registerCourses(String username) 
    {
        System.out.println("Registering for courses...");
    }

    public static void viewSchedule(String username) 
    {
    	
    	DBviewSchedule(username);
    }

   

	public static void trackAcademicProgress(String username) 
    {
		StudentDB(username,4);
    }

    public static void dropCourses(String username) 
    {
    	Scanner sc = new Scanner(System.in);
    	System.out.println("Enter the Course ID you want to Drop: ");
    	int CourseID = sc.nextInt();
    	
        DBdropCourses(username,CourseID);
    }
    public static void submitComplaints(String username,Scanner sc) 
    {
    	System.out.println("Enter your complaint description!");
    	Scanner sc = new Scanner(System.in);
    	String complaintDescription = sc.nextLine();
    	DBsubmitComplaints(username,complaintDescription);
    }
    
    
//    public static void main(String[] args) 
//    {
//    	StudentLogin();
//		
//	}
//    
}
