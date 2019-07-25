package chessprj;

public class Queen extends ChessPiece {

    private boolean firstMove = true;
	public Queen(Player player) {
		super(player);

	}

	public String type() {
		return "Queen";
		
	}
	public void setFirstMove(boolean first){
            firstMove = first;
            
        }//endsetFirstMove
        
	public boolean isValidMove(Move move, IChessPiece[][] board) {
		Bishop move1 = new Bishop(board[move.fromRow][move.fromColumn].player());
		Rook move2 = new Rook(board[move.fromRow][move.fromColumn].player());
		return (move1.isValidMove(move, board) || move2.isValidMove(move, board));
	}
}
