package progettoChess;

import java.util.ArrayList;

public class Torre extends Pezzo {

	public Torre(int squadra) {
		super(squadra);
	}

	@Override
	public Iterable<Integer> mosseConsentite(byte x, byte y) {
		ArrayList<Integer> mosseConsentite = new ArrayList<Integer>();
		
		byte i = 1;
		Squadra other = null;
		//vado verso destra e controllo se la colonna in cui mi sposto ha senso e se non c'è qualcosa di squadra mia
		while(true){
			if (x + i <= 7 && (other = Scacchiera.getPezzoInPosizione((byte)(x + i), y).squadra) != squadra){// o squadra avversaria o NULL vanno bene
					mosseConsentite.add((int)( (x+i)*10 + y ));//aggiungo quella casella alle consentite
					if(other != null)//se però c'è qualcuno so che non posso andare oltre e chiudo
						break;
					else
						i++;
			}
			else break;
		}
		
		i = 1;
		//vado verso l'alto e controllo se la riga in cui mi sposto ha senso e se non c'è qualcosa di squadra mia
		while(true){
			if (y + i <= 7 && (other = Scacchiera.getPezzoInPosizione(x, (byte)(y + i)).squadra) != squadra){// o squadra avversaria o NULL vanno bene
					mosseConsentite.add((int)(x*10 + y + i));//aggiungo quella casella alle consentite
					if(other != null)//se però c'è qualcuno so che non posso andare oltre e chiudo
						break;
					else
						i++;
			}
			else break;
		}
		
		i = 1;
		//vado verso sinistra e controllo se la colonna in cui mi sposto ha senso e se non c'è qualcosa di squadra mia
		while(true){
			if (x - i >= 0 && (other = Scacchiera.getPezzoInPosizione((byte)(x - i), y).squadra) != squadra){// o squadra avversaria o NULL vanno bene
					mosseConsentite.add((int)((x-i)*10 + y));//aggiungo quella casella alle consentite
					if(other != null)//se però c'è qualcuno so che non posso andare oltre e chiudo
						break;
					else
						i++;
			}
			else break;
		}
		
		i = 1;
		//vado verso il basso e controllo se la riga in cui mi sposto ha senso e se non c'è qualcosa di squadra mia
		while(true){
			if (y - i >= 0 && (other = Scacchiera.getPezzoInPosizione(x, (byte)(y - i)).squadra) != squadra){// o squadra avversaria o NULL vanno bene
					mosseConsentite.add((int)(x*10 + (y-i)));//aggiungo quella casella alle consentite
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
