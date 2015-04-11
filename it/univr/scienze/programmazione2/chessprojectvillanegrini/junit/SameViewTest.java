package it.univr.scienze.programmazione2.chessprojectvillanegrini.junit;

import org.junit.*;

import it.univr.scienze.programmazione2.chessprojectvillanegrini.controller.*;
import it.univr.scienze.programmazione2.chessprojectvillanegrini.model.*;
import it.univr.scienze.programmazione2.chessprojectvillanegrini.view.*;


public class SameViewTest {

	@Test
	public void sameViewTest() {
		ChessboardView view = new ChessboardView();
		ChessController controller = (ChessController) view.getController();
		ChessboardModel model = (ChessboardModel) controller.getModel();
		
		Assert.assertSame("ERROR!", controller.getView(), model.getView());
	}
	
	
}
