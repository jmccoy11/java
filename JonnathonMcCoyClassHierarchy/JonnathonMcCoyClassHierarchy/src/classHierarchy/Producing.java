/*
 * Jonnathon McCoy
 * Assignment ClassHierarchy
 * Producing.java
 * 04/14/2016
 */

package classHierarchy;

/**
 * This class represents a Producing Building object.
 * 
 * @author Jonnathon McCoy
 * @version 1.0
 */
public class Producing extends Building
{
	//fields
	String production; //What it produces
	
	//constructors
	/**
	 * Creates a Producing object.
	 * 
	 * @param faction Faction can only be Terran, Zerg
	 * or Protoss.
	 * 
	 * 
	 * @param name Name of the object.
	 * 
	 * @param hitPoints Maximum hit points.
	 * 
	 * @param produces What it produces.
	 */
	public Producing(String faction, String name, 
			int hitPoints, String production)
	{
		super(faction, name, hitPoints, "Production");
		
		this.production = production;
	}//Producing
	
	//methods
	  //getters
	/**
	 * Getter for Production.
	 * 
	 * 
	 * @return Returns String production object.
	 */
	public String getProduction()
	{
		return production;
	}//getProduction
	
	  //setters
	/**
	 * Setter for Production.
	 * 
	 * 
	 * @param production Pass the new production for the
	 * object.
	 */
	public void setProduction(String production)
	{
		this.production = production;
	}//setProduction
	
	  //other methods
	/**
	 * Intended for debugging.
	 * 
	 * Returns Ancestor and Parent class toString() methods
	 * to include faction, name, type, hitPoints, mobile,
	 * buildingType and producing.
	 */
	public String toString()
	{
		return super.toString()
				+ "\n  Produces: " + getProduction();
	}//toString
}//Producing.java
