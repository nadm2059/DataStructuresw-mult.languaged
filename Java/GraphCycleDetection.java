import java.util.*;

// Class to detect cycles in graphs using DFS and Union-Find approaches
public class GraphCycleDetection {

    // DFS approach for cycle detection in a directed graph
    public static boolean hasCycleDFS(int n, List<List<Integer>> graph) {
        boolean[] visited = new boolean[n];   // Tracks visited nodes globally
        boolean[] recStack = new boolean[n];  // Tracks nodes in current recursion stack

        // Iterate through all nodes to cover disconnected components
        for (int i = 0; i < n; i++) {
            if (dfs(i, graph, visited, recStack)) return true; // If cycle detected, return true
        }
        return false;  // No cycle detected in any component
    }

    // Helper DFS function for cycle detection
    private static boolean dfs(int node, List<List<Integer>> graph, boolean[] visited, boolean[] recStack) {
        if (recStack[node]) return true;   // Cycle found if node is already in recursion stack
        if (visited[node]) return false;   // Skip if node already processed without cycle

        visited[node] = true;    // Mark current node as visited
        recStack[node] = true;   // Add current node to recursion stack

        // Visit all neighbors
        for (int neighbor : graph.get(node)) {
            if (dfs(neighbor, graph, visited, recStack)) return true; // If cycle detected downstream, return true
        }

        recStack[node] = false;  // Remove current node from recursion stack before returning
        return false;            // No cycle found from this node
    }

    // Union-Find data structure for cycle detection in undirected graphs
    static class UnionFind {
        int[] parent;   // Parent array for disjoint sets
        int[] rank;     // Rank array to keep tree shallow

        // Constructor initializes parent and rank arrays
        UnionFind(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) parent[i] = i;  // Each node is its own parent initially
        }

        // Find operation with path compression
        int find(int x) {
            if (parent[x] != x)          // If x is not root
                parent[x] = find(parent[x]);  // Recursively find root and compress path
            return parent[x];            // Return root of x
        }

        // Union operation to join two sets; returns false if they are already connected (cycle)
        boolean union(int x, int y) {
            int rootX = find(x);  // Find root of x
            int rootY = find(y);  // Find root of y

            if (rootX == rootY) return false;  // Already connected -> cycle detected

            // Union by rank to keep tree balanced
            if (rank[rootX] < rank[rootY]) parent[rootX] = rootY;
            else if (rank[rootX] > rank[rootY]) parent[rootY] = rootX;
            else {
                parent[rootY] = rootX;
                rank[rootX]++;
            }
            return true;  // Union successful, no cycle detected here
        }
    }

    // Method to detect cycle in undirected graph using Union-Find
    public static boolean hasCycleUnionFind(int n, int[][] edges) {
        UnionFind uf = new UnionFind(n);  // Initialize Union-Find structure for n nodes

        // Iterate over all edges
        for (int[] edge : edges) {
            // If union fails, a cycle exists
            if (!uf.union(edge[0], edge[1])) return true;
        }
        return false;  // No cycles detected after processing all edges
    }

    // Main method demonstrating cycle detection usage
    public static void main(String[] args) {
        // ----- Directed Graph for DFS cycle detection -----
        int nDirected = 4;  // Number of nodes in directed graph

        // Initialize adjacency list for directed graph
        List<List<Integer>> directedGraph = new ArrayList<>();
        for (int i = 0; i < nDirected; i++) {
            directedGraph.add(new ArrayList<>());  // Add empty adjacency list for each node
        }

        // Add edges to directed graph: 0->1, 1->2, 2->0 (cycle), 2->3
        directedGraph.get(0).add(1);
        directedGraph.get(1).add(2);
        directedGraph.get(2).add(0);
        directedGraph.get(2).add(3);

        // Check for cycle using DFS approach and print result
        boolean hasCycleDFS = hasCycleDFS(nDirected, directedGraph);
        System.out.println("Directed Graph has cycle (DFS): " + hasCycleDFS);  // Expected: true

        // ----- Undirected Graph for Union-Find cycle detection -----
        int nUndirected = 4;  // Number of nodes in undirected graph

        // Edge list representation of undirected graph with a cycle
        int[][] edges = {
            {0, 1},
            {1, 2},
            {2, 3},
            {3, 1}  // This edge creates a cycle
        };

        // Check for cycle using Union-Find approach and print result
        boolean hasCycleUF = hasCycleUnionFind(nUndirected, edges);
        System.out.println("Undirected Graph has cycle (Union-Find): " + hasCycleUF);  // Expected: true
    }
}
