class UnionFind:
    def __init__(self, n):
        self.parent = list(range(n))
        self.edges = []  # Store edges for later cycle detection

    def find(self, x):
        if self.parent[x] != x:
            self.parent[x] = self.find(self.parent[x])
        return self.parent[x]

    def union(self, x, y):
        rootX = self.find(x)
        rootY = self.find(y)
        if rootX == rootY:
            return True  # Cycle detected
        self.parent[rootY] = rootX
        return False
    def add_edge(self, u, v):
        self.edges.append((u, v))

    def has_cycle(self):
        for u, v in self.edges:
            if self.union(u, v):
                return True
        return False



def main():
    print("Cycle Detection Demo")
    g = UnionFind(4)
    g.add_edge(0, 1)
    g.add_edge(1, 2)
    g.add_edge(2, 0)  # This creates a cycle

    print("Graph contains cycle?" , g.has_cycle())  # Should return True

if __name__ == "__main__":
    main()
