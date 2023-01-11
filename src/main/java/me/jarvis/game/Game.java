package me.jarvis.game;

import me.jarvis.board.Board;
import me.jarvis.board.Team;
import me.jarvis.player.Move;
import me.jarvis.player.Player;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Game {

    public static Logger log = LogManager.getLogger(Game.class);

    private boolean isFirstPlayerCurrent;

    private final Player firstPlayer;
    private final Player secondPlayer;

    private final Board board;

    public Game(Board board, Player firstPlayer, Player secondPlayer) {
        this.isFirstPlayerCurrent = true;
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
        this.board = board;
    }

    private Player getCurrentPlayer() {
        return this.isFirstPlayerCurrent ? this.firstPlayer : this.secondPlayer;
    }

    private Team getCurrentTeam() {
        return this.isFirstPlayerCurrent ? Team.ONE : Team.TWO;
    }

    private void advance() {
        this.isFirstPlayerCurrent = !this.isFirstPlayerCurrent;
    }

    public Result runWithRuleset(Ruleset ruleset) {
        while (ruleset.isGameStillRunning(this.board)) {
            Player currentPlayer = this.getCurrentPlayer();
            Move move = currentPlayer.getMove(this.board);
            if (!ruleset.isMoveValid(move, this.board)) {
                log.info("{} made an invalid move: {}", this.getCurrentTeam(), move);
                return Result.winOf(this.getCurrentTeam().opponent());
            }

            this.board.set(move.x(), move.y(), this.getCurrentTeam());
            this.advance();

            System.out.println(this.board);
            System.out.println();

            Result evaluation = ruleset.evaluate(this.board);
            if (evaluation.isSome()) {
                return evaluation;
            }
        }

        return Result.createDraw();
    }
}
