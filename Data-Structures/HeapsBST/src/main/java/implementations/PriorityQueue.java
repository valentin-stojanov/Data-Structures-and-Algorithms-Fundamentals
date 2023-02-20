package implementations;

import interfaces.AbstractQueue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PriorityQueue<E extends Comparable<E>> implements AbstractQueue<E> {

    private List<E> elements;

    public PriorityQueue(){
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

    @Override
    public E poll() {
        this.ensureNonEmpty();
        return this.remove();
    }

    private E remove() {
        this.swap(0, this.size() - 1);
        E remove = this.elements.remove(this.size() - 1);
        this.heapifyDown(0);
        return remove;
    }

    @Override
    public E peek() {
        this.ensureNonEmpty();
        return this.elements.get(0);
    }

    private void ensureNonEmpty() {
        if (this.size() == 0) {
            throw new IllegalStateException();
        }
    }

    private void heapifyDown(int index) {
        int parentIndex = index;
        int leftIndex = getLeftIndex(parentIndex);
        int rightIndex = getRightIndex(parentIndex);

        if (this.size() == 2 && !isMaxHeap(this.elements.get(0), this.elements.get(1))) {
            this.swap(0, 1);
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
            this.swap(parentIndex, childIndex);
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
            this.swap(parentIndex, currentIndex);
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

    private int getRightIndex(int parentIndex) {
        return 2 * parentIndex + 2;
    }

    private int getLeftIndex(int parentIndex) {
        return 2 * parentIndex + 1;
    }
}
