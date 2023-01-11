package me.jarvis.board;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public abstract class Board {

    public abstract int getSize();

    public boolean isValidCoordinate(int x, int y) {
        int size = this.getSize();
        return x >= 0 && x < size &&
            y >= 0 && y < size;
    }

    public abstract Field get(int x, int y);
    public abstract void set(int x, int y, Team team);

    public Stream<Field> getFlatFieldStream() {
        int size = this.getSize();
        Stream<Integer> xStream = IntStream.range(0, size).boxed();
        return xStream.flatMap(x -> {
            Stream<Integer> yCoordStream = IntStream.range(0, size).boxed();
            return yCoordStream.map(y -> this.get(x, y));
        });
    }

    public Stream<Field> getRowFieldStream(int row) {
        int size = this.getSize();
        Stream<Integer> indexStream = IntStream.range(0, size).boxed();
        return indexStream.map(column -> this.get(column, row));
    }

    public Stream<Field> getColumnFieldStream(int column) {
        int size = this.getSize();
        Stream<Integer> indexStream = IntStream.range(0, size).boxed();
        return indexStream.map(row -> this.get(column, row));
    }

    public Stream<Field> getDiagonalFieldStream(DiagonalKind kind) {
        int size = this.getSize();
        switch (kind) {
            case TOP_LEFT_TO_BOTTOM_RIGHT -> {
                Stream<Integer> indexStream = IntStream.range(0, size).boxed();
                return indexStream.map(index -> this.get(index, index));
            }
            case BOTTOM_LEFT_TO_TOP_RIGHT -> {
                Stream<Integer> indexStream = IntStream.range(0, size).boxed();
                return indexStream.map(index -> {
                    int inverseX = size - index - 1;
                    return this.get(inverseX, index);
                });
            }
            default -> {
                return Stream.of();
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder resultBuilder = new StringBuilder();

        int size = this.getSize();
        for (int y = 0; y < size; ++y) {
            resultBuilder.append(" ");

            for (int x = 0; x < size; ++x) {
                Field field = this.get(x, y);
                Team fieldTeam = field.occupant();
                resultBuilder.append(fieldTeam.toString());

                if (x != size - 1)
                    resultBuilder.append(" | ");
            }
            resultBuilder.append("\n");

            if (y != size - 1) {
                for (int i = 0; i < size; ++i) {
                    resultBuilder.append("---");
                    if (i != size - 1)
                        resultBuilder.append("|");
                }
                resultBuilder.append("\n");
            }
        }

        return resultBuilder.toString();
    }
}
