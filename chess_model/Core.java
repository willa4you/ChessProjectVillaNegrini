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
	
	public static void move(int sx, int sy, int tx, int ty){
		
		//QUESTO METODO ESEGUE LA MOSSA SENZA CONTROLLARE LA VALIDITA
		//PERCHE CI HA GIA PENSATO AVAILABLEMOVES
		//QUESTO METODO FA SOLO LA MOSSA E EVENTUALI CAMBIAMENTI PER ARROCCO, ENPASSANT E PROMOZIONE DEL PEDONE
		Piece piece = ChessboardModel.getPezzoInPosizione(sx, sy);
		
		//primo controllo: enpassant (da fare prima che la mossa standard sia effettuata 
		//perché controllo il pezzo in casella target prima che sia sostituito/mangiato)
		//se ho mosso un pedone in diagonale (start x diversa da target x), ma in una casella vuota
		//secondo le mosse consentite posso solamente stare procedendo ad un enpassant
		//Essendo l'unico caso negli Scacchi in cui si mangia senza sostituirsi ad un pezzo avversario 
		//sulla scacchiera, il pezzo avversario sarebbe ancora presente in scacchiera a mossa avvenuta.
		
		if (piece instanceof Pawn && sx != tx && ChessboardModel.getPezzoInPosizione(tx, ty) == null){
			//se sono un pedone di squadra 1 elimino chi sta sotto (y meno uno) la mia casella target
			//viceversa se sono un pedone di squadra 2 elimino chi sta sopra (y piu' uno)
			int position = (piece.getTeam() == Team.Team1) ? -1 : 1;
			ChessboardModel.setPezzoInPosizione(null, tx, ty + position);
		}

		// ----------- MOSSA STANDARD
		// la mossa sposta il pezzo sulla casella target:
		// se c'era qualcuno sparisce (mangiato) e nella casella di partenza rimane SEMPRE una casella vuota NULL
		// (l'unico caso in cui si mangia in modo diverso e' l'enpassant affrontato appena sopra)
		ChessboardModel.setPezzoInPosizione(piece, tx, ty);
		ChessboardModel.setPezzoInPosizione(null, sx, sy);
		
		//secondo controllo: arrocco
		//se il pezzo mosso e' un Re e lo spostamento e' di due caselle lungo le x
		//non puo' essere che un arrocco, per via delle mosse consentite
		//in questo caso, e solo in questo, oltre ad aver effettuato la mossa standard
		//si compie inoltre la mossa della torre
		if (piece instanceof King && (sx == tx + 2 || sx == tx - 2)){
			int rookStart = (sx == tx - 2) ? 7 : 0; //se si tratta di arrocco destro la torre parte dalla colonna 7 altrimenti dalla 0
			int rookTarget = (sx == tx - 2) ? 5 : 3;//se si tratta di arrocco destro la torre giunge alla colonna 5 altrimenti alla 3
			ChessboardModel.setPezzoInPosizione(ChessboardModel.getPezzoInPosizione(rookStart, sy), rookTarget, sy);//scelgo la riga del re (sy)
			ChessboardModel.setPezzoInPosizione(null, rookStart, sy);
			//a questo punto, se il programma e' ben fatto, non ho dubbi che il pezzo che ho mosso sia una torre
			//e in quanto CastlingPiece DOVREI mettere il suo parametro moved a true per evitare l'arrocco in futuro
			//ma non serve dato che ho appena mosso il re e il prossimo controllo settera' il suo parametro moved a true
			//e ogni futuro arrocco sara' scongiurato da qui alla fine della partita		
		}
		
		//terzo controllo: la mossa di oggetti CastlingPiece gli preclude per sempre il partecipare all'arrocco in futuro
		//per i pezzi Re e Torre setto moved a true così che non possano piu fare l'arrocco
		//la loro variabile moved è messa a false solo dal costruttore
		//poi ad ogni mossa gia' a partire dalla prima viene sempre messa a true
		//(un po' rindondante, ma mi costerebbe di piu' controllare ogni volta cosa vale per evitare in caso di risettarlo a true...)
		if (piece instanceof CastlingPiece) 
			((CastlingPiece) piece).setMoved();
		
		//quarto controllo: pulizia degli enpassant dei pedoni
		//l'enpassant vale solo il turno successivo a quando il pedone avversario muove di due posizioni
		//se un pedone avversario si e' reso vulnerabile all'enpassant nel turno appena passato, a questo punto e' gia'
		//stato scontato nelle proprie mosse disponibli e, sfruttato o meno, non sara' piu' utilizzabile
		{	
		Piece pawn;	
		for(int i = 0; i < 8; i++)
			for(int j = 0; j < 8; j++)
				if((pawn = ChessboardModel.getPezzoInPosizione(i, j)) instanceof Pawn)
					((Pawn) pawn).setEnpassant(false);
		}
		
		//gli ultimi due controlli sono entrambi relativi alla mossa di un pedone
		//e sono racchiusi in un'unica condizione di instanceof Pawn
		//ATTENZIONE: il primo controllo e' anch'esso relativo alla mossa di un pedone, ma DEVE RESTARE in cima
		//mentre il quarto e il quinto qui sotto DEVONO RESTARE IN FONDO al metodo: NON CAMBIARE LA SEQUENZA
		
		if (piece instanceof Pawn){
			//quinto controllo: la promozione di un pedone
			//se un pedone arriva in fondo deve essere promosso
			//attenzione: lasciare questa mossa alla fine perché fatta prima della mossa standard
			//vedrebbe il nuovo pezzo messo in scacchiera nuovamente sostituito da un pedone (definitivamente!)
			if(ty == 7 || ty == 0){
				Team team = (ty == 7) ? Team.Team1 : Team.Team2; 

				System.out.println("Il pedone va sostituito. Scegli: 1- Regina; 2- Torre; 3- Alfiere; 4- Cavallo:");
				switch(Integer.parseInt(new Scanner(System.in).nextLine())){
				case 1:
					ChessboardModel.setPezzoInPosizione(new Queen(team), tx, ty);
					break;
				case 2:
					ChessboardModel.setPezzoInPosizione(new Rook(team), tx, ty);
					break;
				case 3:
					ChessboardModel.setPezzoInPosizione(new Bishop(team), tx, ty);
					break;
				case 4:
					ChessboardModel.setPezzoInPosizione(new Knight(team), tx, ty);
				}//fine switch
			} //fine se Pedone ha raggiunto fondo scacchiera
			
			//sesto ed ultimo controllo
			//se questo è un pedone che fa due passi, si rende vulnerabile nel turno avversario
			//ad essere mangiato tramite enpassant, quindi setto il suo enpassant a true
			//ATTENZIONE: lasciare questo alla fine altrimenti se si mette sopra la pulizia enpassant
			//la variabile viene "pulita" prima di essere potenzialmente esaminata il turno successivo
			if (sy == ty + 2 || sy == ty - 2)
				((Pawn)piece).setEnpassant(true);
			
		}//chiude mosse pedone
		
		
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
