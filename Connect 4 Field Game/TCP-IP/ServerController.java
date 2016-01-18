package HW113;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerController  implements Runnable 
{
	public static int nOP=4;
	static public ServerSocket server;
	static private Player p[] = new Player[nOP];
	public Player player;
	static int i=0;
	public static char [][] board;
	public static int active = 0;
	public static String name = "";
	public ServerController(Player player, char[][] board) 
	{
		this.player = player;
		this.board = board;
	}
	
	
	public static void main(String[] args) throws Exception
	{
		
		char [][] board=new char[9][25];
		for(int i=0;i<9;i++)
			for(int j=8;j>=i;j--)
				board[i][j]='o';
		for(int i=0;i<9;i++)
			for(int j=9;j<=16;j++)
				board[i][j]='o';
		for(int i=0;i<9;i++)
			for(int j=17;j<25-i;j++)
				board[i][j]='o';
		
	
		
				
	Thread t[] = new Thread[nOP];
	server = new ServerSocket(1111);

	char piece=' ';
	String boardString="";
	for(int i=0; i<nOP; i++)
	{
		try
		{
		System.out.println("waiting for connections with player "+(i+1));
		Socket playerSocket = server.accept();
		System.out.println("connection estalished! Player "+(i+1)+" is now connected :-)");
		InputStream in = playerSocket.getInputStream();
		OutputStream out = playerSocket.getOutputStream();
		out.write("Enter your Name : ".getBytes());
		int n;
		String name="";
		byte buffer[]= new byte[1024];
		if((n = in.read(buffer)) != -1) 
		{
			
			name = new String(buffer, 0, n);
			System.out.println("Name : " + name);
			if(i==0)
				piece = '@';
			else if(i==1)
				piece = '#';
			else if(i==2)
				piece = '$';
			else if(i==3)
				piece = '*';
			p[i] = new Player(name,piece,playerSocket);
			out.flush();
		}

		t[i] = new Thread(new ServerController(p[i],board));
		
		}
		catch(Exception e)
		{
			
		}
	}
	System.out.println("ALL Players are now in sync");
	
	
	
	for(int i =0;i<nOP;i++)
	{
		t[i].start();
	}
	Thread.currentThread().sleep(1000);
	
	int index=0,n;
	byte[] buffer=new byte[1024];
	Connect4FieldController cc;
	OutputStream out;
	InputStream in;
	int f = 0;
	int i = 0;
	while(true)
	{
		f=f%nOP;
		
		out = p[f].sock.getOutputStream();
		in = p[f].sock.getInputStream();
		out.write(("Player "+p[f].getName()+" !! Its your chance").getBytes());
		
		boardString = charToString(board);
		out.write(boardString.getBytes());
		
		while((n = in.read(buffer)) == -1)
		{}
		
		index = Integer.parseInt(new String(buffer, 0, n));
		cc = new Connect4FieldController(board, index, p[f]);
		i = cc.playTheGame();
		
		boardString = charToString(board);
		out.write(boardString.getBytes());
		
		if(i==2)
		{
			System.out.println("Player "+ p[f].getName()+" Won");
			out.write("Won".getBytes());
			out.write(("Congratulations !!! "+ p[f].getName() +" You won").getBytes());
			break;
		}
		if(i==1)
		{
			System.out.println("GAME DRAW");
			break;
		}
	f++;
	}
	
	out = p[(f+1)%nOP].sock.getOutputStream();
	out.write("Won".getBytes());
	out.write(("Sorry You loose "+"\n"+ p[f].getName()+" Won!!").getBytes());
	
	out = p[(f+2)%nOP].sock.getOutputStream();
	out.write("Won".getBytes());
	out.write(("Sorry You loose "+"\n"+ p[f].getName()+" Won!!").getBytes());
	
	out = p[(f+3)%nOP].sock.getOutputStream();
	out.write("Won".getBytes());
	out.write(("Sorry You loose "+"\n"+ p[f].getName()+" Won!!").getBytes());

	System.exit(0);
	}
	public void run()
	{
		InputStream in = null;
		OutputStream out=null;
		try 
		{
			in = player.sock.getInputStream();
			out= player.sock.getOutputStream();
		} catch (IOException e1) 
		{	
			e1.printStackTrace();
		}
		while(true)
		{
			while(i == 0)
			{		
			}
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
}