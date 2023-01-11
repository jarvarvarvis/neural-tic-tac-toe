package me.jarvis.player;

import me.jarvis.board.Board;
import me.jarvis.board.Field;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class PlayerLogic {

    public static Stream<Move> getAvailableMoveStream(Board board) {
        Stream<Integer> xCoordStream = IntStream.range(0, board.getSize()).boxed();
        return xCoordStream.flatMap(x -> {
            Stream<Integer> yCoordStream = IntStream.range(0, board.getSize()).boxed();
            return yCoordStream.filter(y -> {
                Field field = board.get(x, y);
                return !field.isOccupied();
            }).map(y -> new Move(x, y));
        });
    }
}
