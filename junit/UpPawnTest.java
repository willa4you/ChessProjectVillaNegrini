package junit;

import chess_model.*;
import chess_model.pieces.*;

import org.junit.*;
import java.util.Scanner;

public class UpPawnTest {

	@Test
	public void upPawnTest(){
		ChessboardModel.nuovaPartita();//preparo la scacchiera per una nuova partita
		
		for(int i = 0; i < 8; i++)//la svuoto di ogni pezzo
			for(int j = 0; j < 8; j++)
				ChessboardModel.setPezzoInPosizione(null, i, j);
		
		//configuro il caso
		ChessboardModel.setPezzoInPosizione(new Pawn(Team.Team2), 4, 1);
		ChessboardModel.setPezzoInPosizione(new Pawn(Team.Team1), 5, 6);
		
		Piece piece = null;
		boolean condition = false;
		
		System.out.print("Quale pezzo deve comparire dopo? 1- Regina; 2- Torre; 3- Alfiere; 4- Cavallo:");
		int after = Integer.parseInt(new Scanner(System.in).nextLine());
		//stampo a video la scacchiera
		System.out.println(ChessboardModel.stringChessboard());
		System.out.print("Quale squadra vuoi giocare (1 o 2): ");
		switch (Integer.parseInt(new Scanner(System.in).nextLine())){
		case 1:
			Core.move(5, 6, 5, 7);
			Core.upPawn(5, 7);
			piece = ChessboardModel.getPezzoInPosizione(5, 7);
			break;
		case 2:
			Core.move(4, 1, 4, 0);
			Core.upPawn(4, 0);
			piece = ChessboardModel.getPezzoInPosizione(4, 0);
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
		System.out.println(ChessboardModel.stringChessboard());
		
		Assert.assertTrue("ERRORE!", condition);
		
	}
}
