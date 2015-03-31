package it.univr.chess.view;

import javax.swing.JButton;

import java.awt.Color;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;


public abstract class Button extends JButton implements Buttons {
	
	protected final Color default_;
	protected final Color available;
	protected final Color selected = Color.YELLOW;
	protected final Color wrong = Color.RED;
	
	protected final Border thickBorder = new LineBorder(Color.BLACK, 2);
	
	
	public Button(Color default_, Color available) {
		this.default_ = default_;
		this.available = available;
		this.setBackground(default_);
		this.setBorder(thickBorder);
		setBorderPainted(false);
		setOpaque(true);
	}
	
	
	@Override
	public void selected() {
		this.setBackground(selected);
	}
	
	@Override
	public void wrong() {
		this.setBackground(wrong);
	}
	@Override
	public void highlightOff() {
		setBorderPainted(false);
		this.setBackground(default_);
	}
	
	@Override
	public void available() {
		setBorderPainted(true);
		this.setBackground(available);
	}
	

	
	private static final long serialVersionUID = 1L;
	
}
