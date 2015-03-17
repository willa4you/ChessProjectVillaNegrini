package chess_model;

import java.util.ArrayList;

public class Pedone extends Pezzo {

	public Pedone(int squadra) {
		super(squadra);
	}

	@Override
	public Iterable<Integer> mosseConsentite(byte x, byte y) {
		ArrayList<Integer> mosseConsentite = new ArrayList<Integer>();
		Squadra other = null;
	
		//per muovermi in alto a destra non devo uscire e ci dev'essere un avversario
		if(x + 1 <= 7 && y + 1 <= 7 && (other = Scacchiera_.getPezzoInPosizione((byte)(x + 1), (byte)(y + 1)).squadra) != squadra && other != null)
			mosseConsentite.add((int)((x+1)*10 + y + 1));//aggiungo quella casella alle consentite
		
		//per muovermi in alto a sinistra non devo uscire e ci dev'essere un avversario
		if(x - 1 >= 0 && y + 1 <= 7 && (other = Scacchiera_.getPezzoInPosizione((byte)(x - 1), (byte)(y + 1)).squadra) != squadra && other != null)
			mosseConsentite.add((int)((x-1)*10 + y + 1));//aggiungo quella casella alle consentite
		
		//per muovermi in alto non devo uscire e dev'essere casella vuota
		if(y + 1 <= 7 && Scacchiera_.getPezzoInPosizione((byte)(x), (byte)(y + 1)) == null)
			mosseConsentite.add((int)(x*10 + y + 1));//aggiungo quella casella alle consentite
		
		return mosseConsentite;
	}


}
