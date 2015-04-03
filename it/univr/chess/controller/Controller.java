package it.univr.chess.controller;
/**
 * Implementata per limitare l'accesso ai metodi di oggetti ChessController.
 * @author Alessandro Villa
 * @author Matteo Negrini
 */
public interface Controller {
	/**
	 * Il metodo riceve una coordinata e la gira al model.
	 * @param coordinates
	 */
	public void onClick(int x, int y);
	/**
	 * Questo metodo e` invocato dalla PromotionWindow quando viene scelto un
	 * pezzo a sostituire il pedone giunto all'ultima traversa. Il pezzo scelto
	 * viene comunicato attraverso un intero secondo la seguente associazione:
	 * 0- regina;
	 * 1- torre;
	 * 2- alfiere;
	 * 3- cavallo. 
	 * @param piece
	 */
	public void promotion(int piece);
	/**
	 * Crea un nuovo oggetto ChessboardModel e lo assegna alla propria variabile di
	 * istanza.
	 */
	public void newMatch();
}
