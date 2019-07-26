package chessprj;

public class Knight extends ChessPiece {

    private boolean firstMove = true;
    
	public Knight(Player player, ChessModel model) {
		super(player,model);
	}

	public String type() {
		return "Knight";
	}

        public void setFirstMove(boolean first){
            firstMove = first;
            
        }//endsetFirstMove
        
        @Override
        public IChessPiece copy(){
            Knight copy = new Knight(player(), model);
            copy.setFirstMove(firstMove);
            return copy;
        }
        
	public boolean isValidMove(Move move, IChessPiece[][] board){
            boolean valid = false;
            
            //space is empty or occupied by opponent
            if(board[move.toRow][move.toColumn] == null || board[move.toRow][move.toColumn].player()!= player()){
            
                // up two spaces and left or right one
                if(move.toRow == move.fromRow-2)
                    if(move.toColumn == move.fromColumn-1 || move.toColumn == move.fromColumn+1)
                        valid = true;
                
                //up one space and left or right two
                if(move.toRow == move.fromRow-1)
                    if(move.toColumn == move.fromColumn-2 || move.toColumn == move.fromColumn+2)
                        valid = true;
                
                 //down one space and left or right two
                if(move.toRow == move.fromRow+1)
                    if(move.toColumn == move.fromColumn-2 || move.toColumn == move.fromColumn+2)
                        valid = true;
                
                 //down twon spaces and left or right one
                if(move.toRow == move.fromRow+2)
                    if(move.toColumn == move.fromColumn-1 || move.toColumn == move.fromColumn+1)
                        valid = true;
                
                
                
                
                
            }//end check empty or opponent
            
		return valid;
		
	}

}
