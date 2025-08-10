#include <iostream>             // Include input-output stream for printing
#include <vector>               // Include vector container for graph representation
#include <queue>                // Include priority_queue for Dijkstra's algorithm
using namespace std;            // Use standard namespace to avoid std:: prefix

typedef pair<int, int> pii;     // Define shorthand for a pair of integers (distance, vertex)

// Dijkstra’s algorithm to find shortest distances from a start vertex
vector<int> dijkstra(int V, vector<vector<pii>>& adj, int start) {
    vector<int> dist(V, INT_MAX);  // Create distance array, initialized to "infinity"
    priority_queue<pii, vector<pii>, greater<pii>> pq;  // Min-heap to get the vertex with the smallest distance

    dist[start] = 0;               // Set distance to starting node as 0
    pq.push({0, start});           // Push starting node into priority queue

    while (!pq.empty()) {          // Repeat while there are nodes to process
        auto [d, u] = pq.top();    // Extract node with smallest distance
        pq.pop();                  // Remove it from the queue

        if (d > dist[u]) continue; // Skip if we already found a better path to `u`

        // Traverse all neighbors of node `u`
        for (auto [v, w] : adj[u]) {       // For each neighbor `v` and weight `w`
            if (dist[v] > d + w) {         // If new path to `v` is shorter than current
                dist[v] = d + w;           // Update distance to neighbor
                pq.push({dist[v], v});     // Push neighbor into priority queue with new distance
            }
        }
    }
    return dist;  // Return the array of shortest distances
}

int main() {
    int V = 5;  // Total number of vertices in the graph

    // Adjacency list representation of the graph
    vector<vector<pii>> adj(V);  // Create vector of vectors of (neighbor, weight) pairs

    // Define edges: adj[u] = { (v, weight), ... }
    adj[0] = {{1, 10}, {2, 3}};     // Node 0 connects to 1 (10) and 2 (3)
    adj[1] = {{2, 1}, {3, 2}};      // Node 1 connects to 2 (1) and 3 (2)
    adj[2] = {{1, 4}, {3, 8}, {4, 2}}; // Node 2 connects to 1 (4), 3 (8), 4 (2)
    adj[3] = {{4, 7}};              // Node 3 connects to 4 (7)
    adj[4] = {{3, 9}};              // Node 4 connects to 3 (9)

    // Compute shortest paths from source node 0
    vector<int> dist = dijkstra(V, adj, 0);

    // Output the shortest distance to each node
    for (int i = 0; i < V; ++i) {
        cout << "Distance to " << i << ": " << dist[i] << endl;
    }

    return 0;  // End of program
}
