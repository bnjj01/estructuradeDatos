package com.ed.lewischase.tps.tp01;

import com.ed.lewischase.stack.EmptyStackException;
import com.ed.lewischase.stack.LinkedStack;

import java.util.Iterator;

public class Ejercicio07 {
    public static void main(String[] args) {
        LinkedStack<Integer> listaEnlazada01 = new LinkedStack<>();

        //1- push
        System.out.println("1- push");
        System.out.println("Agregando elementos...");
        listaEnlazada01.push(9);
        listaEnlazada01.push(20);
        listaEnlazada01.push(92);
        listaEnlazada01.push(12);
        listaEnlazada01.push(88);
        listaEnlazada01.push(65);

        System.out.println(listaEnlazada01);

        //2- pop
        System.out.println("2- push");
        System.out.println("Eliminar ultimo elemento");
        try{
            System.out.println("Ultimo elemento eliminado: "+ listaEnlazada01.pop());
        }catch (EmptyStackException ex){
            System.out.println("Error: " + ex.getMessage());
        }

        //3- peek
        System.out.println("3- peek");
        try{
            System.out.println("Elemento en la cima: " + listaEnlazada01.peek());
        }catch (EmptyStackException ex){
            System.out.println("Error: " + ex.getMessage());
        }

        //4- isEmpty
        System.out.println("4- isEmpty");
        System.out.println("La pila esta vacia?");
        if(listaEnlazada01.isEmpty()){
            System.out.println("Si.");
        }else System.out.println("No.");

        //5- size
        System.out.println("5- size");
        System.out.println("Cuantos elementos tiene la pila?");
        System.out.println(listaEnlazada01.size());

        //6- toString
        System.out.println("6- toString");
        System.out.println(listaEnlazada01.toString());

        //7- Iterator
        System.out.println("7- LinkedStackIterator");
        Iterator<Integer> iterator = listaEnlazada01.LinkedStackIterator();

        while(iterator.hasNext()){
            int elemento = iterator.next();
            System.out.println("- " + elemento);
        }
    }
}