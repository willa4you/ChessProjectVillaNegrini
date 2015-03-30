package it.univr.chess.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import it.univr.chess.controller.Controller;

public class ChessboardPanel extends JPanel implements View {

	private Button[][] buttons;
	private Controller controller;
	
	//wrongX e wrongY sono le coordinate dell'ultima casella errata selezionata
	private int wrongX = -1;
	private int wrongY;
	
	public ChessboardPanel() {
		setLayout(new GridLayout(10, 10));
		
		
		//creo i bottoni
		createButtons();
		
		//assegno componenti alla griglia (buttons e labels)
		assignComponents();
		
		//finito di assegnare componenti alla griglia passo a
		//settare le icone dei buttons
		assignIcons();

	}
	
	private void assignComponents() {
		
		
		//prima riga

		for (int i = 0; i < 10; i++)
			add(letterLabel(i));
		
		
		//la scacchiera ha l'oringine 0,0 in basso a sinistra
		//mentre la griglia si riempie di elementi dall'alto
		//perci� il for delle x (j) cresce mentre popoliamo le celle
		//mentre il for delle y (i) decresce man mano che passiamo di riga in riga
		for (int i = 7; i >= 0; i--)
			for (int j = 0; j < 10; j++) {
				if (j == 0 || j == 9)
					add(numberLabel(i+1));
				else
					add(buttons[j-1][i]);
			}
		
		//ultima riga (uguale alla prima)
		for (int i = 0; i < 10; i++)
			add(letterLabel(i));
	}
	
	private void assignIcons() {
		
		buttons[0][0].setIcon(Icon.returnIcon("white_rook"));
		buttons[1][0].setIcon(Icon.returnIcon("white_knight"));
		buttons[2][0].setIcon(Icon.returnIcon("white_bishop"));
		buttons[3][0].setIcon(Icon.returnIcon("white_queen"));
		buttons[4][0].setIcon(Icon.returnIcon("white_king"));
		buttons[5][0].setIcon(Icon.returnIcon("white_bishop"));
		buttons[6][0].setIcon(Icon.returnIcon("white_knight"));
		buttons[7][0].setIcon(Icon.returnIcon("white_rook"));
		
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
	
	@Override
	public void setController(Controller controller) {
		this.controller = controller;
	}
	
	private void createButtons() {
		buttons = new Button[8][8];
		Color color = null;
		Color darkSquare = new Color(153, 102, 51);
		Color lightSquare = new Color(255, 255, 204);
		for (int y = 0; y < buttons.length; y++) {
			for (int x = 0; x < buttons[y].length; x++) {
				color = (color == darkSquare) ? lightSquare : darkSquare;
				buttons[x][y] = new Button((x * 10) + y, color);
				buttonListener(x, y);
			}
			color = (color == darkSquare) ? lightSquare : darkSquare;
		}
	}
	
	private void buttonListener(int x, int y) {	
		buttons[x][y].addActionListener(event -> controller.onClick(buttons[x][y].getValue()));
	}

	private Component numberLabel(int i) {
		JLabel a = new JLabel();
		a.setIcon(Icon.returnIcon("" + i));
		a.setOpaque(true);
		a.setBackground(new Color(77, 0, 0));
		return a;
		
	}

	private Component letterLabel(int j) {	
		JLabel a = new JLabel();
		if(j > 0 && j < 9)
			a.setIcon(Icon.returnIcon(Character.toString((char)('A' + j - 1))));
		
		a.setOpaque(true);
		a.setBackground(new Color(77, 0, 0));
		return a;
	}
	
	@Override
	public void selected(int x, int y, Iterable<Integer> availableMoves) {
		int avX, avY; //availables x and y
		for (int moves : availableMoves) {					
			avX = moves / 10;
			avY = moves % 10;
			if((avX % 2 == 0 && avY % 2 == 0) || (avX % 2 == 1 && avY % 2 == 1))
				highlight(avX, avY, new Color(71, 145, 71));
			else
				highlight(avX, avY, new Color(153, 255, 102));
		}
		
		highlight(x, y, Color.YELLOW);
		
		
	}
	
	@Override
	public void moved() {
		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++)
				highlightOff(i, j);
		//quando la mossa e' eseguita se avessi fatto prima un errore
		//una mossa errata del mio avversario in seguito porterebbe a colorare
		//la sua casella errata e decolorare l'ultimo mio errore per via di 
		//wrongX e wrongY settate, percio' setto wrongX a 9 che verra' ignorato
		wrongX = -1;
	}
	
	public void wrongMove(int x, int y) {		
		if (wrongX != -1) //se non e' il primo errore di un turno
			highlightOff(wrongX, wrongY); //tolgo l'highlight rosso da un eventuale errore precedente
			
		wrongX = x;// le salvo per togliere l'higlight rosso al prossimo eventuale errore
		wrongY = y;
		highlight(x, y, Color.RED);
	}
	
	public void selfSelect(int x, int y) {
		if (wrongX != -1)
			highlightOff(wrongX, wrongY);
	}
	
	@Override
	public void highlight(int x, int y, Color color) {

		buttons[x][y].setBackground(color);		
	}
	
	@Override
	public void highlightOff(int x, int y) {

		buttons[x][y].setBackground(buttons[x][y].getColor());
	}
	
	@Override
	public void move(int sx, int sy, int tx, int ty){
		buttons[tx][ty].setIcon(buttons[sx][sy].getIcon());		
	}
	
	@Override
	public void move(int sx, int sy){
		buttons[sx][sy].setIcon(null);
	}
	
	@Override
	public void promotion(boolean team1) {
		new PromotionWindow(team1, controller).setVisible(true);
		
	}

	@Override
	public void promotion(int piece, int x, int y) {
		String team = (y == 7) ? "white_" : "black_";
		
		switch(piece) {
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
	
	private static final long serialVersionUID = 1L;

}
