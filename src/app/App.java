package app;

import doubleLinkedList.DoubleLinkedList;
import java.util.Scanner;

public class App {
    
    public App() {
        DoubleLinkedList<Integer> lista = new DoubleLinkedList<>();
        String recursivo = "recursivo", iterativo = "iterativo";
        // Prueba del add()
        for (int i = 0; i < 5; i++)
            lista.add((int)(Math.random() * 100) + 1);
        
        
        
        DoubleLinkedList<Integer> copia = lista.clone(lista);
        copia.printList();

        copia.mayorMenorPromedio(copia, iterativo);
        copia.mayorMenorPromedio(copia, recursivo);
//        
//        copia.posiciones(copia, "uno", recursivo);
        copia.rango(copia, 10, 80, iterativo);        
        copia.rango(copia, 10, 80, recursivo);
        copia.update(copia, 1, 5, iterativo);
        copia.printList();
        copia.update(copia, 2, 5, recursivo);
        copia.printList();
        
        
//        Scanner s = new Scanner(System.in);
//        int valor, antes, despues, posicion;
//        
//
//        Prueba del remove()
//        System.out.print("¿Valor a eliminar? ");
//        valor = s.nextInt();
//        lista.remove(valor);
//        lista.printList();
//        lista.rPrint();
//
//        Prueba del removeAll()
//        System.out.print("¿Valor que desea eliminar completamente? ");
//        valor = s.nextInt();
//        lista.removeAll(valor);
//        lista.printList();
//        lista.rPrint();
//
//        prueba de addAt()
//        System.out.print("¿Posición en la que desea agregar? ");
//        posicion = s.nextInt();
//        System.out.print("¿Valor que desea agregar? ");
//        valor = s.nextInt();
//        lista.addAt(valor, posicion);
//        lista.printList();
//        lista.rPrint();
//
//        Prueba de addAfter()
//        System.out.print("Valor a agregar? ");
//        valor = s.nextInt();
//        System.out.print("Despues de cual? ");
//        despues = s.nextInt();
//        lista.addAfter(despues, valor);
//        lista.printList();
//        lista.rPrint();
//
//        Prueba de addBefore()
//        System.out.print("Valor a agregar? ");
//        valor = s.nextInt();
//        System.out.print("Antes de cual? ");
//        antes = s.nextInt();
//        lista.addBefore(antes, valor);
//        lista.printList();
//        lista.rPrint();
//
//        Prueba addStart()
//        System.out.print("Valor a agregar al principio? ");
//        valor = s.nextInt();
//        lista.addStart(valor);
//        lista.printList();
//        lista.rPrint();
//
//        Prueba removeAfter()
//        System.out.print("Despues de cual valor va a eliminar? ");
//        despues = s.nextInt();
//        lista.removeAfter(despues);
//        lista.printList();
//        lista.rPrint();
//
//        Prueba removeBefore()
//        System.out.print("Antes de cual valor va a eliminar? ");
//        antes = s.nextInt();
//        lista.removeBefore(antes);
//        lista.printList();
//        lista.rPrint();

    }
}
