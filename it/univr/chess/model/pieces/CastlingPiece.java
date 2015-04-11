package it.univr.chess.model.pieces;

import it.univr.chess.model.ModelPieces;
import it.univr.chess.model.Team;
/**
 * Questa classe astratta implementa il concetto di pezzo che prende parte ad un arrocco.
 * Essa estende Piece dalla quale eredita tutte le caratteristiche. Ad esse aggiunge
 * un'ulteriore attributo: la variabile d'istanza booleana moved, la quale conserva
 * l'informazione se il pezzo e` mai stato mosso durante la partita. Di fatto essa e` settata
 * a false solo dal costruttore; poi ad ogni mossa il gioco provvede a settarla a true
 * definitivamente. Estendono questa classe gli oggetti pezzo di tipo torre e re.
 * 
 * @author Alessandro Villa
 * @author Matteo Negrini
 * 
 */
public abstract class CastlingPiece extends Piece {
	
	protected boolean moved;
	
	/**
	 * Il costruttore riceve la squadra e la scacchiera dalla sottoclasse e le gira alla
	 * classe padre Piece. Inoltre setta a false il parametro moved specificando che appena
	 * creato, l'oggetto non ha ancora mosso.
	 * 
	 * @param team la squadra cui il pezzo appartiene
	 * @param chessboard la scacchiera (ChessboardModel)
	 */
	public CastlingPiece(Team team, ModelPieces chessboard) {
		super(team, chessboard);
		this.moved = false;		
	}
	
	/**
	 * Questo metodo, specifico per gli oggetti CastlingPiece, prende il valore della
	 * variabile d'istanza booleana moved e lo ritorna.
	 * @return true se l'oggetto ha mosso almeno una volta, false altrimenti.
	 */
	public boolean getMoved() {
		return this.moved;
	}
	
	/**
	 * Questo metodo, specifico per gli oggetti CastlingPiece, viene invocato ad ogni
	 * mossa di un oggetto di una sottoclasse. Esso setta la variabile moved a true.
	 */
	public void setMoved() {
		this.moved = true;
	}
	
}
