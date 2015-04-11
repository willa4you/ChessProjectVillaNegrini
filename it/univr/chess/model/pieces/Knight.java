package it.univr.chess.model.pieces;

import it.univr.chess.model.ModelPieces;
import it.univr.chess.model.Team;

import java.util.ArrayList;
/**
 * Questa classe implementa le caratteristiche di un oggetto pezzo degli scacchi (estende infatti Piece) e nello specifico
 * il cavallo. Come le altre classi concrete che implementano un pezzo, ha il compito di definire in maniera unica e coerente
 * con le regole degli scacchi, le mosse che lo contraddistinguono: lo fa attraverso il metodo availableMoves.
 * 
 * @author Alessandro Villa
 * @author Matteo Negrini
 * 
 */
public class Knight extends Piece {
	
	/**
	 * Il costruttore riceve la squadra e la scacchiera di appartenenza e le gira al costruttore della
	 * classe padre, essendo essi attributi comuni a tutti gli oggetti che estendono la classe Piece (la
	 * quale gestisce come vengono memorizzati e restituiti).
	 * 
	 * @param team la squadra cui il pezzo appartiene
	 * @param chessboard la scacchiera (ChessboardModel)
	 */
	public Knight(Team team, ModelPieces chessboard) {
		super(team, chessboard);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Iterable<Integer> availableMoves(int x, int y) {
		ArrayList<Integer> availableMoves = new ArrayList<Integer>();
		Pieces other = null;
		/*
		 * Numeriamo le mosse che puo` fare un cavallo in questo modo:
		 * 
		 * -3-2-
		 * 4---1
		 * --K--
		 * 5---8
		 * -6-7-
		 * dove K e` il cavallo e i numeri le caselle di arrivo numerate
		 * seguendo l'ordine di una circonferenza goniometrica.
		 */
		
		/* Per eseguire la mossa numero 1 e restare all'interno della scacchiera devo trovarmi all'interno dell'ultima
		 * traversa esclusa e all'interno della penultima colonna esclusa: in caso affermativo controllo che quella casella
		 * contenga o null o un pezzo avversario: in tali condizioni posso muovermi su di essa. */
		if (y < 7 && x < 6 && (((other = chessboard.getPiece(x + 2, y + 1)) == null) || other.getTeam() != this.team))
			availableMoves.add((x + 2) * 10 + (y + 1)); // aggiungo quella casella alle consentite
		
		/* Per eseguire la mossa numero 2 e restare all'interno della scacchiera devo trovarmi all'interno della penultima
		 * traversa esclusa e all'interno dell'ultima colonna esclusa: in caso affermativo controllo che quella casella
		 * contenga o null o un pezzo avversario: in tali condizioni posso muovermi su di essa. */
		if (y < 6 && x < 7 && (((other = chessboard.getPiece(x + 1, y + 2)) == null) || other.getTeam() != this.team))
			availableMoves.add((x + 1) * 10 + (y + 2)); // aggiungo quella casella alle consentite
		
		/* Per eseguire la mossa numero 3 e restare all'interno della scacchiera devo trovarmi all'interno della penultima
		 * traversa esclusa e all'interno della prima colonna esclusa: in caso affermativo controllo che quella casella
		 * contenga o null o un pezzo avversario: in tali condizioni posso muovermi su di essa. */
		if (y < 6 && x > 0 && (((other = chessboard.getPiece(x - 1, y + 2)) == null) || other.getTeam() != this.team))
			availableMoves.add((x - 1) * 10 + (y + 2)); // aggiungo quella casella alle consentite
		
		/* Per eseguire la mossa numero 4 e restare all'interno della scacchiera devo trovarmi all'interno dell'ultima
		 * traversa esclusa e all'interno della seconda colonna esclusa: in caso affermativo controllo che quella casella
		 * contenga o null o un pezzo avversario: in tali condizioni posso muovermi su di essa. */
		if (y < 7 && x > 1 && (((other = chessboard.getPiece(x - 2, y + 1)) == null) || other.getTeam() != this.team))
			availableMoves.add((x - 2) * 10 + (y + 1)); // aggiungo quella casella alle consentite	
		
		/* Per eseguire la mossa numero 5 e restare all'interno della scacchiera devo trovarmi all'interno della prima
		 * traversa esclusa e all'interno della seconda colonna esclusa: in caso affermativo controllo che quella casella
		 * contenga o null o un pezzo avversario: in tali condizioni posso muovermi su di essa. */
		if (y > 0 && x > 1 && (((other = chessboard.getPiece(x - 2, y - 1)) == null) || other.getTeam() != this.team))
			availableMoves.add((x - 2) * 10 + (y - 1)); // aggiungo quella casella alle consentite
		
		/* Per eseguire la mossa numero 6 e restare all'interno della scacchiera devo trovarmi all'interno della seconda
		 * traversa esclusa e all'interno della prima colonna esclusa: in caso affermativo controllo che quella casella
		 * contenga o null o un pezzo avversario: in tali condizioni posso muovermi su di essa. */
		if (y > 1 && x > 0 && (((other = chessboard.getPiece(x - 1, y - 2)) == null) || other.getTeam() != this.team))
			availableMoves.add((x - 1) * 10 + (y - 2)); // aggiungo quella casella alle consentite
		
		/* Per eseguire la mossa numero 7 e restare all'interno della scacchiera devo trovarmi all'interno della seconda
		 * traversa esclusa e all'interno dell'ultima colonna esclusa: in caso affermativo controllo che quella casella
		 * contenga o null o un pezzo avversario: in tali condizioni posso muovermi su di essa. */
		if (y > 1 && x < 7 && (((other = chessboard.getPiece(x + 1, y - 2)) == null) || other.getTeam() != this.team))
			availableMoves.add((x + 1) * 10 + (y - 2)); // aggiungo quella casella alle consentite
		
		/* Per eseguire la mossa numero 8 e restare all'interno della scacchiera devo trovarmi all'interno della prima
		 * traversa esclusa e all'interno della penultima colonna esclusa: in caso affermativo controllo che quella casella
		 * contenga o null o un pezzo avversario: in tali condizioni posso muovermi su di essa. */
		if (y > 0 && x < 6 && (((other = chessboard.getPiece(x + 2, y - 1)) == null) || other.getTeam() != this.team))
			availableMoves.add((x + 2) * 10 + (y - 1)); // aggiungo quella casella alle consentite
		
		return availableMoves;
	}

}
