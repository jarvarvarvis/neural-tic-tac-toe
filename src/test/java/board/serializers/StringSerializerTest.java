package board.serializers;

import board.BoardFixture;
import me.jarvis.board.Board;
import me.jarvis.board.BoardSerializerException;
import me.jarvis.board.Team;
import me.jarvis.board.serializers.StringSerializer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StringSerializerTest {

    public static final String SERIALIZED_CHECKERBOARD_BOARD = "3;OXO;XOX;OXO";
    private Board board;

    @BeforeEach
    void setUp() {
        this.board = BoardFixture.createBoardWithCheckerboardPattern(Team.ONE, 3);
    }

    @Test
    void testBoardSerialize() {
        StringSerializer serializer = new StringSerializer();
        String actual = serializer.serialize(board);
        assertEquals(SERIALIZED_CHECKERBOARD_BOARD, actual);
    }

    @Test
    void testBoardDeserialize() throws BoardSerializerException {
        StringSerializer serializer = new StringSerializer();
        Board expected = this.board;
        Board actual = serializer.deserialize(SERIALIZED_CHECKERBOARD_BOARD);
        assertEquals(expected, actual);
    }
}
