package board;

import me.jarvis.board.Team;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TeamTest {

    @Test
    void testOpponentOfTeamOneIsTeamTwo() {
        Team actual = Team.ONE.opponent();
        Team expected = Team.TWO;
        assertEquals(expected, actual);
    }

    @Test
    void testOpponentOfTeamTwoIsTeamOne() {
        Team actual = Team.TWO.opponent();
        Team expected = Team.ONE;
        assertEquals(expected, actual);
    }

    @Test
    void testOpponentOfNoneIsNone() {
        Team actual = Team.NONE.opponent();
        Team expected = Team.NONE;
        assertEquals(expected, actual);
    }
}
