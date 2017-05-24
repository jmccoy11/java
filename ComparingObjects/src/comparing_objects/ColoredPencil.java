package comparing_objects;

public class ColoredPencil implements Comparable<ColoredPencil>
{
	private String color;
	private int pointSize;
	private double price;
	
	public ColoredPencil(String color, int pointSize, double price)
	{
		this.color = color;
		this.pointSize = pointSize;
		this.price = price;
	}

	@Override
	public int compareTo(ColoredPencil other) 
	{
		//multilevel comparison
		int colorComparison = this.color.compareTo(other.color);
		
		if(colorComparison == 0)
		{
			return this.pointSize - other.pointSize;
		}
		else
		{
			return colorComparison;
		}
		
		//compare alphabetically
//		return this.color.compareTo(other.color);
		
//		//compare by price
//		if(this.price < other.price)
//		{
//			return -1;
//		}
//		else if (this.price > other.price)
//		{
//			return 1;
//		}
//		
//		return 0;
		
		//compare by integer
		//return this.pointSize - other.pointSize;  //only works with integers
	}
	
	public String getColor() 
	{
		return color;
	}

	public void setColor(String color) 
	{
		this.color = color;
	}

	public int getPointSize() 
	{
		return pointSize;
	}

	public void setPointSize(int pointSize) 
	{
		this.pointSize = pointSize;
	}

	public double getPrice() 
	{
		return price;
	}

	public void setPrice(double price) 
	{
		this.price = price;
	}

	@Override
	public String toString() 
	{
		return "ColoredPencil [color=" + color + ", pointSize=" + pointSize + ", price=" + price + "]";
	}
}
