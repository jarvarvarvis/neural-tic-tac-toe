package me.jarvis.board;

public class BitBoard extends Board {

    private static final int SIZE = 3;

    private static final int SEGMENT_WIDTH = 3;

    private static final int TEAM_ONE_SEGMENT = 0b100;
    private static final int TEAM_TWO_SEGMENT = 0b010;
    private static final int NONE_SEGMENT    = 0b001;

    private static final int SEGMENT_MASK = 0b111;

    /**
     * The data contains 9 three-bit segments (27 bits in total) that specify the occupying team:
     * 1|2|E 1|2|E 1|2|E <br>
     * 1|2|E 1|2|E 1|2|E <br>
     * 1|2|E 1|2|E 1|2|E <br>
     * <br>
     * 1 = Team One <br>
     * 2 = Team Two <br>
     * E = Empty/None <br>
     */

    private static int initializeData() {
        int data = NONE_SEGMENT;
        for (int i = 0; i < SIZE * SIZE; ++i) {
            data <<= SEGMENT_WIDTH;
            data |= NONE_SEGMENT;
        }
        return data;
    }

    private static Team mapValueToTeam(int value) {
        if (value == TEAM_ONE_SEGMENT)
            return Team.ONE;
        if (value == TEAM_TWO_SEGMENT)
            return Team.TWO;
        if (value == NONE_SEGMENT)
            return Team.NONE;
        throw new IllegalArgumentException(String.format("Unexpected value %d", value));
    }

    private static int mapTeamToValue(Team team) {
        return switch (team) {
            case ONE -> TEAM_ONE_SEGMENT;
            case TWO -> TEAM_TWO_SEGMENT;
            case NONE -> NONE_SEGMENT;
        };
    }

    private int data;

    public BitBoard() {
        this.data = initializeData();
    }

    @Override
    public int getSize() {
        return SIZE;
    }

    private static int getShift(int x, int y) {
        return (y * SIZE + x) * SEGMENT_WIDTH;
    }

    @Override
    public Field get(int x, int y) {
        int shift = getShift(x, y);
        int value = (data >> shift) & SEGMENT_MASK;
        return new Field(mapValueToTeam(value));
    }

    @Override
    public void set(int x, int y, Team team) {
        int shift = getShift(x, y);
        int value = mapTeamToValue(team) << shift;
        int clear = ~(SEGMENT_MASK << shift);

        this.data = (this.data & clear) | value;
    }
}
