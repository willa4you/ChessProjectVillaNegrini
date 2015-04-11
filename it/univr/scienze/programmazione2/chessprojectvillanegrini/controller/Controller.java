package it.univr.scienze.programmazione2.chessprojectvillanegrini.controller;

/**
 * Implementata per limitare l'accesso ai metodi di oggetti ChessController.
 * 
 * @author Alessandro Villa
 * @author Matteo Negrini
 */
public interface Controller {
	
	/**
	 * Il metodo riceve una coordinata e la gira al model.
	 * 
	 * @param x la coordinata x
	 * @param y la coordinata y
	 */
	public void onClick(int x, int y);
	
	/**
	 * Questo metodo e` invocato dalla PromotionWindow quando viene scelto un
	 * pezzo a sostituire il pedone giunto all'ultima traversa. Il pezzo scelto
	 * viene comunicato attraverso un intero secondo la seguente associazione:<br>
	 * 0- regina;<br>
	 * 1- torre;<br>
	 * 2- alfiere;<br>
	 * 3- cavallo. 
	 * 
	 * @param piece il pezzo
	 */
	public void promotion(int piece);
	
	/**
	 * Crea un nuovo oggetto ChessboardModel e lo assegna alla propria variabile di
	 * istanza.
	 */
	public void newMatch();
}
