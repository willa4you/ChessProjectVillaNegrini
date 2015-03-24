package junit;

import chess_model.*;
import chess_model.pieces.*;

import org.junit.*;

import java.util.Scanner;

public class StalemateTest {

	@Test
	public void stalemateTest(){
		boolean condition = false;
		
		ChessboardModel.nuovaPartita();//preparo la scacchiera per una nuova partita
		
		for(int i = 0; i < 8; i++)//la svuoto di ogni pezzo
			for(int j = 0; j < 8; j++)
				ChessboardModel.setPezzoInPosizione(null, i, j);
		
		//configuro il caso
		ChessboardModel.setPezzoInPosizione(new King(Team.Team1), 3, 2);
		ChessboardModel.setPezzoInPosizione(new King(Team.Team2), 7, 7);
		ChessboardModel.setPezzoInPosizione(new Queen(Team.Team1), 0, 1);
		
		//stampo a video la scacchiera
		System.out.println(ChessboardModel.stringChessboard());
		System.out.println("Posiziono la regina in F7 per ottenere uno stallo.");
		System.out.print("Premi un tasto qualsiasi per continuare...");
		new Scanner(System.in).nextLine();
		Core.move(0, 1, 5, 6);
		//stampo a video la scacchiera
		System.out.println(ChessboardModel.stringChessboard());
		Team player = Team.Team2;
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
