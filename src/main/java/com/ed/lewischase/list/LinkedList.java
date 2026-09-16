package com.ed.lewischase.list;

import java.util.Iterator;

import com.ed.lewischase.LinkedNode.LinearNode;

public class LinkedList<T> implements ListADT<T>{
    protected LinearNode<T> front, rear;
    protected int count;

    public LinkedList() {
        front = rear = null;
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
        return new LinkedListIterator<T>(front);
    }

    @Override
    public T last() throws EmptyListException {
        if(isEmpty()) throw new EmptyListException();
        return rear.getElement();
    }

    @Override
    public T remove(T element) throws EmptyListException, ElementNotFoundException {
        if(isEmpty()) throw new EmptyListException();
        //convierto el elemento a eliminar en un objeto comparable para poder compararlo con los elementos de la lista
        Comparable<T> elementComp= (Comparable<T>)element;

        LinearNode<T> current = front;
        LinearNode<T> previous = null;

        while(current != null && elementComp.compareTo(current.getElement()) != 0){
            previous = current;
            current = current.getNext();
        }
        //verifico si existe el elemento enontrado
        if(current == null){throw new ElementNotFoundException("Elemento no encontrado");}

        T copy = current.getElement();

        if(size() == 1){
            front = rear = null;
        }else if(current == front){
            front = front.getNext();
        }else if(current == rear){
            rear = previous;
            rear.setNext(null);
        }else{
            previous.setNext(current.getNext());
        }
        count--;
        return copy;
    }

    @Override
    public T removeFirst() throws EmptyListException {
        if(isEmpty()) throw new EmptyListException();
        LinearNode<T> siguiente = front.getNext();
        T elementoEliminado = front.getElement();
        if(front == rear){
            front = rear = null;
        } else{
            front = siguiente;
        }
        count--;
        return elementoEliminado;
    }

    @Override
    public T removeLast() throws EmptyListException {
        if(isEmpty()) throw new EmptyListException();
        LinearNode<T> previous = front;
        T elementoEliminado = rear.getElement();
        if(front == rear){ 
            front = rear = null;
            count--;
            return elementoEliminado;
        }
        while(previous.getNext() != rear){
            previous = previous.getNext();
        }
        rear = previous;
        rear.setNext(null);
        count--;
        return elementoEliminado;
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(LinearNode<T> current = front; current != null; current = current.getNext()){
            sb.append(current.getElement()).append(" ");
        }
        return sb.toString().trim();
    }
}