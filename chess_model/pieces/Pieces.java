package chess_model.pieces;

import chess_model.Team;

public interface Pieces {

	Team getTeam(); //ci dovrà essere una variabile di tipo Team da restituire
	Iterable<Integer> mosseConsentite(int x, int y);
	boolean check(int x, int y);
}
