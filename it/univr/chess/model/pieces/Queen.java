package it.univr.chess.model.pieces;

import it.univr.chess.model.Model;
import it.univr.chess.model.Team;

import java.util.ArrayList;

public class Queen extends Piece {

	public Queen(Team team, Model chessboard) {
		super(team, chessboard);
	}

	@Override
	public Iterable<Integer> mosseConsentite(int x, int y) {
		ArrayList<Integer> mosseConsentite = new ArrayList<Integer>();
		
		int i = 1;
		Piece other = null;
		//------------ INIZIO MOSSE STILE TORRE
		//vado verso destra e controllo se la colonna in cui mi sposto ha senso e se non c'� qualcosa di team mia
		while (true) {
			if (x + i <= 7 && ((other = chessboard.getPiece(x + i, y)) == null || other.team != this.team)) {// o team avversaria o NULL vanno bene
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
			if (y + i <= 7 && ((other = chessboard.getPiece(x, y + i)) == null || other.team != this.team)) {// o team avversaria o NULL vanno bene
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
			if (x - i >= 0 && ((other = chessboard.getPiece(x - i, y)) == null || other.team != this.team)) {// o team avversaria o NULL vanno bene
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
			if (y - i >= 0 && ((other = chessboard.getPiece(x, y - i)) == null || other.team != this.team)) {// o team avversaria o NULL vanno bene
					mosseConsentite.add(x * 10 + (y - i));//aggiungo quella casella alle consentite
					if (other != null)//se per� c'� qualcuno so che non posso andare oltre e chiudo
						break;
					else
						i++;
			}
			else
				break;
		}
		//------------ FINE MOSSE STILE TORRE
		//------------ INIZIO MOSSE STILE ALFIERE
		i = 1;
		//vado in alto a destra e controllo se la casella in cui mi sposto ha senso e se non c'� qualcosa di team mia
		while (true) {
			if (x + i <= 7 && y + i <= 7 &&
					((other = chessboard.getPiece(x + i, y + i)) == null || other.team != this.team)) {// o team avversaria o NULL vanno bene
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
					((other = chessboard.getPiece(x - i, y + i)) == null || other.team != this.team)) {// o team avversaria o NULL vanno bene
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
					((other = chessboard.getPiece(x - i, y - i)) == null || other.team != this.team)) {// o team avversaria o NULL vanno bene
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
					((other = chessboard.getPiece(x + i, y - i)) == null || other.team != this.team)) {// o team avversaria o NULL vanno bene			
				mosseConsentite.add((x + i) * 10 + (y - i));//aggiungo quella casella alle consentite
				if (other != null)//se per� c'� qualcuno so che non posso andare oltre e chiudo
					break;
				else
					i++;
			}
			else
				break;
		}
		
		//------FINE MOSSE STILE ALFIERE
		
		return mosseConsentite;
	}


}
