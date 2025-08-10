using System;
using System.Collections.Generic;

public class GraphCycleDetector {
    private bool DFS(int node, bool[] visited, bool[] recursionStack, List<int>[] graph) {
        visited[node] = true;
        recursionStack[node] = true;

        foreach (var neighbor in graph[node]) {
            if (!visited[neighbor] && DFS(neighbor, visited, recursionStack, graph))
                return true;
            else if (recursionStack[neighbor])
                return true;
        }

        recursionStack[node] = false;
        return false;
    }

    public bool HasCycle(List<int>[] graph) {
        int n = graph.Length;
        bool[] visited = new bool[n];
        bool[] stack = new bool[n];

        for (int i = 0; i < n; i++)
            if (!visited[i] && DFS(i, visited, stack, graph))
                return true;
        return false;
    }

    public static void Main(string[] args) {
        // Create a directed graph with a cycle
        List<int>[] graphWithCycle = new List<int>[4];
        for (int i = 0; i < 4; i++)
            graphWithCycle[i] = new List<int>();

        graphWithCycle[0].Add(1);
        graphWithCycle[1].Add(2);
        graphWithCycle[2].Add(3);
        graphWithCycle[3].Add(1); // Cycle: 1 -> 2 -> 3 -> 1

        GraphCycleDetector detector = new GraphCycleDetector();
        Console.WriteLine("Graph with cycle: " + detector.HasCycle(graphWithCycle));  // True

        // Create a directed graph without a cycle
        List<int>[] graphWithoutCycle = new List<int>[4];
        for (int i = 0; i < 4; i++)
            graphWithoutCycle[i] = new List<int>();

        graphWithoutCycle[0].Add(1);
        graphWithoutCycle[1].Add(2);
        graphWithoutCycle[2].Add(3);
        // No cycle here

        Console.WriteLine("Graph without cycle: " + detector.HasCycle(graphWithoutCycle));  // False
    }
}
