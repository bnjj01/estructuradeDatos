package com.ed.lewischase.tps.tp01;
import java.lang.reflect.Array;

import com.ed.lewischase.set.ArraySet;
import com.ed.lewischase.set.EmptySetException;
import com.ed.lewischase.set.NoSuchElementException;

public class Ejercicio01 {
    public static void main(String[] args) {
        ArraySet<Integer> conjunto01 = new ArraySet<>();
        ArraySet<Integer> conjunto02 = new ArraySet<>();
        

        
        //1. add se agregan elementos al conjunto
        conjunto01.add(19);
        conjunto01.add(11);
        conjunto01.add(23);
        conjunto01.add(9);
        conjunto01.add(8);
        
        System.out.println(conjunto01);
        
        //2. removeRandom se elimina un elemento aleatorio del conjunto
        System.out.println("Eliminar elemento ramdon: ");
        try{
            System.out.println("Elemento eliminado: " + conjunto01.removeRandom());
        }catch(EmptySetException ex){
            System.out.println("Error: " + ex.getMessage());
        }

        System.out.println(conjunto01);

        //3. remove, se elimina un elemento especifico del conjunto.
        try{
            System.out.println("Elemento eliminado: " + conjunto01.remove(19));
        }catch(EmptySetException | NoSuchElementException ex){
            System.out.println("Error: " + ex.getMessage());
        }
        System.out.println(conjunto01);

        conjunto02.add(900);
        conjunto02.add(923);
        conjunto02.add(968);





        System.out.println(conjunto01);

        System.out.println("El conjunto tiene " + conjunto01.size() + " elementos");

        //union de conjunto 01 y 02
        System.out.println(conjunto01.union(conjunto02));

        //verificar si contiene un elemento especifico
        System.out.println("El conjunto contiene el elemento 11? " + conjunto01.contains(11));

        if(conjunto01.contains(11)){
            try{
                conjunto01.remove(11);
            }catch(EmptySetException | NoSuchElementException ex){
                System.out.println("Error: " + ex.getMessage());
            }
        }

        System.out.println(conjunto01);

    }
}
