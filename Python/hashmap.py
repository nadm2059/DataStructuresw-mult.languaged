# Define the HashMap class
class HashMap:
    # Constructor method to initialize the hash map
    def __init__(self):
        self.size = 1000  # Set the size of the hash table to 1000
        self.table = [[] for _ in range(self.size)]  # Create an array of empty lists (buckets) for chaining

    # Private method to compute hash index for a given key
    def _hash(self, key):
        return key % self.size  # Use modulo operation to find the bucket index

    # Method to insert or update a key-value pair in the hash map
    def put(self, key, value):
        idx = self._hash(key)  # Get the bucket index for the key
        for pair in self.table[idx]:  # Iterate through the bucket
            if pair[0] == key:  # If key already exists
                pair[1] = value  # Update the value
                return  # Exit after updating
        self.table[idx].append([key, value])  # If key not found, append new key-value pair to the bucket

    # Method to retrieve the value for a given key
    def get(self, key):
        idx = self._hash(key)  # Get the bucket index for the key
        for pair in self.table[idx]:  # Iterate through the bucket
            if pair[0] == key:  # If key matches
                return pair[1]  # Return the value
        return -1  # If key not found, return -1

    # Method to remove a key-value pair from the hash map
    def remove(self, key):
        idx = self._hash(key)  # Get the bucket index for the key
        for i, pair in enumerate(self.table[idx]):  # Iterate with index through the bucket
            if pair[0] == key:  # If key matches
                self.table[idx].pop(i)  # Remove the pair from the bucket
                return  # Exit after removal


# Define the main function to demonstrate the HashMap usage
def main():
    print("HashMap Demo")  # Print title
    hm = HashMap()  # Create a new HashMap instance
    hm.put("apple", 5)  # Insert key 'apple' with value 5
    hm.put("banana", 7)  # Insert key 'banana' with value 7
    print("Get apple:", hm.get("apple"))       # Should print 5
    print("Get banana:", hm.get("banana"))     # Should print 7
    print("Get orange:", hm.get("orange"))     # Should print -1 (not found)
    hm.remove("apple")  # Remove key 'apple' from the map
    print("Get apple after removal:", hm.get("apple"))  # Should print -1 (after removal)

# Execute the main function if this script is run directly
if __name__ == "__main__":
    main()
