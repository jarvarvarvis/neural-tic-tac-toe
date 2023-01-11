package me.jarvis.game;

import me.jarvis.board.*;
import me.jarvis.player.Move;

import java.util.function.Function;
import java.util.stream.Stream;

public class TicTacToeRuleset extends Ruleset {

    @Override
    public boolean isGameStillRunning(Board board) {
        Stream<Field> fieldStream = board.getFlatFieldStream();
        return !fieldStream.allMatch(Field::isOccupied);
    }

    @Override
    public boolean isMoveValid(Move move, Board board) {
        return !board.get(move.x(), move.y()).isOccupied();
    }

    private static Result getIteratedResult(int limit,
                                            Function<Integer, Field> comparisonFieldSupplier,
                                            Function<Integer, Stream<Field>> fieldStreamSupplier) {
        for (int index = 0; index < limit; ++index) {
            Stream<Field> fields = fieldStreamSupplier.apply(index);
            Field comparisonField = comparisonFieldSupplier.apply(index);
            Team targetOccupant = comparisonField.occupant();
            if (targetOccupant == Team.NONE)
                continue;

            if (fields.allMatch(field -> field.occupant() == targetOccupant)) {
                return Result.winOf(targetOccupant);
            }
        }

        return Result.createNone();
    }

    private static Result getDirectResult(Field comparisonField, Stream<Field> stream) {
        Team targetOccupant = comparisonField.occupant();
        if (targetOccupant == Team.NONE)
            return Result.createNone();

        if (stream.allMatch(field -> field.occupant() == targetOccupant)) {
            return Result.winOf(targetOccupant);
        }

        return Result.createNone();
    }

    @Override
    public Result evaluate(Board board) {
        // Check rows
        int boardSize = board.getSize();
        Result rowResult = getIteratedResult(boardSize, row -> board.get(0, row), board::getRowFieldStream);
        if (rowResult.isSome()) {
            return rowResult;
        }

        // Check columns
        Result columnResult = getIteratedResult(boardSize, column -> board.get(column, 0), board::getColumnFieldStream);
        if (columnResult.isSome()) {
            return columnResult;
        }

        // Check BottomLeft-TopRight diagonal
        Result bltrDiagonalResult = getDirectResult(board.get(0, 0), board.getDiagonalFieldStream(DiagonalKind.BOTTOM_LEFT_TO_TOP_RIGHT));
        if (bltrDiagonalResult.isSome()) {
            return bltrDiagonalResult;
        }

        // Check TopLeft-BottomRight diagonal
        Result tlbrDiagonalResult = getDirectResult(board.get(0, boardSize - 1), board.getDiagonalFieldStream(DiagonalKind.TOP_LEFT_TO_BOTTOM_RIGHT));
        if (tlbrDiagonalResult.isSome()) {
            return tlbrDiagonalResult;
        }

        return Result.createNone();
    }
}
