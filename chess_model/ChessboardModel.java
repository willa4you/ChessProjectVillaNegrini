package chess_model;

public class ChessboardModel {
	private static ChessboardModel scacchieraObj;
	
	private Piece[][] scacchiera;
	
	private ChessboardModel(){
		//schiero la prima squadra
		scacchiera[0][0] = new Rook(1);
		scacchiera[1][0] = new Knight(1);
		scacchiera[2][0] = new Bishop(1);
		scacchiera[3][0] = new Queen(1);
		scacchiera[4][0] = new King(1);
		scacchiera[5][0] = new Bishop(1);
		scacchiera[6][0] = new Knight(1);
		scacchiera[7][0] = new Rook(1);
		scacchiera[0][1] = new Pawn(1);
		scacchiera[1][1] = new Pawn(1);
		scacchiera[2][1] = new Pawn(1);
		scacchiera[3][1] = new Pawn(1);
		scacchiera[4][1] = new Pawn(1);
		scacchiera[5][1] = new Pawn(1);
		scacchiera[6][1] = new Pawn(1);
		scacchiera[7][1] = new Pawn(1);
		
		//riempio la parte centrale della scacchiera di vuoti
		for(int i = 0;i < 4;i++)
			for(int j = 0; j <= 7; j++)
				scacchiera[j][i + 2] = null;
		
		//schiero la seconda squadra
		scacchiera[0][6] = new Pawn(2);
		scacchiera[1][6] = new Pawn(2);
		scacchiera[2][6] = new Pawn(2);
		scacchiera[3][6] = new Pawn(2);
		scacchiera[4][6] = new Pawn(2);
		scacchiera[5][6] = new Pawn(2);
		scacchiera[6][6] = new Pawn(2);
		scacchiera[7][6] = new Pawn(2);
		scacchiera[0][7] = new Rook(2);
		scacchiera[1][7] = new Knight(2);
		scacchiera[2][7] = new Bishop(2);
		scacchiera[3][7] = new Queen(2);
		scacchiera[4][7] = new King(2);
		scacchiera[5][7] = new Bishop(2);
		scacchiera[6][7] = new Knight(2);
		scacchiera[7][7] = new Rook(2);
	}
	
	public static Piece getPezzoInPosizione(byte x, byte y){
		return scacchieraObj.scacchiera[x][y];
	}
	
	public static boolean setPezzoInPosizione(Piece pezzo, byte x, byte y){
		scacchieraObj.scacchiera[x][y] = pezzo;
		return true;
	}
	
	public static ChessboardModel nuovaPartita(){
		return scacchieraObj = new ChessboardModel();
	}
}
