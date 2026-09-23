package com.ed.lewischase.list;

import com.ed.lewischase.LinkedNode.DoubleNode;

import java.util.Iterator;

public class DoubleLinkedList<T> implements ListADT<T>{
    protected DoubleNode<T> front,rear;
    protected int count;

    public DoubleLinkedList() {
        front = rear = null;
        count = 0;
    }

    @Override
    public T removeFirst() throws EmptyListException {
        if(isEmpty())throw new EmptyListException();
        T copy = front.getElement();
        if(count == 1){
            front=rear=null;
            count--;
            return copy;
        }
        front = front.getNext();
        front.setPrevious(null);
        count--;
        return copy;
    }

    @Override
    public T removeLast() throws EmptyListException {
        if (isEmpty())throw new EmptyListException();
        T copy = rear.getElement();

        if (count==1){
            front = rear=null;
            count--;
            return copy;
        }

        rear = rear.getPrevious();
        rear.setNext(null);
        count--;
        return copy;
    }

    @Override
    public T remove(T element) throws EmptyListException, ElementNotFoundException {
        if (isEmpty()) throw new EmptyListException();
        T copy = element;
        DoubleNode<T> current=front;
        DoubleNode<T> aux=null;
        while(current!=null && !element.equals(current.getElement())){
            if(current.getElement() == element){
                aux.setNext(current.getNext());
                current=current.getPrevious();
                current.setNext(aux.getNext());
                aux.setPrevious();
            }
            current=current.getNext();
        }
        if (current == null) throw new ElementNotFoundException(element.toString());
        return null;
    }

    @Override
    public T first() throws EmptyListException {
        return null;
    }

    @Override
    public T last() throws EmptyListException {
        return null;
    }

    @Override
    public boolean contains(T target) {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }
}
