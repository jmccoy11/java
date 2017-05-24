/*
 * Jonnathon McCoy
 * Assignment ClassHierarchy
 * Infantry.java
 * 04/14/2016
 */

package classHierarchy;

/**
 * This class represents an Infantry Unit object.
 * 
 * @author Jonnathon McCoy
 * @version 1.0
 */
public class Infantry extends Unit
{
	//fields
	String rangeType; //ranged or melee
	Double range;
	
	//constructors
	/**
	 * Creates an Infantry object.
	 * 
	 * 
	 * @param faction Faction can only be Terran, Zerg
	 * or Protoss.
	 * 
	 * @param name Name of the object.
	 * 
	 * @param hitPoints Maximum hit points.
	 * 
	 * @param speed Speed in (units) per second
	 * 
	 * @param rangeType Range Type can only be melee or
	 * ranged.
	 * 
	 * @param range Attack range in distance (units).
	 */
	public Infantry(String faction, String name,
			int hitPoints, double speed, String rangeType,
			double range)
	{
		super(faction, name, hitPoints, "Infantry", speed);
		
		verifyRangeType(rangeType);
		
		this.rangeType = rangeType;
		this.range = range;
	}//Infantry
	
	//methods
	  //getters
	/**
	 * Getter for rangeType.
	 * 
	 * 
	 * @return Returns String for rangeType object.
	 */
	public String getRangeType()
	{
		return rangeType;
	}//getRangeType
	
	/**
	 * Getter for range.
	 * 
	 * 
	 * @return Returns double for range.
	 */
	public double getRange()
	{
		return range;
	}//getRange
	
	  //setters
	/**
	 * Setter for Range.
	 * 
	 * 
	 * @param range Pass the new Range for the object.
	 */
	public void setRange(double range)
	{
		this.range = range;
	}//setRange
	
	  //other methods
	/**
	 * Verifies rangeType is Melee or Ranged.  Prompts
	 * user for correct input.
	 * 
	 * Method is static so it can be used in
	 * CreatorDriver.java.
	 * 
	 * 
	 * @param rangeType Passes current rangeType for 
	 * verification.
	 */
	public static void verifyRangeType(String rangeType)
	{
		while(true) //verification loop
		{
			if(rangeType.toLowerCase().equals("melee") ||
			   rangeType.toLowerCase().equals("ranged"))
			{
				break;
			}
			else
			{
				System.out.println("Unit type can only be "
						+ "Melee or Ranged.  Please "
						+ "enter one of these two options.");
				rangeType = Type.scanner.nextLine();
			}
		}//verification loop
	}//verifyRangeType
	
	/**
	 * Intended for debugging.
	 * 
	 * Returns Ancestor and Parent class toString() methods
	 * to include faction, name, type, hitPoints, mobile,
	 * unitType, speed, rangeType, and range.
	 */
	public String toString()
	{
		return super.toString() 
				+ "\n  Range Type: " + getRangeType()
				+ " | Range: " + getRange();
	}//toString
}//Infantry.java
