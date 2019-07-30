package chessprj;

import java.util.ArrayList;
import java.util.Stack;

/**********************************************************************
 * Data Structure that represents the chess game itself
 **********************************************************************/

public class ChessModel implements IChessModel {

    private IChessPiece[][] board;
    private IChessPiece[][] board2;
    private ArrayList<Move> whiteMoves = new ArrayList<>();
    private ArrayList<Move> blackMoves = new ArrayList<>();
    private ArrayList<Move> blackAttackMoves = new ArrayList<>();
    private ArrayList<Move> whiteAttackMoves = new ArrayList<>();
    private ArrayList<IChessPiece> blackThreats = new ArrayList<>();
    private ArrayList<ChessModel> gameOverMan = new ArrayList<>();
    private Stack<IChessPiece[][]> undoStack;
    private Player player;
    private String msg;
    private boolean whiteInCheck, blackInCheck;
    private int kingRow, kingCol;
    private ArrayList<Integer> blackThreatRows = new ArrayList<>();
    private ArrayList<Integer> blackThreatCols = new ArrayList<>();

    /**********************************************************************
     * Constructor for ChessModel
     **********************************************************************/
    public ChessModel() {
        board = new IChessPiece[8][8];
        player = Player.WHITE;
        undoStack = new Stack<IChessPiece[][]>();

        // creates white non-pawn pieces
        board[7][0] = new Rook(Player.WHITE,this);
        board[7][1] = new Knight(Player.WHITE,this);
        board[7][2] = new Bishop(Player.WHITE,this);
        board[7][3] = new Queen(Player.WHITE,this);
        board[7][4] = new King(Player.WHITE,this);
        board[7][5] = new Bishop(Player.WHITE,this);
        board[7][6] = new Knight (Player.WHITE,this);
        board[7][7] = new Rook(Player.WHITE,this);

        // creates white pawns
        for(int i=0; i<8; i++){
            board[6][i] = new Pawn(Player.WHITE,this);
        }//end for

        // creates black non-pawn pieces
        board[0][0] = new Rook(Player.BLACK,this);
        board[0][1] = new Knight(Player.BLACK,this);
        board[0][2] = new Bishop(Player.BLACK,this);
        board[0][3] = new Queen(Player.BLACK,this);
        board[0][4] = new King(Player.BLACK,this);
        board[0][5] = new Bishop(Player.BLACK,this);
        board[0][6] = new Knight (Player.BLACK,this);
        board[0][7] = new Rook(Player.BLACK,this);

        // creates black pawns
        for(int i=0; i<8; i++){
            board[1][i] = new Pawn(Player.BLACK,this);
        }//end for

    }//end chessModel

    /**********************************************************************
     * @return if game is complete or not
     **********************************************************************/
    public boolean isComplete() {
        boolean over = false;

        ChessModel temp = new ChessModel();

        if(player == Player.WHITE){
            for(Move check: whiteAttackMoves){
                whiteMoves.add(check);
            }//end for

            for(Move check: whiteMoves){
                over = true;
                temp = this.copyModel();
                temp.copy();
                temp.move(check);
                if(temp.inCheck() == false){
                    over = false;
                    break;
                }
                else{
                    temp.undo();
                    continue;
                }//end else
            }//end for
            if(over)
                msg = "GAME OVER: Black wins.";
        }//end white if

        if(player == Player.BLACK){
            for(Move check: blackAttackMoves){
                blackMoves.add(check);
            }//end for

            for(Move check: blackMoves){
                over = true;
                temp = this.copyModel();
                temp.copy();
                temp.move(check);
                if(temp.inCheck() == false){
                    over = false;
                    break;
                }
                else{
                    temp.undo();
                    continue;
                }//end else
            }//end for
            if(over)
                msg = "GAME OVER: White wins.";
        }//end black if

        return over;
    }//end isComplete


    public ChessModel getModel(){
        return this;
    }

    public Move calcBlackBestMove(Move m){
        Move best = new Move();
        getAllWhiteMoves();
        getAllBlackMoves();
        calcAttackMoves();
        ChessModel temp = new ChessModel();
        temp = this.copyModel();
        temp.copy();
        for(Move check: blackAttackMoves){
            blackMoves.add(check);
        }//end for

        for(Move check: blackMoves){
            if(temp.pieceAt(check.toRow, check.toColumn) == null){
                check.setMoveValue(0);
            }
            else{
                check.setMoveValue(temp.pieceAt(check.toRow, check.toColumn).strategicValue());
            }
        }

        return best;
    }

    /**********************************************************************
     * Makes sure the move is valid to happen
     *
     * @return if move is valid
     **********************************************************************/
    public boolean isValidMove(Move move) {
        boolean valid = false;

        IChessPiece location = board[move.fromRow][move.fromColumn];

        // check if move being made is valid

        // makes sure the location exists
        if (location != null)
            // makes sure the location is different from our current location
            if (location.isValidMove(move, board))
                valid = true;

        return valid;
    }

    public void move(Move move) {

        if(board[move.fromRow][move.fromColumn] != null && board[move.fromRow][move.fromColumn].firstMove == true){
            board[move.fromRow][move.fromColumn].setFirstMove(false);
        }
        board[move.toRow][move.toColumn] =  board[move.fromRow][move.fromColumn];
        board[move.fromRow][move.fromColumn] = null;
    }

    public ChessModel copyModel(){
        ChessModel tempBoard = new ChessModel();//create new temporary board
        tempBoard.clearBoard();//delete everything on it so we can copy the real board to it

        for(int x = 0; x < numRows(); x++){
            for(int y = 0; y < numColumns(); y++){
                if(pieceAt(x,y)!= null)
                    tempBoard.setPiece(x, y, pieceAt(x,y).copy());//makes exact copy of each piece
            }//end inner for
        }//end outer for
        return tempBoard;
    }//end copyModel


    /**********************************************************************
     * @return if a king is in check
     **********************************************************************/
    public boolean inCheck() {
        boolean inCheck = false;
        blackInCheck = false;
        resetWhite();
        resetBlack();
        getAllBlackMoves();
        getAllWhiteMoves();
        calcAttackMoves();

        for(Move check: whiteAttackMoves){
           if(pieceAt(check.toRow, check.toColumn) != null){
               if(pieceAt(check.toRow,check.toColumn).type().equals("King") && pieceAt(check.toRow,check.toColumn).player() == player.BLACK){
                   msg = "Black King in Check";
                   inCheck = true;
                        blackInCheck = true;
                        kingCol = check.toColumn;
                        kingRow = check.toRow;
               }
           }
        }

        for(Move check: blackAttackMoves){
           if(pieceAt(check.toRow, check.toColumn) != null){
               if(pieceAt(check.toRow,check.toColumn).type().equals("King") && pieceAt(check.toRow,check.toColumn).player() == player.WHITE){
                   msg = "White King in Check";
                   inCheck = true;
                    whiteInCheck = true;
               }
           }
        }
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

    private void clearBoard(){
        for(int x = 0; x < numRows(); x++){
            for(int y = 0; y < numColumns(); y++){
                setPiece(x,y,null);
            }
        }

    }//end clear board

    /**********************************************************************
     * @return chess piece
     **********************************************************************/
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

    /**********************************************************************
     * Undo previous move
     **********************************************************************/
    public void undo(){
        if(!undoStack.isEmpty()){
            board = undoStack.pop();
        }
    }

    /**********************************************************************
     * Calculates all valid moves for white
     **********************************************************************/
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

    /**********************************************************************
     * Calculates all valid moves for black
     **********************************************************************/
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
                    }//end outer for
                }//end if
            }//end inner for
        }//end outer for
    }//end checkAllBlackMoves

    /**********************************************************************
     * Checks all potential attack moves for black and white pieces
     **********************************************************************/
    public void calcAttackMoves(){
        resetWhite();
        resetBlack();
        checkAllWhiteMoves();
        checkAllBlackMoves();

        // first calculates all valid white and black moves
        for(int i = 0; i < numRows(); i++){
            for(int j = 0; j<numColumns(); j++){
                if(pieceAt(i,j) != null && pieceAt(i,j).player() == Player.BLACK){
                    for(int r = 0; r < numRows(); r++){
                        for(int c = 0; c < numColumns(); c++){
                            Move check = new Move(i,j,r,c);
                            if(pieceAt(check.fromRow,check.fromColumn).type().equals("Pawn")){

                                if(check.toRow == check.fromRow +1 && check.toColumn == check.fromColumn +1){

                                    blackAttackMoves.add(check);
                                }//watch for index out of bounds

                                if(check.toRow == check.fromRow +1 && check.toColumn == check.fromColumn -1){

                                    blackAttackMoves.add(check);
                                }//watch for index out of bounds

                            }//is it a pawn?
                            else

                            if(isValidMove(check)){
                                blackAttackMoves.add(check);
                            }//end if
                        }//end inner inner for
                    }//end outer for
                }//end black if
            }//end inner for
        }//end outer for
        for(int i = 0; i < numRows(); i++){
            for(int j = 0; j<numColumns(); j++){
                if(pieceAt(i,j) != null && pieceAt(i,j).player() == Player.WHITE){
                    for(int r=0; r<numRows();r++){
                        for(int c=0; c<numColumns(); c++){
                            Move check = new Move(i,j,r,c);
                            if(pieceAt(i,j).type().equals("Pawn")){

                                if(check.toRow == check.fromRow -1 && check.toColumn == check.fromColumn +1){

                                    whiteAttackMoves.add(check);
                                }//watch for index out of bounds

                                if(check.toRow == check.fromRow -1 && check.toColumn == check.fromColumn -1){

                                    whiteAttackMoves.add(check);
                                }//watch for index out of bounds

                            }//is it a pawn?

                            else

                            if(isValidMove(check)){
                                whiteAttackMoves.add(check);

                            }//end if
                        }//end inner inner for
                    }//end outer for
                }//end white if
            }
        }
    }//end calcAttackMoves

    /**********************************************************************
     * Getter for amount of possible white moves
     *
     * @return white moves
     **********************************************************************/
    public int getAllWhiteMoves(){
        return whiteMoves.size();
    }

    /**********************************************************************
     * Getter for amount of possible black moves
     *
     * @return black moves
     **********************************************************************/
    public int getAllBlackMoves(){
        return blackMoves.size();
    }

    /**********************************************************************
     * Getter for current message
     *
     * @return msg
     **********************************************************************/
    public String getMessage(){
        return msg;
    }//end getMessage

    /**********************************************************************
     * Getter for all possible white moves
     *
     * @return white moves
     **********************************************************************/
    public ArrayList<Move> whiteMoves(){
        return whiteMoves;
    }

    /**********************************************************************
     * Getter for all possible black moves
     *
     * @return black moves
     **********************************************************************/
    public ArrayList<Move> blackMoves(){
        return blackMoves;
    }

    /**********************************************************************
     * Getter for all white threats
     *
     * @return white threats
     **********************************************************************/
    public ArrayList<Move> getBlackAttackMoves(){
        return blackAttackMoves;
    }

    /**********************************************************************
     * Getter for all black threats
     *
     * @return black threats
     **********************************************************************/
    public ArrayList<Move> getWhiteAttackMoves(){
        return whiteAttackMoves;
    }

    /**********************************************************************
     * Resets white moves and threats
     **********************************************************************/
    public void resetWhite(){
        whiteMoves.clear();
        blackAttackMoves.clear();
    }

    /**********************************************************************
     * Resets black moves and threats
     **********************************************************************/
    public void resetBlack(){
        blackMoves.clear();
        whiteAttackMoves.clear();
    }

    /**********************************************************************
     * Sets piece to parameter location
     *
     * @param row
     * @param column
     * @param piece
     **********************************************************************/
    public void setPiece(int row, int column, IChessPiece piece) {
        board[row][column] = piece;
    }

    // for turn-taking
    public void setNextPlayer() {
        player = player.next();
    }

    /**********************************************************************
     * Promotes pawns if they reach end of the board opposite the side
     * they started on
     **********************************************************************/
    public void promotePawn(){
        // may need pawn parameter??
        // come back to this
    }


    /**********************************************************************
     * Finds which black pieces are in immediate danger of capture
     *
     * @return ArrayList of black pieces in danger
     **********************************************************************/
    private void blackThreats(){

        // makes sure lists are empty to start
        blackThreats.clear();
        blackThreatRows.clear();
        blackThreatCols.clear();

        // iterates through cells where white can attack
        for(Move move : whiteAttackMoves){
            IChessPiece piece = pieceAt(move.toRow, move.toColumn);

            // checks if there are any pieces there
            if(piece == null) continue;

            // makes sure the piece is black
            if(piece.player() == Player.BLACK) {

                blackThreats.add(piece);

                blackThreatRows.add(move.toRow);
                blackThreatCols.add(move.toColumn);
            }
        }
    }


    /**********************************************************************
     * An AI method for the black pieces
     * Plays against human user who is using the white pieces
     *
     * Protects its king, tries to keep itself out of check, tries
     * to keep it's pieces from being captures, attempts to put
     * white player into check
     **********************************************************************/
    public void AI() {

        /*******************************************************
         * Write a simple AI set of rules in the following order.
         * a. Check to see if you are in check.
         * 		i. If so, get out of check by moving the king or
         * 		   placing a piece to block the check
         ********************************************************/

        // checks if black king is in check
        if(blackInCheck){

            // iterates through rows around king
            for(int r = kingRow - 1; r < kingRow + 2; ++r){

                // iterates through columns around king
                for(int c = kingCol - 1; c < kingCol + 2; ++c){

                    // creates a temporary move from the original king spot, to r,c
                    Move temp = new Move(kingRow, kingCol, r, c);

                    // executes move if it is valid
                    if(isValidMove(temp)) {
                        this.move(temp);

                        // exits if move is executed to prevent multiple moves
                        return;
                    }
                }
            }
        }

//        /*******************************************************
//         * b. Attempt to put opponent into check (or checkmate).
//         * 		i. Attempt to put opponent into check without
//         * 	       losing your piece
//         *		ii. Perhaps you have won the game.
//         ********************************************************/
//
//        // if white king is not in check
//        if(!whiteInCheck){
//
//            // (attempt to put that white king into check)
//
//        }
//
        /*******************************************************
         * c. Determine if any of your pieces are in danger,
         *		i. Move them if you can.
         *		ii. Attempt to protect that piece.
         ********************************************************/

        // assesses if any black pieces are in danger
        blackThreats();

        if(!blackThreats.isEmpty()){

            // gets whatever threatened piece is first in list
           IChessPiece piece = blackThreats.get(0);

            // finding location of the threatened piece
            int pieceRow = blackThreatRows.get(0);
            int pieceCol = blackThreatCols.get(0);

            // NOW need to attempt to move that piece...

            // an array list of potential moves
            ArrayList<Move> potentialMoves = new ArrayList<>();

            // iterate through all possible black moves
            for(Move move : blackMoves){

                // finds all potential moves for the piece we are at
                if(move.fromRow == pieceRow && move.fromColumn == pieceCol){

                    // adds all moves for piece we're at to potential moves ArrayList
                    potentialMoves.add(move);
                }
            }

            // now for all potential moves we need to see if they are valid
            for(Move move : potentialMoves){
                if(isValidMove(move)){

                    // executes the move
                    this.move(move);

                    // exits method if moves is made to prevent multiple moves
                    return;

                    // this doesn't account for if this move is actually going to keep it out of
                    // danger or not
                }
            }
        }
//
//        /*******************************************************
//         * d. Move a piece (pawns first) forward toward opponent
//         *    king
//         *		i. check to see if that piece is in danger of
//         *		   being removed, if so, move a different piece.
//         ********************************************************/
//
//        whiteAttackMoves();

    }

}