package me.jarvis.board;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class SquareBoard extends Board {

    private final int size;

    private final Field[][] fields;

    public SquareBoard(int size) {
        this.size = size;
        this.fields = new Field[this.size][this.size];

        for (int y = 0; y < this.size; ++y) {
            for (int x = 0; x < this.size; ++x) {
                this.fields[y][x] = new Field(Team.NONE);
            }
        }
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public Field get(int x, int y) {
        assert this.isValidCoordinate(x, y);
        return this.fields[y][x];
    }

    @Override
    public void set(int x, int y, Team team) {
        assert this.isValidCoordinate(x, y);
        this.fields[y][x] = new Field(team);
    }

    @Override
    public Stream<Field> getFlatFieldStream() {
        return Arrays.stream(this.fields).flatMap(Arrays::stream);
    }

    @Override
    public Stream<Field> getRowFieldStream(int row) {
        return Arrays.stream(this.fields[row]);
    }

    @Override
    public Stream<Field> getColumnFieldStream(int columnIndex) {
        Stream<Integer> indexStream = IntStream.range(0, this.size).boxed();
        return indexStream.map(row -> this.fields[row][columnIndex]);
    }

    @Override
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SquareBoard board = (SquareBoard) o;

        if (size != board.size) return false;
        return Arrays.deepEquals(fields, board.fields);
    }

    @Override
    public int hashCode() {
        int result = size;
        result = 31 * result + Arrays.deepHashCode(fields);
        return result;
    }
}
