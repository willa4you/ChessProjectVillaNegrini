package it.univr.scienze.programmazione2.chessprojectvillanegrini.model.pieces;

import it.univr.scienze.programmazione2.chessprojectvillanegrini.model.Team;

/**
 * Implementata per limitare l'accesso ai metodi di oggetti Piece e sottoclassi.
 * Tutti gli oggetti che rappresentano un pezzo degli scacchi, i quali estendono
 * la classe padre Piece, ereditano da essa l'implementazione di quest'interfaccia.
 * 
 * @author Alessandro Villa
 * @author Matteo Negrini
 */
public interface Pieces {
	
	/**
	 * Il compito di definire una variabile d'istanza che gestisca l'appartenenza
	 * di un pezzo ad una squadra e` delelgato alla classe Piece, ma gia` l'interfaccia
	 * lo suggerisce attraverso questo metodo obbligatorio che indica si debba attraverso
	 * esso restituire la squadra (di tipo enumerativo Team) del pezzo.
	 * 
	 * @return la squadra del pezzo attraverso un valore di tipo enumerativo Team.
	 */
	public Team getTeam();
	
	/**
	 * Questo metodo e` molto importante per ogni pezzo: esso deve, ricevute le coordinate
	 * nel quale il pezzo invocato si trova (esso non ne ha conoscenza diretta) applicare
	 * le regole del gioco relative al pezzo specifico. Nella fattispecie il metodo si occupa
	 * di calcolare come il pezzo puo` muovere senza finire fuori dalla scacchiera, senza
	 * attraversare caselle occupate da compagni e senza oltrepassare caselle occupate da
	 * avversari. Non si occupa di condizioni vietate dal gioco di ordine superiore (ad esempio
	 * mosse che metterebbero la propria squadra sotto scacco). L'iterabile che restituisce
	 * verra` poi scremato rispetto a queste condizioni.
	 * 
	 * @param x la coordinata x del pezzo
	 * @param y la coordinata y del pezzo
	 * @return un iterabile di mosse consentite espresse attraverso coordinate nella forma
	 * di singoli numeri interi (es. coordinate (x = 3, y = 2) diventano il numero 32). 
	 */
	public Iterable<Integer> availableMoves(int x, int y);
	
	/**
	 * Il metodo chiede al pezzo se, date le coordinate di posizione, esso minacci direttamente
	 * il re avversario (ovvero con almeno una delle sue mosse disponibili lo catturerebbe, caso
	 * pero` non previsto dalle regole degli scacchi). Per controllare cio` non c'e` bisogno
	 * di interpellare le regole di mosse consentite di grado superiore, bastano quelle "grezze"
	 * dato che il controllo di un'ipotetica cattura diretta non prevede l'esistenza di un turno
	 * successivo.
	 * 
	 * @param x la coordinata x del pezzo
	 * @param y la cooedinata y del pezzo
	 * @return true se il pezzo minaccia il re avversario, false altrimenti.
	 */
	public boolean check(int x, int y);
}
