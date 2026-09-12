package com.ed.lewischase.tps.tp01;

import com.ed.lewischase.queue.EmptyQueueException;
import com.ed.lewischase.queue.LinkedQueue;
import java.util.Iterator;


public class Ejercicio08 {
    public static void main(String[] args) {
        LinkedQueue<Integer> queque = new LinkedQueue<>();

        //1- enqueue
        System.out.println("1- enqueque, añadir elementos.");
        queque.enqueue(34);
        queque.enqueue(2);
        queque.enqueue(89);
        queque.enqueue(43);
        queque.enqueue(67);


        //2- dequeue
        System.out.println("2- dequeue, eliminar elemento");
        try{
            System.out.println("Elemento eliminado: "+queque.dequeue());
        }catch (EmptyQueueException ex){
            System.out.println("Error: " + ex.getMessage());
        }

        //3- first
        System.out.println("3- first");
        try {
            System.out.println("Cual es el primer elemento de la cola: " + queque.first());
        }catch (EmptyQueueException ex){
            System.out.println("Error: " + ex.getMessage());
        }

        //4- isEmpty
        System.out.println("4- isEmpty");
        System.out.println("La cola esta vacia?");
        if (queque.isEmpty()){
            System.out.println("Si.");
        }else System.out.println("No.");

        //5- size
        System.out.println("5- size");
        System.out.println("Cuantos elementos tiene la cola?");
        System.out.println(queque.size());

        //6- toString
        System.out.println("6- toString");
        System.out.println("Imprimir la cola");
        System.out.println(queque.toString());

        //7- iterador
        System.out.println("7- LinkedQueueIterator");
        Iterator<Integer> iterator = queque.LinkedQueueIterator();
        while(iterator.hasNext()){
            int elemento = iterator.next();
            System.out.println("- " + elemento);
        }

    }
}
