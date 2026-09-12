package com.ed.lewischase.queue;

import com.ed.lewischase.LinkedNode.LinearNode;

import java.util.Iterator;

public class LinkedQueueIterator<T> implements Iterator<T> {
    private LinearNode<T> aux;

    public LinkedQueueIterator(LinearNode<T> aux) {
        this.aux = aux;
    }

    @Override
    public boolean hasNext() {
        return aux != null;
    }

    @Override
    public T next() {
        T element = aux.getElement();
        aux = aux.getNext();
        return element;
    }
}
