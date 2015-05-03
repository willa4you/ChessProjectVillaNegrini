package chessprojectvillanegrini.controller;

import chessprojectvillanegrini.model.ChessboardModel;
import chessprojectvillanegrini.model.ModelController;
import chessprojectvillanegrini.view.View;

/**
 * Gli oggetti di questa classe ricevono gli input dell'interfaccia grafica
 * e li traducono in istruzioni per gli oggetti di classe ChessboardModel
 * invocando i metodi adatti previsti dall'interfaccia Model.<br><br>
 * 
 * Le variabili di istanza sono due puntatori a oggetti che implementano
 * l'interfaccia View (parte grafica) e l'interfaccia Model (parte logica).
 * Il puntatore all'oggetto che implementa Model non e` final perche` ad ogni
 * nuova partita viene sostituito con uno nuovo.
 * 
 * @author Alessandro Villa
 * @author Matteo Negrini
 * @see View
 * @see ModelController
 */
public class ChessController implements Controller {

	private final View view;
	private ModelController model;
	
	/**
	 * Viene creato un nuovo oggetto ChessboardModel che gestisce la logica del gioco.
	 * Gli viene passato un reference di tipo View che punta alla ChessboardView per
	 * comunicare successivamente variazioni di stato nel gioco.
	 * 
	 * @param view e` la ChessboardView che crea il controller che gli passa se` stessa
	 * @see ChessboardModel
	 */
	public ChessController(View view) {
		this.view = view;
		
		model = new ChessboardModel(view);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onClick(int x, int y) {
		model.coordinates(x, y);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void promotion(int piece) {
		model.promotion(piece);	
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void newMatch() {
		model = new ChessboardModel(view);
	}
	
	/**
	 * Utilizzato solo a fini di Test tramite JUnit.
	 * 
	 * @return l'oggetto di interfaccia Model associato.
	 */
	public ModelController getModel() {
		return model;
	}
	
	/**
	 * Utilizzato solo a fini di Test tramite JUnit.
	 * 
	 * @return l'oggetto di interfaccia View associato.
	 */
	public View getView() {
		return view;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setMode(boolean easyMode) {
		model.setMode(easyMode);	
	}
}
