package org.example;


public class CustomLinkedList<T> implements CustomList<T> {

    /**
     * Первый элемент списка
     */
    private Node<T> head;

    /**
     * Последний элемент списка
     */
    private Node<T> tail;

    /**
     * Количество элементов создаваемого списка
     */
    private int size;

    /**
     * Узел списка. В нём содержится:
     * значение узла,
     * следующий элемент списка,
     * предыдущий элемент списка.
     */
    private static class Node<T> {
        T data;
        Node<T> next;
        Node<T> prev;

        Node(T data) {
            this.data = data;
        }
    }

    /**
     * Конструктор.
     * Пока элементов нет, head и tail указывают на null, количество элементов равно нулю
     */
    public CustomLinkedList() {
        head = null;
        tail = null;
        size = 0;
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
     * Добавление элемента в конец списка.
     * При каждом добавлении объекта в список создается один новый узел.
     * В случае с добавлением первого элемента создается узел, у которого предыдущий и следующий элементы отсутствуют, т.е. являются null и
     * созданный узел устанавливается как первый и последний элемент коллекции.
     * Если добавляется элемент не первый:
     * создается узел для нового элемента и устанавливается ссылка на существующий элемент как на предыдущий,
     * а следующим элементом у созданного узла остается null.
     * Этот новый узел сохраняется в переменную связанного списка tail
     *
     * @param e - добавляемый элемент
     * @return - возвращает true при добавлении элемента
     */
    @Override
    public boolean add(T e) {
        Node<T> newNode = new Node<>(e);

        if (head == null) {
            head = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
        }

        tail = newNode;
        size++;
        return true;
    }

    /**
     * Добавление элемента в середину списка.
     * Сначала осуществляется проверка значения index.
     *
     * @param index   - индекс, на который нужно вставить элемент
     * @param element - значение вставляемого элемента
     * @throws IndexOutOfBoundsException, если index не удовлетворяет условиям
     */
    @Override
    public void add(int index, T element) {
        checkIndex(index, size);

        // если index равен размеру колекции, то необходимо вставить элемент в конец списка
        if (index == size) {
            add(element);
        } else {
            // определяется узел, находящийся в данный момент под индексом, под который нам необходимо вставить новый узел
            Node<T> foundNode = getNode(index);
            addBefore(element, foundNode);
        }
    }

    /**
     * Поиск данного узла.
     * Осуществляется с помощью простого цикла for по половине списка
     * (в зависимости от значения индекса — либо с начала до элемента, либо с конца до элемента)
     *
     * @param index - индекс искомого узла
     * @return - возвращает искомый узел
     */
    private Node<T> getNode(int index) {
        Node<T> x;
        if (index < (size >> 1)) {
            x = head;
            for (int i = 0; i < index; i++) {
                x = x.next;
            }
        } else {
            x = tail;
            for (int i = size - 1; i > index; i--) {
                x = x.prev;
            }
        }
        return x;
    }

    /**
     * Вставка узла перед указанным узлом.
     * Создается узел для нового элемента,
     * ссылка на предыдущий элемент устанавливается на узел перед вставляемым,
     * а ссылка на следующий элемент устанавливается на узел после вставляемого.
     * Последовательно заменяются ссылки:
     * для элемента, следующего за новым элементом, заменяется ссылка на предыдущий элемент
     * для предшествующего новому элементу заменяется ссылка на следующий элемент
     * В последнюю очередь увеличивается размер списка
     *
     * @param element   - значение нового узла
     * @param foundNode - узел, перед которым нужно вставить новый узел
     */
    private void addBefore(T element, Node<T> foundNode) {
        Node<T> newNode = new Node<>(element);
        Node<T> prev = foundNode.prev;

        newNode.next = foundNode;
        newNode.prev = prev;
        foundNode.prev = newNode;

        if (prev == null) {
            head = newNode;
        } else {
            prev.next = newNode;
        }
        size++;
    }

    /**
     * Удаление искомомго элемента.
     * Искомый объект сравнивается по порядку со всеми элементами.
     * Когда найден узел, элемент которого равен искомому объекту, вызывается метод unlink(node.data)
     *
     * @param e - удаляемый элемент
     * @return - true, если такой элемент найден и удалён, иначе false
     */
    @Override
    public boolean remove(T e) {
        if (e == null) {
            for (Node<T> x = head; x != null; x = x.next) {
                if (x.data == null) {
                    unlink(x);
                    return true;
                }
            }
        } else {
            for (Node<T> x = head; x != null; x = x.next) {
                if (e.equals(x.data)) {
                    unlink(x);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Удаление ссылок у удаляемого значения.
     * Элемент сохраняется в отдельной переменной.
     * Потом переопределяются ссылки соседних узлов так, чтобы они указывали друг на друга.
     * Затем обнуляется значение узла, который содержит удаляемый объект,
     * а также уменьшается размер коллекции
     *
     * @param x - элемент, требующий удаления
     * @return - возвращает удалённый элемент
     */
    private T unlink(Node<T> x) {
        T element = x.data;
        Node<T> next = x.next;
        Node<T> prev = x.prev;

        if (prev == null) {
            head = next;
        } else {
            prev.next = next;
            x.prev = null;
        }

        if (next == null) {
            tail = prev;
        } else {
            next.prev = prev;
            x.next = null;
        }

        x.data = null;
        size--;
        return element;
    }

    /**
     * Удаление элемента по индексу.
     * Проверка корректности индекса, затем находим узел с данным индексом
     * и удаляем у него ссылки
     *
     * @param index - индекс удаляемого узла
     * @return - возвращает удалённый элемент
     */
    @Override
    public T remove(int index) {
        checkIndex(index, size - 1);
        return unlink(getNode(index));
    }

    /**
     * Получение элемента по индексу.
     * Проверка корректности индекса и затем вызываем уже написанный метод getNode()
     * @param index - индекс искомого элемента
     * @return - возвращает значение искомого элемента
     */
    @Override
    public T get(int index) {
        checkIndex(index, size - 1);
        return getNode(index).data;
    }

    /**
     * Установка нового значения по индексу.
     * Проверка корректности индекса и находим хранящийся под данным индексом узел,
     * устанавливаем ему новое значение,
     * старое запоминаем и возвращаем
     * @param index - индекс узла, у которого нужно заменить значение
     * @param element - устанавливаемое значение
     * @return - возвращаем старое значение
     */
    @Override
    public T set(int index, T element) {
        checkIndex(index, size - 1);
        Node<T> x = getNode(index);
        T oldVal = x.data;
        x.data = element;
        return oldVal;
    }

    /**
     * Получение подсписка.
     * Проверка корректности индексов.
     * Создаем пустой список. Находим узел под индексом fromIndex
     * и с помощью цикла for заносим в него значения node.data
     * @param fromIndex - с какого индекса копировать элементы.
     * @param toIndex   - до какого индекса копировать элементы.
     * @return - возвращает новый список CustomList<T>
     */
    @Override
    public CustomList<T> subList(int fromIndex, int toIndex) {
        if (fromIndex < 0 || toIndex > size || fromIndex > toIndex) {
            throw new IndexOutOfBoundsException("fromIndex = " + fromIndex + ", toIndex = " + toIndex + ", size = " + size);
        }

        CustomList<T> subList = new CustomLinkedList<>();
        Node<T> current = getNode(fromIndex);

        for (int i = fromIndex; i < toIndex; i++) {
            subList.add(current.data);
            current = current.next;
        }

        return subList;
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

    /**
     * Переопределение метода toString. Возвращает элементы списка через запятую в квадратных скобках.
     *
     * @return - значения списка в виде массива
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Node<T> current = head;
        while (current != null) {
            sb.append(current.data);
            if (current.next != null) {
                sb.append(", ");
            }
            current = current.next;
        }
        sb.append("]");
        return sb.toString();
    }
}
