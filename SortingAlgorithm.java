package it.unicam.cs.asdl2122.pt1;

import java.util.List;

/**
 * Interfaccia per algoritmi di ordinamento generici.
 * 
 * @author Luca Tesei
 *
 * @param <E>
 *            Tipo degli elementi delle sequenze da ordinare. La classe E deve
 *            avere un ordinamento naturale totale fra i suoi elementi
 *            realizzato tramite l'implementazione dell'interfaccia Comparable.
 */
public interface SortingAlgorithm<E extends Comparable<E>> {

    /**
     * Ordina una lista di elementi in accordo all'ordinamento totale naturale
     * definito nella classe degli elementi.
     * 
     * @param l
     *              la lista da ordinare (dovrebbe essere una ArrayList)
     * @return un oggetto contenente la lista ordinata e il numero di
     *         operazioni di comparazione effettuate dall'algoritmo.
     */
    public SortingAlgorithmResult<E> sort(List<E> l);

    /**
     * Restituisce il nome dell'algoritmo di ordinamento.
     * 
     * @return il nome dell'algoritmo
     */
    public String getName();

}