


/** 
 * Connect4FieldController.java 
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
 * The class Connect4FieldController It is the controller which will controls the 
 * move and game.
 * It reads value from view and call required 
 *
 * @ author	Niraj Dedhia
 * @ author	Shweta Thakkar
 */
public class Connect4FieldController
{
	static Connect4FieldModel aConnect4Field;
	//static Connect4FieldView aConnect4FieldView = new Connect4FieldView();
	public Player aPlayer;
	static char[][] board;
	//static String s1;
	int index;
	Connect4FieldController(char[][] board, int index, Player aPlayer)
	{
		this.board = board;
		this.index = index;
		this.aPlayer = aPlayer;
	}
	/**
	 * playTheGame() This is the method where game begins. If second palyer name is not Computer 
	 * then game will set for two player else it will computer Vs player.
	 *
	 * @param  null
	 *  
	 * @return null
	 */
	public int playTheGame()
	{
		int i=0;
			aConnect4Field=new Connect4FieldModel(board, aPlayer);
			aConnect4Field.dropPieces(index,aConnect4Field.aPlayer.getGamePiece());	
			board = (aConnect4Field.board);
			aConnect4Field.symbol=aConnect4Field.aPlayer.getGamePiece();
			if (aConnect4Field.didLastMoveWin())
				{
					//aConnect4FieldView.lastMoveWin("Congratulations !!! "+ aConnect4Field.aPlayer.getName() +" You won");
					return 2; 
				}
			if(!aConnect4Field.isItaDraw())
				i = 0;
			else
				i = 1;
			
			return i;
	}
}