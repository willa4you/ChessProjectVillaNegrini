package it.univr.chess.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;

// import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import it.univr.chess.controller.Controller;

public class ChessboardPanel extends JPanel implements View {

	private Button[][] buttons;
	private Controller controller;
	// private static int firstClickValue = -1;
	// private static int secondClickValue = -1;
	
	public ChessboardPanel() {
		setLayout(new GridLayout(10, 10));
		
		createButtons();
		
		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 10; j++)		
				if (i % 2 == 1 && j % 2 == 1)
					add(firstGrid(i, j));
				else if (i % 2 == 1 && j % 2 == 0)
					add(secondGrid(i, j));		
				else if (i % 2 == 0 && j % 2 == 1)
					add(thirdGrid(i, j));
				else				
					add(fourthGrid(i, j));
	}
	
	@Override
	public void setController(Controller controller) {
		this.controller = controller;
	}
	
	private void createButtons() {
		buttons = new Button[8][8];
		for (int h = 0; h < buttons.length; h++)
			for (int k = 0; k < buttons[h].length; k++)
				buttons[h][k] = new Button((h * 10) + k);	
	}
	

	private Component numberLabel(int i) {
		return new JLabel(Integer.toString(8 - i + 1),
				SwingConstants.CENTER);
	}

	private Component letterLabel(int j) {	
		return (j > 0 && j < 9 ?
				(new JLabel(Character.toString((char)('A' + j - 1)), SwingConstants.CENTER)) :
					new JLabel());
	}
	
	private void buttonListener(int i, int j) {	
		buttons[i][j].addActionListener(event -> controller.onClick(buttons[i][j].getValue()));
	}

	private Component firstGrid(int i, int j) {	
		if (i == 0 || i == 9)
			return letterLabel(j);
		
		if (j == 0 || j == 9)
			return numberLabel(i);
		
		buttons[i - 1][j - 1].setBackground(Color.LIGHT_GRAY);
		
		if (i == 1 && j == 1)
			buttons[i - 1][j - 1].setIcon(Icon.returnIcon(Icon.BLACK_ROOK));
		else if (i == 1 && j == 3)
			buttons[i - 1][j - 1].setIcon(Icon.returnIcon(Icon.BLACK_BISHOP));
		else if (i == 1 && j == 5)
			buttons[i - 1][j - 1].setIcon(Icon.returnIcon(Icon.BLACK_KING));
		else if (i == 1 && j == 7)
			buttons[i - 1][j - 1].setIcon(Icon.returnIcon(Icon.BLACK_KNIGHT));		
		else if (i == 7 && j < 9)
			buttons[i - 1][j - 1].setIcon(Icon.returnIcon(Icon.WHITE_PAWN));
		
		buttonListener(i - 1, j - 1);
		
		return buttons[i - 1][j - 1];
	}
	
	private Component secondGrid(int i, int j) {	
		if (i == 0 || i == 9)
			return letterLabel(j);
		
		if (j == 0 || j == 9)
			return numberLabel(i);
			
		buttons[i - 1][j - 1].setBackground(Color.ORANGE);
		
		if (i == 1 && j == 2)
			buttons[i - 1][j - 1].setIcon(Icon.returnIcon(Icon.BLACK_KNIGHT));
		else if (i == 1 && j == 4)
			buttons[i - 1][j - 1].setIcon(Icon.returnIcon(Icon.BLACK_QUEEN));
		else if (i == 1 && j == 6)
			buttons[i - 1][j - 1].setIcon(Icon.returnIcon(Icon.BLACK_BISHOP));
		else if (i == 1 && j == 8)
			buttons[i - 1][j - 1].setIcon(Icon.returnIcon(Icon.BLACK_ROOK));
		else if (i == 7  && j > 0)
			buttons[i - 1][j - 1].setIcon(Icon.returnIcon(Icon.WHITE_PAWN));
		
		buttonListener(i - 1, j - 1);
		
		return buttons[i - 1][j - 1];
	}
	
	private Component thirdGrid(int i, int j) {	
		if (i == 0 || i == 9)
			return letterLabel(j);
		
		if (j == 0 || j == 9)
			return numberLabel(i);
		
		buttons[i - 1][j - 1].setBackground(Color.ORANGE);
		
		if (i == 8 && j == 1)
			buttons[i - 1][j - 1].setIcon(Icon.returnIcon(Icon.WHITE_ROOK));
		else if (i == 8 && j == 3)
			buttons[i - 1][j - 1].setIcon(Icon.returnIcon(Icon.WHITE_BISHOP));
		else if (i == 8 && j == 5)
			buttons[i - 1][j - 1].setIcon(Icon.returnIcon(Icon.WHITE_KING));
		else if (i == 8 && j == 7)
			buttons[i - 1][j - 1].setIcon(Icon.returnIcon(Icon.WHITE_KNIGHT));
		else if (i == 2 && j < 9)
			buttons[i - 1][j - 1].setIcon(Icon.returnIcon(Icon.BLACK_PAWN));
		
		buttonListener(i - 1, j - 1);
		
		return buttons[i - 1][j - 1];
	}
	
	private Component fourthGrid(int i, int j) {	
		if (i == 0 || i == 9)
			return letterLabel(j);
		
		if (j == 0 || j == 9)
			return numberLabel(i);
		
		buttons[i - 1][j - 1].setBackground(Color.LIGHT_GRAY);
		
		if (i == 8 && j == 2)
			buttons[i - 1][j - 1].setIcon(Icon.returnIcon(Icon.WHITE_KNIGHT));
		else if (i == 8 && j == 4)
			buttons[i - 1][j - 1].setIcon(Icon.returnIcon(Icon.WHITE_QUEEN));
		else if (i == 8 && j == 6)
			buttons[i - 1][j - 1].setIcon(Icon.returnIcon(Icon.WHITE_BISHOP));
		else if (i == 8 && j == 8)
			buttons[i - 1][j - 1].setIcon(Icon.returnIcon(Icon.WHITE_ROOK));
		else if (i == 2 && j > 0)
			buttons[i - 1][j - 1].setIcon(Icon.returnIcon(Icon.BLACK_PAWN));
		
		buttonListener(i - 1, j - 1);
		
		return buttons[i - 1][j - 1];
	}
	
	private static final long serialVersionUID = 1L;
}
