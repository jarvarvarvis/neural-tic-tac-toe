package me.jarvis.board;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Board {

    private final int size;

    private final Field[][] fields;

    public Board(int size) {
        this.size = size;
        this.fields = new Field[this.size][this.size];

        for (int y = 0; y < this.size; ++y) {
            for (int x = 0; x < this.size; ++x) {
                this.fields[y][x] = new Field(Team.NONE);
            }
        }
    }

    public int getSize() {
        return this.size;
    }

    public boolean isValidCoordinate(int x, int y) {
        return x >= 0 && x < this.size &&
               y >= 0 && y < this.size;
    }

    public Field get(int x, int y) {
        assert this.isValidCoordinate(x, y);
        return this.fields[y][x];
    }

    public Stream<Field> getFlatFieldStream() {
        return Arrays.stream(this.fields).flatMap(Arrays::stream);
    }

    public Stream<Field> getRowFieldStream(int row) {
        return Arrays.stream(this.fields[row]);
    }

    public Stream<Field> getColumnFieldStream(int columnIndex) {
        Stream<Integer> indexStream = IntStream.range(0, this.size).boxed();
        return indexStream.map(row -> this.fields[row][columnIndex]);
    }

    public Stream<Field> getDiagonalFieldStream(DiagonalKind kind) {
        switch (kind) {
            case TOP_LEFT_TO_BOTTOM_RIGHT -> {
                Stream<Integer> indexStream = IntStream.range(0, this.size).boxed();
                return indexStream.map(index -> this.get(index, index));
            }
            case BOTTOM_LEFT_TO_TOP_RIGHT -> {
                Stream<Integer> indexStream = IntStream.range(0, this.size).boxed();
                return indexStream.map(index -> {
                    int inverseX = this.size - index - 1;
                    return this.get(inverseX, index);
                });
            }
            default -> {
                return Stream.of();
            }
        }
    }

    public void set(int x, int y, Team team) {
        assert this.isValidCoordinate(x, y);
        this.fields[y][x] = new Field(team);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Board) obj;
        return this.size == that.size;
    }

    @Override
    public String toString() {
        Map<Team, Character> teamDisplayMap = Map.of(Team.NONE, ' ', Team.ONE, 'O', Team.TWO, 'X');
        StringBuilder resultBuilder = new StringBuilder();

        for (int y = 0; y < this.size; ++y) {
            resultBuilder.append(" ");

            for (int x = 0; x < this.size; ++x) {
                Field field = this.get(x, y);
                Team fieldTeam = field.occupant();
                char teamDisplayCharacter = teamDisplayMap.get(fieldTeam);
                resultBuilder.append(teamDisplayCharacter);

                if (x != this.size - 1)
                    resultBuilder.append(" | ");
            }
            resultBuilder.append("\n");

            if (y != this.size - 1) {
                for (int i = 0; i < this.size; ++i) {
                    resultBuilder.append("---");
                    if (i != this.size - 1)
                        resultBuilder.append("|");
                }
                resultBuilder.append("\n");
            }
        }

        return resultBuilder.toString();
    }
}
