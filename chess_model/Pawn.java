package chess_model;

import java.util.ArrayList;

public class Pawn extends Piece {

	public Pawn(int team) {
		super(team);
	}

	@Override
	public Iterable<Integer> mosseConsentite(int x, int y) {
		ArrayList<Integer> mosseConsentite = new ArrayList<Integer>();
		Piece other = null;
		
		if (this.team == Team.Team1){//team1 va verso l'alto
			
			//per muovermi in alto a destra non devo uscire e ci dev'essere un avversario
			if(x < 7 && y < 7 && (other = ChessboardModel.getPezzoInPosizione(x + 1, y + 1)) != null && other.team != team)
				mosseConsentite.add((x + 1) * 10 + (y + 1));//aggiungo quella casella alle consentite
			
			//per muovermi in alto non devo uscire e dev'essere casella vuota
			if(y < 7 && ChessboardModel.getPezzoInPosizione(x, y + 1) == null)
				mosseConsentite.add(x * 10 + (y + 1));//aggiungo quella casella alle consentite
			
			//per muovermi in alto a sinistra non devo uscire e ci dev'essere un avversario
			if(x > 0 && y < 7 && (other = ChessboardModel.getPezzoInPosizione(x - 1, y + 1)) !=null && other.team != team)
				mosseConsentite.add((x - 1) * 10 + (y + 1));//aggiungo quella casella alle consentite
		} 
		else {//team2 va verso il basso
			
			//per muovermi in basso a destra non devo uscire e ci dev'essere un avversario
			if(x < 7 && y > 0 && (other = ChessboardModel.getPezzoInPosizione(x + 1, y + 1)) != null && other.team != team)
				mosseConsentite.add((x + 1) * 10 + (y - 1));//aggiungo quella casella alle consentite
			
			//per muovermi in basso non devo uscire e dev'essere casella vuota
			if(y > 0 && ChessboardModel.getPezzoInPosizione(x, y - 1) == null)
				mosseConsentite.add(x * 10 + (y - 1));//aggiungo quella casella alle consentite
			
			//per muovermi in basso a sinistra non devo uscire e ci dev'essere un avversario
			if(x > 0 && y > 0 && (other = ChessboardModel.getPezzoInPosizione(x - 1, y - 1)) !=null && other.team != team)
				mosseConsentite.add((x - 1) * 10 + (y - 1));//aggiungo quella casella alle consentite
		}
		
		return mosseConsentite;
	}


}
