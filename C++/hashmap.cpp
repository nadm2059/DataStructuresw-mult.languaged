#include <iostream>
#include <vector>
using namespace std;

class MyHashMap {
private:
    static const int SIZE = 1000;  // Define fixed size for the hash table
    vector<pair<int, int>> table[SIZE];  // Array of buckets (each bucket is a vector of key-value pairs)

    int hash(int key) {
        return key % SIZE;  // Simple hash function using modulo
    }

public:
    void put(int key, int value) {
        int idx = hash(key);  // Find index using hash
        for (auto &pair : table[idx]) {
            if (pair.first == key) {  // If key exists, update value
                pair.second = value;
                return;
            }
        }
        table[idx].push_back({key, value});  // Otherwise, insert new key-value pair
    }

    int get(int key) {
        int idx = hash(key);  // Find index using hash
        for (auto &pair : table[idx]) {
            if (pair.first == key) return pair.second;  // Return value if found
        }
        return -1;  // Key not found
    }

    void remove(int key) {
        int idx = hash(key);  // Find index
        for (auto it = table[idx].begin(); it != table[idx].end(); ++it) {
            if (it->first == key) {
                table[idx].erase(it);  // Erase key-value pair
                return;
            }
        }
    }
};
int main() {
    MyHashMap map;
    map.put(1, 10);
    map.put(2, 20);
    cout << "Get key 1: " << map.get(1) << endl; // 10
    map.put(1, 30);
    cout << "Get key 1 after update: " << map.get(1) << endl; // 30
    map.remove(1);
    cout << "Get key 1 after removal: " << map.get(1) << endl; // -1
    return 0;
}