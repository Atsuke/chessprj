package chessprj;

public abstract class ChessPiece implements IChessPiece {

        private Player owner;
        private int strategicValue;
        protected static ChessModel model;
        boolean firstMove = true;
        
	protected ChessPiece(Player player, ChessModel model) {
		this.owner = player;
                this.model = model;
	}

	public abstract String type();
        

	public Player player() {
		return owner;
	}

        public IChessPiece copy(){
           
            return this;
            
        }//end copy
        
        public void setStrategicValue(int s){
            strategicValue = s;
        }
        public int strategicValue(){
            return this.strategicValue;
        }
        
	public boolean isValidMove(Move move, IChessPiece[][] board) {
		boolean valid = false;

		//  THIS IS A START... More coding needed
		
		if (((move.fromRow == move.toRow) && (move.fromColumn == move.toColumn)) == false)
			return valid;

		return false;
	}
}
