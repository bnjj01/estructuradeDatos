package com.ed.lewischase.queue;

public interface QueueADT<T> {
    //Añade un elemento al final de la cola
    public void enqueue(T element);
    //Elimina y devuelve el elemento situado al principio de la cola
    public T dequeue() throws EmptyQueueException;
    //Devuelve, sin eliminarlo, el elemento situado al principio de la cola
    public T first() throws EmptyQueueException;
    //Devuelve true si la cola no contiene ningún elemento
    public boolean isEmpty();
    //Devuelve el número de elementos de la cola
    public int size();
    //Devuelve una representación de la cola en forma de cadena de caracteres
    public String toString();
}
