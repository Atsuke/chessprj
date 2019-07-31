package chessprj;

/**********************************************************************
 * Houses information about the Queen chess piece
 **********************************************************************/

public class Queen extends ChessPiece {

    /**********************************************************************
     * Constructor for queen piece
     *
     * @param player
     * @param model
     **********************************************************************/
    public Queen(Player player, ChessModel model) {
        super(player, model);
        setStrategicValue(9);
    }

    /**********************************************************************
     * @return piece type
     **********************************************************************/
    public String type() {
        return "Queen";
    }

    /**********************************************************************
     * set first move
     *
     * @param first
     **********************************************************************/
    public void setFirstMove(boolean first){
        firstMove = first;
    }//end setFirstMove

    /**********************************************************************
     * copies piece
     **********************************************************************/
    @Override
    public IChessPiece copy(){
        Queen copy = new Queen(player(), model);
        copy.setFirstMove(firstMove);
        return copy;
    }

    /**********************************************************************
     * checks if a proposed move is valid
     *
     * @param move
     * @param board
     * @return true if move valid, else false
     **********************************************************************/
    public boolean isValidMove(Move move, IChessPiece[][] board) {

        // makes sure the move is within the confines of the board
        if(!isInBoard(move)) return false;

        Bishop move1 = new Bishop(board[move.fromRow][move.fromColumn].player(),model);
        Rook move2 = new Rook(board[move.fromRow][move.fromColumn].player(),model);
        return (move1.isValidMove(move, board) || move2.isValidMove(move, board));
    }
}