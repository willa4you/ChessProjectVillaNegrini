package chess_model.pieces;

import chess_model.Team;

public abstract class CastlingPiece extends Piece {
	protected boolean moved;
	
	public CastlingPiece(Team team) {
		super(team);
		this.moved = false;		
	//il costruttore di un pezzo che partecipa all'arrocco, fissa che inizialmente non è stato mosso
	}

	public boolean getMoved(){
		return this.moved;
	}
	
	public void setMoved(){
		this.moved = true;
	}
}
