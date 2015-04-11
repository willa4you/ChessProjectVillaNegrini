package it.univr.scienze.programmazione2.chessprojectvillanegrini.view;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextField;
import javax.swing.JOptionPane;

/**
 * Questa classe puo` essere considerata come un contenitore
 * statico che fa da involucro ad un oggetto di tipo ChessbiardView.<br>
 * Statico perche` un'oggetto di questa classe viene creato
 * solamente una volta all'avvio del gioco.<br><br>
 * 
 * Le varibili di istanza sono un puntatore ad un oggetto di
 * tipo JTextField, che permette di stampare a video informazioni
 * relative alla partita, e un puntatore ad un oggetto che implementi
 * l'interfaccia View.
 * 
 * @author Alessandro Villa
 * @author Matteo Negrini
 * @see View
 * @see ChessboardView
 */
public class MainWindow extends JFrame {

	private static final JTextField textField = new JTextField();
	private final View view;
	
	/**
	 * Costruttore che:<br>
	 * - setta la grandezza della finestra e la pone al centro dello schermo;<br>
	 * - impedisce di ridimesionare la finestra;<br>
	 * - setta il LayoutManager della finestra;<br>
	 * - aggiunge un WindowListener al pulsante di chiusera della finestra;<br>
	 * - invoca un metodo che crea un nuovo oggetto di tipo ChessboardView;<br>
	 * - invoca un metodo che crea un JMenuBar;<br>
	 * - setta alcuni parametri di textField e lo aggiunge in fondo alla finestra.
	 */
	public MainWindow() {	
		super("Scacchi");					// 70 pixel per compensare il JMenuBar e il JTextField
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
		
		this.view = addPanel();
		
		setJMenuBar(menuBar());
		
		textField.setEditable(false);
		textField.setHorizontalAlignment(JTextField.CENTER);
		
		add(textField, BorderLayout.SOUTH);
	}
	
	/**
	 * Questo metodo crea un nuovo oggetto di tipo ChessboardView,
	 * lo posiziona al centro della MainWindow e lo ritorna.
	 * 
	 * @return un riferimento ad un oggetto di tipo ChessbiardView
	 * @see ChessboardView
	 */
	private ChessboardView addPanel() {
		ChessboardView panel = new ChessboardView();
		add(panel, BorderLayout.CENTER);
		
		return panel;
	}
	
	/**
	 * Metodo che crea un JMenuBar con diverse opzioni e lo ritorna.<br>
	 * Le opzioni prevedono:<br>
	 * - la possibilita` di incominciare una nuova partita;<br>
	 * - la possibilita` di abilitare o disabilitare i suggerimenti
	 * 	 per le mosse consentite;<br>
	 * - la possibilita` di usicre dal gioco.
	 * 
	 * @return il menuBar
	 * @see ChessboardView#newMatch()
	 * @see ChessboardView#setSuggestions()
	 */
	private JMenuBar menuBar() {
		JMenu optionMenu = new JMenu("Opzioni");

		
		JMenuItem restart = new JMenuItem("Nuova Partita");
		restart.addActionListener(event -> {
			if (JOptionPane.showConfirmDialog(null, "<html><div align=center>Abbandonare la partita?</div></html>",
					"ABBANDONA", JOptionPane.YES_NO_OPTION) == 0)
				view.newMatch();			
		});
		optionMenu.add(restart);
		
		JMenuItem suggestions = new JMenuItem("Abilita/Disabilita suggerimenti");
		suggestions.addActionListener(event -> {
			view.setSuggestions();	
		});
		
		optionMenu.add(suggestions);
		
		JMenuItem exit = new JMenuItem("Esci dal gioco");
		exit.addActionListener(event -> {
			if (JOptionPane.showConfirmDialog(null, "<html><div align=center>Uscire dal gioco?</div></html>",
					"ESCI", JOptionPane.YES_NO_OPTION) == 0)
				System.exit(0);			
		});
		optionMenu.add(exit);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.add(optionMenu);
		
		return menuBar;
	}
	
	/**
	 * Metodo statico invocato da ChessboardView che dice alla MainWindow
	 * cosa stampare a video nel textField.
	 * 
	 * @param message il messaggio da stampare.
	 * @see ChessboardView#sendMessage(boolean team1, boolean check)
	 */
	public static void setText(String message) {
		textField.setText(message);
	}
	
	/**
	 * Metodo statico invocato da diverse classi del package view che
	 * calcola e ritorna la lunghezza del lato della MainWindow in base
	 * alla risoluzione dello schermo in un rapporto di 2/3 rispetto a questa.
	 * 
	 * @return la lunghezza del lato della MainWindow
	 */
	public static int getWindowSide() {
		return (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 3 * 2);
	}
	
	private static final long serialVersionUID = 1L;
	
}
