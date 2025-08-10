// 7. LRU Cache using Doubly Linked List + Map
class LRUNode {
    constructor(key, value) {
        this.key = key;
        this.value = value;
        this.prev = this.next = null;
    }
}

class LRUCache {
    constructor(capacity) {
        this.capacity = capacity;
        this.map = new Map();
        this.head = new LRUNode(0, 0);
        this.tail = new LRUNode(0, 0);
        this.head.next = this.tail;
        this.tail.prev = this.head;
    }

    _remove(node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    _add(node) {
        node.next = this.head.next;
        node.prev = this.head;
        this.head.next.prev = node;
        this.head.next = node;
    }

    get(key) {
        if (this.map.has(key)) {
            let node = this.map.get(key);
            this._remove(node);
            this._add(node);
            return node.value;
        }
        return -1;
    }

    put(key, value) {
        if (this.map.has(key)) this._remove(this.map.get(key));
        let node = new LRUNode(key, value);
        this._add(node);
        this.map.set(key, node);
        if (this.map.size > this.capacity) {
            let lru = this.tail.prev;
            this._remove(lru);
            this.map.delete(lru.key);
        }
    }
}
function mainLRU() {
    // Create LRU cache with capacity 2
    const cache = new LRUCache(2);

    cache.put(1, 1);  // Cache: {1=1}
    cache.put(2, 2);  // Cache: {1=1, 2=2}
    console.log(cache.get(1)); // returns 1; Cache order updated to {2=2, 1=1}

    cache.put(3, 3);  // Evicts key 2; Cache: {1=1, 3=3}
    console.log(cache.get(2)); // returns -1 (not found)

    cache.put(4, 4);  // Evicts key 1; Cache: {3=3, 4=4}
    console.log(cache.get(1)); // returns -1 (not found)
    console.log(cache.get(3)); // returns 3
    console.log(cache.get(4)); // returns 4
}

mainLRU();
