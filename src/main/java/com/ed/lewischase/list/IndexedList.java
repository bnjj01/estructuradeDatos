package com.ed.lewischase.list;

import com.ed.lewischase.LinkedNode.LinearNode;

import java.util.Iterator;

public class IndexedList<T> implements IndexedListADT<T>{
    protected LinearNode<T> front,rear;
    protected int count;
    @Override
    public void add(int index, T element) throws OutOfRangeException {
        if(index<0 || index>size()) throw new OutOfRangeException(index);
        LinearNode<T> nuevoNodo = new LinearNode<>(element);
        if(isEmpty()) {
            front = rear = nuevoNodo;
            count++;
            return;
        }
        if(index == 0){
            nuevoNodo.setNext(front);
            front=nuevoNodo;
            count++;
            return;
        }
        if(index == size()){
            rear.setNext(nuevoNodo);
            rear=nuevoNodo;
            count++;
            return;
        }

        LinearNode<T> previous=front;

        for(int i=0;i<index-1;i++){
            previous=previous.getNext();
        }
        nuevoNodo.setNext(previous.getNext());
        previous.setNext(nuevoNodo);
        count++;
    }

    @Override
    public void set(int index, T element) throws EmptyListException, OutOfRangeException {
        if(isEmpty())throw new EmptyListException();
        if(index < 0 || index >= size()) throw new OutOfRangeException(index);

        if(index == 0){
            front.setElement(element);
            return;
        }
        if(index == count - 1){
            rear.setElement(element);
            return;
        }

        LinearNode<T> current=front;
        for(int i=0;i<index;i++){
            current = current.getNext();
        }

        current.setElement(element);
    }

    @Override
    public void add(T element) {
        LinearNode<T> nuevoNodo = new LinearNode<>(element);
        if(isEmpty()){
            front = rear = nuevoNodo;
        }else {
            rear.setNext(nuevoNodo);
            rear = nuevoNodo;
        }
        count++;
    }

    @Override
    public T get(int index) throws EmptyListException, OutOfRangeException {
        if(isEmpty())throw new EmptyListException();
        if(index < 0 || index >= size()) throw new OutOfRangeException(index);
        if(index == 0) return front.getElement();
        if(index == size()-1) return rear.getElement();

        LinearNode<T> current=front;

        for(int i=0; i<index;i++){
            current=current.getNext();
        }
        return current.getElement();
    }

    @Override
    public int indexOf(T element) throws EmptyListException, ElementNotFoundException {
        if(isEmpty())throw new EmptyListException();

        LinearNode<T> current=front;
        int index = 0;
        while(current!=null){
            if(element.equals(current.getElement())){
                return index;
            }
            current=current.getNext();
            index++;
        }
        throw new ElementNotFoundException(element.toString());
    }

    @Override
    public T remove(int index) throws EmptyListException, OutOfRangeException {
        if(isEmpty())throw new EmptyListException();
        if(index < 0 || index >= size()) throw new OutOfRangeException(index);

        if(index == 0 ){
            return removeFirst();
        }
        if(index == count-1){
            return removeLast();
        }

        LinearNode<T> current=front;
        LinearNode<T> previous=null;

        for(int i=0;i<index;i++){
            previous=current;
            current=current.getNext();
        }
        T copy=current.getElement();
        previous.setNext(current.getNext());
        count--;

        return copy;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(LinearNode<T> current = front; current != null; current = current.getNext()){
            sb.append(current.getElement()).append(" ");
        }
        return sb.toString().trim();
    }

    @Override
    public Iterator<T> iterator() {
        return new IndexedListIterator<>(front);
    }
}
