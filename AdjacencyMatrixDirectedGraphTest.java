package it.unicam.cs.asdl2122.pt1;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

//ATTENZIONE: è vietato includere import a pacchetti che non siano della Java SE

/**
 * Classe di test per la classe AdjacencyMatrixDirectedGraph.
 * 
 * @author Luca Tesei
 */
class AdjacencyMatrixDirectedGraphTest {

    @Test
    final void testAdjacencyMatrixUndirectedGraph() {
        Graph<String> g = new AdjacencyMatrixDirectedGraph<>();
        assertTrue(g.isEmpty());
    }

    @Test
    final void testNodeCount() {
        Graph<String> g = new AdjacencyMatrixDirectedGraph<>();
        assertEquals(0, g.nodeCount());
        g.addNode(new GraphNode<>("s"));
        assertEquals(1, g.nodeCount());
        g.addNode(new GraphNode<>("u"));
        assertEquals(2, g.nodeCount());
    }

    @Test
    final void testEdgeCount() {
        Graph<String> g = new AdjacencyMatrixDirectedGraph<>();
        assertEquals(0, g.edgeCount());
        GraphNode<String> ns = new GraphNode<>("s");
        g.addNode(ns);
        assertEquals(0, g.edgeCount());
        GraphNode<String> nu = new GraphNode<>("u");
        g.addNode(nu);
        GraphEdge<String> esu = new GraphEdge<>(ns, nu, true, 10.1);
        g.addEdge(esu);
        assertEquals(1, g.edgeCount());
        g.addEdge(esu);
        assertEquals(1, g.edgeCount());
        GraphNode<String> nx = new GraphNode<>("x");
        g.addNode(nx);
        GraphEdge<String> esx = new GraphEdge<>(ns, nx, true, 5.12);
        g.addEdge(esx);
        assertEquals(2, g.edgeCount());
    }

    @Test
    final void testSize() {
        Graph<String> g = new AdjacencyMatrixDirectedGraph<>();
        assertEquals(0, g.size());
        GraphNode<String> ns = new GraphNode<>("s");
        g.addNode(ns);
        assertEquals(1, g.size());
        GraphNode<String> nu = new GraphNode<>("u");
        g.addNode(nu);
        assertEquals(2, g.size());
        GraphEdge<String> esu = new GraphEdge<>(ns, nu, true);
        g.addEdge(esu);
        assertEquals(3, g.size());
        GraphNode<String> nx = new GraphNode<>("x");
        g.addNode(nx);
        assertEquals(4, g.size());
        GraphEdge<String> esx = new GraphEdge<>(ns, nx, true, 5.12);
        g.addEdge(esx);
        assertEquals(5, g.size());
        GraphEdge<String> eux = new GraphEdge<>(nu, nx, true, 2.05);
        g.addEdge(eux);
        assertEquals(6, g.size());
        g.addEdge(new GraphEdge<>(nu, nx, true, 5.05));
        assertEquals(6, g.size());
        g.clear();
        assertEquals(0, g.size());
    }

    @Test
    final void testIsEmpty() {
        Graph<String> g = new AdjacencyMatrixDirectedGraph<>();
        assertTrue(g.isEmpty());
        GraphNode<String> ns = new GraphNode<>("s");
        g.addNode(ns);
        assertFalse(g.isEmpty());
        g.clear();
        assertTrue(g.isEmpty());
    }

    @Test
    final void testClear() {
        Graph<String> g = new AdjacencyMatrixDirectedGraph<>();
        assertTrue(g.isEmpty());
        GraphNode<String> ns = new GraphNode<>("s");
        g.addNode(ns);
        assertFalse(g.isEmpty());
        GraphNode<String> nu = new GraphNode<>("u");
        g.addNode(nu);
        GraphEdge<String> esu = new GraphEdge<>(ns, nu, true, 10.1);
        g.addEdge(esu);
        assertFalse(g.isEmpty());
        g.clear();
        assertTrue(g.isEmpty());
    }

    @Test
    final void testIsDirected() {
        Graph<String> g = new AdjacencyMatrixDirectedGraph<>();
        assertTrue(g.isDirected());
    }

    @Test
    final void testAddNode() {
        Graph<String> g = new AdjacencyMatrixDirectedGraph<>();
        assertThrows(NullPointerException.class,
                () -> g.addNode((GraphNode<String>) null));
        assertThrows(NullPointerException.class,
                () -> g.addNode((String) null));
        GraphNode<String> ns = new GraphNode<>("s");
        GraphNode<String> nsTest = new GraphNode<>("s");
        assertNull(g.getNode(ns));
        g.addNode(ns);
        assertNotNull(g.getNode(nsTest));
        String lu = "u";
        String luTest = "u";
        assertNull(g.getNode(luTest));
        g.addNode(lu);
        assertNotNull(g.getNode(luTest));
    }

    @Test
    final void testRemoveNode() {
        Graph<String> g = new AdjacencyMatrixDirectedGraph<>();
        assertThrows(NullPointerException.class,
                () -> g.removeNode((GraphNode<String>) null));
        assertThrows(NullPointerException.class,
                () -> g.removeNode((String) null));
        assertThrows(IndexOutOfBoundsException.class, () -> g.removeNode(0));
        g.addNode("a");
        g.addNode("b");
        g.addNode(new GraphNode<>("c"));
        g.addNode("d");
        g.addEdge("a", "b");
        g.addEdge("b", "c");
        g.addEdge("a", "a");
        g.addEdge("b", "d");
        g.addEdge("a", "d");
        g.addEdge("c", "d");
        assertEquals(0, g.getNodeIndexOf("a"));
        assertEquals(1, g.getNodeIndexOf("b"));
        assertEquals(2, g.getNodeIndexOf("c"));
        assertEquals(3, g.getNodeIndexOf("d"));
        assertEquals(4, g.nodeCount());
        assertThrows(IllegalArgumentException.class, () -> g.removeNode("e"));
        assertThrows(IndexOutOfBoundsException.class, () -> g.removeNode(4));
        g.removeNode("b");
        assertEquals(0, g.getNodeIndexOf("a"));
        assertEquals(1, g.getNodeIndexOf("c"));
        assertEquals(2, g.getNodeIndexOf("d"));
        assertEquals(3, g.nodeCount());
        assertNull(g.getNode("b"));
        // Controlla che la matrice sia ancora quadrata e non ci siano buchi
        assertDoesNotThrow(() -> {
            for (int i = 0; i < g.nodeCount(); i++)
                for (int j = 0; j < g.nodeCount(); j++)
                    g.getEdge(i, j);
        });
        assertNotNull(g.getEdge("a", "a"));
        assertNotNull(g.getEdge("a", "d"));
        assertNotNull(g.getEdge("c", "d"));
        assertNull(g.getEdge("c", "a"));
        assertNull(g.getEdge("d", "d"));
        assertNull(g.getEdge("c", "c"));
        g.removeNode(0);
        assertEquals(0, g.getNodeIndexOf("c"));
        assertEquals(1, g.getNodeIndexOf("d"));
        assertEquals(2, g.nodeCount());
        assertNull(g.getNode("a"));
        // Controlla che la matrice sia ancora quadrata e non ci siano buchi
        assertDoesNotThrow(() -> {
            for (int i = 0; i < g.nodeCount(); i++)
                for (int j = 0; j < g.nodeCount(); j++)
                    g.getEdge(i, j);
        });
        assertNotNull(g.getEdge("c", "d"));
        assertNull(g.getEdge("d", "d"));
        assertNull(g.getEdge("c", "c"));
    }

    @Test
    final void testGetNode() {
        Graph<String> g = new AdjacencyMatrixDirectedGraph<>();
        assertThrows(NullPointerException.class,
                () -> g.getNode((GraphNode<String>) null));
        assertThrows(NullPointerException.class,
                () -> g.getNode((String) null));
        GraphNode<String> ns = new GraphNode<>("s");
        GraphNode<String> nsTest = new GraphNode<>("s");
        assertNull(g.getNode(nsTest));
        g.addNode(ns);
        assertNotNull(g.getNode(nsTest));
        g.addNode("a");
        GraphNode<String> na = g.getNode("a");
        assertNotNull(na);
        na.setColor(GraphNode.COLOR_BLACK);
        assertEquals(g.getNode("a").getColor(), GraphNode.COLOR_BLACK);
        assertFalse(g.addNode("a"));
        assertEquals(g.getNode(na).getColor(), GraphNode.COLOR_BLACK);
        assertNull(g.getNode("b"));
    }

    @Test
    final void testGetNodeInt() {
        Graph<String> g = new AdjacencyMatrixDirectedGraph<>();
        assertThrows(IndexOutOfBoundsException.class, () -> g.getNode(0));
        GraphNode<String> ns = new GraphNode<>("s");
        ns.setColor(1);
        g.addNode(ns);
        assertThrows(IndexOutOfBoundsException.class, () -> g.getNode(1));
        GraphNode<String> nsTest = new GraphNode<>("s");
        assertEquals(nsTest, g.getNode(0));
        assertEquals(1, g.getNode(0).getColor());
        GraphNode<String> nu = new GraphNode<>("u");
        g.addNode(nu);
        assertThrows(IndexOutOfBoundsException.class, () -> g.getNode(2));
        GraphNode<String> nuTest = new GraphNode<>("u");
        assertEquals(nuTest, g.getNode(1));
    }

    @Test
    final void testGetNodeIndexOf() {
        Graph<String> g = new AdjacencyMatrixDirectedGraph<>();
        assertThrows(NullPointerException.class,
                () -> g.getNodeIndexOf((GraphNode<String>) null));
        assertThrows(NullPointerException.class,
                () -> g.getNodeIndexOf((String) null));
        GraphNode<String> ns = new GraphNode<>("s");
        ns.setColor(1);
        g.addNode(ns);
        assertEquals(0, g.getNodeIndexOf("s"));
        assertThrows(IllegalArgumentException.class,
                () -> g.getNodeIndexOf("u"));
        GraphNode<String> nu = new GraphNode<>("u");
        g.addNode(nu);
        assertEquals(1, g.getNodeIndexOf("u"));
        assertEquals(0, g.getNodeIndexOf("s"));
        g.addNode("x");
        assertEquals(2, g.getNodeIndexOf("x"));
        g.addEdge("s", "x");
        assertEquals(0, g.getNodeIndexOf("s"));
        g.removeNode(nu);
        assertThrows(IllegalArgumentException.class,
                () -> g.getNodeIndexOf("u"));
        assertEquals(0, g.getNodeIndexOf("s"));
        assertFalse(g.addNode("s"));
        assertFalse(g.addNode("x"));
        assertEquals(1, g.getNodeIndexOf("x"));
        g.removeNode("s");
        assertThrows(IllegalArgumentException.class,
                () -> g.getNodeIndexOf("s"));
        assertEquals(0, g.getNodeIndexOf("x"));
    }

    @Test
    final void testGetNodes() {
        Graph<String> g = new AdjacencyMatrixDirectedGraph<>();
        Set<GraphNode<String>> nodes = g.getNodes();
        assertTrue(nodes.isEmpty());
        GraphNode<String> ns = new GraphNode<>("s");
        g.addNode(ns);
        GraphNode<String> nu = new GraphNode<>("u");
        g.addNode(nu);
        nodes = g.getNodes();
        Set<GraphNode<String>> testNodes = new HashSet<>();
        GraphNode<String> nsTest = new GraphNode<>("s");
        GraphNode<String> nuTest = new GraphNode<>("u");
        testNodes.add(nuTest);
        testNodes.add(nsTest);
        assertEquals(nodes, testNodes);
        GraphNode<String> nuTestBis = new GraphNode<>("u");
        g.addNode(nuTestBis);
        nodes = g.getNodes();
        assertEquals(nodes, testNodes);
    }

    @Test
    final void testAddEdge() {
        Graph<String> g = new AdjacencyMatrixDirectedGraph<>();
        assertThrows(NullPointerException.class, () -> g.addEdge(null));
        GraphNode<String> ns = new GraphNode<>("s");
        g.addNode(ns);
        GraphNode<String> nu = new GraphNode<>("u");
        assertThrows(IllegalArgumentException.class,
                () -> g.addEdge(new GraphEdge<>(ns, nu, true)));
        assertThrows(IllegalArgumentException.class,
                () -> g.addEdge(new GraphEdge<>(nu, ns, true)));
        g.addNode(nu);
        assertThrows(IllegalArgumentException.class,
                () -> g.addEdge(new GraphEdge<>(ns, nu, false)));
        GraphEdge<String> esu = new GraphEdge<>(ns, nu, true);
        assertTrue(g.addEdge(esu));
        assertNotNull(g.getEdge(new GraphEdge<>(ns, nu, true)));
        assertFalse(g.addEdge(new GraphEdge<>(ns, nu, true, 6.0)));
        g.addNode("x");
        assertTrue(g.addEdge("x", "s"));
        assertNotNull(g.getEdge("x", "s"));
        g.addNode("t");
        assertTrue(g.addWeightedEdge("s", "t", 5.0));
        GraphEdge<String> est = g.getEdge("s", "t");
        assertNotNull(est);
        assertEquals(5, est.getWeight());
        GraphNode<String> nw = new GraphNode<>("w");
        g.addNode(nw);
        assertTrue(g.addWeightedEdge(nw, nu, 4.0));
        assertEquals(4, g.getEdge("w", "u").getWeight());
        assertFalse(g.addEdge("w", "u"));
    }

    @Test
    final void testRemoveEdge() {
        Graph<String> g = new AdjacencyMatrixDirectedGraph<>();
        assertThrows(NullPointerException.class,
                () -> g.removeEdge( null));
        GraphNode<String> ns = new GraphNode<>("s");
        assertThrows(NullPointerException.class,
                () -> g.removeEdge( null, ns));
        assertThrows(NullPointerException.class,
                () -> g.removeEdge(ns, null));
        g.addNode(ns);
        g.addNode("a");
        g.addEdge("s", "a");
        GraphNode<String> nt = new GraphNode<>("t");
        assertThrows(IllegalArgumentException.class,
                () -> g.removeEdge(ns, nt));
        assertThrows(IllegalArgumentException.class,
                () -> g.removeEdge(nt, ns));
        g.addNode(nt);
        assertThrows(IllegalArgumentException.class,
                () -> g.removeEdge(ns, nt));
        g.addEdge("t", "s");
        assertNotNull(g.getEdge("s", "a"));
        g.removeEdge("s", "a");
        assertNull(g.getEdge("s", "a"));
        GraphEdge<String> ets = new GraphEdge<>(nt, ns, true);
        assertNotNull(g.getEdge(ets));
        g.removeEdge(ets);
        assertNull(g.getEdge(ets));
        g.addEdge("a", "t");
        int i = g.getNodeIndexOf("a");
        int j = g.getNodeIndexOf(nt);
        assertNotNull(g.getEdge(i, j));
        g.removeEdge(i, j);
        assertNull(g.getEdge(i, j));
    }

    @Test
    final void testGetEdge() {
        Graph<String> graph = new AdjacencyMatrixDirectedGraph<>();
        //getEdge richiama NullPointerException quando viene passato un parametro nullo
        assertThrows(NullPointerException.class,
                () -> graph.getEdge(null));

        GraphNode<String> node1 = new GraphNode<>("a");
        GraphNode<String> node2 = new GraphNode<>("b");
        GraphNode<String> node3 = new GraphNode<>("c");
        GraphNode<String> node4 = new GraphNode<>("d");
        GraphNode<String> node5 = new GraphNode<>("e");

        graph.addNode(node1);
        graph.addNode(node2);
        graph.addNode(node3);
        graph.addNode(node4);
        graph.addNode(node5);

        GraphEdge<String> edge1 = new GraphEdge<>(node1, node3, graph.isDirected());
        GraphEdge<String> edge2 = new GraphEdge<>(node2, node4, graph.isDirected());
        GraphEdge<String> edge3 = new GraphEdge<>(node2, node5, graph.isDirected());
        GraphEdge<String> edge4 = new GraphEdge<>(node3, node2, graph.isDirected());
        GraphEdge<String> edge5 = new GraphEdge<>(node4, node5, graph.isDirected());
        GraphEdge<String> edge6 = new GraphEdge<>(node5, node1, graph.isDirected());
        GraphEdge<String> edge7 = new GraphEdge<>(node5, node4, graph.isDirected());

        graph.addEdge(edge1);
        graph.addEdge(edge2);
        graph.addEdge(edge3);
        graph.addEdge(edge4);
        graph.addEdge(edge5);
        graph.addEdge(edge6);
        graph.addEdge(edge7);

        //getEdge con arco
        assertEquals(graph.getEdge(edge1), edge1);
        assertEquals(graph.getEdge(edge2), edge2);
        assertEquals(graph.getEdge(edge3), edge3);
        assertEquals(graph.getEdge(edge4), edge4);
        assertEquals(graph.getEdge(edge5), edge5);
        assertEquals(graph.getEdge(edge6), edge6);
        assertEquals(graph.getEdge(edge7), edge7);

        //getEdge con nodi
        assertEquals(graph.getEdge(new GraphEdge<>(node1,node3, true)), edge1);
        assertEquals(graph.getEdge(new GraphEdge<>(node2, node4, true)), edge2);
        assertEquals(graph.getEdge(new GraphEdge<>(node2, node5, true)), edge3);
        assertEquals(graph.getEdge(new GraphEdge<>(node3, node2, true)), edge4);
        assertEquals(graph.getEdge(new GraphEdge<>(node4, node5, true)), edge5);
        assertEquals(graph.getEdge(new GraphEdge<>(node5, node1, true)), edge6);
        assertEquals(graph.getEdge(new GraphEdge<>(node5, node4, true)), edge7);

        //getEdge con label
        assertEquals(graph.getEdge("a", "c"), edge1);
        assertEquals(graph.getEdge("b", "d"), edge2);
        assertEquals(graph.getEdge("b", "e"), edge3);
        assertEquals(graph.getEdge("c", "b"), edge4);
        assertEquals(graph.getEdge("d", "e"), edge5);
        assertEquals(graph.getEdge("e", "a"), edge6);
        assertEquals(graph.getEdge("e", "d"), edge7);

        //getEdge con indici
        assertEquals(graph.getEdge(0,2), edge1);
        assertEquals(graph.getEdge(1,3), edge2);
        assertEquals(graph.getEdge(1,4), edge3);
        assertEquals(graph.getEdge(2,1), edge4);
        assertEquals(graph.getEdge(3,4), edge5);
        assertEquals(graph.getEdge(4,0), edge6);
        assertEquals(graph.getEdge(4,3), edge7);
    }

    @Test
    final void testGetAdjacentNodesOf() {
        Graph<String> graph = new AdjacencyMatrixDirectedGraph<>();

        assertThrows(NullPointerException.class,
                () -> graph.getAdjacentNodesOf((GraphNode<String>) null));

        GraphNode<String> node1 = new GraphNode<>("a");
        GraphNode<String> node2 = new GraphNode<>("b");
        GraphNode<String> node3 = new GraphNode<>("c");
        GraphNode<String> node4 = new GraphNode<>("d");
        GraphNode<String> node5 = new GraphNode<>("e");

        graph.addNode(node1);
        graph.addNode(node2);
        graph.addNode(node3);
        graph.addNode(node4);
        graph.addNode(node5);

        assertThrows(IllegalArgumentException.class,
                () -> graph.getAdjacentNodesOf(new GraphNode<>("f")));

        assertThrows(IllegalArgumentException.class,
                () -> graph.getAdjacentNodesOf("f"));

        assertThrows(IndexOutOfBoundsException.class,
                () -> graph.getAdjacentNodesOf(6));

        GraphEdge<String> edge1 = new GraphEdge<>(node1, node3, graph.isDirected());
        GraphEdge<String> edge2 = new GraphEdge<>(node2, node4, graph.isDirected());
        GraphEdge<String> edge3 = new GraphEdge<>(node2, node5, graph.isDirected());
        GraphEdge<String> edge4 = new GraphEdge<>(node3, node2, graph.isDirected());
        GraphEdge<String> edge5 = new GraphEdge<>(node4, node5, graph.isDirected());
        GraphEdge<String> edge6 = new GraphEdge<>(node5, node1, graph.isDirected());
        GraphEdge<String> edge7 = new GraphEdge<>(node5, node4, graph.isDirected());

        graph.addEdge(edge1);
        graph.addEdge(edge2);
        graph.addEdge(edge3);
        graph.addEdge(edge4);
        graph.addEdge(edge5);
        graph.addEdge(edge6);
        graph.addEdge(edge7);

        //getAdjacentNodesOf passando un nodo
        Set<GraphNode<String>> toCompareAdjacencyOfNode1 = new HashSet<>();
        toCompareAdjacencyOfNode1.add(node3);
        assertEquals(graph.getAdjacentNodesOf(node1), toCompareAdjacencyOfNode1);

        //getAdjacentNodesOf passando una label
        Set<GraphNode<String>> toCompareAdjacencyOfNode2 = new HashSet<>();
        toCompareAdjacencyOfNode2.add(node4);
        toCompareAdjacencyOfNode2.add(node5);
        assertEquals(graph.getAdjacentNodesOf("b"), toCompareAdjacencyOfNode2);

        //getAdjacentNodesOf passando un indice
        Set<GraphNode<String>> toCompareAdjacencyOfNode3 = new HashSet<>();
        toCompareAdjacencyOfNode3.add(node2);
        assertEquals(graph.getAdjacentNodesOf(2), toCompareAdjacencyOfNode3);
    }

    @Test
    final void testGetEdgesOf() {

        Graph<String> graph = new AdjacencyMatrixDirectedGraph<>();

        assertThrows(NullPointerException.class,
                () -> graph.getEdgesOf((GraphNode<String>) null));

        GraphNode<String> node1 = new GraphNode<>("a");
        GraphNode<String> node2 = new GraphNode<>("b");
        GraphNode<String> node3 = new GraphNode<>("c");
        GraphNode<String> node4 = new GraphNode<>("d");
        GraphNode<String> node5 = new GraphNode<>("e");
        GraphNode<String> node6 = new GraphNode<>("f");


        graph.addNode(node1);
        graph.addNode(node2);
        graph.addNode(node3);
        graph.addNode(node4);
        graph.addNode(node5);

        GraphEdge<String> edge1 = new GraphEdge<>(node1, node3, graph.isDirected());
        GraphEdge<String> edge2 = new GraphEdge<>(node2, node4, graph.isDirected());
        GraphEdge<String> edge3 = new GraphEdge<>(node2, node5, graph.isDirected());
        GraphEdge<String> edge4 = new GraphEdge<>(node3, node2, graph.isDirected());
        GraphEdge<String> edge5 = new GraphEdge<>(node4, node5, graph.isDirected());
        GraphEdge<String> edge6 = new GraphEdge<>(node5, node1, graph.isDirected());
        GraphEdge<String> edge7 = new GraphEdge<>(node5, node4, graph.isDirected());


        graph.addEdge(edge1);
        graph.addEdge(edge2);
        graph.addEdge(edge3);
        graph.addEdge(edge4);
        graph.addEdge(edge5);
        graph.addEdge(edge6);
        graph.addEdge(edge7);

        assertThrows(IllegalArgumentException.class,
                () -> graph.getEdgesOf((node6)));

        assertThrows(IllegalArgumentException.class,
                () -> graph.getEdgesOf(("t")));

        assertThrows(IndexOutOfBoundsException.class,
                () -> graph.getEdgesOf((9)));

        //getEdgesOf passando un nodo
        Set<GraphEdge<String>> edgesOfNode1 = new HashSet<>();
        edgesOfNode1.add(edge1);
        assertEquals(graph.getEdgesOf(node1), edgesOfNode1);

        //getEdgesOf passando un una label
        Set<GraphEdge<String>> edgesOfNode2 = new HashSet<>();
        edgesOfNode2.add(edge2);
        edgesOfNode2.add(edge3);
        assertEquals(graph.getEdgesOf("b"), edgesOfNode2);

        //getEdgesOf passando un indice
        Set<GraphEdge<String>> edgesOfNode3 = new HashSet<>();
        edgesOfNode3.add(edge4);

        assertEquals(graph.getEdgesOf(2), edgesOfNode3);

    }

    @Test
    final void testGetEdges() {
        Graph <String> graph = new AdjacencyMatrixDirectedGraph<>();
        GraphNode<String> node1 = new GraphNode<>("a");
        GraphNode<String> node2 = new GraphNode<>("b");
        GraphNode<String> node3 = new GraphNode<>("c");
        GraphNode<String> node4 = new GraphNode<>("d");
        GraphNode<String> node5 = new GraphNode<>("e");

        graph.addNode(node1);
        graph.addNode(node2);
        graph.addNode(node3);
        graph.addNode(node4);
        graph.addNode(node5);

        GraphEdge<String> edge1 = new GraphEdge<>(node1, node3, graph.isDirected());
        GraphEdge<String> edge2 = new GraphEdge<>(node2, node4, graph.isDirected());
        GraphEdge<String> edge3 = new GraphEdge<>(node2, node5, graph.isDirected());
        GraphEdge<String> edge4 = new GraphEdge<>(node3, node2, graph.isDirected());
        GraphEdge<String> edge5 = new GraphEdge<>(node4, node5, graph.isDirected());
        GraphEdge<String> edge6 = new GraphEdge<>(node5, node1, graph.isDirected());
        GraphEdge<String> edge7 = new GraphEdge<>(node5, node4, graph.isDirected());

        graph.addEdge(edge1);
        graph.addEdge(edge2);
        graph.addEdge(edge3);
        graph.addEdge(edge4);
        graph.addEdge(edge5);
        graph.addEdge(edge6);
        graph.addEdge(edge7);

        Set<GraphEdge<String>> edges = new HashSet<>();
        edges.add(edge1);
        edges.add(edge2);
        edges.add(edge3);
        edges.add(edge4);
        edges.add(edge5);
        edges.add(edge6);
        edges.add(edge7);

        assertEquals(edges,graph.getEdges());
    }

    @Test
    final void testGetDegreeOf() {

        Graph <String> graph = new AdjacencyMatrixDirectedGraph<>();

        assertThrows(NullPointerException.class,
                () -> graph.getDegreeOf((GraphNode<String>) null));

        GraphNode<String> node1 = new GraphNode<>("a");
        GraphNode<String> node2 = new GraphNode<>("b");
        GraphNode<String> node3 = new GraphNode<>("c");
        GraphNode<String> node4 = new GraphNode<>("d");
        GraphNode<String> node5 = new GraphNode<>("e");
        GraphNode<String> node6 = new GraphNode<>("f");


        graph.addNode(node1);
        graph.addNode(node2);
        graph.addNode(node3);
        graph.addNode(node4);
        graph.addNode(node5);

        GraphEdge<String> edge1 = new GraphEdge<>(node1, node3, graph.isDirected());
        GraphEdge<String> edge2 = new GraphEdge<>(node2, node4, graph.isDirected());
        GraphEdge<String> edge3 = new GraphEdge<>(node2, node5, graph.isDirected());
        GraphEdge<String> edge4 = new GraphEdge<>(node3, node2, graph.isDirected());
        GraphEdge<String> edge5 = new GraphEdge<>(node4, node5, graph.isDirected());
        GraphEdge<String> edge6 = new GraphEdge<>(node5, node1, graph.isDirected());
        GraphEdge<String> edge7 = new GraphEdge<>(node5, node4, graph.isDirected());

        graph.addEdge(edge1);
        graph.addEdge(edge2);
        graph.addEdge(edge3);
        graph.addEdge(edge4);
        graph.addEdge(edge5);
        graph.addEdge(edge6);
        graph.addEdge(edge7);

        assertThrows(IllegalArgumentException.class,
                () -> graph.getDegreeOf((node6)));

        assertThrows(IllegalArgumentException.class,
                () -> graph.getDegreeOf(("y")));

        assertThrows(IndexOutOfBoundsException.class,
                () -> graph.getDegreeOf((9)));

        assertEquals(2,graph.getDegreeOf(node1));
    }

    @Test
    final void testGetIngoingEdgesOf() {

        Graph <String> graph = new AdjacencyMatrixDirectedGraph<>();

        assertThrows(NullPointerException.class,
                () -> graph.getIngoingEdgesOf((GraphNode<String>) null));

        GraphNode<String> node1 = new GraphNode<>("a");
        GraphNode<String> node2 = new GraphNode<>("b");
        GraphNode<String> node3 = new GraphNode<>("c");
        GraphNode<String> node4 = new GraphNode<>("d");
        GraphNode<String> node5 = new GraphNode<>("e");
        GraphNode<String> node6 = new GraphNode<>("f");


        graph.addNode(node1);
        graph.addNode(node2);
        graph.addNode(node3);
        graph.addNode(node4);
        graph.addNode(node5);

        GraphEdge<String> edge1 = new GraphEdge<>(node1, node3, graph.isDirected());
        GraphEdge<String> edge2 = new GraphEdge<>(node2, node4, graph.isDirected());
        GraphEdge<String> edge3 = new GraphEdge<>(node2, node5, graph.isDirected());
        GraphEdge<String> edge4 = new GraphEdge<>(node3, node2, graph.isDirected());
        GraphEdge<String> edge5 = new GraphEdge<>(node4, node5, graph.isDirected());
        GraphEdge<String> edge6 = new GraphEdge<>(node5, node1, graph.isDirected());
        GraphEdge<String> edge7 = new GraphEdge<>(node5, node4, graph.isDirected());

        graph.addEdge(edge1);
        graph.addEdge(edge2);
        graph.addEdge(edge3);
        graph.addEdge(edge4);
        graph.addEdge(edge5);
        graph.addEdge(edge6);
        graph.addEdge(edge7);

        Set<GraphEdge<String>> edges = new HashSet<>();

        edges.add(edge3);
        edges.add(edge5);

        assertThrows(IllegalArgumentException.class,
                () -> graph.getIngoingEdgesOf((node6)));

        assertThrows(IllegalArgumentException.class,
                () -> graph.getIngoingEdgesOf(("y")));

        assertThrows(IndexOutOfBoundsException.class,
                () -> graph.getIngoingEdgesOf((9)));

        assertEquals(edges,graph.getIngoingEdgesOf(node5));


    }
    
    @Test
    final void testGetPredecessorNodesOf() {

        Graph <String> graph = new AdjacencyMatrixDirectedGraph<>();

        assertThrows(NullPointerException.class,
                () -> graph.getPredecessorNodesOf((GraphNode<String>) null));

        GraphNode<String> node1 = new GraphNode<>("a");
        GraphNode<String> node2 = new GraphNode<>("b");
        GraphNode<String> node3 = new GraphNode<>("c");
        GraphNode<String> node4 = new GraphNode<>("d");
        GraphNode<String> node5 = new GraphNode<>("e");

        GraphNode<String> node6 = new GraphNode<>("f");


        graph.addNode(node1);
        graph.addNode(node2);
        graph.addNode(node3);
        graph.addNode(node4);
        graph.addNode(node5);

        assertThrows(IllegalArgumentException.class,
                () -> graph.getPredecessorNodesOf((node6)));

        assertThrows(IllegalArgumentException.class,
                () -> graph.getPredecessorNodesOf(("y")));

        assertThrows(IndexOutOfBoundsException.class,
                () -> graph.getPredecessorNodesOf((9)));

        GraphEdge<String> edge1 = new GraphEdge<>(node1, node3, graph.isDirected());
        GraphEdge<String> edge2 = new GraphEdge<>(node2, node4, graph.isDirected());
        GraphEdge<String> edge3 = new GraphEdge<>(node2, node5, graph.isDirected());
        GraphEdge<String> edge4 = new GraphEdge<>(node3, node2, graph.isDirected());
        GraphEdge<String> edge5 = new GraphEdge<>(node4, node5, graph.isDirected());
        GraphEdge<String> edge6 = new GraphEdge<>(node5, node1, graph.isDirected());
        GraphEdge<String> edge7 = new GraphEdge<>(node5, node4, graph.isDirected());

        graph.addEdge(edge1);
        graph.addEdge(edge2);
        graph.addEdge(edge3);
        graph.addEdge(edge4);
        graph.addEdge(edge5);
        graph.addEdge(edge6);
        graph.addEdge(edge7);

        Set<GraphNode<String>> predecessorNodes = new HashSet<>();
        predecessorNodes.add(node2);
        predecessorNodes.add(node4);


        assertEquals(predecessorNodes,graph.getPredecessorNodesOf(node5));
    }
}
