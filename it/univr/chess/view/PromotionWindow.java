package it.univr.chess.view;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import it.univr.chess.controller.Controller;

public class PromotionWindow extends JFrame {

	private Button[] promotionButtons;
	private final boolean team1;
	private final Controller controller;
	
	public PromotionWindow(boolean team1, Controller controller) {
		super("Promuovi il pedone");
		this.team1 = team1;
		this.controller = controller;
		setSize(ChessboardView.getWindowSide() / 5 * 2, ChessboardView.getWindowSide() / 6);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		initPromotionButtons();
		
		JPanel promotionPanel = new JPanel();
		promotionPanel.setLayout(new GridLayout(1, 4));
		for (int i = 0; i < 4; i++)
			promotionPanel.add(promotionButtons[i]);
		
		add(promotionPanel);
	}

	private void initPromotionButtons() {
		promotionButtons = new Button[4];
		for (int i = 0; i < 4; i++)
			promotionButtons[i] = new Button(i, null);
		
		for (int i = 0; i < 4; i++) {
			if (team1)
				if (promotionButtons[i].getValue() == 0)
					promotionButtons[i].setIcon(Icon.returnIcon("white_queen"));
				else if (promotionButtons[i].getValue() == 1)
					promotionButtons[i].setIcon(Icon.returnIcon("white_rook"));
				else if (promotionButtons[i].getValue() == 2)
					promotionButtons[i].setIcon(Icon.returnIcon("white_bishop"));
				else
					promotionButtons[i].setIcon(Icon.returnIcon("white_knight"));
			else
				if (promotionButtons[i].getValue() == 0)
					promotionButtons[i].setIcon(Icon.returnIcon("black_queen"));
				else if (promotionButtons[i].getValue() == 1)
					promotionButtons[i].setIcon(Icon.returnIcon("black_rook"));
				else if (promotionButtons[i].getValue() == 2)
					promotionButtons[i].setIcon(Icon.returnIcon("black_bishop"));
				else
					promotionButtons[i].setIcon(Icon.returnIcon("black_knight"));
			
			promotionButtonsListener(i);
		}
	}
	
	private void promotionButtonsListener(int i) {
		promotionButtons[i].addActionListener(event -> {
			controller.promotion(i);
			dispose();
		});
	}

	private static final long serialVersionUID = 1L;
}
