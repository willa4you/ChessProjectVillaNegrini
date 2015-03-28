package it.univr.chess.view;

import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import it.univr.chess.controller.Controller;
import it.univr.chess.controller.ChessController;

public class ChessboardView extends JFrame {

	private final Controller controller;
	public static boolean confirmWindowOpen = false;
	
	public ChessboardView() {	
		super("Scacchiera");												// 20 pixel del JMenuBar
		setSize(getWindowSide(), getWindowSide() + 20);
		setResizable(false);
		setLocationRelativeTo(null); // fa apparire la finestra al centro dello schermo
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				if (!confirmWindowOpen) {
					confirmWindowOpen = true;
					new ConfirmWindow().setVisible(true);
				}
			}
		});
		
		controller = new ChessController(addPanel());	
		
		setJMenuBar(menuBar());
	}
	
	private ChessboardPanel addPanel() {
		ChessboardPanel panel = new ChessboardPanel();
		add(panel);
		
		return panel;
	}
	
	private JMenuBar menuBar() {
		JMenu optionMenu = new JMenu("Opzioni");

		
		JMenuItem restartChoice = new JMenuItem("Nuova Partita");
		// da completare
		optionMenu.add(restartChoice);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.add(optionMenu);
		
		return menuBar;
	}
	
	public static int getWindowSide() {
		return (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 3 * 2);
	}
	
	private static final long serialVersionUID = 1L;
	
}
