package chess_model;
import java.util.Scanner;

public class mainPartita {

	public static void main(String[] args) {
		String partenza = "";
		String arrivo = "";
		byte py, px;
		int a;
		Piece piece = null;
		Piece tmp = null;
		Team player = Team.Team1;//il primo turno
		ChessboardModel.nuovaPartita();
		
		while(true){
			System.out.println(ChessboardModel.stringChessboard());
			System.out.print("\nÈ IL TUO TURNO "); 
			if (player == Team.Team1) System.out.println("GIOCATORE 1");//turno del giocatore
			else System.out.println("GIOCATORE 2");
			if(check(player)) {
				if(mate(player)){
					System.out.println("SCACCO MATTO, PARTITA FINITA!");
					break;
				}
				else System.out.println("ATTENZIONE, SEI SOTTO SCACCO!");
			}
			System.out.print("Inserisci coordinate di partenza: ");
			partenza = new Scanner(System.in).nextLine();
			px = (byte)(partenza.charAt(0) - 'A');//estraggo la x della coordinata scelta
			py = (byte)(Integer.parseInt(partenza.substring(1, 2))-1);//estraggo la y della coordinata scelta
			piece = ChessboardModel.getPezzoInPosizione(px, py);//seleziono il pezzo scelto
			if (piece == null || piece.team != player || px > 7 || px < 0 || py > 7 || py < 0){
				System.out.println("Non hai selezionato un tuo pezzo.");
				continue;
			}//il pezzo deve appartenere alla mia squadra
			System.out.print("Inserisci coordinate di arrivo: ");
			arrivo = new Scanner(System.in).nextLine();
			a = (int)(arrivo.charAt(0) - 'A')*10 + (Integer.parseInt(arrivo.substring(1, 2))-1);
			
			for (int b : piece.mosseConsentite(px, py)){
				System.out.print("Potrei andare in " + (char)('A' + b/10) + (b%10 + 1) + "...");
				if (b == a) {//la mossa che ho scelto (a) combacia con una delle possibili per il pezzo (b)
					System.out.println(" Match!");
					tmp = ChessboardModel.getPezzoInPosizione((byte)(a/10), (byte)(a%10));//la eseguo ma tengo l'eventuale pezzo mangiato in tmp
					ChessboardModel.setPezzoInPosizione(piece, (byte)(a/10), (byte)(a%10));
					ChessboardModel.setPezzoInPosizione(null, px, py);
					//se il mio re va/resta sotto scacco invalido la mossa
					if(check(player)){//se il mio re è in scacco con la mia mossa annullo
						ChessboardModel.setPezzoInPosizione(piece, px, py);//rimetto piece dov'era
						ChessboardModel.setPezzoInPosizione(tmp, (byte)(a/10), (byte)(a%10));//metto tmp dov'era
						System.out.println("Questa mossa sarebbe stata valida, ma il tuo Re è sotto scacco!");
						continue;
					}
					if(player == Team.Team1) player = Team.Team2;
					else player = Team.Team1;
					//break;
				}
				else System.out.println(" Not match!");
			}//chiude for each
		}//chiude while true
	}//chiude il main
	
	private static boolean check(Team player){
		Piece piece = null;
		Team otherPlayer;
		if(player == Team.Team1) otherPlayer = Team.Team2;
		else otherPlayer = Team.Team1;
		
		for(int i = 0; i < 8; i++)
			for(int j = 0; j < 8; j++)
				if((piece = ChessboardModel.getPezzoInPosizione((byte)i, (byte)j)) != null && piece.team == otherPlayer && piece.check((byte)i, (byte)j))
					return true;
		
		return false;
	}
	
	private static boolean mate(Team player){
		Piece piece = null;
		Piece tmp = null;
		boolean check = true;
		
		for(int i = 0; i < 8; i++)
			for(int j = 0; j < 8; j++){
				piece = ChessboardModel.getPezzoInPosizione((byte)i, (byte)j);
				if (piece != null && piece.team == player)
					for (int mc : piece.mosseConsentite((byte)i, (byte)j)){
						tmp = ChessboardModel.getPezzoInPosizione((byte)(mc/10), (byte)(mc%10));//la eseguo ma tengo l'eventuale pezzo mangiato in tmp
						ChessboardModel.setPezzoInPosizione(piece, (byte)(mc/10), (byte)(mc%10));
						ChessboardModel.setPezzoInPosizione(null, (byte)i, (byte)j);
						check = check(player);
						ChessboardModel.setPezzoInPosizione(piece, (byte)i, (byte)j);//rimetto piece dov'era
						ChessboardModel.setPezzoInPosizione(tmp, (byte)(mc/10), (byte)(mc%10));//metto tmp dov'era
						if(!check) return false;
					}
			}
		return true;
	}
}
 
