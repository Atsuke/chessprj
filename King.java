package chessprj;

public class King extends ChessPiece {
    
    
    
    
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
               
                
                if(player() == Player.WHITE){
                  
                   for(Move checkMe: model.blackMoves()){
                       if(move.toRow == checkMe.toRow && move.toColumn == checkMe.toColumn){
                           valid = false;
                        }//end if
                    }//end for
                  
               }//end if 
                
               if(player() == Player.BLACK){
                 
                   for(Move checkMe: model.whiteMoves()){
                       if(model.pieceAt(checkMe.fromRow, checkMe.fromColumn).type().equals("Pawn")){
                           
                       if(move.toRow == checkMe.fromRow-1 && move.toColumn == checkMe.fromColumn-1){
                            valid = false;
                       }
                       
                       if(move.toRow == checkMe.fromRow-1 && move.toColumn == checkMe.fromColumn +1){
                            valid = false;
                       }
                       
                       }//end if
                           else
                       if(move.toRow == checkMe.toRow && move.toColumn == checkMe.toColumn){
                           valid = false;
                           
                        }//end if
                    }//end for
                  
               }//end if
                
                
            }//end if
               
               
               
		return valid;
	}//end isValidMove
}//end Class King
