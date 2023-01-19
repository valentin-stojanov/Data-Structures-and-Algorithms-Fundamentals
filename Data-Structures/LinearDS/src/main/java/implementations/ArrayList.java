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
        ensureCapacity();
        this.elements[size++] = element;

        return true;
    }

    @Override
    public boolean add(int index, E element) {
        if (!isValidIndex(index)) {
            return false;
        }
        ensureCapacity();

        for (int i = this.size; i >= index; i--) {
            this.elements[i + 1] = this.elements[i];
        }

        this.elements[index] = element;
        this.size++;

        return true;
    }

    @Override
    public E get(int index) {
        validateIndex(index);
        return (E) this.elements[index];
    }

    @Override
    public E set(int index, E element) {
        validateIndex(index);
        E temp = (E) this.elements[index];
        this.elements[index] = element;
        return temp;
    }

    @Override
    public E remove(int index) {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public int indexOf(E element) {
        return 0;
    }

    @Override
    public boolean contains(E element) {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
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

    private boolean isValidIndex(int index) {
        return 0 <= index && index < this.size;
    }

    private void validateIndex(int index) {
        if (!isValidIndex(index)) {
            throw new IndexOutOfBoundsException("Invalid index " + index);
        }
    }
}
