package me.jarvis.game;

import me.jarvis.board.SquareBoard;
import me.jarvis.board.Field;
import me.jarvis.player.Move;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class GameLogic {

    public static Stream<Move> getAvailableMoveStream(SquareBoard board) {
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
