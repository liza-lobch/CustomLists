package org.example;

import java.util.Arrays;


public class CustomArrayList<T> implements CustomList<T> {

    /**
     * Ёмкость по умолчанию
     */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * Массив, который будет хранить элементы создаваемого списка
     */
    private T[] list;

    /**
     * Количество элементов создаваемого списка
     */
    private int size;


    /**
     * Конструктор по умолчанию, создаёт список с ёмкостью по умолчанию
     */
    @SuppressWarnings("unchecked")
    public CustomArrayList() {
        list = (T[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    /**
     * Конструктор с указанием начальной ёмкости
     *
     * @param initialCapacity - начальная ёмкость
     * @throws IllegalArgumentException, если указанная ёмкость некорректна, то есть отрицательная
     */
    @SuppressWarnings("unchecked")
    public CustomArrayList(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
        } else {
            list = (T[]) new Object[initialCapacity];
            size = 0;
        }
    }

    /**
     * @return возвращает текущий размер создаваемого списка
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Проверяет, пуст ли созданный список
     *
     * @return возвращает true, если список пуст, иначе false
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Добавление элемента.
     * Проверяется заполненность ёмкости. Если она заполнена, вызывается метод ensureCapacity(), добавляющий к ёмкости capacity/2 + 1.
     *
     * @param e - значение добавляемого элемента
     * @return - возвращает true при успешном добавлении элемента
     */
    @Override
    public boolean add(T e) {
        ensureCapacity();
        list[size++] = e;
        return true;
    }

    /**
     * Проверяется, достаточно ли ёмкости. Если нет, вызывается метод grow()
     */
    private void ensureCapacity() {
        if (size == list.length) {
            grow();
        }
    }

    /**
     * Увеличение ёмкости. Создаётся новый массив бОльшей ёмкости и в него
     * копируются элементы старого массива. Старый массив удаляется сборщиком мусора.
     */
    @SuppressWarnings("unchecked")
    private void grow() {
        int oldCapacity = list.length;
        T[] oldList = list;

        int newCapacity = oldCapacity + (oldCapacity >> 1) + 1;
        list = (T[]) new Object[newCapacity];

        for (int i = 0; i < oldCapacity; i++) {
            list[i] = oldList[i];
        }
    }

    /**
     * Добавление элемента по индексу.
     * Проверяется, достаточно ли ёмкости, если нет - добавляем ёмкость.
     * Сдвигаем все элементы, которые находятся правее указанного индекса, на позицию вправо,
     * вставляем значение в указанный индекс, размер списка увеличивается на один.
     *
     * @param index   - на какое место в списке хотим поставить значение
     * @param element - добавляемое значение
     * @throws IndexOutOfBoundsException, если указан некорректный индекс (отрицательный или больше размера списка)
     */
    @Override
    public void add(int index, T element) {
        checkIndex(index, size);
        ensureCapacity();
        for (int i = size; i > index; i--) {
            list[i] = list[i - 1];
        }
        list[index] = element;
        size++;
    }

    /**
     * Удаление элемента по индексу.
     * Проверяется указанный индекс, чтоб был не отрицательный и меньше размера списка.
     * Затем элементы, которые находятся правее указанного индекса, сдвигаются влево.
     * Последний элемент удаляется и размер уменьшается на единицу.
     *
     * @param index - индекс элемента, который хотим удалить
     * @return - возвращает значение удалённого элемента
     * @throws IndexOutOfBoundsException, если указан некорректный индекс (отрицательный или больше размера списка)
     */
    @Override
    public T remove(int index) {
        checkIndex(index, size - 1);
        T removableElement = list[index];
        for (int i = index; i < size - 1; i++) {
            list[i] = list[i + 1];
        }
        list[--size] = null;
        return removableElement;
    }

    /**
     * Удаление элемента по значению. Удаляется первое вхождение данного значения.
     * Находится index такого элемента в списке при помощи метода findItemIndex(e).
     * Если такой элемент не нашёлся, возвращается false, иначе true и элемент удаляется методом remove(index)
     *
     * @param e - значение элемента, который хотим удалить
     * @return - true - если элемент нашли и удалили, иначе false
     */
    @Override
    public boolean remove(T e) {
        int index = findItemIndex(e);
        if (index == -1) {
            return false;
        } else {
            remove(index);
            return true;
        }
    }

    /**
     * Находит элемент в списке.
     * Если нашёлся, возвращаем index, иначе -1
     *
     * @param item - искомый элемент списка
     * @return - индекс искомого элемента, иначе -1
     */
    private int findItemIndex(T item) {
        for (int i = 0; i < size; i++) {
            if (item.equals(list[i])) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Получение элемента по индексу.
     * Проверяется корректность индекса и затем возвращается искомый элемент.
     *
     * @param index - индекс искомого элемента
     * @return - возвращается искомый элемент
     */
    @Override
    public T get(int index) {
        checkIndex(index, size - 1);
        return list[index];
    }

    /**
     * Замена элемента в указанной позиции index.
     * Проверяется корректность индекса, меняется значение по текущему индексу и возвращается старое значение.
     *
     * @param index   - индекс заменяемого элемента
     * @param element - значение, на которое нужно заменить
     * @return - возвращает старое значение элемента
     */
    @Override
    public T set(int index, T element) {
        checkIndex(index, size - 1);
        T oldValue = list[index];
        list[index] = element;
        return oldValue;
    }

    /**
     * Возвращение подсписка.
     * Проверка корректности индексов, не отрицательные, не больше размера списка, fromIndex < toIndex.
     * Создаёт новый список и через for копирует в него элементы списка.
     *
     * @param fromIndex - с какого индекса копировать элементы.
     * @param toIndex   - до какого индекса копировать элементы.
     * @return - возвращает новый список CustomList<T>
     */
    @Override
    public CustomList<T> subList(int fromIndex, int toIndex) {
        if (fromIndex < 0 || toIndex > size || fromIndex > toIndex) {
            throw new IndexOutOfBoundsException("fromIndex = " + fromIndex + ", toIndex = " + toIndex + ", size = " + size);
        }
        CustomList<T> subList = new CustomArrayList<>(toIndex - fromIndex);
        for (int i = fromIndex; i < toIndex; i++) {
            subList.add(list[i]);
        }
        return subList;
    }

    /**
     * Переопределение метода toString. Возвращает элементы списка через запятую в квадратных скобках.
     *
     * @return - массив значений списка
     */
    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOf(list, size));
    }

    /**
     * Проверка корректности индекса на отрицательность или больше размера списка
     *
     * @param index - искомый индекс списка
     * @param size  - размер списка
     */
    private void checkIndex(int index, int size) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Illegal Index: " + index);
        }
    }

    //*************************************
    // Метод не для прода. Проверка ёмкости
    public void dev_displayArray() {
        for (T item : list) {
            System.out.print(item + " ");
        }
        System.out.println();
    }
}
