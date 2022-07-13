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
 * place", si veda <a href="https://it.wikipedia.org/wiki/Algoritmo_in_loco">...</a>)
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

    private int dim;
    private int count;


    @Override
    public SortingAlgorithmResult<E> sort(List<E> l) {
        dim = l.size();
        for (int i = l.size() / 3; i >= 0; i--)
            heapify(l, i);

        for(dim=l.size()-1;dim>=0;dim--){
            swap(l, 0, dim);
            heapify(l,0);
        }

        return new SortingAlgorithmResult<>(l, count);
    }

    private void heapify(List<E> l, int i) {
        int max = i;
        if(this.getFirstChildNode(i) < dim && l.get(this.getFirstChildNode(i)).compareTo(l.get(max)) >= 0)
        {
            max = this.getFirstChildNode(i);
        }
        if(this.getSecondChildNode(i) < dim && l.get(this.getSecondChildNode(i)).compareTo(l.get(max)) >= 0)
        {
            max = this.getSecondChildNode(i);
        }
        if(this.getThirdChildNode(i) < dim && l.get(this.getThirdChildNode(i)).compareTo(l.get(max)) >= 0)
        {
            max = this.getThirdChildNode(i);
        }
        if(max != i)
        {
            swap(l, i, max);
            heapify(l, max);
            count++;
        }
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
    private int getFirstChildNode(int pos)
    {
        return (3*pos) + 1;
    }

    private int getSecondChildNode(int pos)
    {
        return ((3*pos) + 1)+1;
    }

    private int getThirdChildNode(int pos)
    {
        return ((3*pos) + 1)+2;
    }

    private void swap(List<E> l, int i, int j)
    {
        E element = l.get(i); //posizione i-esima
        l.set(i, l.get(j)); //sostituisce elemento gli elementi
        l.set(j, element); //esegue il set dell'elemento di appoggio
    }

}
