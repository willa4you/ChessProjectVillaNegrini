package chess_model;

public abstract class Piece {
	static enum Squadra {Squadra1, Squadra2}
	public final Squadra squadra;
	
	public Piece(int squadra){
		if (squadra == 1)
			this.squadra = Squadra.Squadra1;
		else
			this.squadra = Squadra.Squadra2;
		
		//il costruttore fissa la squadra del pezzo
	}
	
	public abstract Iterable<Integer> mosseConsentite(byte x, byte y);
	
}
