// 3. Detect Cycle in Graph using DFS in TypeScript
type Graph = { [key: string]: string[] };

function hasCycle_(graph: Graph): boolean {
    // Set to keep track of all visited nodes globally
    const visited: Set<string> = new Set();
    // Set to keep track of nodes in the current recursion stack (DFS path)
    const recStack: Set<string> = new Set();

    // Helper function to perform DFS from the given node
    function dfs(node: string): boolean {
        // If node is already in recursion stack, cycle detected
        if (recStack.has(node)) return true;
        // If node was visited before and no cycle found, skip
        if (visited.has(node)) return false;

        // Mark current node as visited
        visited.add(node);
        // Add node to recursion stack
        recStack.add(node);

        // Explore all neighbors of the current node
        for (const neighbor of graph[node] || []) {
            // If any neighbor leads to a cycle, return true
            if (dfs(neighbor)) return true;
        }

        // Remove node from recursion stack before returning
        recStack.delete(node);
        // No cycle detected from this node
        return false;
    }

    // Check each node in the graph
    for (const node in graph) {
        if (dfs(node)) return true;
    }
    // If no cycles found in any DFS traversal, return false
    return false;
}

// Main function to test hasCycle
function main_(): void {
    // Graph with a cycle: A -> B -> C -> A
    const graphWithCycle: Graph = {
        A: ['B'],
        B: ['C'],
        C: ['A'],  // Cycle here
        D: ['E'],
        E: []
    };

    // Graph without a cycle
    const graphWithoutCycle: Graph = {
        A: ['B', 'C'],
        B: ['D'],
        C: [],
        D: []
    };

    console.log("Graph with cycle:", hasCycle_(graphWithCycle));       // true
    console.log("Graph without cycle:", hasCycle_(graphWithoutCycle)); // false
}

// Run the main function
main_();
