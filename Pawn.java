package chessprj;

public class Pawn extends ChessPiece {
    

	public Pawn(Player player) {
		super(player, model.getModel());
	}//end Constructor

	public String type() {
		return "Pawn";
	}//end type
        
        public void setFirstMove(boolean first){
            firstMove = first;
            
        }//endsetFirstMove
        
        
	// determines if the move is valid for a pawn piece
	public boolean isValidMove(Move move, IChessPiece[][] board) {
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
                
                //dont overwrite other pieces
                if(board[move.toRow][move.toColumn] != null)
                    valid = false;
                
                //move diagonally to take enemie pieces
                if((move.toRow == move.fromRow-1 && move.toColumn == move.fromColumn -1) || (move.toColumn == move.fromColumn + 1 && move.toRow == move.fromRow -1))
                    if(board[move.toRow][move.toColumn] != null)//is the space empty
                        if(board[move.toRow][move.toColumn].player() != player())//is it the same team?
                            valid = true;
                          
            }//end whitePlayer
            
           
            
            //black ifs
            if(player() == Player.BLACK){
                
                //Check if pawn has moved yet
                if(firstMove){
                    //move two spaces on initial move
                    if(move.toRow == move.fromRow + 2 && move.toColumn == move.fromColumn){
                       valid = true;
                       //first turn over
                    }//end if
                    //Or only move one.  Your call really
                    if(move.toRow == move.fromRow + 1 && move.toColumn == move.fromColumn){
                        valid=true;
                        //no longer first move
                    } // end if   
                        }//end first move if
                
                
                //move in straight lines
                if(move.toRow == move.fromRow + 1 && move.toColumn == move.fromColumn)
                        valid=true;
                
                //Dont overwrite other pieces.
                if(board[move.toRow][move.toColumn] != null)
                    valid = false;
            
                //move diagonally to take enemie pieces
                if((move.toRow == move.fromRow +1 && move.toColumn == move.fromColumn -1) || (move.toColumn == move.fromColumn + 1 && move.toRow == move.fromRow +1))
                    if(board[move.toRow][move.toColumn] != null) //is the space empty?
                        if(board[move.toRow][move.toColumn].player() != player())//is it the same team?
                            valid = true;
                
            }//end blackPlayer
        
		return valid;
                
	}//end isValidMove
        
        
}//end Class Pawn