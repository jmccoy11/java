package edu.greenriver.it.lockerarray;

public class LockerArray 
{
	private int data;
	
	public LockerArray(int data)
	{
		if(data > 0)
		{
			this.data = data;
		}
	}
	     
    public void setBit(int index, boolean value)
    {
        if (value == true)
        {
            //set the bit at index "index" to 1
            int mask = (int)Math.pow(2,  index);
            data = data | mask;
        }
        else //value == false
        {
            int mask = (int)Math.pow(2, index);
            mask = ~mask;
             
            data = data & mask;
        }
    }
	     
    public boolean getBit(int index)
    {
        int mask = (int)Math.pow(2, index);
         
        return (mask & data) == mask;
    }
    
	public int getData()
	{
		return this.data;
	}
	
	public void setData(int data)
	{
		if(data > 0)
		{
			this.data = data;
		}
	}
	
	public String toString()
	{
		return "Data: " + data;
	}
}
