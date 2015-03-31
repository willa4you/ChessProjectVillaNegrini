package it.univr.chess.view;

import java.awt.Color;

public class DarkButton extends Button {

	public DarkButton(){
		// in ordine: 	valore intero di riferimento coordinate
		//				colore di default delle caselle scure (marrone)
		//				colore delle caselle scure non disponibili (grigio scuro)
		//				colore delle caselle scure di mossa disponibile (verde scuro)
		super(new Color(153, 102, 51), new Color(71, 145, 71));		
	}
	
	private static final long serialVersionUID = 1L;
}
