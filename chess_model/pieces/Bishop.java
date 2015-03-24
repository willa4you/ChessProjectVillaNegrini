package chess_model.pieces;

import java.util.ArrayList;

import chess_model.ChessboardModel;
import chess_model.Team;

public class Bishop extends Piece {

	public Bishop(Team team) {
		super(team);
	}
	
	@Override
	public Iterable<Integer> mosseConsentite(int x, int y) {
		ArrayList<Integer> mosseConsentite = new ArrayList<Integer>();
		
		int i = 1;
		Piece other = null;
		//vado in alto a destra e controllo se la casella in cui mi sposto ha senso e se non c'� qualcosa di team mia
		while (true) {
			if (x + i <= 7 && y + i <= 7 &&
					((other = ChessboardModel.getPezzoInPosizione(x + i, y + i)) == null || other.team != this.team)) {// o team avversaria o NULL vanno bene
				mosseConsentite.add((x + i) * 10 + (y + i));//aggiungo quella casella alle consentite
				if (other != null)//se per� c'� qualcuno so che non posso andare oltre e chiudo
					break;
				else
					i++;
			}
			else
				break;
		}
		
		i = 1;
		//vado in alto a sinistra e controllo se la casella in cui mi sposto ha senso e se non c'� qualcosa di team mia
		while (true) {
			if (x - i >= 0 && y + i <= 7 && 
					((other = ChessboardModel.getPezzoInPosizione(x - i, y + i)) == null || other.team != this.team)) {// o team avversaria o NULL vanno bene
				mosseConsentite.add((x - i) * 10 + (y + i));//aggiungo quella casella alle consentite
				if (other != null)//se per� c'� qualcuno so che non posso andare oltre e chiudo
					break;
				else
					i++;
			}
			else
				break;
		}
		
		i = 1;
		//vado in basso a sinistra e controllo se la casella in cui mi sposto ha senso e se non c'� qualcosa di team mia
		while (true) {
			if (x - i >= 0 && y - i >= 0 &&
					((other = ChessboardModel.getPezzoInPosizione(x - i, y - i)) == null || other.team != this.team)) {// o team avversaria o NULL vanno bene
				mosseConsentite.add((x - i) * 10 + (y - i));//aggiungo quella casella alle consentite
				if (other != null)//se per� c'� qualcuno so che non posso andare oltre e chiudo
					break;
				else
					i++;
			}
			else
				break;
		}
		
		i = 1;
		//vado in basso a destra e controllo se la casella in cui mi sposto ha senso e se non c'� qualcosa di team mia
		while (true) {
			if (x + i <= 7 && y - i >= 0 &&
					((other = ChessboardModel.getPezzoInPosizione(x + i, y - i)) == null || other.team != this.team)) {// o team avversaria o NULL vanno bene			
				mosseConsentite.add((x + i) * 10 + (y - i));//aggiungo quella casella alle consentite
				if (other != null)//se per� c'� qualcuno so che non posso andare oltre e chiudo
					break;
				else
					i++;
			}
			else
				break;
		}
		
		return mosseConsentite;
	}

	
}
