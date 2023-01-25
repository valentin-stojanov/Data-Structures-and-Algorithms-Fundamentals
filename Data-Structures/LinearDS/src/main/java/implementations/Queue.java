package implementations;

import interfaces.AbstractQueue;

import java.util.Iterator;

public class Queue<E> implements AbstractQueue<E> {
    private Node<E> head;
    private Node<E> tail;
    private int size;

    private static class Node<E> {
        private Node<E> next;
        private E value;

        Node(E element) {
            this.value = element;
        }
    }

    public Queue() {
        this.size = 0;
        this.head = null;
        this.tail = null;
    }

    @Override
    public void offer(E element) {
        Node<E> newElement = new Node<>(element);

        if (this.head == null) {
            this.head = newElement;
            this.tail = newElement;
        }
        this.tail.next = newElement;
        this.tail = newElement;

        this.size++;
    }

    @Override
    public E poll() {
        ensureNotEmptyQueue();

        E head = this.head.value;
        this.head = this.head.next;
        this.size--;
        if (this.size == 0){
            this.tail = null;
            this.head = null;
        }
        return head;
    }

    @Override
    public E peek() {
        ensureNotEmptyQueue();
        return this.head.value;
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
            Node<E> current = head;

            @Override
            public boolean hasNext() {
                return tail.next != null;
            }

            @Override
            public E next() {
                Node<E> element = this.current;
                current = current.next;
                return element.value;
            }
        };
    }

    private void ensureNotEmptyQueue() {
        if (this.size == 0) {
            throw new IllegalStateException();
        }
    }
}
