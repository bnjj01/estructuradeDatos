package com.ed.lewischase.tps.tp02;

import com.ed.lewischase.stack.EmptyStackException;
import com.ed.lewischase.stack.LinkedStack;

import java.util.Iterator;

public class Ejercicio09 {
    public static void main(String[] args) {
        LinkedStack<Integer> pila = new LinkedStack<>();

        pila.push(3);
        pila.push(20);
        pila.push(10);

        Iterator<Integer> iterador = (Iterator<Integer>) pila.LinkedStackIterator();

        System.out.println("ELEMENTOS DE LA PILA");
        while (iterador.hasNext()){
            System.out.println(iterador.next().toString());
        }

        System.out.println("TAMAÑO DE LA PILA: "+ pila.size());
        try {
            System.out.println("TOPE DE LA PILA: "+pila.peek().toString());
        } catch (EmptyStackException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
