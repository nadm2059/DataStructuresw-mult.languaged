#include <iostream>
#include <unordered_map>
using namespace std;

struct Node {
    int key, value;
    Node *prev, *next;
    Node(int k, int v): key(k), value(v), prev(nullptr), next(nullptr) {}
};

class LRUCache {
private:
    int cap;
    unordered_map<int, Node*> map;  // Key -> node map
    Node *head, *tail;  // Dummy head and tail

    void remove(Node* node) {
        node->prev->next = node->next;  // Bypass node
        node->next->prev = node->prev;
    }

    void insert(Node* node) {
        node->next = head->next;  // Insert right after head
        node->prev = head;
        head->next->prev = node;
        head->next = node;
    }

public:
    LRUCache(int capacity): cap(capacity) {
        head = new Node(0, 0);  // Create dummy head
        tail = new Node(0, 0);  // Create dummy tail
        head->next = tail;
        tail->prev = head;
    }

    int get(int key) {
        if (map.find(key) == map.end()) return -1;  // Not found
        Node* node = map[key];  // Get node
        remove(node);  // Move to front
        insert(node);
        return node->value;
    }

    void put(int key, int value) {
        if (map.find(key) != map.end()) {
            remove(map[key]);  // Remove old
            delete map[key];
        }
        if (map.size() == cap) {
            Node* lru = tail->prev;  // Least recently used
            remove(lru);  // Remove from list
            map.erase(lru->key);  // Remove from map
            delete lru;
        }
        Node* node = new Node(key, value);  // Create new node
        insert(node);  // Add to front
        map[key] = node;  // Add to map
    }
};
int main() {
    LRUCache cache(2);
    cache.put(1, 1);
    cache.put(2, 2);
    cout << "Get 1: " << cache.get(1) << endl; // 1
    cache.put(3, 3);  // Evicts key 2
    cout << "Get 2: " << cache.get(2) << endl; // -1
    cache.put(4, 4);  // Evicts key 1
    cout << "Get 1: " << cache.get(1) << endl; // -1
    cout << "Get 3: " << cache.get(3) << endl; // 3
    cout << "Get 4: " << cache.get(4) << endl; // 4
    return 0;
}
