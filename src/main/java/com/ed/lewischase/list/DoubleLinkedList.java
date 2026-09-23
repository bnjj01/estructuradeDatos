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

        DoubleNode<T> current=front;

        while(current!=null && !element.equals(current.getElement())){
            current=current.getNext();
        }
        if(current == null) throw new ElementNotFoundException(element.toString());

        T copy = element;

        if(current == front){
            return removeFirst();
        }else if(current == rear){
            return removeLast();
        } else {
            DoubleNode<T> aux;
            aux=current.getPrevious();
            aux.setNext(current.getNext());
            aux=current.getNext();
            aux.setPrevious(current.getPrevious());
            count--;
            return copy;
        }
    }

    @Override
    public T first() throws EmptyListException {
        if (isEmpty()) throw new EmptyListException();
        return front.getElement();
    }

    @Override
    public T last() throws EmptyListException {
        if (isEmpty()) throw new EmptyListException();
        return rear.getElement();
    }

    @Override
    public boolean contains(T target) {
        DoubleNode<T> current=front;
        while(current!=null){
            if(target.equals(current.getElement())){
                return true;
            }
            current=current.getNext();
        }
        return false;
    }

    @Override
    public boolean isEmpty() {
        return count==0;
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public Iterator<T> iterator() {
        return new DoubleLinkedListIterator<>(front);
    }
}
