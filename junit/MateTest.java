package junit;
import chess_model.*;
import chess_controller.PlayMatch;

import org.junit.*;

import java.util.Scanner;

public class MateTest {

	@Test
	public void mateTest(){
		ChessboardModel.nuovaPartita();//preparo la scacchiera per una nuova partita
		
		for(int i = 0; i < 8; i++)//la svuoto di ogni pezzo
			for(int j = 0; j < 8; j++)
				ChessboardModel.setPezzoInPosizione(null, i, j);
		
		//configuro il caso di scacco matto
		ChessboardModel.setPezzoInPosizione(new King(1), 2, 0);
		ChessboardModel.setPezzoInPosizione(new Knight(2), 2, 2);
		ChessboardModel.setPezzoInPosizione(new Rook(2), 7, 1);
		ChessboardModel.setPezzoInPosizione(new Bishop(2), 1, 2);
		
		
		boolean c = false;
		while(!c){
			//stampo a video la scacchiera
			System.out.println(ChessboardModel.stringChessboard());
			System.out.print("Scrivi la casella in cui vuoi muovere la torre (A2, C2, E2, F2, G2 per scacco matto): ");
			String arrivo = new Scanner(System.in).nextLine();
			int a = (int)(arrivo.charAt(0) - 'A')*10 + (Integer.parseInt(arrivo.substring(1, 2))-1);
			
			for (int b : ChessboardModel.getPezzoInPosizione(7, 1).mosseConsentite(7, 1)){
				System.out.print("Potrei andare in " + (char)('A' + b/10) + (b%10 + 1) + "...");
				if (b == a) {//la mossa che ho scelto (a) combacia con una delle possibili per il pezzo (b)
					System.out.println(" Match!");
					ChessboardModel.setPezzoInPosizione(ChessboardModel.getPezzoInPosizione(7, 1), a/10, a%10);
					ChessboardModel.setPezzoInPosizione(null, 7, 1);
					c = true;
				}
				else System.out.println(" Not match!");
			}//chiude for each
		}
		//stampo a video la scacchiera
		System.out.println(ChessboardModel.stringChessboard());
		
		Assert.assertTrue("NOT MATE!", PlayMatch.mate(Team.Team1));
		
	}
}
