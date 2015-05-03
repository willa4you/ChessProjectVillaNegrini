package chessprojectvillanegrini.view;

import java.awt.GridLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import chessprojectvillanegrini.controller.ChessController;
import chessprojectvillanegrini.controller.Controller;

import java.util.ArrayList;

/**
 * La classe estende JPanel e implementa l'interfaccia View.
 * Rappresenta il core della parte grafica e rende possibili
 * i cambiamenti visivi come lo spostamento delle icone delle
 * pedine e l'attivazione/disattivazione dei vari highlights
 * delle caselle (bottoni). E` sempre compito suo creare una
 * nuova istanza del controllore del gioco.<br><br>
 * 
 * Le variabili di istanza consistono in:<br>
 * - un puntatore ad un array bidimensionale 8 x 8 di Button che
 * 	 rappresenta le caselle (bottoni) della scacchiera.<br>
 * - un punatore ad un oggetto che implementi l'interfaccia Controller.<br>
 * - un puntatore ad un oggetto Color che rappresenta il colore del
 *   background delle label attorno al terreno di gioco.<br>
 * - un booleano per sapere se i suggerimenti delle mosse consentite
 *   sono o meno attivati<br>
 * - un puntatore ad un ArrayList di Integer che contiene le coordinate
 * 	 delle caselle (bottoni) su cui il pezzo selezionato ha il permesso
 *   di andare (es.: 52 = casella in posizione 5, 2 = buttons[5][2]).<br>
 * - due interi che servono per memorizzare le coordinate dell'ultima
 *   casella errata selezionata.
 * 
 * @author Alessandro Villa
 * @author Matteo Negrini
 * @see View
 * @see MainWindow
 * @see Button
 * @see Controller
 * @see ChessController
 */
public class ChessboardView extends JPanel implements View {

	private final Button[][] buttons = new Button[8][8];
	private final Controller controller;
	private final Color labelBackground = new Color(77, 0, 0); // rosso scuro
	private boolean easyMode = false;
	private ArrayList<Integer> availableMoves;	
	private int wrongXY = -1; // le coordinate dell'ultima casella errata selezionata
	private int selectedXY = -1; // le coordinate dell'ultimo pezzo selezionato
	
	/**
	 * Viene creato un nuovo oggetto ChessController che fa da tramite tra la parte
	 * grafica e la parte logica del gioco. Gli viene passato un reference del ChessboardView
	 * stesso in modo da poter comunicare successivamente a quest'ultimo di effettuare
	 * variazioni grafiche della scacchiera.<br>
	 * Viene inoltre settato il LayoutManager ad un GridLayout 10 x 10 che permettera` di avere
	 * il terreno di gioco al centro e le label di numeri e lettere tutt'intorno.<br>
	 * Si invoca poi un metodo che inizializza l'array 8 x 8 di Button e infine si assegnano
	 * le componenti alla ChessbardView.
	 */
	public ChessboardView() {
		
		controller = new ChessController(this);
		
		setLayout(new GridLayout(10, 10));
		
		// creo i bottoni (caselle della scacchiera)
		createButtons();
		
		// assegno componenti alla griglia (buttons e labels)
		addComponents();
	}
	
	/**
	 * Metodo che inizializza le caselle (bottoni) della scacchiera assegnando in maniera
	 * alternata un LightButton e un DarkButton alla variabile di istanza buttons. Viene poi
	 * invocato un metodo che aggiunge un ActionListener al singolo bottone.
	 * 
	 * @see DarkButton
	 * @see LightButton
	 * @see #buttonListener(int x, int y)
	 */
	private void createButtons() {
		for (int x = 0; x < buttons.length; x++)
			for (int y = 0; y < buttons[x].length; y++) {
				if ((x % 2 == 0 && y % 2 == 0) || (x % 2 == 1 && y % 2 == 1))
					buttons[x][y] = new DarkButton();
				else
					buttons[x][y] = new LightButton();

				buttonListener(x, y);
			}
			
			initButtonIcons();
	}
	
	/**
	 * Questo metodo aggiunge un ActionListener al singolo Button della variabile
	 * di istanza buttons, facendo in modo che al click della singola casella
	 * (bottone) corrisponda l'invio al controller delle coordinate della casella
	 * (bottone) stessa.
	 * 
	 * @param x la coordinata x del bottone
	 * @param y la coordinata y del bottone
	 */
	private void buttonListener(int x, int y) {	
		buttons[x][y].addActionListener(event -> controller.onClick(x, y));
	}
	
	/**
	 * Metodo che setta le icone dei bottoni secondo la configurazione
	 * iniziale del gioco
	 * 
	 * @see Icon
	 */
	private void initButtonIcons() {
		
		buttons[0][0].setIcon(Icon.returnIcon("white_rook"));
		buttons[1][0].setIcon(Icon.returnIcon("white_knight"));
		buttons[2][0].setIcon(Icon.returnIcon("white_bishop"));
		buttons[3][0].setIcon(Icon.returnIcon("white_queen"));
		buttons[4][0].setIcon(Icon.returnIcon("white_king"));
		buttons[5][0].setIcon(Icon.returnIcon("white_bishop"));
		buttons[6][0].setIcon(Icon.returnIcon("white_knight"));
		buttons[7][0].setIcon(Icon.returnIcon("white_rook"));
		
		for (int x = 0; x <= 7; x++)
			for (int y = 2; y < 6; y++)
				buttons[x][y].setIcon(null);
		
		for (int i = 0; i < 8; i++) {
			buttons[i][1].setIcon(Icon.returnIcon("white_pawn"));
			buttons[i][6].setIcon(Icon.returnIcon("black_pawn"));
		}
		
		buttons[0][7].setIcon(Icon.returnIcon("black_rook"));
		buttons[1][7].setIcon(Icon.returnIcon("black_knight"));
		buttons[2][7].setIcon(Icon.returnIcon("black_bishop"));
		buttons[3][7].setIcon(Icon.returnIcon("black_queen"));
		buttons[4][7].setIcon(Icon.returnIcon("black_king"));
		buttons[5][7].setIcon(Icon.returnIcon("black_bishop"));
		buttons[6][7].setIcon(Icon.returnIcon("black_knight"));
		buttons[7][7].setIcon(Icon.returnIcon("black_rook"));
	}
	
	/**
	 * Metodo che aggiunge una alla volta alla ChessboardView le componenti che
	 * formano la scacchiera (caselle (bottoni), labels dei numeri e labels delle
	 * lettere)
	 * 
	 * @see #letterLabel(int j)
	 * @see #numberLabel(int i)
	 */
	private void addComponents() {
		
		// prima riga di lettere
		for (int i = 0; i < 10; i++)
			add(letterLabel(i));
			
		/* la scacchiera ha l'oringine 0,0 in basso a sinistra
		 * mentre la griglia si riempie di elementi dall'alto
		 * percio' il for delle x (j) cresce mentre popoliamo le celle (bottoni)
		 * mentre il for delle y (i) decresce man mano che passiamo di riga in riga */
		for (int i = 7; i >= 0; i--)
			for (int j = 0; j < 10; j++) {
				if (j == 0 || j == 9)
					add(numberLabel(i + 1));
				else
					add(buttons[j - 1][i]);
			}
		
		// ultima riga (uguale alla prima)
		for (int i = 0; i < 10; i++)
			add(letterLabel(i));
	}
	
	/**
	 * Questo metodo crea una label e vi setta un'icona di una lettera
	 * 
	 * @param j intero che serve per calcolare il nome dell'immagine della lettera
	 * 		  da posizionare sulla label
	 * @return una label con sopra l'icona di una lettera
	 * @see Icon
	 */
	private JLabel letterLabel(int j) {	
		JLabel label = new JLabel();
		if (j > 0 && j < 9)
			label.setIcon(Icon.returnIcon(Character.toString((char) ('A' + j - 1))));
		
		label.setOpaque(true);
		label.setBackground(labelBackground);
		
		return label;
	}
	
	/**
	 * Questo metodo crea una label e vi setta un'icona di un numero
	 * 
	 * @param i intero che serve per calcolare il nome dell'immagine del numero
	 * 		  da posizionare sulla label
	 * @return una label con sopra l'icona un numero
	 * @see Icon
	 */
	private JLabel numberLabel(int i) {
		JLabel label = new JLabel();
		label.setIcon(Icon.returnIcon(Integer.toString(i)));
		label.setOpaque(true);
		label.setBackground(labelBackground);
		
		return label;	
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void newMatch() {
		controller.newMatch();
		moved();
		initButtonIcons();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setMode() {
		easyMode = !easyMode;
		controller.setMode(easyMode);
		
		if (easyMode && availableMoves != null) {		
			for (int i = 0; i < 8; i++)
				for (int j = 0; j < 8; j++)
					if (availableMoves.contains((i * 10) + j))
						buttons[i][j].available();
//			if (wrongXY != -1)
//				buttons[wrongXY / 10][wrongXY % 10].wrong();
		}
		else
			for (int i = 0; i < 8; i++)
				for (int j = 0; j < 8; j++)
					if (buttons[i][j].getBackground() != Color.YELLOW)
						buttons[i][j].highlightOff();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void selected(int x, int y, ArrayList<Integer> availableMoves) {
		
		if (easyMode && this.availableMoves != null) { // non e' la prima selezione del turno
			buttons[selectedXY / 10][selectedXY % 10].highlightOff(); //"spengo" la gialla
			if (wrongXY != -1)
				buttons[wrongXY / 10][wrongXY % 10].highlightOff(); //tolgo l'highlight rosso da un eventuale errore precedente
			
			for (int xy : this.availableMoves) //"spengo" tutte le verdi
				buttons[xy / 10][xy % 10].highlightOff();
		}
		
		buttons[x][y].selected(); // la casella selezionata diventa gialla
		this.availableMoves = availableMoves;
		selectedXY = x * 10 + y;
		wrongXY = -1;
		
		if (easyMode)	// se sono in modalita' facile evidenzio le mosse disponibili	
			for (int xy : this.availableMoves)
				buttons[xy / 10][xy % 10].available();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void moved() {
		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++)
				buttons[i][j].highlightOff();
		
		wrongXY = -1;
		selectedXY = -1;
		availableMoves = null;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void wrongMove(int x, int y) {
		if (easyMode) {
			if (wrongXY != -1) // se non e' il primo errore di un turno
				buttons[wrongXY / 10][wrongXY % 10].highlightOff(); //tolgo l'highlight rosso da un eventuale errore precedente
			
			// salvo le coordinate errate per settare l'highlight rosso se qualcuno impostasse i suggerimenti durante
			// questa fase del gioco e per togliere l'highlight rosso al prossimo eventuale errore
			wrongXY = x * 10 + y;
			buttons[x][y].wrong();
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void selfSelect(int x, int y) {
		if (wrongXY != -1)
			buttons[wrongXY / 10][wrongXY % 10].highlightOff();
		wrongXY = -1;
	}
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void move(int sx, int sy, int tx, int ty) {
		buttons[tx][ty].setIcon(buttons[sx][sy].getIcon());
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void move(int sx, int sy) {
		buttons[sx][sy].setIcon(null);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void promotion(boolean team1) {
		new PromotionWindow(team1, controller).setVisible(true);
		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void promotion(int piece, int x, int y) {
		String team = (y == 7) ? "white_" : "black_";
		/* non ho bisogno  di ricevere il team come argomento del metodo
		 * perche' so gia' che se la coordinata y di dove si trova il pedone
		 * e' == 7 allora si tratta di un pedone della squadra bianca, altrimenti
		 * la y varra' 0 e il pedone e' della squadra nera. */
		
		switch (piece) {
		case 0:
			buttons[x][y].setIcon(Icon.returnIcon(team + "queen"));
			break;
		case 1:
			buttons[x][y].setIcon(Icon.returnIcon(team + "rook"));
			break;
		case 2:
			buttons[x][y].setIcon(Icon.returnIcon(team + "bishop"));
			break;
		case 3:
			buttons[x][y].setIcon(Icon.returnIcon(team + "knight"));
			break;
		}	
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void mate(boolean team1) {
		if (JOptionPane.showConfirmDialog(null, "<html><div align=center>SCACCO MATTO!<br>" +
				((team1) ? "Giocatore 2" : "Giocatore 1") + " vince.<br>Vuoi fare un'altra partita?</div></html>",
				"SCACCO MATTO", JOptionPane.YES_NO_OPTION) == 1)
			System.exit(0);
		else
			newMatch();
		
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void draw(int draw) {
		String message = "";
		switch (draw) {
		case 0:
			message = "STALLO";
			break;
		case 1:
			message = "POSIZIONE MORTA";
			break;
		case 2:
			message = "REGOLA DELLE 50 MOSSE";
			break;
		}
		if (JOptionPane.showConfirmDialog(null, "<html><div align=center>PATTA PER " + message + "!<br>" +
				"Vuoi fare un'altra partita?</div></html>",
				"PATTA!", JOptionPane.YES_NO_OPTION) == 1)
			System.exit(0);
		else
			newMatch();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void noCoordinatesThanks() {
		JOptionPane.showMessageDialog(null,
			    "Seleziona un pezzo da sostituire al pedone!",
			    "Errore!",
			    JOptionPane.ERROR_MESSAGE);	
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void sendMessage(boolean team1, boolean check) {
		MainWindow.setText("E` IL TUO TURNO SQUADRA " + ((team1) ? "BIANCA." : "NERA.")
				+ ((check) ? " ATTENZIONE, SEI SOTTO SCACCO!" : ""));		
	}
	
	/**
	 * Utilizzato solo a fini di Test tramite JUnit.
	 * 
	 * @return l'oggetto di interfaccia Controller associato.
	 */
	public Controller getController() {
		return controller;
	}
	
	private static final long serialVersionUID = 1L;

}
