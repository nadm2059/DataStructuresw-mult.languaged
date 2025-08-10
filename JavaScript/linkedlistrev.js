// 2. Reverse a Linked List

// Define the ListNode class to represent a node in the linked list
class ListNode {
    // Constructor takes a value for the node
    constructor(val) {
        this.val = val;    // Store the value in the node
        this.next = null;  // Initialize next pointer to null
    }
}

// Function to reverse a linked list iteratively
function reverseListIterative(head) {
    let prev = null;           // Previous node starts as null (end of reversed list)
    while (head) {             // While there are nodes to process
        let next = head.next;  // Store next node temporarily
        head.next = prev;      // Reverse current node's pointer to previous
        prev = head;           // Move prev pointer to current node
        head = next;           // Move to next node in the original list
    }
    return prev;               // prev is the new head of the reversed list
}

// Function to reverse a linked list recursively
function reverseListRecursive(head, prev = null) {
    if (!head) return prev;       // Base case: if head is null, return prev as new head
    let next = head.next;         // Store next node temporarily
    head.next = prev;             // Reverse current node's pointer to prev
    return reverseListRecursive(next, head);  // Recurse on next node, passing current as prev
}

// Helper function to print the linked list values
function printList(head) {
    let curr = head;          // Start from head
    let values = [];          // Array to hold node values
    while (curr) {            // Traverse until the end of the list
        values.push(curr.val); // Add current node's value to array
        curr = curr.next;      // Move to next node
    }
    console.log(values.join(" -> "));  // Print all values joined by arrows
}

// Main function to run the code and test reversals
function main() {
    // Create linked list: 1 -> 2 -> 3 -> 4 -> 5
    let head = new ListNode(1);             // Create first node with value 1
    head.next = new ListNode(2);            // Link second node with value 2
    head.next.next = new ListNode(3);       // Link third node with value 3
    head.next.next.next = new ListNode(4);  // Link fourth node with value 4
    head.next.next.next.next = new ListNode(5); // Link fifth node with value 5

    console.log("Original List:");          // Print label
    printList(head);                        // Print original list

    console.log("\nReversed Iteratively:");  // Print label for iterative reversal
    let reversedIter = reverseListIterative(head);  // Reverse list iteratively
    printList(reversedIter);                // Print reversed list

    console.log("\nReversed Recursively:");  // Print label for recursive reversal
    let reversedRec = reverseListRecursive(reversedIter); // Reverse back recursively
    printList(reversedRec);                 // Print restored original list
}

// Call main function to execute
main();
