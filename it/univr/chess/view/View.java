package it.univr.chess.view;

import java.util.ArrayList;

public interface View {

	public void move(int sx, int sy, int tx, int ty);
	
	public void move(int sx, int sy);
	
	public void selected(int x, int y, ArrayList<Integer> availableMoves);
	
	public void moved();
	
	public void wrongMove(int x, int y);
	
	public void selfSelect(int x, int y);
	
	public void promotion(boolean team1);
	
	public void promotion(int piece, int x, int y);
	
	public void noThanks();
	
	public void mate(boolean team1);
	
	public void draw(int draw);
	
	public void sendMessage(boolean team1, boolean check);
	
	public void newMatch();
	
	public void setSuggestions();
}
