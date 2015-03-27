package it.univr.chess.model.pieces;

import it.univr.chess.model.ChessboardModel2;
import it.univr.chess.model.Core;
import it.univr.chess.model.Team;

public abstract class Piece implements Pieces {
	public final Team team;
	
	public Piece(Team team) {
			this.team = team;		
		//il costruttore fissa la squadra del pezzo
	}
	
	@Override
	public Team getTeam(){
		return this.team;
	}
	
	@Override
	public boolean check(int x, int y) {
		for (int xy : this.mosseConsentite(x, y))
			if (ChessboardModel2.getPezzoInPosizione(xy / 10, xy % 10) instanceof King)
			//non controllo la squadra del re che sto mangiando perch� mosseConsentite non me lo consente
				return true;
		
		return false;
	}
	
}
