package board;

import me.jarvis.board.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BitBoardTest {

    public static final int BOARD_SIZE = 3;

    private Board board;

    @BeforeEach
    void setUp() {
        this.board = BoardFixture.createBitBoardWithCheckerboardPattern(Team.ONE);
    }

    @Test
    void testGetFlatFieldStreamHasValidLength() {
        Stream<Field> fields = this.board.getFlatFieldStream();
        long expected = BOARD_SIZE * BOARD_SIZE;
        long actual = fields.count();

        assertEquals(expected, actual);
    }

    @Test
    void testGetRowFieldStreamHasValidLength() {
        for (int row = 0; row < BOARD_SIZE; ++row) {
            Stream<Field> rowFieldStream = this.board.getRowFieldStream(row);
            long actual = rowFieldStream.count();

            assertEquals(BOARD_SIZE, actual);
        }
    }

    @Test
    void testGetColumnFieldStreamHasValidLength() {
        for (int column = 0; column < BOARD_SIZE; ++column) {
            Stream<Field> columnFieldStream = this.board.getColumnFieldStream(column);
            long actual = columnFieldStream.count();

            assertEquals(BOARD_SIZE, actual);
        }
    }

    @Test
    void testGetTopLeftBottomRightDiagonalFieldStreamHasValidLength() {
        Stream<Field> diagonalFieldStream = this.board.getDiagonalFieldStream(DiagonalKind.TOP_LEFT_TO_BOTTOM_RIGHT);
        long actual = diagonalFieldStream.count();

        assertEquals(BOARD_SIZE, actual);
    }

    @Test
    void testGetBottomLeftTopRightDiagonalFieldStreamHasValidLength() {
        Stream<Field> diagonalFieldStream = this.board.getDiagonalFieldStream(DiagonalKind.BOTTOM_LEFT_TO_TOP_RIGHT);
        long actual = diagonalFieldStream.count();

        assertEquals(BOARD_SIZE, actual);
    }

    @Test
    void testBitBoardGetTeamOneFields() {
        assertEquals(Team.ONE, this.board.get(0, 0).occupant());
        assertEquals(Team.ONE, this.board.get(2, 0).occupant());
        assertEquals(Team.ONE, this.board.get(1, 1).occupant());
        assertEquals(Team.ONE, this.board.get(0, 2).occupant());
        assertEquals(Team.ONE, this.board.get(2, 2).occupant());
    }

    @Test
    void testBitBoardGetTeamTwoFields() {
        assertEquals(Team.TWO, this.board.get(1, 0).occupant());
        assertEquals(Team.TWO, this.board.get(0, 1).occupant());
        assertEquals(Team.TWO, this.board.get(2, 1).occupant());
        assertEquals(Team.TWO, this.board.get(1, 2).occupant());
    }

    @Test
    void testBitBoardSetFieldsToOpponentAndGetTeams() {
        Board manuallyFlippedBoard = BoardFixture.createBitBoardWithCheckerboardPattern(Team.ONE);
        for (int y = 0; y < BOARD_SIZE; ++y) {
            for (int x = 0; x < BOARD_SIZE; ++x) {
                Team occupant = manuallyFlippedBoard.get(x, y).occupant();
                manuallyFlippedBoard.set(x, y, occupant.opponent());
            }
        }

        assertEquals(Team.TWO, manuallyFlippedBoard.get(0, 0).occupant());
        assertEquals(Team.TWO, manuallyFlippedBoard.get(2, 0).occupant());
        assertEquals(Team.TWO, manuallyFlippedBoard.get(1, 1).occupant());
        assertEquals(Team.TWO, manuallyFlippedBoard.get(0, 2).occupant());
        assertEquals(Team.TWO, manuallyFlippedBoard.get(2, 2).occupant());

        assertEquals(Team.ONE, manuallyFlippedBoard.get(1, 0).occupant());
        assertEquals(Team.ONE, manuallyFlippedBoard.get(0, 1).occupant());
        assertEquals(Team.ONE, manuallyFlippedBoard.get(2, 1).occupant());
        assertEquals(Team.ONE, manuallyFlippedBoard.get(1, 2).occupant());
    }
}
