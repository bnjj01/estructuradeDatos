package com.ed.lewischase.set;

import java.util.Iterator;

public interface SetADT<T> {

    /**
     * Añade un elemento a este conjunto, ignorando los duplicados.
     * 
     * @param element Nuevo elemento que sera agregado a el conjuntpo.
     */

    public void add(T element);

    // Elimina y devuelve un elemento aleatorio a este conjunto.
    public T removeRandom() throws EmptySetException;

    // Elimina y devuelve un elemento especifico de este conjunto.
    public T remove(T target) throws EmptySetException, NoSuchElementException;

    // Devuelve la unión de este conjunto y del parámetro.
    public SetADT<T> union(SetADT<T> set);

    // Devuelve true si el conjunto contiene el parámetro.
    public boolean contains(T target);

    // Devuelve tru si este conjunto y el parámetro contienen exaxtamente
    // los mismos elementos.
    public boolean equals(SetADT<T> set) throws EmptySetException, NoSuchElementException;

    // Devuelve true si este conjunto no contiene ningún elemento.
    public boolean isEmpty();

    // Devuelve el número de elementos de este conjunto.
    public int size();

    // Devuelve un iterador para los elementos de este conjunto.
    public Iterator<T> iterator();

    // Devuelve un representación de este cojunto en forma de cadena de caracteres.
    public String toString();

    /**
     * Devuelve un nuevo conjunto que es la diferencia entre el conjunto actual y el conjunto pasado como parámetro.
     */
    public SetADT<T> difference (SetADT<T> other);

    /**
     * Devuelve un nuevo conjunto que es la interseccion entre el conjunto actual y el conjunto pasado como parametro.
     */
    public SetADT<T> intersection (SetADT<T> other);
}
