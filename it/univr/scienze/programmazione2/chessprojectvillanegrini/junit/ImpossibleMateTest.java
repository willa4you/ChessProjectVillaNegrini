package it.univr.scienze.programmazione2.chessprojectvillanegrini.junit;

import org.junit.*;

import it.univr.scienze.programmazione2.chessprojectvillanegrini.model.*;
import it.univr.scienze.programmazione2.chessprojectvillanegrini.model.pieces.*;
import it.univr.scienze.programmazione2.chessprojectvillanegrini.view.*;

public class ImpossibleMateTest {

	@Test
	public void impossibleMateTest(){
		
		ChessboardModel model = new ChessboardModel(new ChessboardView());
		Piece chessboard[][] = new Piece[8][8];
		
		chessboard[6][7] = new King(Team.TEAM2, model);
		chessboard[5][5] = new Bishop(Team.TEAM1, model);
		chessboard[0][2] = new King(Team.TEAM1, model);
		
		model.setChessboard(chessboard);
		
		Assert.assertTrue("ERROR!", model.impossibleMate());
		
	}
	
	@Test
	public void impossibleMateTest2(){
		
		ChessboardModel model = new ChessboardModel(new ChessboardView());
		Piece chessboard[][] = new Piece[8][8];
		
		chessboard[6][7] = new King(Team.TEAM2, model);
		chessboard[5][5] = new Knight(Team.TEAM1, model);
		chessboard[0][2] = new King(Team.TEAM1, model);
		
		model.setChessboard(chessboard);
		
		Assert.assertTrue("ERROR!", model.impossibleMate());
		
	}
	
	@Test
	public void impossibleMateTest3(){
		
		ChessboardModel model = new ChessboardModel(new ChessboardView());
		Piece chessboard[][] = new Piece[8][8];
		
		chessboard[6][7] = new King(Team.TEAM2, model);
		chessboard[0][2] = new King(Team.TEAM1, model);
		
		model.setChessboard(chessboard);
		
		Assert.assertTrue("ERROR!", model.impossibleMate());
		
	}
	
	@Test
	public void impossibleMateTest4(){
		
		ChessboardModel model = new ChessboardModel(new ChessboardView());
		Piece chessboard[][] = new Piece[8][8];
		
		chessboard[6][7] = new King(Team.TEAM2, model);
		chessboard[5][5] = new Knight(Team.TEAM1, model);
		chessboard[0][2] = new King(Team.TEAM1, model);
		chessboard[3][3] = new Bishop(Team.TEAM2, model);
		
		model.setChessboard(chessboard);
		
		Assert.assertFalse("ERROR!", model.impossibleMate());
		
	}
	
	@Test
	public void impossibleMateTest5(){
		
		ChessboardModel model = new ChessboardModel(new ChessboardView());
		Piece chessboard[][] = new Piece[8][8];
		
		chessboard[6][7] = new King(Team.TEAM2, model);
		chessboard[5][5] = new Knight(Team.TEAM1, model);
		chessboard[0][2] = new King(Team.TEAM1, model);
		chessboard[3][3] = new Bishop(Team.TEAM2, model);
		chessboard[3][4] = new Bishop(Team.TEAM2, model);
		
		model.setChessboard(chessboard);
		
		Assert.assertFalse("ERROR!", model.impossibleMate());
		
	}
	
	@Test
	public void impossibleMateTest6(){
		
		ChessboardModel model = new ChessboardModel(new ChessboardView());
		Piece chessboard[][] = new Piece[8][8];
		
		chessboard[6][7] = new King(Team.TEAM2, model);
		chessboard[5][5] = new Knight(Team.TEAM1, model);
		chessboard[0][2] = new King(Team.TEAM1, model);
		chessboard[3][3] = new Bishop(Team.TEAM2, model);
		chessboard[3][4] = new Queen(Team.TEAM2, model);
		
		model.setChessboard(chessboard);
		
		Assert.assertFalse("ERROR!", model.impossibleMate());
		
	}
}