package chess_view;

import javax.swing.JButton;

public class Button extends JButton {
	
	private final byte value;
	
	public Button(byte value) {
		this.value = value;
		
		setBorderPainted(false);
		setOpaque(true);
	}
	
	public byte getValue() {
		return this.value;
	}
	
}
