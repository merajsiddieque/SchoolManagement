package MainPackage;
import ProfessorPackage.professor;
import StudentPackage.student;
import adminPackage.admin;
import java.util.Scanner;
import Interface.login;
public class Main
{
public static void main(String[] args){
	   Scanner sc = new Scanner(System.in);
	   System.out.println("Welcome to the portal ! continue as ");
	   int p = -1;
	   while(p == -1) 
	   	{
		   System.out.println(" 1)admin\n 2)professor\n 3)student\n 4)Exit");
		   System.out.println("choose the role from above");
		   int k = sc.nextInt();
		   		if(k < 1 || k > 4)
		   		{
		   			System.out.println("Invalid choice ! try again");
		   			try {
		   				Thread.sleep(129);
		   				}
		   			catch(InterruptedException ex) {
		   				ex.printStackTrace();
		   				}
		   		}
		   		else
		   		{
		   			p = k;
		   		}
	   	}
	   switch(p) {
	   		case 1:
	   			
	   			admin adminstrator = new admin();
	   			adminstrator.login_method(sc);
	   			adminstrator = null;
	   			break;
	   		case 2:
	   			professor professors = new professor();
	   			professors.login_method(sc);
	   			professors = null;
	   			break;
	   		case 3:
	   			student students = new student();
	   			students.login_method(sc);
	   			students = null;
	   			break;
	   		default:
	   			System.out.println("Have a nice day! You have successfully exited the program.");
	   			System.exit(0);
	   }
	   sc.close();
   }

}
