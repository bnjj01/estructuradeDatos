package com.ed.lewischase.list;

public class Probador {
    public static void main(String[] args) {
        LinkedList<Integer> lista = new LinkedList<>();
        try {
            lista.first();
        } catch (EmptyListException e) {
            System.out.println("Error: "+e.getMessage());
        }
    }
}