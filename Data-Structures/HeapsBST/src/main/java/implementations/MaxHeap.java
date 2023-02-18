package implementations;

import interfaces.Heap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MaxHeap<E extends Comparable<E>> implements Heap<E> {

    private List<E> elements ;

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

    private void heapifyUp(int index) {
        int currentIndex = index;
        int parentIndex = getParent(currentIndex);
        E currentElement = this.elements.get(index);
        E parentElement = this.elements.get(parentIndex);

        while (!isMaxHeap(parentElement, currentElement)){
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
        if (this.size() == 0){
            throw new IllegalStateException();
        }
        return this.elements.get(0);
    }
}
