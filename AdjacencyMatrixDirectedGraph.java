/**
 * 
 */
package it.unicam.cs.asdl2122.pt1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

// TODO completare gli import necessari

// ATTENZIONE: è vietato includere import a pacchetti che non siano della Java SE

/**
 * Classe che implementa un grafo orientato tramite matrice di adiacenza. Non
 * sono accettate etichette dei nodi null e non sono accettate etichette
 * duplicate nei nodi (che in quel caso sono lo stesso nodo).
 * 
 * I nodi sono indicizzati da 0 a nodeCount() - 1 seguendo l'ordine del loro
 * inserimento (0 è l'indice del primo nodo inserito, 1 del secondo e così via)
 * e quindi in ogni istante la matrice di adiacenza ha dimensione nodeCount() *
 * nodeCount(). La matrice, sempre quadrata, deve quindi aumentare di dimensione
 * ad ogni inserimento di un nodo. Per questo non è rappresentata tramite array
 * ma tramite ArrayList.
 * 
 * Gli oggetti GraphNode<L>, cioè i nodi, sono memorizzati in una mappa che
 * associa ad ogni nodo l'indice assegnato (che può cambiare nel tempo). Il
 * dominio della mappa rappresenta quindi l'insieme dei nodi.
 * 
 * Gli archi sono memorizzati nella matrice di adiacenza. A differenza della
 * rappresentazione standard con matrice di adiacenza, la posizione i,j della
 * matrice non contiene un flag di presenza, ma è null se i nodi i e j non sono
 * collegati da un arco e contiene un oggetto della classe GraphEdge<L> se lo
 * sono. Tale oggetto rappresenta l'arco.
 * 
 * Questa classe supporta i metodi di cancellazione di nodi e archi e supporta
 * tutti i metodi che usano indici, utilizzando l'indice assegnato a ogni nodo
 * in fase di inserimento ed eventualmente modificato successivamente.
 * 
 * @author Luca Tesei (template)
 *
 * 
 */
public class AdjacencyMatrixDirectedGraph<L> extends Graph<L> {
    /*
     * Le seguenti variabili istanza sono protected al solo scopo di agevolare
     * il JUnit testing
     */

    /*
     * Insieme dei nodi e associazione di ogni nodo con il proprio indice nella
     * matrice di adiacenza
     */
    protected Map<GraphNode<L>, Integer> nodesIndex;

    /*
     * Matrice di adiacenza, gli elementi sono null o oggetti della classe
     * GraphEdge<L>. L'uso di ArrayList permette alla matrice di aumentare di
     * dimensione gradualmente ad ogni inserimento di un nuovo nodo e di
     * ridimensionarsi se un nodo viene cancellato.
     */
    protected ArrayList<ArrayList<GraphEdge<L>>> matrix;

    /**
     * Crea un grafo vuoto.
     */
    public AdjacencyMatrixDirectedGraph() {
        this.matrix = new ArrayList<ArrayList<GraphEdge<L>>>();
        this.nodesIndex = new HashMap<GraphNode<L>, Integer>();
    }

    @Override
    public int nodeCount() {
        return this.nodesIndex.keySet().size();
    }

    @Override
    public int edgeCount() {
        return this.getEdges().size();
    }

    @Override
    public void clear() {
        //this.matrix = new ArrayList<ArrayList<GraphEdge<L>>>();
        //this.nodesIndex = new HashMap<GraphNode<L>, Integer>();
        this.matrix.clear();
        this.nodesIndex.clear();
    }

    @Override
    public boolean isDirected() {
        return true;
    }

    /*
     * Gli indici dei nodi vanno assegnati nell'ordine di inserimento a partire
     * da zero
     */
    @Override
    public boolean addNode(GraphNode<L> node) {
        // TODO implementare
        if(node == null)
            throw new NullPointerException("Impossibile aggiungere un nodo nullo.");
        //se il nodo è già presente ritorno false
        if(this.nodesIndex.containsKey(node))
            return false;
        //inserisco null fino a che non devo inserire un elemento
        for (int i = 0; i < nodesIndex.size(); i++)
        {
            this.nodesIndex.put(node, null);
        }

        //se la chiave non corrisponde a nessun valore, o è null, viene associata
        //a un valore specifico e restituisce null, altrimenti restituisce il valore corrente
        return this.nodesIndex.putIfAbsent(node, this.nodeCount()) != null;
    }

    /*
     * Gli indici dei nodi vanno assegnati nell'ordine di inserimento a partire
     * da zero
     */
    @Override
    public boolean addNode(L label) {
        // TODO implementare
        return false;
    }

    /*
     * Gli indici dei nodi il cui valore sia maggiore dell'indice del nodo da
     * cancellare devono essere decrementati di uno dopo la cancellazione del
     * nodo
     */
    @Override
    public void removeNode(GraphNode<L> node) {
        // TODO implementare
    }

    /*
     * Gli indici dei nodi il cui valore sia maggiore dell'indice del nodo da
     * cancellare devono essere decrementati di uno dopo la cancellazione del
     * nodo
     */
    @Override
    public void removeNode(L label) {
        // TODO implementare
    }

    /*
     * Gli indici dei nodi il cui valore sia maggiore dell'indice del nodo da
     * cancellare devono essere decrementati di uno dopo la cancellazione del
     * nodo
     */
    @Override
    public void removeNode(int i) {
        // TODO implementare
    }

    @Override
    public GraphNode<L> getNode(GraphNode<L> node) {
        // TODO implementare
        return null;
    }

    @Override
    public GraphNode<L> getNode(L label) {
        // TODO implementare
        return null;
    }

    @Override
    public GraphNode<L> getNode(int i) {
        // TODO implementare
        return null;
    }

    @Override
    public int getNodeIndexOf(GraphNode<L> node) {
        // TODO implementare
        return -1;
    }

    @Override
    public int getNodeIndexOf(L label) {
        // TODO implementare
        return -1;
    }

    @Override
    public Set<GraphNode<L>> getNodes() {
        // TODO implementare
        return null;
    }

    @Override
    public boolean addEdge(GraphEdge<L> edge) {
        // TODO implementare
        return false;
    }

    @Override
    public boolean addEdge(GraphNode<L> node1, GraphNode<L> node2) {
        // TODO implementare
        return false;
    }

    @Override
    public boolean addWeightedEdge(GraphNode<L> node1, GraphNode<L> node2,
            double weight) {
        // TODO implementare
        return false;
    }

    @Override
    public boolean addEdge(L label1, L label2) {
        // TODO implementare
        return false;
    }

    @Override
    public boolean addWeightedEdge(L label1, L label2, double weight) {
        // TODO implementare
        return false;
    }

    @Override
    public boolean addEdge(int i, int j) {
        // TODO implementare
        return false;
    }

    @Override
    public boolean addWeightedEdge(int i, int j, double weight) {
        // TODO implementare
        return false;
    }

    @Override
    public void removeEdge(GraphEdge<L> edge) {
        // TODO implementare
    }

    @Override
    public void removeEdge(GraphNode<L> node1, GraphNode<L> node2) {
        // TODO implementare
    }

    @Override
    public void removeEdge(L label1, L label2) {
        // TODO implementare
    }

    @Override
    public void removeEdge(int i, int j) {
        // TODO implementare
    }

    @Override
    public GraphEdge<L> getEdge(GraphEdge<L> edge) {
        // TODO implementare
        return null;
    }

    @Override
    public GraphEdge<L> getEdge(GraphNode<L> node1, GraphNode<L> node2) {
        // TODO implementare
        return null;
    }

    @Override
    public GraphEdge<L> getEdge(L label1, L label2) {
        // TODO implementare
        return null;
    }

    @Override
    public GraphEdge<L> getEdge(int i, int j) {
        // TODO implementare
        return null;
    }

    @Override
    public Set<GraphNode<L>> getAdjacentNodesOf(GraphNode<L> node) {
        // TODO implementare
        return null;
    }

    @Override
    public Set<GraphNode<L>> getAdjacentNodesOf(L label) {
        // TODO implementare
        return null;
    }

    @Override
    public Set<GraphNode<L>> getAdjacentNodesOf(int i) {
        // TODO implementare
        return null;
    }

    @Override
    public Set<GraphNode<L>> getPredecessorNodesOf(GraphNode<L> node) {
        // TODO implementare
        return null;
    }

    @Override
    public Set<GraphNode<L>> getPredecessorNodesOf(L label) {
        // TODO implementare
        return null;
    }

    @Override
    public Set<GraphNode<L>> getPredecessorNodesOf(int i) {
        // TODO implementare
        return null;
    }

    @Override
    public Set<GraphEdge<L>> getEdgesOf(GraphNode<L> node) {
        // TODO implementare
        return null;
    }

    @Override
    public Set<GraphEdge<L>> getEdgesOf(L label) {
        // TODO implementare
        return null;
    }

    @Override
    public Set<GraphEdge<L>> getEdgesOf(int i) {
        // TODO implementare
        return null;
    }

    @Override
    public Set<GraphEdge<L>> getIngoingEdgesOf(GraphNode<L> node) {
        // TODO implementare
        return null;
    }

    @Override
    public Set<GraphEdge<L>> getIngoingEdgesOf(L label) {
        // TODO implementare
        return null;
    }

    @Override
    public Set<GraphEdge<L>> getIngoingEdgesOf(int i) {
        // TODO implementare
        return null;
    }

    @Override
    public Set<GraphEdge<L>> getEdges() {
        // TODO implementare
        return null;
    }
}
