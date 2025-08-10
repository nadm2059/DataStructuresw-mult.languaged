
class ListNode {
    int val;        // Value stored in node
    ListNode next;  // Reference to the next node

    // Constructor to initialize node with a value
    ListNode(int val) {
        this.val = val;
    }
}

public class LinkedListProblems {

    // Iterative method to reverse a linked list
    public static ListNode reverseListIterative(ListNode head) {
        ListNode prev = null;    // Previous node, initially null
        ListNode curr = head;    // Current node starts at head

        // Traverse list until current is null (end)
        while (curr != null) {
            ListNode nextTemp = curr.next; // Temporarily store next node
            curr.next = prev;               // Reverse current node's pointer
            prev = curr;                   // Move prev forward
            curr = nextTemp;               // Move current forward
        }
        return prev;                      // Prev is new head after reversal
    }

    // Recursive method to reverse a linked list
    public static ListNode reverseListRecursive(ListNode head) {
        // Base case: empty list or single node list
        if (head == null || head.next == null) return head;

        // Recursively reverse the rest of the list
        ListNode p = reverseListRecursive(head.next);

        // Adjust pointers to reverse current node
        head.next.next = head;
        head.next = null;

        return p; // Return new head of reversed list
    }

    // Main method to test linked list reversal
    public static void main(String[] args) {
        // Create linked list 1 -> 2 -> 3 -> 4 -> 5
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        System.out.print("Original List: ");
        printList(head);

        // Reverse iteratively
        ListNode reversedIterative = reverseListIterative(head);
        System.out.print("Reversed Iterative: ");
        printList(reversedIterative);

        // Reverse recursively to restore original
        ListNode reversedRecursive = reverseListRecursive(reversedIterative);
        System.out.print("Reversed Recursive: ");
        printList(reversedRecursive);
    }

    // Helper method to print linked list nodes
    private static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println();
    }
}
 
