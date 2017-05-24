/*
 * Jonnathon McCoy
 * Assignment ClassHierarchy
 * Building.java
 * 04/14/2016
 */

package classHierarchy;

/**
 * This class represents a Building object.
 * 
 * 
 * @author Jonnathon McCoy
 * @version 1.0
 */
public class Building extends Type
{
	//fields
	String buildingType; //production or upgrade
		
	//constructors
	/**
	 * Creates a building object.
	 * 
	 * 
	 * @param faction Sends the faction to the ancestor
	 * class.  Can only be Terran, Zerg, or Protoss.
	 * 
	 * @param name Name of the object.
	 * 
	 * @param hitPoints Maximum hit points.
	 * 
	 * @param buildingType Building type can only be a 
	 * production or static building.
	 */
	public Building(String faction, String name, 
			int hitPoints, String buildingType)
	{
		super(faction, name, "Building", hitPoints, false);
		
		verifyBuildingType(buildingType);
		this.buildingType = buildingType;
	}
	
	//methods
	  //getters
	/**
	 * This is a getter for buildingType
	 * 
	 * 
	 * @return returns String buildingType object.
	 */
	public String getBuildingType()
	{
		return buildingType;
	}
	
	  //other methods
	/**
	 * Verifies building type is Production or Static.
	 * Prompts user for correct input.
	 * 
	 * Method is static so it can be used in the
	 * CreatorDriver.java.
	 * 
	 * 
	 * @param buildingType Passes current buildingType for
	 * verification
	 */
	public static void verifyBuildingType(String buildingType)
	{
		while(true) //is buildingType production or static
		{
			if(buildingType.toLowerCase().equals("production")
					||
			   buildingType.toLowerCase().equals("static"))
			{
				break;
			}
			else //get input from user
			{
				System.out.println("Building type can only "
						+ "be Production or Static.  Please "
						+ "enter one of these two options.");
				buildingType = Type.scanner.nextLine();
			}
		}
	}
	
	/**
	 * Intended for debugging.
	 * 
	 * Returns Ancestor and Parent class toString() methods
	 * to include faction, name, type, hitPoints, mobile,
	 * and buildingType.
	 */
	public String toString()
	{
		return super.toString() 
				+ "\n  Building Type: " + getBuildingType();
	}
}
