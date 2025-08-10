// Java class to implement a simple HashMap without using built-in maps
class MyHashMapJava {

    // Inner class representing a node in the linked list for each bucket
    private static class Node {
        int key, value; // key and value pair
        Node next;      // Pointer to the next node (for handling collisions)

        // Constructor to initialize key-value pair
        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private Node[] buckets; // Array of linked lists to hold the key-value pairs
    private int size = 1000; // Default size of the buckets array

    // Constructor to initialize the HashMap with a fixed number of buckets
    public MyHashMapJava() {
        buckets = new Node[size];
    }

    // Hash function to compute index for a given key
    private int hash(int key) {
        return key % size;
    }

    // Method to insert or update a key-value pair
    public void put(int key, int value) {
        int idx = hash(key); // Get the index using hash
        if (buckets[idx] == null) {
            // If no node at this index, place the new node directly
            buckets[idx] = new Node(key, value);
        } else {
            // Traverse linked list at the index to check if key already exists
            Node curr = buckets[idx];
            while (true) {
                if (curr.key == key) {
                    // Key found, update its value
                    curr.value = value;
                    return;
                }
                if (curr.next == null) break; // End of list reached
                curr = curr.next;
            }
            // Key not found, append new node to the end
            curr.next = new Node(key, value);
        }
    }

    // Method to retrieve value associated with a key
    public int get(int key) {
        int idx = hash(key); // Get index from hash function
        Node curr = buckets[idx]; // Start from the first node in the bucket
        while (curr != null) {
            if (curr.key == key) return curr.value; // Key found, return value
            curr = curr.next; // Move to next node
        }
        return -1; // Key not found
    }

    // Method to remove a key-value pair by key
    public void remove(int key) {
        int idx = hash(key); // Get index for the key
        Node curr = buckets[idx], prev = null; // Pointers for traversal

        while (curr != null) {
            if (curr.key == key) {
                // If the node to remove is the first node in the list
                if (prev == null) {
                    buckets[idx] = curr.next;
                } else {
                    // Bypass the node to remove it
                    prev.next = curr.next;
                }
                return; // Key removed
            }
            prev = curr;       // Move previous pointer
            curr = curr.next;  // Move current pointer
        }
    }

    // Main method to test the functionality of MyHashMapJava
    public static void main(String[] args) {
        MyHashMapJava map = new MyHashMapJava(); // Create instance of MyHashMapJava

        // Insert key-value pairs into the map
        map.put(1, 10);
        map.put(2, 20);
        map.put(1001, 30); // Collision: 1 % 1000 == 1001 % 1000

        // Retrieve values for existing and non-existing keys
        System.out.println("Get key 1: " + map.get(1));       // Output: 10
        System.out.println("Get key 2: " + map.get(2));       // Output: 20
        System.out.println("Get key 1001: " + map.get(1001)); // Output: 30
        System.out.println("Get key 3 (not found): " + map.get(3)); // Output: -1

        // Update the value for an existing key
        map.put(1, 15);
        System.out.println("Updated key 1: " + map.get(1));   // Output: 15

        // Remove a key and check if it's gone
        map.remove(2);
        System.out.println("After removing key 2: " + map.get(2)); // Output: -1
    }
}
