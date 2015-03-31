package it.univr.chess.view;

import java.awt.Color;

public class LightButton extends Button {

	public LightButton(){
		// in ordine: 	valore intero di riferimento coordinate
		//				colore di default delle caselle chiare (panna)
		//				colore delle caselle chiare non disponibili (bianco)
		//				colore delle caselle chiare di mossa disponibile (verde chiaro)
		super(new Color(255, 255, 204), new Color(153, 255, 102));		
	}

	private static final long serialVersionUID = 1L;
}
