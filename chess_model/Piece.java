package chess_model;

public abstract class Piece {
	public final Team team;
	
	public Piece(int team){
		if (team == 1)
			this.team = Team.Team1;
		else
			this.team = Team.Team2;
		
		//il costruttore fissa la squadra del pezzo
	}
	
	public abstract Iterable<Integer> mosseConsentite(byte x, byte y);
	
}
