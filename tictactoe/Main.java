package tictactoe;

import tictactoe.logic.GameController;

/**
 * Main Klasse des tictactoe Spiels.
 * Initialisiert die Applikation.
 */
public class Main {

    public static void main(final String[] args) {
        GameController gameController = new GameController();
        gameController.run();
    }
}
