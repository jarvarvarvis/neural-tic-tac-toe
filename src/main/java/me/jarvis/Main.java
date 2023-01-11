package me.jarvis;

import me.jarvis.board.Board;
import me.jarvis.game.Game;
import me.jarvis.game.Result;
import me.jarvis.game.TicTacToeRuleset;
import me.jarvis.player.Player;
import me.jarvis.player.players.RandomPlayer;

public class Main {

    public static void main(String[] args) {
        Board board = new Board(3);
        Player player1 = new RandomPlayer();
        Player player2 = new RandomPlayer();
        Game game = new Game(board, player1, player2);
        Result result = game.runWithRuleset(new TicTacToeRuleset());
        System.out.println(result);
    }
}