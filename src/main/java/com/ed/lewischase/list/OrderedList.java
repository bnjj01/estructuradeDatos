package com.ed.lewischase.list;
import com.ed.lewischase.LinkedNode.LinearNode;
import java.util.Iterator;

public class OrderedList<T> implements OrderedListADT<T> {
    LinearNode<T> front, rear;
    int count;

    public OrderedList() {
        front = rear = null;
        count = 0;
    }

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

        while(current != null){
            if(elemComp.compareTo(current.getElement()) == 0){
                return true;
            }
            current=current.getNext();
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
        if(count == 0) return true;
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new OrderedListIterator<>(front);
    }

    @Override
    public T last() throws EmptyListException {
        if(isEmpty()) throw new EmptyListException();
        return rear.getElement();
    }

    @Override
    public T remove(T element) throws EmptyListException, ElementNotFoundException {
        if(isEmpty()) throw new EmptyListException();
        Comparable<T> elemComp = (Comparable<T>)element;
        LinearNode<T> current = front;
        LinearNode<T> previous = null;

        while(current != null && elemComp.compareTo(current.getElement()) != 0){
            previous = current;
            current = current.getNext();
        }

        if(current == null){throw new ElementNotFoundException(element.toString());}

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
        T copy = front.getElement();
        if(count == 1){
            front = rear = null;
        }else{
            front = front.getNext();
        }
        count--;
        return copy;
    }

    @Override
    public T removeLast() throws EmptyListException {
        if(isEmpty())throw new EmptyListException();
        T copy = rear.getElement();
        if(count == 1){
            front = rear= null;
            count--;
            return copy;
        }

        LinearNode<T> current = front;
        LinearNode<T> previous = null;

        while(current != rear){
            previous = current;
            current = current.getNext();
        }
        
        rear = previous;
        rear.setNext(null);
        count--;
        return copy;
    }

    @Override
    public int size() {
        return count;
    }
    
}
