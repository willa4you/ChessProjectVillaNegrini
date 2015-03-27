package it.univr.chess.view;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class ChessBoardView extends JFrame {

	private static int firstClickValue = -1;
	private static int secondClickValue = -1;
	public static boolean confirmWindowOpen = false;
	
	private Button[][] buttons;
	
	public ChessBoardView() {	
		super("Scacchiera");												// 20 pixel del JMenuBar
		setSize(getWindowSide(), getWindowSide() + 20);
		setResizable(false);
		setLocationRelativeTo(null); // fa apparire la finestra al centro dello schermo
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				if (!confirmWindowOpen) {
					confirmWindowOpen = true;
					new ConfirmWindow().setVisible(true);
				}
			}
		});
		
		initButtons();	
		
		add(mainPanel());
		
		setJMenuBar(menuBar());
		
		// buttons[4][1].setIcon(Icon.returnIcon("white_pawn.png"));
		// serve solo come test
		// System.out.println(buttons[7][6].getValue());
		// serve solo come test
	}

	private void initButtons() {
		buttons = new Button[8][8];
		for (int h = 0; h < buttons.length; h++)
			for (int k = 0; k < buttons[h].length; k++)
				buttons[h][k] = new Button((h * 10) + k);	
	}
	
	private JPanel mainPanel() {
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
		
		return tilesPanel;
	}
	
	private JMenuBar menuBar() {
		JMenu optionMenu = new JMenu("Options");
		
		JMenuItem resizeChoice = new JMenuItem("Resize");
		resizeChoice.addActionListener(event -> {
			setResizable(true);
			setSize(getWindowSide(), getWindowSide() + 20);
			setResizable(false);
			setLocationRelativeTo(null);
			for (int i = 0; i < buttons.length; i++)
				for (int j = 0; j < buttons[i].length; j++)
					if (buttons[i][j].getIcon() != null) {
						// ho bisogno di sapere il pezzo associato all'immagine
					}
		});
		optionMenu.add(resizeChoice);
		
		JMenuItem restartChoice = new JMenuItem("Restart");
		optionMenu.add(restartChoice);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.add(optionMenu);
		
		return menuBar;
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
	
	private void changeIcon(JButton a, JButton b) {	
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
				
				// se clicco due volte sullo stesso bottone..
				if (firstClickValue == secondClickValue) {
					// ..resetto i valori delle cliccate e ritorno
					firstClickValue = secondClickValue = -1;
					return;
				}
				
				for (int a = 0; a < 8; a++)
					for (int b = 0; b < 8; b++)
						// cerco il bottone che ho cliccato per primo..
						if (firstClickValue == buttons[a][b].getValue()) {
							// ..e porto la sua icona sul secondo bottone che ho cliccato
							// (non avviene uno swap delle icone)
							changeIcon(buttons[a][b], buttons[i][j]);
							
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
	
	private Component forthGrid(int i, int j) {	
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
	
	public static int getWindowSide() {
		return (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 3 * 2);
	}
	
	private static final long serialVersionUID = 1L;
	
}
