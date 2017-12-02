package HW132;
import java.rmi.Naming;
import java.rmi.server.ServerCloneException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class Server extends UnicastRemoteObject implements ServerIF
{
	private static final long serialVersionID = 1L;
	public ArrayList<Player> player;
	public static char [][] board=new char[9][25];
	public Connect4FieldController cc;
	public static int i = 0;

	protected Server() throws Exception 
	{
		player = new ArrayList<Player>();
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

	public synchronized void registerChatClient(String name,ClientIF chatClient) throws Exception 
	{
		char sym='*';
		if (player.size() == 0)
			sym = '*';
		else if (player.size() == 1)
			sym = '@';
		else if (player.size() == 2)
			sym = '#';
		else if (player.size() == 3)
			sym = '$';
		
		Player p = new Player(name,sym,chatClient);
		this.player.add(p);
	}
	
	public synchronized void BroadcastMessage() throws Exception 
	{
		String won;
		while(true)
		{
			ClientIF c = player.get(i%4).chatClient;
			c.retrieveMessage(charToString(this.board));
			int index = c.getIndex();
			cc = new Connect4FieldController(board, index , player.get(i%4));
			int a = cc.playTheGame();
			if(a==2)
			{
				System.out.println("GAME COMPLETE!!!! "+(player.get(i%4).getName()).toUpperCase() + " WON!!!!!!! :-)");
				c.retrieveMessage("Congratulations "+((player.get(i%4).getName())).toUpperCase()+" YOU WON");
				won = ((player.get(i%4).getName())).toUpperCase();
				i=i+1;
				c = player.get(i%4).chatClient;
				c.retrieveMessage("Sorry!! Game Over "+won+" WON");
				i=i+1;
				c = player.get(i%4).chatClient;
				c.retrieveMessage("Sorry!! Game Over "+won+" WON");
				i=i+1;
				c = player.get(i%4).chatClient;
				c.retrieveMessage("Sorry!! Game Over "+won+" WON");
				break;
			}
			c.retrieveMessage(charToString(this.board));
			i++;
		}
	}
	
	public static String charToString(char[][] board)
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
		return charToString;
	}
	public static void main (String [] args) throws Exception
	{
		Naming.rebind("Server", new Server());
	}
}