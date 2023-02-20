package implementations;

import interfaces.AbstractQueue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PriorityQueue<E extends Comparable<E>> implements AbstractQueue<E> {

    private MaxHeap<E> maxHeap;

    public PriorityQueue(){
        this.maxHeap = new MaxHeap<>();
    }

    @Override
    public int size() {
        return this.maxHeap.size();
    }

    @Override
    public void add(E element) {
        this.maxHeap.add(element);
    }

    @Override
    public E peek() {
        return this.maxHeap.peek();
    }

    @Override
    public E poll() {
        return this.maxHeap.remove();
    }

    public void offer(E element){
        this.add(element);
    }
}
