package chessprj;

/**********************************************************************
 * Houses information about the Pawn chess piece
 **********************************************************************/

public class Pawn extends ChessPiece {

    /**********************************************************************
     * Constructor for pawn piece
     *
     * @param player
     * @param model
     **********************************************************************/
    public Pawn(Player player, ChessModel model) {

        super(player, model);
        setStrategicValue(1);

    }//end Constructor

    /**********************************************************************
     * @return piece type
     **********************************************************************/
    public String type() {
        return "Pawn";
    }//end type

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
        Pawn copy = new Pawn(player(), model);
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

        //white ifs
        if(player() == Player.WHITE){

            //Check if pawn has not yet moved
            if(firstMove){
                //move two spaces on initial move
                if(move.toRow == move.fromRow - 2 && move.toColumn == move.fromColumn){
                    valid = true;


                }//end if

                if(move.toRow == move.fromRow -1 && move.toColumn == move.fromColumn){
                    valid=true;

                }//end if
            }//end first move if

            //move in straight lines
            if(move.toRow == move.fromRow -1 && move.toColumn == move.fromColumn)
                valid=true;

            //don't overwrite other pieces
            if(board[move.toRow][move.toColumn] != null)
                valid = false;

            //move diagonally to take enemy pieces
            if((move.toRow == move.fromRow-1 && move.toColumn == move.fromColumn -1) || (move.toColumn == move.fromColumn + 1 && move.toRow == move.fromRow -1))
                if(board[move.toRow][move.toColumn] != null)//is the space empty
                    if(board[move.toRow][move.toColumn].player() != player())//is it the same team?
                        valid = true;

        }//end whitePlayer



        //black ifs
        if(player() == Player.BLACK){

            //check if pawn has moved yet
            if(firstMove){
                //move two spaces on initial move
                if(move.toRow == move.fromRow + 2 && move.toColumn == move.fromColumn){
                    valid = true;
                    //first turn over
                }// end if
                // or only move one.  Your call really
                if(move.toRow == move.fromRow + 1 && move.toColumn == move.fromColumn){
                    valid=true;
                    //no longer first move
                } //end if
            }//end first move if


            //move in straight lines
            if(move.toRow == move.fromRow + 1 && move.toColumn == move.fromColumn)
                valid=true;

            //don't overwrite other pieces.
            if(board[move.toRow][move.toColumn] != null)
                valid = false;

            //move diagonally to take enemy pieces
            if((move.toRow == move.fromRow +1 && move.toColumn == move.fromColumn -1) || (move.toColumn == move.fromColumn + 1 && move.toRow == move.fromRow +1))
                if(board[move.toRow][move.toColumn] != null) //is the space empty?
                    if(board[move.toRow][move.toColumn].player() != player())//is it the same team?
                        valid = true;

        }//end blackPlayer

        return valid;

    }//end isValidMove

}//end Class Pawn