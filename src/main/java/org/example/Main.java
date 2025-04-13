package org.example;

public class Main {
    public static void main(String[] args) {

        /*        ArrayList         */
//        checkConstructorsArrayList();
//        addToTheEndCheckArrayList();
//        addToIndexArrayList();
//        removeByIndexArrayList();
//        removeByValueArrayList();
//        setAndGetCheckArrayList();
//        subListCheckArrayList();

        /*        LinkedList         */
//        checkConstructorLinkedList();
//        addToTheEndLinkedList();
//        addToIndexLinkedList();
//        removeByValueLinkedList();
//        removeByIndexLinkedList();
//        setAndGetCheckLinkedList();
//        subListCheckLinkedList();
    }

    /*        ArrayList         */
    public static void checkConstructorsArrayList() {
        System.out.println("Проверка конструкторов");

//         Конструктор по умолчанию, без параметров
        CustomArrayList<String> list = new CustomArrayList<>();
        list.dev_displayArray();

//         Конструктор с указанием начальной ёмкости
//         CustomArrayList<String> list1 = new CustomArrayList<>(-5);
        CustomArrayList<String> list2 = new CustomArrayList<>(0);
        CustomArrayList<String> list3 = new CustomArrayList<>(5);
        CustomArrayList<String> list4 = new CustomArrayList<>(15);
        list2.dev_displayArray();
        list3.dev_displayArray();
        list4.dev_displayArray();
    }

    public static void addToTheEndCheckArrayList() {
        System.out.println("Проверка добавления элемента в конец списка");

        // Проверка добавления при начальной ёмкости по умолчанию, 0, 5
        CustomArrayList<String> list = new CustomArrayList<>();
//        CustomArrayList<String> list = new CustomArrayList<>(0);
//        CustomArrayList<String> list = new CustomArrayList<>(5);

        list.add("0");
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");
        list.add("7");
        list.add("8");
        list.add("9");
        list.add("10");
        list.add("11");

        list.dev_displayArray();
        System.out.println(list.size());
    }

    public static void addToIndexArrayList() {
        System.out.println("Проверка добавления элемента по индексу");

        // Проверка добавления при начальной ёмкости по умолчанию, 0, 5
        CustomArrayList<String> list = new CustomArrayList<>();
//        CustomArrayList<String> list = new CustomArrayList<>(0);
//        CustomArrayList<String> list = new CustomArrayList<>(5);

        list.add(0, "0");
        list.add(1, "1");
        list.add(2, "2");

        list.add("3");
        list.add("4");

        list.add(0, "5");
        list.add(2, "6");

        list.add("7");
        list.add("8");
        list.add("9");
        list.add("10");

        list.add(5, "11");

        list.dev_displayArray();
        System.out.println(list.size());
    }

    public static void removeByIndexArrayList() {
        System.out.println("Проверка удаления элемента по индексу");

        CustomArrayList<String> list = new CustomArrayList<>();
        list.add("0");
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");

        list.remove(2);
        list.remove(3);

        list.dev_displayArray();
        System.out.println(list.size());
    }

    public static void removeByValueArrayList() {
        System.out.println("Проверка удаления элемента по значению");

        CustomArrayList<String> list = new CustomArrayList<>();

        list.add("0");
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");

        System.out.println(list.remove("3"));
        System.out.println(list.remove("100"));

        list.dev_displayArray();
        System.out.println(list.size());
    }

    public static void setAndGetCheckArrayList() {
        System.out.println("Проверка геттера и сеттера");

        CustomArrayList<String> list = new CustomArrayList<>();

        list.add("0");
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");

        System.out.println(list.get(0));
        System.out.println(list.get(4));
        System.out.println(list.set(2, "5"));
        System.out.println(list.get(2));

        list.dev_displayArray();
        System.out.println(list.size());
    }

    public static void subListCheckArrayList() {
        System.out.println("Проверка возвращения подсписка");

        CustomArrayList<String> list = new CustomArrayList<>();

        list.add("0");
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");

        System.out.println(list);

        System.out.println(list.subList(0, 0));
        System.out.println(list.subList(0, 1));
        System.out.println(list.subList(0, 2));
        System.out.println(list.subList(1, 6));
    }


    /*        LinkedList         */
    public static void checkConstructorLinkedList() {
        System.out.println("Проверка конструктора");

        CustomLinkedList<String> list = new CustomLinkedList<>();
        System.out.println(list);
        System.out.println(list.size());
    }

    public static void addToTheEndLinkedList() {
        System.out.println("Проверка добавления элемента в конец списка");

        CustomLinkedList<String> list = new CustomLinkedList<>();

        list.add("0");
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");

        System.out.println(list);
        System.out.println(list.size());
    }

    public static void addToIndexLinkedList() {
        System.out.println("Проверка добавления элемента по индексу");

        CustomLinkedList<Object> list = new CustomLinkedList<>();

        list.add(0, "0");
        list.add(1, "1");
        list.add(2, "2");

        list.add("3");
        list.add("4");

        list.add(0, "5");
        list.add(2, "6");

        list.add("7");
        list.add("8");
        list.add("9");
        list.add("10");

        list.add(5, "11");

        System.out.println(list);
        System.out.println(list.size());
    }

    public static void removeByValueLinkedList() {
        System.out.println("Проверка удаления элемента по значению");

        CustomLinkedList<String> list = new CustomLinkedList<>();

        list.add("0");
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");

        System.out.println(list.remove("3"));
        System.out.println(list);

        System.out.println(list.remove("100"));
        System.out.println(list);

        System.out.println(list.size());
    }

    public static void removeByIndexLinkedList() {
        System.out.println("Проверка удаления элемента по индексу");

        CustomLinkedList<String> list = new CustomLinkedList<>();
        list.add("0");
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");

        list.remove(2);
        System.out.println(list);

        list.remove(3);
        System.out.println(list);

        System.out.println(list.size());
    }

    public static void setAndGetCheckLinkedList() {
        System.out.println("Проверка геттера и сеттера");

        CustomLinkedList<String> list = new CustomLinkedList<>();

        list.add("0");
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");

        System.out.println(list.get(0));
        System.out.println(list.get(4));
        System.out.println(list.set(2, "5"));
        System.out.println(list.get(2));

        System.out.println(list.size());
    }

    public static void subListCheckLinkedList() {
        System.out.println("Проверка возвращения подсписка");

        CustomLinkedList<String> list = new CustomLinkedList<>();

        list.add("0");
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");

        System.out.println(list);

        System.out.println(list.subList(0, 0));
        System.out.println(list.subList(0, 1));
        System.out.println(list.subList(0, 2));
        System.out.println(list.subList(1, 6));
    }

}