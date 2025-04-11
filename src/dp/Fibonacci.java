package dp;

public class Fibonacci {
    // Using memoization concept -> Top-Down Approach (Recursion + DP)
    static int fibo(int n, int[] dp) {
        if (n <= 1) return n;
        if (dp[n] != -1) {
            return dp[n];
        }
        dp[n] = fibo(n-1, dp) + fibo(n-2, dp);
        return dp[n];
    }

    // Using Bottom-Up Approach (Iterative)
    static int fibo2(int n) {
        if (n <= 1) return n;
        int prev = 0, curr = 1;
        for (int i = 2; i <= n; i++) {
            int next = prev + curr;
            prev = curr;
            curr = next;
        }
        return curr;
    }

    public static void main(String[] args) {
        int n = 10;
        int[] dp = new int[n+1];
        for (int i = 0; i <= n; i++) {
            dp[i] = -1;
        }
        System.out.println(fibo(n, dp));
    }
}
