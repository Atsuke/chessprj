package chessprj;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

/**********************************************************************
 * Data Structure that represents the chess game itself
 **********************************************************************/

public class ChessModel implements IChessModel {

    /** boards */
    private IChessPiece[][] board;
    private IChessPiece[][] board2;

    /** black and white possible moves */
    private ArrayList<Move> blackMoves = new ArrayList<>();
    private ArrayList<Move> whiteMoves = new ArrayList<>();

    /** black and white attack moves */
    private ArrayList<Move> blackAttackMoves = new ArrayList<>();
    private ArrayList<Move> whiteAttackMoves = new ArrayList<>();

    /** black threats and locations */
    private ArrayList<IChessPiece> blackThreats = new ArrayList<>();
    private ArrayList<Integer> blackThreatRows = new ArrayList<>();
    private ArrayList<Integer> blackThreatCols = new ArrayList<>();

    /** white threats and locations */
    private ArrayList<IChessPiece> whiteThreats = new ArrayList<>();
    private ArrayList<Integer> whiteThreatRows = new ArrayList<>();
    private ArrayList<Integer> whiteThreatCols = new ArrayList<>();

    /** king location */
    private int kingRow, kingCol;

    /** if kings are in check */
    private boolean whiteInCheck, blackInCheck;

    /** stack for undo */
    private Stack<IChessPiece[][]> undoStack;

    /** extras */
    private Player player;
    private String msg;
    private Random rand = new Random();

    /**  */
    private ArrayList<ChessModel> gameOver = new ArrayList<>();

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

        ChessModel temp;

        if(player == Player.WHITE){
            for(Move check: whiteAttackMoves){
                whiteMoves.add(check);
            }//end for

            for(Move check: whiteMoves){
                over = true;
                temp = this.copyModel();
                temp.copy();
                temp.move(check);
                if(!temp.inCheck()){
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
                if(!temp.inCheck()){
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

    /**********************************************************************
     * Calculates best move
     * To be used in advanced AI
     **********************************************************************/
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

    /**********************************************************************
     * Executes a move
     *
     * @param move
     **********************************************************************/
    public void move(Move move) {

        if(board[move.fromRow][move.fromColumn] != null && board[move.fromRow][move.fromColumn].firstMove == true){
            board[move.fromRow][move.fromColumn].setFirstMove(false);
        }
        board[move.toRow][move.toColumn] =  board[move.fromRow][move.fromColumn];
        board[move.fromRow][move.fromColumn] = null;

        // promotes a pawn if there is a pawn to be promoted
        promotePawn();
    }

    /**********************************************************************
     * Copies board
     **********************************************************************/
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

    /**********************************************************************
     * @return current player
     **********************************************************************/
    public Player currentPlayer() {
        return player;
    }

    /**********************************************************************
     * @return number of rows
     **********************************************************************/
    public int numRows() {
        return 8;
    }

    /**********************************************************************
     * @return number of columns
     **********************************************************************/
    public int numColumns() {
        return 8;
    }

    /**********************************************************************
     * Clears the board
     **********************************************************************/
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

    /**********************************************************************
     * Copies board
     **********************************************************************/
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

    /**********************************************************************
     * Alternates player for turn-taking purposes
     **********************************************************************/
    public void setNextPlayer() {
        player = player.next();
    }

    /**********************************************************************
     * Promotes pawns if they reach end of the board opposite the side
     * they started on
     **********************************************************************/
    private void promotePawn(){

        // iterate through top and bottom rows
        // looks for pawns from opposing side
        for(int c = 0; c < 8; ++c){

            // looks at top row
            IChessPiece piece = pieceAt(0, c);

            // checks if there is a piece there, and if it is a white pawn
            if(piece != null && piece.player() == Player.WHITE && piece.type().equals("Pawn")){

                // piece becomes a queen
                board[0][c] = new Queen(Player.WHITE,this);
            }

            // looks at bottom row
            piece = pieceAt(7, c);

            // checks if there is a piece there, and if it is a black pawn
            if(piece != null && piece.player() == Player.BLACK && piece.type().equals("Pawn")){

                // piece becomes a queen
                board[7][c] = new Queen(Player.BLACK,this);
            }
        }
    }

    /**********************************************************************
     * Finds which black pieces are in immediate danger of capture
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
     * Finds which white pieces are in immediate danger of capture
     **********************************************************************/
    private void whiteThreats(){

        // makes sure lists are empty to start
        whiteThreats.clear();
        whiteThreatRows.clear();
        whiteThreatCols.clear();

        // iterates through cells where white can attack
        for(Move move : blackAttackMoves){
            IChessPiece piece = pieceAt(move.toRow, move.toColumn);

            // checks if there are any pieces there
            if(piece == null) continue;

            // makes sure the piece is black
            if(piece.player() == Player.WHITE) {

                whiteThreats.add(piece);
                whiteThreatRows.add(move.toRow);
                whiteThreatCols.add(move.toColumn);
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
         * Checks if black king is in danger
         * If so, moves king
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

        /*******************************************************
         * Checks if any black pieces are in danger
         * If so, moves one of those pieces
         ********************************************************/

        // assesses if any black pieces are in danger
        blackThreats();

        if(!blackThreats.isEmpty()){

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

        /*******************************************************
         * Checks if any white pieces could be captured
         * If so, captures them
         ********************************************************/
        // assesses if any white pieces are in danger
        whiteThreats();

        // checks if any white pieces can be captured
        if(whiteThreats.size() != 0){

            // iterates through all possible black attack moves
            for(Move move : blackAttackMoves){

                IChessPiece piece = pieceAt(move.toRow, move.toColumn);

                // checks if there is a white piece in any place a black piece can attack
                if(piece != null && piece.player() == Player.WHITE){

                    // executes the move to capture the piece
                    move(move);

                    // exits if move is executed to prevent multiple moves
                    return;
                }
            }
        }

        /*******************************************************
         * Randomly moves a black piece if no previous conditions
         * are met
         ********************************************************/

        move(blackMoves.get(rand.nextInt(blackMoves.size())));
    }
}