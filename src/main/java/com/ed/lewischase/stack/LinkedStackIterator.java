package com.ed.lewischase.stack;

import com.ed.lewischase.LinkedNode.LinearNode;

import java.util.Iterator;

public class LinkedStackIterator<T> implements Iterator<T> {

    private LinearNode<T> aux;

    public LinkedStackIterator(LinearNode<T> top) {
        this.aux = top;
    }

    @Override
    public T next() {
        T element = aux.getElement();
        aux = aux.getNext();
        return null;
    }

    @Override
    public boolean hasNext() {
        return aux != null;
    }
}
