package adminPackage;
import java.util.Scanner;

import MainPackage.Main;
import passwordPackage.passwords;
public class admin{
   public static void login(Scanner sc) {
	   System.out.println("Login panel - Admin");
	//    Scanner sc = new Scanner(System.in);
	   System.out.println("1)Login \n2)ResetPassword \n3)GoBack \n4)Exit");
	   int choice_main = sc.nextInt();
	   while(choice_main < 1 || choice_main > 4) {
		   System.out.println("Invalid choice Try again !");
		   System.out.println("1)Login \n2)ResetPassword \n3)GoBack \n4)Exit");
		   int p = sc.nextInt();
		   choice_main = p;
	   }
	   switch(choice_main) {
	   	case 1:
		   String username,pass;
		   System.out.println("Enter your username");
		   sc.nextLine();
		   username = sc.nextLine();
		   System.out.println("Enter your password");
		   pass = sc.nextLine();
		   //login is successful
		   if(passwords.signin(username,pass)) 
		   {
			   	System.out.println("You have successfully entered the admin panel");
			    
		   }
		   //login is not valid
		   else
		   {
			   System.out.println("Invalid password or username");
			   while(true)
			   {
				   System.out.println("Do you want to try again !? Y / N");
				   String s = sc.nextLine();
				   if(s.equalsIgnoreCase("y"))
				   {
					   admin.login(sc);
					   return;
				   }
				   else if(s.equalsIgnoreCase("N"))
				   {
					   System.out.println("1)Main page \n2)exit");
					   int choice = sc.nextInt();
				 			switch(choice)
				 			{
				 				case 1:
				 					Main.main(null);
				 					return ;
				 				case 2:
				 					System.out.println("You have successfully exited the program !");
				 					System.exit(0);
				 			}
				   }
				   else
				   {
					   System.out.println("Invalid choice ! choose again");
				   }
			   }
		   }
		   break;
	   	case 2:
	   		boolean flag = true;
	   		while(flag) {
	   			if(passwords.Reset_password(sc)) {
	   				System.out.println("new password is successfully updated");
	   				flag = false;
	   			}
	   			else {
	   				System.out.println("The operation was not successfull");
	   				System.out.println("Do you want to try again ! Y / N");
	   				String YesorNo = sc.nextLine();
					if(YesorNo.equalsIgnoreCase("n")){
                        flag = false;
						login(sc);
					}
	   			}
	   		}
	   		break;
	   	case 3:
	   		Main.main(null);
	   		break;
	   	case 4:
	   		System.out.println("You have exited the program successfully");
	   		System.exit(0);
	   }
		sc.close();
   }
}