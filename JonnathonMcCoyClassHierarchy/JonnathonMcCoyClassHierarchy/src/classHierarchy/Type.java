/*
 * Jonnathon McCoy
 * Assignment ClassHierarchy
 * Type.java
 * 04/14/2016
 */

package classHierarchy;

import java.util.Scanner;

/**
 * This class represents a Type object.
 * 
 * @author Jonnathon McCoy
 * @version 1.0
 */
public class Type
{
	//fields
	private String faction; //Terran, Zerg, or Protoss
	private String name;
	private String type;
	private int hitPoints;
	private boolean mobile;	
	
	public static Scanner scanner = new Scanner(System.in);

	//constructors
	/**
	 * Creates a Type object.
	 * 
	 * 
	 * @param faction Faction can only be Terran, Zerg
	 * or Protoss.
	 * 
	 * @param name Name of the object.
	 * 
	 * @param type Type can only be Unit or Building.
	 * 
	 * @param hitPoints Maximum hit points.
	 * 
	 * @param mobile Boolean: Is the unit able to move.
	 */
	public Type(String faction, String name, String type, 
			int hitPoints, boolean mobile)
	{
		
		verifyFaction(faction);
		verifyType(type);
		
		this.faction = faction;
		this.name = name;
		this.type = type;
		this.hitPoints = hitPoints;
		this.mobile = mobile;		
	}
	
	//methods
	  //getters
	/**
	 * This is a getter for faction.
	 * 
	 * 
	 * @return Returns String Faction object.
	 */
	public String getFaction()
	{
		return faction;
	}//getFaction
	
	/**
	 * This is a getter for name.
	 * 
	 * 
	 * @return Returns String Name object
	 */
	public String getName()
	{
		return name;
	}//getName
	
	/**
	 * This is a getter for type.
	 * 
	 * 
	 * @return Returns String type object.
	 */
	public String getType()
	{
		return type;
	}//getType
	
	/**
	 * This is a getter for hitPoints.
	 * 
	 * 
	 * @return Returns integer hitPoints.
	 */
	public int getHitPoints()
	{
		return hitPoints;
	}//getHitPoints
	
	/**
	 * This is a getter for mobile.
	 * 
	 * 
	 * @return Returns Boolean for mobility.
	 */
	public Boolean getMobile()
	{
		return mobile;
	}//getMobile
	
	
	//setters
	
	/* Once set from constructor, I don't want others to
	 * have access to change the type.
	
	public void setType(String type)
	{
		verifyType(type);
	}
	
	 * Once set from constructor, I don't want others to
	 * have access to change the faction.

	public void setFaction(String faction)
	{		
		if(faction == "")
		{
			if(faction.toLowerCase().equals("terran") ||
					faction.toLowerCase().equals("zerg") ||
					faction.toLowerCase().equals("protoss"))
			{
				this.faction = faction;
			}
			else
			{
				System.out.println("Please enter Terran, "
						+ "Zerg, or Protoss for the "
						+ "faction.");
				faction = factionScanner.nextLine();
			}
		}
		else
		{
			System.out.println("Once set, you cannot "
					+ "change the faction.");
		}
	}*/
	
	/**
	 * This is a setter for name
	 * 
	 * 
	 * @param name Pass the new name for the object.
	 */
	public void setName(String name)
	{
		this.name = name;
	}//setName
	
	/**
	 * This is a setter for hitPoints
	 * 
	 * 
	 * @param name Pass the new integer for maximum
	 * hit points.
	 */
	public void setHitPoints(int hitPoints)
	{
		this.hitPoints = hitPoints;
	}//setHitPoints
	
	/**
	 * This is a setter for mobile.
	 * 
	 * @param name Pass the new boolean for mobility.
	 */
	public void setMobile(boolean mobile)
	{
		this.mobile = mobile;
	}//setMobile
	
	/**
	 * Verifies faction is Terran, Zerg, or Protoss.
	 * Prompts user until one of these are entered.
	 * 
	 * Method is static so it can be used in
	 * CreatorDriver.java.
	 * 
	 * 
	 * @param faction Passes current faction for
	 * verification.
	 */
	public static void verifyFaction(String faction)
	{
		while(true) //verification loop
		{
			if(faction.toLowerCase().equals("terran") ||
			   faction.toLowerCase().equals("zerg") ||
			   faction.toLowerCase().equals("protoss"))
			{
				break;
			}
			else
			{
				System.out.println("The faction can only "
						+ "be Terran, Zerg, or Protoss.  "
						+ "Please enter one of these "
						+ "three options.");
				faction = scanner.nextLine();
			}
		}//verification loop
	}//verifyFaction
	
	/**
	 * Verifies type is building or unit.
	 * Prompts user until one of these are entered.
	 * 
	 * Method is static so it can be used in
	 * CreatorDriver.java.
	 * 
	 * 
	 * @param type Passes current type for
	 * verification.
	 */
	public static void verifyType(String type)
	{
		while(true) //verification loop
		{
			if(type.toLowerCase().equals("building") ||
			   type.toLowerCase().equals("unit"))
			{
				break;
			}
			else
			{
				System.out.println("Please enter "
						+ "building or unit.");
				type = scanner.nextLine();
			}
		}//verification loop
	}//verifyType
	
	/**
	 * Intended for debugging.
	 * 
	 * Returns faction, name, type, hitPoints, and mobile.
	 */
	public String toString()
	{
		return "Faction: " + getFaction() + " | Name: "
				+ getName() + "\n  Type: " + getType() 
				+ " | HitPoints: " + getHitPoints() 
				+ " | Mobile?: " + getMobile();
	}//toString
}//Type.java






