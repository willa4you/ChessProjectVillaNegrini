package chessprojectvillanegrini.view;

import javax.swing.JButton;

import java.awt.Color;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

/**
 * Classe astratta che estende la classe JButton e implementa
 * l'interfaccia Buttons.
 * 
 * Le cinque variabili di istanza comprendono quattro puntatori
 * ad oggetti di tipo Color ed un puntatore ad un oggetto di tipo
 * LineBorder. Rispettivamente rappresentano:
 * - il colore di default della casella (bottone).
 * - il colore verde chiaro; usato per evidenziare le caselle
 *   (bottoni) in cui un pezzo può andare una volta selezionato, ma
 *   ciò avviene solo se e` abilitato dal menu il suggerimento delle
 *   mosse consentite.
 * - il colore di selezione giallo; usato per evidenziare la casella
 *   (bottone) del pezzo selezionato.
 * - il colore rosso; usato per evidenziare la casella (bottone) nel
 * 	 caso in cui questa non faccia parte delle caselle (bottoni) su
 *   cui un pezzo può muoversi.
 * - il bordo della casella (bottone)
 * 
 * @author Alessandro Villa
 * @author Matteo Negrini
 * @see Buttons
 */
public abstract class Button extends JButton implements Buttons {
	
	protected final Color default_;
	protected final Color available = new Color(153, 255, 102); // (verde chiaro)
	protected final Color selected = Color.YELLOW;
	protected final Color wrong = Color.RED;
	
	protected final Border thickBorder = new LineBorder(Color.BLACK, 1);
	
	/**
	 * Viene settato il colore di default della casella (bottone):
	 * panna se si tratta di un LightButton, marrone se si tratta di
	 * un DarkButton.
	 * Vengono settati i bordi della casella (bottone) secondo la
	 * variabile di istanza thickBorder, resi non visibili e
	 * infine la casella (bottone) viene resa "trasparente" in modo
	 * da lasciare intravedere l'immagine sottostante e/o il background
	 * 
	 * @param default_ il colore di default del bottone
	 */
	public Button(Color default_) {
		this.default_ = default_;
		setBackground(default_);
		setBorder(thickBorder);
		setBorderPainted(false);
		setOpaque(true);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void selected() {
		setBackground(selected);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void wrong() {
		setBackground(wrong);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void highlightOff() {
		setBorderPainted(false);
		setBackground(default_);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void available() {
		setBorderPainted(true);
		setBackground(available);
	}

	private static final long serialVersionUID = 1L;
	
}
