package tictactoe.logic;

/**
 * Aufzählung über alle Positionen des Spielfeldes.
 * Die Positionen können zusätzlich als String oder x-,y-Koordinaten abgerufen werden.
 */
public enum FieldPosition {

    TL ("tl", 0, 2),
    T  ( "t", 1, 2),
    TR ("tr", 2, 2),
    L  ( "l", 0, 1),
    M  ( "m", 1, 1),
    R  ( "r", 2, 1),
    BL ("bl", 0, 0),
    B  ( "b", 1, 0),
    BR ("br", 2, 0);

    private final String str;
    private final int x;
    private final int y;

    FieldPosition(String str, int x, int y) {
        this.str = str;
        this.x = x;
        this.y = y;
    }

    public String getStr() {
        return str;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public static FieldPosition fromString(String text) {
        for (FieldPosition fieldPosition : FieldPosition.values()) {
            if (fieldPosition.getStr().equalsIgnoreCase(text)) {
                return fieldPosition;
            }
        }
        return null;
    }
}
