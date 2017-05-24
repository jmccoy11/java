/*
 * Jonnathon McCoy
 * Assignment ClassHierarchy
 * Unit.java
 * 04/14/2016
 */

package classHierarchy;

/**
 * This class represents a Unit Type.
 * 
 * 
 * @author Jonnathon McCoy
 * @version 1.0
 */
public class Unit extends Type
{
	//fields
	String unitType; //infantry or vehicle
	Double speed;
	
	//constructors
	/**
	 * Creates a Unit object.
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
	 * @param unitType unitType can only be Unit or
	 * Building.
	 * 
	 * @param speed Speed of unit in distance (units)
	 * per second.
	 */
	public Unit(String faction, String name, int hitPoints, 
			String unitType, double speed)
	{
		super(faction, name, "Unit", hitPoints, true);
		
		verifyUnitType(unitType);
		
		this.unitType = unitType;
		this.speed = speed;
	}//Unit
	
	//methods
	  //getters
	/**
	 * Getter for unitType.
	 * 
	 * 
	 * @return Returns String for unitType object.
	 */
	public String getUnitType()
	{
		return unitType;
	}//getUnitType
	
	/**
	 * Getter for speed.
	 * 
	 * 
	 * @return Returns double for speed.
	 */
	public double getSpeed()
	{
		return speed;
	}//getSpeed
	
	  //setters
	/**
	 * Setter for speed.
	 * 
	 * 
	 * @param range Pass the new speed for the object.
	 */
	public void setSpeed(double speed)
	{
		this.speed = speed;
	}//setSpeed
	
	  //other methods
	/**
	 * Verifies unitType is Building or Unit.  Prompts
	 * user for correct input.
	 * 
	 * Method is static so it can be used in
	 * CreatorDriver.java.
	 * 
	 * 
	 * @param unitType Passes current unitType for 
	 * verification.
	 */
	public static void verifyUnitType(String unitType)
	{
		while(true)//verification loop
		{
			if(unitType.toLowerCase().equals("infantry") ||
			   unitType.toLowerCase().equals("vehicle"))
			{
				break;
			}
			else
			{
				System.out.println("Unit type can only be "
						+ "Infantry or Vehicle.  Please "
						+ "enter one of these two options.");
				unitType = Type.scanner.nextLine();
			}
		}//verification loop
	}//verifyUnitType
	
	/**
	 * Intended for debugging.
	 * 
	 * Returns Ancestor and Parent class toString() methods
	 * to include faction, name, type, hitPoints, mobile,
	 * unitType, and speed.
	 */
	public String toString()
	{
		return super.toString() + "\n  Unit Type: " + 
				getUnitType() + " | Speed: " + getSpeed();
	}//toString
}//Unit.java
