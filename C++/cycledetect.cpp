#include <iostream>
#include <vector>
using namespace std;

class UnionFind {
private:
    vector<int> parent;  // Stores parent for each node

public:
    UnionFind(int n) : parent(n) {
        for (int i = 0; i < n; ++i) parent[i] = i;  // Each node is its own parent
    }

    int find(int x) {
        if (parent[x] != x)  // Path compression
            parent[x] = find(parent[x]);
        return parent[x];
    }

    bool unionSet(int x, int y) {
        int xr = find(x);  // Find root of x
        int yr = find(y);  // Find root of y
        if (xr == yr) return true;  // If same root, cycle exists
        parent[yr] = xr;  // Union the sets
        return false;
    }
};
int main() {
    int n = 5;
    UnionFind uf(n);

    vector<pair<int, int>> edges = {
        {0, 1}, {1, 2}, {2, 3}, {3, 4}, {2, 4}
    };

    for (auto& [u, v] : edges) {
        if (uf.unionSet(u, v)) {
            cout << "Cycle detected between " << u << " and " << v << endl;
        }
    }

    return 0;
}
