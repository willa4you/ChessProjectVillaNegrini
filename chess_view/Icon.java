package chess_view;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Icon {

	public Icon() {}
	
	public static ImageIcon blackPawn() {
		ImageIcon icon = new ImageIcon("src/chess_view/images/"
				+ "pieces/black_pawn.png");
		Image img = icon.getImage() ;  
		Image newimg = img.getScaledInstance( 10, 10,  java.awt.Image.SCALE_SMOOTH );
		icon = new ImageIcon(newimg);
		return icon;
	}
	
	public static ImageIcon blackRook() {
		return new ImageIcon("src/chess_view/images/"
				+ "/pieces/black_rook.png");
	}
	
	public static ImageIcon blackKnight() {
		return new ImageIcon("src/chess_view/images/"
				+ "pieces/black_knight.png");
	}
	
	public static ImageIcon blackBishop() {
		return new ImageIcon("src/chess_view/images/"
				+ "pieces/black_bishop.png");
	}
	
	public static ImageIcon blackKing() {
		return new ImageIcon("src/chess_view/images/"
				+ "pieces/black_king.png");
	}
	
	public static ImageIcon blackQueen() {
		return new ImageIcon("src/chess_view/images/"
				+ "pieces/black_queen.png");
	}
	
	public static ImageIcon whitePawn() {
		return new ImageIcon("src/chess_view/images/"
				+ "pieces/white_pawn.png");
	}
	
	public static ImageIcon whiteRook() {
		return new ImageIcon("src/chess_view/images/"
				+ "pieces/white_rook.png");
	}
	
	public static ImageIcon whiteKnight() {
		return new ImageIcon("src/chess_view/images/"
				+ "pieces/white_knight.png");
	}
	
	public static ImageIcon whiteBishop() {
		return new ImageIcon("src/chess_view/images/"
				+ "pieces/white_bishop.png");
	}
	
	public static ImageIcon whiteKing() {
		return new ImageIcon("src/chess_view/images/"
				+ "pieces/white_king.png");
	}
	
	public static ImageIcon whiteQueen() {
		return new ImageIcon("src/chess_view/images/"
				+ "pieces/white_queen.png");
	}
}
