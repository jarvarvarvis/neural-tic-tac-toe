package me.jarvis.board;

public record Field(Team occupant) {

    public boolean isOccupied() {
        return this.occupant != Team.NONE;
    }
}
