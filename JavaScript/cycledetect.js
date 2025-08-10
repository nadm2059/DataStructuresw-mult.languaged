// 3. Detect Cycle in Graph using DFS
function hasCycle(graph) {
    // Set to keep track of all visited nodes globally
    const visited = new Set();
    // Set to keep track of nodes in the current recursion stack (DFS path)
    const recStack = new Set();

    // Helper function to perform DFS from the given node
    function dfs(node) {
        // If node is already in recursion stack, cycle detected
        if (recStack.has(node)) return true;
        // If node was visited before and no cycle found, skip
        if (visited.has(node)) return false;

        // Mark current node as visited
        visited.add(node);
        // Add node to recursion stack
        recStack.add(node);

        // Explore all neighbors of the current node
        for (let neighbor of graph[node]) {
            // If any neighbor leads to a cycle, return true
            if (dfs(neighbor)) return true;
        }

        // Remove node from recursion stack before returning
        recStack.delete(node);
        // No cycle detected from this node
        return false;
    }

    // Check each node in the graph
    for (let node in graph) {
        // If DFS from any node detects a cycle, return true
        if (dfs(node)) return true;
    }
    // If no cycles found in any DFS traversal, return false
    return false;
}

// Main function to test hasCycle
function main() {
    // Graph with a cycle: A -> B -> C -> A
    const graphWithCycle = {
        A: ['B'],
        B: ['C'],
        C: ['A'],  // Cycle here
        D: ['E'],
        E: []
    };

    // Graph without a cycle
    const graphWithoutCycle = {
        A: ['B', 'C'],
        B: ['D'],
        C: [],
        D: []
    };

    // Test and print whether cycle exists in graphWithCycle
    console.log("Graph with cycle:", hasCycle(graphWithCycle));       // true
    // Test and print whether cycle exists in graphWithoutCycle
    console.log("Graph without cycle:", hasCycle(graphWithoutCycle)); // false
}

// Run the main function
main();
