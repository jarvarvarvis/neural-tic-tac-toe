package me.jarvis.game;

import me.jarvis.board.Board;
import me.jarvis.player.Move;

public abstract class Ruleset {

    public abstract boolean isGameStillRunning(Board board);

    public abstract boolean isMoveValid(Move move, Board board);

    public abstract Result evaluate(Board board);
}
