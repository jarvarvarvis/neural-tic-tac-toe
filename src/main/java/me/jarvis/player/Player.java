package me.jarvis.player;

import me.jarvis.board.Board;

public interface Player {

    Move getMove(Board board);
}
