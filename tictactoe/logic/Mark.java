package tictactoe.logic;

/**
 * Stellt alle Möglicken Markierungen auf einem tictactoe Feld dar.
 */
public enum Mark {
    NONE,
    X,
    O;

    public boolean isEmpty() {
        return this == NONE;
    }

    public Mark getOppositeMark() {

        Mark oppositeMark = NONE;

        if (this == X) {
            oppositeMark = O;
        }

        if (this == O) {
            oppositeMark = X;
        }

        return oppositeMark;
    }
}
