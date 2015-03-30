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
	
	private static final long serialVersionUID = 1L;
}
