import java.util.*;

// LRU Cache implementation extending LinkedHashMap
public class LRUCacheLinkedHashMap extends LinkedHashMap<Integer, Integer> {
    private int capacity;  // Maximum cache capacity

    // Constructor initializes cache with given capacity and accessOrder true for LRU ordering
    public LRUCacheLinkedHashMap(int capacity) {
        super(capacity, 0.75f, true);  // true enables access order
        this.capacity = capacity;
    }

    // Get value for key, return -1 if not found
    public int get(int key) {
        return super.getOrDefault(key, -1);
    }

    // Put key-value pair into cache
    public void put(int key, int value) {
        super.put(key, value);
    }

    // Automatically remove eldest entry if cache size exceeds capacity
    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer,Integer> eldest) {
        return size() > capacity;
    }

    // Main method to demonstrate LRUCache usage
    public static void main(String[] args) {
        // Create LRU Cache with capacity 3
        LRUCacheLinkedHashMap cache = new LRUCacheLinkedHashMap(3);

        // Add key-value pairs
        cache.put(1, 10);
        cache.put(2, 20);
        cache.put(3, 30);
        System.out.println("Cache after 3 inserts: " + cache);

        // Access some keys
        System.out.println("Get key 2: " + cache.get(2));  // Access key 2, should be 20
        System.out.println("Cache after accessing key 2: " + cache);

        // Add another key, should evict least recently used (key 1)
        cache.put(4, 40);
        System.out.println("Cache after adding key 4: " + cache);

        // Try to get evicted key
        System.out.println("Get key 1 (evicted): " + cache.get(1));  // Should return -1
    }
}
