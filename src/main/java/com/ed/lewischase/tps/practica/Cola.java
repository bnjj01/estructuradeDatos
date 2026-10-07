package com.ed.lewischase.tps.practica;

import com.ed.lewischase.queue.EmptyQueueException;
import com.ed.lewischase.queue.LinkedQueue;
import com.ed.lewischase.stack.LinkedStack;

public class Cola {

    public LinkedQueue<Integer> IntercalarMitades(LinkedQueue<Integer> cola){
        LinkedQueue<Integer> colaAuxiliar = new LinkedQueue<>();
        int mitadCola=cola.size()/2;
        for(int i=0; i<mitadCola;i++){
            try {
                colaAuxiliar.enqueue(cola.dequeue());
            }catch (Exception e){
                System.out.println("Error: "+e.getMessage());
            }
        }

        while(!colaAuxiliar.isEmpty()){
            try{
                cola.enqueue(colaAuxiliar.dequeue());
                cola.enqueue(cola.dequeue());
            } catch (EmptyQueueException e) {
                throw new RuntimeException(e);
            }
        }
        return cola;
    }

    public LinkedQueue<Integer> invertirElementosCola(LinkedQueue<Integer> col,int k){
        LinkedStack<Integer> pila = new LinkedStack<>();
        int count=col.size();
        for (int i=0; i<k; i++){
            try {
                pila.push(col.dequeue());
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }


        while(!pila.isEmpty()){
            try{
                col.enqueue(pila.pop());
            }catch (Exception e){
                System.out.println("Error: "+e.getMessage());
            }
        }

        for(int i=0; i<count-k;i++){
            try{
                col.enqueue(col.dequeue());
            }catch (Exception e){
                System.out.println("Error: "+e.getMessage());
            }
        }
        return col;
    }
    public static void main(String[] args) {
        LinkedQueue<Integer> cola1 = new LinkedQueue<>();

        cola1.enqueue(34);
        cola1.enqueue(67);
        cola1.enqueue(54);
        cola1.enqueue(78);
        cola1.enqueue(56);
        cola1.enqueue(12);

        //cola1.dequeue(12);

    }
}
