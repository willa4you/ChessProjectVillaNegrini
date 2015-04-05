package it.univr.chess.view;

/**
 * Implementata per limitare l'accesso ai metodi di oggetti Button.
 * 
 * @author Alessandro Villa
 * @author Matteo Negrini
 */
interface Buttons {

	/**
	 * Metodo invocato da ChessboardView che setta il background
	 * del bottone a giallo, il che indica che il pezzo e` stato
	 * selezionato. Questo accade solo alla casella (bottone) e` associato
	 * un pezzo che ha mosse disponibili (controllo effettuato nel ChessboardModel
	 * e comunicato a ChessboardView).
	 */
	public void selected();
	
	/**
	 * Metodo invocato da ChessboardView che setta il background
	 * del bottone a rosso. L'invocazione avviene se, una volta
	 * selezionato un pezzo da muovere, si cerca di spostarlo
	 * su di una casella non prevista dalle sue mosse disponibili
	 * (controllo effettuato nel ChessboardModel e comunicato a ChessboardView).
	 */
	public void wrong();
	
	/**
	 * Metodo invocato da ChessboardView che setta il background a verde
	 * e i bordi visibili delle caselle (bottoni) dove il pezzo selezionato può
	 * andare. Questo avviene solo se e` abilitato dal menu il suggerimento delle
	 * mosse consentite
	 */
	public void available();
	
	/**
	 * Metodo invocato da ChessboardView che setta il background
	 * del bottone al colore di default (panna o marrone). Questo avviene
	 * quando:
	 * - si selezionano ripetutamente caselle errate in cui muoversi (
	 *   risetta al colore di default la casella errata selezionata in precedenza).
	 * - si effettua una mossa correttamente (risetta al colore di default
	 *   il giallo della selezione, il rosso dell'errore ed eventuali verdi
	 *   di suggerimento).
	 * (controlli effettuati nel ChessboardModel e comunicati a ChessboardView)
	 * Inoltre setta a false la visibilità dei bordi dei bottoni
	 */
	public void highlightOff();
}
