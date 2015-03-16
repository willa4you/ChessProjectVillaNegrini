package chessboard;

import java.awt.*;

import javax.swing.*;

public class ConfirmWindow extends JFrame {
	
	public static final int WIDTH = 200;
	public static final int HEIGHT = 100;
	
	public ConfirmWindow() {
		
		setSize(WIDTH, HEIGHT);
		setMinimumSize(new Dimension(WIDTH, HEIGHT));
		setMaximumSize(new Dimension(WIDTH, HEIGHT));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocation(450, 100);
		setLayout(new BorderLayout());
		
		JLabel confirmLabel = new JLabel("Are you sure you want to exit?");
		confirmLabel.setBackground(Color.ORANGE);
		confirmLabel.setOpaque(true); // se non lo metto non si vede il colore sottostante
		
		add(confirmLabel, BorderLayout.CENTER);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(Color.LIGHT_GRAY);
		buttonPanel.setLayout(new FlowLayout());
		
		JButton exitButton = new JButton("Yes");
		exitButton.addActionListener(event -> System.exit(0)); // LAMBDAS
		buttonPanel.add(exitButton);
		
		JButton cancelButton = new JButton("No");
		cancelButton.addActionListener(event -> dispose());	// LAMBDAS
		buttonPanel.add(cancelButton);
		
		add(buttonPanel, BorderLayout.SOUTH);
	}

}
