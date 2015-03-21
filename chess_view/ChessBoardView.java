package chess_view;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class ChessBoardView extends JFrame {

	private static final int WIDTH = 700;
	private static final int HEIGHT = 700;
	
	private static byte firstClickValue = -1;
	private static byte secondClickValue = -1;
	
	private Button[][] buttons = new Button[8][8];
	
	public static void main(String[] args) {
		
		new ChessBoardView().setVisible(true);
	}
	
	public ChessBoardView() {
		
		super("Scacchiera");
		setSize(WIDTH, HEIGHT);
		setMinimumSize(new Dimension(WIDTH, HEIGHT)); // la finestra non si può nè
		setMaximumSize(new Dimension(WIDTH, HEIGHT)); // rimpicciolire nè ingrandire
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setLocation(500, 50); // dove fare apparire la finestra sullo schermo
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				new ConfirmWindow().setVisible(true);
			}
		});
		setLayout(new BorderLayout());
		
		for (byte h = 0; h < buttons.length; h++)
			for (byte k = 0; k < buttons[h].length; k++)
				buttons[h][k] = new Button((byte) ((h * 10) + k));
		
		JPanel tilesPanel = new JPanel();
		tilesPanel.setLayout(new GridLayout(10, 10));
		
		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 10; j++)		
				if (i % 2 == 1 && j % 2 == 1)
					tilesPanel.add(firstGrid(i, j));
				else if (i % 2 == 1 && j % 2 == 0)
					tilesPanel.add(secondGrid(i, j));		
				else if (i % 2 == 0 && j % 2 == 1)
					tilesPanel.add(thirdGrid(i, j));
				else				
					tilesPanel.add(forthGrid(i, j));	
		
		add(tilesPanel, BorderLayout.CENTER);
		
		// buttons[4][1].setIcon(Icon.returnIcon("white_pawn.png"));
		// serve solo come test
		System.out.println(buttons[7][6].getValue());
		// serve solo come test
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
	
	private void swapIcon(JButton a, JButton b) {
		
		// se entrambi i bottoni cliccati danno getIcon() diverso da null,
		// il metodo non fa nulla;
		
		if (a.getIcon() == null) {
			a.setIcon(b.getIcon());
			b.setIcon(null);
		}
		else if (b.getIcon() == null) {
			b.setIcon(a.getIcon());
			a.setIcon(null);
		}
	}
	
	private void buttonListener(int i, int j) {
		
		buttons[i][j].addActionListener(event -> {
			
			if (firstClickValue == -1 && (buttons[i][j].getIcon() != null))			
				firstClickValue = buttons[i][j].getValue();			
			else if (firstClickValue != -1) {				
				secondClickValue = buttons[i][j].getValue();
				
				// vero se clicco due volte sullo stesso bottone
				if (firstClickValue == secondClickValue) {
					firstClickValue = secondClickValue = -1;
					return;
				}
				
				for (int a = 0; a < 8; a++)
					for (int b = 0; b < 8; b++)
						if (firstClickValue == buttons[a][b].getValue()) {
							swapIcon(buttons[a][b], buttons[i][j]);
							
							firstClickValue = secondClickValue = -1;
						}
			}
		});
		
	}

	private Component firstGrid(int i, int j) {
		
		if (i == 0 || i == 9)
			return letterLabel(j);
		
		if (j == 0 || j == 9)
			return numberLabel(i);
		
		buttons[i - 1][j - 1].setBackground(Color.LIGHT_GRAY);
		
		if (i == 1 && j == 1)
			buttons[i - 1][j - 1].setIcon(Icon.returnIcon("black_rook.png"));
		else if (i == 1 && j == 3)
			buttons[i - 1][j - 1].setIcon(Icon.returnIcon("black_bishop.png"));
		else if (i == 1 && j == 5)
			buttons[i - 1][j - 1].setIcon(Icon.returnIcon("black_king.png"));
		else if (i == 1 && j == 7)
			buttons[i - 1][j - 1].setIcon(Icon.returnIcon("black_knight.png"));		
		else if (i == 7 && j < 9)
			buttons[i - 1][j - 1].setIcon(Icon.returnIcon("white_pawn.png"));
		
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
			buttons[i - 1][j - 1].setIcon(Icon.returnIcon("black_knight.png"));
		else if (i == 1 && j == 4)
			buttons[i - 1][j - 1].setIcon(Icon.returnIcon("black_queen.png"));
		else if (i == 1 && j == 6)
			buttons[i - 1][j - 1].setIcon(Icon.returnIcon("black_bishop.png"));
		else if (i == 1 && j == 8)
			buttons[i - 1][j - 1].setIcon(Icon.returnIcon("black_rook.png"));
		else if (i == 7  && j > 0)
			buttons[i - 1][j - 1].setIcon(Icon.returnIcon("white_pawn.png"));
		
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
			buttons[i - 1][j - 1].setIcon(Icon.returnIcon("white_rook.png"));
		else if (i == 8 && j == 3)
			buttons[i - 1][j - 1].setIcon(Icon.returnIcon("white_bishop.png"));
		else if (i == 8 && j == 5)
			buttons[i - 1][j - 1].setIcon(Icon.returnIcon("white_king.png"));
		else if (i == 8 && j == 7)
			buttons[i - 1][j - 1].setIcon(Icon.returnIcon("white_knight.png"));
		else if (i == 2 && j < 9)
			buttons[i - 1][j - 1].setIcon(Icon.returnIcon("black_pawn.png"));
		
		buttonListener(i - 1, j - 1);
		
		return buttons[i - 1][j - 1];
	}
	
	private Component forthGrid(int i, int j) {
		
		if (i == 0 || i == 9)
			return letterLabel(j);
		
		if (j == 0 || j == 9)
			return numberLabel(i);
		
		buttons[i - 1][j - 1].setBackground(Color.LIGHT_GRAY);
		
		if (i == 8 && j == 2)
			buttons[i - 1][j - 1].setIcon(Icon.returnIcon("white_knight.png"));
		else if (i == 8 && j == 4)
			buttons[i - 1][j - 1].setIcon(Icon.returnIcon("white_queen.png"));
		else if (i == 8 && j == 6)
			buttons[i - 1][j - 1].setIcon(Icon.returnIcon("white_bishop.png"));
		else if (i == 8 && j == 8)
			buttons[i - 1][j - 1].setIcon(Icon.returnIcon("white_rook.png"));
		else if (i == 2 && j > 0)
			buttons[i - 1][j - 1].setIcon(Icon.returnIcon("black_pawn.png"));
		
		buttonListener(i - 1, j - 1);
		
		return buttons[i - 1][j - 1];
	}
	
}
