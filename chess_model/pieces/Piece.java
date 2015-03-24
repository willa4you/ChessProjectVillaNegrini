package chess_model.pieces;

import chess_model.ChessboardModel;
import chess_model.Team;
import chess_model.Core;

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
			if (ChessboardModel.getPezzoInPosizione(xy / 10, xy % 10) instanceof King)
			//non controllo la squadra del re che sto mangiando perch� mosseConsentite non me lo consente
				return true;
		
		return false;
	}
	
}
