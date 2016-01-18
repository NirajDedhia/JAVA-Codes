package HW113;

/** 
 * Connect4FieldInterface.java Its an interface which declares methods without body.
 * We have implements this interface using class Connect4Field
 *
 * Version:	v 1.7  
 *     
 * 
 * Revision: 
 *     Initial revision 1.0 09/21/2015 njd sst
 */
public interface Connect4FieldInterface {
	public boolean checkIfPiecedCanBeDroppedIn(int column);
	public void dropPieces(int column, char gamePiece);
	boolean didLastMoveWin();
	public boolean isItaDraw();
	public String toString();
}