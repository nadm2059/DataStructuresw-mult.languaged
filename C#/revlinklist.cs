

using System;

public class ListNode {
    public int val;
    public ListNode next;
    public ListNode(int x) { val = x; }
}

public class LinkedListProblems {
    // Iterative
    public ListNode ReverseIterative(ListNode head) {
        ListNode prev = null, curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    // Recursive
    public ListNode ReverseRecursive(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode newHead = ReverseRecursive(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    // Helper to print linked list
    public void PrintList(ListNode head) {
        while (head != null) {
            Console.Write(head.val + " ");
            head = head.next;
        }
        Console.WriteLine();
    }

    // Main method
    public static void Main(string[] args) {
        // Create sample linked list: 1 -> 2 -> 3 -> 4 -> 5
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        LinkedListProblems llp = new LinkedListProblems();

        Console.WriteLine("Original List:");
        llp.PrintList(head);

        // Reverse using iterative method
        ListNode reversedIterative = llp.ReverseIterative(head);
        Console.WriteLine("Reversed Iterative:");
        llp.PrintList(reversedIterative);

        // Reverse back using recursive method
        ListNode reversedRecursive = llp.ReverseRecursive(reversedIterative);
        Console.WriteLine("Reversed Recursive (back to original):");
        llp.PrintList(reversedRecursive);
    }
}
