package tictactoe.logic;

/**
 * Logik des Spiels.
 * Kontrolliert das Spielfeld, den Status des Spiels und ermittelt die Gewinner.
 */
public class Logic {

    private Mark markOfHuman;
    private Mark markOfBot;
    private final BotInterface bot;
    private Status gameStatus;
    private Player winner;
    private final Field field;

    protected Logic(BotInterface bot) {
        this.field = new Field();
        this.bot = bot;
        this.gameStatus = Status.RUNNING;
        this.winner = Player.NOBODY;
    }

    protected void setMarkChosenByHuman(Mark markChosenByHuman) {
        this.markOfHuman = markChosenByHuman;
        this.markOfBot = markChosenByHuman.getOppositeMark();
    }

    protected Status getGameStatus () {
        return gameStatus;
    }

    protected Mark[][] getField() {
        return this.field.getCopyOfField();
    }

    protected Player getWinner() {
        return this.winner;
    }

    protected void markPosition(FieldPosition markPosition) {

        if (this.gameStatus == Status.RUNNING) {

            if(this.field.isPositionEmpty(markPosition)){
                this.field.setMark(markPosition, this.markOfHuman);
                refreshGameStatusAndWinner();

                if (this.gameStatus == Status.RUNNING) {
                    bot.placeMark(this.field, this.markOfBot);
                    refreshGameStatusAndWinner();
                }

            }
        }
    }

    protected void refreshGameStatusAndWinner() {

        Mark winningMark = this.field.getWinningMark();

        switch (winningMark) {
            case NONE:
                if (this.field.isFull()) {
                    this.gameStatus = Status.GAMEOVER;
                    this.winner = Player.NOBODY;
                }
                break;
            case X:
            case O:
                this.gameStatus = Status.GAMEOVER;
                this.winner = whichPlayerUsesThisMark(winningMark);
                break;
        }

    }

    protected Player whichPlayerUsesThisMark(Mark winningMark) {
        Player markUser = Player.NOBODY;

        if (this.markOfHuman == winningMark) {
            markUser = Player.HUMAN;
        }

        if (this.markOfBot == winningMark) {
            markUser = Player.BOT;
        }

        return markUser;
    }

    protected boolean botHasToDoFirstMove() {
        return this.markOfBot == Mark.X;
    }

    protected void makeBotMove() {
        bot.placeMark(this.field, this.markOfBot);
    }

    protected void finishPreparation() {
        if (botHasToDoFirstMove()){
            makeBotMove();
        }
    }
}
