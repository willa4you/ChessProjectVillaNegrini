package chessboard;

import javax.swing.ImageIcon;
import java.awt.Image;

public class Icon {

	public Icon() {}
	
	public static ImageIcon blackPawn() {
		ImageIcon icon = new ImageIcon("src/chessboard/images/"
				+ "pieces/black/black_pawn.png");
		Image img = icon.getImage() ;  
		Image newimg = img.getScaledInstance( 10, 10,  java.awt.Image.SCALE_SMOOTH );
		icon = new ImageIcon(newimg);
		return icon;
	}
	
	public static ImageIcon blackRook() {
		return new ImageIcon("src/chessboard/images/"
				+ "pieces/black/black_rook.png");
	}
	
	public static ImageIcon blackKnight() {
		return new ImageIcon("src/chessboard/images/"
				+ "pieces/black/black_knight.png");
	}
	
	public static ImageIcon blackBishop() {
		return new ImageIcon("src/chessboard/images/"
				+ "pieces/black/black_bishop.png");
	}
	
	public static ImageIcon blackKing() {
		return new ImageIcon("src/chessboard/images/"
				+ "pieces/black/black_king.png");
	}
	
	public static ImageIcon blackQueen() {
		return new ImageIcon("src/chessboard/images/"
				+ "pieces/black/black_queen.png");
	}
	
	public static ImageIcon whitePawn() {
		return new ImageIcon("src/chessboard/images/"
				+ "pieces/white/white_pawn.png");
	}
	
	public static ImageIcon whiteRook() {
		return new ImageIcon("src/chessboard/images/"
				+ "pieces/white/white_rook.png");
	}
	
	public static ImageIcon whiteKnight() {
		return new ImageIcon("src/chessboard/images/"
				+ "pieces/white/white_knight.png");
	}
	
	public static ImageIcon whiteBishop() {
		return new ImageIcon("src/chessboard/images/"
				+ "pieces/white/white_bishop.png");
	}
	
	public static ImageIcon whiteKing() {
		return new ImageIcon("src/chessboard/images/"
				+ "pieces/white/white_king.png");
	}
	
	public static ImageIcon whiteQueen() {
		return new ImageIcon("src/chessboard/images/"
				+ "pieces/white/white_queen.png");
	}
}
