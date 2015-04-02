package it.univr.chess.junit;

import org.junit.*;

import it.univr.chess.model.*;
import it.univr.chess.controller.*;
import it.univr.chess.view.*;


public class SameViewTest {

	@Test
	public void sameViewTest() {
		ChessboardView view = new ChessboardView();
		ChessController controller = (ChessController) view.getController();
		ChessboardModel model = (ChessboardModel) controller.getModel();
		
		Assert.assertSame("ERROR!", controller.getView(), model.getView());
	}
	
	
}
