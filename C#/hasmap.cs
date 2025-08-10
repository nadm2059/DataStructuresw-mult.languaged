using System;
using System.Collections.Generic;

public class MyHashMap {
    private const int SIZE = 1000;
    private List<(int key, int value)>[] map;

    public MyHashMap() {
        map = new List<(int, int)>[SIZE];
        for (int i = 0; i < SIZE; i++)
            map[i] = new List<(int, int)>();
    }

    private int Hash(int key) => key % SIZE;

    public void Put(int key, int value) {
        int index = Hash(key);
        for (int i = 0; i < map[index].Count; i++) {
            if (map[index][i].key == key) {
                map[index][i] = (key, value);
                return;
            }
        }
        map[index].Add((key, value));
    }

    public int Get(int key) {
        int index = Hash(key);
        foreach (var pair in map[index]) {
            if (pair.key == key) return pair.value;
        }
        return -1;
    }

    public void Remove(int key) {
        int index = Hash(key);
        map[index].RemoveAll(p => p.key == key);
    }

    // Main method to test MyHashMap
    public static void Main(string[] args) {
        MyHashMap hashMap = new MyHashMap();

        Console.WriteLine("Put(1, 10)");
        hashMap.Put(1, 10);

        Console.WriteLine("Put(2, 20)");
        hashMap.Put(2, 20);

        Console.WriteLine("Get(1) = " + hashMap.Get(1)); // Should print 10
        Console.WriteLine("Get(3) = " + hashMap.Get(3)); // Should print -1

        Console.WriteLine("Put(2, 30)"); // Update value
        hashMap.Put(2, 30);
        Console.WriteLine("Get(2) = " + hashMap.Get(2)); // Should print 30

        Console.WriteLine("Remove(2)");
        hashMap.Remove(2);
        Console.WriteLine("Get(2) = " + hashMap.Get(2)); // Should print -1
    }
}
