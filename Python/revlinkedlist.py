class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next

# Iterative
def reverse_iterative(head):
    prev = None
    while head:
        nxt = head.next
        head.next = prev
        prev = head
        head = nxt
    return prev

# Recursive
def reverse_recursive(head):
    if not head or not head.next:
        return head
    new_head = reverse_recursive(head.next)
    head.next.next = head
    head.next = None
    return new_head
def build_linked_list(values):
    head = None
    for val in reversed(values):
        node = ListNode(val)
        node.next = head
        head = node
    return head

def print_list(head):
    while head:
        print(head.val, end=" -> ")
        head = head.next
    print("None")

def main():
    print("Linked List Reverse Demo")
    head = build_linked_list([1, 2, 3, 4, 5])
    print("Original List:")
    print_list(head)

    reversed_head = reverse_iterative(head)
    print("Reversed List (Iterative):")
    print_list(reversed_head)

    # Reverse again using recursive
    reversed_head = reverse_recursive(reversed_head)
    print("Reversed List (Recursive):")
    print_list(reversed_head)

if __name__ == "__main__":
    main()