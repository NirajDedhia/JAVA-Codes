/** 
 * Connect4FieldModel.java 
 *
 * Version:	v 1.7  
 *     
 * 
 * Revision: 
 *     Initial revision 1.1 10/04/2015 njd sst
 */

import java.io.*;
import java.util.*;

/**
 * The class Connect4FieldModel implements the interface named Connect4FieldInterface.
 * Here we have initialized two users and implemented the entire business logic of game.
 * This class has constructor which will initialize the board and make it ready for user to play.
 *
 * @ author	Niraj Dedhia
 * @ author	Shweta Thakkar
 */

class Connect4FieldModel implements Connect4FieldInterface
{
	Player aPlayer;
	Player bPlayer;
	int turn=0,computer_place=0;
	int last_move,last_move_row;
	char symbol;
	Random rndm= new Random();
	int computer_last_column,local_computer_column;
	char [][] board=new char[9][25];
	// Class constructor to initialize board
	Connect4FieldModel()
	{
		for(int i=0;i<9;i++)
			for(int j=8;j>=i;j--)
				board[i][j]='o';
		for(int i=0;i<9;i++)
			for(int j=9;j<=16;j++)
				board[i][j]='o';
		for(int i=0;i<9;i++)
			for(int j=17;j<25-i;j++)
				board[i][j]='o';
	}
	
	/**
	 * checkIfPiecedCanBeDroppedIn() method will accept column number and it will check 
	 * whether a particular piece can be inserted in board or not. It will return true 
	 * if it can be inserted or return false if column is full.
	 *
	 * @param   Column number Integer
	 *  
	 * @return ret boolean value as in true or false
	 */
	public boolean checkIfPiecedCanBeDroppedIn(int column)
	{
		boolean ret = true;
		if(column<0 || column>24)
		{
			//System.out.println("You have entered the wrong column");
			//System.out.println("Please insert column between 0 to 24");
			ret = false;	
		}
		else
		{
			if (board[0][column]!='o')
				{
				ret = false;		//can not
				}
		}
		return ret;		//can be
	}
	
	/**
	 * dropPieces() method will accept two parameter column number and gamePiece. 
	 * This method will drop the piece in given entered column. It will check first
	 * whether column is full or not and in case of full it will ask player to 
	 * enter other column.
	 *
	 * @param   Column number Integer
	 * @param   gamePiece Character
	 *  
	 * @return null
	 */
	
	public void dropPieces(int column, char gamePiece)
	{
		if(checkIfPiecedCanBeDroppedIn(column))
		{
			if(column>7 && column<17)
			{
				for(int row=8;row>=0;row--)
				{
					if(board[row][column]=='o')
						{
							board[row][column]=gamePiece;
							last_move=column;
							last_move_row=row+1;
							break;
						}
				}
			}
			else
			{
				for(int row=0;row<9;row++)
				{
					if(board[row][column]==0 || board[row][column]=='+' || board[row][column]=='*'|| board[row][column]=='C')
						{
							board[row-1][column]=gamePiece;
							last_move=column;
							last_move_row=row;
							break;
						}
				}
			}
		}
		else
		{
			//System.out.println("Sorry!!! Column "+column+" is full!!! you can not drop your piece here.");
			//System.out.println("Please select some other column.");
			turn-=1;
		}
	}
	
	/**
	 * didLastMoveWin() This method does not accept anything and it will be called to check whether 
	 * player won or not It will check all the possibilities like horizontally, vertically and diagonally.
	 * If player wins then it will terminate the game and and displays the result.
	 *
	 * Along with this checking this method will also checks whether board has three consecutive pieces 
	 * of player or not. This checking will help computer to block the player. 
	 *
	 * @param  null
	 *  
	 * @return null
	 */
	
	public boolean didLastMoveWin()
	{
			String oneLine;
			String secondLine="";
			String thirdLine="";
			String fourthLine="";
			int j=last_move;
			int i=last_move_row-1;
			
			oneLine= ""+board[i][j];
			
			for(int k=1;k<4;k++)
			{
				if((i+k)<9)
					oneLine+=board[i+k][j];
			}
			for(int k=1;k<4;k++)
			{
				if((j-(4-k))>=0)
					secondLine+=board[i][j-(4-k)];
			}
			secondLine+= ""+board[i][j];
			int lengthSecondLine=secondLine.length();
			for(int k=1;k<4;k++)
			{
				if((j+k)<25)
					secondLine+=board[i][j+k];
			}
			for(int k=1;k<4;k++)
			{
				if((i-(4-k))>0 && (j-(4-k))>0)
					thirdLine+=board[i-(4-k)][j-(4-k)];
				if((i-(4-k))>0 && (j+(4-k))<25)
					fourthLine+=board[i-(4-k)][j+(4-k)];
			}
			thirdLine+= ""+board[i][j];
			int lengthThirdLine=thirdLine.length();
			fourthLine+= ""+board[i][j];
			int lengthFourthLine=fourthLine.length();
			for(int k=1;k<4;k++)
			{
				if((i+k)<9 && (j+k)<25)
					thirdLine+=board[i+k][j+k];
				if((i+k)<9 && (j-k)>0)
					fourthLine+=board[i+k][j-k];
			}
			String pattern=""+symbol+symbol+symbol+symbol;
			String pattern_for_computer=""+symbol+symbol+symbol;
			
			
			if(oneLine.contains(pattern_for_computer))
			{
				if(checkIfPiecedCanBeDroppedIn(last_move))
					local_computer_column=last_move;
				else
					local_computer_column=computer_last_column;
			}
			else if(secondLine.contains(pattern_for_computer) )
			{
				local_computer_column=last_move+returnColumn(secondLine, lengthSecondLine-1,symbol);
			}
			else if(thirdLine.contains(pattern_for_computer))
			{
				local_computer_column=last_move+returnColumn(thirdLine, lengthThirdLine-1,symbol);
			}
			else if(fourthLine.contains(pattern_for_computer))
			{
				local_computer_column=last_move+returnColumn(fourthLine, lengthFourthLine-1,symbol);
			}
			else
			{
				local_computer_column=computer_last_column;
			}
			
			
			if (oneLine.contains(pattern) || secondLine.contains(pattern) || thirdLine.contains(pattern) || fourthLine.contains(pattern))
			{
				return true;
			}
			
			return false;
	}
	/**
	 * init() This method initialize two PlayerInterface objects and type cast them to Player class.
	 * After initialization it calls playTheGame() method to begin the game.
	 *
	 * @param  PlayerInterface object, PlayerInterface object
	 *  
	 * @return null
	 */
	public void init(PlayerInterface aPlayer, PlayerInterface bPlayer)
	{
		this.aPlayer=(Player)aPlayer;
		this.bPlayer=(Player)bPlayer;
		//playTheGame();
	}
	
	/**
	 * isItaDraw() This will check whether game is draw or not.
	 *
	 * @param  null
	 *  
	 * @return true if draw or false
	 */
	public boolean isItaDraw()
	{
		boolean ret=true;
		for(int column=0;column<25;column++)
			{
				if(board[0][column]=='o')
					{
					return false;
					}
			}
		System.out.println("Game Draw");
		return ret;
	}
	
	/**
	 * toString() This method will convert character array to String and return String.
	 *
	 * @param  null
	 *  
	 * @return board String
	 */
	public String toString()
	{
		String charToString="";
		for(int i=0;i<9;i++)
			{
			for(int j=0;j<25;j++)
			{
				if(board[i][j]!=0)
					charToString += " " + board[i][j];
				else
					charToString+=" "+" ";
			}
			charToString += "\n";
			}
		return (charToString);
	}
	
	public void playTheGame()
	{
		
	}
		/**
		 * returnColumn() This method will return column for computer to insert its piece.
		 * Here it will check all the possibilities in which computer can block player
		 * and decide its own move.
		 *
		 * @param  vertical, horizontal and diagonal possibilities 
		 * @param  index of last move in above string
		 * @param  piece
		 *  
		 * @return column for computer to drop its piece
		 */
		public int returnColumn(String line, int index, char sym)
		{
			int column=0;
			String temp=""+sym+sym+sym;
			if(line.contains("o"+temp+"o"))
			{
				didLastMoveWin();
			}
			if(line.contains("0"+temp+"o"))
			{
				column=(line.indexOf("0"+temp+"o")+4)-index;
			}
			if(line.contains("o"+temp+"0"))
			{
				column=(line.indexOf("o"+temp+"0"))-index;
			}
			if(line.contains("C"+temp+"C"))
			{
				column=computer_last_column;
			}
			if(line.contains("C"+temp+"o"))
			{
				column=(line.indexOf("C"+temp+"o")+4)-index;
			}
			if(line.contains("o"+temp+"C"))
			{
				column=(line.indexOf("o"+temp+"C"))-(index+1);
			}
			if(line.contains("0"+temp+"0"))
			{
				column=computer_last_column;
			}
			return column;
		}
}
