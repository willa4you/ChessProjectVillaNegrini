package chess_view;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Icon {
	
	private static final int WIDTH = 75;
	private static final int HEIGHT = 75;
	private static final String path = "src/chess_view/images/pieces/";
	public Icon() {}
	
	public static ImageIcon returnIcon(String name){
		return new ImageIcon(new ImageIcon(path + name).getImage().getScaledInstance(WIDTH, HEIGHT, java.awt.Image.SCALE_SMOOTH ));	
	}
}
