package graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Connected_Comp {
    // Adjacency matrix is given
    public int NumOfComponents(int[][] isConnected) {
        boolean[] visited = new boolean[isConnected.length];
        int count = 0;
        for (int i = 0; i < isConnected.length; i++) {
            if (!visited[i]) {
                bfs(i, visited, isConnected);
                count++;
            }
        }
        return count;
    }

    static public void bfs(int start, boolean[] visited, int[][] adj) {
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        visited[start] = true;

        while (!q.isEmpty()) {
            int node = q.remove();
            for (int i = 0; i < adj.length; i++) {
                if (adj[node][i] == 1 && !visited[i]) {
                    visited[i] = true;
                    q.offer(i);
                }
            }
        }
    }
}
