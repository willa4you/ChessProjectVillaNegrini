package it.univr.chess.view;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Icon {
	
	private static final String PATH 		= "src/it/univr/chess/view/images/";
	
	private Icon() {}
	
	public static ImageIcon returnIcon(String name){
		return new ImageIcon(
				new ImageIcon(PATH + name).getImage().getScaledInstance(
						ChessboardView.getWindowSide() / 10, ChessboardView.getWindowSide() / 10, Image.SCALE_SMOOTH));	
	}
}
