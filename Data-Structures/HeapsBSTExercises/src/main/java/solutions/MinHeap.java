package solutions;

import interfaces.Decrease;
import interfaces.Heap;

import java.util.ArrayList;
import java.util.List;

public class MinHeap<E extends Comparable<E> & Decrease<E>> implements Heap<E> {

    private List<E> elements;

    public MinHeap() {
        this.elements = new ArrayList<>();
    }

    @Override
    public int size() {
        return this.elements.size();
    }

    @Override
    public void add(E element) {
        this.elements.add(element);
        E parent = this.elements.get(this.getParentIndex(this.elements.size() - 1));
        if (this.isBigger(parent, element)) {
            this.heapifyUp(parent, element);
        }
    }

    @Override
    public E peek() {
        this.ensureNonEmptyHeap();
        return this.elements.get(0);
    }

    @Override
    public E poll() {
        this.ensureNonEmptyHeap();
        int lastIndex = this.elements.size() - 1;

        if (this.elements.size() == 1) {
            return this.elements.remove(lastIndex);
        }
        this.swap(0, lastIndex);

        E removedElement = this.elements.remove(lastIndex);
        this.heapifyDown();
        return removedElement;
    }

    @Override
    public void decrease(E element) {
        int indexOfElement = this.elements.indexOf(element);
        if (indexOfElement == -1) {
            throw new IllegalStateException("Element: " + element + " doesn't exist!");
        }
        if (indexOfElement == 0) {
            this.elements.get(0)
                    .decrease();
            return;
        }

        int indexOfParentElement = this.getParentIndex(indexOfElement);

        this.elements.get(indexOfElement)
                .decrease();
        this.heapifyUp(this.elements.get(indexOfParentElement), this.elements.get(indexOfElement));
    }

    private boolean isBigger(E parent, E element) {
        return parent.compareTo(element) > 0;
    }

    private void ensureNonEmptyHeap() {
        if (this.elements.isEmpty()) {
            throw new IllegalStateException();
        }
    }

    private int getParentIndex(int index) {
        return (index - 1) / 2;
    }

    private boolean isNotMinHeap(E parent, E element) {
        return this.isBigger(parent, element);
    }

    private void swap(int parentIndex, int elementIndex) {
        E tmp = this.elements.get(parentIndex);
        this.elements.set(parentIndex, this.elements.get(elementIndex));
        this.elements.set(elementIndex, tmp);
    }

    private void heapifyUp(E parent, E element) {
        int elementIndex = this.elements.size() - 1;
        int parentIndex = this.getParentIndex(elementIndex);

        while (this.isNotMinHeap(parent, element)) {
            this.swap(parentIndex, elementIndex);
            parent = this.elements.get(this.getParentIndex(parentIndex));
            element = this.elements.get(parentIndex);
            elementIndex = parentIndex;
            parentIndex = this.getParentIndex(elementIndex);
        }
    }

    /**
     * Returns the index of the right child or -1 if the node doesn't have right child
     *
     * @param parentIndex index of the parent node.
     * @return index of the right child or -1 if the node doesn't have right child
     */
    private int getRightChildIndex(int parentIndex) {
        return 2 * parentIndex + 2 > this.size() - 1 ? -1 : 2 * parentIndex + 2;
    }

    /**
     * Returns the index of the left child or -1 if the node doesn't have left child
     *
     * @param parentIndex index of the parent node.
     * @return index of the left child or -1 if the node doesn't have left child
     */
    private int getLeftChildIndex(int parentIndex) {
        return 2 * parentIndex + 1 > this.size() - 1 ? -1 : 2 * parentIndex + 1;
    }

    private void heapifyDown() {
        if (this.elements.size() == 1) {
            return;
        } else if (this.elements.size() == 2) {
            if (this.isNotMinHeap(this.elements.get(0), this.elements.get(1))) {
                swap(0, 1);
            }
            return;
        }

        int parentIndex = 0;
        int leftChildIndex = this.getLeftChildIndex(parentIndex);
        int rightChildIndex = this.getRightChildIndex(parentIndex);
        E parentElement = this.elements.get(parentIndex);
        E leftChild = this.elements.get(leftChildIndex);
        E rightChild = this.elements.get(rightChildIndex);

        while (this.isNotMinHeap(parentElement, leftChild) || this.isNotMinHeap(parentElement, rightChild)) {
            if (this.isBigger(rightChild, leftChild)) {
                this.swap(parentIndex, leftChildIndex);
                parentIndex = leftChildIndex;
            } else {
                this.swap(parentIndex, rightChildIndex);
                parentIndex = rightChildIndex;
            }

            leftChildIndex = this.getLeftChildIndex(parentIndex);
            rightChildIndex = this.getRightChildIndex(parentIndex);

            if (leftChildIndex < 0 || rightChildIndex < 0) {
                return;
            }

            parentElement = this.elements.get(parentIndex);
            leftChild = this.elements.get(leftChildIndex);
            rightChild = this.elements.get(rightChildIndex);

        }

    }
}
