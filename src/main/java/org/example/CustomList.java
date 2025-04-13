package org.example;


public interface CustomList<T> {

    int size();

    boolean isEmpty();


    boolean add(T e);

    void add(int index, T element);


    boolean remove(T e);

    T remove(int index);


    T get(int index);

    T set(int index, T element);


    CustomList<T> subList(int fromIndex, int toIndex);

}
