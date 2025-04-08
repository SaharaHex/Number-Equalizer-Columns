package model;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class SequenceController implements SequenceProvider {
    private final Map<String, int[]> seeds;

    public SequenceController() {
        seeds = new HashMap<>();
        seeds.put("Seed 1", new int[]{5, 6, 2, 8, 4, 7, 3, 2, 6, 7, 1, 1, 2, 3, 5, 2, 6, 2, 1, 2}); // 10 number
        seeds.put("Seed 2", new int[]{3, 7, 5, 5, 8, 2, 4, 6, 4, 3, 2, 1, 3, 5, 2, 2, 4, 1, 1, 5}); // 10 number
        seeds.put("Seed 3", new int[]{4, 5, 6, 7, 3, 8, 2, 5, 6, 5, 1, 9, 3, 2, 4, 5, 8, 1, 2, 2}); // 10 number
        seeds.put("Seed 4", new int[]{8, 2, 5, 6, 1, 7, 4, 6, 3, 4, 6, 4, 2, 3, 1, 5, 2, 1, 1, 5}); // 10 number
        seeds.put("Seed 5", new int[]{6, 4, 2, 8, 3, 7, 5, 6, 9, 1, 4, 6, 2, 3, 6, 1, 1, 5, 4, 6, 3, 4, 1, 2, 1, 2}); // 15 number
        seeds.put("Seed 6", new int[]{5, 8, 3, 7, 4, 6, 2, 9, 1, 5, 7, 6, 3, 4, 3, 1, 2, 3, 4, 9, 1, 8, 4, 1, 1, 5}); // 15 number
        seeds.put("Seed 7", new int[]{4, 8, 6, 2, 5, 7, 3, 6, 3, 4, 2, 5, 2, 1, 2, 4, 8, 7, 3, 5, 2, 6, 9, 4, 3, 7, 2, 1, 2, 1}); // 20 number
        seeds.put("Seed 8", new int[]{3, 7, 2, 9, 4, 6, 8, 5, 1, 4, 3, 2, 8, 4, 5, 9, 6, 2, 3, 5, 1, 8, 4, 6, 5, 3, 1, 2, 1, 1}); // 20 number
        seeds.put("Seed 9", new int[]{9, 5, 6, 7, 6, 6, 5, 1, 3, 5, 1, 9, 3, 2, 4, 5, 3, 1, 2, 1}); // 10 number
        seeds.put("Seed 10", new int[]{5, 2, 5, 6, 1, 7, 8, 4, 2, 4, 4, 9, 2, 3, 8, 3, 1, 4, 3, 3}); // 10 number
    }

    @Override
    public Map.Entry<String, int[]> getRandomSeed() {
        // Convert the keys of the seeds map to an array
        String[] keys = seeds.keySet().toArray(new String[0]);

        // Generate a random index
        Random random = new Random();
        int randomIndex = random.nextInt(keys.length);

        // Get the random key and its associated sequence
        String randomKey = keys[randomIndex];
        int[] sequence = seeds.get(randomKey);

        // Print the random seed name and its sequence
        for (int num : sequence) {
            System.out.print(num + " ");
        }
        System.out.println(" Selected: " + randomKey);

        // Return the key-value pair as a Map.Entry
        return Map.entry(randomKey, sequence);
    }
}