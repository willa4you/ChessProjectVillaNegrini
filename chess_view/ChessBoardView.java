package chess_view;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class ChessBoardView extends JFrame {

	public static final int WIDTH = 700;
	public static final int HEIGHT = 700;
	
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
		
		JPanel tilesPanel = new JPanel();
		tilesPanel.setLayout(new GridLayout(10, 10));
		
		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 10; j++)		
				if (i % 2 == 1 && j % 2 == 1)
					tilesPanel.add(whiteGrid(i, j));
				else if (i % 2 == 1 && j % 2 == 0)
					tilesPanel.add(greenGrid(i, j));		
				else if (i % 2 == 0 && j % 2 == 1)
					tilesPanel.add(blueGrid(i, j));
				else				
					tilesPanel.add(redGrid(i, j));	
		
		add(tilesPanel, BorderLayout.CENTER);
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

	private Component whiteGrid(int i, int j) {
		
		if (i == 0 || i == 9)
			return letterLabel(j);
		
		if (j == 0 || j == 9)
			return numberLabel(i);
		
		JButton b = new JButton();
		b.setBackground(Color.LIGHT_GRAY);
		b.setBorderPainted(false); // i bordi del bottone non sono più visibili
		b.setOpaque(true); // rende il bottone trasparente
		
		if (i == 1 && j == 1)
			b.setIcon(Icon.returnIcon("black_rook.png"));
		else if (i == 1 && j == 3)
			b.setIcon(Icon.returnIcon("black_bishop.png"));
		else if (i == 1 && j == 5)
			b.setIcon(Icon.returnIcon("black_king.png"));
		else if (i == 1 && j == 7)
			b.setIcon(Icon.returnIcon("black_knight.png"));		
		else if (i == 7 && j < 9)
			b.setIcon(Icon.returnIcon("white_pawn.png"));
		
		b.addActionListener(event -> b.setBackground(Color.WHITE));
		// serve solo come test
		
		return b;
	}
	
	private Component greenGrid(int i, int j) {
		
		if (i == 0 || i == 9)
			return letterLabel(j);
		
		if (j == 0 || j == 9)
			return numberLabel(i);
		
		JButton b = new JButton();
		b.setBackground(Color.ORANGE);
		b.setBorderPainted(false);
		b.setOpaque(true);
		
		if (i == 1 && j == 2)
			b.setIcon(Icon.returnIcon("black_knight.png"));
		else if (i == 1 && j == 4)
			b.setIcon(Icon.returnIcon("black_queen.png"));
		else if (i == 1 && j == 6)
			b.setIcon(Icon.returnIcon("black_bishop.png"));
		else if (i == 1 && j == 8)
			b.setIcon(Icon.returnIcon("black_rook.png"));
		else if (i == 7  && j > 0)
			b.setIcon(Icon.returnIcon("white_pawn.png"));
		
		b.addActionListener(event -> b.setBackground(Color.GREEN));
		// serve solo come test
		
		return b;
	}
	
	private Component blueGrid(int i, int j) {
		
		if (i == 0 || i == 9)
			return letterLabel(j);
		
		if (j == 0 || j == 9)
			return numberLabel(i);
		
		JButton b = new JButton();
		b.setBackground(Color.ORANGE);
		b.setBorderPainted(false);
		b.setOpaque(true);
		
		if (i == 8 && j == 1)
			b.setIcon(Icon.returnIcon("white_rook.png"));
		else if (i == 8 && j == 3)
			b.setIcon(Icon.returnIcon("white_bishop.png"));
		else if (i == 8 && j == 5)
			b.setIcon(Icon.returnIcon("white_king.png"));
		else if (i == 8 && j == 7)
			b.setIcon(Icon.returnIcon("white_knight.png"));
		else if (i == 2 && j < 9)
			b.setIcon(Icon.returnIcon("black_pawn.png"));
		
		b.addActionListener(event -> b.setBackground(Color.BLUE));
		// serve solo come test
		
		return b;
	}
	
	private Component redGrid(int i, int j) {
		
		if (i == 0 || i == 9)
			return letterLabel(j);
		
		if (j == 0 || j == 9)
			return numberLabel(i);
		
		JButton b = new JButton();
		b.setBackground(Color.LIGHT_GRAY);
		b.setBorderPainted(false);
		b.setOpaque(true);
		
		if (i == 8 && j == 2)
			b.setIcon(Icon.returnIcon("white_knight.png"));
		else if (i == 8 && j == 4)
			b.setIcon(Icon.returnIcon("white_queen.png"));
		else if (i == 8 && j == 6)
			b.setIcon(Icon.returnIcon("white_bishop.png"));
		else if (i == 8 && j == 8)
			b.setIcon(Icon.returnIcon("white_rook.png"));
		else if (i == 2 && j > 0)
			b.setIcon(Icon.returnIcon("black_pawn.png"));
		
		b.addActionListener(event -> b.setBackground(Color.RED));
		// serve solo come test
		
		return b;
	}
	
	/*
	private JPanel numbersPanel() {
		
		JPanel numbersPanel = new JPanel();
		numbersPanel.setLayout(new GridLayout(8, 1));
		for (int i = 0; i < 8; i++)
			numbersPanel.add(new JLabel(" " + (8 - i) + " "), SwingConstants.CENTER);
		
		return numbersPanel;
	}
	
	private JPanel lettersPanel() {
		
		JPanel lettersPanel = new JPanel();
		lettersPanel.setLayout(new GridLayout(1, 8));
		for (int i = 0; i < 8; i++)
			//lettersPanel.add(new JLabel("         " + (char) ('A' + i) + "        "));
			lettersPanel.add(new JLabel(Character.toString((char)('A' + i)), SwingConstants.CENTER));
		
		return lettersPanel;
	}
	
	private JPanel northPanel() {
		
		JPanel northPanel = new JPanel();
		northPanel.setLayout(new BorderLayout());
		
		JPanel northLettersPanel = lettersPanel();
		northPanel.add(northLettersPanel, BorderLayout.SOUTH);
		
		// DA COMPLETARE
		
		return northPanel;
	}
	
	private JPanel southPanel() {
		
		JPanel southPanel = new JPanel();
		southPanel.setLayout(new BorderLayout());
		
		JPanel southLettersPanel = lettersPanel();
		southPanel.add(southLettersPanel, BorderLayout.NORTH);
		
		JTextField textFieldLeft = new JTextField();
		southPanel.add(textFieldLeft, BorderLayout.WEST);
		textFieldLeft.setPreferredSize(new Dimension(100, 0));
		JTextField textFieldCenter = new JTextField();
		southPanel.add(textFieldCenter, BorderLayout.CENTER);
		JTextField textFieldRight = new JTextField();
		textFieldRight.setPreferredSize(new Dimension(100, 0));
		southPanel.add(textFieldRight, BorderLayout.EAST);
		
		return southPanel;
	}
	*/

}

