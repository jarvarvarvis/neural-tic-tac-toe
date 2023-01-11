package me.jarvis.game;

import me.jarvis.board.SquareBoard;
import me.jarvis.player.Move;

public abstract class Ruleset {

    public abstract boolean isGameStillRunning(SquareBoard board);

    public abstract boolean isMoveValid(Move move, SquareBoard board);

    public abstract Result evaluate(SquareBoard board);
}
