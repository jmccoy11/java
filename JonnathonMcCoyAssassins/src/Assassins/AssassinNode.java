/*
 * Jonnathon McCoy
 * AssassinsNode.java
 * 06/07/2016
 * 
 * AssassinsNode Object Class
 */
package Assassins;

/**
 * AssassinsNode Object Class
 * 
 * @author Jonnathon McCoy
 * @version 1.0
 */
public class AssassinNode 
{
	private String name;  //name of the assassin
	private String killer;  //the person who killed this assassin
	
	private AssassinNode next; //pointer to the next Node
	
	/**
	 * Constructor for AssassinNode
	 * 
	 * @param name String name to be passed to name field.
	 */
	public AssassinNode(String name)
	{
		this.name = name;
	}

	/**
	 * getter for AssassinNode.name.
	 * 
	 * @return String name
	 */
	public String getName() 
	{
		return name;
	}

	/**
	 * getter for AssassinNode.killer.
	 * 
	 * @return String killer
	 */
	public String getKiller() 
	{
		return killer;
	}

	/**
	 * setter for AssassinNode.killer
	 * 
	 * @param killer String new killer
	 */
	public void setKiller(String killer) 
	{
		this.killer = killer;
	}
	
	/**
	 * getter for next AssassinNode.next
	 * 
	 * @return AssassinNode.next
	 */
	public AssassinNode getNext() 
	{
		return next;
	}

	/**
	 * setter for AssassinNode.next
	 * @param next
	 */
	public void setNext(AssassinNode next) 
	{
		this.next = next;
	}

	/**
	 * String representation of an object.
	 * 
	 * Return String AssassinNode name, next.name, and killer.
	 */
	public String toString() {
		return "AssassinNode [name =" + name + ", stalking =" + next.getName()
			+ ", being stalked by =" + killer + "]";
	}
	
	
}
