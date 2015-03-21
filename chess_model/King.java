package chess_model;

import java.util.ArrayList;

public class King extends Piece {

	public King(int team) {
		super(team);
	}

	@Override
	public Iterable<Integer> mosseConsentite(int x, int y) {
		ArrayList<Integer> mosseConsentite = new ArrayList<Integer>();
		Piece other = null;
		
		//destra
		if (x < 7 && (((other = ChessboardModel.getPezzoInPosizione(x + 1, y)) == null) || other.team != team))
			mosseConsentite.add((x + 1) * 10 + y);//aggiungo quella casella alle consentite
		//alto a destra
		if (x < 7 && y < 7 && (((other = ChessboardModel.getPezzoInPosizione(x + 1, y + 1)) == null) || other.team != team))
			mosseConsentite.add((x + 1) * 10 + (y + 1));//aggiungo quella casella alle consentite		
		//alto
		if (y < 7 && (((other = ChessboardModel.getPezzoInPosizione(x, y + 1)) == null) || other.team != team))
			mosseConsentite.add(x * 10 + (y + 1));//aggiungo quella casella alle consentite
		//alto a sinistra
		if (x > 0 && y < 7 && (((other = ChessboardModel.getPezzoInPosizione(x - 1, y + 1)) == null) || other.team != team))
			mosseConsentite.add((x - 1) * 10 + (y + 1));//aggiungo quella casella alle consentite	
		//sinistra
		if (x > 0 && (((other = ChessboardModel.getPezzoInPosizione(x - 1, y)) == null) || other.team != team))
			mosseConsentite.add((x - 1) * 10 + y);//aggiungo quella casella alle consentite	
		//basso a sinistra
		if (x > 0 && y > 0 && (((other = ChessboardModel.getPezzoInPosizione(x - 1, y - 1)) == null) || other.team != team))
			mosseConsentite.add((x - 1) * 10 + (y - 1));//aggiungo quella casella alle consentite
		//basso
		if (y > 0 && (((other = ChessboardModel.getPezzoInPosizione(x, y - 1)) == null) || other.team != team))
			mosseConsentite.add((x * 10) + (y - 1));//aggiungo quella casella alle consentite
		//basso a destra (e fine)
		if (x < 7 && y > 0  && (((other = ChessboardModel.getPezzoInPosizione(x + 1, y - 1)) == null) || other.team != team))
			mosseConsentite.add((x + 1) * 10 + (y - 1));//aggiungo quella casella alle consentite	
		
		return mosseConsentite;
	}

}
