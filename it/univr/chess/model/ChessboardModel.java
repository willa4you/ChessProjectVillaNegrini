package it.univr.chess.model;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import it.univr.chess.model.pieces.*;
import it.univr.chess.view.View;

public class ChessboardModel implements Model {
	
	private enum Step {
		STARTFROM, GOTO, PROMOTION
	}
	
	private final View view;
	private Piece chessboard[][] = new Piece[8][8];
	private Team turn = Team.TEAM1;
	private Step step = Step.STARTFROM;
	private int sx, sy; // start x e y
	
	public ChessboardModel(View view) {
		this.view = view;
		//schiero la prima squadra
		chessboard[0][0] = new Rook(Team.TEAM1, this);
		chessboard[1][0] = new Knight(Team.TEAM1, this);
		chessboard[2][0] = new Bishop(Team.TEAM1, this);
		chessboard[3][0] = new Queen(Team.TEAM1, this);
		chessboard[4][0] = new King(Team.TEAM1, this);
		chessboard[5][0] = new Bishop(Team.TEAM1, this);
		chessboard[6][0] = new Knight(Team.TEAM1, this);
		chessboard[7][0] = new Rook(Team.TEAM1, this);
		
		for (int i = 0; i < 8; i++)
			chessboard[i][1] = new Pawn(Team.TEAM1, this);
		
		//riempio la parte centrale della chessboard di vuoti
		for (int i = 0;i < 4; i++)
			for (int j = 0; j <= 7; j++)
				chessboard[j][i + 2] = null;
		
		//schiero la seconda squadra
		for (int i = 0; i < 8; i++)
			chessboard[i][6] = new Pawn(Team.TEAM2, this);
		
		chessboard[0][7] = new Rook(Team.TEAM2, this);
		chessboard[1][7] = new Knight(Team.TEAM2, this);
		chessboard[2][7] = new Bishop(Team.TEAM2, this);
		chessboard[3][7] = new Queen(Team.TEAM2, this);
		chessboard[4][7] = new King(Team.TEAM2, this);
		chessboard[5][7] = new Bishop(Team.TEAM2, this);
		chessboard[6][7] = new Knight(Team.TEAM2, this);
		chessboard[7][7] = new Rook(Team.TEAM2, this);
	}
	
	@Override
	public Piece getPiece(int x, int y) {
		return chessboard[x][y];
	}
	
	@Override
	public void coordinates(int x, int y) {
		switch(step){
		case STARTFROM:
			//se non ho selezionato un elemento null E ho selezionato uno della mia squadra con ALMENO UNA MOSSA DISPONIBILE
			if (chessboard[x][y] != null && chessboard[x][y].getTeam() == turn && availableMoves(x, y).iterator().hasNext()) {	
				//setto le mie variabili d'istanza per quando il metodo viene richiamato con coordinate d'arrivo
				sx = x;
				sy = y;
				step = Step.GOTO; //mi preparo alla nuova modalita' quando ricevero' nuove coordinate
				
				view.selected(x, y, availableMoves(x, y)); //avverto view che e' cambiata fase del gioco e un pezzo e' selezionato
			}
			break;
		case GOTO:
			if (x == sx && y == sy) {
				view.selfSelect(x, y);
				break;
			}
			//ricevute le coordinate di arrivo, controllo se combaciano con
			//almeno una mossa disponibile del pezzo in coordinate di partenza
			boolean match = false;
			for (int moves : availableMoves(sx, sy))
				if (match = (x == (moves / 10) && y == (moves % 10))) {
					move(sx, sy, x, y); //se c'e' il match eseguo la mossa
					nextTurn(); //eseguo le operazioni di cambio turno
					view.moved(); //comunico a view il cambio di stato								
					break; //se trovo un match non proseguo nell'iterazione
				}
			
			if (!match)
				view.wrongMove(x, y);
			break;
		default:
			JOptionPane.showMessageDialog(null,
				    "Seleziona un pezzo da sostituire al pedone!",
				    "Errore!",
				    JOptionPane.ERROR_MESSAGE);
			break;
		}
	}
	
	private void nextTurn() {
		//passaggio del turno e primi controlli del turno successivo
		if (step != Step.PROMOTION)
			step = Step.STARTFROM; //le prossime coordinate che ricevero' saranno di partenza
		
		turn = (turn == Team.TEAM1) ? Team.TEAM2 : Team.TEAM1; //cambio squadra in gioco
		
		// i controlli qui sotto sono le prime cose da fare al turno successivo
		// per il giocatore di squadra opposta alla mossa appena effettuata 
		
		if (check(turn)) {//se il re e' sotto scacco
			if (mate(turn)){
				if (JOptionPane.showConfirmDialog(null, "<html><div align=center>SCACCO MATTO!<br>" +
						((turn == Team.TEAM1) ? "Giocatore 2" : "Giocatore 1") + " vince.<br>Vuoi fare un'altra partita?</div></html>",
						"SCACCO MATTO", JOptionPane.YES_NO_OPTION) == 1)
					System.exit(0);
				//else
					//nuova partita
			}
			else System.out.println("ATTENZIONE, SEI SOTTO SCACCO!");
		}
		else if(mate(turn) || staleMate()){
			//Tecnicamente, se non posso muovere, ma non sono in scacco, si tratta di STALLO
			//Oppure se mi trovo in una condizione di stallo matematico (re vs. re, re vs. re+cavallo, re vs. re+alfiere)
			if (JOptionPane.showConfirmDialog(null, "<html><div align=center>PATTA!<br>" +
					"Vuoi fare un'altra partita?</div></html>",
					"PATTA", JOptionPane.YES_NO_OPTION) == 1)
				System.exit(0);
			//else
				//nuova partita
			
		} //controlli scacco e scacco matto
		
	}
	
	private Iterable<Integer> availableMoves(int x, int y){
		//questo metodo restituisce un iterabile di cooridnate espresse in numeri interi
		//delle mosse realmente effettuabili dal pezzo selezionato in coordinate x ed y
		//per fare questo chiede al pezzo stesso quali sono le sue mosse disponibili
		//e vaglia l'iterabile che riceve attraverso una serie di regole di ordine superiore
		//delle quali il pezzo non conosce i dettagli (non ha visione di gioco) scremandolo delle mosse non valide
		//questo metodo restituisce un iterabile di cardinalita' uguale o minore di quello ricevuto dal pezzo
		
		Piece piece = chessboard[x][y];
		ArrayList<Integer> availableMoves = new ArrayList<Integer>();
		
		int tx, ty; // coordinate delle caselle target delle mosse consentite

		for (int available : piece.mosseConsentite(x, y)){
			tx = available / 10;
			ty = available % 10;
			
			//CONTROLLI ARROCCO (se il pezzo e' Re e la x si sposta di 2)
			if (piece instanceof King && (tx == x + 2 || tx == x - 2)) {
				
				//se mi viene suggerito un arrocco, le prime condizioni sono state superate a livello pezzo
				//manca il test se sono sotto scacco ora, oppure sono sotto scacco nella casella di passaggio
				//(il test di non dover essere sotto scacco nella casella di arrivo non viene qui considerato perche' si fa sempre)
				
				if (check(piece.getTeam())) //se sono sotto scacco ora non aggiungo la mossa alle consentite
					continue;
				
				//se provo arrocco sinistro e il re e' sotto scacco nella casella di transizione passo a un'altra mossa
				if ((tx == x - 2) && swapAndTryCheck(x, y, x - 1, y)) 
					continue;

				//se provo arrocco destro e il re e' sotto scacco nella casella di transizione passo a un'altra mossa
				if ((tx == x + 2) && swapAndTryCheck(x, y, x + 1, y)) 
					continue;
					
			} // FINE CONTROLLI ARROCCO
			
			// CONTROLLO CATTURA IN ENPASSANT
			// il controllo standard guarda che l'eventuale mossa non mi metta o mantenga il re
			// sotto scacco; in un caso particolare di cattura in enpassant, potrebbe capitare
			// di muovere per catturare un pedone che minaccia il mio Re; la sola mossa in diagonale
			// non fa sparire il pezzo avversario dalla scacchiera (unico caso negli scacchi) percio' 
			// per testarlo devo creare un'istruzione ad hoc che lo faccia sparire
			
			// controllo che sia un pedone, muova in diagonale e in una casella con null (condizione 
			// sufficiente per essere sicuri si tratti di una cattura in enpassant e nient'altro
			if (piece instanceof Pawn && x != tx && chessboard[tx][ty] == null) {
				//se sono un pedone di squadra 1 elimino chi sta sotto (y meno uno) la mia casella target
				//viceversa se sono un pedone di squadra 2 elimino chi sta sopra (y piu' uno)
				int position = (piece.getTeam() == Team.TEAM1) ? -1 : 1;
				Piece tmp = chessboard[tx][ty + position];
				chessboard[tx][ty + position] = null; //faccio sparire durante il controllo il pedone catturato
				boolean condition = swapAndTryCheck(x, y, tx, ty);
				chessboard[tx][ty + position] = tmp; //rimetto il pedone dov'era
				if (condition)
					continue; //se anche catturandolo e facendolo sparire sarei in scacco, non aggiungo la mossa
			}
			
			//CONTROLLO STANDARD
			//ATTENZIONE: l'else significa "se si trattava di un caso di cattura in enpassant, NON SI TRATTA
			// di un caso di cattura tradizionale (caso comunque piu' frequente in assoluto): saltalo e prosegui."
			//nel caso generico devo fare il controllo che muovere il pezzo nella casella che esso mi suggerisce
			//non mi conduca ad una condizione di scacco, percio' simulo la mossa, controllo e se sono
			//sotto scacco, non la aggiungo
			else if (swapAndTryCheck(x, y, tx, ty))
				continue;
			
			//---Se ho superato tutta questa serie di controlli e di continue
			//---e sono arrivato qui, posso aggiungere la mossa a quelle consentite
			availableMoves.add(available);
		}//FINE FOR EACH

		return availableMoves;
	}
	
	private boolean swapAndTryCheck(int sx, int sy, int tx, int ty){
		Piece tmp = chessboard[tx][ty]; //effettuo uno swap temporaneo "salvando" il contenuto della casella target in un tmp
		chessboard[tx][ty] = chessboard[sx][sy];
		chessboard[sx][sy] = null;
		boolean condition = check(chessboard[tx][ty].getTeam()); //controllo se la mossa conduce a una situazione di scacco potenziale
		chessboard[sx][sy] = chessboard[tx][ty];//rimetto tutto a posto
		chessboard[tx][ty] = tmp; 
		
		return condition;
	}
	
	private boolean check(Team player){
		Piece piece = null;
		Team otherPlayer;
		otherPlayer = (player == Team.TEAM1) ? Team.TEAM2 : Team.TEAM1;
		
		for(int i = 0; i < 8; i++)
			for(int j = 0; j < 8; j++)
				if((piece = chessboard[i][j]) != null && piece.getTeam() == otherPlayer && piece.check(i, j))
					return true;
		
		return false;
	}
	
	private boolean mate(Team player){
		//controllo di SCACCO MATTO
		//(oltre che per lo scacco matto viene usato anche per controllare un particolare caso di stallo:
		//infatti in particolari configurazioni capita che il re non sia sotto scacco, ma ogni mossa rimasta
		//al giocatore lo metterebbe in una condizione di scacco; questo secondo le regole non significa
		//perdere la partita, ma concluderla con una patta dovuta ad uno stallo)
		Piece piece = null;
		
		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++) {
				piece = chessboard[i][j];
				if (piece != null && piece.getTeam() == player)
					for (int moves : piece.mosseConsentite(i, j))
						if(!swapAndTryCheck(i, j, moves / 10, moves % 10))
							return false;
			} //basta trovare una mossa del mio team che non mi veda sotto scacco per ritornare false e terminare il metodo
		
		//se nemmeno una mossa evita al mio re di essere sotto scacco si tratta di SCACCO MATTO
		return true;
	}
	
	private boolean staleMate(){
		//controllo di stallo per impossibilita' certa di dare scacco in questa partita
		//da parte di entrambe le squadre
		
		int counterTeam1 = 0;
		int counterTeam2 = 0;
		Piece piece;
		//i casi di stallo sono 1)re vs. re, 2) re vs. re + cavallo 3) re vs. re + alfiere
		//percio' se incontro un pezzo diverso da questi restituisco subito false
		//se incontro questi pezzi, al terzo trovato restituisco false (es. re + cavallo + cavallo non crea stallo)
		for(int i = 0; i < 8; i++)
			for(int j = 0; j < 8; j++) {
				piece = chessboard[i][j];
				if ( piece == null)
					continue;
				if (piece instanceof Queen || piece instanceof Pawn || piece instanceof Rook)
					return false;
				if (piece.getTeam() == Team.TEAM1)
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
			return false; //non e' ancora stallo matematico (es. re + alfiere vs. re + cavallo)
		
		//------ TUTTI I RIMANENTI SONO CASI DI STALLO (1 vs. 2 fatti di re, alfieri e/o cavalli oppure 1 vs. 1 per forza due re) ---- //
		return true; 		
	}
	private void move(int sx, int sy, int tx, int ty){
		
		//QUESTO METODO ESEGUE LA MOSSA SENZA CONTROLLARE LA VALIDITA
		//PERCHE CI HA GIA PENSATO AVAILABLEMOVES
		//QUESTO METODO FA SOLO LA MOSSA E EVENTUALI CAMBIAMENTI PER ARROCCO, ENPASSANT E PROMOZIONE DEL PEDONE
		Piece piece = chessboard[sx][sy];
		
		// ----------------- PRIMO CONTROLLO: CATTURA IN ENPASSANT
		//(da fare prima che la mossa standard sia effettuata perche' devo controllare
		//il pezzo in casella target PRIMA che la mossa sia compiuta)
		//se ho mosso un pedone in diagonale (start x diversa da target x), ma in una casella vuota
		//secondo le mosse consentite posso solamente stare procedendo ad una cattura in enpassant
		//Essendo l'unico caso negli Scacchi in cui si cattura senza sostituirsi ad un pezzo avversario 
		//sulla scacchiera, occorre un'azione ulteriore per farlo sparire.
		
		if (piece instanceof Pawn && sx != tx && chessboard[tx][ty] == null){
			//se sono un pedone di squadra 1 elimino chi sta sotto (y meno uno) la mia casella target
			//viceversa se sono un pedone di squadra 2 elimino chi sta sopra (y piu' uno)
			int position = (piece.getTeam() == Team.TEAM1) ? -1 : 1;
			chessboard[tx][ty + position] = null;
			view.move(tx, ty + position);
			
		}
		// -------------------------------- fine primo controllo
		
		// ----------- MOSSA STANDARD
		// la mossa sposta il pezzo selezionato sulla casella target:
		// se c'era qualcuno avversario, la cattura si compie come naturale conseguenza del fatto che
		// l'unico reference che puntava ad esso era la coordinata di chessboard tx, ty la quale ora
		// punta al pezzo che ha mosso; l'oggetto Piece che era puntato quindi viene eliminato dal garbage
		// in seconda istanza, nella casella di partenza rimane SEMPRE una casella vuota NULL (per OGNI mossa)
		// (l'unico caso in cui si mangia in modo diverso e' l'enpassant affrontato appena sopra)
		chessboard[tx][ty] = piece;
		view.move(sx, sy, tx, ty);//COMANDI VIEW
		
		chessboard[sx][sy] = null;
		view.move(sx, sy);//COMANDI VIEW
		
		// --------------------- FINE MOSSA STANDARD
		
		// -------------------- SECONDO CONTROLLO: ARROCCO
		//se il pezzo mosso e' un Re e lo spostamento e' di due caselle lungo le x
		//non puo' essere che un arrocco, per via delle mosse consentite
		//in questo caso, e solo in questo, oltre ad aver effettuato la mossa standard
		//si compie inoltre la mossa della torre
		if (piece instanceof King && (tx == sx + 2 || tx == sx - 2)){
			//setto coordinate di partenza e arrivo della torre
			//controllo se si tratta di arrocco destro o altrimenti (sinistro)
			int rookStart = (tx == sx + 2) ? 7 : 0; //PARTENZA: se arrocco destro colonna 7 altrimenti colonna 0
			int rookTarget = (tx == sx + 2) ? 5 : 3;//ARRIVO: se arrocco destro colonna 5 altrimenti colonna 3
			
			chessboard[rookTarget][sy] = chessboard[rookStart][sy]; //scelgo sempre inevitabilmente la riga del re (sy)
			view.move(rookStart, sy, rookTarget, sy); //COMANDI VIEW
			
			chessboard[rookStart][sy] = null;
			view.move(rookStart, sy);//COMANDI VIEW
			
			//a questo punto, se il programma e' ben fatto, non ho dubbi che il pezzo che ho mosso sia una torre
			//e in quanto CastlingPiece DOVREI mettere il suo parametro moved a true per evitare l'arrocco in futuro
			//ma non serve dato che essendo arrocco muovo il Re e il prossimo controllo settera' il suo parametro moved a true
			//in quanto pezzo selezionato per muovere e ogni futuro arrocco sara' scongiurato da qui alla fine della partita		
		}
		// --------------------- FINE SECONDO CONTROLLO
		
		// ----------------- TERZO CONTROLLO: CASTLING PIECE
		// la mossa di oggetti CastlingPiece preclude loro per sempre il partecipare all'arrocco in futuro
		// per i pezzi Re e Torre setto moved a true cosi che non possano piu fare l'arrocco
		// la loro variabile moved e' messa a false solo dal costruttore
		// poi ad ogni mossa gia' a partire dalla prima viene sempre messa a true
		// (un po' rindondante, ma mi costerebbe di piu' controllare ogni volta cosa vale per evitare in caso di risettarlo a true...)
		
		if (piece instanceof CastlingPiece) 
			((CastlingPiece) piece).setMoved();
		
		// ---------------- FINE TERZO CONTROLLO
		
		
		// ---------------- QUARTO CONTROLLO: PULIZIA ENPASSANT DECADUTI
		// l'enpassant vale solo il turno successivo a quando il pedone avversario muove di due posizioni
		// se un pedone avversario si e' reso vulnerabile all'enpassant nel turno appena passato, a questo punto e' gia'
		// stato scontato nelle proprie mosse disponibli e, sfruttato o meno, non sara' piu' utilizzabile
		{	
		Piece pawn;	
		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++)
				if ((pawn = chessboard[i][j]) instanceof Pawn)
					((Pawn) pawn).setEnpassant(false);
		}
		// --------------- FINE QUARTO CONTROLLO
		
		//gli ultimi due controlli sono entrambi relativi alla mossa di un pedone
		//e sono racchiusi in un'unica condizione di instanceof Pawn
		//ATTENZIONE: il primo controllo e' anch'esso relativo alla mossa di un pedone, ma DEVE RESTARE in cima
		//mentre il quarto e il quinto qui sotto DEVONO RESTARE IN FONDO al metodo: NON CAMBIARE LA SEQUENZA
		
		if (piece instanceof Pawn){
			// --------- QUINTO CONTROLLO: PROMOZIONE DI UN PEDONE
			// se un pedone arriva in fondo deve essere promosso
			// attenzione: lasciare questa mossa alla fine perche' se fatta prima della mossa standard
			// vedrebbe il pezzo forte appena messo in scacchiera sostituito nuovamente dalla mossa deL pedone (definitivamente!)
			if(ty == 7 || ty == 0){
				
				// chiamo la promotion window passando true se Team1 e false se Team2
				this.sx = tx;
				this.sy = ty;
				//ATTENZIONE: solo in questo caso uso le coordinate di start per sapere
				//in un momento successivo dove posizionare il nuovo pezzo
				
				view.promotion((ty == 7));//(se y e' 7 puo' solo essere squadra 1)
				step = Step.PROMOTION; //senza un valore di step valido ogni coordinata ricevuta viene ignorata
				//solo una selezione nella promotionWindow riattivera' step in STARTFROM
				
			} 
			// ------------ FINE QUINTO CONTROLLO
			
			// ------------ SESTO ED ULTIMO CONTROLLO: PEDONE VULNERABILE ALL'ENPASSANT
			// se questo e' un pedone che fa due passi, si rende vulnerabile nel turno avversario
			// ad essere mangiato tramite enpassant, quindi setto il suo enpassant a true
			// ATTENZIONE: lasciare questo alla fine altrimenti se si mette sopra la pulizia enpassant
			// la variabile viene "pulita" prima di essere potenzialmente esaminata nel turno successivo
			
			//controllo se si e' mosso di due spazi in verticale
			if (ty == sy + 2 || ty == sy - 2)
				((Pawn)piece).setEnpassant(true);
			
			// --------- FINE SESTO CONTROLLO
			
		} //chiude il controllo instanceof Pawn per i controlli 5 e 6
		
		// ---------- FINE CONTROLLI E FINE MOSSA
	}

	@Override
	public void promotion(int piece) {
		step = Step.STARTFROM;
		Team team = (turn == Team.TEAM1) ? Team.TEAM2 : Team.TEAM1;
		
		switch(piece){
		case 0:
			chessboard[sx][sy] = new Queen(team, this);
			break;
		case 1:
			chessboard[sx][sy] = new Rook(team, this);
			break;
		case 2:
			chessboard[sx][sy] = new Bishop(team, this);
			break;
		case 3:
			chessboard[sx][sy] = new Knight(team, this);
		}//fine switch
		view.promotion(piece, sx, sy);
		
	}
	

}
