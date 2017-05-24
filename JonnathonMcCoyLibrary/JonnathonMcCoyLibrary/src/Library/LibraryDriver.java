/*
 * Jonnathon McCoy
 * May 19, 2016
 * LibraryDriver.java
 * 
 * Entry point for Library program
 */
package Library;

/**
 * Entry point for Library program
 * 
 * @author Jonnathon McCoy
 * @version 1.0
 */
public class LibraryDriver 
{
	/**
	 * Entry point for Library program.
	 * 
	 * @param args Command line arguments
	 */
	public static void main(String[] args) 
	{
		Library library = new Library();
		greeting();

		library.menu();
	}//main()

	//Program greeting
	private static void greeting()
	{
		System.out.println("Welcome to the Library:");
	}//greeting()
}//LibraryDriver.java
