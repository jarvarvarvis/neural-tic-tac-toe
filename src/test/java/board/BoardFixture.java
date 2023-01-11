package board;

import me.jarvis.board.Board;
import me.jarvis.board.Team;

public class BoardFixture {

    public static Board createBoardWithCheckerboardPattern(Team firstTeam, int size) {
        Board board = new Board(size);
        Team currentTeam = firstTeam;

        for (int y = 0; y < size; ++y) {
            for (int x = 0; x < size; ++x) {
                board.set(x, y, currentTeam);
                currentTeam = currentTeam.opponent();
            }
        }

        return board;
    }
}
