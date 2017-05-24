/*
 * Jonnathon McCoy
 * March 3, 2017
 * 
 * package: edu.greenriver.it.webcrawler
 * class: WebCrawlerConsole.java
 * 
 * Entry point for the WebCrawler program.
 * 
 * Displays the menu, accepts user URL entries, creates new Producer/Consumer Threads,
 * and prints the stats of the program to the console.
 */
package greenriver.edu.it.webcrawler;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Displays the menu, accepts user URL entries, creates new Producer/Consumer Threads,
 * and prints the stats of the program to the console.
 * 
 * @author Jonnathon McCoy
 * @version 1.0
 */
public class WebCrawlerConsole 
{
	//fields
	private static int keywordHits = 0;
	private static int fetchers = 0;
	private static int parsers = 0;
	
	private static WebCrawlerConsole main;
	private static ArrayList<String> keywords = new ArrayList<String>();
	
	private static Scanner input = new Scanner(System.in);
	
	//methods
	/**
	 * Entry point for the WebCrawler program.
	 * 
	 * @param args0 - Command line arguments
	 */
	public static void main(String[] args0)
	{
		main = new WebCrawlerConsole();
		main.run();
	}
	
	/*
	 * Print the menu and call the corresponding methods.
	 */
	private void run()
	{
		boolean programLoop = true;
		
		while(programLoop)
		{
			menu();
			System.out.print("Selection: ");
			String userInput = input.nextLine();
			
			int validInput = -1;
			
			try
			{
				validInput = Integer.parseInt(userInput);
			}
			catch(NumberFormatException e)
			{
				System.out.println("Please enter a valid selection.");
			}
			
			System.out.println();
			
			
			switch(validInput)
			{
				case 0: forInstructor();
						break;
				case 1: addSeedURL();
						break;
				case 2: addConsumer();
						break;
				case 3: addProducer();
						break;
				case 4: addKeywordSearch();
						break;
				case 5: printStats();
						break;
				//case 10: debug();  //Not synchronized and will most likely cause a ConcurrentModificationException
						   //break;	//if multiple Producer and Consumer Threads are running.
				default: break;
			}
			
			System.out.println();
		}
	}
	
	/*
	 * Prints the menu to the screen.
	 */
	private static void menu()
	{
		System.out.println("0. ** FOR INSTRUCTOR **");
		System.out.println("     Seed 5 URLs, set 3 keywords, creates 1000 Producers and 10 Consumers");
		System.out.println();
		System.out.println("1. Add seed url");
		System.out.println("2. Add consumer (Parser)");
		System.out.println("3. Add producer (Fetcher)");
		System.out.println("4. Add keyword search");
		System.out.println("5. Print stats");
	}
	
	/*
	 * Specific method to make things easier for the instructor.
	 */
	private static void forInstructor()
	{
		System.out.println("           ** FOR INSTRUCTOR **");
		System.out.println("   This option will do the following:");
		System.out.println();
		System.out.println("   Add www.cnn.com, www.king5.com, www.msn.com, www.yahoo.com, and www.nbc.com to the seed URLs");
		System.out.println();
		System.out.println("   Add the following keywords to the keyword search:");
		System.out.println("       Trump, America, and Russia");
		System.out.println();
		System.out.println("   Spool up 1000 Fetchers (Producers)");
		System.out.println("   Spool up 10 Parsers (Consumers)");
		System.out.println();
		System.out.println("   Keep pressing option 5 to watch as the program progresses.");
		System.out.println();
		System.out.println("   Please press 1 to continue or press 0 again to go back.");
		
		String userInput = input.nextLine();
		boolean valid = false;
		while(!valid)
		{
			if(userInput.equals("1"))
			{
				valid = true;
				try 
				{
					SharedLink.addLink("https://www.cnn.com");
					SharedLink.addLink("https://www.king5.com");
					SharedLink.addLink("https://www.msn.com");
					SharedLink.addLink("https://www.yahoo.com");
					SharedLink.addLink("https://www.nbc.com");
					
					keywords.add("Trump");
					keywords.add("America");
					keywords.add("Russia");
					
					
					for(int i=0; i < 1000; i++)
					{
						addProducer();
					}
					
					for(int i=0; i < 10; i++)
					{
						addConsumer();
					}
				} 
				catch (InterruptedException e) 
				{
					//do nothing
				}
			}
			else if(userInput.equals("0"))
			{
				valid = true;
			}
			else
			{
				System.out.println("Please press 1 to continue or press 0 again to go back.");
				userInput = input.nextLine();
			}
		}
	}
	
	/*
	 * you should prompt the user for a url, which is added to the link queue
	 */
	private static void addSeedURL()
	{
		System.out.println("Enter a website to search for keywords.");
		String userInput = input.nextLine();
		
		if(userInput.length() >= 7 && !(userInput.substring(0, 6).equals("https://")))
		{
			userInput = "http://" + userInput;
			
			try 
			{
				SharedLink.addLink(userInput);
			} 
			catch (InterruptedException e) 
			{
				//do nothing
			}
		}
	}
	
	/*
	 * creates and starts a new Parse thread. If there are no pages on the page
	 * 	queue, this will result in the thread going to sleep with a call to wait()
	 */
	private static void addConsumer()
	{
		parsers++;
		Parser consumer = new Parser();
		consumer.start();
	}
	
	/*
	 * creates and starts a new Fetch thread. If there are no links on the link 
	 * queue, this will result in the thread going to sleep with a call to wait().
	 */
	private static void addProducer()
	{
		fetchers++;
		Fetcher producer = new Fetcher();
		producer.start();
	}
	
	/*
	 * creates a new keyword that Parsers can look for when parsing documents. Your 
	 * program should maintain a list of keywords that each Parser can access.
	 */
	private static void addKeywordSearch()
	{
		String userInput;
		System.out.println("Enter a new keyword to search for:");
		userInput = input.nextLine();
		keywords.add(userInput);
	}
	
	/*
	 * Print the following details
	 * Keywords found: 
	 * Links found:
	 * Pages found:
	 * Failed downloads:
	 * Producers:
	 * Consumers:
	 */
	private static void printStats()
	{
		System.out.println("Keywords found: " + keywordHits);
		System.out.println("Links found: " + SharedLink.getLinksFound());
		System.out.println("Pages found: " + SharedPage.getPagesDownloaded());
		System.out.println("Failed downloads: " + SharedPage.getFailedDownloads());
		System.out.println("Producers: " + fetchers);
		System.out.println("Consumers: " + parsers);
	}
	
	/**
	 * Getter for the keywords ArrayList
	 * 
	 * @return  - ArrayList - user entered keywords
	 */
	public static ArrayList<String> getKeywords()
	{
		return keywords;
	}
	
	/**
	 * Increment how many times a keyword was found.
	 */
	public static void keywordHit()
	{
		keywordHits++;
	}
	
	/**
	 * For use in debugging the SharedLink class.
	 */
	private static void debug()
	{
		System.out.println("Keywords:");
		for(String word : keywords)
		{
			System.out.println(word);
		}
		
		System.out.println("-----------------");
		System.out.println();
		SharedLink.debugLinks();
		
		System.out.println("-----------------");
		System.out.println();
		SharedPage.debugPages();
		
		System.out.println();
	}
}
