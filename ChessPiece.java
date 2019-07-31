package chessprj;

/**********************************************************************
 * Data Structure for chess pieces
 **********************************************************************/
public abstract class ChessPiece implements IChessPiece {

    /** instance variables */
    private Player owner;
    private int strategicValue;
    protected static ChessModel model;
    boolean firstMove = true;

    /**********************************************************************
     * Constructor for ChessPiece
     *
     * @param player
     * @param model
     **********************************************************************/
    protected ChessPiece(Player player, ChessModel model) {
        this.owner = player;
        this.model = model;
    }

    /**********************************************************************
     * @return current player
     **********************************************************************/
    public Player player() {
        return owner;
    }

    /**********************************************************************
     * Getter for strategic value
     *
     * @return IChessPiece
     **********************************************************************/
    public IChessPiece copy(){
        return this;
    }//end copy

    /**********************************************************************
     * Setter for strategic value
     *
     * @param s
     **********************************************************************/
    public void setStrategicValue(int s) {
        strategicValue = s;
    }

    /**********************************************************************
     * Getter for strategic value
     *
     * @return strategic value
     **********************************************************************/
    public int strategicValue(){
        return this.strategicValue;
    }

    /**********************************************************************
     * Checks if move stays in confines of board
     *
     * @return true if in board, else false
     **********************************************************************/
    protected boolean isInBoard(Move move){

        if(move.toRow < 0 || move.toRow > 7 || move.toColumn < 0 || move.toColumn > 7) return false;

        return true;
    }

    // abstracted due to the uniqueness of this method for each piece
    public abstract String type();

    // abstracted due to the uniqueness of this method for each piece
    public abstract boolean isValidMove(Move move, IChessPiece[][] board);

}