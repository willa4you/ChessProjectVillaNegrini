package it.univr.chess.view;

import javax.swing.JButton;
import java.awt.Color;

public class Button extends JButton {
	
	private final int value;
	private final Color color;
	
	public Button(int value, Color color) {
		this.value = value;
		this.color = color;
		this.setBackground(color);
		
		setBorderPainted(false);
		setOpaque(true);
	}
	
	public int getValue() {
		return this.value;
	}
	
	public Color getColor() {
		return color;
	}
	
	private static final long serialVersionUID = 1L;
	
}
