package tictactoe.logic;

import java.util.HashSet;
import java.util.Set;

/**
 * Field Klasse.
 * Kapselt den Zugriff auf das Spielfeld und Spielfeldlogik.
 */
public class Field {

    private static final int DIMENSION = 3;
    private static final int DIAGONAL_COUNT = 2;


    private final Mark[][] field;

    public Field() {

        this.field = new Mark[DIMENSION][DIMENSION];

        for (int col = 0; col < DIMENSION; ++col) {
            for (int row = 0; row < DIMENSION; ++row) {
                this.field[col][row] =  Mark.NONE;
            }
        }

    }

    public Mark[][] getCopyOfField() {

        Mark [][] fieldCopy = new Mark[DIMENSION][DIMENSION];

        for (int col = 0; col < DIMENSION; ++col) {
            System.arraycopy(this.field[col], 0, fieldCopy[col], 0, DIMENSION);
        }

        return fieldCopy;
    }

    public boolean isFull() {

        for (int col = 0; col < DIMENSION; ++col) {
            for (int row = 0; row < DIMENSION; ++row) {
                if (this.field[col][row] ==  Mark.NONE) {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean isPositionEmpty(FieldPosition markPosition) {
        return this.field[markPosition.getX()][markPosition.getY()] == Mark.NONE;
    }

    public void setMark(FieldPosition markPosition, Mark mark) {
        assert isPositionEmpty(markPosition);
        this.field[markPosition.getX()][markPosition.getY()] = mark;
    }

    public Mark getWinningMark() {

        Set<Mark>[] arrayOfRowSets = new Set[DIMENSION];
        Set<Mark>[] arrayOfColumnSets = new Set[DIMENSION];
        Set<Mark>[] arrayOfDiagonalSets = new Set[DIAGONAL_COUNT];

        // initialize setsSet
        for (int i = 0; i < DIMENSION; i++) {
            arrayOfRowSets[i] = new HashSet<>();
            arrayOfColumnSets[i] = new HashSet<>();
        }

        for (int i = 0; i < DIAGONAL_COUNT; i++) {
            arrayOfDiagonalSets[i] = new HashSet<>();
        }

        // Put all Elements into Sets
        for (int col = 0; col < DIMENSION; col++) {
            for (int row = 0; row < DIMENSION; row++) {

                arrayOfRowSets[col].add(this.field[col][row]);
                arrayOfColumnSets[row].add(this.field[col][row]);

                if(col == row) {
                    arrayOfDiagonalSets[0].add(this.field[col][row]);
                }

                if(col + row == DIMENSION - 1) {
                    arrayOfDiagonalSets[1].add(this.field[col][row]);
                }
            }
        }


        // if a set contains only one element, put it into superset
        Set<Mark> setOfWinningMarks = new HashSet<>();
        for (int i = 0; i < DIMENSION; i++) {
            if (arrayOfRowSets[i].size() == 1) {
                setOfWinningMarks.addAll(arrayOfRowSets[i]);
            }

            if (arrayOfColumnSets[i].size() == 1) {
                setOfWinningMarks.addAll(arrayOfColumnSets[i]);
            }
        }

        for (int i = 0; i < DIAGONAL_COUNT; i++) {
            if(arrayOfDiagonalSets[i].size() == 1) {
                setOfWinningMarks.addAll(arrayOfDiagonalSets[i]);
            }
        }



        Mark winningMark = Mark.NONE;

        if (setOfWinningMarks.contains(Mark.X)) {
            winningMark = Mark.X;
        }

        if (setOfWinningMarks.contains(Mark.O)) {
            winningMark = Mark.O;
        }

        return winningMark;
    }

}
