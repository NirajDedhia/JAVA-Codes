import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
/** 
 *
 * @author Hiral Barot(bhiral@pdx.edu) Created on 7th Oct 2015 10:00 PM
 * Updated on 12th Oct 1:26 AM
 *
 */
 
//Defining Main Class

public class Forecast{
	
	public static void main(String [] args)
	
	{
       Scanner s= new Scanner(System.in);
      
        System.out.println("Enter temperature in fahrenheit");
        double temp=s.nextInt();
        WeatherEncap w=new WeatherEncap(temp);
        w.attribute();
        double p=w.calTemp2Deg();
        System.out.println("Temperature in degree to fahrenheit : "+p);
        System.out.println(w);
	}
}