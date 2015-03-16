package progettoChess;

import java.util.ArrayList;

public class Re extends Pezzo {

	public Re(int squadra) {
		super(squadra);
	}

	@Override
	public Iterable<Integer> mosseConsentite(byte x, byte y) {
		ArrayList<Integer> mosseConsentite = new ArrayList<Integer>();
		return mosseConsentite;
	}

}
