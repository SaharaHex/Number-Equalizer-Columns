package model;

import java.util.Map;

public interface SequenceProvider {
    Map.Entry<String, int[]> getRandomSeed();
}