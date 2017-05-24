/*
 * Jonnathon McCoy
 * May 19, 2016
 * Library.java
 * 
 * A library system that allows users to search for books or CDs
 * check out items, and display a list of the items they have
 * checked out.
 */

package Library;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;


/**
 *  * A library system that allows users to search for books or CDs
 * check out items, and display a list of the items they have
 * checked out.
 * 
 * @author Jonnathon McCoy
 * @version 1.0
 *
 */
public class Library 
{
	//fields
	private final int NUM_ITEMS = 112;
	private final int MAX_ITEMS = 10;
	
	private Item items[] = new Item[NUM_ITEMS];
	private Item checkedOut[] = new Item[MAX_ITEMS];
	private ArrayList<Item> searchIndex = 
			new ArrayList<Item>(NUM_ITEMS);
	private int nextAvailableIndex = -1;
	private Scanner in = new Scanner(System.in);
	private DateTimeFormatter formatter = 
			DateTimeFormatter.ofPattern("yyyy-MM-dd"); 

	//constructors
	/**
	 * Constructor for Library class
	 */
	public Library()
	{
		readLibrary();
		readCheckedOut();
	}//Library()
	
	
	//methods
	/**
	 * prompts the user for an item number, then sets the checked out 
	 * date for the item and adds it to the checkedOut array.  If the 
	 * user is at their checkout limit, they are not allowed to check 
	 * out any more items.  A user may not check out the same item twice. 
	 * If the item is not in the library, display an appropriate message.
	 */
	public void checkOut()
	{
		int value = 0;
		
		//sets nextAvailable Index the the closest null position within
		//checkOut array
		for(int i = 0; i < checkedOut.length; i++)
		{
			if(checkedOut[i] == null)
			{
				nextAvailableIndex = i;
				break;
			}
			else
			{
				nextAvailableIndex = -1;
			}
		}
		
		//if there are no null values in checkedOut, checkedOut has reached
		//the maximum items in the array
		if(nextAvailableIndex == -1)
		{
			System.out.println("You have reached the maximum number (10) "
					+ "of items allowed to be checked out.");
			System.out.println("Please return an item to check out "
					+ "out another item.");
		}
		
		//if an index is available, retrieve userInput to check out
		//an item.
		else
		{
			String userInput = "_";
			value = verifyCheckoutInput(userInput);
			
			boolean result = isCheckOutInArray(value);
			
			if(result != false)
			{
				System.out.println();
				System.out.println("Item checked out.");
				
				if (result != false)
				{
					saveAll();
					sortCheckedOut();
				}
			}
		}

		menu();
	}//checkOut
	
	
	/**
	 * reads the contents of checked-out.txt into the checkedOut[] array
	 */
	public void readCheckedOut()
	{
		Scanner reader = null;
		
		try
		{
			reader = new Scanner(new FileInputStream("checked-out.txt"));
			
			int count = 0;
			while(reader.hasNextLine())
			{
				String line = reader.nextLine();
				String[] temp = line.split("\\^");
				
				if(temp[1].equals("cd"))
				{
					checkedOut[count] = new CD (Integer.parseInt(temp[0]),
							temp[2],temp[3]);
					if(temp.length-1 == 4)
					{
						LocalDate dueDate = LocalDate.parse(temp[4], formatter);
						checkedOut[count].setCheckOutDate(dueDate);
					}
				}
				else
				{
					checkedOut[count] = new Book(Integer.parseInt(temp[0]),
							temp[2], temp[3], temp[4]);
					if(temp.length-1 == 5)
					{
						LocalDate dueDate = LocalDate.parse(temp[5], formatter);
						checkedOut[count].setCheckOutDate(dueDate);
					}
				}
				count++;
			}
		}
		catch(FileNotFoundException exc)
		{
			System.out.println("Error encountered when attempting to open "
					+ "checked-out.txt " + exc.getMessage());
		}
		finally
		{
			if (reader != null)
			{
				reader.close();
			}
		}	
	} //readCheckedOut()
	
	
	/**
	 * prints the program menu and directs to the proper method associated
	 * with each option
	 */
	public void menu()
	{
		String userInput;
		boolean menuLoop = true;
		while(menuLoop)
		{
			System.out.println();
			System.out.println("MAIN MENU");
			System.out.println("---------");
			System.out.println("[S]earch the library");
			System.out.println("[C]heck out an item");
			System.out.println("[P]rint my items");
			System.out.println("e[X]it");
			System.out.println();
			System.out.print("Your selection: ");
			
			userInput = in.nextLine();
			
			if(userInput.toLowerCase().equals("s"))
			{
				menuLoop = false;
				search();
			}
			else if (userInput.toLowerCase().equals("c"))
			{
				menuLoop = false;
				checkOut();
			}
			else if (userInput.toLowerCase().equals("p"))
			{
				menuLoop = false;
				printList();
			}
			else if (userInput.toLowerCase().equals("x"))
			{
				menuLoop = false;
			}
		}
	}//menu()
	
	
	/**
	 * sort the checkedOut array using a bubble sort.
	 */
	public void sortCheckedOut()
	{
		System.out.println();
		//Sort by Book/CD first.  Book comes before CD 
		for(int pass = 0; pass < checkedOut.length; pass++)
		{
			//start at the end, and work down.
			for(int pos = checkedOut.length-pass-1; pos >= 0; pos--)
			{
				if(checkedOut[pos] != null)
				{
					//if pos is CD and the next index is book
					//move CD +1 position.
					if(checkedOut[pos] instanceof CD &&
							checkedOut[pos+1] instanceof Book)
					{
						//check item with position to the right because when pos gets
						//to 0, checking pos-1 will result in an ArrayOutOfBoundsException
						Item temp = checkedOut[pos];
						checkedOut[pos] = checkedOut[pos+1];
						checkedOut[pos+1] = temp;
					}
					
					if(pos != 0)
					{
						int result = checkedOut[pos].compareTo(
								checkedOut[pos-1]);
						if(result < 0)
						{
							Item temp = checkedOut[pos];
							checkedOut[pos] = checkedOut[pos-1];
							checkedOut[pos-1] = temp;
						}
					}
				}
			}
		}
	}//sortCheckedOut()
	
	
	/*
	 * returns a boolean indicating whether an item's due date has passed
	 * @param item retrieves the due date for the item and compares it
	 * to the local date.
	 */
	private boolean isOverdue(Item item)
	{
		if(item.dueDate().isBefore(LocalDate.now()))
		{
			return true;
		}
		else
		{
			return false;
		}
	}//isOverdue()
	
	
	/*
	 * displays a list of all items that are currently checked out, 
	 * including their due date.  If an item is overdue, that should 
	 * be indicated in the list. Items should be printed in order first 
	 * by type, and then in alphabetical order, by title.  Note: The 
	 * logic for sorting should be handled in your compareTo( ) method.
	 */
	private void printList()
	{
		System.out.println();
		System.out.println("Your items:");
		
		for(int i = 0; i < checkedOut.length; i++)
		{
			if (checkedOut[i] != null)
			{
				//if item is Overdue, indicate with **OVERDUE**
				if (isOverdue(checkedOut[i]))
				{
					System.out.println(checkedOut[i].toString() 
							+" **OVERDUE**");
				}
				else
				{
					System.out.println(checkedOut[i].toString());
				}
			}
		}
	
		menu();
	}//printList()
	
	
	/*
	 * prompts the user for a search term, and then searches the library 
	 * items for the term.  All items that have the search term anywhere 
	 * in their title or artist or author should be included in the 
	 * search results.  Searches are case-insensitive. If there are no 
	 * matches, display an appropriate message
	 */
	private void search()
	{
		System.out.println();
		System.out.println("SEARCH");
		System.out.print("Enter search term: ");
		String userInput = in.nextLine();
		
		//clear the searchIndex
		searchIndex.clear();
		
		/*
		 * search the array and enter the items into searchIndex
		 * if found.
		 */
		inArray(userInput,items);
		
		if(searchIndex.size() == 0)
		{
			System.out.println("No items match search criteria.");
		}
		else
		{
			System.out.println("Search results:");
			for(int i = 0; i < searchIndex.size(); i++)
			{
				System.out.println((searchIndex.get(i).toString()));
			}
		}
		
		menu();
	}//search()
	
	
	//reads the contents of library.txt into the library[] array.
	private void readLibrary()
	{
		Scanner reader = null;
		
		try
		{
			reader = new Scanner(new FileInputStream("library.txt"));
			
			int count = 0;
			while(reader.hasNextLine())
			{
				String line = reader.nextLine();
				String[] temp = line.split("\\^");
				
				//if line is a cd, use CD class to build object
				if(temp[1].equals("cd"))
					items[count] = new CD (Integer.parseInt(temp[0]),
							temp[2],temp[3]);
				//else use Book class to build object
				else
				{
					items[count] = new Book(Integer.parseInt(temp[0]),
							temp[2], temp[3], temp[4]);
				}
				count++;
			}
		}
		catch(FileNotFoundException exc)
		{
			System.out.println("Error encountered when attempting to open "
					+ "library.txt " + exc.getMessage());
		}
		finally
		{
			if (reader != null)
			{
				reader.close();
			}
		}
	} //readLibrary()
	
	
	/*
	 * Saves checkedOut[] to checked-out.txt file.
	 */
	private void saveAll()
	{
		PrintWriter writer = null;
		
		try
		{
			writer = new PrintWriter(new FileOutputStream("checked-out.txt",
					true));
			
			writer.println(checkedOut[nextAvailableIndex].writeToFileToString());
		}
		catch(FileNotFoundException exc)
		{
			System.out.println("Error encountered when attempting to open "
					+ "library.txt " + exc.getMessage());
		}
		finally
		{
			if (writer != null)
			{
				writer.close();
			}
		}
		
	}
	
	
	/*
	 * search the array and enter the items into searchIndex
	 * if found in order to print all items that match the userInput
	 * requested.
	 */
	private void inArray(String input, Item[] itemArray)
	{
		for(int i = 0; i < itemArray.length; i++)
		{
			if(itemArray[i].toString().toLowerCase().contains(input.toLowerCase()))
			{
				searchIndex.add(items[i]);
			}
		}
	}//inArray()
	
	
	/*
	 * @return boolean is ItemNumber currently in items[]
	 */
	private boolean isCheckOutInArray(int itemNum)
	{
		int index = 0;
		System.out.println();
		
		if (itemNum != -1)
		{
			for(int i = 0; i < items.length; i++)
			{
				if(items[i].getItemNum() == itemNum)
				{
					//print the item toString for confirmation
					System.out.println("The book you would like to "
							+ "check out is:");
					System.out.println(items[i].toString());
					//set the index to i to add to checkedOut[] later
					index = i;
					
					//Confirm user selection
					String response = "";
					while(!(response.equals("y")) && !(response.equals("n")))
					{
						System.out.println();
						System.out.println("Is this correct? (Y)es or "
								+ "(No)");
						response = in.nextLine();
						response = response.toLowerCase();
					}
					
					if (response.equals("y"))
					{
						//add item[i] to next available spot in checkedOut[]
						checkedOut[nextAvailableIndex] = items[index];
						checkedOut[nextAvailableIndex].setCheckOutDate(LocalDate.now());
						return true;
					}
					else
					{
						return false;
					}
				}
			}
		}
		
		//compiler needed a return statement and would not accept it in
		//an else statement
		
		//else
		//{
		System.out.println("That item is not in the library database."); 
		return false;
		//}
	}
	
	
	/*
	 * Verify user input is an integer
	 * Also check if the item is already in checkedOut[]
	 */
	private int verifyCheckoutInput(String input)
	{
		int value = -1;
		
		boolean verify = true;
		while(verify)
		{
			if(input.equals(""))
			{
				verify = false;
				break;
			}
			
			try
			{
				value = Integer.parseInt(input);
				verify = false;
			}
			catch(NumberFormatException exc)
			{
				System.out.println();
				System.out.println("Item Number to check out: ");
				System.out.println("Press \"Enter\" to cancel and "
						+ "return to the main menu.");
				input = in.nextLine();
			}
		}
		
		//Is item already in checkedOut[]
		for(int i = 0; i < checkedOut.length; i++)
		{
			if(checkedOut[i] != null)
			{
				if(checkedOut[i].getItemNum() == value)
				{
					System.out.println("You can only check out an "
							+ "item once.");
					value = -1;
				}
			}
		}
		return value;
	}//verifyCheckOutInput()
}//Library.java
