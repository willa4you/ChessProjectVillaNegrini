package it.univr.chess.controller;

import it.univr.chess.model.ChessboardModel;
import it.univr.chess.model.Model;
import it.univr.chess.view.View;

public class ChessController implements Controller {

	private final View view;
	private Model model;
	
	public ChessController(View view) {
		this.view = view;
		
		model = new ChessboardModel(view);
	}

	@Override
	public void onClick(int coordinates) {
		model.coordinates(coordinates / 10, coordinates % 10);
	}

	@Override
	public void promotion(int piece) {
		model.promotion(piece);
		
	}
	
	@Override
	public void newMatch() {
		model = new ChessboardModel(view);
	}
	
}
