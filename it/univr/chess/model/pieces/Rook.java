package it.univr.chess.model.pieces;

import it.univr.chess.model.ChessboardModel2;
import it.univr.chess.model.Team;

import java.util.ArrayList;

public class Rook extends CastlingPiece {
	
	public Rook(Team team) {
		super(team);
	}

	@Override
	public Iterable<Integer> mosseConsentite(int x, int y) {
		ArrayList<Integer> mosseConsentite = new ArrayList<Integer>();
		
		int i = 1;
		Piece other = null;
		//vado verso destra e controllo se la colonna in cui mi sposto ha senso e se non c'� qualcosa di team mia
		while (true) {
			if (x + i <= 7 && ((other = ChessboardModel2.getPezzoInPosizione(x + i, y)) == null || other.team != this.team)) {// o team avversaria o NULL vanno bene
					mosseConsentite.add((x + i) * 10 + y);//aggiungo quella casella alle consentite
					if (other != null)//se per� c'� qualcuno so che non posso andare oltre e chiudo
						break;
					else
						i++;
			}
			else
				break;
		}
		
		i = 1;
		//vado verso l'alto e controllo se la riga in cui mi sposto ha senso e se non c'� qualcosa di team mia
		while (true) {
			if (y + i <= 7 && ((other = ChessboardModel2.getPezzoInPosizione(x, y + i)) == null || other.team != this.team)) {// o team avversaria o NULL vanno bene
					mosseConsentite.add((x * 10 + (y + i)));//aggiungo quella casella alle consentite
					if (other != null)//se per� c'� qualcuno so che non posso andare oltre e chiudo
						break;
					else
						i++;
			}
			else
				break;
		}
		
		i = 1;
		//vado verso sinistra e controllo se la colonna in cui mi sposto ha senso e se non c'� qualcosa di team mia
		while (true) {
			if (x - i >= 0 && ((other = ChessboardModel2.getPezzoInPosizione(x - i, y)) == null || other.team != this.team)) {// o team avversaria o NULL vanno bene
					mosseConsentite.add((x - i) * 10 + y);//aggiungo quella casella alle consentite
					if (other != null)//se per� c'� qualcuno so che non posso andare oltre e chiudo
						break;
					else
						i++;
			}
			else
				break;
		}
		
		i = 1;
		//vado verso il basso e controllo se la riga in cui mi sposto ha senso e se non c'� qualcosa di team mia
		while (true) {
			if (y - i >= 0 && ((other = ChessboardModel2.getPezzoInPosizione(x, y - i)) == null || other.team != this.team)) {// o team avversaria o NULL vanno bene
					mosseConsentite.add(x * 10 + (y - i));//aggiungo quella casella alle consentite
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
