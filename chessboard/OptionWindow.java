package chessboard;

import java.awt.*;

import javax.swing.*;

public class OptionWindow extends JFrame {
	
	public static final int WIDTH = 300;
	public static final int HEIGHT = 200;
	
	public static void main(String[] args) {
		
		new OptionWindow().setVisible(true);
	}
	
	public OptionWindow() {
		
		super("Option Window");
		setSize(WIDTH, HEIGHT);
		setMinimumSize(new Dimension(WIDTH, HEIGHT));
		setMaximumSize(new Dimension(WIDTH, HEIGHT));
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setLocation(50, 50);
		setLayout(new BorderLayout());
		
		/*
		JPanel panelNorth = new JPanel();
		panelNorth.setLayout(new BorderLayout());
		
		JPanel panelSouth = new JPanel();
		panelSouth.setBackground(Color.RED);
		panelSouth.setLayout(new BorderLayout());
		
		JTextField playerTurn = new JTextField();
		
		panelSouth.add(playerTurn, BorderLayout.NORTH);
		
		JLabel emptyLabel = new JLabel();
		emptyLabel.setBackground(Color.WHITE);
		playerTurn.add(emptyLabel, BorderLayout.CENTER);
		
		JTextField worningField = new JTextField();
		
		panelSouth.add(worningField, BorderLayout.SOUTH);
		
		add(panelNorth, BorderLayout.NORTH);
		
		add(panelSouth, BorderLayout.SOUTH);
		*/
	
	}

}
