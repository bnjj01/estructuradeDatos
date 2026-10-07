package com.ed.lewischase.tps.practica;

import com.ed.lewischase.list.ElementNotFoundException;
import com.ed.lewischase.list.EmptyListException;
import com.ed.lewischase.list.LinkedList;
import com.ed.lewischase.list.UnorderedList;
import com.ed.lewischase.queue.EmptyQueueException;
import com.ed.lewischase.queue.LinkedQueue;
import com.ed.lewischase.stack.EmptyStackException;
import com.ed.lewischase.stack.LinkedStack;

import java.rmi.server.UnicastRemoteObject;
import java.util.Iterator;

public class Lista {
/*
    public UnorderedList<Integer> interseccion(UnorderedList<Integer> L1,UnorderedList<Integer>  L2){
        UnorderedList<Integer> L3 = new UnorderedList<>();
        Iterator<Integer> iter1 = L1.iterator();
        Iterator<Integer> iter2 = L1.iterator();

        while(iter1.hasNext() && iter2.hasNext()){
            if(iter1.next().equals(iter2.next())){
                L3.addToRear(iter1.next());
            }
        }
        return L3;
    }*/

//    public UnorderedList<Integer> filtrarAprobados(UnorderedList<Integer> notas){
//        UnorderedList<Integer> aprobados = new UnorderedList<>();
//
//        Iterator<Integer> iter = notas.iterator();
//        while (iter.hasNext()){
//            int nota = iter.next();
//            if(nota>=6){
//                aprobados.addToRear(nota);
//            }
//        }
//
//        return aprobados;
//    }

    /* 1. Haciendo uso de lista, resuelva el siguiente problema: dos listas de enteros
     L1 y L2, se desea obtener una tercera lista L3 (con la informacion de la posicion
     donde se encuentra el elemento de L1 en L2), colocar el valor -1 si el elemento
     no se encuentra en L2*/

    public UnorderedList<Integer> infoPosicion(UnorderedList<Integer> L1, UnorderedList<Integer> L2){
        UnorderedList<Integer> L3 = new UnorderedList<>();

        Iterator<Integer> iterL1 = L1.iterator();
        Iterator<Integer> iterL2 = L2.iterator();
        int pos=0;
        while(iterL1.hasNext() && iterL2.hasNext()){
            int elementoL1 = iterL1.next();
            int elementoL2 = iterL2.next();

            if(elementoL1 == elementoL2){
                L3.addToRear(pos);
            }else{
                L3.addToRear(-1);
            }
            pos++;
        }
        return L3;
    }

    /*
    2. Dada una cadena de caracteres en una lista enlazada, implementa un metodo que
    determine si los parentesis en la cadena estan balanceados, Es decir, si cada
    parentesis de apertura tiene su corresponmdiente parentesis de cierre en el orden
    coirrecto(nota: utilizar una pila)
   */

    public boolean estaBalanceado(String cadena){
        LinkedStack<Character> pila = new LinkedStack<>();

        for (int i=0; i<cadena.length(); i++){
            char letra = cadena.charAt(i);
            if (letra=='('){
                if(pila.size()==1){
                    return false;
                }
                pila.push(letra);
            }
            if(letra==')'){
                try {
                    pila.pop();
                } catch (EmptyStackException e) {
                    return false;
                }
            }
        }
        return true;
    }

    public UnorderedList<Integer> eliminarObjetivo(UnorderedList<Integer> lista, int objetivo){
        while(lista.contains(objetivo)){
            try {
                lista.remove(objetivo);
            } catch (ElementNotFoundException | EmptyListException e) {
                throw new RuntimeException(e);
            }
        }
        return lista;
    }


    public UnorderedList<Integer> generarReporte(LinkedQueue<Integer> cinta){
        UnorderedList<Integer> reporte = new UnorderedList<>();
        LinkedStack<Integer> defectuosas = new LinkedStack<>();
        
        while (!cinta.isEmpty()){ // vacío la cinta.
            int pesoCaja;
            try{
                pesoCaja=cinta.dequeue();
            } catch (EmptyQueueException e) {
                throw new RuntimeException(e);
            }
            if((pesoCaja % 2) == 0){
                reporte.addToRear(pesoCaja);
            }else defectuosas.push(pesoCaja);
        }
        int longitudDefectuosos=defectuosas.size();
        for(int i=0;i<longitudDefectuosos;i++){ //doy vuelta la pila.
            try{
                defectuosas.push(defectuosas.pop());
            } catch (EmptyStackException e) {
                throw new RuntimeException(e);
            }
        }
        while(!defectuosas.isEmpty()){ //añado las cajas al reporte.
            try {
                reporte.addToRear(defectuosas.pop());
            } catch (EmptyStackException e) {
                throw new RuntimeException(e);
            }
        }
        return reporte;
    }

    public boolean balanceadoUniversal(String cadena){
        LinkedStack<Character> pila = new LinkedStack<>();
        for(int i=0; i<cadena.length();i++){
            char letra = cadena.charAt(i);
            try{
                if(letra=='('){
                    pila.push(letra);
                }else
            }
        }

        return true;
    }

    public static void main(String[] args) {
        LinkedList<Integer> L1 = new LinkedList<>();
        LinkedList<Integer> L2 = new LinkedList<>();

        Iterator<Integer> iter = L1.iterator();
//        while(){
//            LinkedList<Integer> L3 = new LinkedList<>();
//        }
    }

}
