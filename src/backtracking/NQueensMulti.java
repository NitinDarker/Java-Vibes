package backtracking;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class NQueensMulti {
    static int n;
    static AtomicInteger totalCount = new AtomicInteger(0);
    static List<int[]> solutionsList = Collections.synchronizedList(new ArrayList<>());

    public static void solveParallel(int size) throws InterruptedException {
        n = size;
        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        for (int col = 0; col < n; col++) {
            int currentPos = 1 << col;
            int[] initialSolution = new int[n];
            initialSolution[0] = col;

            int finalCol = col;
            executor.execute(() -> {
                solve(1, currentPos, currentPos >> 1, currentPos << 1, initialSolution.clone());
            });
        }

        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.MINUTES);
        System.out.println("Total solutions: " + totalCount.get());
    }

    private static void solve(int row, int badCol, int dig1, int dig2, int[] solutions) {
        if (row == n) {
            solutionsList.add(solutions.clone());
            totalCount.incrementAndGet();
            return;
        }

        int safeSpots = ((1 << n) - 1) & ~(badCol | dig1 | dig2);
        while (safeSpots > 0) {
            int currentPos = safeSpots & -safeSpots;
            safeSpots -= currentPos;
            solutions[row] = Integer.numberOfTrailingZeros(currentPos);
            solve(row + 1, badCol | currentPos, (dig1 | currentPos) >> 1, (dig2 | currentPos) << 1, solutions);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int boardSize = 17; // You can change this
        long start = System.currentTimeMillis();
        solveParallel(boardSize);
        long end = System.currentTimeMillis();
        System.out.println("Execution Time: " + (end - start) + "ms");
    }
}
