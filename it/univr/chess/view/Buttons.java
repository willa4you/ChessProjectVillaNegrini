package it.univr.chess.view;

interface Buttons {

	public void selected(); //setta background giallo
	
	public void wrong(); //setta background rosso
	
	public void highlightOff(); //setta background colore default
	
	public void available(); //setta background verde e bordi
}
