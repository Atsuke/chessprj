package chessprj;

/**********************************************************************
 * Houses information about the Bishop chess piece
 **********************************************************************/

public class Bishop extends ChessPiece {

    public Bishop(Player player, ChessModel model) {
        super(player, model);
        setStrategicValue(3);
    }

    public String type() {
        return "Bishop";
    }

    public void setFirstMove(boolean first){
        firstMove = first;

    }//end setFirstMove

    @Override
    public IChessPiece copy(){
        Bishop copy = new Bishop(player(), model);
        copy.setFirstMove(firstMove);
        return copy;
    }

    public boolean isValidMove(Move move, IChessPiece[][] board) {
        boolean valid = false;

        //Move in diagonal lines only
        if(Math.abs(move.fromRow - move.toRow)== Math.abs(move.fromColumn - move.toColumn)){
            int tempCol;
            int tempRow;

            //check that destination is empty or not occupied by same team
            if(board[move.toRow][move.toColumn] == null || board[move.toRow][move.toColumn].player()!= player()){

                //Destination is empty now check the path.

                //Negative Up Diagonal
                if(move.toRow < move.fromRow && move.toColumn < move.fromColumn){
                    tempCol = move.fromColumn-1;
                    tempRow = move.fromRow-1;
                    valid = true;

                    while(tempCol > move.toColumn && tempRow > move.toRow){
                        if(board[tempRow][tempCol] != null){
                            valid = false;
                            break;
                        }//end if
                        else
                            valid = true;
                        tempCol--;
                        tempRow--;
                    }//end While

                }//end negative up diagonal.

                //Positive Up Diagonal
                if(move.toRow < move.fromRow && move.toColumn > move.fromColumn){
                    tempCol = move.fromColumn+1;
                    tempRow = move.fromRow-1;
                    valid = true;
                    while(tempCol < move.toColumn && tempRow > move.toRow){
                        if(board[tempRow][tempCol] != null){
                            valid = false;
                            break;
                        }//end if
                        tempCol++;
                        tempRow--;
                    }//end While

                }//end positive up diagonal.

                //Positive Down Diagonal
                if(move.toRow > move.fromRow && move.toColumn > move.fromColumn){
                    tempCol = move.fromColumn+1;
                    tempRow = move.fromRow+1;
                    valid = true;
                    while(tempCol < move.toColumn && tempRow < move.toRow){
                        if(board[tempRow][tempCol] != null){
                            valid = false;
                            break;
                        }//end if

                        tempCol++;
                        tempRow++;
                    }//end While

                }//end positive down diagonal.

                //Negative Down Diagonal
                if(move.toRow > move.fromRow && move.toColumn < move.fromColumn){
                    tempCol = move.fromColumn-1;
                    tempRow = move.fromRow+1;
                    valid = true;
                    while(tempCol > move.toColumn && tempRow < move.toRow){
                        if(board[tempRow][tempCol] != null){
                            valid = false;
                            break;
                        }//end if

                        tempCol--;
                        tempRow++;
                    }//end While

                }//end Negative Down diagonal.

            }//end empty destination if

        }//end if
        return valid;

    }//end isValidMove

}//end Class Bishop