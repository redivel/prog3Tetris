package hu.bme.redivel.Tetris;

public class Entry {
    private String name;
    private int points;

    public Entry(String name, int points) {
        this.name = name;
        this.points = points;
    }

    public String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }
}
