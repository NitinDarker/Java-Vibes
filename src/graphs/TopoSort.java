package graphs;

import java.util.ArrayList;
import java.util.Stack;

public class TopoSort {
    private static ArrayList<ArrayList<Integer>> convertAdj(int[][] edges, int V) {
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
        }
        return adj;
    }

    private static void dfs(int start, ArrayList<ArrayList<Integer>> adj, int[] visited, Stack<Integer> st) {
        visited[start] = 1;
        for (int node : adj.get(start)) {
            if (visited[node] == 0) dfs(node, adj, visited, st);
        }
        st.add(start);
    }

    public static ArrayList<Integer> topoSort(int V, int[][] edges) {
        ArrayList<ArrayList<Integer>> adj = convertAdj(edges, V);
        int[] visited = new int[V];
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < V; i++) {
            if (visited[i] == 0) {
                dfs(i, adj, visited, st);
            }
        }
        ArrayList<Integer> sorted = new ArrayList<>();
        while (!st.isEmpty()) {
            sorted.add(st.pop());
        }
        return sorted;
    }
}
