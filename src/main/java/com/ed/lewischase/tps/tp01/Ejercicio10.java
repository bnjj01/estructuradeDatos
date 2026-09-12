package com.ed.lewischase.tps.tp01;

import com.ed.lewischase.queue.LinkedQueue;
import com.ed.lewischase.queue.EmptyQueueException;

import java.util.Scanner;
import java.util.Stack;

public class Ejercicio10 {
    public static boolean esPalindromo(String cadena) {
        String limpia = cadena.replaceAll("\\s+", "").toLowerCase();

        LinkedQueue<Character> cola = new LinkedQueue<>();
        Stack<Character> pila = new Stack<>();

        for (int i = 0; i < limpia.length(); i++) {
            char letra = limpia.charAt(i);
            cola.enqueue(letra);
            pila.push(letra);
        }

        try {
            while (!cola.isEmpty()) {
                char letraCola = cola.dequeue();
                char letraPila = pila.pop();

                if (letraCola != letraPila) {
                    return false;
                }
            }
        } catch (EmptyQueueException e) {
            System.out.println("Error al leer la cola: " + e.getMessage());
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println("--- Probador de Palíndromos ---");

        String[] pruebas = {
                "neuquen",
                "reconocer",
                "Hola",
                "Estructuras de datos"
        };

        for (String palabra : pruebas) {
            if (esPalindromo(palabra)) {
                System.out.println("Es palíndromo: '" + palabra + "'");
            } else {
                System.out.println("No es palíndromo: '" + palabra + "'");
            }
        }
    }
}
