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
	
	public static void main(String[] args) {
		new FinalWindow(true, Team.TEAM2).setVisible(true);
	}
	
	private boolean mate;
	private Team player;
	
	public FinalWindow(boolean mate) {
		this.mate = mate;
		setSize(ChessboardView.getWindowSide() / 3 * 2, ChessboardView.getWindowSide() / 3);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		
		add(messageLabel(), BorderLayout.NORTH);
		
		add(new JLabel("Vuoi cominciare una nuova partita?", SwingConstants.CENTER),
				BorderLayout.CENTER);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		
		JButton newMatch = new JButton("Sì");
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
	
	public FinalWindow(boolean mate, Team player) {
		this(mate);
		this.player = player;
	}
	
	private JLabel messageLabel() {
		JLabel messageLabel;
		if (mate && (player == Team.TEAM1)) {
			messageLabel = new JLabel("<html><b><font size=15>SCACCO MATTO</font></b><br>"
					+ "<br><font size=10>Giocatore1 vince</font></html>", SwingConstants.CENTER);
			return messageLabel;
		}
		else if (mate && (player == Team.TEAM2)) { 
			messageLabel = new JLabel("<html><b><font size=15>SCACCO MATTO</font></b><br>"
					+ "<br><font size=10>Giocatore2 vince</font></html>", SwingConstants.CENTER);
			return messageLabel;
		}
		else {
			messageLabel = new JLabel("<html><b><font size=15>PATTA</font></b></html>", SwingConstants.CENTER);
			return messageLabel;
		}
			
	}

	private static final long serialVersionUID = 1L;

}
