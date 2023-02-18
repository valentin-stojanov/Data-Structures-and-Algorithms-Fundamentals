package implementations;

import interfaces.Heap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MaxHeap<E extends Comparable<E>> implements Heap<E> {

    private List<E> elements;

    public MaxHeap() {
        this.elements = new ArrayList<>();
    }

    @Override
    public int size() {
        return this.elements.size();
    }

    @Override
    public void add(E element) {
        this.elements.add(element);
        this.heapifyUp(this.size() - 1);
    }

    public E remove() {
        swap(0, this.size() - 1);
        E remove = this.elements.remove(this.size() - 1);
        heapifyDown(0);
        return remove;
    }

    private void heapifyDown(int index) {
        int parentIndex = index;
        int leftIndex = getLeftIndex(parentIndex);
        int rightIndex = getRightIndex(parentIndex);

        if (this.size() == 2 && !isMaxHeap(this.elements.get(0), this.elements.get(1))) {
            swap(0, 1);
            return;
        }
        if (leftIndex >= this.size() || rightIndex >= this.size()) {
            return;
        }

        E parentElement = this.elements.get(parentIndex);
        E leftElement = this.elements.get(leftIndex);
        E rightElement = this.elements.get(rightIndex);

        int direction = rightElement.compareTo(leftElement);

        E childElement;
        int childIndex = -1;

        if (direction <= 0) {
            childElement = leftElement;
            childIndex = leftIndex;
        } else {
            childElement = rightElement;
            childIndex = rightIndex;
        }

        while (!isMaxHeap(parentElement, childElement)) {
            swap(parentIndex, childIndex);
            parentIndex = childIndex;
            leftIndex = getLeftIndex(parentIndex);
            rightIndex = getRightIndex(parentIndex);
            if (leftIndex >= this.size() || rightIndex >= this.size()) {
                break;
            }
            parentElement = this.elements.get(parentIndex);
            leftElement = this.elements.get(leftIndex);
            rightElement = this.elements.get(rightIndex);
            direction = rightElement.compareTo(leftElement);

            if (direction <= 0) {
                childElement = leftElement;
                childIndex = leftIndex;
            } else {
                childElement = rightElement;
                childIndex = rightIndex;
            }
        }
    }

    private void heapifyUp(int index) {
        int currentIndex = index;
        int parentIndex = getParent(currentIndex);
        E currentElement = this.elements.get(index);
        E parentElement = this.elements.get(parentIndex);

        while (!isMaxHeap(parentElement, currentElement)) {
            swap(parentIndex, currentIndex);
            currentIndex = parentIndex;
            parentIndex = getParent(currentIndex);
            currentElement = this.elements.get(currentIndex);
            parentElement = this.elements.get(parentIndex);
        }

    }

    private void swap(int parentElementIndex, int currentElementIndex) {
        E parentElement = this.elements.get(parentElementIndex);
        this.elements.set(parentElementIndex, this.elements.get(currentElementIndex));
        this.elements.set(currentElementIndex, parentElement);
    }

    private boolean isMaxHeap(E parentElement, E currentElement) {
        return parentElement.compareTo(currentElement) >= 0;
    }

    private int getParent(int index) {
        return (index - 1) / 2;
    }

    @Override
    public E peek() {
        if (this.size() == 0) {
            throw new IllegalStateException();
        }
        return this.elements.get(0);
    }

    private int getRightIndex(int parentIndex) {
        return 2 * parentIndex + 2;
    }

    private int getLeftIndex(int parentIndex) {
        return 2 * parentIndex + 1;
    }
}
