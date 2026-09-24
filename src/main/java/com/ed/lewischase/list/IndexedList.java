package com.ed.lewischase.list;

import com.ed.lewischase.LinkedNode.LinearNode;

public class IndexedList<T> extends LinkedList<T> implements IndexedListADT<T>{
    public IndexedList() {
        super();
    }

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
}
