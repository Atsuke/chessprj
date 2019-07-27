package chessprj;
import java.util.ArrayList;
import java.util.Stack;

public class ChessModel implements IChessModel {	 
    private IChessPiece[][] board;
    private IChessPiece[][] board2;
    private ArrayList<Move> whiteMoves = new ArrayList<Move>();
    private ArrayList<Move> blackMoves = new ArrayList<Move>();
    private ArrayList<Move> whiteThreats = new ArrayList<Move>();
    private ArrayList<Move> blackThreats = new ArrayList<Move>();
    private Player player;
    private Stack<IChessPiece[][]> undoStack;
    private String msg;
        

	// declare other instance variables as needed

	public ChessModel() {
                board = new IChessPiece[8][8];
                player = Player.WHITE;
                undoStack = new Stack<IChessPiece[][]>();
                

        board[7][0] = new Rook(Player.WHITE,this);
        board[7][1] = new Knight(Player.WHITE,this);
        board[7][2] = new Bishop(Player.WHITE,this);
        board[7][3] = new Queen(Player.WHITE,this);
        board[7][4] = new King(Player.WHITE,this);
        board[7][5] = new Bishop(Player.WHITE,this);
        board[7][6] = new Knight (Player.WHITE,this);
        board[7][7] = new Rook(Player.WHITE,this);
        for(int i=0; i<8; i++){
            board[6][i] = new Pawn(Player.WHITE,this);
        }//end for
        
        board[0][0] = new Rook(Player.BLACK,this);
        board[0][1] = new Knight(Player.BLACK,this);
        board[0][2] = new Bishop(Player.BLACK,this);
        board[0][3] = new Queen(Player.BLACK,this);
        board[0][4] = new King(Player.BLACK,this);
        board[0][5] = new Bishop(Player.BLACK,this);
        board[0][6] = new Knight (Player.BLACK,this);
        board[0][7] = new Rook(Player.BLACK,this);
        for(int i=0; i<8; i++){
            board[1][i] = new Pawn(Player.BLACK,this);
        }//end for
	}//end chessModel

	public boolean isComplete() {
		boolean valid = false;
		return valid;
	}


        public ChessModel getModel(){
            return this;
        }
	public boolean isValidMove(Move move) {
		boolean valid = false;
                
                
		if (board[move.fromRow][move.fromColumn] != null)
			if (board[move.fromRow][move.fromColumn].isValidMove(move, board) == true)
                return true;
                
                
		return valid;
	}

	public void move(Move move) {
           
            pieceAt(move.fromRow,move.fromColumn).setFirstMove(false);
		board[move.toRow][move.toColumn] =  board[move.fromRow][move.fromColumn];
		board[move.fromRow][move.fromColumn] = null;
                
	}

	public boolean inCheck() {
		boolean inCheck = false;
                    threatChecks();
                for(int r=0; r<numRows(); r++)
                    for(int c = 0; c<numColumns(); c++)
                    if(pieceAt(r,c) != null){
                        if(pieceAt(r,c).type().equals("King")){
                            if(pieceAt(r,c).player()==Player.BLACK){
                              for(Move check: blackThreats)
                                  if(check.toRow == r && check.toColumn == c){
                                      msg = "Black King in Check";
                                      inCheck = true;
                                  }//end row/column check
                                      
                            }//end black player
                            else if(pieceAt(r,c).player()==Player.WHITE){
                              for(Move check: whiteThreats)
                                  if(check.toRow == r && check.toColumn == c){
                                      msg = "White King in Check";
                                      inCheck = true;
                                  }//end row/column check
                                      
                            }//end white player
                        }//end king if
                    }//end null if
		return inCheck;
	}//end inCheck

        
	public Player currentPlayer() {
		return player;
	}

	public int numRows() {
		return 8;
	}

	public int numColumns() {
		return 8;
	}

	public IChessPiece pieceAt(int row, int column) {		
		return board[row][column];
	}
        
        public void copy(){
            board2 = new IChessPiece[8][8];
            for(int x = 0; x < numRows(); x++){
                for(int y = 0; y < numColumns(); y++){
                    if(pieceAt(x,y)!= null)
                    board2[x][y] = pieceAt(x,y).copy();
                }//end inner for
            
            }//end outer for
            
             undoStack.push(board2);
            
        }//end copy
        
        public void undo(){
            if(!undoStack.isEmpty()){
                board = undoStack.pop();
            }
        }
        
        public void checkAllWhiteMoves(){
            for(int i=0; i< numRows(); i++)
                for(int j=0; j<numColumns();j++){
                    if(pieceAt(i,j)!=null && pieceAt(i,j).player()==Player.WHITE){
                        for(int r=0; r<numRows();r++)
                            for(int c=0; c<numColumns(); c++){
                                Move check = new Move(i,j,r,c);
                               if(isValidMove(check)){
                                   whiteMoves.add(check);
                               }//end if
                            }//end inner inner for
                    }//end if 
                }//end inner for
        }//end checkAllWhiteMoves
        
        public void checkAllBlackMoves(){
            for(int i=0; i< numRows(); i++){
                for(int j=0; j<numColumns();j++){
                    if(pieceAt(i,j) != null && pieceAt(i,j).player() == Player.BLACK){
                        for(int r=0; r<numRows();r++){
                            for(int c=0; c<numColumns(); c++){
                                Move check = new Move(i,j,r,c);
                               if(isValidMove(check)){
                                   blackMoves.add(check);
                               }//end if
                            }//end inner inner for
                    }//end outerfor
                    }//end if 
                }//end inner for
            }//end outer for
        }//end checkAllBlackMoves
        
        public void threatChecks(){
        checkAllBlackMoves();
        checkAllWhiteMoves();
        for(Move check: whiteMoves){
            
            if(pieceAt(check.fromRow, check.fromColumn) != null && pieceAt(check.fromRow, check.fromColumn).type().equals("Pawn")){
                 Move temp = new Move(check.fromRow, check.fromColumn, check.fromRow -1, check.fromColumn-1);
                 blackThreats.add(temp);
                  Move temp2 = new Move(check.fromRow, check.fromColumn, check.fromRow -1, check.fromColumn+1);
                 blackThreats.add(temp2);
               
            }//end if
         else if(isValidMove(check)){
            blackThreats.add(check);
            }//end if
        }//end for each
                
        for(Move check: blackMoves){
            
            if(pieceAt(check.fromRow, check.fromColumn) != null && pieceAt(check.fromRow, check.fromColumn).type().equals("Pawn")){
                 Move temp = new Move(check.fromRow, check.fromColumn, check.fromRow +1, check.fromColumn-1);
                 whiteThreats.add(temp);
                  Move temp2 = new Move(check.fromRow, check.fromColumn, check.fromRow +1, check.fromColumn+1);
                 whiteThreats.add(temp2);
               
            }//end if
         else if(isValidMove(check)){
            whiteThreats.add(check);
            }//end if
        }//end for each
       
}//end threatChecks
                
        
	public void setNextPlayer() {
		player = player.next();
	}

        public int getAllBlackMoves(){
        
            return blackMoves.size();
        }
        
        public String getMessage(){
            return msg;
        }//end getMessage
        
         public int getAllWhiteMoves(){
        
            return whiteMoves.size();
        }
         
         public ArrayList<Move> whiteMoves(){
         
             return whiteMoves;
         }
         
          public ArrayList<Move> blackMoves(){
         
             return blackMoves;
         }
          
          public ArrayList<Move> blackThreats(){
         
             return blackThreats;
         }
          
          public ArrayList<Move> whiteThreats(){
         
             return whiteThreats;
         }
          
          public void resetWhite(){
              whiteMoves.clear();
              whiteThreats.clear();
          }
          
          
          public void resetBlack(){
              blackMoves.clear();
              blackThreats.clear();
          }
          
	public void setPiece(int row, int column, IChessPiece piece) {
		board[row][column] = piece;
	}

	public void AI() {
		/*
		 * Write a simple AI set of rules in the following order. 
		 * a. Check to see if you are in check.
		 * 		i. If so, get out of check by moving the king or placing a piece to block the check 
		 * 
		 * b. Attempt to put opponent into check (or checkmate). 
		 * 		i. Attempt to put opponent into check without losing your piece
		 *		ii. Perhaps you have won the game. 
		 *
		 *c. Determine if any of your pieces are in danger, 
		 *		i. Move them if you can. 
		 *		ii. Attempt to protect that piece. 
		 *
		 *d. Move a piece (pawns first) forward toward opponent king 
		 *		i. check to see if that piece is in danger of being removed, if so, move a different piece.
		 */

		}
}
