package graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class BFS {
    // Function to return Breadth First Search Traversal of given graph.
    static public ArrayList<Integer> bfs(ArrayList<ArrayList<Integer>> adj) {
        int n = adj.size(); // Number of nodes
        boolean[] visited = new boolean[n]; // Visited array
        ArrayList<Integer> res = new ArrayList<>(); // Resulting vector
        Queue<Integer> q = new LinkedList<>();

        q.add(0);
        visited[0] = true;

        while (!q.isEmpty()) {
            int front = q.remove();
            res.add(front);
            for (int nei : adj.get(front)) {
                if (!visited[nei]) {
                    q.add(nei);
                    visited[nei] = true;
                }
            }
        }
        return res;
    }
}
// Time Complexity: O(V + E) (Each node and edge is processed once)
//Space Complexity: O(V + E) (Adjacency list, visited array, and queue)
