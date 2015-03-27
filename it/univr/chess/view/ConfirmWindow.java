package it.univr.chess.view;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

public class ConfirmWindow extends JFrame {
	
	public static final int WIDTH = 200;
	public static final int HEIGHT = 100;
	
	public ConfirmWindow() {
		
		setSize(WIDTH, HEIGHT);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				ChessboardView.confirmWindowOpen = false;
			}
		});
		
		
		JLabel confirmLabel = new JLabel("Are you sure you want to exit?");
		confirmLabel.setBackground(Color.ORANGE);
		confirmLabel.setOpaque(true); // se non lo metto non si vede il colore sottostante
		
		add(confirmLabel, BorderLayout.CENTER);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(Color.LIGHT_GRAY);
		buttonPanel.setLayout(new FlowLayout());
		
		JButton exitButton = new JButton("Yes");
		exitButton.addActionListener(event -> System.exit(0));
		buttonPanel.add(exitButton);
		
		JButton cancelButton = new JButton("No");
		cancelButton.addActionListener(event -> {
			ChessboardView.confirmWindowOpen = false;
			dispose();
		});
		buttonPanel.add(cancelButton);
		
		add(buttonPanel, BorderLayout.SOUTH);
	}
	
	private static final long serialVersionUID = 1L;

}
