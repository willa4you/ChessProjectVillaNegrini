package it.univr.chess.view;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import it.univr.chess.controller.Controller;

/**
 * Questa classe estede JFrame e genera una finestra per la
 * promozione del pedone.
 * 
 * Le varibili di istanza sono un puntatore ad un array di quattro
 * Button, un booleano per sapere la squadra del pedone da promuovere
 * e un puntatore all'oggetto che implementa l'interfaccia Controller
 * 
 * @author Alessandro Villa
 * @author Matteo Negrini
 * @see Button
 * @see Controller
 */
public class PromotionWindow extends JFrame {

	private final Button[] promotionButtons = new Button[4];
	private final boolean team1;
	private final Controller controller;
	
	/**
	 * Crea la PromotionWindow.
	 * Setta la larghezza della finestra in un rapporto
	 * 2/5 con la larghezza della MainWindow e l'altezza
	 * in un rapporto 1/6 con l'altezza della MainWindow.
	 * La finestra non e` ridimensionabile e compare al
	 * centro dello schermo quando invocata.
	 * 
	 * @param team1 la squadra del pedone da promuovere: true = squadra1, false = squadra2.
	 * @param controller il controller a cui comunicare gli eventi.
	 * @see MainWindow
	 */
	public PromotionWindow(boolean team1, Controller controller) {
		super("Promuovi il pedone");
		this.team1 = team1;
		this.controller = controller;
		setSize(MainWindow.getWindowSide() / 5 * 2, MainWindow.getWindowSide() / 6);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		initPromotionButtons();
		
		add(promotionPanel());
	}

	/**
	 * Questo metodo assegna in maniera alternata un LightButton
	 * e un DarkButton alla variabile di istanza promotionButtons,
	 * settando inoltre un'icona ad ognuno dei Button secondo l'ordine
	 * in cui compaiono nell'array di stringe piece.
	 * 
	 * @see Button
	 * @see Buttons
	 * @see LightButton
	 * @see DarkButton
	 * @see Icon
	 */
	private void initPromotionButtons() {
		String[] pieces = {"queen", "rook", "bishop", "knight"};
		
		for (int i = 0; i < 4; i++) {
			promotionButtons[i] = (i % 2 == 0) ? new DarkButton() : new LightButton();
			promotionButtons[i].setIcon(Icon.returnIcon(((team1) ? "white_" : "black_") + pieces[i]));
			promotionButtonsListener(i);
		}

	}
	
	/**
	 * Crea un pannello su cui posizionare l'array di quattro Button
	 * (precedentemente inizializzato) secondo un GridLayout 1x4 e lo ritorna.
	 * 
	 * @return promotionPanel.
	 */
	private JPanel promotionPanel() {
		JPanel promotionPanel = new JPanel();
		promotionPanel.setLayout(new GridLayout(1, 4));
		for (int i = 0; i < 4; i++)
			promotionPanel.add(promotionButtons[i]);
		
		return promotionPanel;
	}
	
	/**
	 * Aggiunge un ActionListener al singolo Button in posizione i-esima
	 * all'interno di promotionButtons.
	 * Il click di uno dei quattro Button causerà l'invio
	 * dell'indice i del Button al controller (che lo rigirerà a sua volta 
	 * al ChessboardModel) e il dispose della PromotionWindow.
	 * 
	 * @param i la posizione i-esima del singolo Button dentro promotionButtons.
	 */
	private void promotionButtonsListener(int i) {
		promotionButtons[i].addActionListener(event -> {
			controller.promotion(i);
			dispose();
		});
	}

	private static final long serialVersionUID = 1L;
}
