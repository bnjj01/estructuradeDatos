package com.ed.lewischase.tps.practica;

import com.ed.lewischase.queue.EmptyQueueException;
import com.ed.lewischase.queue.LinkedQueue;
import com.ed.lewischase.stack.EmptyStackException;
import com.ed.lewischase.stack.LinkedStack;

import java.util.Iterator;

public class Pila {
    /**
    public boolean detectorPalindromo(String palabra) {
        LinkedStack<Character> pila = new LinkedStack<>();
        LinkedQueue<Character> cola = new LinkedQueue<>();

        for (int i = 0; i < palabra.length(); i++) {
            char letra = palabra.charAt(i);
            pila.push(letra);
            cola.enqueue(letra);
        }

        while (!cola.isEmpty()) {
            try{
                if (pila.pop() != cola.dequeue()) {
                    return false;
                }
            }catch (Exception e){
                System.out.println("Error: "+ e.getMessage());
            }
        }
        return true;
    }**/
    public static void main(String[] args) {
        /**
        LinkedStack<Integer> pila1 = new LinkedStack<>();

        pila1.push(12);
        pila1.push(2);
        pila1.push(43);
        pila1.push(32);
        pila1.push(65);
        pila1.push(6);

        Iterator<Integer> iterador = pila1.LinkedStackIterator();

        while(iterador.hasNext())
            System.out.println("Elementos de la pila: "+iterador.next());

        System.out.println("Tamaño de la pila: "+pila1.size());

        System.out.println(pila1.toString());
        try{
            Integer eliminado = pila1.pop();
            System.out.println("Elemento Eliminado: "+ eliminado);
        } catch (EmptyStackException e) {
            throw new RuntimeException(e);
        }
        **/

        /** Invertir una Palabra
        LinkedStack<Character> palabraInvertida = new LinkedStack<>();

        String palabra = "EXAMEN";
        for (int i=0; i < palabra.length(); i++){
            char letra = palabra.charAt(i);
            palabraInvertida.push(letra);
        }

        String palabraInver = "";
        System.out.println(palabraInvertida.toString());
        while(!palabraInvertida.isEmpty()){
            try {
                palabraInver = palabraInver+palabraInvertida.pop();
            }catch (EmptyStackException e){
                System.out.println("Error: "+e.getMessage());
            }
        }
        System.out.println(palabraInver);
        **/

    }
}
