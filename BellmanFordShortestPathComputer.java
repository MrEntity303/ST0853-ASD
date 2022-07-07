package it.unicam.cs.asdl2122.pt1;

import java.util.List;
//TODO completare gli import necessari

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
            throw new NullPointerException();
        if(graph.isEmpty())
            throw new IllegalArgumentException();
        if(!graph.isDirected())
            throw new IllegalArgumentException();
        for (GraphEdge<L> newEdge : graph.getEdges()) {
            if(newEdge.hasWeight())
                throw new IllegalArgumentException();
        }
        this.graph = graph;
    }

    @Override
    public void computeShortestPathsFrom(GraphNode<L> sourceNode) {
        // TODO implementare
    }

    @Override
    public boolean isComputed() {
        return this.isComputed;
    }

    @Override
    public GraphNode<L> getLastSource() {
        // TODO implementare
        return this.lastSource;
    }

    @Override
    public Graph<L> getGraph() {
        return this.graph;
    }

    @Override
    public List<GraphEdge<L>> getShortestPathTo(GraphNode<L> targetNode) {
        // TODO implementare
        return null;
    }

    // TODO inserire eventuali metodi privati per l'implementazione
}
