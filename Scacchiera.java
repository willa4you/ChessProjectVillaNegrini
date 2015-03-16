package progettoChess;

public class Scacchiera {
	private static Scacchiera scacchieraObj;
	
	private Pezzo[][] scacchiera;
	
	private Scacchiera(){
		//schiero la prima squadra
		scacchiera[0][0] = new Torre(1);
		scacchiera[1][0] = new Cavallo(1);
		scacchiera[2][0] = new Alfiere(1);
		scacchiera[3][0] = new Regina(1);
		scacchiera[4][0] = new Re(1);
		scacchiera[5][0] = new Alfiere(1);
		scacchiera[6][0] = new Cavallo(1);
		scacchiera[7][0] = new Torre(1);
		scacchiera[0][1] = new Pedone(1);
		scacchiera[1][1] = new Pedone(1);
		scacchiera[2][1] = new Pedone(1);
		scacchiera[3][1] = new Pedone(1);
		scacchiera[4][1] = new Pedone(1);
		scacchiera[5][1] = new Pedone(1);
		scacchiera[6][1] = new Pedone(1);
		scacchiera[7][1] = new Pedone(1);
		
		//riempio la parte centrale della scacchiera di vuoti
		for(int i = 0;i < 4;i++)
			for(int j = 0; j <= 7; j++)
				scacchiera[j][i + 2] = null;
		
		//schiero la seconda squadra
		scacchiera[0][6] = new Pedone(2);
		scacchiera[1][6] = new Pedone(2);
		scacchiera[2][6] = new Pedone(2);
		scacchiera[3][6] = new Pedone(2);
		scacchiera[4][6] = new Pedone(2);
		scacchiera[5][6] = new Pedone(2);
		scacchiera[6][6] = new Pedone(2);
		scacchiera[7][6] = new Pedone(2);
		scacchiera[0][7] = new Torre(2);
		scacchiera[1][7] = new Cavallo(2);
		scacchiera[2][7] = new Alfiere(2);
		scacchiera[3][7] = new Regina(2);
		scacchiera[4][7] = new Re(2);
		scacchiera[5][7] = new Alfiere(2);
		scacchiera[6][7] = new Cavallo(2);
		scacchiera[7][7] = new Torre(2);
	}
	
	public static Pezzo getPezzoInPosizione(byte x, byte y){
		return scacchieraObj.scacchiera[x][y];
	}
	
	public static boolean setPezzoInPosizione(Pezzo pezzo, byte x, byte y){
		scacchieraObj.scacchiera[x][y] = pezzo;
		return true;
	}
	
	public static Scacchiera nuovaPartita(){
		return scacchieraObj = new Scacchiera();
	}
}
