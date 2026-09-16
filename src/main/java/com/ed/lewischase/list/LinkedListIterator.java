package com.ed.lewischase.list;
import java.util.Iterator;
import com.ed.lewischase.LinkedNode.LinearNode;

public class LinkedListIterator<T> implements Iterator<T> {
    private LinearNode<T> current;

    public LinkedListIterator(LinearNode<T> front) {
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
