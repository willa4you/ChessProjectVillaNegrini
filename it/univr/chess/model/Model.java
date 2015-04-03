package it.univr.chess.model;

import it.univr.chess.model.pieces.Piece;
/**
 * Implementata per limitare l'accesso ai metodi di oggetti ChessboardModel.
 * @author Alessandro Villa
 * @author Matteo Negrini
 */
public interface Model {
	/**
	 * Ricevute le coordinate, in prima analisi controlla la fase del gioco:
	 * se si tratta di coordinate di partenza verifica se si riferiscono ad un pezzo
	 * della squadra a cui appartiene il turno di gioco ed il quale pezzo ha mosse
	 * disponibili.
	 * 
	 * @param x La coordinata x comunicata
	 * @param y La coordinata y comunicata
	 */
	public void coordinates(int x, int y);
	
	public void promotion(int piece);
	
	public Piece getPiece(int x, int y);
}
