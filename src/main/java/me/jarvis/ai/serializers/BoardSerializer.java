package me.jarvis.ai.serializers;

import me.jarvis.ai.DataSerializer;
import me.jarvis.board.Board;
import me.jarvis.board.Field;
import me.jarvis.board.Team;

public class BoardSerializer extends DataSerializer<Board, Team> {

    double serializeTeam(Team team, Team ownTeam) {
        if (team == Team.NONE)
            return 0.0;
        if (team == ownTeam)
            return 1.0;
        return -1.0;
    }

    @Override
    public double[] serialize(Board input, Team ownTeam) {
        int size = input.getSize();
        double[] data = new double[size * size];
        for (int y = 0; y < size; ++y) {
            for (int x = 0; x < size; ++x) {
                Field field = input.get(x, y);
                Team occupant = field.occupant();
                data[y * size + x] = this.serializeTeam(occupant, ownTeam);
            }
        }

        return data;
    }
}
