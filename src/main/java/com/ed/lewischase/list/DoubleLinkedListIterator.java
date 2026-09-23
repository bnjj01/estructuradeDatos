package com.ed.lewischase.list;
import java.util.Iterator;

import com.ed.lewischase.LinkedNode.DoubleNode;

public class DoubleLinkedListIterator<T> implements Iterator<T>{
    DoubleNode<T> current;

    public DoubleLinkedListIterator(DoubleNode<T> front) {
        current = front;
    }

    @Override
    public boolean hasNext() {
        return current!=null;
    }

    @Override
    public T next() {
        T result = current.getElement();
        current=current.getNext();
        return result;
    }
}
