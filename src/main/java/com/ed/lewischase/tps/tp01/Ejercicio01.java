package com.ed.lewischase.tps.tp01;

import com.ed.lewischase.set.ArraySet;
import com.ed.lewischase.set.EmptySetException;
import com.ed.lewischase.set.NoSuchElementException;

public class Ejercicio01 {
    public static void main(String[] args) {
        ArraySet<Integer> conjunto01 = new ArraySet<>();
        ArraySet<Integer> conjunto02 = new ArraySet<>();
        ArraySet<Integer> conjunto03 = new ArraySet<>();

        // 1. add se agregan elementos al conjunto
        System.out.println("1-");
        conjunto01.add(19);
        conjunto01.add(11);
        conjunto01.add(23);
        conjunto01.add(9);
        conjunto01.add(8);

        System.out.println(conjunto01);

        // 2. removeRandom se elimina un elemento aleatorio del conjunto
        System.out.println("2-");
        System.out.println("--Eliminar elemento ramdon--");
        try {
            System.out.println("Elemento eliminado: " + conjunto01.removeRandom());
        } catch (EmptySetException ex) {
            System.out.println("Error: " + ex.getMessage());
        }

        System.out.println(conjunto01);

        // 3. remove, se elimina un elemento especifico del conjunto.
        System.out.println("3-");
        try {
            System.out.println("Elemento eliminado: " + conjunto01.remove(19));
        } catch (EmptySetException | NoSuchElementException ex) {
            System.out.println("Error: " + ex.getMessage());
        }

        System.out.println(conjunto01);

        // 4. union, devuelve la union de dos conjuntos
        conjunto02.add(900);
        conjunto02.add(923);
        conjunto02.add(968);
        System.out.println("4-");
        System.out.println(conjunto01.union(conjunto02));

        // 5. contains, verificar si contiene un elemento especifico
        System.out.println("El conjunto contiene el elemento 11? " + conjunto01.contains(11));

        // 6. equals, devuelve true si dos conjuntos tienen los mismos elementos
        System.out.println("6- equals");
        try{
            System.out.println(conjunto01.equals(conjunto02));
        }catch ( EmptySetException | NoSuchElementException ex){
            System.out.println("Error: " + ex.getMessage());
        }

        //7. addAll agrega todos los elementos de un conjunto a otro
        System.out.println("7- addAll");
        conjunto02.add(546);
        conjunto02.add(678);
        conjunto02.add(234);
        conjunto01.addAll(conjunto03);

        //8. isEmpty
        System.out.println("8- isEmpty");
        System.out.println(conjunto01.isEmpty());

        //9. size, devuelve el numero de elementos del conjunto.
        System.out.println("9- size");
        System.out.println(conjunto01.size());

        //10. iterator
        conjunto01.iterator();

        //11.toString
        System.out.println("11-");
        System.out.println(conjunto02.toString());

        //12.difference
        conjunto01.add(20);
        conjunto01.add(19);
        conjunto01.add(11);
        conjunto01.add(80);
        conjunto02.add(20);
        conjunto02.add(11);
        conjunto02.add(80);
        System.out.println("12- difference");
        System.out.println(conjunto01.difference(conjunto02));

    }
}
