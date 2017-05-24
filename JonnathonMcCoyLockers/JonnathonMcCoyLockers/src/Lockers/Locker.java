/*
 * Jonnathon McCoy
 * 05/25/2016
 * Lockers.java
 * 
 * A class to create a Locker Object and store a Locker
 * number and its current state, open or closed.
 */

package Lockers;

/**
 * A class to create a Locker Object and store a Locker
 * number and its current state, open or closed.
 * 
 * @author Jonnathon McCoy
 *
 * @version 1.0
 */
public class Locker 
{
	private int lockerNum;
	private boolean closed;
	
	/**
	 * Constructor for a Locker Object.
	 * 
	 * @param lockerNum int Locker Number
	 * 
	 * @param closed boolean closed = true, open = false
	 */
	public Locker(int lockerNum, boolean closed)
	{
		this.lockerNum = lockerNum + 1;
		this.closed = closed;
	}//Locker()
	
	
	/**
	 * Getter for Locker number
	 * 
	 * @return int Locker number
	 */
	public int getLockerNum()
	{
		return lockerNum;
	}//getLockerNum()
	
	/**
	 * Getter for closed.  Closed = true, Open = false.
	 * 
	 * @return String: if closed = false return "Open" else
	 * return "Closed".
	 */
	public String getStatus()
	{
		if(closed == false)
		{
			return "Open";
		}
		else return "Closed";
	}//getStatus()
	
	
	/**
	 * Setter for closed.  In LockerDriver, if the lockerNum
	 * is divisible by the current pass, 1, 2, 3, etc with zero
	 * remainder, then change the state of the locker.  If false, 
	 * then change to true.  Otherwise, it must be true so change
	 * to false.
	 */
	public void toggle()
	{
		if(this.closed == false)
		{
			this.closed = true;
		}
		else
		{
			this.closed = false;
		}
	}//toggle
	
	/**
	 * String representation of an object.
	 * 
	 * @return String lockerNum = status
	 */
	public String toString()
	{
		return getLockerNum() + " = " + getStatus();
	}//toString()
}//Locker.java
