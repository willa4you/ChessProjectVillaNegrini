package it.univr.chess.view;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

public class ConfirmWindow extends JFrame {
	
	public ConfirmWindow() {
		
		setSize(ChessboardView.getWindowSide() / 5 * 2, ChessboardView.getWindowSide() / 6);
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
		
		add(new JLabel("Sicuro di voler uscire?", SwingConstants.CENTER),
				BorderLayout.CENTER);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		
		JButton exitButton = new JButton("Sì");
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
