package it.unicam.cs.asdl2122.pt1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

//ATTENZIONE: è vietato includere import a pacchetti che non siano della Java SE

/**
 * Implementazione dell'algoritmo di Bellman-Ford per il calcolo di cammini
 * minimi a sorgente singola in un graph pesato che può contenere anche pesi
 * negativi, ma non cicli di peso negativo.
 * 
 * @author Template: Luca Tesei
 *
 * @param <L>
 *                etichette dei nodi del graph
 */
public class BellmanFordShortestPathComputer<L>
        implements SingleSourceShortestPathComputer<L> {

    private Graph<L> graph;

    private boolean isComputed;

    private GraphNode<L> lastSource = null;

    /**
     * Crea un calcolatore di cammini minimi a sorgente singola per un graph
     * orientato e pesato.
     *
     * @param graph
     *                  il graph su cui opera il calcolatore di cammini minimi
     * @throws NullPointerException
     *                                      se il graph passato è nullo
     *
     * @throws IllegalArgumentException
     *                                      se il graph passato è vuoto
     *
     * @throws IllegalArgumentException
     *                                      se il graph passato non è diretto
     *
     * @throws IllegalArgumentException
     *                                      se il graph passato non è pesato,
     *                                      cioè esiste almeno un arco il cui
     *                                      peso è {@code Double.NaN}.
     */
    public BellmanFordShortestPathComputer(Graph<L> graph) {
        if(graph == null)
            throw new NullPointerException("Il grafo passato e' nullo.");
        if(graph.isEmpty())
            throw new IllegalArgumentException("Il grafo passato e' vuoto.");
        if(!graph.isDirected())
            throw new IllegalArgumentException("Il grafo passato non e' diretto.");
        for (GraphEdge<L> newEdge : graph.getEdges()) {
            if(!newEdge.hasWeight())
                throw new IllegalArgumentException("Il grafo passato non e' pesato.");
        }
        this.graph = graph;
        this.isComputed = false;
        this.lastSource = null;

    }

    @Override
    public void computeShortestPathsFrom(GraphNode<L> sourceNode)
    {
        if(sourceNode == null)
            throw new NullPointerException("Il nodo passato e' nullo.");

        if(!graph.getNodes().contains(sourceNode))
            throw new IllegalArgumentException("Il nodo passato non esiste.");

        //step 1
        //confronto ogni nodo del grafo con un nuovo nodo
        //source sarà il nodo da scoprire
        for (GraphNode<L> next : this.getGraph().getNodes())
        {
            //il nuovo nodo sorgente avrà distanza pari a infinito
            next.setFloatingPointDistance(Double.POSITIVE_INFINITY);
            next.setPrevious(null);
            //se il nodo passato è uguale a quello da confrontare
            //vuol dire che quello sarà a prescindere il nodo
            //di partenza da cui inizia a calcolare il
            //cammino minimo, quindi lo setto a 0.
            if(sourceNode.equals(next))
                next.setFloatingPointDistance(0.0);
        }

        //step2
        //scorro tutti i nodi del grafo
        for (int i = 0; i < this.getGraph().nodeCount() - 1; i++)
        {
            /*
             * per ogni arco del grafo creo due nodi:
             * uno sorgente (node1), e uno di destinazione (node2),
             * se la distanza di node2 è maggiore della distanza di node1,
             * copio la distanza di node1 + il peso del suo arco
             * nella distanza di node2
             * */
            for (GraphEdge<L> newEdge : this.graph.getEdges())
            {
                if (newEdge.getNode2().getFloatingPointDistance() > newEdge.getNode1().getFloatingPointDistance()
                        + newEdge.getWeight())
                {
                    newEdge.getNode2().setFloatingPointDistance(newEdge.getNode1().getFloatingPointDistance()
                            + newEdge.getWeight());
                    newEdge.getNode2().setPrevious(newEdge.getNode1());
                }
            }
        }
        //step 3
        /*
             * per ogni arco del grafo creo due nodi:
             * uno sorgente (node1), e uno di destinazione (node2),
             * se la distanza di node1 + il peso del suo nuovo arco
             * e' minore della distanza di node2,
             * ci sarà un ciclo con peso negativo
             * */
        for (GraphEdge<L> newEdge: this.getGraph().getEdges())
            if((newEdge.getNode1().getFloatingPointDistance() + newEdge.getWeight()) <
                    newEdge.getNode2().getFloatingPointDistance())
                throw new IllegalStateException("Il grafo presenta un ciclo con peso negativo.");

        this.isComputed = true;
        this.lastSource = sourceNode;
    }


    @Override
    public boolean isComputed() {
        return this.isComputed;
    }

    @Override
    public GraphNode<L> getLastSource() {
        if(lastSource == null)
            throw new IllegalStateException("Il calcolo non e' stato eseguito");
        return lastSource;
    }

    @Override
    public Graph<L> getGraph() {
        return this.graph;
    }

    @Override
    public List<GraphEdge<L>> getShortestPathTo(GraphNode<L> targetNode) {
        // TODO implementare
        if(targetNode == null)
            throw new NullPointerException("Il nodo passato è nullo.");

        if(!this.graph.getNodes().contains(targetNode))
            throw new IllegalArgumentException("Il nodo passato non esiste.");

        if(!isComputed())
            throw new IllegalStateException("Il calcolo non e' stato eseguito.");

        //la nuova lista di archi da salvare
        List<GraphEdge<L>> edgeList = new ArrayList<>();


        while (targetNode.getPrevious() != null) {
            //aggiungo l'arco che lo collega al precedente
            edgeList.add(graph.getEdge(targetNode.getPrevious(), targetNode));
            if (!targetNode.equals(this.getLastSource()))
                //se il targetNode non è uguale all'ultima sorgente, imposto i previous
                //fin quando non ne ha più, altrimenti interrompo il ciclo
                targetNode = targetNode.getPrevious();
            else
                break;
        }
          //dato che il target si trova alla fine e inizio a scorrere da esso,
          //faccio il reverse dell'array per avere gli archi ordinati dal primo all'ultimo
        Collections.reverse(edgeList);
        return edgeList;
    }
}
