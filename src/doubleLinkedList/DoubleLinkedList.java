package doubleLinkedList;

import exception.isEmptyException;
import java.lang.reflect.Array;

public class DoubleLinkedList<T extends Comparable<T>> {
    private Node<T> head;
    private Node<T> tail;
    private int     lenght;
    
    public DoubleLinkedList() {
        head   = new Node<>();
        tail   = new Node<>();
        lenght = 0;
    }
    
    public void isEmpty() throws isEmptyException {
        if (head.getNext() == null)
            throw new isEmptyException("Empty list");
    }
    
    public int lenght() {
        return lenght;
    }
    
    // Este método es mío
    public void printList() {
        try {
            isEmpty();
            System.out.println(printList(head) + " lenght: " + lenght);
        } catch (isEmptyException e) {
            System.out.println(e.getMessage());
        }
    }
    
    private String printList(Node<T> node) {
        if (node.getNext() == null)
            return "";
        else
            return "[" + node.getNext().getValue() + "] " + printList(node.getNext());
    }
    
    // Para este reflejé la lógica de mi printList()
    public void rPrint() {
        try {
            isEmpty();
            System.out.println(rPrint(tail) + " lenght: " + lenght);
        } catch (isEmptyException e) {
            System.out.println(e.getMessage());
        }
    }
    
    private String rPrint(Node<T> node) {
        if (node.getBack() == null)
            return "";
        else
            return "[" + node.getBack().getValue() + "] " + rPrint(node.getBack());
    }
    
    private boolean isThere(Node<T> node, T value) {
        if (node.getNext() == null)
            return false;
        else if (node.getNext().getValue().equals(value))
            return true;
        else
            return isThere(node.getNext(), value);
    }
    
    private Node<T> isThereNode(Node<T> node, T value) {
        if (node.getNext() == null)
            return null;
        else if (node.getNext().getValue().equals(value))
            return node.getNext();
        else
            return isThereNode(node.getNext(), value);
    }
    
    private Node<T> getElementAt(int position) {
        if (position < 0 || position >= lenght)
            return null;
        else
            return getElementAt(head.getNext(), 0, position);
    }
    
    private Node<T> getElementAt(Node<T> node, int i, int position) {
        if (i == position)
            return node;
        else
            return getElementAt(node.getNext(), i + 1, position);
    }
    
    public boolean add(T value) {
        if (value == null) // Solo si el valor es nulo regresaría falso
            return false;
        else {
            Node<T> _new = new Node<>(value);
            try { // Caso 1: La lista no está vacía
                isEmpty();
                tail.getBack().setNext(_new);
                _new.setBack(tail.getBack());
                tail.setBack(_new);
            } catch (isEmptyException e) { // Caso 2: La lista está vacía
                head.setNext(_new);
                tail.setBack(_new);
            }
            lenght++;
            return true;
        }
    }
    
    public boolean add(Node<T> node) {
        return add(node.getValue());
    }
    
    public boolean remove(T value) {
        try {
            isEmpty();
            if (value == null) // Valor nulo
                return false;
            else if (isThere(head, value)) { // Sí se encuentra en la lista
                Node<T> rem = isThereNode(head, value);
                if (rem.getBack() == null)
                    if (rem.getNext() == null) { // Caso 1: Es el ÚNICO
                        head.setNext(null);
                        tail.setBack(null);
                    } else { // Caso 2: Es el PRIMERO
                        rem.getNext().setBack(null);
                        head.setNext(rem.getNext());
                        rem.setNext(null);
                    }
                else
                    if (rem.getNext() == null) { // Caso 3: Es el ÚLTIMO
                        rem.getBack().setNext(null);
                        tail.setBack(rem.getBack());
                        rem.setBack(null);
                    } else { // Caso 4: Esta ENMEDIO
                        rem.getNext().setBack(rem.getBack());
                        rem.getBack().setNext(rem.getNext());
                        rem.setNext(null);
                        rem.setBack(null);
                    }
                rem = null;
                System.gc();
                lenght--;
                return true;
            } else // No se encuentra en la lista
                return false;
        } catch (isEmptyException e) { // Lista vacía
            return false;
        }
    }
    
    public boolean remove(Node<T> node) {
        return remove(node.getValue());
    }
    
    public boolean removeAll(T value) {
        try {
            isEmpty();
            if (value == null)
                return false;
            else
                if (isThere(head, value)) {
                    do {
                        remove(value);
                    } while (isThere(head, value));
                    return true;
                } else
                    return false;
        } catch (isEmptyException e) {
            return false;
        }
    }
    
    public boolean addAt(T value, int position) {
        try {
            isEmpty();
            if (value == null)
                return false;
            else {
                Node<T> tmp = getElementAt(position);
                if (tmp == null)
                    return false;
                else {
                    Node<T> _new = new Node<>(value);
                    if (tmp.getBack() == null) { // Se agregaría al principio
                        head.setNext(_new);
                        tmp.setBack(_new);
                        _new.setNext(tmp);
                    } else {
                        tmp.getBack().setNext(_new);
                        _new.setBack(tmp.getBack());
                        tmp.setBack(_new);
                        _new.setNext(tmp);
                    }
                    lenght++;
                    return true;
                }
            }
        } catch (isEmptyException e) {
            return false;
        }
    }
    
    public boolean addAt(Node<T> node, int position) {
        return addAt(node.getValue(), position);
    }
    
    public boolean addAfter(T after, T value) {
        try {
            isEmpty();
            if (after == null || value == null)
                return false;
            else {
                Node<T> tmp = isThereNode(head, after);
                if (tmp == null)
                    return false;
                else {
                    Node<T> _new = new Node<>(value);
                    if (tmp.getNext() == null) {
                        tmp.setNext(_new);
                        _new.setBack(tmp);
                        tail.setBack(_new);
                    } else {
                        tmp.getNext().setBack(_new);
                        _new.setNext(tmp.getNext());
                        tmp.setNext(_new);
                        _new.setBack(tmp);
                    }
                    lenght++;
                    return true;
                }
            }
        } catch (isEmptyException e) {
            return false;
        }
    }
    
    public boolean addBefore(T before, T value) {
        try {
            isEmpty();
            if (before == null || value == null)
                return false;
            else {
                Node<T> tmp = isThereNode(head, before);
                if (tmp == null)
                    return false;
                else {
                    Node<T> _new = new Node<>(value);
                    if (tmp.getBack() == null) {
                        head.setNext(_new);
                        _new.setNext(tmp);
                        tmp.setBack(_new);
                    } else {
                        tmp.getBack().setNext(_new);
                        _new.setBack(tmp.getBack());
                        _new.setNext(tmp);
                        tmp.setBack(_new);
                    }
                    lenght++;
                    return true;
                }
            }
        } catch (isEmptyException e) {
            return false;
        }
    }
    
    public boolean removeAfter(T after) {
        try {
            isEmpty();
            if (after == null)
                return false;
            else {
                Node<T> tmp = isThereNode(head, after);
                if (tmp == null)
                    return false;
                else if (tmp.getNext() == null)
                    return false;
                else {
                    Node<T> rem = tmp.getNext();
                    if (rem.getNext() == null) {
                        tmp.setNext(null);
                        tail.setBack(tmp);
                        rem.setBack(null);
                    } else {
                        rem.getNext().setBack(tmp);
                        tmp.setNext(rem.getNext());
                        rem.setNext(null);
                        rem.setBack(null);
                    }
                    rem = null;
                    System.gc();
                    lenght--;
                    return true;
                }
            }
        } catch (isEmptyException e) {
            return false;
        }
    }
    
    public boolean removeBefore(T before) {
        try {
            isEmpty();
            if (before == null)
                return false;
            else {
                Node<T> tmp = isThereNode(head, before);
                if (tmp == null)
                    return false;
                else if (tmp.getBack() == null)
                    return false;
                else {
                    Node<T> rem = tmp.getBack();
                    if (rem.getBack() == null) {
                        head.setNext(tmp);
                        tmp.setBack(null);
                        rem.setNext(null);
                    } else {
                        rem.getBack().setNext(tmp);
                        tmp.setBack(rem.getBack());
                        rem.setBack(null);
                        rem.setNext(null);
                    }
                    rem = null;
                    System.gc();
                    lenght--;
                    return true;
                }
            }
        } catch (isEmptyException e) {
            return false;
        }
    }
    
    public boolean addStart(T value) {
        if (value == null)
            return false;
        else {
            Node<T> _new = new Node<>(value);
            try {
              isEmpty();
              head.getNext().setBack(_new);
              _new.setNext(head.getNext());
              head.setNext(_new);
            } catch (isEmptyException e) {
                head.setNext(_new);
                tail.setBack(_new);
            }
            lenght++;
            return true;
        }
    }
    
    public boolean addStart(Node<T> node) {
        return addStart(node.getValue());
    }
    
/*
    Este método no es necesario en lista doblemente enlazadas
    private Node<T> getPrevElement(Node<T> node, T value) {return null;}
*/
/*
    Ni siquiera sé cual es el punto de este
    private boolean removeFrom(Node<T> node) {return false;}
*/
    
    public DoubleLinkedList<T> clone(DoubleLinkedList<T> org) {
        try {
            org.isEmpty();
            DoubleLinkedList<T> _new = new DoubleLinkedList<>();
            for (int i = 0; i < org.lenght(); i++) {
                Node<T> _tmp = org.getElementAt(i);
                _new.add(_tmp);
            }
            return _new;
        } catch (isEmptyException e) {
        }
        return null;
    }

    public void update(DoubleLinkedList<T> copia, T value, int x, String str) {
        try {
            copia.isEmpty();
            String instace = copia.checkInstance(copia);

            switch (str) {
                case "recursivo":
                    if (update(copia.head.getNext(), value, 0, x)) {
                        System.out.println("Se actualizo correctamente.");
                    } else {
                        System.out.println("No actualizo correctamente.");
                    }
                    return;
                case "iterativo":
                    boolean flag = false;
                    for (int i = 0; i < copia.lenght(); i++) {
                        Node<T> node = copia.getElementAt(i);
                        if (x == i) {
                            node.setValue(value);
                            flag = true;
                            break;
                        }
                    }
                    if (flag) {
                        System.out.println("Se actualizo correctamente.");
                    } else {
                        System.out.println("No actualizo correctamente.");
                    }
                    return;
            }
        } catch (isEmptyException e) {
        }
    }

    private boolean update(Node<T> node, T value, int i, int pos) {
        if (node == null) {
            return false;
        } else if (i == pos) {
            node.setValue(value);
            return true;
        }
        return update(node.getNext(), value, ++i, pos);
    }

    public void rango(DoubleLinkedList<T> copia, int X, int Y, String str) {
        try {
            copia.isEmpty();
            String instace = copia.checkInstance(copia);

            switch (str) {
                case "recursivo":
                    System.out.print("Entre el rango de (" + X + "," + Y + ") se encuentran en: ");
                    rango(copia.head.getNext(), X, Y);
                    System.out.println("");
                    return;
                case "iterativo":
                    System.out.print("Entre el rango de (" + X + "," + Y + ") se encuentran en: ");
                    for (int i = 0; i < copia.lenght(); i++) {
                        Node<T> node = copia.getElementAt(i);
                        if ((Integer) node.getValue() > X && (Integer) node.getValue() < Y) {
                            System.out.print(node.getValue() + ",");
                        }
                    }
                    System.out.println("");
                    return;
            }
        } catch (isEmptyException e) {
        }
    }

    private void rango(Node<T> node, int X, int Y) {
        if (node == null) {
            return;
        } else if ((Integer) node.getValue() > X && (Integer) node.getValue() < Y) {
            System.out.print(node.getValue() + ",");
        }
        rango(node.getNext(), X, Y);
    }

    public void posiciones(DoubleLinkedList<T> copia, T value, String str) {
        try {
            copia.isEmpty();
            String instace = copia.checkInstance(copia);

            switch (str) {
                case "recursivo":
                    System.out.print("El elemento '" + value + "' se encuentra en: ");
                    posiciones(copia.head.getNext(), 0, value);
                    System.out.println("");
                    return;
                case "iterativo":
                    System.out.print("El elemento '" + value + "' se encuentra en: ");
                    for (int i = 0; i < copia.lenght(); i++) {
                        Node<T> node = copia.getElementAt(i);
                        if (node.getValue().compareTo(value) == 0) {
                            System.out.print(i + ",");
                        }
                    }
                    System.out.println("");
                    return;
            }
        } catch (isEmptyException e) {
        }
    }

    private void posiciones(Node<T> node, int i, T value) {
        if (node == null) {
            return;
        } else if (node.getValue().compareTo(value) == 0) {
            System.out.print(i + ",");
        }
        posiciones(node.getNext(), ++i, value);
    }

    public void mayorMenorPromedio(DoubleLinkedList<T> copia, String str) {
        try {
            copia.isEmpty();
            DoubleLinkedList<T> _new = new DoubleLinkedList<>();
            double prom = copia.promedio(copia.head.getNext(), 0, 0, copia.checkInstance(copia));
            String instace = copia.checkInstance(copia);

            T[] _arr;

            switch (str) {
                case "recursivo":
                    if ("Integer".equals(instace)) {
                        _arr = (T[]) Array.newInstance(Integer.class, 2);
                        _arr[0] = copia.head.getNext().getValue();
                        _arr[1] = copia.head.getNext().getValue();
                        _arr = copia.mayorMenorProm(_arr, copia.head.getNext(), prom, checkInstance(copia));
                        System.out.println("El numero mayor es: " + _arr[0]);
                        System.out.println("El numero menor es: " + _arr[1]);
                        System.out.println("Su promedio es: " + ((Integer) _arr[0] + (Integer) _arr[1]) / 2);
                    } else if ("String".equals(instace)) {
                        _arr = (T[]) Array.newInstance(String.class, 2);
                        _arr[0] = copia.head.getNext().getValue();
                        _arr[1] = copia.head.getNext().getValue();
                        _arr = copia.mayorMenorProm(_arr, head.getNext(), prom, checkInstance(copia));
                        System.out.println("El numero mayor es: " + _arr[0]);
                        System.out.println("El numero menor es: " + _arr[1]);
                        String Y = (String) _arr[0];
                        String Z = (String) _arr[1];
                        System.out.println("Su promedio es: " + (Y.length() + Z.length()) / 2);
                    }
                    return;
                case "iterativo":
                    if ("Integer".equals(instace)) {
                        _arr = (T[]) Array.newInstance(Integer.class, 2);
                        _arr[0] = copia.head.getNext().getValue();
                        _arr[1] = copia.head.getNext().getValue();
                        for (int i = 0; i < copia.lenght(); i++) {
                            Node<T> node = copia.getElementAt(i);
                            if ((Integer) node.getValue() > (Integer) _arr[0]) {
                                _arr[0] = node.getValue();
                            }
                            if ((Integer) node.getValue() < (Integer) _arr[1]) {
                                _arr[1] = node.getValue();
                            }
                        }
                        System.out.println("El numero mayor es: " + _arr[0]);
                        System.out.println("El numero menor es: " + _arr[1]);
                        System.out.println("Su promedio es: " + ((Integer) _arr[0] + (Integer) _arr[1]) / 2);
                    } else if ("String".equals(instace)) {
                        _arr = (T[]) Array.newInstance(String.class, 2);
                        _arr[0] = copia.head.getNext().getValue();
                        _arr[1] = copia.head.getNext().getValue();
                        for (int i = 0; i < copia.lenght(); i++) {
                            Node<T> node = copia.getElementAt(i);
                            String X = (String) node.getValue();
                            String Y = (String) _arr[0];
                            String Z = (String) _arr[1];
                            if (X.length() > Y.length()) {
                                _arr[0] = node.getValue();
                            }
                            if (X.length() < Z.length()) {
                                _arr[1] = node.getValue();
                            }
                        }
                        System.out.println("El numero mayor es: " + _arr[0]);
                        System.out.println("El numero menor es: " + _arr[1]);
                        String Y = (String) _arr[0];
                        String Z = (String) _arr[1];
                        System.out.println("Su promedio es: " + (Y.length() + Z.length()) / 2);
                    }
                    return;

            }
        } catch (isEmptyException e) {
        }
    }

    private T[] mayorMenorProm(T[] arr, Node<T> node, double prom, String str) {
        switch (str) {
            case "Integer":
                if (node == null) {
                    return arr;
                }
                if ((Integer) node.getValue() > (Integer) arr[0]) {
                    arr[0] = node.getValue();
                }
                if ((Integer) node.getValue() < (Integer) arr[1]) {
                    arr[1] = node.getValue();
                }
                return mayorMenorProm(arr, node.getNext(), prom, str);
            case "String":
                if (node == null) {
                    return arr;
                }
                String X = (String) node.getValue();
                String Y = (String) arr[0];
                String Z = (String) arr[1];
                if (X.length() > Y.length()) {
                    arr[0] = node.getValue();
                }
                if (X.length() < Z.length()) {
                    arr[1] = node.getValue();
                }
                return mayorMenorProm(arr, node.getNext(), prom, str);
        }
        return null;
    }

    private String checkInstance(DoubleLinkedList<T> copia) {
        if (copia.head.getNext().getValue() instanceof Integer) {
            return "Integer";
        }
        if (copia.head.getNext().getValue() instanceof String) {
            return "String";
        }
        return null;
    }

    public DoubleLinkedList<T> mayoresPromedio(DoubleLinkedList<T> org, String str) {
        try {
            org.isEmpty();
            DoubleLinkedList<T> copia = org.clone(org);
            DoubleLinkedList<T> _new = new DoubleLinkedList<>();
            double prom = copia.promedio(copia.head.getNext(), 0, 0, copia.checkInstance(copia));
            System.out.println("El promedio es: " + prom);
            switch (str) {
                case "recursivo":
                    return mayoresPromedo(copia.head.getNext(), _new, prom);
                case "iterativo":
                    for (int i = 0; i < copia.lenght(); i++) {
                        Node<T> node = copia.getElementAt(i);
                        if ((Integer) node.getValue() > prom) {
                            _new.add(node);
                        }
                    }
                    return _new;
            }
        } catch (isEmptyException e) {
        }
        return null;
    }

    private DoubleLinkedList<T> mayoresPromedo(Node<T> node, DoubleLinkedList<T> _new, double prom) {
        if (node == null) {
            return _new;
        } else if ((Integer) node.getValue() > prom) {
            _new.add(node);
        }
        return mayoresPromedo(node.getNext(), _new, prom);
    }

    private double promedio(Node<T> node, int sum, int i, String str) {
        switch (str) {
            case "Integer":
                if (node == null) {
                    return sum / i;
                } else {
                    sum += (Integer) node.getValue();
                }
                return promedio(node.getNext(), sum, ++i, str);
            case "String":
                if (node == null) {
                    return sum / i;
                } else {
                    String s = (String) node.getValue();
                    sum += s.length();
                }
                return promedio(node.getNext(), sum, ++i, str);
            default:
                return -1d;
        }
    }

    public DoubleLinkedList<T> parImpar(DoubleLinkedList<T> org, String str) {
        try {
            org.isEmpty();
            DoubleLinkedList<T> copia = org.clone(org);
            DoubleLinkedList<T> _new = new DoubleLinkedList<>();
            switch (str) {
                case "recursivo":
                    return _new.par_Impar(_new, copia.head.getNext());
                case "iterativo":
                    for (int i = 0; i < copia.lenght(); i++) {
                        Node<T> node = copia.getElementAt(i);
                        if (!esPar(node.getValue())) {
                            _new.add(node);
                        }
                    }
                    return _new;
            }
        } catch (isEmptyException e) {
        }
        return null;
    }

    private DoubleLinkedList<T> par_Impar(DoubleLinkedList<T> copia, Node<T> node) {
        if (node == null) {
            return copia;
        } else if (!esPar(node.getValue())) {
            copia.add(node);
        }
        return par_Impar(copia, node.getNext());
    }

    private boolean esPar(T value) {
        return (Integer) value % 2 == 0;
    }
}
