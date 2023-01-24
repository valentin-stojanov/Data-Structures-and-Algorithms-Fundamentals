package implementations;

import interfaces.LinkedList;

import java.util.Iterator;

public class SinglyLinkedList<E> implements LinkedList<E> {

    private Node<E> first;
    private int size;

    public SinglyLinkedList() {
        this.first = null;
        this.size = 0;
    }

    private static class Node<E> {
        private Node<E> next;
        private E value;

        Node(E element) {
            this.value = element;
        }
    }

    @Override
    public void addFirst(E element) {
        Node<E> nextElement = new Node<>(element);

        nextElement.next = this.first;
        this.first = nextElement;

        this.size++;
    }

    @Override
    public void addLast(E element) {
        Node<E> lNode = new Node<>(element);

        if (this.size > 0) {
            Node<E> current = this.first;
            Node<E> lastElement = goToLastElement(current);
            lastElement.next = lNode;
            this.size++;
        } else {
            this.addFirst(element);
        }
    }

    @Override
    public E removeFirst() {
        Node<E> tmp = this.first;
        this.first = this.first.next;
        this.size--;

        return tmp.value;
    }

    @Override
    public E removeLast() {
        ensureNonEmptyList();
        Node<E> prelast = this.first;

        if (this.size > 1) {
            while (prelast.next.next != null) {
                prelast = prelast.next;
            }
        }

        Node<E> last = prelast.next;
        prelast.next = null;
        this.size--;

        return last.value;
    }

    @Override
    public E getFirst() {
        return this.first.value;
    }

    @Override
    public E getLast() {
        return this.goToLastElement(this.first)
                .value;
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

            Node<E> current = first;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public E next() {
                E value = this.current.value;
                this.current = this.current.next;
                return value;
            }
        };
    }

    private void ensureNonEmptyList() {
        if (this.size == 0) {
            throw new IllegalStateException();
        }
    }

    private Node<E> goToLastElement(Node<E> current) {
        while (current.next != null) {
            current = current.next;
        }
        return current;
    }
}
