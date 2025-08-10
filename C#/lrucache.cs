using System;
using System.Collections.Generic;

public class LRUCache {
    class Node {
        public int key, value;
        public Node prev, next;
        public Node(int k, int v) { key = k; value = v; }
    }

    private int capacity;
    private Dictionary<int, Node> map;
    private Node head, tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new Dictionary<int, Node>();
        head = new Node(0, 0);
        tail = new Node(0, 0);
        head.next = tail;
        tail.prev = head;
    }

    private void Remove(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void Add(Node node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }

    public int Get(int key) {
        if (!map.ContainsKey(key)) return -1;
        var node = map[key];
        Remove(node);
        Add(node);
        return node.value;
    }

    public void Put(int key, int value) {
        if (map.ContainsKey(key)) {
            Remove(map[key]);
        } else if (map.Count >= capacity) {
            map.Remove(tail.prev.key);
            Remove(tail.prev);
        }
        Node node = new Node(key, value);
        Add(node);
        map[key] = node;
    }

    public static void Main(string[] args) {
        LRUCache cache = new LRUCache(2);

        Console.WriteLine("Put(1, 1)");
        cache.Put(1, 1); // cache = {1=1}
        Console.WriteLine("Put(2, 2)");
        cache.Put(2, 2); // cache = {1=1, 2=2}
        Console.WriteLine("Get(1) = " + cache.Get(1)); // returns 1, cache = {2=2, 1=1}
        Console.WriteLine("Put(3, 3)");
        cache.Put(3, 3); // LRU key 2 evicted, cache = {1=1, 3=3}
        Console.WriteLine("Get(2) = " + cache.Get(2)); // returns -1 (not found)
        Console.WriteLine("Put(4, 4)");
        cache.Put(4, 4); // LRU key 1 evicted, cache = {3=3, 4=4}
        Console.WriteLine("Get(1) = " + cache.Get(1)); // returns -1 (not found)
        Console.WriteLine("Get(3) = " + cache.Get(3)); // returns 3
        Console.WriteLine("Get(4) = " + cache.Get(4)); // returns 4
    }
}
