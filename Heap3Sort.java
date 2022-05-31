/**
 * 
 */
package it.unicam.cs.asdl2122.pt1;

import java.util.List;

/**
 * Classe che implementa un algoritmo di ordinamento basato su uno heap
 * ternario. Usa una variante dei metodi di un MaxHeap ternario in modo da
 * implementare l'algoritmo utilizzando solo un array (arraylist) e alcune
 * variabili locali di appoggio (implementazione cosiddetta "in loco" o "in
 * place", si veda https://it.wikipedia.org/wiki/Algoritmo_in_loco)
 * 
 * Uno heap ternario è uno heap in cui ogni nodo ha tre figli e non due, come in
 * uno heap binario. La strategia di rappresentazione e i metodi d'inserimento
 * / estrazione del minimo / heapify devono essere adattati al caso di tre
 * figli, ma algoritmica-mente sono analoghi.
 * 
 * Lo heap ternario deve essere pensato in modo che accetti elementi ripetuti e
 * non accetti elementi null.
 * 
 * @author Luca Tesei (Template)
 *
 */
public class Heap3Sort<E extends Comparable<E>> implements SortingAlgorithm<E> {

    // TODO inserire eventuali variabili istanza che servono

    @Override
    public SortingAlgorithmResult<E> sort(List<E> l) {
        // TODO implementare
        return null;
    }

    private void heapify(List<E> l, int i) {
        // TODO implementare adattando al caso di heap ternario
    }

    @Override
    public String getName() {
        return "Heap3Sort";
    }

    /*
     * // TODO inserire eventuali metodi privati di comodo per calcolare
     * l'indice del primo, secondo e terzo di un nodo in posizione i. Si noti
     * che la posizione 0 è significativa e contiene sempre la radice dello
     * heap.
     */

}
