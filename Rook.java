package chessprj;

/**********************************************************************
 * Houses information about the Rook chess piece
 **********************************************************************/

public class Rook extends ChessPiece {

    /**********************************************************************
     * Constructor for rook piece
     *
     * @param player
     * @param model
     **********************************************************************/
    public Rook(Player player, ChessModel model) {
        super(player, model);
        setStrategicValue(5);
    }

    /**********************************************************************
     * @return piece type
     **********************************************************************/
    public String type() {
        return "Rook";
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
        Rook copy = new Rook(player(), model);
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
        int tempRow;
        int tempCol;

        //Horizontal moves
        if(move.toRow == move.fromRow || move.toColumn == move.fromColumn){
            //empty square or opponent
            if(board[move.toRow][move.toColumn] == null || board[move.toRow][move.toColumn].player()!= player()){
                valid = true;
                //Square is a "available"  can you get there?
                //to the left
                if(move.toColumn < move.fromColumn){
                    for(tempCol = move.fromColumn-1; tempCol > move.toColumn; tempCol--){
                        if(board[move.fromRow][tempCol] != null)
                            valid = false;
                    }//end for
                }//end to the Left

                //to the right
                if(move.toColumn > move.fromColumn){
                    for(tempCol = move.fromColumn +1; tempCol < move.toColumn; tempCol++){
                        if(board[move.fromRow][tempCol] != null)
                            valid = false;
                    }//end for
                }//end to the right

                //up
                if(move.toRow < move.fromRow){
                    for(tempRow = move.fromRow-1; tempRow > move.toRow; tempRow--){
                        if(board[tempRow][move.fromColumn] != null)
                            valid = false;
                    }//end for
                }//Up

                //Down
                if(move.toRow > move.fromRow){
                    for(tempRow = move.fromRow+1; tempRow < move.toRow; tempRow++){
                        if(board[tempRow][move.fromColumn] != null)
                            valid = false;
                    }//end for
                }//Down
            }//end check empty or opponent
        }//end Horizontal moves

        return valid;

    }//end isValidMove
}//end Class Rook