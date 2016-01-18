

/** 
 * Player.java 
 *
 * Version:	v 1.7  
 *     
 * 
 * Revision: 
 *     Initial revision 1.0 11/16/2015 njd sst
 */

import java.net.DatagramSocket;
import java.net.Socket;
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
	Scanner s=new Scanner(System.in);
	String name;
	char gamePiece;
	public DatagramSocket sock;
	Player(String name, char gamePiece, DatagramSocket server)
	{
		this.name=name;
		this.gamePiece=gamePiece;
		this.sock = server;
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