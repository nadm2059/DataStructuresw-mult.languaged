import java.util.*;

public class LRUCacheDLL {
    // Doubly linked list node class
    private class Node {
        int key, value;
        Node prev, next;
        Node(int k, int v) { key = k; value = v; }
    }

    private Map<Integer, Node> map;  // HashMap for O(1) access
    private int capacity;            // Max capacity of cache
    private Node head, tail;         // Dummy head and tail nodes for DLL

    // Constructor initializes capacity, map, and dummy head/tail
    public LRUCacheDLL(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        head = new Node(0,0);
        tail = new Node(0,0);
        head.next = tail;
        tail.prev = head;
    }

    // Get value by key and mark node as most recently used
    public int get(int key) {
        if (!map.containsKey(key)) return -1;    // Return -1 if not found
        Node node = map.get(key);
        remove(node);                            // Remove from current position
        insertToHead(node);                      // Insert at head (most recently used)
        return node.value;
    }

    // Put key-value into cache, updating existing or adding new
    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.value = value;                 // Update value
            remove(node);                       // Remove old position
            insertToHead(node);                 // Insert at head
        } else {
            if (map.size() == capacity) {      // If capacity reached
                Node toRemove = tail.prev;     // Remove least recently used node (tail.prev)
                remove(toRemove);
                map.remove(toRemove.key);
            }
            Node newNode = new Node(key, value);
            insertToHead(newNode);
            map.put(key, newNode);
        }
    }

    // Remove node from DLL
    private void remove(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    // Insert node right after head (mark as most recently used)
    private void insertToHead(Node node) {
        node.next = head.next;
        node.next.prev = node;
        head.next = node;
        node.prev = head;
    }

    // Main method to test LRUCacheDLL functionality
    public static void main(String[] args) {
        LRUCacheDLL cache = new LRUCacheDLL(3);  // Create cache with capacity 3

        cache.put(1, 100);  // Add key=1, value=100
        cache.put(2, 200);  // Add key=2, value=200
        cache.put(3, 300);  // Add key=3, value=300
        System.out.println("Get key 2: " + cache.get(2)); // Access key 2 (should print 200)
        cache.put(4, 400);  // Add key=4, value=400, should evict key=1 (LRU)
        System.out.println("Get key 1 (evicted): " + cache.get(1)); // Should print -1 (not found)
        System.out.println("Get key 3: " + cache.get(3)); // Should print 300
        System.out.println("Get key 4: " + cache.get(4)); // Should print 400
    }
}

