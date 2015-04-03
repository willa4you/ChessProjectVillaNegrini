package it.univr.chess.model.pieces;

import it.univr.chess.model.ModelPieces;
import it.univr.chess.model.Team;

import java.util.ArrayList;
/**
 * Questa classe implementa le caratteristiche di un oggetto pezzo degli scacchi (estende infatti CastlingPiece la quale estende
 * Piece) e nello specifico la torre. Come le altre classi concrete che implementano un pezzo, ha il compito di definire in maniera
 * unica e coerente con le regole degli scacchi, le mosse che lo contraddistinguono: lo fa attraverso il metodo availableMoves.
 * Inoltre e` un pezzo che partecipa all'arrocco percio` estende la classe astratta CastlingPiece la quale implementa e prevede
 * metodi per la gestione di un attributo moved (vedi classe astratta CastlingPiece).
 * @author Alessandro Villa
 * @author Matteo Negrini
 * 
 */
public class Rook extends CastlingPiece {
	/**
	 * Il costruttore riceve la squadra e la scacchiera di appartenenza e le gira al costruttore della
	 * classe padre, essendo essi attributi comuni a tutti gli oggetti che estendono la classe Piece (la
	 * quale gestisce come vengono memorizzati e restituiti).
	 * @param team
	 * @param chessboard
	 */
	public Rook(Team team, ModelPieces chessboard) {
		super(team, chessboard);
	}

	@Override
	public Iterable<Integer> availableMoves(int x, int y) {
		ArrayList<Integer> availableMoves = new ArrayList<Integer>();
		
		int i = 1;
		Pieces other = null;
		/* vado verso destra (x incrementa, y fissa) e controllo se la casella in cui mi sposto esiste all'interno
		 * della scacchiera ed in caso affermativo controllo che quella casella contenga o null o un pezzo avversario:
		 * in tali condizioni posso muovermi su di essa. */
		while (true) {
			if (x + i <= 7 && ((other = chessboard.getPiece(x + i, y)) == null || other.getTeam() != this.team)) {
					availableMoves.add((x + i) * 10 + y); // aggiungo questa casella alle consentite
					if (other != null) // se c'e` qualcuno so che non posso andare oltre e chiudo
						break;
					else
						i++; // se nella casella non c'e` nessuno posso proseguire in quella direzione
			}
			else
				break; // non proseguo in questa direzione se la scacchiera e` finita o incontro un compagno di squadra
		}
		
		i = 1;
		/* vado verso l'alto (x fissa, y incrementa) e controllo se la casella in cui mi sposto esiste all'interno
		 * della scacchiera ed in caso affermativo controllo che quella casella contenga o null o un pezzo avversario:
		 * in tali condizioni posso muovermi su di essa. */
		while (true) {
			if (y + i <= 7 && ((other = chessboard.getPiece(x, y + i)) == null || other.getTeam() != this.team)) {// o team avversaria o NULL vanno bene
					availableMoves.add((x * 10 + (y + i))); // aggiungo questa casella alle consentite
					if (other != null) // se c'e` qualcuno so che non posso andare oltre e chiudo
						break;
					else
						i++; // se nella casella non c'e` nessuno posso proseguire in quella direzione
			}
			else
				break; // non proseguo in questa direzione se la scacchiera e` finita o incontro un compagno di squadra
		}
		
		i = 1;
		/* vado verso sinistra (x decrementa, y fissa) e controllo se la casella in cui mi sposto esiste all'interno
		 * della scacchiera ed in caso affermativo controllo che quella casella contenga o null o un pezzo avversario:
		 * in tali condizioni posso muovermi su di essa. */
		while (true) {
			if (x - i >= 0 && ((other = chessboard.getPiece(x - i, y)) == null || other.getTeam() != this.team)) {// o team avversaria o NULL vanno bene
					availableMoves.add((x - i) * 10 + y); // aggiungo questa casella alle consentite
					if (other != null) // se c'e` qualcuno so che non posso andare oltre e chiudo
						break;
					else
						i++; // se nella casella non c'e` nessuno posso proseguire in quella direzione
			}
			else
				break; // non proseguo in questa direzione se la scacchiera e` finita o incontro un compagno di squadra
		}
		
		i = 1;
		/* vado verso il basso (x fissa, y decrementa) e controllo se la casella in cui mi sposto esiste all'interno
		 * della scacchiera ed in caso affermativo controllo che quella casella contenga o null o un pezzo avversario:
		 * in tali condizioni posso muovermi su di essa. */
		while (true) {
			if (y - i >= 0 && ((other = chessboard.getPiece(x, y - i)) == null || other.getTeam() != this.team)) {// o team avversaria o NULL vanno bene
					availableMoves.add(x * 10 + (y - i)); // aggiungo questa casella alle consentite
					if (other != null) // se c'e` qualcuno so che non posso andare oltre e chiudo
						break;
					else
						i++; // se nella casella non c'e` nessuno posso proseguire in quella direzione
			}
			else
				break; // non proseguo in questa direzione se la scacchiera e` finita o incontro un compagno di squadra
		}
	
		return availableMoves;
	}

}
