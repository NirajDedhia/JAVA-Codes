

/** 
 * Connect4FieldInterface.java Its an interface which declares methods without body.
 * We have implements this interface using class Player
 *
 * Version:	v 1.7  
 *     
 * 
 * Revision: 
 *     Initial revision 1.0 11/16/2015 njd sst
 */public interface PlayerInterface {

// this is how your constructor has to be
// Player(Connect4FieldInterface theField, String name, char gamePiece)
	
	public char getGamePiece();
	public String getName();
	public int  nextMove();
}