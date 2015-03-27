package it.univr.chess.model;

import it.univr.chess.model.pieces.*;
import it.univr.chess.view.View;

public class ChessboardModel implements Model {
	
	private enum Step {
		STARTFROM, GOTO;
	}
	
	private final View view;
	private Piece chessboard[][] = new Piece[8][8];
	Team turn = Team.TEAM1;
	Step step = Step.STARTFROM;
	private int sx, sy, tx, ty; // start e target x e y
	
	public ChessboardModel(View view) {
		this.view = view;
		//schiero la prima squadra
		chessboard[0][0] = new Rook(Team.TEAM1);
		chessboard[1][0] = new Knight(Team.TEAM1);
		chessboard[2][0] = new Bishop(Team.TEAM1);
		chessboard[3][0] = new Queen(Team.TEAM1);
		chessboard[4][0] = new King(Team.TEAM1);
		chessboard[5][0] = new Bishop(Team.TEAM1);
		chessboard[6][0] = new Knight(Team.TEAM1);
		chessboard[7][0] = new Rook(Team.TEAM1);
		for (int i = 0; i < 8; i++)
			chessboard[i][1] = new Pawn(Team.TEAM1);
		
		//riempio la parte centrale della chessboard di vuoti
		for (int i = 0;i < 4; i++)
			for (int j = 0; j <= 7; j++)
				chessboard[j][i + 2] = null;
		
		//schiero la seconda squadra
		for (int i = 0; i < 8; i++)
			chessboard[i][6] = new Pawn(Team.TEAM2);
		chessboard[0][7] = new Rook(Team.TEAM2);
		chessboard[1][7] = new Knight(Team.TEAM2);
		chessboard[2][7] = new Bishop(Team.TEAM2);
		chessboard[3][7] = new Queen(Team.TEAM2);
		chessboard[4][7] = new King(Team.TEAM2);
		chessboard[5][7] = new Bishop(Team.TEAM2);
		chessboard[6][7] = new Knight(Team.TEAM2);
		chessboard[7][7] = new Rook(Team.TEAM2);
	}
	
	@Override
	public void coordinates(int x, int y) {
		
	}
	
	
	public Piece getPezzoInPosizione(int x, int y) {
		return chessboard[x][y];
	}
	
	public boolean setPezzoInPosizione(Piece pezzo, int x, int y) {
		chessboard[x][y] = pezzo;
		return true;
	}
	
	public CastlingPiece getCastlingRook(int x, int y){
		Piece piece = chessboard[x][y];
		return (piece instanceof Rook) ? (CastlingPiece) piece : null;
	}

}
