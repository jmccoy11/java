//******************************************************************************************************** 
// CLASS: GradebookWriter (GradebookWriter.java) 
// 
// COURSE AND PROJECT INFO 
// CSE205 Object Oriented Programming and Data Structures, Spring 2017 
// Project Number: project-03 
// 
// AUTHOR 
// Jessica Thomas (jdthom22@asu.edu) 
//********************************************************************************************************
package Project3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
* GradebookWriter inherits from PrintWriter and writes the gradebook info to the file name passed to the ctor.
*/
public class GradebookWriter extends PrintWriter 
{
	Roster pRoster;
	 /**
	  * GradebookWriter()
	  * Call the super class ctor that takes a String.
	  */
	 public GradebookWriter(File outputfile) throws FileNotFoundException
	 {
		 super(outputfile);
	 }
	
	 /**
	  * writeGradebook()
	  * Writes the gradebook info to the file, which was opened in the ctor.
	  *
	  * PSEUDOCODE:
	  * EnhancedFor each student in pRoster.getStudentList() Do
	  *    Call println(student)
	  * End For
	  * Call close()
	  */
	 public void writeGradebook(Roster pRoster)
	 {
		 for(Student student : pRoster.getStudentList())
		 {
			 println(student); //write to file
		 }
		 close();
	 }
}
