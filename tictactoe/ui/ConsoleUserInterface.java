package tictactoe.ui;

import tictactoe.logic.FieldPosition;
import tictactoe.logic.Mark;
import tictactoe.logic.Player;
import tictactoe.logic.UserInterface;

import java.util.Objects;
import java.util.Scanner;

/**
 * Implementierung des Interfaces UserInterface.
 *
 * Durch diese Klasse wirde terminalbasierte Kommunkikation zwischen
 * Benutzer und Spiel ermöglicht.
 */
public class ConsoleUserInterface implements UserInterface {

    private static final String TXT_GREET_HUMAN = "Greetings Human!";
    private static final String TXT_REQUEST_MARK = "Please chose a Mark [X|O]: ";
    private static final String TXT_WRONG_INPUT = "Your input contains mistakes, as expected.";
    private static final String TXT_GAME_COMPLETED = "Game completed!";
    private static final String TXT_WIN_MESSAGE_HUMAN = "The Human won.";
    private static final String TXT_WIN_MESSAGE_BOT = "The superior A.I. defeated the Human.";
    private static final String TXT_WIN_MESSAGE_NOBODY = "That's a tie!";
    private static final String TXT_SELECT_POSITION = "Please select a Position: ('h' for help)";
    private static final String TXT_UNKNOWN_POSITION = "Position unknown. Lets try again.";

    private static final String TXT_HELP =
            "\n" +
            "Use one of the following position to set the mark:\n" +
            "   tl | t | tr \n" +
            "    l | m | r  \n" +
            "   bl | b | br \n" +
            "\n" +
            "example: ul = set mark on top left corner\n";

    private boolean isLastPositionOfArray(int position, int arrayLength) {
        return position >= arrayLength - 1;
    }

    private Mark convertCharToMark(char character) {
        Mark mark;

        switch (character) {
            case 'x':
            case 'X':
                mark = Mark.X;
                break;
            case 'o':
            case 'O':
                mark = Mark.O;
                break;
            default:
                mark = Mark.NONE;
                break;
        }
        return mark;
    }


    private char makeMarkPrintable(Mark mark) {
        char character;

        switch (mark) {
            case X:
                character = 'x';
                break;
            case O:
                character = 'o';
                break;
            case NONE:
                character = ' ';
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + mark);
        }

        return character;
    }


    @Override
    public Mark getHumanFieldMark() {

        Scanner sc = new Scanner(System.in);
        Mark mark;

        System.out.println(TXT_GREET_HUMAN);

        do {
            System.out.println(TXT_REQUEST_MARK);

            String input = sc.next();
            mark = convertCharToMark(input.charAt(0));
            
            if (mark == Mark.NONE) {
                System.out.println(TXT_WRONG_INPUT);
            }
            
        } while (mark == Mark.NONE);

        return mark;
    }

    @Override
    public FieldPosition getHumanMarkPosition() {

        Scanner sc = new Scanner(System.in);
        FieldPosition fieldPosition = null;
        
        do {
            System.out.println(TXT_SELECT_POSITION);

            String input = sc.next();

            if (Objects.equals(input, "h")) {
                System.out.println(TXT_HELP);
            } else {
                fieldPosition = FieldPosition.fromString(input);
                if (fieldPosition == null) {
                    System.out.println(TXT_UNKNOWN_POSITION);
                }
            }


        } while (fieldPosition == null);

        return fieldPosition;
    }

    @Override
    public void displayField(Mark[][] field) {

        for (int y = field.length-1; y >= 0; --y) {

            // Row of Marks
            for (int x = 0; x < field[0].length; ++x) {
                System.out.print(makeMarkPrintable(field[x][y]));
                if (!isLastPositionOfArray(x, field[0].length)) {
                    System.out.print("|");
                }
            }

            System.out.println();

            // Row separating rows of marks
            if (y > 0) {

                for (int x = 0; x < field[0].length; ++x){
                    if (isLastPositionOfArray(x, field[0].length)) {
                        System.out.print("-");
                    } else {
                        System.out.print("-+");
                    }
                }
            }

            System.out.println();

        }
    }

    @Override
    public void displayEndOfGame(Player winner) {
        
        System.out.println(TXT_GAME_COMPLETED);

        switch (winner){

            case NOBODY:
                System.out.println(TXT_WIN_MESSAGE_NOBODY);
                break;
            case HUMAN:
                System.out.println(TXT_WIN_MESSAGE_HUMAN);
                break;
            case BOT:
                System.out.println(TXT_WIN_MESSAGE_BOT);
                break;
        }

    }
}
