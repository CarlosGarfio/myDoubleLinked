package doubleLinkedList;

public class Node<T> {
    private T       value;
    private Node<T> next;
    private Node<T> back;
    
    public Node() {
        this(null);
    }
    
    public Node(T value) {
        this.value = value;
        this.next  = null;
        this.back  = null;
    }
    
    public T getValue() {
        return value;
    }
    
    public void setValue(T value) {
        this.value = value;
    }
    
    public Node<T> getNext() {
        return next;
    }
    
    public void setNext(Node<T> next) {
        this.next = next;
    }
    
    public Node<T> getBack() {
        return back;
    }
    
    public void setBack(Node<T> back) {
        this.back = back;
    }
    
}
