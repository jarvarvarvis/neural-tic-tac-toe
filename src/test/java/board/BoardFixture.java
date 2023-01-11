package board;

import me.jarvis.board.BitBoard;
import me.jarvis.board.Board;
import me.jarvis.board.SquareBoard;
import me.jarvis.board.Team;

public class BoardFixture {

    public static Board createSquareBoardWithCheckerboardPattern(Team firstTeam, int size) {
        Board board = new SquareBoard(size);
        Team currentTeam = firstTeam;

        for (int y = 0; y < size; ++y) {
            for (int x = 0; x < size; ++x) {
                board.set(x, y, currentTeam);
                currentTeam = currentTeam.opponent();
            }
        }

        return board;
    }

    public static Board createBitBoardWithCheckerboardPattern(Team firstTeam) {
        Board board = new BitBoard();
        Team currentTeam = firstTeam;

        for (int y = 0; y < 3; ++y) {
            for (int x = 0; x < 3; ++x) {
                board.set(x, y, currentTeam);
                currentTeam = currentTeam.opponent();
            }
        }

        return board;
    }
}
