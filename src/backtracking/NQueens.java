package backtracking;

import java.time.Duration;
import java.time.Instant;

class NQueens {
    public int solutions = 0;

    public int nQueens(int n) {
        int badCol = 0; // Columns under attack
        int dig1 = 0; // Left Dig under attack
        int dig2 = 0; // Right Dig
        nQueensSolver(n, 0, badCol, dig1, dig2);
        return solutions;
    }

    public void nQueensSolver(int n, int row, int badCol, int dig1, int dig2) {
        if (row == n) {
            solutions++;
            return;
        }
        int safeSpots = (1 << n) - 1; // n=4 -> 1111
        safeSpots = safeSpots & ~(badCol | dig1 | dig2);
        while (safeSpots > 0) {
            int pos = safeSpots & -safeSpots;
            if (pos != 0) nQueensSolver(n, row + 1, badCol | pos, (dig1 | pos) >> 1, (dig2 | pos) << 1);
            safeSpots -= pos;
        }
    }
}

class Main {
    public static void main(String[] args) {
        Instant start = Instant.now();

        NQueens obj = new NQueens();
        System.out.println(obj.nQueens(16));

        Instant end = Instant.now();
        Duration time = Duration.between(start, end);
        System.out.println("Time Taken: " + time.toSeconds() + "s (" + time.toMillis() + "ms)");
    }
}
