package it.univr.chess.model.pieces;

import it.univr.chess.model.Team;

public interface Pieces {

	Team getTeam(); //ci dovr� essere una variabile di tipo Team da restituire
	Iterable<Integer> mosseConsentite(int x, int y);
	boolean check(int x, int y);
}
