/**
 * 
 */
package it.unicam.cs.asdl2122.pt1;


// TODO completare gli import necessari

// ATTENZIONE: è vietato includere import a pacchetti che non siano della Java SE

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Iterator;
import java.util.HashSet;

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
     * Matrice di adiacenza, gli elementi sono null od oggetti della classe
     * GraphEdge<L>. L'uso di ArrayList permette alla matrice di aumentare di
     * dimensione gradualmente a ogni inserimento di un nuovo nodo e di
     * ridimensionarsi se un nodo viene cancellato.
     */
    protected ArrayList<ArrayList<GraphEdge<L>>> matrix;

    /**
     * Crea un grafo vuoto.
     */
    public AdjacencyMatrixDirectedGraph() {
        this.matrix = new ArrayList<>();
        this.nodesIndex = new HashMap<>();
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
        if(node == null)
            throw new NullPointerException("Impossibile aggiungere un nodo nullo.");

        //se il nodo è già presente ritorno false
        if(this.nodesIndex.containsKey(node))
            return false;

        //se la chiave non corrisponde a nessun valore, o è null, viene associata
        //a un valore specifico e restituisce null, altrimenti restituisce il valore corrente
        this.nodesIndex.putIfAbsent(node, this.nodeCount());

        // toInsert sara' il nuovo array list di un nodo
        //ogni arrayList di un nodo conterra' solo i nodi a cui e' collegato
        ArrayList<GraphEdge<L>> toInsert = new ArrayList<>();
        matrix.add(toInsert);

        return true;
    }

    /*
     * Gli indici dei nodi vanno assegnati nell'ordine d'inserimento a partire
     * da zero
     */
    @Override
    public boolean addNode(L label) {
        return this.addNode(new GraphNode<>(label) );
    }

    /*
     * Gli indici dei nodi il cui valore sia maggiore dell'indice del nodo da
     * cancellare devono essere decrementati di uno dopo la cancellazione del
     * nodo
     */
    @Override
    public void removeNode(GraphNode<L> node) {

        if(node == null)
            throw new NullPointerException("Nodo passato e' nullo");

        if(!nodesIndex.containsKey(node))
            throw new IllegalArgumentException("Il nodo onon esiste in questo grafo");

        int indexApp= -1;

        Iterator<Map.Entry<GraphNode<L>, Integer>> it = nodesIndex.entrySet().iterator();
        //variabile di appoggio per il valore del iterator
        Map.Entry<GraphNode<L>, Integer> app;
        while(it.hasNext())
        {
            app = it.next();
            if(app.getKey().equals(node)) {
                //indexApp = app.getValue();
                matrix.remove((int) app.getValue());
                //nodesIndex.remove(node);
            }
            //if(indexApp > app.getValue()) app.setValue(app.getValue() - 1);
        }

        for(ArrayList<GraphEdge<L>> edges : this.matrix) {
            for(GraphEdge<L> edge : edges) {
                if(edge.getNode2().equals(node)) edges.remove(edge);
            }
        }
        nodesIndex.remove(node);
        int index = 0;
        while(it.hasNext())
        {
            it.next().setValue(index);
            index++;
        }

    }

    /*
     * Gli indici dei nodi il cui valore sia maggiore dell'indice del nodo da
     * cancellare devono essere decrementati di uno dopo la cancellazione del
     * nodo
     */
    @Override
    public void removeNode(L label) {
        this.removeNode(this.getNode(label));
    }

    /*
     * Gli indici dei nodi il cui valore sia maggiore dell'indice del nodo da
     * cancellare devono essere decrementati di uno dopo la cancellazione del
     * nodo
     */
    @Override
    public void removeNode(int i) {
        this.removeNode(this.getNode(i));
    }

    @Override
    public GraphNode<L> getNode(GraphNode<L> node) {
        if (node == null)
            throw new NullPointerException("Nodo nullo.");
        //confronta tutti i nodi esistenti in indexNode
        for (GraphNode<L> element : nodesIndex.keySet()) {
            if (element.equals(node)) {
                return element;
            }
        }
        return null;
    }

    @Override
    public GraphNode<L> getNode(L label) {
        return getNode(new GraphNode<>(label));
    }

    @Override
    public GraphNode<L> getNode(int i) {
        if (i < 0 || i > nodeCount()-1)
            throw new IndexOutOfBoundsException("Fuori dai limiti dell'intervallo");

        //creo un iteratore per poter scorrere intera mappa ed estrarre il nodo tramite indice d'inserimento
        Iterator<Map.Entry<GraphNode<L>, Integer>> it = nodesIndex.entrySet().iterator();
        //variabile di appoggio per il valore del iterator
        Map.Entry<GraphNode<L>, Integer> app;
        while(it.hasNext())
        {
            app = it.next();
            if(app.getValue() == i)
                return app.getKey();
        }
        return null;
    }

    @Override
    public int getNodeIndexOf(GraphNode<L> node) {

        if(node == null)
            throw new NullPointerException("Nodo nullo");

        if(!this.nodesIndex.containsKey(node))
            throw new IllegalArgumentException("Il nodo passato non esiste in questo grafo");

        //creo un iteratore per poter scorrere intera mappa ed estrarre il nodo tramite indice d'inserimento
        Iterator<Map.Entry<GraphNode<L>, Integer>> it = nodesIndex.entrySet().iterator();
        //variabile di appoggio per il valore del iterator
        Map.Entry<GraphNode<L>, Integer> app;
        while(it.hasNext())
        {
            app = it.next();
            if(app.getKey().equals(node))
                return app.getValue();
        }
        return -1;
    }

    @Override
    public int getNodeIndexOf(L label) {
        return this.getNodeIndexOf(new GraphNode<>(label));
    }

    @Override
    public Set<GraphNode<L>> getNodes() {
        return nodesIndex.keySet();
    }

    @Override
    public boolean addEdge(GraphEdge<L> edge) {
        if(edge == null)
            throw new NullPointerException("L'arco passato è nullo");

        if(!(nodesIndex.containsKey(edge.getNode1()) && nodesIndex.containsKey(edge.getNode2())))
            throw new IllegalArgumentException("Uno dei due nodi specificati nell'arco non esiste");

        if(!edge.isDirected())
            throw new IllegalArgumentException("Questo arco e' non orientato");

        //scorro tramite foreach tutti gli array list dentro a matrix
        for(ArrayList<GraphEdge<L>> graphEdges : matrix) {
            for (GraphEdge<L> edgeApp : graphEdges)
            {
                if(edge.equals(edgeApp))
                    return false;
            }
        }

        matrix.get(this.getNodeIndexOf(edge.getNode1())).add(edge);
        edge.getNode2().setPrevious(edge.getNode1());
        return true;
    }

    @Override
    public boolean addEdge(GraphNode<L> node1, GraphNode<L> node2) {
        return this.addEdge(new GraphEdge<>(node1, node2, true));
    }

    @Override
    public boolean addWeightedEdge(GraphNode<L> node1, GraphNode<L> node2,
            double weight) {
        return this.addEdge(new GraphEdge<>(this.getNode(node1), this.getNode(node2),true, weight));
    }

    @Override
    public boolean addEdge(L label1, L label2) {
        return this.addEdge(new GraphEdge<>(this.getNode(label1), this.getNode(label2), true));
    }

    @Override
    public boolean addWeightedEdge(L label1, L label2, double weight) {
        return this.addEdge(new GraphEdge<>(this.getNode(label1), this.getNode(label2), true, weight));
    }

    @Override
    public boolean addEdge(int i, int j) {
        return addEdge(new GraphEdge<>(this.getNode(i), this.getNode(j), true));
    }

    @Override
    public boolean addWeightedEdge(int i, int j, double weight) {
        return addEdge(new GraphEdge<>(this.getNode(i), this.getNode(j), true, weight));
    }

    @Override
    public void removeEdge(GraphEdge<L> edge) {
        if(edge == null)
            throw new NullPointerException("L'arco e' nullo");
        if(edge.getNode2() == null || edge.getNode1() == null)
            throw new IllegalArgumentException("Un nodo e' nullo");

        for(ArrayList<GraphEdge<L>> edges : this.matrix) {
            for(GraphEdge<L> edgeApp : edges) {
                if(edgeApp.equals(edge)) edges.remove(edge);
            }
        }
    }

    @Override
    public void removeEdge(GraphNode<L> node1, GraphNode<L> node2) {
        this.removeEdge(this.getEdge(this.getNode(node1), this.getNode(node2)));
    }

    @Override
    public void removeEdge(L label1, L label2) {
        this.removeEdge(this.getEdge(this.getNode(label1), this.getNode(label2)));
    }

    @Override
    public void removeEdge(int i, int j) {
        this.removeEdge(this.getEdge(this.getNode(i), this.getNode(j)));
    }

    @Override
    public GraphEdge<L> getEdge(GraphEdge<L> edge) {
        if(edge == null)
            throw new NullPointerException("Arco passato e' nullo");

        if(this.getNode(edge.getNode1()) == null || this.getNode(edge.getNode2()) == null)
            throw new IllegalArgumentException("Uno dei 2 nodi dell'arco non esiste");
        //scorro tramite foreach tutti gli array list dentro a matrix
        for(ArrayList<GraphEdge<L>> graphEdges : matrix) {
            for (GraphEdge<L> edgeApp : graphEdges)
            {
                if(edge.equals(edgeApp))
                    return edgeApp;
            }
        }
        return null;
    }

    @Override
    public GraphEdge<L> getEdge(GraphNode<L> node1, GraphNode<L> node2) {
        return this.getEdge(new GraphEdge<>(this.getNode(node1), this.getNode(node2), true));
    }

    @Override
    public GraphEdge<L> getEdge(L label1, L label2) {
        return this.getEdge(new GraphEdge<>(this.getNode(label1), this.getNode(label2), true));
    }

    @Override
    public GraphEdge<L> getEdge(int i, int j) {
        return this.getEdge(new GraphEdge<>(this.getNode(i), this.getNode(j), true));
    }

    @Override
    public Set<GraphNode<L>> getAdjacentNodesOf(GraphNode<L> node) {
        if(node == null)
            throw new NullPointerException("Nodo passato e' nullo");

        if(!this.nodesIndex.containsKey(node))
            throw new IllegalArgumentException("Il nodo passato non esiste");
        //mi creo un set per conservare piu di un nodo adiacente, per comodita' nei test il
        //prof ha consigliato di usare una HashSet
        Set<GraphNode<L>> adjacent = new HashSet<>();
        //scorro tramite foreach tutti gli array list dentro a matrix
        for(ArrayList<GraphEdge<L>> edges : this.matrix) {
            for(GraphEdge<L> edge : edges) {
                if(edge.getNode1().equals(node)) adjacent.add(edge.getNode2());
            }
        }
        return adjacent;
    }

    @Override
    public Set<GraphNode<L>> getAdjacentNodesOf(L label) {
        return getAdjacentNodesOf(new GraphNode<L>(label));
    }

    @Override
    public Set<GraphNode<L>> getAdjacentNodesOf(int i) {
        if (i < 0 || i > this.nodeCount() - 1)
            throw new IndexOutOfBoundsException("L'indice passato come parametro non è presente.");

        if (!this.isDirected())
            throw new UnsupportedOperationException("Operazione non supportata da un grafo non diretto.");

        return getAdjacentNodesOf(this.getNode(i));
    }

    @Override
    public Set<GraphNode<L>> getPredecessorNodesOf(GraphNode<L> node) {
        if(node == null)
            throw new NullPointerException("Nodo passato e' nullo");

        if(!this.nodesIndex.containsKey(node))
            throw new IllegalArgumentException("Il nodo passato non esiste");

        if(!this.isDirected())
            throw new UnsupportedOperationException("Questo e' un grafo non orientato");
        //mi creo un set per conservare piu di un nodo adiacente, per comodita' nei test il
        //prof ha consigliato di usare una HashSet
        Set<GraphNode<L>> predecessor = new HashSet<>();
        //scorro tramite foreach tutti gli array list dentro a matrix
        for(ArrayList<GraphEdge<L>> edges : this.matrix) {
            for(GraphEdge<L> edge : edges) {
                if(edge.getNode2().equals(node)) predecessor.add(edge.getNode1());
            }
        }
        return predecessor;
    }

    @Override
    public Set<GraphNode<L>> getPredecessorNodesOf(L label) {
        return getPredecessorNodesOf(this.getNode(label));
    }

    @Override
    public Set<GraphNode<L>> getPredecessorNodesOf(int i) {
        return getPredecessorNodesOf(this.getNode(i));
    }

    @Override
    public Set<GraphEdge<L>> getEdgesOf(GraphNode<L> node) {
        Set<GraphEdge<L>> edgesApp = new HashSet<>();
        //scorro tramite foreach tutti gli array list dentro a matrix
        for(ArrayList<GraphEdge<L>> edges : this.matrix) {
            for(GraphEdge<L> edge : edges) {
                if(edge.getNode1().equals(node)) edgesApp.add(edge);
            }
        }
        return edgesApp;
    }

    @Override
    public Set<GraphEdge<L>> getEdgesOf(L label) {
        return this.getEdgesOf(this.getNode(label));
    }

    @Override
    public Set<GraphEdge<L>> getEdgesOf(int i) {
        return this.getEdgesOf(this.getNode(i));
    }

    @Override
    public Set<GraphEdge<L>> getIngoingEdgesOf(GraphNode<L> node) {
        Set<GraphEdge<L>> edgesApp = new HashSet<>();
        //scorro tramite foreach tutti gli array list dentro a matrix
        for(ArrayList<GraphEdge<L>> edges : this.matrix) {
            for(GraphEdge<L> edge : edges) {
                if(edge.getNode2().equals(node)) edgesApp.add(edge);
            }
        }
        return edgesApp;
    }

    @Override
    public Set<GraphEdge<L>> getIngoingEdgesOf(L label) {
        return this.getIngoingEdgesOf(this.getNode(label));
    }

    @Override
    public Set<GraphEdge<L>> getIngoingEdgesOf(int i) {
        return this.getIngoingEdgesOf(this.getNode(i));
    }

    @Override
    public Set<GraphEdge<L>> getEdges() {
        Set<GraphEdge<L>> edgesApp = new HashSet<>();
        //scorro tramite foreach tutti gli array list dentro a matrix
        for(ArrayList<GraphEdge<L>> edges : this.matrix) {
            edgesApp.addAll(edges);
        }
        return edgesApp;
    }
}
