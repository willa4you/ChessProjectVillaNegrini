package junit;

import java.util.Scanner;

import org.junit.Assert;
import org.junit.Test;

import chess_model.ChessboardModel;
import chess_model.Core;
import chess_model.Team;
import chess_model.pieces.*;

public class Stalemate2Test {

	@Test
	public void stalemate2Test(){
		int choice = 0;
		
		System.out.print("Quale caso preferisci testare: 1- Re vs. re, 2- Re vs. Re & Cav., 3- Re vs. Re & Alf.:");
		choice = Integer.parseInt(new Scanner(System.in).nextLine());
		
		ChessboardModel.nuovaPartita();//preparo la scacchiera per una nuova partita
		
		for(int i = 0; i < 8; i++)//la svuoto di ogni pezzo
			for(int j = 0; j < 8; j++)
				ChessboardModel.setPezzoInPosizione(null, i, j);
		
		//configuro il caso
		ChessboardModel.setPezzoInPosizione(new King(Team.Team1), 6, 0);
		ChessboardModel.setPezzoInPosizione(new King(Team.Team2), 2, 7);
		ChessboardModel.setPezzoInPosizione(new Rook(Team.Team2), 5, 1);
		
		if (choice == 2)
			ChessboardModel.setPezzoInPosizione(new Knight(Team.Team2), 0, 2);
		if (choice == 3)
			ChessboardModel.setPezzoInPosizione(new Bishop(Team.Team2), 0, 3);
		
		
		//stampo a video la scacchiera
		System.out.println(ChessboardModel.stringChessboard());
		System.out.print("Muovi il re bianco: mangiando la torre avversaria causerai uno stallo.");
		String input = new Scanner(System.in).nextLine();
		int tx = (int)(input.charAt(0) - 'A');//estraggo la x della coordinata scelta
		int ty = Integer.parseInt(input.substring(1, 2)) - 1;//estraggo la y della coordinata scelta

		Core.move(6, 0, tx, ty);
		//stampo a video la scacchiera
		System.out.println(ChessboardModel.stringChessboard());
		
		
		Assert.assertTrue("NESSUNO STALLO!", Core.staleMate());
	}
}
