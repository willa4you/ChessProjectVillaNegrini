package chess_model;

import java.util.ArrayList;

public class Cavallo extends Pezzo {

	public Cavallo(int squadra) {
		super(squadra);
	}

	@Override
	public Iterable<Integer> mosseConsentite(byte x, byte y) {
		ArrayList<Integer> mosseConsentite = new ArrayList<Integer>();
		return mosseConsentite;
	}


}
