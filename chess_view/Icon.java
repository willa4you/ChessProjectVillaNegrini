package chess_view;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Icon {
	
	private static final int WIDTH = 75;
	private static final int HEIGHT = 75;

	public Icon() {}
	
	public static ImageIcon blackPawn() {		 
		return new ImageIcon(new ImageIcon("src/chess_view/images/"
				+ "pieces/black_pawn.png").getImage().getScaledInstance(
						WIDTH, HEIGHT,  java.awt.Image.SCALE_SMOOTH ));	
	}
	
	public static ImageIcon blackRook() {
		return new ImageIcon(new ImageIcon("src/chess_view/images/"
				+ "pieces/black_rook.png").getImage().getScaledInstance(
						WIDTH, HEIGHT,  java.awt.Image.SCALE_SMOOTH ));
	}
	
	public static ImageIcon blackKnight() {
		return new ImageIcon(new ImageIcon("src/chess_view/images/"
				+ "pieces/black_knight.png").getImage().getScaledInstance(
						WIDTH, HEIGHT,  java.awt.Image.SCALE_SMOOTH ));
	}
	
	public static ImageIcon blackBishop() {
		return new ImageIcon(new ImageIcon("src/chess_view/images/"
				+ "pieces/black_bishop.png").getImage().getScaledInstance(
						WIDTH, HEIGHT,  java.awt.Image.SCALE_SMOOTH ));
	}
	
	public static ImageIcon blackKing() {
		return new ImageIcon(new ImageIcon("src/chess_view/images/"
				+ "pieces/black_king.png").getImage().getScaledInstance(
						WIDTH, HEIGHT,  java.awt.Image.SCALE_SMOOTH ));
	}
	
	public static ImageIcon blackQueen() {
		return new ImageIcon(new ImageIcon("src/chess_view/images/"
				+ "pieces/black_queen.png").getImage().getScaledInstance(
						WIDTH, HEIGHT,  java.awt.Image.SCALE_SMOOTH ));
	}
	
	public static ImageIcon whitePawn() {
		return new ImageIcon(new ImageIcon("src/chess_view/images/"
				+ "pieces/white_pawn.png").getImage().getScaledInstance(
						WIDTH, HEIGHT,  java.awt.Image.SCALE_SMOOTH ));
	}
	
	public static ImageIcon whiteRook() {
		return new ImageIcon(new ImageIcon("src/chess_view/images/"
				+ "pieces/white_rook.png").getImage().getScaledInstance(
						WIDTH, HEIGHT,  java.awt.Image.SCALE_SMOOTH ));
	}
	
	public static ImageIcon whiteKnight() {
		return new ImageIcon(new ImageIcon("src/chess_view/images/"
				+ "pieces/white_knight.png").getImage().getScaledInstance(
						WIDTH, HEIGHT,  java.awt.Image.SCALE_SMOOTH ));
	}
	
	public static ImageIcon whiteBishop() {
		return new ImageIcon(new ImageIcon("src/chess_view/images/"
				+ "pieces/white_bishop.png").getImage().getScaledInstance(
						WIDTH, HEIGHT,  java.awt.Image.SCALE_SMOOTH ));
	}
	
	public static ImageIcon whiteKing() {
		return new ImageIcon(new ImageIcon("src/chess_view/images/"
				+ "pieces/white_king.png").getImage().getScaledInstance(
						WIDTH, HEIGHT,  java.awt.Image.SCALE_SMOOTH ));
	}
	
	public static ImageIcon whiteQueen() {
		return new ImageIcon(new ImageIcon("src/chess_view/images/"
				+ "pieces/white_queen.png").getImage().getScaledInstance(
						WIDTH, HEIGHT,  java.awt.Image.SCALE_SMOOTH ));
	}
}
