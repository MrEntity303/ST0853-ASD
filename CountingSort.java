package it.unicam.cs.asdl2122.pt1;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementazione dell'algoritmo di ordinamento in tempo lineare denominato
 * Counting Sort. In questo caso l'algoritmo, oltre a restituire l'array
 * ordinato, invece del numero di comparazioni effettuate restituisce la
 * dimensione dell' array accessorio che ha creato per eseguire i calcoli.
 *
 * @author Luca Tesei (Template)
 *
 */
public class CountingSort implements SortingAlgorithm<Integer> {

    /*
     * Invece del numero di confronti restituisce la dimensione dell'array
     * accessorio che ha creato per svolgere la computazione.
     */
    @Override
    public SortingAlgorithmResult<Integer> sort(List<Integer> l) {
        Integer min = l.get(0), max = l.get(0);
        for (int n : l)
        {
            if(n <= min)
                min = n;
            if(n >= max)
                max = n;
        }
        int[] tmpArr = new int[max-min+1];
        for (int n : l)
        {
            tmpArr[n-min] = tmpArr[n-min] +1;
        }



        for (int i = 1; i < tmpArr.length ; i++)
        {
            tmpArr[i] = tmpArr[i] + tmpArr[i-1];
        }


        ArrayList<Integer> result = new ArrayList<>(l);

        for(int i = result.size()-1; i >= 0; i--)
        {
            result.set(tmpArr[l.get(i)-min]-1, l.get(i));
            tmpArr[l.get(i)-min] = tmpArr[l.get(i)-min] -1;
        }

        return new SortingAlgorithmResult<>(result, tmpArr.length);
    }

    @Override
    public String getName() {
        return "CountingSort";
    }
}