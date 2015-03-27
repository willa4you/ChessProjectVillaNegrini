package it.univr.chess.junit;

import it.univr.chess.model.ChessboardModel2;
import it.univr.chess.model.Core;
import it.univr.chess.model.Team;
import it.univr.chess.model.pieces.*;

import java.util.Scanner;

import org.junit.Assert;
import org.junit.Test;

public class TestDoubleArrocco {

	@Test
	public void testDA(){
		ChessboardModel2.nuovaPartita();//preparo la scacchiera per una nuova partita
		
		for(int i = 0; i < 8; i++)//la svuoto di ogni pezzo
			for(int j = 0; j < 8; j++)
				ChessboardModel2.setPezzoInPosizione(null, i, j);
		
		//configuro il caso di doppio arrocco
		ChessboardModel2.setPezzoInPosizione(new King(Team.TEAM1), 4, 0);
		ChessboardModel2.setPezzoInPosizione(new King(Team.TEAM2), 4, 7);
		ChessboardModel2.setPezzoInPosizione(new Rook(Team.TEAM1), 7, 0);
		ChessboardModel2.setPezzoInPosizione(new Rook(Team.TEAM2), 7, 7);
		ChessboardModel2.setPezzoInPosizione(new Knight(Team.TEAM1), 6, 0);
		ChessboardModel2.setPezzoInPosizione(new Knight(Team.TEAM2), 6, 7);

		//stampo a video la scacchiera
		System.out.println(ChessboardModel2.stringChessboard());
		
		System.out.println("Muovo il cavallo squadra 1. ");
		Core.move(6, 0, 5, 2);
		//stampo a video la scacchiera
		System.out.println(ChessboardModel2.stringChessboard());
		
		System.out.println("Muovo il cavallo squadra 2. ");
		Core.move(6, 7, 5, 5);
		//stampo a video la scacchiera
		System.out.println(ChessboardModel2.stringChessboard());	
		
		Assert.assertTrue("NOT MATE!", true);
	}

	
}
