package chess_model;

import java.util.ArrayList;

public class Knight extends Piece {

	public Knight(int squadra) {
		super(squadra);
	}

	@Override
	public Iterable<Integer> mosseConsentite(byte x, byte y) {
		ArrayList<Integer> mosseConsentite = new ArrayList<Integer>();
		//prima mossa partendo da radianti 0 
		if(y < 7 && x < 6 && ChessboardModel.getPezzoInPosizione((byte)(x + 2), (byte)(y + 1)).squadra != squadra)
			mosseConsentite.add((int)((x + 2) * 10 + y + 1));//aggiungo quella casella alle consentite
		//seconda mossa partendo da radianti 0
		if(y < 6 && x < 7 && ChessboardModel.getPezzoInPosizione((byte)(x + 1), (byte)(y + 2)).squadra != squadra)
			mosseConsentite.add((int)((x + 1) * 10 + y + 2));//aggiungo quella casella alle consentite		
		//terza mossa partendo da radianti 0
		if(y < 6 && x > 0 && ChessboardModel.getPezzoInPosizione((byte)(x - 1), (byte)(y + 2)).squadra != squadra)
			mosseConsentite.add((int)((x - 1) * 10 + y + 2));//aggiungo quella casella alle consentite		
		//quarta mossa partendo da radianti 0
		if(y < 7 && x > 1 && ChessboardModel.getPezzoInPosizione((byte)(x - 2), (byte)(y + 1)).squadra != squadra)
			mosseConsentite.add((int)((x - 2) * 10 + y + 1));//aggiungo quella casella alle consentite			
		//quinta mossa partendo da radianti 0
		if(y > 0 && x > 1 && ChessboardModel.getPezzoInPosizione((byte)(x - 2), (byte)(y - 1)).squadra != squadra)
			mosseConsentite.add((int)((x - 2) * 10 + y - 1));//aggiungo quella casella alle consentite
		//sesta mossa partendo da radianti 0
		if(y > 1 && x > 0 && ChessboardModel.getPezzoInPosizione((byte)(x - 1), (byte)(y - 2)).squadra != squadra)
			mosseConsentite.add((int)((x - 1) * 10 + y - 2));//aggiungo quella casella alle consentite
		//settima mossa partendo da radianti 0
		if(y > 1 && x < 7 && ChessboardModel.getPezzoInPosizione((byte)(x + 1), (byte)(y - 2)).squadra != squadra)
			mosseConsentite.add((int)((x + 1) * 10 + y - 2));//aggiungo quella casella alle consentite
		//ottava mossa partendo da radianti 0 (e chiudendo la circonferenza)
		if(y > 0 && x < 6 && ChessboardModel.getPezzoInPosizione((byte)(x + 2), (byte)(y - 1)).squadra != squadra)
			mosseConsentite.add((int)((x + 2) * 10 + y - 1));//aggiungo quella casella alle consentite	
		return mosseConsentite;
	}


}
