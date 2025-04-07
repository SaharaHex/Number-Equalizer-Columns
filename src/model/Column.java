package model;

public class Column {
    private int total;

    public Column() {
        total = 0;
    }

    public void addNumber(int number) {
        total += number;
    }

    public int getTotal() {
        return total;
    }
}