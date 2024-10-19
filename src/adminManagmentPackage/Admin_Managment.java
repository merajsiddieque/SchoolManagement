package adminManagmentPackage;
import java.util.Scanner;
import MainPackage.Main;
import adminPackage.admin;
public class Admin_Managment 
{
	// view_courses and delete_courses and add_courses and assign_professors and update_student_records
    public static void admin_manage(Scanner Sc){
        System.out.println("1)Manage Courses \n2)Assign Professors \n3)Update Student Records \n4)Go Back \n5)Log Out \n6)Exit");
        int choice;
        choice = Sc.nextInt();
        while(choice < 1 || choice > 6) {
        	System.out.println("Invalid choice Try again !");
            System.out.println("1)Manage Courses \n2)Assign Professors \n3)Update Student Records \n4)Go Back \n5)Log Out \n6)Exit");
            int p;
            p = Sc.nextInt();
            choice = p;
        }
        ManageCourses Mc;
        switch(choice){
        	case 1:
        		Mc = new ManageCourses();
        		int choice_Mc;
        		System.out.println("1)View Course \n2)Delete Course \n3)Add Courses \n4)Go Back \n5)Log Out \n6)Exit");
        		choice_Mc = Sc.nextInt();
        		while(choice_Mc < 1 || choice_Mc > 6) {
        			System.out.println("1)View Course \n2)Delete Course \n3)Add Courses \n4)Go Back \\n5)Log Out \\n6)Exit");
        			int choice_Mc_temp = Sc.nextInt();
        			choice_Mc = choice_Mc_temp;
        		}
        		switch(choice_Mc) {
        			case 1:
        				Mc.view_course();
        				break;
        			case 2:
        				Mc.delete_course(Sc);
        				break;
        			case 3:
        				Mc.add_course(Sc);
        				break;
        			case 4:
        				//Go back
        				Mc = null;
        				admin_manage(Sc);
        				break;
        			case 5:
        				//Log Out
        				Main.main(null);
        				break;
        			case 6:
        				//Exit
        				System.out.println("You have Exited the System Successfully");
        				System.exit(0);
        		}
        		break;
        	case 2:
        		//Assign Professors
        		break;
        	case 3:
        		//Update student Records
        		break;
        	case 4:
        		//Go back
        		Mc = null;
        		admin.login(Sc);
        		break;
        	case 5:
        		//Log Out
        		Mc = null;
        		Main.main(null);
        		break;
        	case 6:
        		//Exit
        		System.out.println("You have Successfully exited the program");
        		System.exit(0);
        		break;
        }
    }
}