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

    public static Team fromChar(char character) {
        switch (character) {
            case 'O' -> {
                return ONE;
            }
            case 'X' -> {
                return TWO;
            }
            case ' ' -> {
                return NONE;
            }
            default -> throw new IllegalArgumentException(String.format("Unexpected argument: %s", character));
        }
    }
}
