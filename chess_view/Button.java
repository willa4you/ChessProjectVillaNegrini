package chess_view;

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
	
}
