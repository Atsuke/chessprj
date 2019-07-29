package chessprj;

public class Move {
	public int fromRow, fromColumn, toRow, toColumn;
        public int moveValue;

	public Move() {
	}

	public Move(int fromRow, int fromColumn, int toRow, int toColumn) {
		this.fromRow = fromRow;
		this.fromColumn = fromColumn;
		this.toRow = toRow;
		this.toColumn = toColumn;
	}

        public void setMoveValue(int v){
            moveValue = v;
        }
        
        public int getMoveValue(){
            return moveValue;
        }
        
	@Override
	public String toString() {
		return "Move [fromRow=" + fromRow + ", fromColumn=" + fromColumn + ", toRow=" + toRow + ", toColumn=" + toColumn
				+ "]";
	}
	
	
}
