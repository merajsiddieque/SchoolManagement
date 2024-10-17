package StudentPackage;

import java.util.Scanner;

import MainPackage.Main;

public class student {

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

        if (username.equals("user") && pass.equals("pass")) { 
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
                    viewSchedule();
                    break;
                case 2:
                    viewAvailableCourses();
                    break;
                case 3:
                    registerCourses();
                    break;
                case 4:
                    trackAcademicProgress();
                    break;
                case 5:
                    dropCourses();
                    break;
                case 6:
                    submitComplaints();
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

    public static void viewAvailableCourses()
    {
        System.out.println("Displaying available courses...");
    }

    public static void registerCourses() 
    {
        System.out.println("Registering for courses...");
    }

    public static void viewSchedule() 
    {
        System.out.println("Displaying schedule...");
    }

    public static void trackAcademicProgress() 
    {
        System.out.println("Tracking academic progress...");
    }

    public static void dropCourses() 
    {
        System.out.println("Dropping courses...");
    }

    public static void submitComplaints() 
    {
        System.out.println("Submitting complaints...");
    }
    
}
