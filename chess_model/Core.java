package chess_model;

import java.util.Scanner;
import java.util.ArrayList;

import chess_model.pieces.*;

public class Core {

	public static boolean check(Team player){
		Piece piece = null;
		Team otherPlayer;
		otherPlayer = (player == Team.Team1) ? Team.Team2 : Team.Team1;
		
		for(int i = 0; i < 8; i++)
			for(int j = 0; j < 8; j++)
				if((piece = ChessboardModel.getPezzoInPosizione(i, j)) != null && piece.getTeam() == otherPlayer && piece.check(i, j))
					return true;
		
		return false;
	}
	
	public static boolean mate(Team player){
		Piece piece = null;
		Piece tmp = null;
		boolean check = true;
		
		for(int i = 0; i < 8; i++)
			for(int j = 0; j < 8; j++) {
				piece = ChessboardModel.getPezzoInPosizione(i, j);
				if (piece != null && piece.getTeam() == player)
					for (int mc : piece.mosseConsentite(i, j)){
						tmp = ChessboardModel.getPezzoInPosizione(mc/10, mc%10);//la eseguo ma tengo l'eventuale pezzo mangiato in tmp
						ChessboardModel.setPezzoInPosizione(piece, mc/10, mc%10);
						ChessboardModel.setPezzoInPosizione(null, i, j);
						check = check(player);
						ChessboardModel.setPezzoInPosizione(piece, i, j);//rimetto piece dov'era
						ChessboardModel.setPezzoInPosizione(tmp, mc/10, mc%10);//metto tmp dov'era
						if(!check) return false;
					}
			}
		return true;
	}
	
	public static Team getTeam(int x, int y){
		Piece piece = ChessboardModel.getPezzoInPosizione(x, y);
		return (piece == null) ? null : piece.getTeam();
	}
	
	public static Iterable<Integer> availableMoves(int x, int y){
		Piece piece = ChessboardModel.getPezzoInPosizione(x, y);
		Piece tmp = null;
		ArrayList<Integer> availableMoves = new ArrayList<Integer>();
		
		int tx, ty; // coordinate delle caselle target delle mosse consentite
		boolean condition = false; //variabile temporanea dove salvo una condizione
		System.out.print("Pezzo: 'Potrei andare in ");
		for (int available : piece.mosseConsentite(x, y)){
			tx = available/10;
			ty = available%10;
			System.out.print(""+(char)('A' + tx) + (ty + 1) + ", ");
			//primi controlli: arrocco (se il pezzo è Re e la x si sposta di 2)
			if (piece instanceof King && (x == tx + 2 || x == tx - 2)) {
				//se mi viene suggerito un arrocco, le prime condizioni sono superate a livello pezzo
				//manca il test se sono sotto scacco ora oppure sono sotto scacco nella casella di passaggio
				//(il test di non dover essere sotto scacco nella casella di arrivo è lasciato alla fine perché si fa comunque)
				if (Core.check(piece.getTeam())) //se sono sotto scacco ora non aggiungo la mossa alle consentite
					continue;
				if (x == tx + 2){//se mi sta venendo suggerito un arrocco sinistro
					ChessboardModel.setPezzoInPosizione(piece, x - 1, y);
					ChessboardModel.setPezzoInPosizione(null, x, y);
					condition = Core.check(piece.getTeam()); //guardo se nella casella di passaggio il re è sotto scacco e lo salvo in condition
					ChessboardModel.setPezzoInPosizione(null, x - 1, y);
					ChessboardModel.setPezzoInPosizione(piece, x, y); //rimetto il re dov'era
					if(condition) //se il re è sotto scacco nella casella di transizione passo a un'altra mossa
						continue;
				}
				else if (x == tx - 2){//se mi sta venendo suggerito un arrocco destro
					ChessboardModel.setPezzoInPosizione(piece, x + 1, y);
					ChessboardModel.setPezzoInPosizione(null, x, y);
					condition = Core.check(piece.getTeam()); //guardo se nella casella di passaggio il re è sotto scacco e lo salvo in condition
					ChessboardModel.setPezzoInPosizione(null, x + 1, y);
					ChessboardModel.setPezzoInPosizione(piece, x, y); //rimetto il re dov'era
					if(condition) //se il re è sotto scacco nella casella di transizione passo a un'altra mossa
						continue;
				}
			} // FINE CONTROLLI ARROCCO
			//In ogni caso devo fare il controllo che muovere un pezzo qualsiasi nella casella suggerita
			//non mi conduca ad una condizione di scacco perciò simulo la mossa e controllo
			tmp = ChessboardModel.getPezzoInPosizione(tx, ty);//mi serve un tmp dove tenere l'eventuale pezzo mangiato
			ChessboardModel.setPezzoInPosizione(piece, tx, ty);
			ChessboardModel.setPezzoInPosizione(null, x, y);
			condition = Core.check(piece.getTeam());//controllo se il mio re va/resta sotto scacco
			ChessboardModel.setPezzoInPosizione(piece, x, y);//rimetto piece dov'era
			ChessboardModel.setPezzoInPosizione(tmp, tx, ty);//metto tmp dov'era
			if (condition) // se il re sta sotto scacco passo alla prossima mossa
				continue;
			
			//---Se ho superato tutta questa serie di controlli e di continue
			//---e sono arrivato qui, posso aggiungere la mossa a quelle consentite
			availableMoves.add(available);
		}//FINE FOR EACH
		System.out.println("'.");
		System.out.print("Core: 'Puoi andare in ");
		return availableMoves;
	}
	
	public static int move(int sx, int sy, int tx, int ty){
		int target = tx * 10 + ty;
		Piece piece = ChessboardModel.getPezzoInPosizione(sx, sy);
		Piece tmp = null;
		int result = 0; //result torna 0 se nessun match disponibile, 1 se mossa correttamente effettuata, 2 se mossa possibile, ma non eseguita causa scacco
		
		for (int available : piece.mosseConsentite(sx, sy)){
			System.out.print("Potrei andare in " + (char)('A' + available/10) + (available%10 + 1) + "...");
			if (available == target) {//la mossa che ho scelto combacia con una delle possibili per il pezzo
				System.out.println(" Match!");
				result = 1;
				tmp = ChessboardModel.getPezzoInPosizione(tx, ty);//la eseguo ma tengo l'eventuale pezzo mangiato in tmp
				ChessboardModel.setPezzoInPosizione(piece, tx, ty);
				ChessboardModel.setPezzoInPosizione(null, sx, sy);
				//se il mio re va/resta sotto scacco invalido la mossa (vale anche per arrocco)
				if(Core.check(piece.getTeam())){
					result = 2;
					ChessboardModel.setPezzoInPosizione(piece, sx, sy);//rimetto piece dov'era
					ChessboardModel.setPezzoInPosizione(tmp, tx, ty);//metto tmp dov'era
				}

				else {
					
					if (piece instanceof CastlingPiece)
						{((CastlingPiece) piece).setMoved();
						System.out.println("Questo pezzo non potrà più fare l'arrocco.");
						}
				}
				//break; PER ORA LO LASCIO COMMENTATO, MA IN FUTURO, APPENA TROVO UN MATCH ESCO DAL FOR EACH	
			}
			else
				System.out.println(" Not match!");
		}//chiude for each
		return result;
	}
	
	public static void upPawn(int x, int y){
		Piece piece = ChessboardModel.getPezzoInPosizione(x, y);
		if (piece instanceof Pawn){
			System.out.println("Il pedone va sostituito. Scegli: 1- Regina; 2- Torre; 3- Alfiere; 4- Cavallo:");
			switch(Integer.parseInt(new Scanner(System.in).nextLine())){
			case 1:
				ChessboardModel.setPezzoInPosizione(new Queen(piece.getTeam()), x, y);
				break;
			case 2:
				ChessboardModel.setPezzoInPosizione(new Rook(piece.getTeam()), x, y);
				break;
			case 3:
				ChessboardModel.setPezzoInPosizione(new Bishop(piece.getTeam()), x, y);
				break;
			case 4:
				ChessboardModel.setPezzoInPosizione(new Knight(piece.getTeam()), x, y);
			}//fine switch
		}//fine if Pawn
	}
	
	public static boolean staleMate(){
		int counterTeam1 = 0;
		int counterTeam2 = 0;
		Piece piece;
		//i casi di stallo sono 1)re vs. re, 2) re vs. re + cavallo 3) re vs. re + alfiere
		//perciò se incontro un pezzo diverso da questi restituisco subito false
		//se incontro questi pezzi, al terzo trovato restituisco false (es. re + cavallo + cavallo non crea stallo)
		for(int i = 0; i < 8; i++)
			for(int j = 0; j < 8; j++) {
				piece = ChessboardModel.getPezzoInPosizione(i, j);
				if ( piece == null)
					continue;
				if (piece instanceof Queen || piece instanceof Pawn || piece instanceof Rook)
					return false;
				if (piece.getTeam() == Team.Team1)
					if(counterTeam1 == 2)
						return false;
					else
						counterTeam1++;
				else
					if(counterTeam2 == 2)
						return false;
					else
						counterTeam2++;
			}
		//finito il ciclo potrei avere un ultimo caso di non stallo non ancora considerato
		if (counterTeam1 == 2 && counterTeam2 == 2)
			return false; //non è ancora stallo matematico (es. re + alfiere vs. re + cavallo)
		
		//------ TUTTI I RIMANENTI SONO CASI DI STALLO (1 vs. 2 fatti di re, alfieri e/o cavalli oppure 1 vs. 1 per forza due re) ---- //
		return true; 

			
	}
}
