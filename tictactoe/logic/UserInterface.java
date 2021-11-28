package tictactoe.logic;

/**
 * UserInterface Interface.
 * Beschreibt alle Anforderungen an UserInterfaces.
 */
public interface UserInterface {

    Mark getHumanFieldMark();
    FieldPosition getHumanMarkPosition();
    void displayField(Mark[][] field);
    void displayEndOfGame(Player winner);

}
