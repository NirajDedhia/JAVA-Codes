
import java.util.Scanner;

public class WeatherEncap{
	
//declaring variables as private
private static double temp;
//private static String skyCondition;
public enum skyCondition{sunny, snowy, cloudy, rainy};
static skyCondition s1;

//Default Constructor
WeatherEncap()
{
System.out.println("The weather today is cloudy more than it’s sunny");
System.out.println("the average temperature for the Portland area is 54.5°");
this.temp=54.5;
}
    
//Parameterized constructor with parameters as temperature and Skyconditions
WeatherEncap(double temp)
{
this.temp = temp;
}


//Defining method to convert temp from Fraheneit to Degree celsius
public static double calTemp2Deg()
{
	double degreeTemp;
    degreeTemp = ((temp-32) * 5/9);
	//System.out.println("The Temperature in Degree Celsius : "+degreeTemp);
	return degreeTemp;
}
static String stringWeather;
static String str;
public static void attribute()
{
	Scanner s = new Scanner(System.in);
	System.out.println("Enter the Weather (Sunny, Cloudy, Snowy, Rainy)");
	String weather=s.nextLine();
	weather=weather.toLowerCase();
	
	s1=skyCondition.sunny;
	if(temp<32)
		s1=skyCondition.sunny;
	else if (temp>32 && temp<65)
		s1=skyCondition.cloudy;
	else if (temp>65 && temp<100)
		s1=skyCondition.snowy;
	else if (temp>100)
		s1=skyCondition.rainy;
	
	switch(s1)
	{
	case sunny :str = "sunny"; 
				if(equals("sunny",weather))
					{stringWeather="Consistant";}
				else {stringWeather="Not Consistant";}
				break;
				
	case cloudy :str = "cloudy";  
				if(equals("cloudy",weather))
					{stringWeather="Consistant";}
				else {stringWeather="Not Consistant";}
				break;

	case snowy :str = "snowy"; 
				if(equals("snowy",weather))
					{stringWeather="Consistant";}
				else {stringWeather="Not Consistant";}
				break;

	case rainy :str = "rainy";
				if(equals("rainy",weather))
					{stringWeather="Consistant";}
				else {stringWeather="Not Consistant";}
				break;
	
	}
	
 }

public static boolean equals(String str1, String str2)
{
	boolean ret;
	if(str1.compareTo(str2)==0)
		ret = true;
	else
		ret=false;
	return ret;
}

@Override
public String toString()
{
String s= "Temperature entered by you in fahrenheit is "+ this.temp;
s=s+"\n";
s=s+"Sky condition is : "+str;
s=s+"\n";
s=s+"Weather is "+stringWeather;
return s;

}
}
