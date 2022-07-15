/**
 * 
 */
package it.unicam.cs.asdl2122.pt1;

import java.util.List;

/**
 * Algoritmo di ordinamento che usa un albero AVL con molteplicità per ordinare
 * una lista di elementi. La strategia di realizzazione è semplice: si
 * inseriscono i valori da ordinare in un AVLTree e poi si fa una visita inOrder
 * per ottenere la lista ordinata di elementi.
 * 
 * @author Luca Tesei (Template)
 *
 */
public class AVLTreeSort<E extends Comparable<E>>
        implements SortingAlgorithm<E> {

    public SortingAlgorithmResult<E> sort(List<E> l) {
        //creo un avl tree vuoto
        AVLTree<E> avlTree= new AVLTree<>();
        int compareCounter = 0;
        /*
        * inserisco nell'avl tutti gli elementi della lista
        * e accumulo il numero di confronti
        */
        for (E element: l)
            compareCounter += avlTree.insert(element);

        //ritorno il numero di confronti e lista ottenuta
        return new SortingAlgorithmResult<>(avlTree.inOrderVisit(), compareCounter);
    }

    public String getName() {
        return "AVLTreeSort";
    }

}
