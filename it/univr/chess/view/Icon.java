package it.univr.chess.view;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Icon {
	
	private static final String PATH 		= "src/it/univr/chess/view/images/";
	
	private Icon() {}
	
	public static ImageIcon returnIcon(String name){
		return new ImageIcon(
				new ImageIcon(PATH + name + ".png").getImage().getScaledInstance(
						MainWindow.getWindowSide() / 10, MainWindow.getWindowSide() / 10, Image.SCALE_SMOOTH));	
	}
}
