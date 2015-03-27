package it.univr.chess.junit;

import it.univr.chess.model.*;
import it.univr.chess.model.pieces.*;

import org.junit.*;
import java.util.Scanner;

public class UpPawnTest {

	@Test
	public void upPawnTest(){
		ChessboardModel2.nuovaPartita();//preparo la scacchiera per una nuova partita
		
		for(int i = 0; i < 8; i++)//la svuoto di ogni pezzo
			for(int j = 0; j < 8; j++)
				ChessboardModel2.setPezzoInPosizione(null, i, j);
		
		//configuro il caso
		ChessboardModel2.setPezzoInPosizione(new Pawn(Team.TEAM2), 4, 1);
		ChessboardModel2.setPezzoInPosizione(new Pawn(Team.TEAM1), 5, 6);
		
		Piece piece = null;
		boolean condition = false;
		
		System.out.print("Quale pezzo deve comparire dopo? 1- Regina; 2- Torre; 3- Alfiere; 4- Cavallo:");
		int after = Integer.parseInt(new Scanner(System.in).nextLine());
		//stampo a video la scacchiera
		System.out.println(ChessboardModel2.stringChessboard());
		System.out.print("Quale squadra vuoi giocare (1 o 2): ");
		switch (Integer.parseInt(new Scanner(System.in).nextLine())){
		case 1:
			Core.move(5, 6, 5, 7);
			piece = ChessboardModel2.getPezzoInPosizione(5, 7);
			break;
		case 2:
			Core.move(4, 1, 4, 0);
			piece = ChessboardModel2.getPezzoInPosizione(4, 0);
			break;
		}
		switch (after) {
		case 1:
			if (piece instanceof Queen) condition = true;
			break;
		case 2:
			if (piece instanceof Rook) condition = true;
			break;
		case 3:
			if (piece instanceof Bishop) condition = true;
			break;
		case 4:
			if (piece instanceof Knight) condition = true;
			break;
		}
		//stampo a video la scacchiera
		System.out.println(ChessboardModel2.stringChessboard());
		
		Assert.assertTrue("ERRORE!", condition);
		
	}
}
