package implementations;

import interfaces.LinkedList;

import java.util.Iterator;

public class DoublyLinkedList<E> implements LinkedList<E> {
    private Node<E> head;
    private Node<E> tail;
    private int size;

    private static class Node<E> {
        private E element;
        private Node<E> next;
        private Node<E> previous;

        public Node(E value) {
            this.element = value;
        }
    }

    public DoublyLinkedList() {
        this.head = this.tail = null;
        this.size = 0;
    }

    @Override
    public void addFirst(E element) {
        Node<E> newNode = new Node<>(element);
        if (this.head == null) {
            this.head = newNode;
            this.tail = newNode;
            newNode.previous = null;
            newNode.next = null;
        } else {
//        Link a new Node to the previous one.
            newNode.previous = this.head;
//        Make a connection to the newly added node (the next Node).
            this.head.next = newNode;
//        Moving the head pointer to the newly added node.
            this.head = newNode;
        }
        this.size++;
    }

    @Override
    public void addLast(E element) {
        Node<E> newNode = new Node<>(element);

        if (this.head == null) {
            this.head = newNode;
            this.tail = newNode;
            newNode.previous = null;
            newNode.next = null;
        } else {
//            Connect the newly added node to current tail.
            newNode.next = this.tail;
//            Connect the current tail to the newly added node.
            this.tail.previous = newNode;
//            Moving the tail pointer to the newly added node.
            this.tail = newNode;
        }
        this.size++;
    }

    @Override
    public E removeFirst() {
        ensureNotEmpty();
        E element = this.head.element;
        if (this.size == 1) {
            this.head = null;
            this.tail = null;
        } else {
            this.head = this.head.previous;
            this.head.next = null;
        }
        this.size--;
        return element;
    }

    @Override
    public E removeLast() {
        ensureNotEmpty();
        E element = this.tail.element;
        if (this.size == 1) {
            this.head = null;
            this.tail = null;
        } else {
            this.tail = this.tail.next;
            this.tail.previous = null;
        }
        this.size--;

        return element;
    }

    @Override
    public E getFirst() {
        ensureNotEmpty();
        return this.head.element;
    }

    @Override
    public E getLast() {
        ensureNotEmpty();
        return this.tail.element;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }


    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Node<E> current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public E next() {
                E element = current.element;
                current = current.next;
                return element;
            }
        };
    }

    private void ensureNotEmpty() {
        if (this.size == 0) {
            throw new IllegalStateException("Illegal remove for empty LinkedList");
        }
    }
}
