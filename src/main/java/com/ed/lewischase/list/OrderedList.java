package com.ed.lewischase.list;
import com.ed.lewischase.LinkedNode.LinearNode;
import java.util.Iterator;

public class OrderedList<T> implements OrderedListADT<T> {
    LinearNode<T> front, rear;
    int count;

    @Override
    public void add(T element) {
        LinearNode<T> newNode = new LinearNode<>(element);
        Comparable<T> elementComp = (Comparable<T>)element;

        if(isEmpty()){
            front = rear = newNode;
            count++;
            return;
        }else if(elementComp.compareTo(front.getElement()) <= 0){
            newNode.setNext(front);
            front = newNode;
            count++;
            return;
        }

        LinearNode<T> current = front;
        LinearNode<T> previous = null;

        while(current != null && elementComp.compareTo(current.getElement()) > 0){
            previous=current;
            current=current.getNext();
        }
        if(current == null){
            previous.setNext(newNode);
            rear = newNode;
        }else{
            previous.setNext(newNode);
            newNode.setNext(current);
        }
        count++;
    }

    @Override
    public boolean contains(T target) {
        Comparable<T> elemComp = (Comparable<T>)target;
        LinearNode<T> current=front;

        if(isEmpty()) return false;

        while(current.getNext() != null){
            if(elemComp.compareTo(current.getElement()) == 0){
                return true;
            }
            current=current.getNext();
        }
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
