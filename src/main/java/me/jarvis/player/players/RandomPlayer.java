package me.jarvis.player.players;

import me.jarvis.board.SquareBoard;
import me.jarvis.player.Move;
import me.jarvis.player.Player;
import me.jarvis.game.GameLogic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class RandomPlayer implements Player {

    private static final Logger log = LogManager.getLogger(RandomPlayer.class);

    private final Random random;

    public RandomPlayer() {
        this.random = new Random();
    }
    
    @Override
    public Move getMove(SquareBoard board) {
        Stream<Move> moveStream = GameLogic.getAvailableMoveStream(board);
        List<Move> moves = moveStream.toList();
        int index = this.random.nextInt(moves.size());
        Move move = moves.get(index);
        log.info("Chosen move: {}", move);
        return move;
    }
}
