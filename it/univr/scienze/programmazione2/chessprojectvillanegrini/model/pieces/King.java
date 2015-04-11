package it.univr.scienze.programmazione2.chessprojectvillanegrini.model.pieces;

import it.univr.scienze.programmazione2.chessprojectvillanegrini.model.ModelPieces;
import it.univr.scienze.programmazione2.chessprojectvillanegrini.model.Team;

import java.util.ArrayList;

/**
 * Questa classe implementa le caratteristiche di un oggetto pezzo degli scacchi (estende infatti CastlingPiece la quale estende
 * Piece) e nello specifico il re. Come le altre classi concrete che implementano un pezzo, ha il compito di definire in maniera
 * unica e coerente con le regole degli scacchi, le mosse che lo contraddistinguono: lo fa attraverso il metodo availableMoves.
 * Inoltre e` un pezzo che partecipa all'arrocco percio` estende la classe astratta CastlingPiece la quale implementa e prevede
 * metodi per la gestione di un attributo moved (vedi classe astratta CastlingPiece).
 * 
 * @author Alessandro Villa
 * @author Matteo Negrini
 * 
 */
public class King extends CastlingPiece {
	
	/**
	 * Il costruttore riceve la squadra e la scacchiera di appartenenza e le gira al costruttore della
	 * classe padre, essendo essi attributi comuni a tutti gli oggetti che estendono la classe Piece (la
	 * quale gestisce come vengono memorizzati e restituiti).
	 * @param team la squadra cui il pezzo appartiene
	 * @param chessboard la scacchiera (ChessboardModel)
	 */
	public King(Team team, ModelPieces chessboard) {
		super(team, chessboard);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Iterable<Integer> availableMoves(int x, int y) {
		ArrayList<Integer> availableMoves = new ArrayList<Integer>();
		Pieces other = null;
		
		/* vado verso destra e controllo se la casella in cui mi sposto esiste all'interno della scacchiera 
		 * (x diversa dall'ultima colonna) ed in caso affermativo controllo che quella casella contenga o null
		 * o un pezzo avversario: in tali condizioni posso muovermi su di essa. */
		if (x < 7 && (((other = chessboard.getPiece(x + 1, y)) == null) || other.getTeam() != this.team))
			availableMoves.add((x + 1) * 10 + y); // aggiungo quella casella alle consentite
		
		/* Eseguo i controlli per un eventuale arrocco verso destra (arrocco corto) passando al metodo 
		 * che controlla, la traversa da esaminare (y) e se si tratta di un arrocco corto (true). */
		if (castling(y, true))
			availableMoves.add((x + 2) * 10 + y); // aggiungo quella casella alle consentite
		
		/* vado in alto a destra e controllo se la casella in cui mi sposto esiste all'interno della scacchiera 
		 * (x diversa dall'ultima colonna e y diversa dall'ultima traversa) ed in caso affermativo controllo che
		 * quella casella contenga o null o un pezzo avversario: in tali condizioni posso muovermi su di essa. */
		if (x < 7 && y < 7 && (((other = chessboard.getPiece(x + 1, y + 1)) == null) || other.getTeam() != this.team))
			availableMoves.add((x + 1) * 10 + (y + 1)); // aggiungo quella casella alle consentite	
		
		/* vado in alto e controllo se la casella in cui mi sposto esiste all'interno della scacchiera 
		 * (y diversa dall'ultima traversa) ed in caso affermativo controllo che
		 * quella casella contenga o null o un pezzo avversario: in tali condizioni posso muovermi su di essa. */
		if (y < 7 && (((other = chessboard.getPiece(x, y + 1)) == null) || other.getTeam() != this.team))
			availableMoves.add(x * 10 + (y + 1)); // aggiungo quella casella alle consentite
		
		/* vado in alto a sinistra e controllo se la casella in cui mi sposto esiste all'interno della scacchiera 
		 * (x diversa dalla prima colonna e y diversa dall'ultima traversa) ed in caso affermativo controllo che
		 * quella casella contenga o null o un pezzo avversario: in tali condizioni posso muovermi su di essa. */
		if (x > 0 && y < 7 && (((other = chessboard.getPiece(x - 1, y + 1)) == null) || other.getTeam() != this.team))
			availableMoves.add((x - 1) * 10 + (y + 1)); // aggiungo quella casella alle consentite	
		
		/* vado a sinistra e controllo se la casella in cui mi sposto esiste all'interno della scacchiera 
		 * (x diversa dalla prima colonna) ed in caso affermativo controllo che
		 * quella casella contenga o null o un pezzo avversario: in tali condizioni posso muovermi su di essa. */
		if (x > 0 && (((other = chessboard.getPiece(x - 1, y)) == null) || other.getTeam() != this.team))
			availableMoves.add((x - 1) * 10 + y); // aggiungo quella casella alle consentite
		
		/* Eseguo i controlli per un eventuale arrocco verso sinistra (arrocco lungo) passando al metodo 
		 * che controlla, la traversa da esaminare (y) e se si tratta di un arrocco corto (false quindi lungo). */
		if (castling(y, false))
			availableMoves.add((x - 2) * 10 + y); // aggiungo quella casella alle consentite
		
		/* vado in basso a sinistra e controllo se la casella in cui mi sposto esiste all'interno della scacchiera 
		 * (x diversa dalla prima colonna e y diversa dalla prima traversa) ed in caso affermativo controllo che
		 * quella casella contenga o null o un pezzo avversario: in tali condizioni posso muovermi su di essa. */
		if (x > 0 && y > 0 && (((other = chessboard.getPiece(x - 1, y - 1)) == null) || other.getTeam() != this.team))
			availableMoves.add((x - 1) * 10 + (y - 1)); // aggiungo quella casella alle consentite
		
		/* vado in basso e controllo se la casella in cui mi sposto esiste all'interno della scacchiera 
		 * (y diversa dalla prima traversa) ed in caso affermativo controllo che
		 * quella casella contenga o null o un pezzo avversario: in tali condizioni posso muovermi su di essa. */
		if (y > 0 && (((other = chessboard.getPiece(x, y - 1)) == null) || other.getTeam() != this.team))
			availableMoves.add((x * 10) + (y - 1)); // aggiungo quella casella alle consentite
		
		/* vado in basso a destra e controllo se la casella in cui mi sposto esiste all'interno della scacchiera 
		 * (x diversa dall'ultima colonna e y diversa dalla prima traversa) ed in caso affermativo controllo che
		 * quella casella contenga o null o un pezzo avversario: in tali condizioni posso muovermi su di essa. */
		if (x < 7 && y > 0  && (((other = chessboard.getPiece(x + 1, y - 1)) == null) || other.getTeam() != this.team))
			availableMoves.add((x + 1) * 10 + (y - 1)); // aggiungo quella casella alle consentite	
		
		return availableMoves;
	}
	
	/**
	 * Questo metodo e` previsto solo ed esclusivamente per i pezzi di tipo King. Nonostante infatti anche le
	 * torri partecipino all'arrocco, la decisione di eseguirlo parte esclusivamente muovendo il re di due caselle.
	 * Questo metodo valuta se e` possibile farlo alla luce delle regole base dell'arrocco ovvero:<br>1- il re non deve
	 * aver mai mosso;<br>2- la torre in direzione in cui si cerca un arrocco non deve aver mai mosso;<br>3- le due caselle
	 * che li separano devono essere vuote (contengono null).<br>Le regole di grado superiore che prevedono non sussistano
	 * certi stati di minaccia al re, sono delegate alla scacchiera che ha visione di gioco completa.
	 *  
	 * @param y La traversa in cui si cerca di eseguirlo (definisce anche la squadra)
	 * @param shortCastling true == arrocco corto, arrocco lungo altrimenti
	 * @return true se l'arrocco e` possibile rispetto alle regole base.
	 */
	private boolean castling(int y, boolean shortCastling) {
		
		// Primo controllo: se ho mosso il re almeno una volta, l'arrocco e` vietato
		if (this.moved)
			return false;
		
		// Secondo controllo: se ho mosso la torre almeno una volta, l'arrocco e` vietato
		int rookPlace = (shortCastling) ? 7 : 0; //la colonna in cui cercare la torre
		Pieces rook = chessboard.getPiece(rookPlace, y);
		if ( !(rook instanceof Rook) || ((CastlingPiece) rook).getMoved())
			return false;
		
		// Terzo controllo: se le caselle tra re e torre non sono tutte vuote l'arrocco e` vietato	
		if (shortCastling) {
			if ( (chessboard.getPiece(5, y) != null) || (chessboard.getPiece(6, y) != null) )
				return false;
		}
		else
			if ( (chessboard.getPiece(3, y) != null) || (chessboard.getPiece(2, y) != null) || (chessboard.getPiece(1, y) != null) )
				return false;
		
		
		/* Se sono arrivato fino qui senza ritornare false
		 * allora posso tornare true.*/
		return true;
	}
	
}
