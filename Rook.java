package chessprj;

public class Rook extends ChessPiece {

    private boolean firstMove= true;
    
	public Rook(Player player) {
		
		super(player);
		
	}

	public String type() {
		
		return "Rook";
		
	}
	
        public void setFirstMove(boolean first){
            firstMove = first;
            
        }//endsetFirstMove
        
	/**************************************************************************
         *isValidMove
         * Determines if move is valid for rook piece. up/down left/right
         * @param Move
         * @param IChessPiece[][]
         **************************************************************************/
	public boolean isValidMove(Move move, IChessPiece[][] board) {
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
