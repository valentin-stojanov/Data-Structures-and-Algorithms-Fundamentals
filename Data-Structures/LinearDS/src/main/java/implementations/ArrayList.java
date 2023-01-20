package implementations;

import interfaces.List;

import java.util.Iterator;

public class ArrayList<E> implements List<E> {
    private static final int INITIAL_SIZE = 4;
    private int size;
    private Object[] elements;

    public ArrayList() {
        this.size = 0;
        this.elements = new Object[INITIAL_SIZE];
    }

    public ArrayList(int initialSize) {
        this.size = 0;
        this.elements = new Object[initialSize];
    }

    @Override
    public boolean add(E element) {
        this.ensureCapacity();
        this.elements[size++] = element;

        return true;
    }

    @Override
    public boolean add(int index, E element) {
        if (!this.isValidIndex(index)) {
            return false;
        }
        this.ensureCapacity();

        for (int i = this.size; i >= index; i--) {
            this.elements[i + 1] = this.elements[i];
        }

        this.elements[index] = element;
        this.size++;

        return true;
    }

    @Override
    public E get(int index) {
        this.validateIndex(index);
        return (E) this.elements[index];
    }

    @Override
    public E set(int index, E element) {
        this.validateIndex(index);
        E temp = (E) this.elements[index];
        this.elements[index] = element;
        return temp;
    }

    @Override
    public E remove(int index) {
        this.validateIndex(index);
        if (this.size < this.elements.length / 3) {
            this.elements = shrink();
        }
        E temp = (E) this.elements[index];

        if (this.size > 0) {
            for (int i = index; i < this.size; i++) {
                this.elements[i] = elements[i + 1];
            }
        }
        size--;
        return temp;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public int indexOf(E element) {
        int index = -1;
        for (int i = 0; i < this.size; i++) {
            if (this.elements[i].equals(element)){
                index = i;
                break;
            }
        }
        return index;
    }

    @Override
    public boolean contains(E element) {
        int i = this.indexOf(element);
        return i != -1;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return this.index < size();
            }

            @Override
            public E next() {
                return get(index++);
            }
        };
    }

    private void ensureCapacity() {
        if (this.size < this.elements.length) {
            return;
        }
//        keep in mind that the max array size is Integer.MAX_VALUE -2!!!
        Object[] temp = new Object[elements.length * 2];
        for (int i = 0; i < this.elements.length; i++) {
            temp[i] = this.elements[i];
        }
        elements = temp;
    }

    private Object[] shrink() {
        Object[] temp = new Object[this.elements.length / 2];
        for (int i = 0; i < this.size; i++) {
            temp[i] = this.elements[i];
        }
        return temp;
    }

    private boolean isValidIndex(int index) {
        return 0 <= index && index < this.size;
    }

    private void validateIndex(int index) {
        if (!isValidIndex(index)) {
            throw new IndexOutOfBoundsException("Invalid index " + index);
        }
    }
}
