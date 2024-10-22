package Exceptional_Handling;

public class ExceedCreditLimit extends RuntimeException{
   public ExceedCreditLimit()
   {
	   super("Credit limit exceeded");
   }
}
