package com.ed.lewischase.list;

import com.ed.lewischase.LinkedNode.LinearNode;
import java.util.Iterator;

public class IndexedListIterator<T> implements Iterator<T> {
    private LinearNode<T> current;

    public IndexedListIterator(LinearNode<T> front){
        current = front;
    }

    @Override
    public boolean hasNext() {
        return current != null;
    }

    @Override
    public T next() {
        T result = current.getElement();
        current = current.getNext();
        return result;
    }
}
