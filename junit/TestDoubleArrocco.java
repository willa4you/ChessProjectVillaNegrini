package junit;

import java.util.Scanner;

import org.junit.Assert;
import org.junit.Test;

import chess_model.ChessboardModel;
import chess_model.Core;
import chess_model.Team;
import chess_model.pieces.*;

public class TestDoubleArrocco {

	@Test
	public void testDA(){
		ChessboardModel.nuovaPartita();//preparo la scacchiera per una nuova partita
		
		for(int i = 0; i < 8; i++)//la svuoto di ogni pezzo
			for(int j = 0; j < 8; j++)
				ChessboardModel.setPezzoInPosizione(null, i, j);
		
		//configuro il caso di doppio arrocco
		ChessboardModel.setPezzoInPosizione(new King(Team.Team1), 4, 0);
		ChessboardModel.setPezzoInPosizione(new King(Team.Team2), 4, 7);
		ChessboardModel.setPezzoInPosizione(new Rook(Team.Team1), 7, 0);
		ChessboardModel.setPezzoInPosizione(new Rook(Team.Team2), 7, 7);
		ChessboardModel.setPezzoInPosizione(new Knight(Team.Team1), 6, 0);
		ChessboardModel.setPezzoInPosizione(new Knight(Team.Team2), 6, 7);

		//stampo a video la scacchiera
		System.out.println(ChessboardModel.stringChessboard());
		
		System.out.println("Muovo il cavallo squadra 1. ");
		Core.move(6, 0, 5, 2);
		//stampo a video la scacchiera
		System.out.println(ChessboardModel.stringChessboard());
		
		System.out.println("Muovo il cavallo squadra 2. ");
		Core.move(6, 7, 5, 5);
		//stampo a video la scacchiera
		System.out.println(ChessboardModel.stringChessboard());	
		
		Assert.assertTrue("NOT MATE!", true);
	}

	
}
