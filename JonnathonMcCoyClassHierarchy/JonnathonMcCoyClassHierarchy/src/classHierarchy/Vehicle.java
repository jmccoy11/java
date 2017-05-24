/*
 * Jonnathon McCoy
 * Assignment ClassHierarchy
 * Infantry.java
 * 04/14/2016
 */

package classHierarchy;

/**
 * This class represents an Vehicle Unit object.
 * 
 * @author Jonnathon McCoy
 * @version 1.0
 */
public class Vehicle extends Unit
{
	//fields
	String groundOrAir;
	boolean transport;
	
	//constructors
	/**
	 * Creates an Vehicle object.
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
	 * @param groundOrAir groundOrAir can only be Ground
	 * or Air.
	 * 
	 * @param transport Boolean for if the unit can transport
	 * other units.
	 */
	public Vehicle(String faction, String name, 
			int hitPoints, double speed, String groundOrAir,
			boolean transport)
	{
		super(faction, name, hitPoints, "Vehicle",
				100);
		
		verifyGroundOrAir(groundOrAir);
		
		this.groundOrAir = groundOrAir;
		this.transport = transport;
	}//Vehicle
	
	//methods
	  //getters
	/**
	 * Getter for groundOrAir.
	 * 
	 * 
	 * @return Returns String for groundOrAir object.
	 */
	public String getGroundOrAir()
	{
		if(groundOrAir.toLowerCase().equals("air"))
		{
			return "Flying";
		}
		else return groundOrAir;
	}//getGroundOrAir
	
	/**
	 * Getter for transport.
	 * 
	 * 
	 * @return Returns Boolean for transport object.
	 */
	public boolean getTransport()
	{
		return transport;
	}//getTransport
	
	  //setters
	
	/**
	 * Setter for transport.
	 * 
	 * 
	 * @param range Pass the new boolean for the transport 
	 * object.
	 */
	public void setTransport(boolean transport)
	{
		this.transport = transport;
	}//setTransport
	
	  //other methods
	/**
	 * Verifies groundOrAir is Ground or Air.  Prompts
	 * user for correct input.
	 * 
	 * Method is static so it can be used in
	 * CreatorDriver.java.
	 * 
	 * 
	 * @param groundOrAir Passes current groundOrAir for 
	 * verification.
	 */
	public static String verifyGroundOrAir(String groundOrAir)
	{
		while(true)//verification loop
		{
			if(groundOrAir.toLowerCase().equals("ground") ||
			   groundOrAir.toLowerCase().equals("air"))
			{
				return groundOrAir;
			}
			else
			{
				System.out.println("Vehicle type can only "
						+ "be Ground or Air.  Please "
						+ "enter one of these two options.");
				groundOrAir = Type.scanner.nextLine();
			}
		}//verification loop
	}//verifyGroundOrAir
	
	/**
	 * Intended for debugging.
	 * 
	 * Returns Ancestor and Parent class toString() methods
	 * to include faction, name, type, hitPoints, mobile,
	 * unitType, speed, groundOrAir, and transport.
	 */
	public String toString()
	{
		return super.toString() 
				+ "\n  Ground or Flying: " + getGroundOrAir()
				+ " | Transport?: " + getTransport();
	}//toString
}//Vehicle.java
