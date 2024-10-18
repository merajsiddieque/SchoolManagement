package Exceptional_Handling;
public class passwordException extends RuntimeException{
	public passwordException() {
		super("The password is wrong");
	}
}