package chessprojectvillanegrini.model.pieces;

import java.util.ArrayList;

import chessprojectvillanegrini.model.ModelPieces;
import chessprojectvillanegrini.model.Team;

/**
 * Questa classe implementa le caratteristiche di un oggetto pezzo degli scacchi (estende infatti Piece) e nello specifico
 * l'alfiere. Come le altre classi concrete che implementano un pezzo, ha il compito di definire in maniera unica e coerente
 * con le regole degli scacchi, le mosse che lo contraddistinguono: lo fa attraverso il metodo availableMoves.
 * 
 * @author Alessandro Villa
 * @author Matteo Negrini
 * 
 */
public class Bishop extends Piece {
	
	/**
	 * Il costruttore riceve la squadra e la scacchiera di appartenenza e le gira al costruttore della
	 * classe padre, essendo essi attributi comuni a tutti gli oggetti che estendono la classe Piece (la
	 * quale gestisce come vengono memorizzati e restituiti).
	 * 
	 * @param team la squadra cui il pezzo appartiene
	 * @param chessboard la scacchiera (ChessboardModel)
	 */
	public Bishop(Team team, ModelPieces chessboard) {
		super(team, chessboard);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Iterable<Integer> availableMoves(int x, int y) {
		ArrayList<Integer> availableMoves = new ArrayList<Integer>();
		
		int i = 1;
		Pieces other = null;
		/* vado in alto a destra (x e y incrementano) e controllo se la casella in cui mi sposto esiste all'interno
		 * della scacchiera ed in caso affermativo controllo che quella casella contenga o null o un pezzo avversario:
		 * in tali condizioni posso muovermi su di essa. */
		while (true) {
			if (x + i <= 7 && y + i <= 7 &&
					((other = chessboard.getPiece(x + i, y + i)) == null || other.getTeam() != this.team)) {
				availableMoves.add((x + i) * 10 + (y + i)); // aggiungo questa casella alle consentite
				if (other != null) // se c'e` qualcuno so che non posso andare oltre e chiudo
					break;
				else
					i++; // se nella casella non c'e` nessuno posso proseguire in quella direzione
			}
			else
				break; // non proseguo in questa direzione se la scacchiera e` finita o incontro un compagno di squadra
		}
		
		i = 1;
		/* vado in alto a sinistra (x decrementa e y incrementa) e controllo se la casella in cui mi sposto esiste all'interno
		 * della scacchiera ed in caso affermativo controllo che quella casella contenga o null o un pezzo avversario:
		 * in tali condizioni posso muovermi su di essa. */
		while (true) {
			if (x - i >= 0 && y + i <= 7 && 
					((other = chessboard.getPiece(x - i, y + i)) == null || other.getTeam() != this.team)) {
				availableMoves.add((x - i) * 10 + (y + i)); // aggiungo questa casella alle consentite
				if (other != null) // se c'e` qualcuno so che non posso andare oltre e chiudo
					break;
				else
					i++; // se nella casella non c'e` nessuno posso proseguire in quella direzione
			}
			else
				break; // non proseguo in questa direzione se la scacchiera e` finita o incontro un compagno di squadra
		}
		
		i = 1;
		/* vado in basso a sinistra (x e y decrementano) e controllo se la casella in cui mi sposto esiste all'interno
		 * della scacchiera ed in caso affermativo controllo che quella casella contenga o null o un pezzo avversario:
		 * in tali condizioni posso muovermi su di essa. */
		while (true) {
			if (x - i >= 0 && y - i >= 0 &&
					((other = chessboard.getPiece(x - i, y - i)) == null || other.getTeam() != this.team)) {
				availableMoves.add((x - i) * 10 + (y - i)); // aggiungo questa casella alle consentite
				if (other != null) // se c'e` qualcuno so che non posso andare oltre e chiudo
					break;
				else
					i++; // se nella casella non c'e` nessuno posso proseguire in quella direzione
			}
			else
				break; // non proseguo in questa direzione se la scacchiera e` finita o incontro un compagno di squadra
		}
		
		i = 1;
		/* vado in basso a destra (x incrementa e y decrementa) e controllo se la casella in cui mi sposto esiste all'interno
		 * della scacchiera ed in caso affermativo controllo che quella casella contenga o null o un pezzo avversario:
		 * in tali condizioni posso muovermi su di essa. */
		while (true) {
			if (x + i <= 7 && y - i >= 0 &&
					((other = chessboard.getPiece(x + i, y - i)) == null || other.getTeam() != this.team)) {	
				availableMoves.add((x + i) * 10 + (y - i)); // aggiungo questa casella alle consentite
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
