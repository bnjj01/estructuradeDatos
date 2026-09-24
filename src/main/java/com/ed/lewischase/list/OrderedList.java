package com.ed.lewischase.list;
import com.ed.lewischase.LinkedNode.LinearNode;

public class OrderedList<T> extends LinkedList<T> implements OrderedListADT<T> {
    public OrderedList() {
        super();
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

    public void removeDuplicates() {
        if (isEmpty() || front == rear) {
            return;
        }
        LinearNode<T> current = front;
        while (current != null && current.getNext() != null) {

            LinearNode<T> siguiente = current.getNext();

            if (current.getElement().equals(siguiente.getElement())) {
                current.setNext(siguiente.getNext());
                if (siguiente == rear) {
                    rear = current;
                }
                count--;

            } else {
                current = current.getNext();
            }
        }
    }

}
