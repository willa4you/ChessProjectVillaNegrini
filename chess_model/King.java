package chess_model;

import java.util.ArrayList;

public class King extends Piece {

	public King(int squadra) {
		super(squadra);
	}

	@Override
	public Iterable<Integer> mosseConsentite(byte x, byte y) {
		ArrayList<Integer> mosseConsentite = new ArrayList<Integer>();
		
		//destra
		if(x < 7 && ChessboardModel.getPezzoInPosizione((byte)(x + 1), y).squadra != squadra)
			mosseConsentite.add((int)((x + 1) * 10 + y));//aggiungo quella casella alle consentite
		//alto a destra
		if(x < 7 && y < 7 && ChessboardModel.getPezzoInPosizione((byte)(x + 1), (byte)(y + 1)).squadra != squadra)
			mosseConsentite.add((int)((x + 1) * 10 + y + 1));//aggiungo quella casella alle consentite		
		//alto
		if(y < 7 && ChessboardModel.getPezzoInPosizione(x, (byte)(y + 1)).squadra != squadra)
			mosseConsentite.add((int)(x * 10 + y + 1));//aggiungo quella casella alle consentite
		//alto a sinistra
		if(x > 0 && y < 7 && ChessboardModel.getPezzoInPosizione((byte)(x - 1), (byte)(y + 1)).squadra != squadra)
			mosseConsentite.add((int)((x - 1) * 10 + y + 1));//aggiungo quella casella alle consentite	
		//sinistra
		if(x > 0 && ChessboardModel.getPezzoInPosizione((byte)(x - 1), y).squadra != squadra)
			mosseConsentite.add((int)((x - 1) * 10 + y));//aggiungo quella casella alle consentite	
		//basso a sinistra
		if(x > 0 && y > 0 && ChessboardModel.getPezzoInPosizione((byte)(x - 1), (byte)(y - 1)).squadra != squadra)
			mosseConsentite.add((int)((x - 1) * 10 + y - 1));//aggiungo quella casella alle consentite
		//basso
		if(y > 0 && ChessboardModel.getPezzoInPosizione(x, (byte)(y - 1)).squadra != squadra)
			mosseConsentite.add((int)(x * 10 + y - 1));//aggiungo quella casella alle consentite
		//basso a destra (e fine)
		if(x < 7 && y > 0 && ChessboardModel.getPezzoInPosizione((byte)(x + 1), (byte)(y - 1)).squadra != squadra)
			mosseConsentite.add((int)((x + 1) * 10 + y - 1));//aggiungo quella casella alle consentite	
		return mosseConsentite;
	}

}
