package it.univr.chess.view;

import it.univr.chess.controller.Controller;
import java.awt.Color;

public interface View {

	void setController(Controller controller);
	
	void highlight(int x, int y, Color color);
	
	void highlightOff(int x, int y);
	
	void move(int sx, int sy, int tx, int ty);
	
	void move(int sx, int sy);
	
	void selected(int x, int y, Iterable<Integer> availableMoves);
	
	void moved();
	
	void wrongMove(int x, int y);
	
	void selfSelect(int x, int y);
	
	void promotion(boolean team1);
	
	void promotion(int piece, int x, int y);
}
