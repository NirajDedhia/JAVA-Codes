

import java.util.*;

public class HashSetNew extends HashSet
{
	public Object a[]=new Object[10000];
	public int size=0;
	public static int indexContain = 0;
	public boolean add(Object e) 
	{
		if(!contains(e))
		{
			int index = indexing(e.hashCode());
			this.a[index] = e;
			this.size++;
			if(this.size == (this.a.length*0.50))
			{
				rehashing();
			}
			return true;
		}
		return false;
		
	}
	public int indexing(int hashcode)
	{
		if(hashcode < 0)
			hashcode = hashcode * -1;
		int index=hashcode % this.a.length;
		while(this.a[index] != null)
		{
			index = (index+1)%this.a.length;
		}
		return index;
	}
	
	public void rehashing ()
	{
		Object b[] = new Object[2*this.a.length];
		Object c[] = this.a;
		this.a = b;
		this.size = 0;
		for(int i = 0;i<c.length;i++)
		{
			add(c[i]);
		}
	}
	
	public void clear() 
	{
		this.size = 0;
		this.a = new Object [10000];
	}

	public boolean contains(Object o) 
	{
		if(o != (null))
		{
			indexContain = o.hashCode();
			if(indexContain<0)
				indexContain = indexContain * -1;
			indexContain = indexContain % a.length;
			while( a[indexContain]!=null && !a[indexContain].equals(o))
			{
				indexContain= (indexContain+1)%a.length;
			}
			if(a[indexContain]==null)
				return false;
			else
				return true;
		}
		else
		{
			return true;
		}
	}

	public boolean isEmpty() 
	{
		if (this.size == 0)
			return true;
		return false;
	}

	public boolean remove(Object o) 
	{
		indexContain = 0;
		if(contains(o))
		{
			a[indexContain]=null;
			size = size -1;
			return true;
		}
		return false;
	}

	public int size() 
	{
		return this.size;
	}
	
	class myIterator implements Iterator
	{
		int i =0;

		public boolean hasNext() 
		{
			return i<a.length;
		}

		public Object next() 
		{
			int in = i;
			i++;
			return a[in];
		}
		
		public void remove()
		{
			
		}
		
	}
	public Iterator iterator() 
	{
		return new myIterator();
	}
}