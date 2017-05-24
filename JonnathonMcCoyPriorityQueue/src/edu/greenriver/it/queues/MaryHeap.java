package edu.greenriver.it.queues;

/**
 * This class acts as a priority queue using an m-ary min-heap.
 * 
 * @author ?
 * @version ?
 */
public class MaryHeap<T extends Comparable<T>>
{
	private static final int INIT_SIZE = 10;
	private static final double RESIZE_FACTOR = 1.5;
	private static final int M = 4;
	
	//the m-ary tree is stored in this array
	private T[] data;
	private int size;
	
	//default constructor
	@SuppressWarnings("unchecked")
	public MaryHeap()
	{
		data = (T[]) new Comparable[INIT_SIZE];
	}
	
	//parameterized constructor
	@SuppressWarnings("unchecked")
	public MaryHeap(T[] inputs)
	{
		data = (T[]) new Comparable[inputs.length];
		
		for (int i = 0; i < inputs.length; i++)
		{
			data[i] = inputs[i];
		}
		
		size = inputs.length;
		
		//then build the heap
		buildHeap();
	}
	
	public void insert(T element)
	{
		//check to see if we need to resize
		if (size >= data.length-1)
		{
			resize();
		}
		
		//insert a new element into the heap
		data[size] = element;
		swim(size);
		size++;
	}
	
	public T peek()
	{
		if (isEmpty())
		{
			return null;
		}
		
		//returns, but does NOT remove, the minimum element in the heap
		return data[0];
	}
	
	//removes AND returns the minimum element in the heap
	public T delMin()
	{
		if (isEmpty())
		{
			return null;
		}
		
		//save our result
		T result = data[0];
		data[0] = null;
		
		//move the top index to the root of the tree
		size--;
		swap(0, size);
		sink(0);
		
		return result;
	}
	
	public int size()
	{
		//returns the number of elements in the heap
		return size;
	}
	
	public boolean isEmpty()
	{
		//returns true if the heap is empty, otherwise false
		return size == 0;
	}
	
	@SuppressWarnings("unchecked")
	public void clear()
	{
		size = 0;
		
		//works but I don't think it was what you were looking for in one line of code
		// while(!isEmpty()) delMin(); 
	}
	
	//returns true if the heap contains the given element, otherwise false
	public boolean contains(T element)
	{
		for (int i = 0; i < size; i++)
		{
			if (data[i].compareTo(element) == 0)
			{
				return true;
			}
		}
		return false;
	}
	
	public String toString() 
	{
		String result = "[";
		for(int i = 0; i < data.length; i++)
		{
			if(i == data.length-1)
			{
				result += data[i] + "]";
			}
			else
			{
				result += data[i] + ", ";
			}
		}
		return result;
	}
	
	//private methods
	
	private void buildHeap()
	{
		for (int i = size / M; i >= 0; i--)
		{
			sink(i); //sink the child
		}
	}
	
	private void sink(int index)
	{
		while (index < size / M)
		{
			int left = index * M + 1;
			int midLeft = index * M + 2;
			int midRight = index * M + 3;
			int right = index * M + 4;
			
			int indexToCheck = left;
			
			//check to ensure that the index doesn't exceed the length of the array
			//check to ensure that the data at that index is not equal to null
			//check to see if the data on the right is smaller than the indexToCheck
			if (right < size && data[right] != null &&
					data[right].compareTo(data[indexToCheck]) < 0)
			{
				indexToCheck = right;
			}
			if (midRight < size && data[midRight] != null &&
					data[midRight].compareTo(data[indexToCheck]) < 0)
			{
				indexToCheck = midRight;
			}
			if (midLeft < size && data[midLeft] != null &&
					data[midLeft].compareTo(data[indexToCheck]) < 0)
			{
				indexToCheck = midLeft;
			}
			
			//compare the index with the smallest child
			if(data[indexToCheck].compareTo(data[index]) < 0)
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
	
	private void swim(int index)
	{
		//stop when we reach the root (index 1)
		while (index > 0)
		{
			int parentIndex = (index -1) / M;
			
			if (data[index].compareTo(data[parentIndex]) < 0)
			{
				swap(index, parentIndex);
			}
			
			index = parentIndex;
		}
	}
	
	private void swap(int first, int second)
	{
		T temp = data[first];
		data[first] = data[second];
		data[second] = temp;
	}
	
	private void resize()
	{
		Double d = Math.floor(this.data.length*RESIZE_FACTOR);
		int newSize = d.intValue();
		@SuppressWarnings("unchecked")
		T[] newData = (T[]) new Comparable[newSize];
		
		for (int i = 0; i < data.length; i++)
		{
			newData[i] = data[i];
		}
		
		data = newData;
	}
}