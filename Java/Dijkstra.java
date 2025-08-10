import java.util.*;

public class Dijkstra {

    // Node class to represent each vertex and distance, implements Comparable for PriorityQueue sorting
    static class Node implements Comparable<Node> {
        int vertex, dist;  // vertex id and distance from source

        Node(int vertex, int dist) {
            this.vertex = vertex;
            this.dist = dist;
        }

        // Compare nodes based on distance for priority queue ordering
        public int compareTo(Node other) {
            return Integer.compare(this.dist, other.dist);
        }
    }

    // Dijkstra algorithm: finds shortest distances from src to all vertices in graph
    // graph is adjacency list of nodes: List of (neighbor, weight) pairs
    public static int[] dijkstra(int n, List<List<Node>> graph, int src) {
        int[] dist = new int[n];               // Distance array
        Arrays.fill(dist, Integer.MAX_VALUE); // Initialize all distances to infinity
        dist[src] = 0;                        // Distance to source is zero

        PriorityQueue<Node> pq = new PriorityQueue<>(); // Min-heap to get vertex with min dist
        pq.offer(new Node(src, 0));                      // Add source node

        // Process queue until empty
        while (!pq.isEmpty()) {
            Node current = pq.poll();       // Get node with smallest distance

            // Skip if we already found a better path
            if (current.dist > dist[current.vertex]) continue;

            // Relax edges
            for (Node neighbor : graph.get(current.vertex)) {
                int newDist = dist[current.vertex] + neighbor.dist;
                if (newDist < dist[neighbor.vertex]) {
                    dist[neighbor.vertex] = newDist;  // Update shortest distance
                    pq.offer(new Node(neighbor.vertex, newDist)); // Add updated distance to queue
                }
            }
        }
        return dist; // Return shortest distances array
    }

    // Main method to test Dijkstra's algorithm
    public static void main(String[] args) {
        int n = 5;  // Number of vertices in graph

        // Initialize adjacency list for graph
        List<List<Node>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        // Add edges: (vertex, weight)
        // Example graph:
        // 0 -> 1 (10), 0 -> 4 (5)
        // 1 -> 2 (1), 1 -> 4 (2)
        // 2 -> 3 (4)
        // 3 -> 2 (6), 3 -> 0 (7)
        // 4 -> 1 (3), 4 -> 2 (9), 4 -> 3 (2)
        graph.get(0).add(new Node(1, 10));
        graph.get(0).add(new Node(4, 5));
        graph.get(1).add(new Node(2, 1));
        graph.get(1).add(new Node(4, 2));
        graph.get(2).add(new Node(3, 4));
        graph.get(3).add(new Node(2, 6));
        graph.get(3).add(new Node(0, 7));
        graph.get(4).add(new Node(1, 3));
        graph.get(4).add(new Node(2, 9));
        graph.get(4).add(new Node(3, 2));

        // Run Dijkstra from source vertex 0
        int[] distances = dijkstra(n, graph, 0);

        // Print shortest distances from source
        System.out.println("Vertex\tDistance from Source");
        for (int i = 0; i < n; i++) {
            System.out.println(i + "\t" + distances[i]);
        }
    }
}
