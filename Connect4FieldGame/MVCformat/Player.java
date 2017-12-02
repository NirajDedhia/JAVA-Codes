/** 
 * Player.java 
 *
 * Version:	v 1.7  
 *     
 * 
 * Revision: 
 *     Initial revision 1.0 09/21/2015 njd sst
 */

import java.util.*;

/**
 * The class Player implements the interface named PlayerInterface.
 * Here constructor is define to initialize the local variables. 
 *
 * @ author	Niraj Dedhia
 * @ author	Shweta Thakkar
 */
public class Player implements PlayerInterface
{
	//public Connect4FieldInterface theField = new Connect4FieldModel();
	Scanner s=new Scanner(System.in);
	String name;
	char gamePiece;
// this is how your constructor has to be
	Player(Connect4FieldInterface theField, String name, char gamePiece)
	{
		//this.theField=theField;
		this.name=name;
		this.gamePiece=gamePiece;
	}
	/**
	 * getGamePiece() It is a getter method of gamePiece. It will return
	 * particular player's piece.
	 *
	 * @param   null
	 *  
	 * @return  gamePiece character
	 */
	public char getGamePiece()
	{
		return this.gamePiece;
	}
	/**
	 * getName() It is a getter method of player's name. It will return
	 * particular player's name.
	 *
	 * @param   null
	 *  
	 * @return  name String
	 */
	public String getName()
	{
		return this.name;
	}
	/**
	 * nextMove() method will ask player to give column number.
	 *
	 * @param   null
	 *  
	 * @return Column number Integer
	 */
	public int  nextMove()
	{
		int column;
		System.out.println("Enter column number");
		column=s.nextInt();
		return column;
	}
}