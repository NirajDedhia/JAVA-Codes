/** 
 * Connect4FieldView.java 
 *
 * Version:	v 1.7  
 *     
 * 
 * Revision: 
 *     Initial revision 1.0 10/04/2015 njd sst
 */
import java.io.*;
import java.util.*;

/**
 * The class Connect4FieldView It will have main method and It is the View component.
 * HEre it takes input from user and pass it to controller 
 * and reads the output from user and displays the output. 
 * Here we provide two options to player where he can select 
 * two player game or single player.
 *
 * @ author	Niraj Dedhia
 * @ author	Shweta Thakkar
 */
public class Connect4FieldView
{
	static Scanner s=new Scanner(System.in);
	public static void main (String args[])
	{
		Connect4FieldController cc;
		System.out.println("===========================================================");
		System.out.println("                      GAME BEGINS NOW                      ");
		System.out.println("===========================================================");
		System.out.println("");
		System.out.println("--->Enter 1 for One player game -- YOU vs COMPUTER");
		System.out.println("--->Enter 2 for Two player game -- Player A vs Player B");
		int option = s.nextInt();
		if(option>2 || option<1)
		{
		System.out.println("You have entered wrong option");
		}
		else
		{
		cc=new Connect4FieldController(option);
		Connect4FieldController.start();
		}
	}
	/**
	 * lastMoveWin(String) method will display the which player won.
	 *
	 * @param  String
	 *  
	 * @return avoided
	 */
	public static void lastMoveWin(String win)
	{
		System.out.println(win);
	}
	/**
	 * displayBoard(String) method will display the board.
	 *
	 * @param  String
	 *  
	 * @return avoided
	 */
	public static void displayBoard(String board)
	{
		System.out.println(board);
	}
	/**
	 * turn(String) method will display the whose chance value.
	 *
	 * @param  String
	 *  
	 * @return avoided
	 */
	public static void turn(String turnPrint)
	{
		System.out.println(turnPrint);
	}
}