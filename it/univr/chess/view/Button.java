package it.univr.chess.view;

import javax.swing.JButton;

import java.awt.Color;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public abstract class Button extends JButton implements Buttons {
	
	protected final Color default_;
	protected final Color available = new Color(153, 255, 102);
	protected final Color selected = Color.YELLOW;
	protected final Color wrong = Color.RED;
	
	protected final Border thickBorder = new LineBorder(Color.BLACK, 1);
	
	public Button(Color default_) {
		this.default_ = default_;
		setBackground(default_);
		setBorder(thickBorder);
		setBorderPainted(false);
		setOpaque(true);
	}
	
	
	@Override
	public void selected() {
		setBackground(selected);
	}
	
	@Override
	public void wrong() {
		setBackground(wrong);
	}
	
	@Override
	public void highlightOff() {
		setBorderPainted(false);
		setBackground(default_);
	}
	
	@Override
	public void available() {
		setBorderPainted(true);
		setBackground(available);
	}
	

	
	private static final long serialVersionUID = 1L;
	
}
