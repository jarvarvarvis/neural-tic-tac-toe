package me.jarvis.game;

import me.jarvis.board.Team;

public final class Result {

    private final ResultKind kind;

    private Result(ResultKind kind) {
        this.kind = kind;
    }

    public boolean isSome() {
        return this.kind != ResultKind.NONE;
    }

    public static Result createDraw() {
        return new Result(ResultKind.DRAW);
    }

    public static Result winOf(Team team) {
        assert team != Team.NONE;
        if (team == Team.ONE) {
            return new Result(ResultKind.WIN_FOR_ONE);
        }
        return new Result(ResultKind.WIN_FOR_TWO);
    }

    public static Result createNone() {
        return new Result(ResultKind.NONE);
    }

    @Override
    public String toString() {
        return "Result{" +
            "kind=" + kind +
            '}';
    }
}
