package chessprojectvillanegrini.junit;

import org.junit.*;

import chessprojectvillanegrini.controller.*;
import chessprojectvillanegrini.model.*;
import chessprojectvillanegrini.view.*;


public class SameViewTest {

	@Test
	public void sameViewTest() {
		ChessboardView view = new ChessboardView();
		ChessController controller = (ChessController) view.getController();
		ChessboardModel model = (ChessboardModel) controller.getModel();
		
		Assert.assertSame("ERROR!", controller.getView(), model.getView());
	}
	
	
}
