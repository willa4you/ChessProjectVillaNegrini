package it.univr.chess.model;

import it.univr.chess.model.pieces.Pieces;
/**
 * Implementata per limitare l'accesso ai metodi di oggetti ChessboardModel.
 * Specifica per gli accessi da parte di oggetti di tipo Piece.
 * @author Alessandro Villa
 * @author Matteo Negrini
 */
public interface ModelPieces {
	/** 
	 * @param x
	 * @param y
	 * @returnr il pezzo presente nelle coordinate x, y
	 * della scacchiera.
	 */
	public Pieces getPiece(int x, int y);
}
