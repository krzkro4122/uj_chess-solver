package edu.uj.po.src;

public enum Direction {
    NORTH(0, 1),
    NORTH_EAST(1, 1),
    EAST(1, 0),
    SOUTH_EAST(1, -1),
    SOUTH(0, -1),
    SOUTH_WEST(-1, -1),
    WEST(-1, 0),
    NORTH_WEST(-1, 1);

    private int x;
    private int y;

    public int getFileCoef() {
        return x;
    }

    public int getRankCoef() {
        return y;
    }

    Direction(int x, int y) {
        this.x = x;
        this.y = y;
    }

}
