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
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public T last() throws EmptyListException {
        if(isEmpty()) throw new EmptyListException();
        return rear.getElement();
    }

    @Override
    public T remove(T element) throws EmptyListException, ElementNotFoundException {
        if(isEmpty()) throw new EmptyListException();
        if(!contains(element)) throw new ElementNotFoundException(element.toString());
        LinearNode<T> current = front;
        LinearNode<T> previous = null;
        T elementoEliminado = null;
        while(current != null){
            if(!current.getElement().equals(element)){
                previous = current;
                current = current.getNext();
            }else if(current == front && current == rear){
                    elementoEliminado = current.getElement();
                    front = null;
                    rear = null;
                    count--;
                    return elementoEliminado;
                } else if(current == front) {
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


    /*
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
            previous.setNext(current.getNext());
        }
        T copy = current.getElement();
        if(size() == 1){
            front = rear = null;
        }else if(current == front){
            front = front.getNext();
        }else if(current == rear){
            rear = previous;
            rear.setNext(null);
        }else{
            previus.setNext(current.getNext());
        }
        count--;
        return copy;
    }
    */

    @Override
    public T removeFirst() throws EmptyListException {
        if(isEmpty()) throw new EmptyListException();
        LinearNode<T> siguiente = front.getNext();
        T elementoEliminado = front.getElement();
        if(front == rear){
            front = rear = null;
        } else{
            elementoEliminado = front.getElement();
            front = siguiente;
        }
        count--;
        return elementoEliminado;
    }

    @Override
    public T removeLast() throws EmptyListException {
        if(isEmpty()) throw new EmptyListException();
        LinearNode<T> previus = front;
        T elementoEliminado = rear.getElement();
        if(front == rear){ 
            front = rear = null;
            count--;
            return elementoEliminado;
        }
        while(previus.getNext() != rear){
            previus = previus.getNext();
        }
        rear = previus;
        rear.setNext(null);
        count--;
        return elementoEliminado;
    }

    @Override
    public int size() {
        return count;
    }
}
