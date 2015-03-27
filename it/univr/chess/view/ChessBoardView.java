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
	
	private Button[][] buttons;
	
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
		JMenu optionMenu = new JMenu("Options");
		
		JMenuItem resizeChoice = new JMenuItem("Resize");
		resizeChoice.addActionListener(event -> {
			setResizable(true);
			setSize(getWindowSide(), getWindowSide() + 20);
			setResizable(false);
			setLocationRelativeTo(null);
			for (int i = 0; i < buttons.length; i++)
				for (int j = 0; j < buttons[i].length; j++)
					if (buttons[i][j].getIcon() != null) {
						// ho bisogno di sapere il pezzo associato all'immagine
					}
		});
		optionMenu.add(resizeChoice);
		
		JMenuItem restartChoice = new JMenuItem("Restart");
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
