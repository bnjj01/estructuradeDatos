package com.ed.lewischase.list;
import com.ed.lewischase.LinkedNode.LinearNode;
import java.util.Iterator;

public class OrderedList<T> implements OrderedListADT<T> {
    LinearNode<T> front, rear;
    int count;

    @Override
    public void add(T element) {
        LinearNode<T> newNode = new LinearNode<>(element);
        count++;
    }

    @Override
    public boolean contains(T target) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public T first() throws EmptyListException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean isEmpty() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public T last() throws EmptyListException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public T remove(T element) throws EmptyListException, ElementNotFoundException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public T removeFirst() throws EmptyListException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public T removeLast() throws EmptyListException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int size() {
        // TODO Auto-generated method stub
        return 0;
    }
    
}
