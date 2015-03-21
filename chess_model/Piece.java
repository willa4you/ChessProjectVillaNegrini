package chess_model;

public abstract class Piece {
	public final Team team;
	
	public Piece(int team) {
		if (team == 1)
			this.team = Team.Team1;
		else
			this.team = Team.Team2;
		
		//il costruttore fissa la squadra del pezzo
	}
	
	public abstract Iterable<Integer> mosseConsentite(int x, int y);
	public boolean check(int x, int y) {
		for (int xy : this.mosseConsentite(x, y))
			if (ChessboardModel.getPezzoInPosizione(xy / 10, xy % 10) instanceof King)
			//non controllo la squadra del re che sto mangiando perch� mosseConsentite non me lo consente
				return true;
		
		return false;
	}
	
}
