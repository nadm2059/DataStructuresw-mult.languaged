using System;                     // Provides base class functionality like Console
using System.Collections.Generic; // Provides List and Dictionary types
using System.Linq;                // Provides LINQ functions like Enumerable.Repeat
using System.Collections;         // Included but not necessary here

public class Dijkstra {
    // Computes shortest paths from 'start' node to all other nodes in a graph using Dijkstra's algorithm
    public int[] ShortestPath(int n, List<(int to, int weight)>[] graph, int start) {
        // Initialize distance array with maximum int value (infinity)
        int[] dist = Enumerable.Repeat(int.MaxValue, n).ToArray();
        dist[start] = 0; // Distance to start node is 0

        // Priority queue where each item is a tuple (node, distance), ordered by distance
        var pq = new PriorityQueue<(int node, int dist), int>();
        pq.Enqueue((start, 0), 0); // Enqueue start node with priority 0

        // Continue until all reachable nodes are processed
        while (pq.Count > 0) {
            var (node, d) = pq.Dequeue(); // Dequeue node with smallest known distance

            if (d > dist[node]) continue; // Skip if this path is not optimal (a shorter path was already found)

            // Check all neighbors of the current node
            foreach (var (neighbor, weight) in graph[node]) {
                int newDist = d + weight; // Compute new distance via current node
                if (newDist < dist[neighbor]) { // If this new path is shorter
                    dist[neighbor] = newDist;   // Update distance to neighbor
                    pq.Enqueue((neighbor, newDist), newDist); // Enqueue updated path
                }
            }
        }

        return dist; // Return final shortest distances
    }

    // Main method to test the Dijkstra algorithm
    public static void Main(string[] args) {
        int n = 5; // Number of nodes in the graph

        // Create an adjacency list to represent the graph
        var graph = new List<(int to, int weight)>[n];
        for (int i = 0; i < n; i++)
            graph[i] = new List<(int, int)>(); // Initialize each node's adjacency list

        // Add directed, weighted edges to the graph
        graph[0].Add((1, 10)); // Edge from 0 to 1 with weight 10
        graph[0].Add((2, 3));  // Edge from 0 to 2 with weight 3
        graph[1].Add((2, 1));  // Edge from 1 to 2 with weight 1
        graph[1].Add((3, 2));  // Edge from 1 to 3 with weight 2
        graph[2].Add((1, 4));  // Edge from 2 to 1 with weight 4
        graph[2].Add((3, 8));  // Edge from 2 to 3 with weight 8
        graph[2].Add((4, 2));  // Edge from 2 to 4 with weight 2
        graph[3].Add((4, 7));  // Edge from 3 to 4 with weight 7
        graph[4].Add((3, 9));  // Edge from 4 to 3 with weight 9

        int start = 0; // Start node for Dijkstra's algorithm
        Dijkstra dijkstra = new Dijkstra(); // Create an instance of the Dijkstra class

        // Compute shortest distances from start node to all other nodes
        int[] distances = dijkstra.ShortestPath(n, graph, start);

        // Print the shortest distance from the start node to each node
        Console.WriteLine($"Shortest distances from node {start}:");
        for (int i = 0; i < distances.Length; i++) {
            // Print "Unreachable" if the distance is still infinity
            Console.WriteLine($"To node {i}: {(distances[i] == int.MaxValue ? "Unreachable" : distances[i].ToString())}");
        }
    }
}
