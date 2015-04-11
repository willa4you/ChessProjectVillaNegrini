package it.univr.scienze.programmazione2.chessprojectvillanegrini.view;

import java.awt.Color;

/**
 * Classe che estende la classe Button, il cui unico
 * scopo e` quello di dire alla superclasse il suo colore
 * di default
 * 
 * @author Alessandro Villa
 * @author Matteo Negrini
 * @see Button
 * @see Buttons
 */
public class LightButton extends Button {

	/**
	 * Invoca il costruttore della superclasse Button
	 * la quale settera il color panna come colore di default
	 * della casella (bottone)
	 */
	public LightButton() {
		super(new Color(255, 255, 204)); // (panna)
	}

	private static final long serialVersionUID = 1L;
}
