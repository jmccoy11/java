package edu.greenriver.it.intro;

public class Shoe 
{
	private double size;
	private int heelHeight;
	private String color;
	
	public Shoe(double size, int heelHeight, String color) 
	{
		super();
		this.size = size;
		this.heelHeight = heelHeight;
		this.color = color;
	}
	
	public double getSize() 
	{
		return size;
	}
	
	public void setSize(double size) 
	{
		this.size = size;
	}
	
	public int getHeelHight() 
	{
		return heelHeight;
	}
	
	public void setHeelHight(int heelHight) 
	{
		this.heelHeight = heelHight;
	}
	
	public String getColor() 
	{
		return color;
	}
	
	public void setColor(String color) 
	{
		this.color = color;
	}
	
	@Override
	public String toString() 
	{
		return "Shoes [size=" + size + ", heelHight=" + heelHeight + ", color=" + color + "]";
	}

	@Override
	public int hashCode() 
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + heelHeight;
		long temp;
		temp = Double.doubleToLongBits(size);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object other) 
	{
		if(other == null)
		{
			return false;
		}
		else if (this == other)
		{
			return true;
		}
		else if (!this.getClass().equals(other.getClass()))
		{
			return false;
		}
		else
		{
			Shoe otherShoe = (Shoe)other;
			
			return this.color.equals(otherShoe.color) && 
					this.heelHeight == otherShoe.heelHeight &&
					this.size == otherShoe.size;
		}
		/*if (this == obj)
			return true;
		
		if (obj == null)
			return false;
		
		if (getClass() != obj.getClass())
			return false;
		
		Shoes other = (Shoes) obj;
		
		if (color == null) 
		{
			if (other.color != null)
				return false;
		} 
		else if (!color.equals(other.color))
			return false;
		if (heelHight != other.heelHight)
			return false;
		if (Double.doubleToLongBits(size) != Double.doubleToLongBits(other.size))
			return false;
		return true;*/
	}
	
	
}
