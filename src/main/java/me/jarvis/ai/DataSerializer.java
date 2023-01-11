package me.jarvis.ai;

public abstract class DataSerializer<I, C> {

    public abstract double[] serialize(I input, C context);
}
