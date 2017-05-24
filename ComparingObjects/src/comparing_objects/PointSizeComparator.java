package comparing_objects;

import java.util.Comparator;

public class PointSizeComparator implements Comparator<ColoredPencil>
{
	@Override
	public int compare(ColoredPencil pencil1, ColoredPencil pencil2) 
	{
		return pencil1.getPointSize() - pencil2.getPointSize();
	}

}
