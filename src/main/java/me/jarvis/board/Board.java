package me.jarvis.board;

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

    public abstract Stream<Field> getFlatFieldStream();
    public abstract Stream<Field> getRowFieldStream(int row);
    public abstract Stream<Field> getColumnFieldStream(int columnIndex);
    public abstract Stream<Field> getDiagonalFieldStream(DiagonalKind kind);

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
