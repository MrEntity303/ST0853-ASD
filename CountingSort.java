package it.unicam.cs.asdl2122.pt1;

import java.util.List;

/**
 * Implementazione dell'algoritmo di ordinamento in tempo lineare denominato
 * Counting Sort. In questo caso l'algoritmo, oltre a restituire l' array
 * ordinato, invece del numero di comparazioni effettuate restituisce la
 * dimensione del array accessorio che ha creato per eseguire i calcoli.
 *
 * @author Luca Tesei (Template)
 *
 */
public class CountingSort<Integer extends Comparable<Integer>>
        implements SortingAlgorithm<Integer> {

    /*
     * Invece del numero di confronti restituisce la dimensione del array
     * accessorio che ha creato per svolgere la computazione.
     */
    @Override
    public SortingAlgorithmResult<Integer> sort(List<Integer> l) {
        // TODO implementare
        return null;
    }

    @Override
    public String getName() {
        return "CountingSort";
    }

}
