package it.univr.chess.junit;

import org.junit.*;

import it.univr.chess.model.*;
import it.univr.chess.model.pieces.*;
import it.univr.chess.view.*;

public class StalemateTest {

	@Test
	public void stalemateTest(){
		
		ChessboardModel model = new ChessboardModel(new ChessboardView());
		Piece chessboard[][] = new Piece[8][8];
		
		chessboard[0][0] = new King(Team.TEAM2, model);
		chessboard[5][6] = new Queen(Team.TEAM2, model);
		chessboard[7][7] = new King(Team.TEAM1, model);
		
		model.setChessboard(chessboard);
		
		Assert.assertTrue("ERROR!", (!model.check(Team.TEAM1) && model.mate(Team.TEAM1)));
		
	}
	
	@Test
	public void stalmateTest2(){
		
		ChessboardModel model = new ChessboardModel(new ChessboardView());
		Piece chessboard[][] = new Piece[8][8];
		
		chessboard[0][0] = new King(Team.TEAM2, model);
		chessboard[0][6] = new Queen(Team.TEAM2, model);
		chessboard[7][7] = new King(Team.TEAM1, model);
		
		model.setChessboard(chessboard);
		
		Assert.assertFalse("ERROR!", (!model.check(Team.TEAM1) && model.mate(Team.TEAM1)));
		
	}
}