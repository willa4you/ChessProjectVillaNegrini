package it.univr.chess;

import it.univr.chess.view.MainWindow;

import java.awt.EventQueue;

import javax.swing.JFrame;
/**
 * Il metodo di partenza per ogni sessione di gioco.
 * Viene creato un nuovo oggetto frame di tipo MainWindow che estende JFrame
 * e viene settato visibile.
 * 
 * @author Alessandro Villa
 * @author Matteo Negrini
 */
public class Main {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				JFrame frame = new MainWindow();
				frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}
}

