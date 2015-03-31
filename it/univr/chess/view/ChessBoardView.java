package it.univr.chess.view;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.util.ArrayList;

import it.univr.chess.controller.Controller;

public class ChessboardView extends JPanel implements View {

	private final Button[][] buttons = new Button[8][8];
	private Controller controller;
	private final Color labelBackground = new Color(77, 0, 0);
	
	//wrongX e wrongY sono le coordinate dell'ultima casella errata selezionata
	private int wrongX = -1;
	private int wrongY;
	
	public ChessboardView() {
		setLayout(new GridLayout(10, 10));
		
		//creo i bottoni (caselle della scacchiera)
		createButtons();
		
		//assegno componenti alla griglia (buttons e labels)
		assignComponents();
	}
	
	private void createButtons() {
		for (int y = 0; y < buttons.length; y++)
			for (int x = 0; x < buttons[y].length; x++) {
				if((x % 2 == 0 && y % 2 == 0) || (x % 2 == 1 && y % 2 == 1))
					buttons[x][y] = new DarkButton();
				else
					buttons[x][y] = new LightButton();

				buttonListener(x, y);
			}
			
			assignButtonsIcons();
	}
	
	private void buttonListener(int x, int y) {	
		buttons[x][y].addActionListener(event -> controller.onClick((x * 10) + y));
	}
	
	private void assignButtonsIcons() {
		
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
	
	private void assignComponents() {
		
		//prima riga di lettere
		for (int i = 0; i < 10; i++)
			add(letterLabel(i));
			
		//la scacchiera ha l'oringine 0,0 in basso a sinistra
		//mentre la griglia si riempie di elementi dall'alto
		//percio' il for delle x (j) cresce mentre popoliamo le celle
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
	
	private Component letterLabel(int j) {	
		JLabel label = new JLabel();
		if(j > 0 && j < 9)
			label.setIcon(Icon.returnIcon(Character.toString((char)('A' + j - 1))));
		
		label.setOpaque(true);
		label.setBackground(labelBackground);
		return label;
	}
	
	private Component numberLabel(int i) {
		JLabel label = new JLabel();
		label.setIcon(Icon.returnIcon(Integer.toString(i)));
		label.setOpaque(true);
		label.setBackground(labelBackground);
		return label;
		
	}
	
	@Override
	public void setController(Controller controller) {
		this.controller = controller;
	}
	
	@Override
	public void selected(int x, int y, ArrayList<Integer> availableMoves) {
		buttons[x][y].selected();
		
		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++)
				if(availableMoves.contains(new Integer((i * 10) + j)))
					buttons[i][j].available();	
	}
	
	@Override
	public void moved() {
		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++)
				buttons[i][j].highlightOff();
		//quando la mossa e' eseguita se avessi fatto prima un errore
		//una mossa errata del mio avversario in seguito porterebbe a colorare
		//la sua casella errata e decolorare l'ultimo mio errore per via di 
		//wrongX e wrongY settate, percio' setto wrongX a 9 che verra' ignorato
		wrongX = -1;
	}
	
	public void wrongMove(int x, int y) {		
		if (wrongX != -1) //se non e' il primo errore di un turno
			buttons[wrongX][wrongY].highlightOff();//tolgo l'highlight rosso da un eventuale errore precedente
			
		wrongX = x;// le salvo per togliere l'higlight rosso al prossimo eventuale errore
		wrongY = y;
		buttons[x][y].wrong();
	}
	
	public void selfSelect(int x, int y) {
		if (wrongX != -1)
			buttons[wrongX][wrongY].highlightOff();
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

	@Override
	public void mate(boolean team1) {
		if (JOptionPane.showConfirmDialog(null, "<html><div align=center>SCACCO MATTO!<br>" +
				((team1) ? "Giocatore 2" : "Giocatore 1") + " vince.<br>Vuoi fare un'altra partita?</div></html>",
				"SCACCO MATTO", JOptionPane.YES_NO_OPTION) == 1)
			System.exit(0);
		//else
			//nuova partita
		
	}
	
	@Override
	public void staleMate() {
		if (JOptionPane.showConfirmDialog(null, "<html><div align=center>PATTA!<br>" +
				"Vuoi fare un'altra partita?</div></html>",
				"PATTA", JOptionPane.YES_NO_OPTION) == 1)
			System.exit(0);
		//else
			//nuova partita
	}
	
	@Override
	public void noThanks() {
		JOptionPane.showMessageDialog(null,
			    "Seleziona un pezzo da sostituire al pedone!",
			    "Errore!",
			    JOptionPane.ERROR_MESSAGE);	
	}
	
	private static final long serialVersionUID = 1L;

	@Override
	public void sendMessage(boolean team1, boolean check) {
		MainWindow.setText("E IL TUO TURNO SQUADRA " + ((team1) ? "BIANCA." : "NERA.")
				+ ((check) ? " ATTENZIONE, SEI SOTTO SCACCO!" : ""));		
	}

}
