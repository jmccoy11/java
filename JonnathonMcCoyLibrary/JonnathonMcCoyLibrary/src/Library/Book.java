/*
 * Jonnathon McCoy
 * May 19, 2016
 * Book.java
 * 
 * Class for creating a Book Object
 */

package Library;


/**
 * Class for creating a Book Object
 * 
 * @author Jonnathon McCoy
 * @version 1.0
 */
public class Book extends Item
{
	private String author;
	private String summary;
	
	
	/**
	 * Constructor for Book Class
	 * 
	 * @param itemNum int itemNum to be passed to Item super()
	 * 
	 * @param author String for author field
	 * 
	 * @param title String title to be passed to Item super()
	 * 
	 * @param summary String for summary field
	 */
	public Book(int itemNum, String author, String title, 
			String summary)
	{
		super(itemNum, title);
		this.author = author;
		this.summary = summary;
	}//Book()
	
	/**
	 * getter for Author field
	 * 
	 * @return return String Author field
	 */
	public String getAuthor() 
	{
		return author;
	}//getAuthor()

	
	/**
	 * getter for Summary field
	 * 
	 * @return String Summary field
	 */
	public String getSummary() 
	{
		return summary;
	}//getSummary()

	
	/**
	 * returns number of days for Book loan period
	 * 
	 * @return int 30 days
	 */
	public int loanPeriod()
	{
		return 30; //30 day checkout time for books
	}//loanPeriod()

	
	/**
	 * returns a String representation of an item.
	 * 
	 * @return String itemNum, title, author, and dueDate if assigned
	 */
	public String toString() 
	{
		if(dueDate() == null)
		{
			return super.getItemNum() + " - Book: " + super.getTitle() + ", " 
					+ author + ".";
		}
		else
		{
			return super.getItemNum() + " - Book: " + super.getTitle() + ", " 
					+ author + ". Date Due: " + dueDate();
		}	
	}//toString()
	
	
	/**
	 * returns String representation of an item to be used when writing
	 * to checked-out.txt.
	 * 
	 * @return int itemNum, Class Book, author, title, summary, and
	 * dueDate.  Only time this toString is used is when an item has
	 * already been checked out and is being written to checked-out.txt.
	 * Therefore, every item will have a dueDate.
	 */
	public String writeToFileToString()
	{
		return this.getItemNum() + "^book^" + author + "^" 
				+ this.getTitle() + "^" + summary + "^" + this.dueDate(); 
	}//writeToFileToString()
	

	/**
	 * Compares (a) object to (b) object.
	 * 
	 * @return if (a) > (b) return 1, if (a) < (b) return -1, else return 0
	 * 
	 */
	public int compareTo(Object item)
	{
		//typecast Object item to Item item for access to
		//Item and child class methods.
		Item temp = (Item) item;
		
		//book, CD
		//checkedOut[a] comparedTo checkedOut[b]
		if (this.getTitle().compareTo(temp.getTitle()) > 0)
		{
			return 1;
		}
		else if (this.getTitle().compareTo(temp.getTitle()) < 0)
		{
			return -1;
		}
		else return 0;
	}//compareTo()
}//Book.java