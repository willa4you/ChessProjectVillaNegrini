package it.univr.chess.view;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import it.univr.chess.controller.Controller;

public class PromotionWindow extends JFrame {

	private final Button[] promotionButtons = new Button[4];
	private final boolean team1;
	private final Controller controller;
	
	public PromotionWindow(boolean team1, Controller controller) {
		super("Promuovi il pedone");
		this.team1 = team1;
		this.controller = controller;
		setSize(MainWindow.getWindowSide() / 5 * 2, MainWindow.getWindowSide() / 6);
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
		String[] pieces = {"queen", "rook", "bishop", "knight"};
		
		for (int i = 0; i < 4; i++) {
			promotionButtons[i] = (i % 2 == 0) ? new DarkButton() : new LightButton();
			promotionButtons[i].setIcon(Icon.returnIcon(((team1) ? "white_" : "black_") + pieces[i]));
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
