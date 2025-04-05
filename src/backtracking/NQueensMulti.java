package backtracking;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

class MyTask implements Runnable {
    int n, row, badCols, dig1, dig2, pos;
    AtomicInteger solutions;

    MyTask(int r, int c, int d1, int d2, int n, int p, AtomicInteger solutions) {
        this.n = n;
        row = r;
        badCols = c;
        dig1 = d1;
        dig2 = d2;
        pos = p;
        this.solutions = solutions;
    }

    @Override
    public void run() {
        solve(n, row + 1, badCols | pos, (dig1 | pos) >> 1, (dig2 | pos) << 1);
    }

    void solve(int n, int row, int badCol, int dig1, int dig2) {
        if (row == n) {
            solutions.incrementAndGet();
            return;
        }
        int safeSpots = ((1 << n) - 1) & ~(badCol | dig1 | dig2);
        while (safeSpots > 0) {
            int pos = safeSpots & -safeSpots;
            safeSpots -= pos;
            solve(n, row + 1, badCol | pos, (dig1 | pos) >> 1, (dig2 | pos) << 1);
        }
    }
}
public class NQueensMulti {
    public static int nQueens(int n) throws InterruptedException {
        AtomicInteger solutions = new AtomicInteger();
        List<Thread> threads = new ArrayList<>();
        int safeSpots = (1 << n) - 1;

        int row = 0;
        int badCol = 0, dig1 = 0, dig2 = 0;
        safeSpots = safeSpots & ~(badCol | dig1 | dig2);

        while (safeSpots > 0) {
            int pos = safeSpots & -safeSpots;
            safeSpots -= pos;
            Thread t = new Thread(new MyTask(row, badCol, dig1, dig2, n, pos, solutions));
            threads.add(t);
            t.start();
        }

        for (Thread t : threads) t.join(); // Wait for all threads to finish
        return solutions.get();
    }

    public static void main(String[] args) throws InterruptedException {
        Instant start = Instant.now();
        System.out.println(nQueens(18));
        Instant end = Instant.now();
        Duration time = Duration.between(start, end);
        System.out.println("Time Taken: " + time.toMillis() + "ms");
    }
}
