package me.jarvis.board;

public enum Team {

    ONE,
    TWO,
    NONE;

    public Team opponent() {
        return switch (this) {
            case ONE -> TWO;
            case TWO -> ONE;
            case NONE -> NONE;
        };
    }

    @Override
    public String toString() {
        return switch (this) {
            case ONE -> "O";
            case TWO -> "X";
            case NONE -> " ";
        };
    }
}
