package tictactoe.logic;

import tictactoe.ui.ConsoleUserInterface;

/**
 * Konkreter Klasse des GameControllers.
 * Verbindet die UI mit der Spiellogik.
 */
public class GameController {

    private final Logic logic;
    private final UserInterface userInterface;

    public GameController() {
        this.logic = new Logic (new Bot());
        this.userInterface = new ConsoleUserInterface();
    }

    public void run() {

        Mark mark = userInterface.getHumanFieldMark();
        assert !mark.isEmpty();

        logic.setMarkChosenByHuman(mark);
        logic.finishPreparation();

        while (logic.getGameStatus() == Status.RUNNING) {
            userInterface.displayField(logic.getField());
            FieldPosition markPosition = userInterface.getHumanMarkPosition();
            logic.markPosition(markPosition);
        }

        userInterface.displayField(logic.getField());

        Player winner = logic.getWinner();
        userInterface.displayEndOfGame(winner);
    }

}
