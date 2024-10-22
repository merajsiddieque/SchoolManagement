package MainPackage;
import ProfessorPackage.professor;
import StudentPackage.student;
import adminPackage.admin;
import java.util.Scanner;
//we have to handle the redudancy inside the professor like many tuples are equal inside the professors
public class Main
{
public static void main(String[] args){
	   Scanner sc = new Scanner(System.in);
	   System.out.println("Welcome to the portal ! continue as ");
	   int p = -1;
	   while(p == -1) 
	   	{  
		   System.out.println("---SVNIT---");
		   System.out.println("Choose the role from below-");
		   System.out.println(" 1)Administrator.\n 2)Professor.\n 3)Student.\n 4)Exit.");
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
	   			sc.nextLine();
	   			adminstrator.login_method(sc);
	   			adminstrator = null;
	   			break;
	   		case 2:
	   			professor professors = new professor();
	   			sc.nextLine();
	   			professors.login_method(sc);
	   			professors = null;
	   			break;
	   		case 3:
	   			student students = new student();
	   			sc.nextLine();
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
