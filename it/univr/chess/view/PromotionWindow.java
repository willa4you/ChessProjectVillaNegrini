package it.univr.chess.view;

import it.univr.chess.model.Team;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class PromotionWindow extends JFrame {

	private Team team; 
	private Button[] promotionButtons;
	
	public static void main(String[] args) {
		new PromotionWindow(Team.Team2).setVisible(true);
	}
	
	public PromotionWindow(Team team) {
		super("Promotion Window");
		this.team = team;
		setSize(ChessBoardView.getWindowSide() / 5 * 2, ChessBoardView.getWindowSide() / 6);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		initPromotionButtons();
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1, 4));
		for (int i = 0; i < 4; i++)
			panel.add(promotionButtons[i]);
		
		add(panel);
	}

	private void initPromotionButtons() {
		promotionButtons = new Button[4];
		for (int i = 0; i < 4; i++)
			promotionButtons[i] = new Button(i);
		
		for (int i = 0; i < 4; i++) {
			if (team == Team.Team1)
				if (promotionButtons[i].getValue() == 0)
					promotionButtons[i].setIcon(Icon.returnIcon(Icon.WHITE_QUEEN));
				else if (promotionButtons[i].getValue() == 1)
					promotionButtons[i].setIcon(Icon.returnIcon(Icon.WHITE_ROOK));
				else if (promotionButtons[i].getValue() == 2)
					promotionButtons[i].setIcon(Icon.returnIcon(Icon.WHITE_BISHOP));
				else
					promotionButtons[i].setIcon(Icon.returnIcon(Icon.WHITE_KNIGHT));
			else
				if (promotionButtons[i].getValue() == 0)
					promotionButtons[i].setIcon(Icon.returnIcon(Icon.BLACK_QUEEN));
				else if (promotionButtons[i].getValue() == 1)
					promotionButtons[i].setIcon(Icon.returnIcon(Icon.BLACK_ROOK));
				else if (promotionButtons[i].getValue() == 2)
					promotionButtons[i].setIcon(Icon.returnIcon(Icon.BLACK_BISHOP));
				else
					promotionButtons[i].setIcon(Icon.returnIcon(Icon.BLACK_KNIGHT));
			
			promotionButtonsListener(i);
		}
	}
	
	private void promotionButtonsListener(int i) {
		promotionButtons[i].addActionListener(event -> {
			// da completare
			dispose();
		});
	}

	private static final long serialVersionUID = 1L;
}
