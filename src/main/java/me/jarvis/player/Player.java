package me.jarvis.player;

import me.jarvis.board.SquareBoard;

public interface Player {

    Move getMove(SquareBoard board);
}
