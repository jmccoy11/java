/*
 * Jonnathon McCoy
 * May 19, 2016
 * CD.java
 * 
 * Class for creating a CD Object
 */


package Library;


/**
 * Class for creating a CD Object
 * 
 * @author Jonnathon McCoy
 * @version 1.0
 */
public class CD extends Item
{
	private String artist;
	
	
	/**
	 * Constructor for CD Class
	 *
	 * @param itemNum int itemNum to be passed to Item super()
	 * 
	 * @param artist String for artist field
	 * 
	 * @param title String title to be passed to Item super()
	 */
	public CD(int itemNum, String artist, String title)
	{
		super(itemNum, title);
		this.artist = artist;
	}//CD()
	
	
	/**
	 * getter for Artist field
	 * 
	 * @return String Artist field
	 */
	public String getArtist()
	{
		return artist;
	}//getArtist()
	
	
	/**
	 * Returns number of days for CD loan period
	 * 
	 * @return int 14 days
	 */
	public int loanPeriod()
	{
		return 14; //14 day checkout period for CD
	}//loanPeriod()

	
	/**
	 * returns a String representation of an item.
	 * 
	 * @return String itemNum, title, artist, and dueDate if assigned
	 */
	public String toString() 
	{
		if(dueDate() == null)
		{
			return super.getItemNum() + " - Book: " + super.getTitle() + ", " 
					+ artist + ".";
		}
		else
		{
			return super.getItemNum() + " - CD: " + super.getTitle() + ", " 
					+ artist + ". Date Due: " + dueDate();
		}
	}//toString()
	
	
	/**
	 * returns String representation of an item to be used when writing
	 * to checked-out.txt.
	 * 
	 * @return int itemNum, Class CD, Artist, title, and
	 * dueDate.  Only time this toString is used is when an item has
	 * already been checked out and is being written to checked-out.txt.
	 * Therefore, every item will have a dueDate.
	 */
	public String writeToFileToString()
	{
		return this.getItemNum() + "^cd^" + artist + "^" 
				+ this.getTitle() + "^" + this.dueDate(); 
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
		if (temp != null)
		{
			if (this.getTitle().compareTo(temp.getTitle()) > 0)
			{
				return 1;
			}
			if (this.getTitle().compareTo(temp.getTitle()) < 0)
			{
				return -1;
			}
			else return 0;
		}
		else return 0;
	}//compareTo()	
}//CD.java
