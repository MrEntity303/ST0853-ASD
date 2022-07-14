package it.unicam.cs.asdl2122.pt1;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementazione dell'algoritmo di ordinamento in tempo lineare denominato
 * Counting Sort. In questo caso l'algoritmo, oltre a restituire l' array
 * ordinato, invece del numero di comparazioni effettuate restituisce la
 * dimensione dell' array accessorio che ha creato per eseguire i calcoli.
 *
 * @author Luca Tesei (Template)
 *
 */
public class CountingSort implements SortingAlgorithm<Integer> {

    /*
     * Invece del numero di confronti restituisce la dimensione dell' array
     * accessorio che ha creato per svolgere la computazione.
     */
    @Override
    public SortingAlgorithmResult<Integer> sort(List<Integer> l) {
        //Trova il range di valore contenuti nella sequenza
        //conservando il valore minimo e massimo in variabili di appoggio
        Integer min = l.get(0), max = l.get(0);
        for (int n : l)
        {
            if(n <= min)
                min = n;
            if(n >= max)
                max = n;
        }

        //conta il numero di volte che un valore si ripete nella sequenza
        //ATTENZIONE un valore potrebbe non essere presente e avere frequenza zero
        int[] tmpArr = new int[max-min+1];
        for (int n : l)
        {
            tmpArr[n-min] = tmpArr[n-min] +1;
        }


        //costruisce l' array di appoggio che serve per ordinare
        //ogni elemento della sequenza al posto giusto
        for (int i = 1; i < tmpArr.length ; i++)
        {
            tmpArr[i] = tmpArr[i] + tmpArr[i-1];
        }

        //array result con la sequenza ordinata
        ArrayList<Integer> result = new ArrayList<>(l);

        //ordina l' array leggendo la posizione in cui posizione un elemento
        //dall' array usato per la computazione, mettendolo correttamente
        //sull'indice corretto
        for(int i = result.size()-1; i >= 0; i--)
        {
            result.set(tmpArr[l.get(i)-min]-1, l.get(i));
            tmpArr[l.get(i)-min] = tmpArr[l.get(i)-min] -1;
        }

        //ritorna array ordinato e la dimensione dell' array usato per la computazione
        return new SortingAlgorithmResult<>(result, tmpArr.length);
    }

    @Override
    public String getName() {
        return "CountingSort";
    }
}