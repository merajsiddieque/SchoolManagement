package Exceptional_Handling;
public class InvalidCoursIdException extends Exception{
      public InvalidCoursIdException(int ID) {
    	  System.out.println("CourseID-"+ID+"already exists.");
      }
}
//Made the changes inside the ManageCourses