/* 
 * Jonnathon McCoy
 * February 17, 2017
 * 
 * Package: edu.greenriver.it.threads_exercise
 * Class: ThreadsDriver.java
 * 
 * Driver class for the Threads Exercise.
 */

package edu.greenriver.it.threads_exercise;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Driver class for the Threads Exercise.
 * 
 * @author Jonnathon McCoy
 * @version 1.0
 */
public class ThreadsDriver 
{
	private static final String PATH = "lists/";
	private static final String TESTFILE = "list_1.txt";
	private static final String OUTPUT_FILENAME = "master_list.txt";
	private static boolean programLoop = true;
	
	public static Scanner console = new Scanner(System.in);
	
	/**
	 * Entry point for the program.
	 * 
	 * @param args - Command-line arguments.
	 */
	public static void main(String[] args)
	{
		while(programLoop)
		{
			menu();
		}
		
		System.out.println();
		System.out.println("Program closing...");
		System.out.println("Have a great day!");
	}
	
	/*
	 * Retrieve input from user for menu option and select the method to run
	 * using a switch/case statement.
	 */
	private static void menu()
	{
		menuPrint();
		String userInput;
		int menuOption = -1;
		
		try
		{
			userInput = console.nextLine();
			
			if(userInput.equalsIgnoreCase("x"))
			{
				programLoop = false;
			}
			else
			{
				menuOption = Integer.parseInt(userInput);
			}
		}
		catch(NumberFormatException exc)
		{
			System.out.println();
			System.out.println("Erroneous input.");
			System.out.println();
		}
		catch(Exception exc)
		{
			System.out.println("Something went wrong. " + exc.getMessage());
			exc.printStackTrace();
		}//try/catch
		
		System.out.println();
		System.out.println("------------------------------------------------------");
		
		switch(menuOption)
		{
			case 1:	problem1();
					break;
			case 2:	problem2a();
					break;
			case 3:	problem2b();
					break;
			case 4:	problem2c();
					break;
			case 5:	problem3a();
					break;
			case 6:	problem3b();
					break;
			case 7:	problem4a();
					break;
			case 8:	problem4b();
					break;
			case 0: problem1(); //instructor option to print the output of all problems in the program
					problem2a();
					problem2b();
					problem2c();
					problem3a("apple"); //predetermined word so Instructor doesn't have to put in input
					problem3b("banana"); //predetermined word so Instructor doesn't have to put in the input
					problem4a();
					problem4b();
					System.out.println("If you would like to test the search capability without a predetermined");
					System.out.println("word, please chose option 5 to search in just list_1.txt or chose option");
					System.out.println("6 if you would like to search among all files in the /lists directory.");
					System.out.println();
					break;
			default: break;
		}//switch/case
	}//menu()
	
	/*
	 * Print statements to console.
	 */
	private static void menuPrint()
	{
		System.out.println("------------------------------------------------------");
		System.out.println();
		System.out.println("Welcome to my Threads Exercise.");
		System.out.println();
		System.out.println("Please select an option from the following:");
		System.out.println();
		System.out.println("(0) For instructor: Print the results of all problems to the console.");
		System.out.println();
		System.out.println("(1) Problem 1:  Print out the name of all files in \"Lists\" directory.");
		System.out.println("(2) Problem 2a: Print the total number of lines in a files.");
		System.out.println("(3) Problem 2b: Print the total line count in each file.");
		System.out.println("(4) Problem 2c: Same as 2b but should be sequential.");
		System.out.println("(5) Problem 3a: Search for a \"term\" in a file. Output whether it was found.");
		System.out.println("(6) Problem 3b: Search for a \"term\" in the directory /lists.");
		System.out.println("(7) Problem 4a: Insert all words or phrases from all files into one ArrayList using Threads.");
		System.out.println("(8) Problem 4b: Make Threads \"thread-safe\".");
		System.out.println();
		System.out.println("(x) Exit");
		System.out.println();
		System.out.print("Enter Selection: ");
	}//menuPrint()
	
	/*
	 * Create a secondary thread that prints out the name of all files in your "lists" directory. 
	 * 
	 * For example, you would see the following output:

		Analyzing lists director... (printed from the main thread)
		Found file: list_1.txt  (printed from the secondary thread)
		Found file: list_10.txt
		Found file: list_2.txt
		Found file: list_3.txt
		Found file: list_4.txt
		Found file: list_5.txt
		Found file: list_6.txt
		Found file: list_7.txt
		Found file: list_8.txt
		Found file: list_9.txt
		Done analyzing lists directory... (printed from the main thread)
	 */
	private static void problem1()
	{
		System.out.println();
		System.out.println("Problem 1:");
		System.out.println();
		System.out.println("Analyzing lists director... (printed from the main thread)");
		
		FilenamePrintThread directory = new FilenamePrintThread("lists/");
		directory.start();
		try 
		{
			directory.join();
		} 
		catch (InterruptedException e) 
		{
			//Do nothing.
		}//try/catch
		
		System.out.println("Done analyzing lists directory... (printed from the main thread)");
		System.out.println();
	}//problem1()
	
	/*
	 * Create a new thread class that stores the name of a file in your "lists" directory. When ran, the thread 
	 * should print out the total number of lines in the file (use a Scanner object to traverse the file). 
	 * 
	 * For example:

		list_1.txt: 60388 (printed from the secondary thread)
	 */
	private static void problem2a()
	{
		System.out.println();
		System.out.println("Problem 2a:");
		System.out.println();
		System.out.println("How many lines are in list_1.txt?");
		
		String filename = PATH+TESTFILE;
		
		CountListThread list1 = new CountListThread(filename);
		list1.start();
		try 
		{
			list1.join();
		} 
		catch (InterruptedException exc) 
		{
			System.out.println("Thread interrupted " + exc.getMessage());
		}
		
		System.out.println(list1.getListName() + ": " + list1.getLineCount() + " lines");
		System.out.println();
	}//problem2a()
	
	/*
	 * Create a loop in your driver class that takes each file in your "lists" directory 
	 * and passes the file to your thread class from 2a. This should allow several threads to 
	 * run simultaneously, calculating the total file count of all files in the "lists" directory. 
	 * 
	 * Here is some sample output: 

			lists/list_2.txt: 4690  (printed from the secondary thread)
			lists/list_9.txt: 32193
			lists/list_10.txt: 22227
			lists/list_3.txt: 41242
			lists/list_5.txt: 21877
			lists/list_6.txt: 82532
			lists/list_7.txt: 64662
			lists/list_1.txt: 60388
			lists/list_4.txt: 81883
			lists/list_8.txt: 67725
	 */
	private static void problem2b()
	{
		System.out.println();
		System.out.println("Problem 2b:");
		System.out.println();
		System.out.println("Printing the lines of each file in /lists directory");
		System.out.println();
		
		File directory = new File(PATH); //create directory object
		String[] listOfFiles = directory.list(); //create String array from the File.list() method 
		CountListThread[] listCounter = new CountListThread[listOfFiles.length];  //create array of CountListThreads
		
		for(int i = 0; i < listOfFiles.length; i++)
		{
			listCounter[i] = new CountListThread(PATH+listOfFiles[i]);
			listCounter[i].start();
		}
		
		for(int i = 0; i < listCounter.length; i++)
		{
			try
			{
				listCounter[i].join();
			}
			catch (InterruptedException ex)
			{
				System.out.println("Thread interrupted " + ex.getMessage());
			}//try/catch
		}
		
		for(int i = 0; i < listCounter.length; i++)
		{
			System.out.println(listCounter[i].getListName()+": " + listCounter[i].getLineCount() + " lines");
		}
		
		System.out.println();
	}//problem2b()
	
	/*
	 * Practice retrieving the results from your thread objects by using the join() method. Do the following:

		Add a field called "lineCount" to your thread class from 2a. 
		Save the result of your computation from run() to your lineCount field.
		Write a getter for lineCount.
		Remove your println() statement in run() that prints out the lineCount.
		Alter your code segment from 2b so that the main thread waits for all ten threads to complete work 
		  before retrieving results of each computation using your new getter.
	 */
	private static void problem2c()
	{
		System.out.println();
		System.out.println("Problem 2c:");
		System.out.println();
		System.out.println("Performed alterations to problem2a and problem2b to achieve the same results");
		System.out.println("   using different methods.");
		System.out.println("Removed println statements from CountListThread run() method.");
		System.out.println("Added getters and setters for listName and lineCount.");
		System.out.println("Added for loop to print listName and lineCount after work has been performed.");
		System.out.println();
	}//problem2c()
	
	/*
	 * Create a word search thread that stores a file name (word list) and a search term (string) as a 
	 * field. The thread should open the word list specified and loop over each line of the file. If a 
	 * line matches the search term given than it should print out the following:

		"term" found in lists/list_1.txt
		
	 * Test your word search thread by prompting the user for a search term. Pass the search term and a 
	    word list filename to a new search thread. For example, your output might look like the following:
		
		Enter a word to search for: apple
		"apple" found in word-phrase lists/list_1.txt
		
	 * Searching for a missing term should have no output
	 */
	private static void problem3a()
	{
		System.out.println();
		System.out.println("Problem 3a:");
		System.out.println();
		System.out.println("We are going to search for a word in list_1.txt.");
		System.out.println();
		
		String filename = PATH+TESTFILE;
		
		System.out.print("Enter a word to search for: ");
		String term = console.nextLine();
		
		
		SearchThread search = new SearchThread(filename,term);
		search.start();
		
		try
		{
			search.join();
		}
		catch (InterruptedException ex)
		{
			System.out.println("Thread interrupted " + ex.getMessage());
		}//try/catch
		
		System.out.println();
	}//problem3a()
	
	/*
	 * Overloaded problem3a so that if Instructor uses option 0 to print everything,
	 * The program doesn't stop to request input again.
	 */
	private static void problem3a(String predeterminedWord)
	{
		System.out.println();
		System.out.println("Problem 3a:");
		System.out.println();
		System.out.println("We are going to search for a word in list_1.txt.");
		System.out.println();
		
		String filename = PATH+"list_1.txt";

		SearchThread search = new SearchThread(filename,predeterminedWord);
		search.start();
		
		try
		{
			search.join();
		}
		catch (InterruptedException ex)
		{
			System.out.println("Thread interrupted " + ex.getMessage());
		}
		
		System.out.println();
	}//problem3a(predeterminedWord)
	
	/*
	 * Alter your solution to 3a by starting several threads, one for each word list file, 
	 *   that each simultaneously search for the given search term. Make sure that your output 
	 *   prints which file a term was found within, for example:
	 *   
			Enter a word to search for: banana
			"banana" found in lists/list_3.txt
			"banana" found in lists/list_7.txt
			"banana" found in lists/list_4.txt
			"banana" found in lists/list_9.txt
			"banana" found in lists/list_8.txt
			"banana" found in lists/list_1.txt
			"banana" found in lists/list_5.txt
			"banana" found in lists/list_6.txt
	 */
	private static void problem3b()
	{
		System.out.println();
		System.out.println("Problem 3b:");
		System.out.println();
		
		System.out.println("Altered Problem 3a to go create multiple files equal to the amount in the lists directory");
		System.out.println("  and searched through each file to find the term.");
		System.out.println();
		
		System.out.println("We are going to search for a word in our /lists directory");
		System.out.println();
		
		File directory = new File(PATH);
		String[] listOfFiles = directory.list();
		SearchThread[] searchThreads = new SearchThread[listOfFiles.length];
		
		System.out.print("Enter a word to search for: ");
		String term = console.nextLine();
		
		for(int i = 0; i < listOfFiles.length; i++)
		{
			searchThreads[i] = new SearchThread(PATH+listOfFiles[i],term);
			searchThreads[i].start();
		}
		
		for(int i = 0; i < searchThreads.length; i++)
		{
			try
			{
				searchThreads[i].join();
			}
			catch (InterruptedException ex)
			{
				System.out.println("Thread interrupted " + ex.getMessage());
			}//try/catch
		}
		
		System.out.println();
	}//problem3b
	
	/*
	 * Overloaded problem3b so that if Instructor uses option 0 to print everything,
	 * The program doesn't stop to request input again.
	 */
	private static void problem3b(String predeterminedWord)
	{
		System.out.println();
		System.out.println("Problem 3a:");
		System.out.println();
		
		System.out.println("We are going to search for a word in our /lists directory");
		System.out.println();
		
		File directory = new File(PATH);
		String[] listOfFiles = directory.list();
		SearchThread[] searchThreads = new SearchThread[listOfFiles.length];
		
		for(int i = 0; i < listOfFiles.length; i++)
		{
			searchThreads[i] = new SearchThread(PATH+listOfFiles[i], predeterminedWord);
			searchThreads[i].start();
		}
		
		for(int i = 0; i < searchThreads.length; i++)
		{
			try
			{
				searchThreads[i].join();
			}
			catch (InterruptedException ex)
			{
				System.out.println("Thread interrupted " + ex.getMessage());
			}//try/catch
		}
		
		System.out.println();
	}
	
	/*
	 * Create a multi-threaded solution for merging all words or phrases from each file 
	 *   into a single file. Use the following technique:
	 *   
	 * Create a shared data area (critical region) that manages an array list of string 
	 * values that will be pulled from each file. Use the following code to get started.
	 * 
	 		Create a new thread class that accepts a filename (word list) and opens the 
	 		given file and adds each line of the file to the shared data class above 
	 		(using the SharedData.add() method).
	 		
			Spin up ten instances of your new thread class, one for each word list file. 
			This will add all file contents for all word lists to the array list stored 
			in your shared data class.
			
			After starting the threads above, call join() on each thread to pause 
			execution on the main thread until all secondary threads have completed.
			
			Write a final loop that uses a PrintWriter object to write the contents of 
			your shared data to a file called master_list.txt.
			
			You will need to use the wordPhraseTotal() and getWordPhrase() methods to loop 
			over the internal array list in your shared data class.
	 */
	private static void problem4a()
	{
		System.out.println();
		System.out.println("Problem 4a:");
		System.out.println();
		
		System.out.println("Adding the contents of all files to master_list.txt.");
		
		File directory = new File(PATH);
		String[] listOfFiles = directory.list();
		WordListThread[] masterList = new WordListThread[listOfFiles.length];
		
		for(int i = 0; i < listOfFiles.length; i++)
		{
			masterList[i] = new WordListThread(PATH+listOfFiles[i]);
			masterList[i].start();
		}
		
		for(int i = 0; i < masterList.length; i++)
		{
			try
			{
				masterList[i].join();
			}
			catch (InterruptedException ex)
			{
				System.out.println("Thread interrupted " + ex.getMessage());
			}//try/catch
		}
		
		//write masterList to an outputfile
		PrintWriter writer = null;
		try 
		{
			System.out.println("Creating output file...");
			writer = new PrintWriter(new File(OUTPUT_FILENAME));
		} 
		catch (FileNotFoundException exc) 
		{
			System.out.println("File not found: " + exc.getMessage());
		}//try/catch
		
		System.out.println("Adding data to file...");
		for(int i = 0; i < SharedData.wordPhraseTotal(); i++)
		{
			writer.println(SharedData.getWordPhrase(i));
		}
		
		System.out.println("Writing to file complete.");
		writer.close();
		
		CountListThread countMasterList = new CountListThread(OUTPUT_FILENAME);
		countMasterList.start();
		try 
		{
			countMasterList.join();
		} 
		catch (InterruptedException exc) 
		{
			System.out.println("Thread interrupted " + exc.getMessage());
		}//try/catch
		
		System.out.println();
		System.out.println("The master_list.txt file line count is: ");
		System.out.println(countMasterList.getListName() + ": " + countMasterList.getLineCount() + " lines");
		
		System.out.println();
	}//problem4a()
	
	/*
	 * If you haven't already, alter your shared data class to make it thread safe. This will 
	 * require that you use synchronization blocks with each interaction of your array list.
	 */
	private static void problem4b()
	{
		System.out.println();
		System.out.println("Problem 4b:");
		System.out.println();
		
		System.out.println("Added synchronized locks to the SharedData class.");		
		
		System.out.println();
		System.out.println();
	}//problem4b()
}//ThreadsDriver.java
