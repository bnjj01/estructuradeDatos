package com.ed.lewischase.list;

import java.util.Iterator;

import com.ed.lewischase.LinkedNode.LinearNode;

public class LinkedList<T> implements ListADT<T>{
    private LinearNode<T> front;
    private LinearNode<T> rear;
    private int count;

    public LinkedList() {
        front = null;
        rear = null;
        count = 0;
    }
    
    @Override
    public boolean contains(T target) {
        for(LinearNode<T> current = front; current != null; current = current.getNext()){
            if( target != null && target.equals(current.getElement())) return true;
        }
        return false;
    }

    @Override
    public T first() throws EmptyListException {
        if(isEmpty()) throw new EmptyListException();
        return front.getElement();
    }

    @Override
    public boolean isEmpty() {
        return front==null;
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
        if(isEmpty()) throw new EmptyListException();
        if(!contains(element)) throw new ElementNotFoundException(element.toString());
        LinearNode<T> current = front;
        LinearNode<T> previous = null;
        T elementoEliminado;
        while(current != null){
            if(!current.getElement().equals(element)){
                previous = current;
                current = current.getNext();
            }else if(current == front) {
                    elementoEliminado = current.getElement();
                    front = current.getNext();
                    count--;
                    return elementoEliminado;
                }else if (current == rear){
                    elementoEliminado = current.getElement();
                    previous.setNext(null);
                    rear = previous;
                    current = null;
                    count--;
                    return elementoEliminado;
                }else{
                    elementoEliminado = current.getElement();
                    LinearNode<T> siguiente = current.getNext();
                    current.setNext(null);
                    previous.setNext(siguiente);
                    current = siguiente;
                    count--;
                    return elementoEliminado;
                }
        }
        return elementoEliminado;
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
        return count;
    }

    
}
