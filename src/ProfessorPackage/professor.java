package ProfessorPackage;

import MainPackage.Main;
import SqlDB.DBConnect;
import java.util.Scanner;
import passwordPackage.passwords;
import Interface.login;

public class professor extends DBConnect implements login
{   
	String username,pass;
	public void login_method(Scanner sc) 
    {
        String username_method,pass_method;
        System.out.println("Enter your username:");
        username_method = sc.nextLine();
        System.out.println("Enter your password:");
        pass_method = sc.nextLine();
        set_username(username_method);
        set_pass(pass_method);
        if (passwords.signin("proff" + username_method,pass_method)) { 
            System.out.println("You have successfully entered the Professor Panel");
            System.out.println("Welcome " + username + "!");
            int professorId = 0;
            for(int i = 2;i<username.length();i++)
            {
            	professorId = professorId*10 + (username.charAt(i)-'0');
            }
            while(true)
            {
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
                	viewSchedule(get_username(),sc,professorId);
                    break;
                case 2:
                	manageCourses(get_username(),sc,professorId);
                	break;
                case 3:
                	viewEnrolledStudents(get_username(),sc,professorId);
                    break;
                case 4:
                    System.out.println("Have a nice day! You have successfully exited the program.");
                    System.exit(0);
                    return;
                case 5:
                	Main.main(null);
                	break;
            }
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
                    login_method(sc);
                    return;
                }
                else if (retryChoice.equalsIgnoreCase("N")) {
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
	public static void viewSchedule(String Username,Scanner sc,int p) 
	{
		professorDB(Username,1,sc,p);
	}
	public static void manageCourses(String Username,Scanner sc,int p) 
	{
		professorDB(Username,2,sc,p);
	}
	
	public static void viewEnrolledStudents(String Username,Scanner sc,int p) 
	{
		professorDB(Username,3,sc,p);
	}
	public void set_username(String username) 
	{
		this.username = username;
	}
	public String get_username() 
	{
		return username;
	}
	public void set_pass(String pass) 
	{
		this.pass = pass;
	}
	public String get_pass() 
	{
		return pass;
	}
}
