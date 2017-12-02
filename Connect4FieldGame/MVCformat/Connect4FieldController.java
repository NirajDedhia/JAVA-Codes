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
	static Connect4FieldModel aConnect4Field=new Connect4FieldModel();
	static Connect4FieldView aConnect4FieldView = new Connect4FieldView();
	static int option;
	Connect4FieldController(int option)
	{
		this.option=option;
	}
	/**
	 * start() starting point of controller, first it initializes the players
	 * and then begins the game by calling playTheGame().
	 *
	 * @param  null
	 *  
	 * @return null
	 */
	public static void start()
	{
		Player aPlayer = new Player(aConnect4Field, "A", '+');
		Player bPlayer = new Player(aConnect4Field, "B", '*');
		if(option==2)
		{
			aConnect4Field.init(aPlayer, bPlayer);
			playTheGame();
		}
		else
		{
			bPlayer.name="Computer";
			bPlayer.gamePiece='C';
			aConnect4Field.init(aPlayer, bPlayer);
			playTheGame();
		}
	}
	/**
	 * playTheGame() This is the method where game begins. If second palyer name is not Computer 
	 * then game will set for two player else it will computer Vs player.
	 *
	 * @param  null
	 *  
	 * @return null
	 */
	public static void playTheGame()
	{


		if(aConnect4Field.bPlayer.getName()!="Computer")
		{
			while(!aConnect4Field.isItaDraw())
			{
				if(aConnect4Field.turn%2==0)
				{
					aConnect4Field.dropPieces(aConnect4Field.aPlayer.nextMove(),aConnect4Field.aPlayer.getGamePiece());
					aConnect4FieldView.displayBoard(aConnect4Field.toString());
					aConnect4Field.symbol=aConnect4Field.aPlayer.getGamePiece();
					if (aConnect4Field.didLastMoveWin())
					{
						aConnect4FieldView.lastMoveWin("Congratulations !!! Player A, You won");
						break;
					}
				}
				else
				{
					aConnect4Field.dropPieces(aConnect4Field.bPlayer.nextMove(),aConnect4Field.bPlayer.getGamePiece());
					aConnect4FieldView.displayBoard(aConnect4Field.toString());
					aConnect4Field.symbol=aConnect4Field.bPlayer.getGamePiece();
					if (aConnect4Field.didLastMoveWin())
					{
						aConnect4FieldView.lastMoveWin("Congratulations !!! Player B, You won");
						break;
					}
				}
				aConnect4Field.turn+=1;
			}
		}
		else
		{
			int column=16;
			while(!aConnect4Field.isItaDraw())
			{
				if(aConnect4Field.turn%2==0)
				{
					aConnect4FieldView.turn("Player A turn");
					aConnect4Field.dropPieces(aConnect4Field.aPlayer.nextMove(),aConnect4Field.aPlayer.getGamePiece());
					aConnect4FieldView.displayBoard(aConnect4Field.toString());
					aConnect4Field.symbol=aConnect4Field.aPlayer.getGamePiece();
					if (aConnect4Field.didLastMoveWin())
					{
						aConnect4FieldView.lastMoveWin("Congratulations !!! Player A, You won");
						break;
					}
				}
				else
				{
					aConnect4FieldView.turn("Computer turn");
					if(aConnect4Field.turn<2)
						{
						column=aConnect4Field.rndm.nextInt(24);
						if(column<0)
							column*=-1;
						if(aConnect4Field.last_move==0)
							aConnect4Field.dropPieces(aConnect4Field.last_move+1,aConnect4Field.bPlayer.getGamePiece());
						else
							aConnect4Field.dropPieces(aConnect4Field.last_move-1,aConnect4Field.bPlayer.getGamePiece());
						aConnect4Field.computer_last_column= column;
						}
					else
						{
						column=aConnect4Field.local_computer_column;
						if(aConnect4Field.checkIfPiecedCanBeDroppedIn(column))
							aConnect4Field.dropPieces(column,aConnect4Field.bPlayer.getGamePiece());
						else
							{
							aConnect4Field.symbol='C';
								aConnect4Field.didLastMoveWin();
								if(aConnect4Field.checkIfPiecedCanBeDroppedIn(column))
									aConnect4Field.dropPieces(column,aConnect4Field.bPlayer.getGamePiece());
								else
								{
									column=(column<16 && column>0 ? column+1:column-1);
									aConnect4Field.dropPieces(column,aConnect4Field.bPlayer.getGamePiece());
								}
							}
						}
					aConnect4FieldView.displayBoard(aConnect4Field.toString());
					aConnect4Field.symbol=aConnect4Field.bPlayer.getGamePiece();
					if (aConnect4Field.didLastMoveWin())
					{
						aConnect4FieldView.lastMoveWin("Computer Won and you loose");
						break;
					}
				}
				aConnect4Field.turn+=1;
			}
		}
	
	
	}
}