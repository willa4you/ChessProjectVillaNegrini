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
	public boolean check(byte x, byte y){
		Piece other = null;
		for(int xy : this.mosseConsentite(x, y))
			if(((other = ChessboardModel.getPezzoInPosizione((byte)(xy/10), (byte)(xy%10))) instanceof King) && other.team != this.team)
				return true;
		
		return false;
	}
	
}
