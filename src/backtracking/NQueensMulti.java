package backtracking;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

class MyThread implements Runnable {
    int n, row, badCols, dig1, dig2, pos;
    AtomicInteger totalCount;
    int[] solutions;

    MyThread(int r, int c, int d1, int d2, int n, int p, AtomicInteger totalCount) {
        this.n = n;
        row = r;
        badCols = c;
        dig1 = d1;
        dig2 = d2;
        pos = p;
        this.totalCount = totalCount;
        solutions = new int[n];
        solutions[row] = Integer.numberOfTrailingZeros(pos); // __builtin_ctz(n);
    }

    @Override
    public void run() {
        int[] newSolutions = solutions.clone();
        solve(n, row + 1, badCols | pos, (dig1 | pos) >> 1, (dig2 | pos) << 1, newSolutions);
    }

    void solve(int n, int row, int badCol, int dig1, int dig2, int[] solutions) {
        if (row == n) {
            totalCount.incrementAndGet();
//            try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/backtracking/N-Queen.txt", true))) {
//                for (int ele : solutions) {
//                    writer.write(ele + " ");
//                }
//                writer.write("\n");
//            } catch (IOException e) {
//                System.out.println("YOU SUCK");
//            }
            return;
        }
        int safeSpots = ((1 << n) - 1) & ~(badCol | dig1 | dig2);
        while (safeSpots > 0) {
            pos = safeSpots & -safeSpots;
            safeSpots -= pos;
            solutions[row] = Integer.numberOfTrailingZeros(pos);
            int[] newSolutions = solutions.clone();
            solve(n, row + 1, badCol | pos, (dig1 | pos) >> 1, (dig2 | pos) << 1, newSolutions);
        }
    }
}

public class NQueensMulti {
    public static int nQueens(int n) throws InterruptedException {
        AtomicInteger totalCount = new AtomicInteger();
        List<Thread> threads = new ArrayList<>();

//        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/backtracking/N-Queen.txt", false))) {
//            writer.write(""); // Clears the file
//        } catch (IOException _) {
//            System.out.println("File Not Found");
//        }

        for (int i = 0; i < n; i++) {
            int pos = (1 << i);
            Thread t = new Thread(new MyThread(0, 0, 0, 0, n, pos, totalCount));
            threads.add(t);
            t.start();
        }

        for (Thread t : threads) t.join();

//        try (FileWriter writer = new FileWriter("src/backtracking/N-Queen.txt")) {
//            writer.write(Integer.toString(totalCount.get()));
//        } catch(IOException e) {
//            System.out.println("YOU SUCK");
//        }
        return totalCount.get();
    }

    public static void main(String[] args) throws InterruptedException {
        Instant start = Instant.now();

//        Scanner sc = new Scanner(System.in);
//        System.out.print("Enter the value of n = ");
//        int n = sc.nextInt();
        int ans = nQueens(6);
        System.out.println("Found " + ans + " solutions");
        System.out.println("All Solutions are stored in N-Queen.txt");

        Instant end = Instant.now();
        Duration time = Duration.between(start, end);
        System.out.println("Time Taken: " + time.toSeconds() + "s (" + time.toMillis() + "ms)");
    }
}