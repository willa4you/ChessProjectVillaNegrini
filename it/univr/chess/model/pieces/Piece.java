package it.univr.chess.model.pieces;

import it.univr.chess.model.ModelPieces;
import it.univr.chess.model.Team;
/**
 * Classe astratta che implementa l'interfaccia Pieces e descrive due dei suoi
 * tre metodi (alle sottoclassi sara` solo chiesto di implementare il metodo
 * delle mosse disponibili, giustamente diverso per ogni tipo di pezzo).
 * Sono qui previste due variabili d'istanza, entrambe final, le quali alla
 * creazione assegnano al pezzo una squadra ed una scacchiera di riferimento
 * (l'oggetto ChessboardModel al quale ci si riferisce attraverso l'interfaccia
 * ModelPieces, trovandosi tecnicamente in un package separato).
 * 
 * @author Alessandro Villa
 * @author Matteo Negrini
 *
 */
public abstract class Piece implements Pieces {
	protected final Team team;
	protected final ModelPieces chessboard;
	/**
	 * Il costruttore fissa la squadra di appartenenza e la scacchiera, ovvero l'oggetto
	 * Model che gestisce la partita. Vengono ricevuti come argomenti.
	 * @param team
	 * @param chessboard
	 */
	public Piece(Team team, ModelPieces chessboard) {
			this.team = team;
			this.chessboard = chessboard;
	}
	
	@Override
	public Team getTeam() {
		return this.team;
	}
	
	@Override
	public boolean check(int x, int y) {
		for (int xy : this.availableMoves(x, y))
			/* Appena trovo una mossa che mi porterebbe su una casella dove c'e` un Re
			 * (necessariamente di squadra avversaria) ritorno true. */
			if (chessboard.getPiece(xy / 10, xy % 10) instanceof King)
				return true;
		
		// Se il for finisce senza ritornare true, e` condizione sufficiente per ritornare false
		return false;
	}
	
}
