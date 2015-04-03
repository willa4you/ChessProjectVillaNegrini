package it.univr.chess.model;

/**
 * Implementata per limitare l'accesso ai metodi di oggetti ChessboardModel.
 * Specifica per gli accessi da parte di oggetti di tipo View.
 * @author Alessandro Villa
 * @author Matteo Negrini
 */
public interface Model {
	/**
	 * Il metodo riceve le coordinate di una casella ed e` istruito ad eseguire 
	 * le giuste istruzioni in base: 1- alla fase di gioco determinata
	 * dal fatto di essere in attesa di coordinate o di partenza o di arrivo; 
	 * 2 - a controlli eseguiti sulla validita` della casella comunicata.
	 * 
	 * @param x La coordinata x comunicata
	 * @param y La coordinata y comunicata
	 */
	public void coordinates(int x, int y);
	
	/**
	 * Il metodo riceve un intero da 0 a 3 a seguito di una scelta
	 * dell'utente riguardo a quale pezzo sostituire al pedone giunto
	 * in ultima traversa.
	 * L'associazione e` la seguente:
	 * 0- regina;
	 * 1- torre;
	 * 2- alfiere;
	 * 3- cavallo. 
	 * @param piece
	 */
	public void promotion(int piece);
	
}
