package it.univr.chess.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextField;
import javax.swing.JOptionPane;

import it.univr.chess.controller.ChessController;

public class MainWindow extends JFrame {

	private static JTextField textField = new JTextField();
	
	
	public MainWindow() {	
		super("Scacchiera");					// 70 pixel per compensare il JMenuBar e il JTextField
		setSize(getWindowSide(), getWindowSide() + 70);
		setResizable(false);
		setLocationRelativeTo(null); // fa apparire la finestra al centro dello schermo
		setLayout(new BorderLayout());
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				if (JOptionPane.showConfirmDialog(null, "Vuoi realmente abbandonare la partita?",
						"Abbandona partita", JOptionPane.YES_NO_OPTION) == 0)
					System.exit(0);
			}
		});
		
		new ChessController(addPanel());	
		
		setJMenuBar(menuBar());
		
		textField.setEditable(false);
		textField.setHorizontalAlignment(JTextField.CENTER);
		
		add(textField, BorderLayout.SOUTH);
	}
	
	private ChessboardView addPanel() {
		ChessboardView panel = new ChessboardView(this);
		add(panel, BorderLayout.CENTER);
		
		return panel;
	}
	
	private JMenuBar menuBar() {
		JMenu optionMenu = new JMenu("Opzioni");

		
		JMenuItem restart = new JMenuItem("Nuova Partita");
		restart.addActionListener(event -> {
			EventQueue.invokeLater(new Runnable() {

				@Override
				public void run() {
					JFrame frame = new MainWindow();
					frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
					frame.setVisible(true);
				}
			});
			
			this.dispose();
		});
		optionMenu.add(restart);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.add(optionMenu);
		
		return menuBar;
	}
	
	public static void setText(String message) {
		textField.setText(message);
	}
	
	public static int getWindowSide() {
		return (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 3 * 2);
	}
	
	private static final long serialVersionUID = 1L;
	
}
