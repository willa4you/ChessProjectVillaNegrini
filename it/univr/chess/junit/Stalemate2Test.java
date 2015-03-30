/*package it.univr.chess.junit;

import it.univr.chess.model.ChessboardModel2;
import it.univr.chess.model.Core;
import it.univr.chess.model.Team;
import it.univr.chess.model.pieces.*;

import java.util.Scanner;

import org.junit.Assert;
import org.junit.Test;

public class Stalemate2Test {

	@Test
	public void stalemate2Test(){
		int choice = 0;
		
		System.out.print("Quale caso preferisci testare: 1- Re vs. re, 2- Re vs. Re & Cav., 3- Re vs. Re & Alf.:");
		choice = Integer.parseInt(new Scanner(System.in).nextLine());
		
		ChessboardModel2.nuovaPartita();//preparo la scacchiera per una nuova partita
		
		for(int i = 0; i < 8; i++)//la svuoto di ogni pezzo
			for(int j = 0; j < 8; j++)
				ChessboardModel2.setPezzoInPosizione(null, i, j);
		
		//configuro il caso
		ChessboardModel2.setPezzoInPosizione(new King(Team.TEAM1), 6, 0);
		ChessboardModel2.setPezzoInPosizione(new King(Team.TEAM2), 2, 7);
		ChessboardModel2.setPezzoInPosizione(new Rook(Team.TEAM2), 5, 1);
		
		if (choice == 2)
			ChessboardModel2.setPezzoInPosizione(new Knight(Team.TEAM2), 0, 2);
		if (choice == 3)
			ChessboardModel2.setPezzoInPosizione(new Bishop(Team.TEAM2), 0, 3);
		
		
		//stampo a video la scacchiera
		System.out.println(ChessboardModel2.stringChessboard());
		System.out.print("Muovi il re bianco: mangiando la torre avversaria causerai uno stallo.");
		String input = new Scanner(System.in).nextLine();
		int tx = (int)(input.charAt(0) - 'A');//estraggo la x della coordinata scelta
		int ty = Integer.parseInt(input.substring(1, 2)) - 1;//estraggo la y della coordinata scelta

		Core.move(6, 0, tx, ty);
		//stampo a video la scacchiera
		System.out.println(ChessboardModel2.stringChessboard());
		
		
		Assert.assertTrue("NESSUNO STALLO!", Core.staleMate());
	}
}
*/