import java.io.*;
import java.util.*;

class tw2
{
	public static void main(String[] args)
	{
		String[] input = { "2015-08, 	    2016-04", "2015-08-15, clicks, 635", "2016-03-24, app_installs, 683", "2015-04-05, favourites, 763",
						  "2016-01-22, favourites, 788", "2015-12-26, clicks, 525", "2016-06-03, retweet, 101", "2015-12-02, app_installs, 982",
						  "2016-09-17, app_installs, 770", "2015-11-07, impression, 245", "2016-10-16, impression, 567" };
		TreeMap output = new TreeMap();  

		String dates[] = input[0].split(",");
		dates[0] = dates[0].trim();
		dates[1] = dates[1].trim();

		for(int i=1; i<input.length; i++)
		{
			processEntry(input[i].trim(), output, dates);
		}

		String[] result = formatOutput(output);
		printOutput(result);
	}

	/**
	* This method process every items and accordingly decide whether to consider in output or not.
	*
	* param: entry currrent iten read.
	* param: TreeMap output to store output.
	* param: dates contains start and end date.
	*
	* Return: Boolean
	*/
	public static void processEntry(String entry, TreeMap output, String[] dates)
	{
		String[] entryContents = entry.split(",");
		String date = entryContents[0].substring(0,7);
		if(requiredEntry(date.trim(), dates))
		{
			addEntryToOutput(entryContents, output, date.trim());
		}
	}

	/**
	* This method returns true if date for the item falls in between given start and end date 
	* else returns false.
	*
	* param: entryContents date for current item.
	* param: dates contains start and end date.
	*
	* Return: Boolean
	*/
	public static Boolean requiredEntry(String entryContents, String[] dates)
	{
		if(entryContents.compareTo(dates[0]) >= 0 && entryContents.compareTo(dates[1]) < 0 )
			return true;
		return false;
	}

	/**
	* This method adds the item to time series output as its date fall in between start and end Date.
	*
	* param: entryContents item to be added.
	* param: TreeMap output to store output.
	* param: date for that item.
	*
	* Return: Void
	*/
	public static void addEntryToOutput(String[] entryContents, TreeMap output, String date)
	{
		if(output.containsKey(date)) // If date exist then update its content
		{
			TreeMap item = (TreeMap)output.get(date);
			for(int i=1; i<entryContents.length; i+=2)
			{
				if(item.containsKey(entryContents[i].trim()))
				{
					int temp = (int)item.get(entryContents[i].trim());
					temp += (int)Integer.parseInt(entryContents[i+1].trim());
					item.put( entryContents[i].trim(), temp );
				}
				else
				{
					item.put( entryContents[i].trim(), Integer.parseInt(entryContents[i+1].trim()) );
				}
			}
			output.put(date, item);
		}
		else // else add entry to output
		{
			TreeMap item = new TreeMap();
			for(int i=1; i<entryContents.length; i+=2)
			{
				item.put(entryContents[i].trim(), Integer.parseInt(entryContents[i+1].trim()));
			}
			output.put(date, item);
		}
	}

	/**
	* This method formats the actual result in to String for displaying.
	*
	* param: TreeMap contains fial result time series data in TreeMap Datastructure.
	*
	* Return: resulting String Array
	*/
	public static String[] formatOutput(TreeMap output)
	{
		String[] result = new String[output.size()];
		Iterator i = output.entrySet().iterator();
		int j = 0;
		while(i.hasNext())
		{
			result[j] = "";
			Map.Entry me = (Map.Entry)(i.next());
			result[j] += (String)me.getKey() + ", " + treeMapToString((TreeMap)me.getValue());
			j++;
		}

		return (result);
	}

	/**
	* This method converts(formates) traMap to String.
	*
	* param: TreeMap contain result in order
	*
	* Return: composite item for perticulat date
	*/
	public static String treeMapToString(TreeMap hm)
	{
		Iterator i = hm.entrySet().iterator();
		String returnValue = "";
		while(i.hasNext())
		{
			Map.Entry me = (Map.Entry)(i.next());
			returnValue += (String)me.getKey() + ", " + me.getValue() + ", ";
		}

		return (returnValue.substring(0,returnValue.length()-2));
	}

	/**
	* This method prints the output from end date to new date.
	*
	* param: result in asc order
	*
	* Return: Void
	*/
	public static void printOutput(String[] result)
	{
		for(int i=result.length-1; i>=0; i--)
		{
			System.out.println(result[i]);
		}
	}
}