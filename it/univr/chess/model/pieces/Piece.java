package it.univr.chess.model.pieces;

import it.univr.chess.model.Model;
import it.univr.chess.model.Team;

public abstract class Piece implements Pieces {
	public final Team team;
	public final Model chessboard;
	
	public Piece(Team team, Model chessboard) {
			this.team = team;
			this.chessboard = chessboard;
		//il costruttore fissa la squadra del pezzo
	}
	
	@Override
	public Team getTeam(){
		return this.team;
	}
	
	@Override
	public boolean check(int x, int y) {
		for (int xy : this.mosseConsentite(x, y))
			if (chessboard.getPiece(xy / 10, xy % 10) instanceof King)
			//non controllo la squadra del re che sto mangiando perch� mosseConsentite non me lo consente
				return true;
		
		return false;
	}
	
}
