public class LinkedListtest {
    // Main method for testing
    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        list.add(3);
        list.add(1);
        list.add(4);
        list.add(2);

        System.out.println("Original List:");
        list.printList();

        list.sort();
        System.out.println("Sorted List:");
        list.printList();

        System.out.println("Index of 2: " + list.getIndex(2));
        list.remove(3);
        System.out.println("List after removing 3:");
        list.printList();
    }
}
