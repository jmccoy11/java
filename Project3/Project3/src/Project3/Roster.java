//******************************************************************************************************** 
// CLASS: Roster (Roster.java) 
// 
// COURSE AND PROJECT INFO 
// CSE205 Object Oriented Programming and Data Structures, Spring 2017 
// Project Number: project-03 
// 
// AUTHOR 
// Jessica Thomas (jdthom22@asu.edu) 
//********************************************************************************************************
package Project3;

import java.util.ArrayList;

/**
* The Roster class encapsulates an ArrayList<Student> which stores the information for each student in the gradebook.
*/
public class Roster 
{

	 // Declare mStudentList
	 ArrayList<Student> mStudentList;
	 
	
	 /**
	  * Roster()
	  *
	  * PSEUDOCODE:
	  * Create mStudentList.
	  */
	 public Roster()
	 {
		 mStudentList = new ArrayList<Student>();
	 }
	
	 /**
	  * addStudent()
	  *
	  * PSEUDOCODE:
	  * Add (append) pStudent to mStudentList.
	  */
	 public void addStudent(Student pStudent)
	 {
		 mStudentList.add(pStudent);
	 }
	
	 /**
	  * getStudent()
	  * Searches mStudentList for a Student with pLastName.
	  *
	  * PSEUDOCODE:
	  * index = Call Searcher.search(getStudentList(), pLastName)
	  * If index == -1 Then Return null
	  * Else return the Student object in mStudentList at index
	  */
	 public Student getStudent(String pLastName)
	 {
		 int index = Searcher.search(getStudentList(), pLastName);
		 
		 if (index == -1)
		 {
			 return null;
		 }
		 else
		{
			 return getStudentList().get(index);
		}
	 }
	
	 /**
	  * getStudentList()
	  * Accessor method for mStudentList.
	  */
	 public ArrayList<Student> getStudentList() 
	 {
	     return mStudentList;
	 }
	
	 /**
	  * setStudentList()
	  * Mutator method for mStudentList.
	  */
	 public void setStudentList(ArrayList<Student> pStudentList) 
	 {
	     mStudentList = pStudentList;
	 }
	
	 /**
	  * sortRoster()
	  * Called to sort the roster by last name.
	  *
	  * PSEUDOCODE:
	  * Call Sorter.sort() passing the list of students
	  */
	 public void sortRoster()
	 {
		 Sorter.sort(mStudentList);
	 }
	
	 /**
	  * Returns a String representation of this Roster. Handy for debugging.
	  */
	 @Override
	 public String toString() 
	 {
	     String result = "";
	     for (Student student : getStudentList()) result += student + "\n";
	     return result;
	 }
}
