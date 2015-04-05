package it.univr.chess.model.pieces;

import it.univr.chess.model.ModelPieces;
import it.univr.chess.model.Team;

import java.util.ArrayList;

/**
 * Questa classe implementa le caratteristiche di un oggetto pezzo degli scacchi (estende infatti Piece) e nello specifico
 * il pedone. Come le altre classi concrete che implementano un pezzo, ha il compito di definire in maniera unica e coerente
 * con le regole degli scacchi, le mosse che lo contraddistinguono: lo fa attraverso il metodo availableMoves.
 * Inoltre ha delle caratteristiche speciali che lo contraddistinguono in quanto unico pezzo che partecipa alla cattura
 * in enpassant. Ogni volta che un pedone esegue come prima mossa uno spostamento di due caselle, si rende vulnerabile
 * alla cattura in enpassant e per comunicarlo alla scacchiera e agli avversari, possiede una variabile d'istanza booleana
 * che e` settata a true se e solo se il pedone ha, nell'ultimo turno, mosso di due caselle (come prima mossa).
 * 
 * @author Alessandro Villa
 * @author Matteo Negrini
 * 
 */
public class Pawn extends Piece {
	
	private boolean enpassant;
	
	/**
	 * Il costruttore riceve la squadra e la scacchiera di appartenenza e le gira al costruttore della
	 * classe padre, essendo essi attributi comuni a tutti gli oggetti che estendono la classe Piece (la
	 * quale gestisce come vengono memorizzati e restituiti).
	 * Inoltre setta il proprio attributo esclusivo enpassant a false in quanto all'inizio non ha ancora
	 * mosso, tantomento di due caselle.
	 * 
	 * @param team la squadra cui il pezzo appartiene
	 * @param chessboard la scacchiera (ChessboardModel)
	 */
	public Pawn(Team team, ModelPieces chessboard) {
		super(team, chessboard);
		enpassant = false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Iterable<Integer> availableMoves(int x, int y) {
		ArrayList<Integer> availableMoves = new ArrayList<Integer>();
		Pieces other = null;
		
		/*
		 * I pedoni muovono in maniera diversa a seconda della squadra di appartenenza dato che sono gli unici a non
		 * poter retrocedere durante una partita. In questo caso il concetto di avanti e indietro e` speculare per una
		 * squadra rispetto all'altra e i controlli, seppur simili, vanno sdoppiati e considerati separatamente con 
		 * condizioni di maggiore/minore ultima/prima traversa differenti.
		 * Controllo percio` la squadra di appartenenza tenendo conto del fatto che i pedoni della squadra uno possono
		 * andare solo verso l'alto e i pedoni della squadra due possono andare solo verso il basso.
		 */		
		if (this.team == Team.TEAM1) {
			
			/* Per andare in alto a destra controllo se la casella in cui mi sposto esiste all'interno della scacchiera 
			 * (x diversa dall'ultima colonna e y diversa dall'ultima traversa, quest'ultimo controllo forse inutile a causa
			 * della promozione obbligatoria) ed in caso affermativo controllo che
			 * quella casella contenga o null o un pezzo avversario o possa andarci per enpassant (passo al metodo le mie
			 * coordinate e true se sto andando a destra) : in tali condizioni posso muovermi su di essa. */
			if (x < 7 && y < 7 &&
					( ((other = chessboard.getPiece(x + 1, y + 1)) != null && other.getTeam() != team) || enpassant(x, y, true) ))
				availableMoves.add((x + 1) * 10 + (y + 1)); // aggiungo quella casella alle consentite
			
			/* Per andare in alto di una casella controllo se la casella in cui mi sposto esiste all'interno della scacchiera 
			 * (y diversa dall'ultima traversa, controllo forse inutile a causa della promozione obbligatoria)
			 * ed in caso affermativo controllo che quella casella contenga null e solo null: in tali condizioni
			 * posso muovermi su di essa. */
			if (y < 7 && chessboard.getPiece(x, y + 1) == null)
				availableMoves.add(x * 10 + (y + 1)); // aggiungo quella casella alle consentite
			
			/* Per andare in alto di due caselle controllo se mi trovo nella traversa di partenza (y uguale ad 1)
			 * ed in caso affermativo controllo che le due caselle del tragitto contengano null e solo null: in tali
			 * condizioni posso muovermi su di essa. */
			if (y == 1 && chessboard.getPiece(x, y + 1) == null && chessboard.getPiece(x, y + 2) == null)
				availableMoves.add(x * 10 + (y + 2)); // aggiungo quella casella alle consentite
			
			/* Per andare in alto a sinistra controllo se la casella in cui mi sposto esiste all'interno della scacchiera 
			 * (x diversa dalla prima colonna e y diversa dall'ultima traversa, quest'ultimo controllo forse inutile a causa
			 * della promozione obbligatoria) ed in caso affermativo controllo che
			 * quella casella contenga o null o un pezzo avversario o possa andarci per enpassant (passo al metodo le mie
			 * coordinate e true se sto andando a destra, percio` false) : in tali condizioni posso muovermi su di essa. */
			if (x > 0 && y < 7 && 
					( ((other = chessboard.getPiece(x - 1, y + 1)) != null && other.getTeam() != team) || enpassant(x, y, false) ))
				availableMoves.add((x - 1) * 10 + (y + 1)); // aggiungo quella casella alle consentite
		} 
		else { // Se il pedone appartiene alla squadra 2 procede verso il basso
			
			/* Per andare in basso a destra controllo se la casella in cui mi sposto esiste all'interno della scacchiera 
			 * (x diversa dall'ultima colonna e y diversa dalla prima traversa, quest'ultimo controllo forse inutile a causa
			 * della promozione obbligatoria) ed in caso affermativo controllo che
			 * quella casella contenga o null o un pezzo avversario o possa andarci per enpassant (passo al metodo le mie
			 * coordinate e true se sto andando a destra) : in tali condizioni posso muovermi su di essa. */
			if (x < 7 && y > 0 && 
					( ((other = chessboard.getPiece(x + 1, y - 1)) != null && other.getTeam() != team) || enpassant(x, y, true) ))
				availableMoves.add((x + 1) * 10 + (y - 1)); // aggiungo quella casella alle consentite
			
			/* Per andare in basso di una casella controllo se la casella in cui mi sposto esiste all'interno della scacchiera 
			 * (y diversa dalla prima traversa, controllo forse inutile a causa della promozione obbligatoria)
			 * ed in caso affermativo controllo che quella casella contenga null e solo null: in tali condizioni
			 * posso muovermi su di essa. */
			if (y > 0 && chessboard.getPiece(x, y - 1) == null)
				availableMoves.add(x * 10 + (y - 1)); // aggiungo quella casella alle consentite
			
			/* Per andare in basso di due caselle controllo se mi trovo nella traversa di partenza (y uguale a 6)
			 * ed in caso affermativo controllo che le due caselle del tragitto contengano null e solo null: in tali
			 * condizioni posso muovermi su di essa. */
			if(y == 6 && chessboard.getPiece(x, y - 1) == null && chessboard.getPiece(x, y - 2) == null)
				availableMoves.add(x * 10 + (y - 2)); // aggiungo quella casella alle consentite
			
			/* Per andare in basso a sinistra controllo se la casella in cui mi sposto esiste all'interno della scacchiera 
			 * (x diversa dalla prima colonna e y diversa dalla prima traversa, quest'ultimo controllo forse inutile a causa
			 * della promozione obbligatoria) ed in caso affermativo controllo che
			 * quella casella contenga o null o un pezzo avversario o possa andarci per enpassant (passo al metodo le mie
			 * coordinate e true se sto andando a destra, percio` false) : in tali condizioni posso muovermi su di essa. */
			if (x > 0 && y > 0 &&
					( ((other = chessboard.getPiece(x - 1, y - 1)) != null && other.getTeam() != team) || enpassant(x, y, false) ))
				availableMoves.add((x - 1) * 10 + (y - 1)); // aggiungo quella casella alle consentite
		}
		
		return availableMoves;
	}
	
	/**
	 * Questo metodo e` specifico per i pezzi pedone i quali possono talvolta catturare un pedone avversario
	 * tramite la regola dell'enpassant, ovvero il pedone avversario ha appena mosso di due passi lasciando
	 * la traversa di partenza e si e` con quella mossa posizionato al mio fianco.
	 * Il metodo controlla se ho a fianco pedoni vulnerabili a questa cattura.
	 * 
	 * @param x
	 * @param y
	 * @param right
	 * @return true se ho pedoni a fianco vulnerabili alla cattura in enpassant, false altrimenti.
	 */
	private boolean enpassant(int x, int y, boolean right) {
		/* Attenzione: il fatto che a fianco abbia un pedone in stato di enpassant
		 * e' garanzia del fatto che la casella in cui mi posso muovere in diagonale
		 * sia vuota: infatti se il pedone a fianco e' in enpassant, ha appena mosso di due caselle
		 * e per le regole del gioco deve averle attraversate entrambe da vuote. */
		
		int move = (right) ? 1 : -1; //setto se devo controllare +1 o -1 sulle x (destra o sinistra)
		Pieces other;
		/* Controllo se a fianco ho un pedone e se e' in enpassant: non verifico che sia avversario
		 * perche' l'enpassant dura un turno e se fosse mio, il turno precedente (quello dell'avversario)
		 * avrebbe provveduto a rimuovere il suo stato di enpassant. */
		if (((other = chessboard.getPiece(x + move, y)) instanceof Pawn) && ((Pawn)other).enpassant)
			return true;
		else
			return false;
	}
	
	/**
	 * Questo metodo viene usato dalla scacchiera per dire al pezzo pedone che si trova o non si
	 * trova vulnerabile all'enpassant. Il metodo setta la variabile d'istanza del pedone di 
	 * conseguenza.
	 * 
	 * @param enpassant
	 */
	public void setEnpassant(boolean enpassant) {
		this.enpassant = enpassant;
	}
	
}
