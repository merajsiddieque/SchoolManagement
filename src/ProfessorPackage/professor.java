package ProfessorPackage;

import java.util.Scanner;
import SqlDB.DBConnect;

import MainPackage.Main;
import passwordPackage.passwords;

public class professor extends DBConnect
{
	public static void ProfessorLogin() 
    {
        String username, pass;
        System.out.println("Enter your username:");
        username = sc.nextLine();


        System.out.println("Enter your password:");
        pass = sc.nextLine();

//        String password = "select password from passwordDatabase where password ="+ pass;

        if (passwords.signin(username, pass)) { 
            System.out.println("You have successfully entered the Student Panel");
            System.out.println("Welcome " + username + "!");
            System.out.println(" 1. View Schedule \n 2. Manage Courses \n 3. View Enrolled Students \n 4. Exit \n 5. Logout");

            int funcChoice = sc.nextInt();
            
            while(funcChoice < 1 || funcChoice > 5) {
            	System.out.println("Invalid choice try again");
                System.out.println(" 1. View Schedule \n 2. Manage Courses \n 3. View Enrolled Students \n 4. Exit \n 5. Logout");
            	int temp_choice = sc.nextInt();
            	funcChoice = temp_choice;
            }

            switch (funcChoice) {
                case 1:
                	viewSchedule();
                    break;
                case 2:
                	manageCourses();
                	break;
                case 3:
                	viewEnrolledStudents();
                    break;
                case 4:
                    System.out.println("Have a nice day! You have successfully exited the program.");
                    System.exit(0);
                    return;
                case 5:
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
                    ProfessorLogin(sc);
                    return;
                } else if (retryChoice.equalsIgnoreCase("N")) {
                    System.out.println(" 1. Main page \n 2. Exit");
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
	public static void manageCourses() 
	{
		System.out.println("manage couses ....");
		
	}
	
	 public static void viewSchedule() 
	    {
	        System.out.println("Displaying schedule...");
	    }

	public static void viewEnrolledStudents() 
	{
		System.out.println("enrolledstudent ....");
		
	}
}
