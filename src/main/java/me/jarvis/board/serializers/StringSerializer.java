package me.jarvis.board.serializers;

import me.jarvis.board.*;

public class StringSerializer implements BoardSerializer<String> {

    @Override
    public String serialize(Board board) {
        StringBuilder builder = new StringBuilder();
        int size = board.getSize();

        builder.append(size);

        for (int y = 0; y < size; ++y) {
            builder.append(";");
            for (int x = 0; x < size; ++x) {
                Field field = board.get(x, y);
                Team occupant = field.occupant();
                builder.append(occupant.toString());
            }
        }

        return builder.toString();
    }

    @Override
    public Board deserialize(String serializedState) throws BoardSerializerException {
        String[] parts = serializedState.split(";");

        int size;
        try {
            size = Integer.parseInt(parts[0]);
        } catch (Exception e) {
            throw new BoardSerializerException(e);
        }

        assert parts.length == size + 1;

        Board board = new Board(size);
        for (int row = 0; row < parts.length - 1; ++row) {
            String serializedRow = parts[row + 1];
            assert serializedRow.length() == size;
            char[] rowChars = serializedRow.toCharArray();
            for (int column = 0; column < size; ++column) {
                Team team = Team.fromChar(rowChars[column]);
                board.set(column, row, team);
            }
        }

        return board;
    }
}
