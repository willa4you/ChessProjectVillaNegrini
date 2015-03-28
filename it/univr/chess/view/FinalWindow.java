package it.univr.chess.view;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class FinalWindow extends JFrame {
	
	private final boolean mateOrDrawn;
	
	public FinalWindow(boolean mateOrDrawn) {
		this.mateOrDrawn = mateOrDrawn;
		setSize(ChessboardView.getWindowSide() / 5 * 2, ChessboardView.getWindowSide() / 6);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
	}
	
	private static final long serialVersionUID = 1L;

}
