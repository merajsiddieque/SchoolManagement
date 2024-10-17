package adminPackage;
import java.util.Scanner;
public class admin{
   public static void AdminLogin() {
	   Scanner sc = new Scanner(System.in);
	   String username,pass;
	   System.out.println("Enter your username");
	   username = sc.nextLine();
	   sc.nextLine();
	   System.out.println("Enter your password");
	   pass = sc.nextLine();
		if(username.equals("admin") && pass.equals("password")) 
		{
			   System.out.println("You have successfully entered the admin panel");
			   //admin managment for records and that stuff
		}
		else
		{
			while(true)
			{
				System.out.println("Do you want to try again !? Y / N");
				String s = sc.nextLine();
				if(s.equalsIgnoreCase("y"))
				{
					admin.AdminLogin();
					return;
				}
				else if(s.equalsIgnoreCase("N"))
				{
					System.out.println("1)Main page \n  2)exit");
					int choice = sc.nextInt();
				 		switch(choice)
				 		{
				           
				 		}
				 	return;
				}
			   else
			   {
				 System.out.println("Invalid choice ! choose again");
			   }
			}
		}
   }
}