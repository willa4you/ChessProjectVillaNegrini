package chessprojectvillanegrini.model;

import chessprojectvillanegrini.model.pieces.Pieces;

/**
 * Implementata per limitare l'accesso ai metodi di oggetti ChessboardModel.<br>
 * Specifica per gli accessi da parte di oggetti di tipo Piece.
 * 
 * @author Alessandro Villa
 * @author Matteo Negrini
 */
public interface ModelPieces {
	
	/** 
	 * @param x la coordinata x del pezzo.
	 * @param y la coordinata y del pezzo.
	 * @return il pezzo presente nelle coordinate x, y
	 * 		   della scacchiera.
	 */
	public Pieces getPiece(int x, int y);
}
