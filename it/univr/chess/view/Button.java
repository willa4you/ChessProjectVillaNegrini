package it.univr.chess.view;

import javax.swing.JButton;

public class Button extends JButton {
	
	private final int value;
	
	public Button(int value) {
		this.value = value;
		
		setBorderPainted(false);
		setOpaque(true);
	}
	
	public int getValue() {
		return this.value;
	}
	
	private static final long serialVersionUID = 1L;
	
}
