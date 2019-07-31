package chessprj;

/**********************************************************************
 * Houses information about the King chess piece
 **********************************************************************/

public class King extends ChessPiece {

    /**********************************************************************
     * Constructor for king piece
     *
     * @param player
     * @param model
     **********************************************************************/
    public King(Player player, ChessModel model) {
        super(player,model);
        setStrategicValue(500);
    }

    /**********************************************************************
     * @return piece type
     **********************************************************************/
    public String type() {
        return "King";
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
        King copy = new King(player(), model);
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

        boolean valid = false;

        //space is empty or occupied by opponent
        if(board[move.toRow][move.toColumn] == null || board[move.toRow][move.toColumn].player()!= player()){

            //up and left one square
            if(move.toRow == move.fromRow -1 && move.toColumn == move.fromColumn -1)
                valid = true;

            //straight up one square
            if(move.toRow == move.fromRow -1 && move.toColumn == move.fromColumn)
                valid = true;

            //up and right one square
            if(move.toRow == move.fromRow -1 && move.toColumn == move.fromColumn +1)
                valid = true;

            //left one square
            if(move.toRow == move.fromRow && move.toColumn == move.fromColumn -1)
                valid = true;

            //right one square
            if(move.toRow == move.fromRow && move.toColumn == move.fromColumn +1)
                valid = true;

            //down and left one square
            if(move.toRow == move.fromRow +1 && move.toColumn == move.fromColumn -1)
                valid = true;

            //straight down one square
            if(move.toRow == move.fromRow +1 && move.toColumn == move.fromColumn)
                valid = true;

            //down and right one square
            if(move.toRow == move.fromRow +1 && move.toColumn == move.fromColumn +1)
                valid = true;

            if(player() == Player.BLACK){
                for(Move check: model.getWhiteAttackMoves())
                    if(move.toRow == check.toRow && move.toColumn == check.toColumn)
                        valid = false;

            }//end if

            if(player() == Player.WHITE){
                for(Move check: model.getBlackAttackMoves())
                    if(move.toRow == check.toRow && move.toColumn == check.toColumn)
                        valid = false;
            }//end if
        }
        return valid;

    }//end isValidMove

}//end Class King