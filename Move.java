package chessprj;

/**********************************************************************
 * Data Structure for Movement
 **********************************************************************/

public class Move {

    /** to and from row and columns */
    public int fromRow, fromColumn, toRow, toColumn;

    public int moveValue;

    /**********************************************************************
     * Empty alternate constructor
     **********************************************************************/
    public Move() {
    }

    /**********************************************************************
     * Constructor for Move class
     *
     * @param fromRow
     * @param fromColumn
     * @param toRow
     * @param toColumn
     **********************************************************************/
    public Move(int fromRow, int fromColumn, int toRow, int toColumn) {
        this.fromRow = fromRow;
        this.fromColumn = fromColumn;
        this.toRow = toRow;
        this.toColumn = toColumn;
    }

    /**********************************************************************
     * Setter for MoveValue
     *
     * @param v
     **********************************************************************/
    public void setMoveValue(int v){
        moveValue = v;
    }

    /**********************************************************************
     * Getter for MoveValue
     *
     * @return moveValue
     **********************************************************************/
    public int getMoveValue(){
        return moveValue;
    }

    /**********************************************************************
     * toString for Move class
     **********************************************************************/
    @Override
    public String toString() {
        return "Move [fromRow=" + fromRow + ", fromColumn=" + fromColumn + ", toRow=" + toRow + ", toColumn=" + toColumn
                + "]";
    }
}