package chess_view;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Scacchiera extends JFrame {

	public static final int WIDTH = 700;
	public static final int HEIGHT = 700;
	
	public static void main(String[] args) {
		
		new Scacchiera().setVisible(true);
	}
	
	public Scacchiera() {
		
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
		tilesPanel.setLayout(new GridLayout(8, 8));
		
		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++)
				if (i % 2 == 0 && j % 2 == 0)
					tilesPanel.add(a8_g8_a2_g2_Grid(i, j));
				else if (i % 2 == 0 && j % 2 == 1)
					tilesPanel.add(a7_g7_a1_g1_Grid(i, j));		
				else if (i % 2 == 1 && j % 2 == 0)
					tilesPanel.add(b8_h8_b2_h2_Grid(i, j));
				else				
					tilesPanel.add(b7_h7_b1_h1_Grid(i, j));

		add(tilesPanel, BorderLayout.CENTER);
		
		add(numbersPanel(), BorderLayout.WEST);
		
		add(numbersPanel(), BorderLayout.EAST);
		
		add(northPanel(), BorderLayout.NORTH);
		
		add(southPanel(), BorderLayout.SOUTH);
				
	}

	private JButton a8_g8_a2_g2_Grid(int i, int j) {
		
		JButton b = new JButton();
		b.setBackground(Color.LIGHT_GRAY);
		b.setBorderPainted(false); // i bordi del bottone non sono più visibili
		b.setOpaque(true); // rende il bottone trasparente
		
		if (i == 0 && j == 0)
			b.setIcon(Icon.blackRook());
		else if (i == 0 && j == 2)
			b.setIcon(Icon.blackBishop());
		else if (i == 0 && j == 4)
			b.setIcon(Icon.blackKing());
		else if (i == 0 && j == 6)
			b.setIcon(Icon.blackKnight());		
		else if (i == 6)
			b.setIcon(Icon.whitePawn());
		
		b.addActionListener(event -> b.setBackground(Color.WHITE));
		// serve solo come test
		
		return b;
	}
	
	private JButton a7_g7_a1_g1_Grid(int i, int j) {
		
		JButton b = new JButton();
		b.setBackground(Color.ORANGE);
		b.setBorderPainted(false);
		b.setOpaque(true);
		
		if (i == 0 && j == 1)
			b.setIcon(Icon.blackKnight());
		else if (i == 0 && j == 3)
			b.setIcon(Icon.blackQueen());
		else if (i == 0 && j == 5)
			b.setIcon(Icon.blackBishop());
		else if (i == 0 && j == 7)
			b.setIcon(Icon.blackRook());
		else if (i == 6)
			b.setIcon(Icon.whitePawn());
		
		b.addActionListener(event -> b.setBackground(Color.GREEN));
		// serve solo come test
		
		return b;
	}
	
	private JButton b8_h8_b2_h2_Grid(int i, int j) {
		
		JButton b = new JButton();
		b.setBackground(Color.ORANGE);
		b.setBorderPainted(false);
		b.setOpaque(true);
		
		if (i == 7 && j == 0)
			b.setIcon(Icon.whiteRook());
		else if (i == 7 && j == 2)
			b.setIcon(Icon.whiteBishop());
		else if (i == 7 && j == 4)
			b.setIcon(Icon.whiteKing());
		else if (i == 7 && j == 6)
			b.setIcon(Icon.whiteKnight());
		else if (i == 1)
			b.setIcon(Icon.blackPawn());
		
		b.addActionListener(event -> b.setBackground(Color.BLUE));
		// serve solo come test
		
		return b;
	}
	
	private JButton b7_h7_b1_h1_Grid(int i, int j) {
		
		JButton b = new JButton();
		b.setBackground(Color.LIGHT_GRAY);
		b.setBorderPainted(false);
		b.setOpaque(true);
		
		if (i == 7 && j == 1)
			b.setIcon(Icon.whiteKnight());
		else if (i == 7 && j == 3)
			b.setIcon(Icon.whiteQueen());
		else if (i == 7 && j == 5)
			b.setIcon(Icon.whiteBishop());
		else if (i == 7 && j == 7)
			b.setIcon(Icon.whiteRook());
		else if (i == 1)
			b.setIcon(Icon.blackPawn());
		
		b.addActionListener(event -> b.setBackground(Color.RED));
		// serve solo come test
		
		return b;
	}
	
	private JPanel numbersPanel() {
		
		JPanel numbersPanel = new JPanel();
		numbersPanel.setLayout(new GridLayout(8, 1));
		for (int i = 0; i < 8; i++)
			numbersPanel.add(new JLabel(" " + (8 - i) + " "));
		
		return numbersPanel;
	}
	
	private JPanel lettersPanel() {
		
		JPanel lettersPanel = new JPanel();
		lettersPanel.setLayout(new FlowLayout());
		for (int i = 0; i < 8; i++)
			lettersPanel.add(new JLabel("         " + (char) ('A' + i) + "        "));
		
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

}

