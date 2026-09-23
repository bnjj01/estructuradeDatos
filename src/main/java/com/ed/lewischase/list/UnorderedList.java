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
        // Si está vacía o tiene un solo elemento, no hay duplicados posibles
        if (isEmpty() || front == rear) {
            return;
        }

        DoubleNode<T> current = front;

        // Bucle externo: Elige un elemento para comparar
        while (current != null) {

            DoubleNode<T> runner = current.getNext(); // El corredor empieza un paso adelante

            // Bucle interno: Busca copias en el resto de la lista
            while (runner != null) {

                // Guardamos el siguiente nodo ANTES de borrar el runner, para no perder el rastro
                DoubleNode<T> nextNode = runner.getNext();

                if (runner.getElement().equals(current.getElement())) {

                    // ¡ENCONTRAMOS UN DUPLICADO! Hay que eliminar el nodo 'runner'.
                    // Recuerda que el 'runner' NUNCA será el 'front' porque siempre empieza adelante de current.

                    if (runner == rear) {
                        // TODO: Lógica si el duplicado es el último elemento (Mover rear y cortar flecha next)

                    } else {
                        // TODO: Lógica si el duplicado está en el medio (Conectar el de atrás con el de adelante a 2 manos)

                    }
                    count--; // Restamos al contador porque eliminamos uno
                }

                // Avanzamos el corredor al siguiente nodo que habíamos guardado
                runner = nextNode;
            }

            // Cuando el corredor revisa todo, current avanza un paso para elegir el siguiente elemento
            current = current.getNext();
        }
    }
}