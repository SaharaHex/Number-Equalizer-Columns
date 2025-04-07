package model;

public class GameController implements Game {
    private final Column[] columns;
    private int currentColumn;
    private int number; // The current generated number
    private int turns;
    private int[] numberSequence; // Optional sequence of numbers
    private int sequenceIndex;   // Index to track the current position in the sequence

    public GameController() {
        this(null); // Default to random numbers if no sequence is provided
    }

    public GameController(int[] numberSequence) {
        columns = new Column[4];
        for (int i = 0; i < 4; i++) {
            columns[i] = new Column();
        }
        currentColumn = 0;
        this.numberSequence = numberSequence;
        sequenceIndex = 0;
        number = generateNumber();
    }

    private int generateNumber() {
        if (numberSequence != null && sequenceIndex < numberSequence.length) {
            // Use the next number in the sequence
            return numberSequence[sequenceIndex++];
        } else {
            // Default to random number if no sequence or sequence ends
            return (int) (Math.random() * 10) + 1;
        }
    }

    @Override
    public void moveLeft() {
        currentColumn = (currentColumn == 0) ? 3 : currentColumn - 1;
    }

    @Override
    public void moveRight() {
        currentColumn = (currentColumn == 3) ? 0 : currentColumn + 1;
    }

    @Override
    public void dropNumber() {
        columns[currentColumn].addNumber(number);
        number = generateNumber();
    }

    @Override
    public boolean checkWin() {
        int total = columns[0].getTotal();
        for (Column column : columns) {
            if (column.getTotal() != total) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void turnNumber() {
        turns += 1;
    }

    public int[] getColumnTotals() {
        int[] totals = new int[columns.length];
        for (int i = 0; i < columns.length; i++) {
            totals[i] = columns[i].getTotal();
        }
        return totals;
    }

    public int getCurrentColumn() {
        return currentColumn;
    }

    public int getNumber() {
        return number;
    }

    public int getTotalTurns() {
        return turns;
    }
}