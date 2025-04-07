package graphs;

import java.util.ArrayList;

public class DFS {
    public void dfsInGraph(int node, ArrayList<Integer> res, boolean[] visited, ArrayList<ArrayList<Integer>> adj) {
        res.add(node);
        for (int nei : adj.get(node)) {
            if (!visited[nei]) {
                visited[nei] = true;
                dfsInGraph(nei, res, visited, adj);
            }
        }
    }

    // Function to return a list containing the DFS traversal of the graph.
    public ArrayList<Integer> dfs(ArrayList<ArrayList<Integer>> adj) {
        ArrayList<Integer> res = new ArrayList<>();
        boolean[] visited = new boolean[adj.size()];
        visited[0] = true;
        dfsInGraph(0, res, visited, adj);
        return res;
    }
}
