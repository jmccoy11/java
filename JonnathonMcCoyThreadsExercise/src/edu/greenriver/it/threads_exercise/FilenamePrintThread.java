/* 
 * Jonnathon McCoy
 * February 17, 2017
 * 
 * Package: edu.greenriver.it.threads_exercise
 * Class: FilenamePrintThread.java
 * 
 * A class that extends from the Thread class to print files within a directory
 *   to the console.
 */

package edu.greenriver.it.threads_exercise;

import java.io.File;

/**
 * A class that extends from the Thread class to print files within a directory
 *   to the console.
 *   
 * @author Jonnathon McCoy
 * @version 1.0
 *
 */
public class FilenamePrintThread extends Thread
{
	private File directory;
	
	/**
	 * Parameterized constructor for the FilenamePrintThread.
	 * 
	 * @param path - String - directory pathname
	 */
	public FilenamePrintThread(String path)
	{
		this.directory = new File(path);
	}//FilenamePrintThread constructor
	
	@Override
	/**
	 * Create an array of the filenames within the directory.
	 * 
	 * Loop through the array and print the names of the files to the console.
	 */
	public void run()
	{
		String[] listOfFiles = directory.list();
		
		for(int i = 0; i < listOfFiles.length; i++)
		{
			System.out.println("Found file: " + listOfFiles[i]);
		}
	}//run()
}//FilenamePrintThread.java
