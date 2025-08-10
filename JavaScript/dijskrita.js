// Dijkstra's algorithm implementation to find the shortest paths from a start node to all other nodes
function dijkstra(graph, start) {
    const dist = {};  // Object to store the shortest distances from start to each node
    const pq = [[0, start]];  // Priority queue initialized with the start node and distance 0

    // Initialize distances to all nodes as Infinity (unknown)
    for (let node in graph) {
        dist[node] = Infinity;
    }

    dist[start] = 0;  // Distance to the start node is always 0

    // Continue processing while there are nodes in the priority queue
    while (pq.length > 0) {
        // Sort the queue so that the node with the smallest distance comes first
        pq.sort((a, b) => a[0] - b[0]);

        // Remove the node with the smallest distance
        let [d, node] = pq.shift();

        // Loop through all neighbors of the current node
        for (let [neighbor, weight] of graph[node]) {
            let alt = d + weight;  // Calculate new distance to neighbor through current node
            if (alt < dist[neighbor]) {  // If the new distance is shorter than the current known distance
                dist[neighbor] = alt;  // Update the shortest distance to this neighbor
                pq.push([alt, neighbor]);  // Add neighbor to the queue to explore its neighbors later
            }
        }
    }

    return dist;  // Return the object containing the shortest distances to each node
}

// Main function to demonstrate Dijkstra’s algorithm
function mainDijkstra() {
    // Define a weighted graph using an adjacency list
    // Each node maps to a list of [neighbor, weight] pairs
    const graph = {
        A: [['B', 1], ['C', 4]],  // Node A connects to B (weight 1) and C (weight 4)
        B: [['C', 2], ['D', 5]],  // Node B connects to C (weight 2) and D (weight 5)
        C: [['D', 1]],            // Node C connects to D (weight 1)
        D: []                     // Node D has no outgoing edges
    };

    // Run Dijkstra's algorithm from node 'A'
    const distances = dijkstra(graph, 'A');

    // Print the shortest distances from A to all other nodes
    console.log("Shortest distances from A:", distances);
}

// Call the main function to execute the program
mainDijkstra();
