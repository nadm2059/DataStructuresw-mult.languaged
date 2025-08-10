#include <iostream>
using namespace std;
// Define the node structure
struct ListNode {
    int val;
    ListNode* next;
    ListNode(int x) : val(x), next(nullptr) {}  // Constructor
};

// Iterative reverse
ListNode* reverseListIterative(ListNode* head) {
    ListNode* prev = nullptr;  // Previous node starts as null
    while (head) {
        ListNode* nextTemp = head->next;  // Store next node
        head->next = prev;  // Reverse pointer
        prev = head;        // Move prev forward
        head = nextTemp;    // Move head forward
    }
    return prev;  // New head of reversed list
}

// Recursive reverse
ListNode* reverseListRecursive(ListNode* head) {
    if (!head || !head->next) return head;  // Base case: empty or last node
    ListNode* newHead = reverseListRecursive(head->next);  // Recurse
    head->next->next = head;  // Reverse pointer
    head->next = nullptr;     // Cut the old link
    return newHead;           // Return new head
}
void printList(ListNode* head) {
    while (head) {
        cout << head->val << " ";
        head = head->next;
    }
    cout << endl;
}

int main() {
    ListNode* head = new ListNode(1);
    head->next = new ListNode(2);
    head->next->next = new ListNode(3);

    cout << "Original: ";
    printList(head);

    head = reverseListIterative(head);
    cout << "Reversed Iterative: ";
    printList(head);

    head = reverseListRecursive(head);
    cout << "Reversed Recursive: ";
    printList(head);

    return 0;
}