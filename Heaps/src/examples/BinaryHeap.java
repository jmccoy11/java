package examples;

public class BinaryHeap<T extends Comparable<T>>
{
	private static final int RESIZE_FACTOR = 2;

	private static final int INITIAL_SIZE = 10;
	
	private T[] data;
	private int size;
	
	@SuppressWarnings("unchecked")
	public BinaryHeap()
	{
		data = (T[])new Comparable[INITIAL_SIZE + 1];
	}
	
	@SuppressWarnings("unchecked")
	public BinaryHeap(T[] inputs)
	{
		//create an array of a similar size
		data = (T[])new Comparable[inputs.length + 1];
		
		//copy across my elements from the input to the array
		for (int i = 0; i < inputs.length; i++)
		{
			data[i + 1] = inputs[i];
		}
		size = inputs.length;
		
		//then build the heap
		buildHeap();
	}
	
	private void buildHeap()
	{
		for (int i = size / 2; i >= 1; i--)
		{
			sink(i);
		}
	}
	
	public void insert(T element)
	{
		//check if we need to resize
		if (size >= data.length - 1)
		{
			resize();
		}
		
		//add the new element
		size++;
		data[size] = element;
		swim(size);
	}
	
	public T delMin()
	{
		if (isEmpty())
		{
			return null;
		}
		
		//save our result
		T result = data[1];
		data[1] = null;
		
		//move the top index to the root of the tree
		swap(1, size);
		size--;
		sink(1);
		
		return result;
	}
	
	public int size()
	{
		return size;
	}
	
	public boolean isEmpty()
	{
		return size == 0;
	}
	
	public T peekMin()
	{
		return null;
	}
	
	@SuppressWarnings("unchecked")
	private void resize()
	{
		T[] newData = (T[])new Comparable[data.length * RESIZE_FACTOR];
		
		//copy across all elements
		for (int i = 0; i < data.length; i++)
		{
			newData[i] = data[i];
		}
		
		data = newData;
	}
	
	private void swim(int index)
	{
		//stop when we reach the root (index 1)
		while (index > 1)
		{
			int parentIndex = index / 2;
			
			if (data[index].compareTo(data[parentIndex]) < 0)
			{
				swap(index, parentIndex);
			}
			
			index = parentIndex;
		}
	}
	
	private void sink(int index)
	{
		while (index <= size / 2)
		{
			int left = 2 * index;
			int right = 2 * index + 1;
			
			int indexToCheck = left;
			
			//if there is a right child and it is the smaller child
			if (right < data.length && data[right] != null && 
					data[right].compareTo(data[left]) < 0)
			{
				indexToCheck = right;
			}
			
			//compare the parent with the smallest child
			if (data[indexToCheck].compareTo(data[index]) < 0)
			{
				swap(indexToCheck, index);
				index = indexToCheck;
			}
			else
			{
				break;
			}
		}
	}
	
	private void swap(int first, int second)
	{
		T temp = data[first];
		data[first] = data[second];
		data[second] = temp;
	}
}








