package implementations;

import interfaces.Deque;

import java.util.Iterator;

public class ArrayDeque<E> implements Deque<E> {
    private final int DEFAULT_CAPACITY = 7;
    private int headIndex;
    private int tailIndex;
    private int size;
    private Object[] elements;

    public ArrayDeque() {
        this.elements = new Object[DEFAULT_CAPACITY];
        this.headIndex = this.elements.length / 2;
        this.tailIndex = this.headIndex;
    }

    @Override
    public void add(E element) {
        addLast(element);
    }

    @Override
    public void offer(E element) {
        addFirst(element);
    }

    @Override
    public void addFirst(E element) {
        if (this.tailIndex <= 0) {
            ensureCapacity();
        }

        if (this.isEmpty()) {
            this.elements[this.headIndex] = element;
        } else {
            this.elements[--this.tailIndex] = element;
        }

        this.size++;
    }

    @Override
    public void addLast(E element) {
        if (this.headIndex >= this.elements.length - 1) {
            ensureCapacity();
        }

        if (this.isEmpty()) {
            this.elements[this.headIndex] = element;
        } else {
            this.elements[++this.headIndex] = element;
        }

        this.size++;
    }

    @Override
    public void push(E element) {
        addLast(element);
    }

    @Override
    public void insert(int index, E element) {
        isValidIndex(index);
        int mid = (this.headIndex + this.tailIndex) >> 1;
        int realIndex = index + this.tailIndex;

        if (capacity() == 0) {
            ensureCapacity();
        } else if (realIndex <= mid) {
            if (this.tailIndex < 1) {
                ensureCapacity();
            }

            for (int i = this.tailIndex; i < realIndex ; i++) {
                 this.elements[i - 1] = this.elements[i];
            }

            this.tailIndex--;
            this.size++;
            set(index, element);

        } else {
            if (this.headIndex > this.elements.length - 2){
                ensureCapacity();
            }


        }
    }

    @Override
    public void set(int index, E element) {
        isValidIndex(index);
        this.elements[index + this.tailIndex] = element;
    }

    @Override
    public E peek() {
        return (E) this.elements[this.headIndex];
    }

    @Override
    public E poll() {
        return this.removeFirst();
    }

    @Override
    public E pop() {
        return this.removeLast();
    }

    @Override
    public E get(int index) {
        isValidIndex(index);
        return (E) this.elements[index + this.tailIndex];
    }

    @Override
    public E get(Object object) {
        int realIndexOfObject = findRealIndexOfObject(object);
        return realIndexOfObject != -1
                ? (E) this.elements[realIndexOfObject]
                : null;
    }

    @Override
    public E remove(int index) {
        isValidIndex(index);
        int realIndex = index + this.tailIndex;
        int mid = (this.headIndex + this.tailIndex) >> 1;
        E removedElement = (E) this.elements[realIndex];

        if (realIndex == this.tailIndex) {
            removeFirst();
        } else if (realIndex == this.headIndex) {
            removeLast();
        } else if (realIndex <= mid) {

            for (int i = realIndex; i > this.tailIndex; i--) {
                this.elements[i] = this.elements[i - 1];
            }
            this.elements[tailIndex] = null;
            this.tailIndex++;
            this.size--;
        } else {

            for (int i = realIndex; i < this.headIndex; i++) {
                this.elements[i] = this.elements[i + 1];
            }
            this.elements[headIndex] = null;
            this.headIndex--;
            this.size--;
        }

        return removedElement;
    }

    @Override
    public E remove(Object object) {
        int realIndexOfObject = findRealIndexOfObject(object);
        return realIndexOfObject != -1
                ? remove(realIndexOfObject - this.tailIndex)
                : null;
    }

    @Override
    public E removeFirst() {
        E firstElement = (E) this.elements[this.headIndex];
        this.elements[this.headIndex] = null;
        this.size--;
        return firstElement;
    }

    @Override
    public E removeLast() {
        E lastElement = (E) this.elements[this.tailIndex];
        this.elements[this.tailIndex] = null;
        this.size--;
        return lastElement;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public int capacity() {
        return this.elements.length - this.size;
    }

    @Override
    public void trimToSize() {
        Object[] newElements = new Object[this.size];
        for (int i = this.tailIndex, j = 0; i < this.headIndex; i++, j++) {
            newElements[j] = this.elements[i];
        }
        this.elements = newElements;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int startIndex = tailIndex;

            @Override
            public boolean hasNext() {
                return this.startIndex < headIndex;
            }

            @Override
            public E next() {
                return (E) elements[this.startIndex++];
            }
        };
    }

    private void ensureCapacity() {
        Object[] newElements = new Object[this.elements.length * 2 + 1];
        int indexToStartFillingFrom = this.elements.length - (this.size / 2);

        for (int i = indexToStartFillingFrom, j = this.tailIndex; j <= this.headIndex; i++, j++) {
            newElements[i] = this.elements[j];
        }

        this.elements = newElements;
        this.tailIndex = indexToStartFillingFrom;
//        size = head - tail + 1 --> head = tail + size - 1
        this.headIndex = this.tailIndex + this.size - 1;
    }

    private void isValidIndex(int index) {
        int realIndex = index + this.tailIndex;

        if (!(this.tailIndex <= realIndex && realIndex <= this.headIndex)) {
            throw new IndexOutOfBoundsException();
        }
    }

    private int findRealIndexOfObject(Object object) {
        int index = -1;
        for (int i = tailIndex; i < headIndex; i++) {
            if (object.equals(this.elements[i])) {
                index = i;
                break;
            }
        }
        return index;
    }
}
