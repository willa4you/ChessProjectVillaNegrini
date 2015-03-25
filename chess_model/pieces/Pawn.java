package chess_model.pieces;

import java.util.ArrayList;

import chess_model.ChessboardModel;
import chess_model.Team;

public class Pawn extends Piece {
	private boolean enpassant; //comunica di essere mangiabile enpassant
	
	public Pawn(Team team) {
		super(team);
		enpassant = false;
	}

	@Override
	public Iterable<Integer> mosseConsentite(int x, int y) {
		ArrayList<Integer> mosseConsentite = new ArrayList<Integer>();
		Piece other = null;
		
		if (this.team == Team.Team1) {//team1 va verso l'alto
			
			//per muovermi in alto a destra non devo uscire e (o c'è un avversario, o è vuoto e a destra ho un pedone che è mangiabile enpassant)
			if (x < 7 && y < 7 &&
					( ((other = ChessboardModel.getPezzoInPosizione(x + 1, y + 1)) != null && other.team != team) || enpassant(x, y, true) ))
				mosseConsentite.add((x + 1) * 10 + (y + 1));//aggiungo quella casella alle consentite
			
			//per muovermi in alto non devo uscire e dev'essere casella vuota
			if (y < 7 && ChessboardModel.getPezzoInPosizione(x, y + 1) == null)
				mosseConsentite.add(x * 10 + (y + 1));//aggiungo quella casella alle consentite
			
			//per muovermi in alto di due passi devo essere nella riga di partenza e mi devono seguire due caselle vuote
			if(y == 1 && ChessboardModel.getPezzoInPosizione(x, y + 1) == null && ChessboardModel.getPezzoInPosizione(x, y + 2) == null)
				mosseConsentite.add(x * 10 + (y + 2));//aggiungo quella casella alle consentite
			
			//per muovermi in alto a sinistra non devo uscire e (o c'è un avversario, o è vuoto e a sinistra ho un pedone che è mangiabile enpassant)
			if (x > 0 && y < 7 && 
					( ((other = ChessboardModel.getPezzoInPosizione(x - 1, y + 1)) != null && other.team != team) || enpassant(x, y, false) ))
				mosseConsentite.add((x - 1) * 10 + (y + 1));//aggiungo quella casella alle consentite
		} 
		else {//team2 va verso il basso
			
			//per muovermi in basso a destra non devo uscire e (o c'è un avversario, o è vuoto e a destra ho un pedone che è mangiabile enpassant)
			if (x < 7 && y > 0 && 
					( ((other = ChessboardModel.getPezzoInPosizione(x + 1, y - 1)) != null && other.team != team) || enpassant(x, y, true) ))
				mosseConsentite.add((x + 1) * 10 + (y - 1));//aggiungo quella casella alle consentite
			
			//per muovermi in basso non devo uscire e dev'essere casella vuota
			if (y > 0 && ChessboardModel.getPezzoInPosizione(x, y - 1) == null)
				mosseConsentite.add(x * 10 + (y - 1));//aggiungo quella casella alle consentite
			
			//per muovermi in basso di due passi devo essere nella riga di partenza e mi devono seguire due caselle vuote
			if(y == 6 && ChessboardModel.getPezzoInPosizione(x, y - 1) == null && ChessboardModel.getPezzoInPosizione(x, y - 2) == null)
				mosseConsentite.add(x * 10 + (y - 2));//aggiungo quella casella alle consentite
			
			//per muovermi in basso a sinistra non devo uscire e (o c'è un avversario, o è vuoto e a destra ho un pedone che è mangiabile enpassant)
			if (x > 0 && y > 0 &&
					( ((other = ChessboardModel.getPezzoInPosizione(x - 1, y - 1)) != null && other.team != team) || enpassant(x, y, false) ))
				mosseConsentite.add((x - 1) * 10 + (y - 1));//aggiungo quella casella alle consentite
		}
		
		return mosseConsentite;
	}
	
	private boolean enpassant(int x, int y, boolean right){
		//attenzione: il fatto che a fianco abbia un pedone in stato di enpassant
		//e' garanzia del fatto che la casella in cui mi posso muovere in diagonale
		//sia vuota: infatti se il pedone a fianco e' in enpassant, ha appena mosso di due caselle
		//e per le regole del gioco deve averle attraversate entrambe da vuote
		int move = (right) ? 1 : -1; //setto se devo controllare +1 o -1 sulle x (destra o sinistra)
		Piece other;
		//controllo se a fianco ho un pawn e se e' in enpassant: non verifico che sia avversario
		//perche' l'enpassant dura un turno e se fosse mio il turno precedente dell'avversario
		//avrebbe rimosso il suo stato di enpassant
		if (((other = ChessboardModel.getPezzoInPosizione(x + move, y)) instanceof Pawn) && ((Pawn)other).enpassant)
			return true;
		else
			return false;
	}

	public boolean getEnpassant(){
		return this.enpassant;
	}
	
	public void setEnpassant(boolean enpassant){
		this.enpassant = enpassant;
	}
}
