package it.univr.chess.view;

import java.awt.Image;

import javax.swing.ImageIcon;

/**
 * Classe che ha come unico scopo quello di creare e ritornare
 * una ImageIcon di un pezzo, numero o lettera.
 * 
 * L'unica variabile di istanza e` una stringa che rappresenta
 * il path della cartella da cui attingere le immagini che
 * servono per il gioco.
 * 
 * @author Alessandro Villa
 * @author Matteo Negrini
 */
public class Icon {
	
	private static final String PATH = "src/it/univr/chess/view/images/";
	
	/**
	 * Non e` possibile creare un'istanza di questa classe
	 * al di fuori essa stessa
	 */
	private Icon() {}
	
	/**
	 * Questo metodoc crea un'icona del pezzo/numero/lettera specificato.
	 * La grandezza dell'icona e` in un rapporto 1/10 con la grandezza
	 * della MainWindow
	 * 
	 * @param name una stringa che contiene il nome del pezzo/numero/lettera
	 * @return ritorna un'icona del pezzo/numero/lettera specificato come parametro
	 * @see MainWindow
	 */
	public static ImageIcon returnIcon(String name){
		return new ImageIcon(
				new ImageIcon(PATH + name + ".png").getImage().getScaledInstance(
						MainWindow.getWindowSide() / 10, MainWindow.getWindowSide() / 10, Image.SCALE_SMOOTH));	
	}
}
