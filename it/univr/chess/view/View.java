package it.univr.chess.view;

import java.util.ArrayList;

/**
 * Implementata per limitare l'accesso ai metodi di oggetti ChessboardView.
 * 
 * @author Alessandro Villa
 * @author Matteo Negrini
 */
public interface View {

	/**
	 * Questo metodo assegna l'icona della casella (bottone) di
	 * coordinate sx (start_x), sy (start_y) alla casella (bottone)
	 * di coordinate tx (target_x), ty (target_y).<br>
	 * Di fatto questo metodo rende possibile il movimento dei pezzi.
	 * 
	 * @param sx la coordinata x della casella (bottone) di partenza.
	 * @param sy la coordinata y della casella (bottone) di partenza.
	 * @param tx la coordinata x della casella (bottone) di arrivo.
	 * @param ty la coordinata y della casella (bottone) di arrivo.
	 */
	public void move(int sx, int sy, int tx, int ty);
	
	/**
	 * Questo metodo setta a null l'icona della casella (bottone)
	 * in coordinate sx (start_x), sy (start_y).
	 * 
	 * @param sx la coordinata x della casella (bottone) di partenza.
	 * @param sy la coordinata y della casella (bottone) di partenza.
	 */
	public void move(int sx, int sy);
	
	/**
	 * Questo metodo invoca il metodo selected() sulla casella (bottone)
	 * di coordinate x, y e, se la variabile di istanza suggestions e`
	 * settata a true, invoca il metodo available sulle caselle (bottoni)
	 * su cui il pezzo selezionato puo` andare.
	 * 
	 * @param x la coordinata x della casella (bottone) selezionata.
	 * @param y la coordinata y della casella (bottone) selezionata.
	 * @param availableMoves l'ArrayList di mosse consentite dal pezzo
	 * 		  in coordinate x, y.
	 * @see Button#selected()
	 * @see Button#available()
	 */
	public void selected(int x, int y, ArrayList<Integer> availableMoves);
	
	/**
	 * Metodo invocato a mossa effettuata per cancellare tutti
	 * gli highlight dalla scacchiera.
	 */
	public void moved();
	
	/**
	 * Questo metodo riceve le coordinate x, y della casella (bottone)
	 * selezionata e sulla quale non e` previsto dalle mosse consentite
	 * che il pezzo vada.
	 * Di quella casella (bottone) viene chiamato il metodo wrong() che
	 * setta il suo background a rosso.<br>
	 * Inoltre viene eliminato il rosso su eventuali caselle (bottoni)
	 * selezionati in precedenza.
	 * 
	 * @param x la coordinata x della casella (bottone) selezionata.
	 * @param y la coordinata y della casella (bottone) selezionata.
	 * @see Button#wrong()
	 * @see Button#highlightOff()
	 */
	public void wrongMove(int x, int y);
	
	/**
	 * Metodo invocato quando, dopo aver selezionato la casella (bottone)
	 * di partenza, riseleziono la stessa casella (bottone).
	 * L'unico effetto è quello di togliere il rosso su un'eventuale 
	 * casella (bottone) selezionato in precedenza.
	 * 
	 * @param x la coordinata x della casella (bottone) selezionata.
	 * @param y la coordinata y della casella (bottone) selezionata.
	 * @see Button#highlightOff()
	 */
	public void selfSelect(int x, int y);
	
	/**
	 * Metodo che ha come unico scopo quello di creare una
	 * nuova istanza di PromotionWindow e settarla visibile.
	 * 
	 * @param team1 true == team1, team2 altrimenti.
	 */
	public void promotion(boolean team1);
	
	/**
	 * Questo metodo riceve le coordinate del pedone da sostituire
	 * e setta l'icona della casella (bottone) in quelle coordinate
	 * a seconda del valore dell'intero piece:<br>
	 * 0- regina;<br>
	 * 1- torre;<br>
	 * 2- alfiere;<br>
	 * 3- cavallo.
	 * 
	 * @param piece un intero che indica quale pezzo si e` selezionato
	 * 		  dalla PromotionWindow.
	 * @param x la coordinata x del pedone da sostituire.
	 * @param y la coordinata y del pedone da sostituire.
	 * @see it.univr.chess.model.ChessboardModel#promotion(int piece)
	 */
	public void promotion(int piece, int x, int y);
	
	/**
	 * Metodo che ha come unico scopo quello di far apparire un
	 * messageDialog di errore se si selezionano caselle (bottoni)
	 * della schacchiera invece di quelle della PromotionWindow.
	 */
	public void noCoordinatesThanks();
	
	/**
	 * Metodo invocato solo in caso di scacco matto e che ha come
	 * unico scopo quello di far apparire un messageDialog che dice
	 * chi e` il vincitore e chiede all'utente se desidera incominciare
	 * una nuova partita.
	 * 
	 * @param team1 true == team1, team2 altrimenti.
	 */
	public void mate(boolean team1);
	
	/**
	 * Il metodo riceve un intero che indica il motivo della patta:<br>
	 * 0- stallo;<br>
	 * 1- posizione morta;<br>
	 * 2- regola delle 50 mosse<br>
	 * e fa apparire un messageDialog che dice il motivo della patta
	 * e chiede all'utente se desidera incominciare una nuova partita.
	 * 
	 * @param draw intero che indica il motivo della patta.
	 */
	public void draw(int draw);
	
	/**
	 * Metodo che invia un messaggio alla MainWindow in base ai valori
	 * degli argomenti. La MainWindow provvedera` poi a stampare a video il messaggio
	 * 
	 * @param team1 true == team1, team2 altrimenti.
	 * @param check true == scacco, niente altrimenti.
	 */
	public void sendMessage(boolean team1, boolean check);
	
	/**
	 * Questo metodo dice al controller di iniziare una nuova partita
	 * 
	 * @see it.univr.chess.controller.ChessController#newMatch()
	 */
	public void newMatch();
	
	/**
	 * Questo metodo viene invocato quando si seleziona l'opzione
	 * "Abilita/Disabilita suggerimenti" dal menu della MainWindow.
	 * L'effetto della chiamata è quello di settare la variabile di
	 * istanza suggestions a true se prima era false, o a false se
	 * prima era true. Se la variabile e` diventata true dopo essere
	 * stata settata, allora il metodo verifica se la variabile di istanza
	 * availableMoves e` diversa da null e in caso affermativo chiama il
	 * metodo available() sulle caselle (bottoni) in cui il pezzo
	 * selezionato puo` andare; altrimenti toglie l'highlight da tutte le 
	 * caselle (bottoni) esclusa la casella selezionata su cui si trova
	 * il pezzo da muovere.
	 * 
	 * @see Button#available()
	 * @see Button#highlightOff()
	 */
	public void setSuggestions();
}
