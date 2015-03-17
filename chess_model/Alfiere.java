package chess_model;

import java.util.ArrayList;

public class Alfiere extends Pezzo {

	public Alfiere(int squadra){
		super(squadra);
	}
	
	@Override
	public Iterable<Integer> mosseConsentite(byte x, byte y) {
		ArrayList<Integer> mosseConsentite = new ArrayList<Integer>();
		
		byte i = 1;
		Squadra other = null;
		//vado in alto a destra e controllo se la casella in cui mi sposto ha senso e se non c'è qualcosa di squadra mia
		while(true){
			if (x + i <= 7 && y + i <= 7 && (other = Scacchiera_.getPezzoInPosizione((byte)(x + i), (byte)(y + i)).squadra) != squadra){// o squadra avversaria o NULL vanno bene
					mosseConsentite.add((int)((x+i)*10 + y + i));//aggiungo quella casella alle consentite
					if(other != null)//se però c'è qualcuno so che non posso andare oltre e chiudo
						break;
					else
						i++;
			}
			else break;
		}
		
		i = 1;
		//vado in alto a sinistra e controllo se la casella in cui mi sposto ha senso e se non c'è qualcosa di squadra mia
		while(true){
			if (x - i >= 0 && y + i <= 7 && (other = Scacchiera_.getPezzoInPosizione((byte)(x - i), (byte)(y + i)).squadra) != squadra){// o squadra avversaria o NULL vanno bene
					mosseConsentite.add((int)((x-i)*10 + y + i));//aggiungo quella casella alle consentite
					if(other != null)//se però c'è qualcuno so che non posso andare oltre e chiudo
						break;
					else
						i++;
			}
			else break;
		}
		
		i = 1;
		//vado in basso a sinistra e controllo se la casella in cui mi sposto ha senso e se non c'è qualcosa di squadra mia
		while(true){
			if (x - i >= 0 && y - i >= 0 && (other = Scacchiera_.getPezzoInPosizione((byte)(x - i), (byte)(y - i)).squadra) != squadra){// o squadra avversaria o NULL vanno bene
					mosseConsentite.add((int)((x-i)*10 + y - i));//aggiungo quella casella alle consentite
					if(other != null)//se però c'è qualcuno so che non posso andare oltre e chiudo
						break;
					else
						i++;
			}
			else break;
		}
		
		i = 1;
		//vado in basso a destra e controllo se la casella in cui mi sposto ha senso e se non c'è qualcosa di squadra mia
		while(true){
			if (x + i <= 7 && y - i >= 0 && (other = Scacchiera_.getPezzoInPosizione((byte)(x + i), (byte)(y - i)).squadra) != squadra){// o squadra avversaria o NULL vanno bene
					mosseConsentite.add((int)((x+i)*10 + y - i));//aggiungo quella casella alle consentite
					if(other != null)//se però c'è qualcuno so che non posso andare oltre e chiudo
						break;
					else
						i++;
			}
			else break;
		}
		
		return mosseConsentite;
	}

	
}
