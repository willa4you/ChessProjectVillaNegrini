package it.univr.chess.controller;

import it.univr.chess.model.ChessboardModel;
import it.univr.chess.model.Model;
import it.univr.chess.view.View;

public class ChessController implements Controller {

	private final View view;
	private final Model model;
	
	public ChessController(View view) {
		this.view = view;
		
		view.setController(this); // view e' ChessboardView
		
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
	
}
