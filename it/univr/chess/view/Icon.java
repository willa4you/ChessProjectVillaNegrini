package it.univr.chess.view;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Icon {
	
	private static final String PATH 		= "src/it/univr/chess/view/images/pieces/";
	
	public static final String BLACK_PAWN 	= "black_pawn.png";
	public static final String BLACK_ROOK 	= "black_rook.png";
	public static final String BLACK_KNIGHT = "black_knight.png";
	public static final String BLACK_BISHOP = "black_bishop.png";
	public static final String BLACK_QUEEN  = "black_queen.png";
	public static final String BLACK_KING 	= "black_king.png";
	public static final String WHITE_PAWN 	= "white_pawn.png";
	public static final String WHITE_ROOK	= "white_rook.png";
	public static final String WHITE_KNIGHT = "white_knight.png";
	public static final String WHITE_BISHOP = "white_bishop.png";
	public static final String WHITE_QUEEN	= "white_queen.png";
	public static final String WHITE_KING   = "white_king.png";
	
	private Icon() {}
	
	public static ImageIcon returnIcon(String name){
		return new ImageIcon(
				new ImageIcon(PATH + name).getImage().getScaledInstance(
						ChessboardView.getWindowSide() / 10, ChessboardView.getWindowSide() / 10, Image.SCALE_SMOOTH));	
	}
}
