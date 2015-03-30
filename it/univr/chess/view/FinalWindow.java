package it.univr.chess.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import it.univr.chess.model.Team;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class FinalWindow extends JFrame {
	
	// true = matto, false = patta
	private boolean mate;
	private Team player;
	// se mate = true, devo sapere chi dei due
	// player ha vinto
	
	// in caso patta passo al costruttore: false, null
	public FinalWindow(boolean mate, Team player) {
		this.mate = mate;
		this.player = player;
		
		setSize(ChessboardView.getWindowSide() / 3 * 2, ChessboardView.getWindowSide() / 3);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		
		add(messageLabel(), BorderLayout.NORTH);
		
		add(new JLabel("<html><font size=4>Vuoi cominciare una nuova partita?</font></html>", SwingConstants.CENTER),
				BorderLayout.CENTER);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		
		JButton newMatch = new JButton("Si");
		newMatch.addActionListener(event -> {
			// nuova partita
			// TODO
		});
		buttonPanel.add(newMatch);
		
		JButton exitButton = new JButton("No");
		exitButton.addActionListener(event -> System.exit(0));
		buttonPanel.add(exitButton);
		
		add(buttonPanel, BorderLayout.SOUTH);
		
	}
	
	private JLabel messageLabel() {
		JLabel messageLabel;
		if (mate) {
			messageLabel = new JLabel("<html><p align=center><b><font size=8>SCACCO MATTO</font></b>"
					+ "<br><font size=6>" + ((player == Team.TEAM1) ? "Giocatore1" : "Giocatore2")
					+ " vince</font></p></html>", SwingConstants.CENTER);
			return messageLabel;
		}
		else {
			messageLabel = new JLabel("<html><b><font size=8>PATTA</font></b></html>", SwingConstants.CENTER);
			return messageLabel;
		}
			
	}

	private static final long serialVersionUID = 1L;

}
