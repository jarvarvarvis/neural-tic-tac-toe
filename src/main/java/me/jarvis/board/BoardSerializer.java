package me.jarvis.board;

public interface BoardSerializer<O> {

    O serialize(Board board);

    Board deserialize(O serializedState) throws BoardSerializerException;
}
