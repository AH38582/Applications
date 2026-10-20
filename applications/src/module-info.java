module JavaFX {
	requires javafx.controls;
	requires javafx.media;
	requires java.desktop;
	requires javafx.graphics;
	
	exports applications;
	exports applications.gui;
	exports applications.games;
	exports applications.utilities;
	exports applications.utilities.calculator;
	exports applications.utilities.notes;
	exports applications.games.numberguess;
}