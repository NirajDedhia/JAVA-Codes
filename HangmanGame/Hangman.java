/** 
 * Hangman.java 
 *
 * Version:	v 1.7  
 *     
 * 
 * Revision: 
 *     Initial revision 1.0 09/13/2015 njd sst
 */

import java.io.*;
import java.util.*;

/**
 * This is the implementation of Hangman game.
 * Here we are reading a word from file and then asking users to guess 
 * the missing letters in a word. On the basis of guess and attempts 
 * we are deciding score. Right guess will add 10 point and wrong will 
 * reduce 5 points as well as a chance to guess. 
 * The player with highest score will win.
 * Also we are displaying top 4 players at the end of game.
 * 
 * @ author	Niraj Dedhia
 * @ author	Shweta Thakkar
 */

public class Hangman 
	{
	/**
	 * The main program.
	 * Main method is calling two methods one is fileRead() which 
	 * will read all the words in .txt file and randomly select a word.
	 * PlayerVsPlayer() where the exact game starts.
	 *
	 * @param	args    command line arguments (Avoided)
	 * 
	 */
	public static void main(String [] args) 
		{
			String[] words = fileRead();
			playerVsplayer(words);			
		}
	/**
	 * fileRead() :  This method will read the text file here in our case it is 'words.txt'.
	 * 
	 * @return words[]  Its an array of all the words present in a file.
	 * 
	 */
	public static String[] fileRead()
	{
		int noOfWords=35;
		String words[]=new String[noOfWords];
		String textFile= "words.txt";
		try 
		{
			FileReader filereader = new FileReader(textFile);
			BufferedReader bufferedreader = new BufferedReader(filereader);
			for(int i=0;i<noOfWords;i++)
				{
				words[i]=bufferedreader.readLine();
				}
			bufferedreader.close();
		
		}catch(IOException ex) 
		{
		System.out.println("Error reading file '" + textFile + "'");                  
		}
		
		return(words);
	}
	/**
	 * playerVsplayer() :  Here we are taking array of words and this method will 
	 * randomly select a word from list. Post which this method is randomly selecting 
	 * some letters to display and other needs to be guessed by player.
	 * Also it calls a method names playGame where actual logic resides.
	 * At the end of game it displays the score of individual player.
	 * 
	 * @param words[]  Its an array of all the words present in a file.
	 * 
	 */
	public static void playerVsplayer(String[] words)
	{
		Random rndm= new Random();
		String word;
		Scanner sin = new Scanner(System.in);
		System.out.println("Enter number of players");
		int n=Integer.parseInt(sin.next());
		int score[]=new int[n];
		
		for(int noOfPlayer=0;noOfPlayer<n;noOfPlayer++)
		{
			char s[][]=new char[13][9];
			for(int i=0;i<13;i++)
				for(int j=0;j<9;j++)
					s[i][j]='.';
			s[12][0]='*';
			s[12][2]='*';
			for(int i=0;i<12;i++)
				s[i][1]='*';
			word = words[rndm.nextInt(words.length)]; 
			System.out.println("-------------------------------------------------------------");
			System.out.println("                  Player "+ (noOfPlayer+1)+" start:");
			System.out.println("-------------------------------------------------------------");
			score[noOfPlayer]=0;
			System.out.print("                       ");
			score[noOfPlayer]=playGame(s, noOfPlayer,word);
			System.out.println("Score of Player "+ (noOfPlayer+1) +" : "+ score[noOfPlayer]);
			System.out.println("-------------------------------------------------------------");
			System.out.println();
		}	
		calculateScore(score, n);
	}
	/**
	 * playGame() : The game logic is implemented in this method.
	 * It will display a word with blanks and player needs to guess a letter.
	 * If player guesses right then it will add 10 point else it will deduct  5
	 * point from his/her score. After each and every wrong guess this method
	 * draws hangman body and once all attempts gets over it will draw complete 
	 * hangman and ends the game for the respective player. Once player 
	 * guesses the whole word correctly then it will print
	 * score and ends the game for the player.
	 * 
	 * @param s[][] it is multidimensional array for drawing hangman.
	 * @param noOfPlayer Total number of player playing this game.
	 * @param word its a selected word for player to guess.
	 * 
	 * @return score its a individual score for each player.
	 * 
	 */
	public static int playGame(char[][] s, int noOfPlayer, String word)
	{
		String newWord="";
		int found=0;
		Scanner sin = new Scanner(System.in);
		Random rndm= new Random();
		int score=0;
		
		String[] userWord=new String[word.length()];
		for(int userWordElements=0;userWordElements<word.length();userWordElements++)
		{
			userWord[userWordElements]="_";
		}
		for(int i=0;i<4;i++)
		{
			int displayletter=(rndm.nextInt(word.length()));
			userWord[displayletter]=word.substring(displayletter,displayletter+1);
		}
		for(int userWordElements=0;userWordElements<word.length();userWordElements++)
		{
			System.out.print(userWord[userWordElements]+" ");
		}
	
		System.out.println();
		for(int chances=1;chances<=8;chances++)
		{
			newWord="";
			found=1;
			System.out.print("==>  ");
			System.out.print("Enter your guess :   ");
			String guessedLetter=sin.next();
			int wordOccurances=0;
			int userWordOccurances=0;
			for(int wordLength=0;wordLength<word.length();wordLength++)
			{
				if(word.substring(wordLength,wordLength+1).equals(guessedLetter))
					wordOccurances+=1;
				if((userWord[wordLength]).equals(guessedLetter))
					userWordOccurances+=1;
			}
			if(userWordOccurances!=wordOccurances || (userWordOccurances==0 && wordOccurances==0))
				{
				for(int wordLength=0;wordLength<word.length();wordLength++)
				{
					if(word.substring(wordLength,wordLength+1).equals(guessedLetter))
					{
						userWord[wordLength]=guessedLetter;
						found=0;
						score+=10;
					}
				}
				if(found==0)
					chances-=1;
				else
				{
					System.out.println();
					System.out.println("Oops !!! It was wrong guess");
					score-=5;
					drawHangman(s,chances);
					if(chances==8)
						{
							System.out.println("You have used all your attempts ==> GAME OVER");
							System.out.println("The answer was: "+ word);
						}
					else
					{
						System.out.println("You left with "+(8-chances)+" attempts...");
						System.out.println("Try Again...");
					}
				}
				System.out.print("                      ");
				for(int userWordElements=0;userWordElements<word.length();userWordElements++)
				{
					System.out.print(userWord[userWordElements]+" ");
					newWord+=userWord[userWordElements];
				}
				System.out.println();
				if(newWord.equals(word))
				{
					System.out.println("Congratulation!!! you have done it :-) ");
					break;
				}
				System.out.println();
				}
			else
				{
				System.out.println("'"+guessedLetter+"' is already exist");
				System.out.println("Try for other letters!!!");
				chances-=1;
				}
		}	
		return(score);
	}
	/**
	 * drawHangman() :  The method will called during each wrong guess to draw
	 * hangman body for respective user.
	 * 
	 * @param s[][]  It is a character array to draw hangman.
	 * @chances its the number of wrong attempts while guessing 
	 * letters for a word.
	 * 
	 */
	public static void drawHangman(char s[][], int chances)
	{
		switch(chances)
		{
			case 8:	s[8][7]='*';
			case 7:	s[8][5]='*';				
			case 6: s[5][7]='*';
			case 5: s[5][5]='*';
			case 4: for(int i=4;i<8;i++)
						s[i][6]='*';
			case 3: s[3][6]='O';
			case 2: s[1][6]='*';
			s[2][6]='*';
			case 1: for(int i=1;i<7;i++)
						s[0][i]='*';
		}
		for(int i=0;i<13;i++)
			{
			for(int j=0;j<9;j++)
				System.out.print(s[i][j]);
			System.out.println();
			}
	}
	/**
	 * calculateScore() :  This method sort out the top four player
	 * and displays them.
	 * 
	 * @param score[] its an array which has the scores of all the players.
	 * 
	 */
	public static void calculateScore(int[] score, int n)
	{
		Arrays.sort(score);
		System.out.println("==================================================");
		System.out.println("    Player                                  Score");
		System.out.println("==================================================");
		for(int indexSortedArray=(n-1);indexSortedArray>=0;indexSortedArray--)
			System.out.println("       "+ (n-indexSortedArray)+"                              "+ score[indexSortedArray]);
		System.out.println("==================================================");
	}
	
	}