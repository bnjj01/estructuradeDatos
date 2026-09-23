package com.ed.lewischase.list;

import com.ed.lewischase.LinkedNode.LinearNode;

import java.util.Iterator;

public class UnorderedList<T> implements UnorderedListADT<T>{
    protected LinearNode<T> front,rear;
    protected int count;
    public UnorderedList(){
        front =rear=null;
        count=0;
    }

    @Override
    public void addToFront(T element) {
        LinearNode<T> newNode = new LinearNode<>(element);
        if(isEmpty()){
            front = rear = newNode;
        }else{
            newNode.setNext(front);
            front=newNode;
        }
        count++;
    }

    @Override
    public void addToRear(T element) {
        LinearNode<T> newNode = new LinearNode<>(element);
        if(isEmpty()){
            front = rear = newNode;
        }else{
            rear.setNext(newNode);
            rear=newNode;
        }
        count++;
    }

    @Override
    public void addAfter(T element, T target) throws ElementNotFoundException, EmptyListException {
        if(isEmpty())throw new EmptyListException();
        LinearNode<T> newNode = new LinearNode<>(element);

        LinearNode<T> current=front;
        while(current!=null){
            if(current.getElement().equals(target)){
                newNode.setNext(current.getNext());
                current.setNext(newNode);
                if(current == rear){
                    rear=newNode;
                }
                count++;
                return;
            }

            current = current.getNext();
        }

        throw new ElementNotFoundException(target.toString());
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

        LinearNode<T> current = front;
        LinearNode<T> previous = null;

        while(current != null && !element.equals(current.getElement())){
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
}
