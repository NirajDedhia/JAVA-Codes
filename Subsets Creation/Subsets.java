/** 
 * Subsets.java 
 *
 * Version:	v 1.7  
 *     
 * Revision: 
 *     Initial revision 1.0 09/03/2015 njd and sst
 */

import java.util.*;
import java.io.*;

/**
 * The class Subsets will evaluate and print all possible subsets for 'people' peoples
 *
 * @ author	Niraj Dedhia
 * @ author	Shweta Thakkar
 */

class Subsets
{
	/**
	 * The main program.
	 * Main method initiate the process and it will take value of 'n' from
	 * user and post which it calls cevaluateSets() method
	 * to evaluate and print all the possible subsets for 'people'.
	 *
	 * @param	args    command line arguments (Avoided)
	 * 
	 */

	public static void main(String[] args)
	{
		Scanner s = new Scanner(System.in);
		System.out.println("Enter people of people are attending party");
		int people = Integer.parseInt(s.next());
		//int people=3;
		System.out.println("");
		System.out.println("========================================================");
		System.out.println("     All possible Subsets for "+ people + " people are as follows :");
		System.out.println("========================================================");
		System.out.println("");
		evaluateSets(people);
	}
	
	/**
	 *'evaluateSets()' : This method will accept an integer for 
	 * which we need to evaluate subsets.
	 * It will find total how many sets can be formed for 'people'.
	 * It will display those sets as well.
	 *
	 * @param	args    people
	 * 
	 */

	public static void evaluateSets(int people)
	{
		int poss = (int)(Math.pow(2,people));
		String s[] = new String[poss];
		for(int i=0; i<poss; i++)
		{
			s[i]=(new StringBuffer(Integer.toBinaryString(i))).reverse().toString();
		}
		
		for(int i=0; i<poss; i++)
		{
			String sets= "{";
			for(int j=0; j<s[i].length(); j++)
			{
				if(s[i].charAt(j)=='1')
					sets=sets+(j+1)+",";
			}
			if(!sets.equals("{"))
				sets=sets.substring(0, sets.length()-1);
			
			sets=sets+"}";
			System.out.print(" "+sets+" ");
			s[i]=sets;
			System.out.println();
		}
		System.out.println("");	  
	}
}
