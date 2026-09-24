package com.ed.lewischase.list;

import com.ed.lewischase.LinkedNode.DoubleNode;

public class UnorderedList<T> extends DoubleLinkedList<T> implements UnorderedListADT<T>{
    public UnorderedList(){
        super();
    }

    @Override
    public void addToFront(T element) {
        DoubleNode<T> newNode = new DoubleNode<>(element);
        if(isEmpty()){
            front = rear = newNode;
        }else{
            newNode.setNext(front);
            front.setPrevious(newNode);
            front=newNode;
        }
        count++;
    }

    @Override
    public void addToRear(T element) {
        DoubleNode<T> newNode = new DoubleNode<>(element);
        if(isEmpty()){
            front = rear = newNode;
        }else{
            rear.setNext(newNode);
            newNode.setPrevious(rear);
            rear=newNode;
        }
        count++;
    }

    @Override
    public void addAfter(T element, T target) throws ElementNotFoundException, EmptyListException {
        if(isEmpty())throw new EmptyListException();
        DoubleNode<T> newNode = new DoubleNode<>(element);

        DoubleNode<T> current=front;
        while(current!=null){
            if(current.getElement().equals(target)){
                newNode.setNext(current.getNext());
                newNode.setPrevious(current);
                if(current == rear){
                    rear=newNode;
                }else{
                    current.getNext().setPrevious(newNode);
                }
                current.setNext(newNode);
                count++;
                return;
            }
            current = current.getNext();
        }
        throw new ElementNotFoundException(target.toString());
    }
    public void removeDuplicates() {
        if (isEmpty() || front == rear) {
            return;
        }
        DoubleNode<T> current = front;
        while (current != null) {
            DoubleNode<T> runner = current.getNext();
            while (runner != null) {
                DoubleNode<T> nextNode = runner.getNext();
                if (runner.getElement().equals(current.getElement())) {
                    if (runner == rear) {
                        rear=runner.getPrevious();
                        rear.setNext(null);
                    } else {
                        DoubleNode<T> aux;
                        aux= runner.getPrevious();
                        aux.setNext(runner.getNext());
                        aux=runner.getNext();
                        aux.setPrevious(runner.getPrevious());
                    }
                    count--;
                }
                runner = nextNode;
            }
            current = current.getNext();
        }
    }
}