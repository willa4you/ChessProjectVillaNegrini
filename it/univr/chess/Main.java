package it.univr.chess;

import it.univr.chess.view.ChessboardView;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				JFrame frame = new ChessboardView();
				frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}
}

