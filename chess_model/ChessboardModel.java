package chess_model;

public class ChessboardModel {
	private static ChessboardModel scacchieraObj;
	
	private Piece scacchiera[][] = new Piece[8][8];
	
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
		for (int i=0; i < 8; i++)
			scacchiera[i][1] = new Pawn(1);
		
		//riempio la parte centrale della scacchiera di vuoti
		for(int i = 0;i < 4;i++)
			for(int j = 0; j <= 7; j++)
				scacchiera[j][i + 2] = null;
		
		//schiero la seconda squadra
		for (int i=0; i < 8; i++)
			scacchiera[i][6] = new Pawn(2);
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
	
	public static void nuovaPartita(){
		//nessun altra classe può fare una new ChessboardModel
		//se viene richiamata una nuova partita per la seconda volta occorre fare pulizia...
		scacchieraObj = new ChessboardModel();
	}
	
	@Override
	public String toString(){
		String chessboardToPlay = "";
		String charPiece = "";
		chessboardToPlay += "\n   A B C D E F G H\n\n";
		for(int y = 7; y >= 0; y--){
			chessboardToPlay += (y + 1) + "  ";
			for(int x = 0; x < 8; x++){
				if(scacchiera[x][y] instanceof Rook) charPiece = "R ";
				else if(scacchiera[x][y] instanceof Bishop) charPiece = "B ";
				else if(scacchiera[x][y] instanceof King) charPiece = "K ";
				else if(scacchiera[x][y] instanceof Queen)  charPiece = "Q ";
				else if(scacchiera[x][y] instanceof Knight) charPiece = "C ";
				else if(scacchiera[x][y] instanceof Pawn) charPiece = "P ";
				else charPiece = "- ";
				if(scacchiera[x][y] != null && scacchiera[x][y].team == Team.Team1) charPiece = charPiece.toLowerCase();
				chessboardToPlay += charPiece;
				if(x == 7) chessboardToPlay += " " + (y + 1) + "\n";
			}
		}
		chessboardToPlay += "\n   A B C D E F G H";
		return chessboardToPlay;
	}
	
	public static String stringChessboard(){
		return scacchieraObj.toString();
	}
}
