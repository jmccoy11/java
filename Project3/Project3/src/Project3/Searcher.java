//******************************************************************************************************** 
// CLASS: Searcher (Searcher.java) 
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

public class Searcher {

	public static int search(ArrayList<Student> pStudentList, String pLastName) {
		
		for(int i = 0; i < pStudentList.size(); i++)
		{
			if(pLastName.toLowerCase().compareTo(pStudentList.get(i).getLastName().toLowerCase()) == 0)
			{
				return i; //return the index where pLastName was found in studentList
			}
		}
		
		return -1;
	}

}
