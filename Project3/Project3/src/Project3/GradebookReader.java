//***************************************************************************************************************************
//CLASS: GradebookReader
//
//AUTHOR
//Kevin R. Burger (burgerk@asu.edu)
//Computer Science & Engineering Program
//Fulton Schools of Engineering
//Arizona State University, Tempe, AZ 85287-8809
//Web: http://www.devlang.com
//***************************************************************************************************************************
package Project3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
* GradebookRead reads the gradebook info from the file name passed to the ctor.
*/
public class GradebookReader  
{

	 private Scanner mIn;
	
	 /**
	  * Attempts to open the gradebook file for reading. If successful, mIn will be used to read from the file. If the file
	  * cannot be opened, a FileNotFoundException will be thrown.
	  */
	 public GradebookReader(String pFname) throws FileNotFoundException 
	 {
	     mIn = new Scanner(new File(pFname));
	 }
	
	 /**
	  * Reads the exam scores for a Student.
	  */
	 private void readExam(Student pStudent) 
	 {
	     for (int n = 0; n < CourseConstants.NUM_EXAMS; ++n) 
	     {
	         pStudent.addExam(mIn.nextInt());
	     }
	 }
	
	 /**
	  * Called to read the gradebook information. Calls readRoster() to read the student records and then sorts the roster
	  * by last name.
	  */
	 public Roster readGradebook() 
	 {
	     Roster roster = readRoster();
	     roster.sortRoster();
	     
	     return roster;
	 }
	
	 /**
	  * Reads the homework scores for a Student.
	  */
	 private void readHomework(Student pStudent) 
	 {
	     for (int n = 0; n < CourseConstants.NUM_HOMEWORKS; ++n) 
	     {
	         pStudent.addHomework(mIn.nextInt());
	     }
	 }
	
	 /**
	  * Reads the student information from the input file adding Student objecs to the roster.
	  */
	 private Roster readRoster() 
	 {
	     Roster roster = new Roster();
	     while (mIn.hasNext()) 
	     {
	         String lastName = mIn.next();
	         String firstName = mIn.next();
	         Student student = new Student(firstName, lastName);
	         readHomework(student);
	         readExam(student);
	         roster.addStudent(student);
	     }
	     return roster;
	 }
}
