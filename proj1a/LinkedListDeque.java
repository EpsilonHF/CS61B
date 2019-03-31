/* 
 * define the linkedlist deque class
 */

public class LinkedListDeque<Type> {
    private int size;
    private Node sentinel;
    
    // define node in deque
    public class Node {
        public Type item;
        public Node next;
        public Node pre;

        public Node(Type val, Node n1, Node n2) {
            item = val;
            next = n1;
            pre = n2;
        }
    }
    
    // create an empty deque
    public LinkedListDeque() {
        size = 0;
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.pre = sentinel;
    }
    
    // add node at the front of deque
    public void addFirst(Type item) {
        Node n = new Node(item, sentinel.next, sentinel);
        sentinel.next.pre = n;
        sentinel.next = n;
        size++;
    }
    
    // add node at the back of deque
    public void addLast(Type item) {
        Node n = new Node(item, sentinel, sentinel.pre);
        sentinel.pre.next = n;
        sentinel.pre = n;
        size++; 
    }
    
    // return wether deque is empty
    public boolean isEmpty() {
        return size == 0;
    }
    
    // return number of nodes in deque
    public int size() {
        return size;
    }
    
    // print the items in deque
    public void printDeque() {
        for (Node n = sentinel.next; n != sentinel; n = n.next) {
            System.out.print(n.item);
            if (n.next != sentinel)
                System.out.print(" ");
        }
    }
    
    // remove and returns the node at the front of deque
    public Type removeFirst() {
        if (size == 0)
            return null;
        size--;
        Type val = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.pre = sentinel;
        return val;
    }
    
    // remove and returns the node at the back of deque
    public Type removeLast() {
        if (size == 0)
            return null;
        size--;
        Type val = sentinel.pre.item;
        sentinel.pre = sentinel.pre.pre;
        sentinel.pre.next = sentinel;
        return val;
    }

    // return nth item in deque
    public Type get(int index) {
        if (index >= size)
            return null;
        Node n = sentinel;
        for (int i = 0; i <= index; i++)
            n = n.next;
        return n.item;
    }
    
    // return nth item in deque
    public Type getRecursive(int index) {
        if (index >= size)
            return null;
        Node n = sentinel.next;
        return helper(index, n);
    }

    private Type helper(int index, Node n) {
        if (index == 0)
            return n.item;
        else
            return helper(index-1, n.next);
    }
}
