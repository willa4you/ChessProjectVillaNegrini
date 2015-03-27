package it.univr.chess.model.pieces;

import it.univr.chess.model.ChessboardModel2;
import it.univr.chess.model.Core;
import it.univr.chess.model.Team;

import java.util.ArrayList;

public class King extends CastlingPiece {

	public King(Team team) {
		super(team);
	}

	@Override
	public Iterable<Integer> mosseConsentite(int x, int y) {
		ArrayList<Integer> mosseConsentite = new ArrayList<Integer>();
		Piece other = null;
		
		//destra
		if (x < 7 && (((other = ChessboardModel2.getPezzoInPosizione(x + 1, y)) == null) || other.team != team))
			mosseConsentite.add((x + 1) * 10 + y);//aggiungo quella casella alle consentite
		//destra arrocco corto
		if (castling(y, true))
			mosseConsentite.add((x + 2) * 10 + y);//aggiungo quella casella alle consentite
			//alto a destra
		if (x < 7 && y < 7 && (((other = ChessboardModel2.getPezzoInPosizione(x + 1, y + 1)) == null) || other.team != team))
			mosseConsentite.add((x + 1) * 10 + (y + 1));//aggiungo quella casella alle consentite		
		//alto
		if (y < 7 && (((other = ChessboardModel2.getPezzoInPosizione(x, y + 1)) == null) || other.team != team))
			mosseConsentite.add(x * 10 + (y + 1));//aggiungo quella casella alle consentite
		//alto a sinistra
		if (x > 0 && y < 7 && (((other = ChessboardModel2.getPezzoInPosizione(x - 1, y + 1)) == null) || other.team != team))
			mosseConsentite.add((x - 1) * 10 + (y + 1));//aggiungo quella casella alle consentite	
		//sinistra
		if (x > 0 && (((other = ChessboardModel2.getPezzoInPosizione(x - 1, y)) == null) || other.team != team))
			mosseConsentite.add((x - 1) * 10 + y);//aggiungo quella casella alle consentite	
		//sinistra arrocco lungo
		if (castling(y, false))
			mosseConsentite.add((x - 2) * 10 + y);//aggiungo quella casella alle consentite		
		//basso a sinistra
		if (x > 0 && y > 0 && (((other = ChessboardModel2.getPezzoInPosizione(x - 1, y - 1)) == null) || other.team != team))
			mosseConsentite.add((x - 1) * 10 + (y - 1));//aggiungo quella casella alle consentite
		//basso
		if (y > 0 && (((other = ChessboardModel2.getPezzoInPosizione(x, y - 1)) == null) || other.team != team))
			mosseConsentite.add((x * 10) + (y - 1));//aggiungo quella casella alle consentite
		//basso a destra (e fine)
		if (x < 7 && y > 0  && (((other = ChessboardModel2.getPezzoInPosizione(x + 1, y - 1)) == null) || other.team != team))
			mosseConsentite.add((x + 1) * 10 + (y - 1));//aggiungo quella casella alle consentite	
		
		return mosseConsentite;
	}

	private boolean castling(int y, boolean shortCastling){
		int firstPlace, secondPlace, rookPlace;
		
		if(shortCastling){
			firstPlace = 5;
			secondPlace = 6;
			rookPlace = 7;
		}
		else {
			firstPlace = 3;
			secondPlace = 2;
			rookPlace = 0;
		}
		
		//i controlli di un arrocco a livello di pezzo non sono esaustivi
		//i controlli sulla presenza di uno scacco eventuale vengono svolti dal core
		//primo controllo: se ho mosso il re una volta, non potr� mai pi� fare arrocco
		if (this.moved)
			return false;
		
		//secondo controllo: devono essere vuote le due caselle alla mia sinistra
		if (ChessboardModel2.getPezzoInPosizione(firstPlace, y) != null)
			return false;
		if (ChessboardModel2.getPezzoInPosizione(secondPlace, y) != null)
			return false;
		
		//terzo controllo: dev'esserci la torre e non deve aver mai mosso
		Piece piece = ChessboardModel2.getPezzoInPosizione(rookPlace, y);
		if (piece instanceof Rook && !(((CastlingPiece) piece).getMoved()))
			return true;
		else
			return false;
	}
	
}
