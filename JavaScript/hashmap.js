// JavaScript: HashMap class
class MyHashMap {
    constructor() {
        // Set the size of the bucket array
        this.size = 1000;
        // Create an array of empty arrays (buckets) to store key-value pairs
        this.buckets = Array(this.size).fill(null).map(() => []);
    }

    // Hash function: returns an index for a given key
    hash(key) {
        return key % this.size;
    }

    // Inserts or updates a key-value pair
    put(key, value) {
        // Get the correct bucket based on the hash of the key
        let bucket = this.buckets[this.hash(key)];

        // Loop through the bucket to see if the key already exists
        for (let pair of bucket) {
            if (pair[0] === key) {
                // If key exists, update the value
                pair[1] = value;
                return;
            }
        }

        // If key doesn't exist, push the new key-value pair into the bucket
        bucket.push([key, value]);
    }

    // Retrieves the value for a given key
    get(key) {
        // Get the correct bucket based on the hash of the key
        let bucket = this.buckets[this.hash(key)];

        // Search the bucket for the key
        for (let pair of bucket) {
            if (pair[0] === key) {
                // Return the value if key is found
                return pair[1];
            }
        }

        // If key is not found, return -1
        return -1;
    }

    // Removes the key-value pair for a given key
    remove(key) {
        // Get the correct bucket based on the hash of the key
        let bucket = this.buckets[this.hash(key)];

        // Filter out the pair with the matching key
        this.buckets[this.hash(key)] = bucket.filter(pair => pair[0] !== key);
    }
}

// ---------------- Main ----------------

// Create a new instance of MyHashMap
const map = new MyHashMap();

// Insert key-value pair (1, 10)
map.put(1, 10);

// Insert key-value pair (2, 20)
map.put(2, 20);

// Insert key-value pair (102, 999)
// This may collide with key 2 if the hash function results in the same index
map.put(102, 999);

// Retrieve value for key 1 and print it (should be 10)
console.log(map.get(1));     // Output: 10

// Retrieve value for key 2 and print it (should be 20)
console.log(map.get(2));     // Output: 20

// Retrieve value for key 102 and print it (should be 999)
console.log(map.get(102));   // Output: 999

// Remove key 2 from the map
map.remove(2);

// Try to retrieve value for key 2 again (should be -1 since it was removed)
console.log(map.get(2));     // Output: -1
