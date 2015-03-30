package it.univr.chess.model;

import it.univr.chess.model.pieces.Piece;

public interface Model {

	public void coordinates(int x, int y);
	
	public void promotion(int piece);
	
	public Piece getPiece(int x, int y);
}
