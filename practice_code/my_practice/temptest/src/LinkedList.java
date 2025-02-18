

public class LinkedList {
    class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }
    private Node head;

    // Method to add a new node at the end of the list
    public void add(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
    }

    // Method to remove a node by value
    public boolean remove(int data) {
        if (head == null) return false;

        if (head.data == data) {
            head = head.next;
            return true;
        }

        Node current = head;
        while (current.next != null) {
            if (current.next.data == data) {
                current.next = current.next.next;
                return true;
            }
            current = current.next;
        }
        return false;
    }

    // Method to get the index of a node by value
    public int getIndex(int data) {
        Node current = head;
        int index = 0;
        while (current != null) {
            if (current.data == data) {
                return index;
            }
            current = current.next;
            index++;
        }
        return -1; // Return -1 if the value is not found
    }

    // Method to sort the linked list
    public void sort() {
        if (head == null || head.next == null) return;

        // Convert linked list to array for sorting
        int size = getSize();
        int[] arr = new int[size];
        Node current = head;
        for (int i = 0; i < size; i++) {
            arr[i] = current.data;
            current = current.next;
        }

        // Sort the array
        java.util.Arrays.sort(arr);

        // Convert sorted array back to linked list
        current = head;
        for (int i = 0; i < size; i++) {
            current.data = arr[i];
            current = current.next;
        }
    }

    // Helper method to get the size of the linked list
    private int getSize() {
        Node current = head;
        int size = 0;
        while (current != null) {
            size++;
            current = current.next;
        }
        return size;
    }

    // Method to print the linked list (for testing purposes)
    public void printList() {
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }
}