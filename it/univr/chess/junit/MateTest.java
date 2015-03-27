package it.univr.chess.junit;
import org.junit.*;

import it.univr.chess.model.*;
import it.univr.chess.model.pieces.Bishop;
import it.univr.chess.model.pieces.King;
import it.univr.chess.model.pieces.Knight;
import it.univr.chess.model.pieces.Rook;

import java.util.Scanner;

public class MateTest {

	@Test
	public void mateTest(){
		ChessboardModel2.nuovaPartita();//preparo la scacchiera per una nuova partita
		
		for(int i = 0; i < 8; i++)//la svuoto di ogni pezzo
			for(int j = 0; j < 8; j++)
				ChessboardModel2.setPezzoInPosizione(null, i, j);
		
		//configuro il caso di scacco matto
		ChessboardModel2.setPezzoInPosizione(new King(Team.TEAM1), 2, 0);
		ChessboardModel2.setPezzoInPosizione(new Knight(Team.TEAM2), 2, 2);
		ChessboardModel2.setPezzoInPosizione(new Rook(Team.TEAM2), 7, 1);
		ChessboardModel2.setPezzoInPosizione(new Bishop(Team.TEAM2), 1, 2);
		
		
		boolean c = false;
		while(!c){
			//stampo a video la scacchiera
			System.out.println(ChessboardModel2.stringChessboard());
			System.out.print("Scrivi la casella in cui vuoi muovere la torre (A2, C2, E2, F2, G2 per scacco matto): ");
			String arrivo = new Scanner(System.in).nextLine();
			int a = (int)(arrivo.charAt(0) - 'A')*10 + (Integer.parseInt(arrivo.substring(1, 2))-1);
			
			for (int b : ChessboardModel2.getPezzoInPosizione(7, 1).mosseConsentite(7, 1)){
				System.out.print("Potrei andare in " + (char)('A' + b/10) + (b%10 + 1) + "...");
				if (b == a) {//la mossa che ho scelto (a) combacia con una delle possibili per il pezzo (b)
					System.out.println(" Match!");
					ChessboardModel2.setPezzoInPosizione(ChessboardModel2.getPezzoInPosizione(7, 1), a/10, a%10);
					ChessboardModel2.setPezzoInPosizione(null, 7, 1);
					c = true;
				}
				else System.out.println(" Not match!");
			}//chiude for each
		}
		//stampo a video la scacchiera
		System.out.println(ChessboardModel2.stringChessboard());
		
		Assert.assertTrue("NOT MATE!", Core.mate(Team.TEAM1));
		
	}
}
