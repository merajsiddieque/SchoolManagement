package StudentPackage;
import MainPackage.Main;
import SqlDB.DBConnect;
import java.util.Scanner;
import passwordPackage.passwords;

public class student extends DBConnect{
	Scanner sc = new Scanner(System.in);

    public static void StudentLogin() 
    {
        Scanner sc = new Scanner(System.in);
        String username, pass;

        System.out.println("Enter your username:");
        username = sc.nextLine();

//        String id = "select id from passwordDatabase where id ='" + username + "'";

        System.out.println("Enter your password:");
        pass = sc.nextLine();

//        String password = "select password from passwordDatabase where password ='" + pass + "'";

        if (passwords.signin(username, pass)){ 
            System.out.println("You have successfully entered the Student Panel");
            System.out.println("Welcome " + username + "!");
            System.out.println("\n 1. View Schedule \n 2. View Available Courses \n 3. Register For Courses");
            System.out.println(" 4. Track Academic Progress \n 5. Drop Courses \n 6. Submit Complaints \n 7. Exit \n 8. Logout");

            int funcChoice = sc.nextInt();
            while(funcChoice < 1 || funcChoice > 8) {
            	System.out.println("Invalid choice try again");
                System.out.println("\n 1. View Schedule \n 2. View Available Courses \n 3. Register For Courses");
                System.out.println(" 4. Track Academic Progress \n 5. Drop Courses \n 6. Submit Complaints \n 7. Exit \n 8. Logout");
            	int temp_choice = sc.nextInt();
            	funcChoice = temp_choice;
            }
            switch (funcChoice) {
                case 1:
                    viewSchedule(username);
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
                    dropCourses(username);
                    break;
                case 6:
                    submitComplaints(username);
                    break;
                case 7:
                    System.out.println("Have a nice day! You have successfully exited the program.");
                    System.exit(0);
                    return;
                case 8:
                	Main.main(null);
                	break;
            }
        } else {
            while (true) 
            {
                System.out.println("Invalid login! Do you want to try again? (Y/N)");
                String retryChoice = sc.nextLine();

                if (retryChoice.equalsIgnoreCase("Y")) 
                {
                    StudentLogin();
                    return;
                } else if (retryChoice.equalsIgnoreCase("N")) {
                    System.out.println("1. Main page \n 2. Exit");
                    int choice = sc.nextInt();
                    if (choice == 1) 
                    {
                    	Main.main(null);
                    } else if (choice == 2)
                    {
                        System.out.println("Exiting... Goodbye!");
                        System.exit(0);
                    }
                    return;
                } else 
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

    	
    	StudentDB(username,2);
    }

    public static void registerCourses(String username) 
    {
    	StudentDB(username,3);
    }

   

	public static void trackAcademicProgress(String username) 
    {
		StudentDB(username,4);
    }

    public static void dropCourses(String username) 
    {	
    	StudentDB(username,5);
    }

    public static void submitComplaints(String username) 
    {
    	StudentDB(username,6);
    }
    
    
    public static void main(String[] args) 
    {
    	StudentLogin();
		
	}
    
}
