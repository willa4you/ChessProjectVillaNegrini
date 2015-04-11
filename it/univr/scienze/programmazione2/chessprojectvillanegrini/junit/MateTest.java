package it.univr.scienze.programmazione2.chessprojectvillanegrini.junit;

import org.junit.*;

import it.univr.scienze.programmazione2.chessprojectvillanegrini.model.*;
import it.univr.scienze.programmazione2.chessprojectvillanegrini.model.pieces.*;
import it.univr.scienze.programmazione2.chessprojectvillanegrini.view.*;

public class MateTest {

	@Test
	public void mateTest(){
		
		ChessboardModel model = new ChessboardModel(new ChessboardView());
		Piece chessboard[][] = new Piece[8][8];
		
		chessboard[0][7] = new King(Team.TEAM2, model);
		chessboard[2][5] = new Bishop(Team.TEAM1, model);
		chessboard[4][4] = new Bishop(Team.TEAM1, model);
		chessboard[0][5] = new King(Team.TEAM1, model);
		
		model.setChessboard(chessboard);
		
		Assert.assertTrue("ERROR!", (model.check(Team.TEAM2) && model.mate(Team.TEAM2)));
		
	}
	
	@Test
	public void mateTest2(){
		
		ChessboardModel model = new ChessboardModel(new ChessboardView());
		Piece chessboard[][] = new Piece[8][8];
		
		chessboard[0][7] = new King(Team.TEAM2, model);
		chessboard[2][5] = new Bishop(Team.TEAM1, model);
		chessboard[4][4] = new Bishop(Team.TEAM1, model);
		chessboard[0][4] = new King(Team.TEAM1, model);
		
		model.setChessboard(chessboard);
		
		Assert.assertFalse("ERROR!", (model.check(Team.TEAM2) && model.mate(Team.TEAM2)));
		
	}
}