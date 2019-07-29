package chessprj;

import java.util.ArrayList;

/**********************************************************************
 * Houses information about the Queen chess piece
 **********************************************************************/

public class Queen extends ChessPiece {

    private int strategicValue = 9;

    public Queen(Player player, ChessModel model) {
        super(player, model);
    }

    public String type() {
        return "Queen";

    }
    public void setFirstMove(boolean first){
        firstMove = first;

    }//end setFirstMove

    @Override
    public IChessPiece copy(){
        Queen copy = new Queen(player(), model);
        copy.setFirstMove(firstMove);
        return copy;
    }

    public boolean isValidMove(Move move, IChessPiece[][] board) {
        Bishop move1 = new Bishop(board[move.fromRow][move.fromColumn].player(),model);
        Rook move2 = new Rook(board[move.fromRow][move.fromColumn].player(),model);
        return (move1.isValidMove(move, board) || move2.isValidMove(move, board));
    }

}