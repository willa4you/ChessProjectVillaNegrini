package chess_model;
import java.util.Scanner;

public class mainPartita {

	public static void main(String[] args) {
		String partenza = "";
		String arrivo = "";
		byte py, px;
		int a;
		boolean team1 = true;
		Piece piece = null;
		Team player = Team.Team1;
		ChessboardModel.nuovaPartita();
		
		while(true){
			System.out.println(ChessboardModel.stringChessboard());
			if (player == Team.Team1) System.out.println("\nGIOCATORE 1");
			else System.out.println("\nGIOCATORE 2");
			System.out.print("Inserisci coordinate di partenza: ");
			partenza = new Scanner(System.in).nextLine();
			px = (byte)(partenza.charAt(0) - 'A');
			py = (byte)(Integer.parseInt(partenza.substring(1, 2))-1);
			piece = ChessboardModel.getPezzoInPosizione(px, py);
			if (piece == null || piece.team != player || px > 7 || px < 0 || py > 7 || py < 0){
				System.out.println("Non hai selezionato un tuo pezzo.");
				continue;
			}
			System.out.print("Inserisci coordinate di arrivo: ");
			arrivo = new Scanner(System.in).nextLine();
			a = (int)(arrivo.charAt(0) - 'A')*10 + (Integer.parseInt(arrivo.substring(1, 2))-1);
			
			for (int b : ChessboardModel.getPezzoInPosizione(px, py).mosseConsentite(px, py)){
				System.out.print("Potrei andare in " + (char)('A' + b/10) + (b%10 + 1) + "...");
				if (b == a) {
					System.out.println(" Match!");
					ChessboardModel.setPezzoInPosizione(ChessboardModel.getPezzoInPosizione(px, py), (byte)(a/10), (byte)(a%10));
					ChessboardModel.setPezzoInPosizione(null, px, py);
					if(player == Team.Team1) player = Team.Team2;
					else player = Team.Team1;
					//break;
				}
				else System.out.println(" Not match!");
			}
		}
		//popolo la scacchiera con un set che va implementato
		//ogni volta che creo un oggetto pezzo gli devo passare la squadra (1 o 2) e la scacchiera
		//ovvero la variabile di istanza mia
	}

}
