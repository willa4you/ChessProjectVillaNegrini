package it.univr.chess.model.pieces;

import it.univr.chess.model.Model;
import it.univr.chess.model.Team;

public abstract class CastlingPiece extends Piece {
	protected boolean moved;
	
	public CastlingPiece(Team team, Model chessboard) {
		super(team, chessboard);
		this.moved = false;		
	//il costruttore di un pezzo che partecipa all'arrocco, fissa che inizialmente non � stato mosso
	}

	public boolean getMoved(){
		return this.moved;
	}
	
	public void setMoved(){
		this.moved = true;
	}
}
