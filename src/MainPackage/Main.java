package MainPackage;

import java.util.Scanner;


import StudentPackage.student;
import ProfessorPackage.professor;
import passwordPackage.passwords;

import java.lang.InterruptedException;

public class Main
{
   public static void main(String[] args) {
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
		   				Thread.sleep(100);
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
	   			//admin login
	   			break;
	   		case 2:
//	   			ProfessorLogin();
	   			break;
	   		case 3:
//	   			StudentLogin();
	   			break;
	   		case 4:
	   			//Exit
	   			System.out.println("Have a nice day! You have successfully exited the program.");
	   			System.exit(0);
	   			return ;
	   }
   }

}
