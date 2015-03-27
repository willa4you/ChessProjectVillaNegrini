package it.univr.chess.junit;

import org.junit.*;

import it.univr.chess.model.*;
import it.univr.chess.model.pieces.*;

import java.util.Scanner;

public class StalemateTest {

	@Test
	public void stalemateTest(){
		boolean condition = false;
		
		ChessboardModel2.nuovaPartita();//preparo la scacchiera per una nuova partita
		
		for(int i = 0; i < 8; i++)//la svuoto di ogni pezzo
			for(int j = 0; j < 8; j++)
				ChessboardModel2.setPezzoInPosizione(null, i, j);
		
		//configuro il caso
		ChessboardModel2.setPezzoInPosizione(new King(Team.TEAM1), 3, 2);
		ChessboardModel2.setPezzoInPosizione(new King(Team.TEAM2), 7, 7);
		ChessboardModel2.setPezzoInPosizione(new Queen(Team.TEAM1), 0, 1);
		
		//stampo a video la scacchiera
		System.out.println(ChessboardModel2.stringChessboard());
		System.out.println("Posiziono la regina in F7 per ottenere uno stallo.");
		System.out.print("Premi un tasto qualsiasi per continuare...");
		new Scanner(System.in).nextLine();
		Core.move(0, 1, 5, 6);
		//stampo a video la scacchiera
		System.out.println(ChessboardModel2.stringChessboard());
		Team player = Team.TEAM2;
		if(Core.check(player)) {
			if(Core.mate(player)){
				System.out.println("SCACCO MATTO, PARTITA FINITA!");
			}
			else System.out.println("ATTENZIONE, SEI SOTTO SCACCO!");
		}
		else if(Core.mate(player)){
			//Tecnicamente se ci fosse un matto in condizioni di non scacco si tratterebbe di STALLO
			System.out.println("STALLO, PARTITA FINITA!");
			condition = true;
		}
		
		Assert.assertTrue("ERRORE!", condition);
	}
	
}
