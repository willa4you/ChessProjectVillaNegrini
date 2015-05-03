package chessprojectvillanegrini.model;

/**
 * Implementata per limitare l'accesso ai metodi di oggetti ChessboardModel.<br>
 * Specifica per gli accessi da parte di oggetti di tipo Controller.
 * 
 * @author Alessandro Villa
 * @author Matteo Negrini
 */
public interface ModelController {
	
	/**
	 * Il metodo riceve le coordinate di una casella ed e` istruito ad eseguire 
	 * le giuste istruzioni in base:<br> 1- alla fase di gioco determinata
	 * dal fatto di essere in attesa di coordinate o di partenza o di arrivo;<br> 
	 * 2 - a controlli eseguiti sulla validita` della casella comunicata.
	 * 
	 * @param x La coordinata x comunicata
	 * @param y La coordinata y comunicata
	 */
	public void coordinates(int x, int y);
	
	/**
	 * Il metodo riceve un intero da 0 a 3 a seguito di una scelta
	 * dell'utente riguardo a quale pezzo sostituire al pedone giunto
	 * in ultima traversa.<br>
	 * L'associazione e` la seguente:<br>
	 * 0- regina;<br>
	 * 1- torre;<br>
	 * 2- alfiere;<br>
	 * 3- cavallo.
	 * 
	 * @param piece 0 == regina, 1 == torre, 2 == alfiere, 3 == cavallo
	 */
	public void promotion(int piece);
	
	/**
	 * Il metodo setta la modalita' di gioco come scelta da menu opzioni.
	 * Il parametro easyMode se settato a true permette di cambiare pezzo
	 * con cui giocare all'interno dello stesso turno.
	 * @param easyMode 	E' il booleano ricevuto in seguito alla pressione
	 * 					del tasto sul menu.
	 */
	public void setMode(boolean easyMode);
}
