package chessprj;

public class King extends ChessPiece {
    
    private int strategicValue = 500;
    
    
	public King(Player player, ChessModel model) {
		super(player,model);
               
	}

	public String type() {
		return "King";
	}
	
        public void setFirstMove(boolean first){
            firstMove = first;
            
        }//endsetFirstMove
        
        @Override
        public IChessPiece copy(){
            King copy = new King(player(), model);
            copy.setFirstMove(firstMove);
            return copy;
        }
        
	public boolean isValidMove(Move move, IChessPiece[][] board) {
            
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
                   for(Move check: model.blackThreats())
                       if(move.toRow == check.toRow && move.toColumn == check.toColumn)
                           valid = false;
                 
               }//end if
                
                if(player() == Player.WHITE){
                   for(Move check: model.whiteThreats())
                       if(move.toRow == check.toRow && move.toColumn == check.toColumn)
                           valid = false;
                }//end if
            }
		return valid;
              
	}//end isValidMove
        
}//end Class King