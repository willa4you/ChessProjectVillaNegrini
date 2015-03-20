package chess_model;

import java.util.ArrayList;

public class Rook extends Piece {

	public Rook(int team) {
		super(team);
	}

	@Override
	public Iterable<Integer> mosseConsentite(byte x, byte y) {
		ArrayList<Integer> mosseConsentite = new ArrayList<Integer>();
		
		byte i = 1;
		Piece other = null;
		//vado verso destra e controllo se la colonna in cui mi sposto ha senso e se non c'� qualcosa di team mia
		while(true){
			if (x + i <= 7 && ((other = ChessboardModel.getPezzoInPosizione((byte)(x + i), y)) == null || other.team != this.team)){// o team avversaria o NULL vanno bene
					mosseConsentite.add((int)( (x+i)*10 + y ));//aggiungo quella casella alle consentite
					if(other != null)//se per� c'� qualcuno so che non posso andare oltre e chiudo
						break;
					else
						i++;
			}
			else break;
		}
		
		i = 1;
		//vado verso l'alto e controllo se la riga in cui mi sposto ha senso e se non c'� qualcosa di team mia
		while(true){
			if (y + i <= 7 && ((other = ChessboardModel.getPezzoInPosizione(x, (byte)(y + i))) == null || other.team != this.team)){// o team avversaria o NULL vanno bene
					mosseConsentite.add((int)(x*10 + y + i));//aggiungo quella casella alle consentite
					if(other != null)//se per� c'� qualcuno so che non posso andare oltre e chiudo
						break;
					else
						i++;
			}
			else break;
		}
		
		i = 1;
		//vado verso sinistra e controllo se la colonna in cui mi sposto ha senso e se non c'� qualcosa di team mia
		while(true){
			if (x - i >= 0 && ((other = ChessboardModel.getPezzoInPosizione((byte)(x - i), y)) == null || other.team != this.team)){// o team avversaria o NULL vanno bene
					mosseConsentite.add((int)((x-i)*10 + y));//aggiungo quella casella alle consentite
					if(other != null)//se per� c'� qualcuno so che non posso andare oltre e chiudo
						break;
					else
						i++;
			}
			else break;
		}
		
		i = 1;
		//vado verso il basso e controllo se la riga in cui mi sposto ha senso e se non c'� qualcosa di team mia
		while(true){
			if (y - i >= 0 && ((other = ChessboardModel.getPezzoInPosizione(x, (byte)(y - i))) == null || other.team != this.team)){// o team avversaria o NULL vanno bene
					mosseConsentite.add((int)(x*10 + (y-i)));//aggiungo quella casella alle consentite
					if(other != null)//se per� c'� qualcuno so che non posso andare oltre e chiudo
						break;
					else
						i++;
			}
			else break;
		}
	
		return mosseConsentite;
	}

}
