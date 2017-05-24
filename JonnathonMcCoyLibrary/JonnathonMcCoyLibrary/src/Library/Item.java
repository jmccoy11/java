/*
 * Jonnathon McCoy
 * May 19, 2016
 * CD.java
 * 
 * Parent class for creating Book and CD classes from
 */


package Library;

import java.time.LocalDate;


/**
 * Parent class for creating Book and CD classes from
 * 
 * @author Jonnathon McCoy
 * @version 1.0
 */
public abstract class Item implements Loanable, Comparable<Object>
{
	private int itemNum;
	private String title;
	private LocalDate checkOutDate;
	
	
	/**
	 * Constructor for Item class with Item Number and Title.
	 * 
	 * @param itemNum int Item Number
	 * 
	 * @param title String title of Item
	 */
	public Item(int itemNum, String title)
	{
		this.itemNum = itemNum;
		this.title = title;
	}//Item()
	
	
	/**
	 * takes a date and assigns it to an item
	 * 
	 * @param date LocalDate to set checkOutdate
	 */
	public void setCheckOutDate(LocalDate date)
	{
		this.checkOutDate = date;
	}//setCheckOutDate()

	
	/**
	 * getter for title field
	 * 
	 * @return String title
	 */
	public String getTitle()
	{
		return title;
	}//getTitle()
	
	
	/**
	 * getter for itemNum
	 * 
	 * @return int itemNum
	 */
	public int getItemNum()
	{
		return itemNum;
	}//getItemNum()
	
	
	/**
	 * getter for checkOutDate
	 * 
	 * @return LocalDate checkOutDate 
	 */
	public LocalDate getCheckOutDate()
	{
		return checkOutDate;
	}//getCheckOutDate()
	
	
	/**
	 * Calculates and returns an item's due date based on its
	 * check out date and loan period
	 * 
	 * @return int checkOutDate + loanPeriod for class
	 */
	public LocalDate dueDate()
	{	
		if(checkOutDate == null)
		{
			return null;
		}
		else
		{
			return checkOutDate.plusDays((long)loanPeriod());
		}
	}//dueDate()
	
	
	/**
	 * returns a boolean indicating whether an item's due date
	 * has passed
	 * 
	 * @return boolean if checkOutDate is past today's date
	 */
	public boolean isOverdue()
	{
		if(checkOutDate != null)
		{
			if(dueDate().isBefore(LocalDate.now()))
			{
				return true;
			}
		}
		//else
		return false;
	}//isOverdue()
	
	
	/**
	 * compares items based on type (i.e. class name), and then title
	 * within type.  In other words, books will be sorted before CDs
	 */
	public abstract int compareTo(Object item);
	
	
	/**
	 * returns String representation of an item to be used when writing
	 * to checked-out.txt.
	 * 
	 * @return All fields concatenated with "^"  
	 */
	public abstract String 	writeToFileToString();
	
	
	/**
	 * returns a String representation of an item, which includes
	 * item number, class name, and title
	 * 
	 * @return String itemNum, title, checkOutDate
	 */
	public String toString() 
	{
		if(checkOutDate == null)
		{
			return "Item [itemNum=" + itemNum + ", title=" + title + "]";
		}
		//else
		return "Item [itemNum=" + itemNum + ", title=" + title + "] "
				+ "Check out Date: " + getCheckOutDate();
	}//toString()
}//Item.java
