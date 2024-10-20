package adminPackage;
import java.util.Scanner;
import MainPackage.Main;
import adminManagmentPackage.Admin_Managment;
import passwordPackage.passwords;
import Interface.login;
public class admin implements login{
   public  void login_method(Scanner Sc){
	   System.out.println("Login panel - Admin");
	   System.out.println("1)Login \n2)ResetPassword \n3)GoBack \n4)Exit");
	   int choice_main = Sc.nextInt();
	   while(choice_main < 1 || choice_main > 4) {
		   System.out.println("Invalid choice Try again !");
		   System.out.println("1)Login \n2)ResetPassword \n3)GoBack \n4)Exit");
		   int p = Sc.nextInt();
		   choice_main = p;
	   }
	   switch(choice_main) {
	   	case 1:
		   String username,pass;
		   System.out.println("Enter your username");
		   Sc.nextLine();
		   username = Sc.nextLine();
		   System.out.println("Enter your password");
		   pass = Sc.nextLine();
		   //login is successful
		   if(passwords.signin(username,pass)) 
		   {
			    Admin_Managment.admin_manage(Sc);
		   }
		   else
		   {
			   System.out.println("Invalid password or username");
			   while(true)
			   {
				   System.out.println("Do you want to try again !? Y / N");
				   String s = Sc.nextLine();
				   if(s.equalsIgnoreCase("y"))
				   {
					   login_method(Sc);
					   return;
				   }
				   else if(s.equalsIgnoreCase("N"))
				   {
					   System.out.println("1)Main page \n2)exit");
					   int choice = Sc.nextInt();
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
	   			if(passwords.Reset_password(Sc)) {
	   				System.out.println("new password is successfully updated");
	   				flag = false;
	   			}
	   			else {
	   				System.out.println("The operation was not successfull");
	   				System.out.println("Do you want to try again ! Y / N");
	   				String YesorNo = Sc.nextLine();
					if(YesorNo.equalsIgnoreCase("n")){
                        flag = false;
						login_method(Sc);
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
   }
}