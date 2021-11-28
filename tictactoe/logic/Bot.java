package tictactoe.logic;

import java.util.Random;

/**
 * Random Bot.
 * Platziert das eigene Zeichen per Zufall auf einer freien Position auf dem Feld.
 */
public class Bot implements BotInterface{

    public void placeMark(Field field, Mark markOfBot) {
        assert !field.isFull();

        FieldPosition fieldPosition;

        do {
            int positionIndex = new Random().nextInt(FieldPosition.values().length);
            fieldPosition = FieldPosition.values()[positionIndex];
        } while (!field.isPositionEmpty(fieldPosition));

        field.setMark(fieldPosition, markOfBot);

    }
}
