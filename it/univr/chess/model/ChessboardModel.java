package it.univr.chess.model;

import java.util.ArrayList;

import it.univr.chess.model.pieces.*;
import it.univr.chess.view.View;

/**
 * La classe implementa una scacchiera attraverso un array bidimensionale di
 * dimensione 8x8 e le principali fasi e regole del gioco ad eccezione di quelle
 * riconducibili direttamente ai singoli pezzi (ovvero i movimenti consentiti).
 * Ogni partita alterna turni di una squadra a quelli dell'altra ed ogni turno
 * alterna fasi di attesa di coordinate di partenza a fasi di attesa di coordinate
 * di arrivo (con l'unica eccezione dell'attesa di un pezzo che promuova il pedone
 * giunto all'ultima traversa, fase nella quale ogni coordinata e` rifiutata).<br><br>
 * 
 * Le variabili di istanza sx ed sy salvano le coordinate di partenza per poterle
 * utilizzare alla ricezione delle coordinate di arrivo.
 * La variabile fiftyMoves serve per implementare la regola delle 50 mosse.
 * 
 * @author Alessandro Villa
 * @author Matteo Negrini
 * @see View
 * @see Pieces
 * @see Team
 */
public class ChessboardModel implements ModelController, ModelPieces {
	// Le tre fasi di un turno
	private enum Step {
		STARTFROM, GOTO, PROMOTION
	}
	
	private final View view; // l'oggetto che cura la grafica a cui comunicare cambi di stato
	private final Pieces chessboard[][] = new Pieces[8][8]; // la scacchiera logica
	
	// variabile di istanza che gestisce l'alternarsi dei turni alla quale
	// i metodi di questa classe fanno riferimento quando eseguono controlli (ad es. scacco)
	private Team turn = Team.TEAM1; 
	
	private Step step = Step.STARTFROM; //la variabile che memorizza l'attuale fase del turno
	
	private int sx, sy; // start x e y (vedi commento della classe)
	private int fiftyMoves; // dopo 50 mosse senza muovere un pedone o catturare, la partita termina in patta
	
	/**
	 * Il costruttore popola la scacchiera creando e posizionando
	 * gli oggetti di tipo Piece secondo la conformazione iniziale.
	 * 
	 * @param view un reference all'oggetto che si occupa
	 * della grafica salvato per comunicargli poi, durante le
	 * fasi di gioco, i cambiamenti di stato.
	 */
	public ChessboardModel(View view) {
		this.view = view;
		// schiero la prima squadra
		chessboard[0][0] = new Rook(Team.TEAM1, this);
		chessboard[1][0] = new Knight(Team.TEAM1, this);
		chessboard[2][0] = new Bishop(Team.TEAM1, this);
		chessboard[3][0] = new Queen(Team.TEAM1, this);
		chessboard[4][0] = new King(Team.TEAM1, this);
		chessboard[5][0] = new Bishop(Team.TEAM1, this);
		chessboard[6][0] = new Knight(Team.TEAM1, this);
		chessboard[7][0] = new Rook(Team.TEAM1, this);
		
		for (int x = 0; x < 8; x++)
			chessboard[x][1] = new Pawn(Team.TEAM1, this);
		
		// riempio la parte centrale della chessboard di vuoti
		for (int x = 0; x <= 7; x++)
			for (int y = 2; y < 6; y++)
				chessboard[x][y] = null;
		
		// schiero la seconda squadra
		for (int x = 0; x < 8; x++)
			chessboard[x][6] = new Pawn(Team.TEAM2, this);
		
		chessboard[0][7] = new Rook(Team.TEAM2, this);
		chessboard[1][7] = new Knight(Team.TEAM2, this);
		chessboard[2][7] = new Bishop(Team.TEAM2, this);
		chessboard[3][7] = new Queen(Team.TEAM2, this);
		chessboard[4][7] = new King(Team.TEAM2, this);
		chessboard[5][7] = new Bishop(Team.TEAM2, this);
		chessboard[6][7] = new Knight(Team.TEAM2, this);
		chessboard[7][7] = new Rook(Team.TEAM2, this);
		
		view.sendMessage(turn == Team.TEAM1, false);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Pieces getPiece(int x, int y) {
		return chessboard[x][y];
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void coordinates(int x, int y) {
		// controllo a che fase del turno mi trovo
		switch (step) {
		case STARTFROM: //caso attesa coordinate di partenza
			//se ho selezionato una casella NON vuota E ho selezionato un pezzo della mia squadra con ALMENO UNA MOSSA DISPONIBILE
			if (chessboard[x][y] != null && chessboard[x][y].getTeam() == turn && availableMoves(x, y).iterator().hasNext()) {	
				// setto le mie variabili d'istanza per quando il metodo viene richiamato con coordinate d'arrivo
				sx = x;
				sy = y;
				step = Step.GOTO; // mi preparo alla nuova modalita' quando ricevero' nuove coordinate
				
				view.selected(x, y, availableMoves(x, y)); //avverto view che e' cambiata fase del gioco e un pezzo e' selezionato
			}
			break;
		case GOTO: // caso attesa coordinate di arrivo
			if (x == sx && y == sy) { // se seleziono le stesse coordinate di partenza il break fa terminare anticipatamente
				view.selfSelect(x, y);
				break;
			}
			// ricevute le coordinate di arrivo, controllo se combaciano con
			// almeno una mossa disponibile del pezzo in coordinate di partenza
			boolean match = false;
			for (int moves : availableMoves(sx, sy)) // itero le mosse disponibili del pezzo
				if (match = (x == (moves / 10) && y == (moves % 10))) { // se le coordinate combaciano
					move(x, y); // eseguo la mossa
					if (step != Step.PROMOTION) // in caso stia per promuovere un pedone rimando i controlli di fine turno
						nextTurn(); // eseguo le operazioni di cambio fase e cambio turno di squadra
					view.moved(); // comunico a view il cambio di stato								
					break; // se trovo un match non proseguo nell'iterazione delle restanti mosse disponibili
				}
			if (!match) // se giungo qui senza che match sia diventato true nell'iterazione le coordinate sono errate
				view.wrongMove(x, y); // comunica alla view uno stato di coordinate errate
			break;
		case PROMOTION:
			view.noCoordinatesThanks(); // se aspetto un pezzo da sostituire ad un pedone promosso rifiuto ogni coordinata
			break;
		}
	}
	/**
	 * Il metodo esegue cambiamenti di stato propri di un fine turno e controlli proprio dell'immediato
	 * inizio di turno successivo quali condizioni sopraggiunte di scacco, scacco matto o di pareggio.
	 */
	private void nextTurn() {
		
		step = Step.STARTFROM; // le prossime coordinate che ricevero' saranno di partenza
		
		turn = (turn == Team.TEAM1) ? Team.TEAM2 : Team.TEAM1; //cambio turno della squadra
		
		// i controlli qui sotto sono le prime cose da fare al turno successivo
		// per il giocatore di squadra opposta alla mossa appena effettuata 
		boolean check = check(turn); // true se sono sotto scacco
		boolean mate = mate(turn); // true se non posso muovere
		
		if (check && mate) //controllo scacco matto
			view.mate(turn == Team.TEAM1);
		// Se non posso muovere, ma non sono sotto scacco, si tratta di STALLO
		else if (!check && mate) // controlo stallo
			view.draw(0);
		else if (impossibleMate()) //controllo scacco matto impossibile
			view.draw(1);
		// Se passano 50 mosse senza muovere un pedone o catturare la partita finisce in patta	
		else if (fiftyMoves >= 50) // controllo 50 mosse
			view.draw(2);
	
		view.sendMessage(turn == Team.TEAM1, check);
	}
	/**
	 * Questo metodo viene invocato per sapere le mosse a disposizione di un pezzo in coordinate x, y.
	 * Per conoscerle esso riceve ed itera dal pezzo stesso le mosse che esso ritiene possibili
	 * secondo i suoi canoni di movimento. Per ognuna di esse esegue dei controlli aggiuntivi
	 * relativi a restrizioni aggiuntive proprie del gioco degli scacchi delle quali il pezzo non 
	 * conosce i dettagli (non ha visione di gioco).<br>
	 * Restituisce un ArrayList iterabile di cardinalita' uguale o minore di quello ricevuto.
	 * 
	 * @param x la coordinata x del pezzo
	 * @param y la coordinata y del pezzo
	 * @return un arrayList di interi con le mosse a disposizione di un pezzo in coordinate x, y.
	 */
	private ArrayList<Integer> availableMoves(int x, int y) {
		ArrayList<Integer> availableMoves = new ArrayList<Integer>(); // l'array list da popolare e restituire
		
		int tx, ty; // coordinate delle mosse consentite dal pezzo, da controllare

		for (int available : chessboard[x][y].availableMoves(x, y)) {
			tx = available / 10;
			ty = available % 10;
			
			// CONTROLLI ARROCCO (se il pezzo e' Re e la x si sposta di 2)
			if (chessboard[x][y] instanceof King && (tx == x + 2 || tx == x - 2)) {
				
				/* se mi viene suggerito un arrocco, le prime condizioni sono state superate a livello pezzo
				// manca il test se sono sotto scacco ora, oppure sono sotto scacco nella casella di passaggio
				// (il test di non dover essere sotto scacco nella casella di arrivo si fa per ogni mossa e non e` qui considerato) */
				
				if (check(chessboard[x][y].getTeam())) //se sono sotto scacco ora l'arrocco non e` consentito
					continue;
				
				//se provo arrocco sinistro, ma il re e' sotto scacco nella casella di transizione l'arrocco non e` consentito
				if ((tx == x - 2) && tryCheck(x, y, x - 1, y)) 
					continue;

				//se provo arrocco destro, ma il re e' sotto scacco nella casella di transizione l'arrocco non e` consentito
				if ((tx == x + 2) && tryCheck(x, y, x + 1, y)) 
					continue;
					
			} // FINE CONTROLLI ARROCCO
			
			/* CONTROLLO CATTURA IN ENPASSANT
			 * Il controllo standard guarda che l'eventuale mossa non mi metta o mantenga il re
			 * sotto scacco; in un caso particolare di cattura in enpassant, potrebbe capitare
			 * di muovere per catturare un pedone che minaccia il mio Re; la sola mossa in diagonale
			 * non fa sparire il pezzo avversario dalla scacchiera (unico caso negli scacchi) percio' 
			 * per testarlo devo creare un'istruzione ad hoc che lo faccia sparire. */
			
			// Controllo che sia un pedone, muova in diagonale e in una casella con null (condizione 
			// sufficiente per essere sicuri si tratti di una cattura in enpassant e nient'altro).
			if (chessboard[x][y] instanceof Pawn && x != tx && chessboard[tx][ty] == null) {
				// se sono un pedone di squadra 1 rimuovo temporaneamente chi sta sotto (y meno uno) la casella target
				// viceversa se sono un pedone di squadra 2 rimuovo temporaneamente chi sta sopra (y piu' uno)
				int position = (chessboard[x][y].getTeam() == Team.TEAM1) ? -1 : 1;
				Pieces tmp = chessboard[tx][ty + position];
				chessboard[tx][ty + position] = null;
				boolean condition = tryCheck(x, y, tx, ty); // verifico se con cattura in enpassant sarei sotto scacco
				chessboard[tx][ty + position] = tmp; //rimetto il pedone catturato dov'era
				if (condition)
					continue; //se catturandolo e facendolo sparire sarei sotto scacco, non aggiungo la mossa
			} // FINE CONTROLLO EN PASSANT
			
			/* CONTROLLO STANDARD
			 * ATTENZIONE: l'else significa "se si trattava di un caso di cattura in enpassant, NON SI TRATTA
			 * di un caso di mossa tradizionale (caso comunque piu' frequente in assoluto): saltalo e prosegui."
			 * Nel caso generico (previsto anche come ultimo controllo dell'arrocco) devo verificare che muovere
			 * nelle coordinate suggerite a questa iterazione non mi conduca ad una condizione di scacco, percio'
			 * simulo la mossa, controllo se sia in scacco e in caso affermativo proseguo con la successiva
			 * non includendola nell'elenco che restituiro`. */
			else if (tryCheck(x, y, tx, ty))
				continue;
			
			/* Se ho superato tutta questa serie di controlli senza incappare in un continue
			 * e sono arrivato qui, posso aggiungere la mossa a quelle consentite. */
			availableMoves.add(available);
		}// FINE FOR EACH delle mosse disponibili suggerite dal pezzo

		return availableMoves;
	}
	/**
	 * Il metodo prova a simulare una mossa da sx, sy a tx, ty e controllare se il re della squadra
	 * che muove e` sotto scacco al termine della stessa. Ritorna true o false sulla base di questo
	 * controllo.
	 * 
	 * @param sx la coordinata x della casella (bottone) di partenza
	 * @param sy la coordinata y della casella (bottone) di partenza
	 * @param tx la coordinata x della casella (bottone) di arrivo
	 * @param ty la coordinata y della casella (bottone) di arrivo
	 * @return se il re e` sotto scacco al termine di una mossa simulata.
	 */
	private boolean tryCheck(int sx, int sy, int tx, int ty) {
		Pieces tmp = chessboard[tx][ty]; // effettuo una mossa temporanea "salvando" il contenuto della casella target in un tmp
		chessboard[tx][ty] = chessboard[sx][sy];
		chessboard[sx][sy] = null; // simulo la mossa
		boolean condition = check(chessboard[tx][ty].getTeam()); // controllo eventuale scacco
		chessboard[sx][sy] = chessboard[tx][ty];
		chessboard[tx][ty] = tmp; // rimetto tutto a posto
		
		return condition;
	}
	
	/**
	 * Il metodo riceve una squadra e controlla se nello stato attuale della scacchiera,
	 * il re di quella squadra e` minacciato da almeno un pezzo avversario (ovvero e` sotto scacco).
	 * 
	 * @param player la squadra cui il te appartiene.
	 * @return true se l'attuale stato della scacchiera vede il re della squadra passata come parametro
	 * minacciato da qualche pezzo avversario, false altrimenti.
	 */
	public boolean check(Team player) {
		Team otherPlayer = (player == Team.TEAM1) ? Team.TEAM2 : Team.TEAM1; // la squadra avversaria
		
		// Scorro tutta la scacchiera in cerca di pezzi avversari e chiedo loro se minacciano il mio re.
		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++)
				if (chessboard[i][j] != null && chessboard[i][j].getTeam() == otherPlayer && chessboard[i][j].check(i, j))
					return true; // appena uno restituisce true, tutto il metodo restituisce true all'istante
		
		return false; // se concludo la scacchiera senza che nessuno minacci il mio re, restituisco false
	}
	
	/**
	 * Il metodo controlla se, per una squadra ricevuta come argomento, ogni sua possibile mossa la porta in una condizione
	 * nella quale il re e` minacciato dall'avversario (cioe` e` sotto scacco), ovvero la squadra non ha alcuna mossa disponibile.
	 * Questa e` condizione necessaria ma NON SUFFICIENTE per determinare uno scacco matto (per questo occorre che il re sia sotto
	 * scacco anche nella situazione attuale).
	 * 
	 * @param player la squadra
	 * @return true se, ogni mossa a me concessa, mi porta ad essere minacciato dall'avversario (condizione di scacco),
	 * false altrimenti (esiste almeno una mossa in cui non sono minacciato).
	 */
	public boolean mate(Team player) {
		/* In particolari configurazioni capita che il re non sia sotto scacco, ma ogni mossa rimasta
		 * al giocatore lo metterebbe in una condizione di scacco; questo secondo le regole non significa
		 * perdere la partita, ma concluderla con una patta dovuta ad uno stallo). */
		
		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++)
				if (chessboard[i][j] != null && chessboard[i][j].getTeam() == player && availableMoves(i, j).iterator().hasNext())
					return false; // basta trovare una mossa disponibile per ritornare false e terminare il metodo
		
		// se non ho nemmeno una mossa disponibile
		return true;
	}
	/**
	 * Il metodo controlla se ci si trova in una situazione morta conosciuta, ovvero casi
	 * nei quali e` matematicamente impossibile dare scacco matto in qualsiasi modo per entrambe le squadre.
	 * I casi considerati sono:<br> 1)re vs. re,<br> 2) re vs. re + cavallo <br>3) re vs. re + alfiere.
	 * 
	 * @return true se ci si trova in qualcuna delle situazioni morte previste.
	 */
	public boolean impossibleMate() {		
		int counter[] = new int[2];
		int team;

		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++) {
				if (chessboard[i][j] == null)
					continue;
				/* i casi previsti prevedono solo pezzi di tipo Re, Alfiere e Cavallo
				 * percio' se incontro un pezzo diverso da questi restituisco subito false */
				if (chessboard[i][j] instanceof Queen || chessboard[i][j] instanceof Pawn || chessboard[i][j] instanceof Rook)
					return false;
				
				// se incontro questi pezzi, al terzo trovato per squadra restituisco false (es. squadra con re + cavallo + cavallo)
				team = (chessboard[i][j].getTeam() == Team.TEAM1) ? 0 : 1;
				if (counter[team] == 2)
					return false;
				else
					counter[team]++;
			}
		
		/* finito il ciclo potrei avere un ultimo caso di non stallo non ancora considerato
		 * ovvero due squadre con due pezzi ciascuno di tipo Re, Cavallo e/o Alfiere (es. re + alfiere vs. re + cavallo)*/
		if (counter[0] == 2 && counter[1] == 2)
			return false; // lo scacco matto non e` ancora impossibile e restituisco false
		
		/*------ Tutti i rimanenti sono casi di situazione morta e scacco matto impossibile
		 * del tipo 1pz. vs. 2pz. fatti di re, alfieri e/o cavalli oppure 1pz. vs. 1pz. obbligatoriamente due re. ---- */
		return true; // se giungo qui senza che alcuna condizione riesca a far tornare false al metodo, restituisco true
	}
	/**
	 * Il metodo esegue la mossa senza controllarne la validita` perche` si presuppone il controllo sia gia`
	 * avvenuto e abbia dato esito positivo. Per eseguire lo spostamento si avvale delle coordinate di partenza
	 * salvate nelle variabili di istanza sx, sy e delle coordinate di arrivo passategli come argomenti.
	 * Oltre a spostare l'oggetto che si trova nelle coordinate di partenza in quelle di arrivo, questo metodo si
	 * occupa anche di eventi particolari dovuti alle regole di Arrocco, Enpassant e promozione del pedone.
	 * Infine il metodo si occupa di incrementare o azzerare il contatore relativo alla regola delle 50 mosse, 
     * a seconda delle condizioni che si verificano. 
     * 
	 * @param tx coordinate di arrivo del pezzo
	 * @param ty coordinate di arrivo del pezzo
	 */
	private void move(int tx, int ty) {
		
		/* ----------------- CONTROLLO 50 MOSSE
		 * Se c'e` una cattura, ovvero la casella target e` occupata, oppure ho mosso un pedone,
		 * la regola delle 50 mosse prevede che il contatore si azzeri, altrimenti si incrementi.
		 * ATTENZIONE: esiste un'unico caso nel quale la cattura si esegue senza che la casella
		 * target sia occupata e si tratta della cattura in enpassant: anche se non e` considerato
		 * dalla condizione che verifica una cattura, rientra comunque nei casi verificati 
		 * trattandosi per sua natura di una mossa di Pedone. */
		if (chessboard[tx][ty] != null || chessboard[sx][sy] instanceof Pawn)
			fiftyMoves = 0;
		else
			fiftyMoves++;
		// ------------------ FINE CONTROLLO 50 MOSSE
		
		/* ----------------- CASTLING PIECE E DIVIETO DI ARROCCO FUTURO
		 * La mossa di oggetti CastlingPiece preclude loro per sempre il partecipare all'arrocco.
		 * Essi hanno una variabile booleana moved messa a false solo dal costruttore; poi ad ogni mossa 
		 * viene sempre messa e rimessa a true. */
		
		if (chessboard[sx][sy] instanceof CastlingPiece) 
			((CastlingPiece) chessboard[sx][sy]).setMoved();
		
		// ---------------- FINE CASTLING PIECE
		
		/* -------------------- ARROCCO
		 * Se il pezzo mosso e' un Re e lo spostamento e' di due caselle lungo le x, in accordo con le
		 * mosse consentite non puo' che trattarsi di un arrocco: in questo caso, e solo in questo, 
		 * oltre alla mossa standard del re muove anche un altro pezzo, ovvero la torre coinvolta. */
		if (chessboard[sx][sy] instanceof King && (tx == sx + 2 || tx == sx - 2)) {
			
			// setto coordinate di partenza e arrivo della torre
			int rookStart = (tx == sx + 2) ? 7 : 0; // PARTENZA: se arrocco destro colonna 7 altrimenti colonna 0
			int rookTarget = (tx == sx + 2) ? 5 : 3;// ARRIVO: se arrocco destro colonna 5 altrimenti colonna 3
			
			chessboard[rookTarget][sy] = chessboard[rookStart][sy]; // scelgo sempre inevitabilmente la traversa del re (sy)
			view.move(rookStart, sy, rookTarget, sy); // COMANDI VIEW
			
			chessboard[rookStart][sy] = null; // anche la mossa della torre lascia un vuoto nella casella di partenza
			view.move(rookStart, sy);// COMANDI VIEW
			
			/* A questo punto, se il programma e' ben fatto, non ho dubbi che il pezzo che ho mosso sia una torre
			 * e in quanto CastlingPiece DOVREI mettere il suo parametro moved a true per evitare l'arrocco in futuro
			 * ma non serve dato che essendo arrocco ho gia` settato la variabile moved del re a true
			 * e ogni futuro arrocco sara' vietato da qui alla fine della partita. */
		}
		// --------------------- FINE ARROCCO
			
		/* ---------------- PULIZIA ENPASSANT DECADUTI
		 * L'enpassant vale solo il turno successivo a quando il pedone avversario muove di due posizioni e solo 
		 * su di esso. Se un pedone avversario si e' reso vulnerabile all'enpassant nel turno appena passato, a 
		 * questo punto e' gia' stato scontato nelle proprie mosse disponibli e, sfruttato o meno, non sara' piu'
		 * utilizzabile. */
		{	
		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++)
				if (chessboard[i][j] instanceof Pawn)
					((Pawn) chessboard[i][j]).setEnpassant(false);
		}
		// --------------- FINE PULIZIA ENPASSANT

		// --------------- CASI RELATIVI ALLA MOSSA DI UN PEDONE		
		if (chessboard[sx][sy] instanceof Pawn) {

			/* ----------------- CATTURA IN ENPASSANT
			 * Se ho mosso un pedone in diagonale (start x diversa da target x), ma in una casella vuota,
			 * in accordo con le mosse consentite posso solamente stare procedendo ad una cattura in enpassant.
			 * Essendo l'unico caso negli Scacchi in cui si cattura senza sostituirsi ad un pezzo avversario 
			 * sulla scacchiera, occorre un'azione ulteriore per farlo sparire. */
			
			if (sx != tx && chessboard[tx][ty] == null) {
				
				// se sono un pedone di squadra 1 elimino chi sta sotto (y meno uno) la mia casella target
				// viceversa se sono un pedone di squadra 2 elimino chi sta sopra (y piu' uno)
				int position = (chessboard[sx][sy].getTeam() == Team.TEAM1) ? -1 : 1;
				chessboard[tx][ty + position] = null;
				view.move(tx, ty + position); // comunico all view il cambio di stato				
			}
			// ------------------- FINE CATTURA IN ENPASSANT
			
			/* ------------------- PROMOZIONE DI UN PEDONE GIUNTO ALL'ULTIMA TRAVERSA
			 * Se la traversa target e` la prima o l'ultima, dato che stiamo considerando la mossa
			 * di un pedone, occorre procedere prima del passaggio del turno, alla promozione dello stesso
			 * in un pezzo a scelta tra Regina, Torre, Alfiere o Cavallo. Avviso la view lo stato affinche`
			 * mostri una finestra di scelta e metto il model in uno stato di attesa per la promozione a cavallo
			 * tra quelli usuali di attesa coordinate di partenza o arrivo.
			 */
			else if (ty == 7 || ty == 0) {		
				
				// dico a view di chiamare la promotion window e gli passo true se si tratta del giocatore 1, false altrimenti
				view.promotion((ty == 7));// (se y e' 7 puo' solo essere squadra 1)
				step = Step.PROMOTION;
				// finche` permane questa fase, ogni coordinata ricevuta viene ignorata
				// e inoltre i controlli di fine turno vengono posticipati				
			} 
			// ------------ FINE PROMOZIONE DEL PEDONE
			
			/* ------------ PEDONE VULNERABILE ALL'ENPASSANT
			 * Se questo e' un pedone che fa due passi, si rende vulnerabile ad essere catturato tramite
			 * enpassant dall'avversario nel turno successivo. Setto il suo enpassant a true.
			 * ATTENZIONE: lasciare questo DOPO la pulizia enpassant eseguita sopra. */
			
			// Controllo se si e' mosso di due spazi in verticale.
			if (ty == sy + 2 || ty == sy - 2)
				((Pawn) chessboard[sx][sy]).setEnpassant(true);
			
			// --------- FINE PEDONE VULNERABILE ALL'ENPASSANT
			
		} // -------- FINE CASI RELATIVI ALLA MOSSA DI UN PEDONE
		
		/* ------------------- MOSSA STANDARD
		 * La mossa sposta il pezzo selezionato dalla casella di partenza alla casella target:
		 * se c'era qualcuno avversario, la cattura si compie come naturale conseguenza del fatto che
		 * l'unico reference che puntava ad esso era chessboard in coordinate tx, ty che ora
		 * puntano al pezzo che ha mosso; l'oggetto Piece che era puntato quindi viene eliminato dal garbage
		 * (l'unico caso differente e` la cattura in enpassant verificata qui sopra);
		 * in seconda istanza, nella casella di partenza rimane SEMPRE una casella vuota NULL per OGNI mossa. */
		chessboard[tx][ty] = chessboard[sx][sy];
		view.move(sx, sy, tx, ty);//COMANDI VIEW
		
		chessboard[sx][sy] = null;
		view.move(sx, sy);//COMANDI VIEW
		
		// --------------------- FINE MOSSA STANDARD
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void promotion(int piece) {
		/* per sapere in quali coordinate mettere il pezzo
		 * cerco un pedone lungo le traverse 7 e 0
		 * e appena lo trovo interrompo il ciclo e mantengo
		 * le coordinate. */
		int x = 0;
		int y = 0;
		for (x = 0; x < 8; x++) {
			if (chessboard [x][7] instanceof Pawn) {
				y = 7;
				break;
			}
			else if (chessboard [x][0] instanceof Pawn) {
				y = 0;
				break;
			}
		}
		/* sostituisco un nuovo pezzo al pedone in base alla scelta
		 * ricevuta da promotionWindow attraverso un intero tra 0 e 3 */
		switch (piece) {
		case 0:
			chessboard[x][y] = new Queen(turn, this);
			break;
		case 1:
			chessboard[x][y] = new Rook(turn, this);
			break;
		case 2:
			chessboard[x][y] = new Bishop(turn, this);
			break;
		case 3:
			chessboard[x][y] = new Knight(turn, this);
		}// fine switch
		
		nextTurn(); // adesso posso passare al turno successivo
		view.promotion(piece, x, y); // avviso la view di aggiornarsi		
	}
	
	/**
	 * Questo metodo viene utilizzato solamente in fase di test JUnit per settare istantaneamente
	 * la scacchiera in uno stato di interesse raggiungibile altrimenti solo attraverso un numero
	 * elevato di singole mosse. Riceve un array bidimensionale di oggetti Piece gia` pronto, non
	 * lo sostituisce a quello attuale perche` final, ma sostituisce tutte le singole celle.
	 * 
	 * @param chessboard l'array bidimensionale di tipo Piece
	 */
	public void setChessboard(Piece chessboard[][]) {
		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++)
				this.chessboard[i][j] = chessboard[i][j];
	}
	/**
	 * Questo metodo viene utilizzato solamente in fase di test JUnit per verificare che tutti gli
	 * oggetti di tipo Model e Controller puntino sempre alla stessa view a partita avviata.
	 * 
	 * @return una reference ad un oggetto che implementa l'interfaccia view
	 */
	public View getView() {
		return view;
	}
}
