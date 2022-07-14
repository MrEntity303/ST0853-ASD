package it.unicam.cs.asdl2122.pt1;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * 
 * @author Template: Luca Tesei
 *
 */
class BellmanFordShortestPathComputerTest {

    @Test
    final void testBellmanFordShortestPathComputer() {
        Graph<String> graph = new AdjacencyMatrixDirectedGraph<>();
        GraphNode<String> nodeA = new GraphNode<>("A");
        graph.addNode(nodeA);
        GraphNode<String> nodeB = new GraphNode<>("B");
        graph.addNode(nodeB);
        GraphNode<String> nodeC = new GraphNode<>("C");
        graph.addNode(nodeC);
        GraphNode<String> nodeD = new GraphNode<>("D");
        graph.addNode(nodeD);
        GraphNode<String> nodeE = new GraphNode<>("E");
        graph.addNode(nodeE);

        graph.addEdge(new GraphEdge<>(nodeA, nodeB, true, 6));
        graph.addEdge(new GraphEdge<>(nodeA, nodeC, true, 7));
        graph.addEdge(new GraphEdge<>(nodeB, nodeD, true, 5));
        graph.addEdge(new GraphEdge<>(nodeB, nodeE, true, -4));
        graph.addEdge(new GraphEdge<>(nodeB, nodeC, true, 8));
        graph.addEdge(new GraphEdge<>(nodeC, nodeD, true, -3));
        graph.addEdge(new GraphEdge<>(nodeC, nodeE, true, 9));
        graph.addEdge(new GraphEdge<>(nodeD, nodeB, true, -2));
        graph.addEdge(new GraphEdge<>(nodeE, nodeD, true, 7));
        graph.addEdge(new GraphEdge<>(nodeE, nodeA, true, 2));

        // Check delle Exceptions
        // Parametro null
        assertThrows(NullPointerException.class, () -> {
            new BellmanFordShortestPathComputer<>(null);
        });
        // Grafo Vuoto
        assertThrows(IllegalArgumentException.class, () -> {
            Graph<String> g = new AdjacencyMatrixDirectedGraph<>();
            new BellmanFordShortestPathComputer<>(g);
        });
        // Archi non pesati
        assertThrows(IllegalArgumentException.class, () -> {
            Graph<String> g = new AdjacencyMatrixDirectedGraph<>();
            GraphNode<String> node0 = new GraphNode<>("0");
            g.addNode(node0);
            GraphNode<String> node1 = new GraphNode<>("1");
            g.addNode(node1);
            GraphNode<String> node2 = new GraphNode<>("2");
            g.addNode(node2);
            g.addEdge(new GraphEdge<>(node0, node2, true));
            g.addEdge(new GraphEdge<>(node1, node2, true, 2));
            new BellmanFordShortestPathComputer<>(g);
        });

        BellmanFordShortestPathComputer<String> alg = new BellmanFordShortestPathComputer<>(
                graph);
        assertFalse(alg.isComputed());
        alg.computeShortestPathsFrom(nodeA);
        assertTrue(alg.isComputed());
        assertEquals(nodeA, alg.getLastSource());
    }

    @Test
    final void testComputeShortestPathsFrom() {
        Graph<String> graph = new AdjacencyMatrixDirectedGraph<>();
        GraphNode<String> nodeA = new GraphNode<>("A");
        graph.addNode(nodeA);
        GraphNode<String> nodeB = new GraphNode<>("B");
        graph.addNode(nodeB);
        GraphNode<String> nodeC = new GraphNode<>("C");
        graph.addNode(nodeC);
        GraphNode<String> nodeD = new GraphNode<>("D");
        graph.addNode(nodeD);
        GraphNode<String> nodeE = new GraphNode<>("E");
        graph.addNode(nodeE);

        graph.addEdge(new GraphEdge<>(nodeA, nodeB, true, 6));
        graph.addEdge(new GraphEdge<>(nodeA, nodeC, true, 7));
        graph.addEdge(new GraphEdge<>(nodeB, nodeD, true, 5));
        graph.addEdge(new GraphEdge<>(nodeB, nodeE, true, -4));
        graph.addEdge(new GraphEdge<>(nodeB, nodeC, true, 8));
        graph.addEdge(new GraphEdge<>(nodeC, nodeD, true, -3));
        graph.addEdge(new GraphEdge<>(nodeC, nodeE, true, 9));
        graph.addEdge(new GraphEdge<>(nodeD, nodeB, true, -2));
        graph.addEdge(new GraphEdge<>(nodeE, nodeD, true, 7));
        graph.addEdge(new GraphEdge<>(nodeE, nodeA, true, 2));

        BellmanFordShortestPathComputer<String> alg = new BellmanFordShortestPathComputer<>(
                graph);
        // Check delle Exceptions
        // Parametro null
        assertThrows(NullPointerException.class,
                () -> alg.computeShortestPathsFrom(null));
        // Non non esistente nel grafo
        assertThrows(IllegalArgumentException.class, () -> {
            GraphNode<String> node0 = new GraphNode<>("0");
            alg.computeShortestPathsFrom(node0);
        });

        graph.removeEdge(new GraphEdge<>(nodeB, nodeD, true, -3));
        graph.addEdge(new GraphEdge<>(nodeB, nodeD, true, 5));
        alg.computeShortestPathsFrom(nodeA);
        assertEquals(0, graph.getNode("A").getFloatingPointDistance());
        assertEquals(2, graph.getNode("B").getFloatingPointDistance());
        assertEquals(7, graph.getNode("C").getFloatingPointDistance());
        assertEquals(4, graph.getNode("D").getFloatingPointDistance());
        assertEquals(-2, graph.getNode("E").getFloatingPointDistance());
    }

    @Test
    final void testIsComputed() {
        Graph<String> graph = new AdjacencyMatrixDirectedGraph<>();
        GraphNode<String> nodeA = new GraphNode<>("A");
        graph.addNode(nodeA);
        GraphNode<String> nodeB = new GraphNode<>("B");
        graph.addNode(nodeB);
        GraphNode<String> nodeC = new GraphNode<>("C");
        graph.addNode(nodeC);
        GraphNode<String> nodeD = new GraphNode<>("D");
        graph.addNode(nodeD);
        GraphNode<String> nodeE = new GraphNode<>("E");
        graph.addNode(nodeE);

        graph.addEdge(new GraphEdge<>(nodeA, nodeB, true, 6));
        graph.addEdge(new GraphEdge<>(nodeA, nodeC, true, 7));
        graph.addEdge(new GraphEdge<>(nodeB, nodeD, true, 5));
        graph.addEdge(new GraphEdge<>(nodeB, nodeE, true, -4));
        graph.addEdge(new GraphEdge<>(nodeB, nodeC, true, 8));
        graph.addEdge(new GraphEdge<>(nodeC, nodeD, true, -3));
        graph.addEdge(new GraphEdge<>(nodeC, nodeE, true, 9));
        graph.addEdge(new GraphEdge<>(nodeD, nodeB, true, -2));
        graph.addEdge(new GraphEdge<>(nodeE, nodeD, true, 7));
        graph.addEdge(new GraphEdge<>(nodeE, nodeA, true, 2));
        BellmanFordShortestPathComputer<String> alg = new BellmanFordShortestPathComputer<>(
                graph);
        assertFalse(alg.isComputed());
        alg.computeShortestPathsFrom(nodeA);
        assertTrue(alg.isComputed());
    }

    @Test
    final void testGetLastSource() {
        Graph<String> graph = new AdjacencyMatrixDirectedGraph<>();
        GraphNode<String> nodeA = new GraphNode<>("A");
        graph.addNode(nodeA);
        GraphNode<String> nodeB = new GraphNode<>("B");
        graph.addNode(nodeB);
        GraphNode<String> nodeC = new GraphNode<>("C");
        graph.addNode(nodeC);
        GraphNode<String> nodeD = new GraphNode<>("D");
        graph.addNode(nodeD);
        GraphNode<String> nodeE = new GraphNode<>("E");
        graph.addNode(nodeE);

        graph.addEdge(new GraphEdge<>(nodeA, nodeB, true, 6));
        graph.addEdge(new GraphEdge<>(nodeA, nodeC, true, 7));
        graph.addEdge(new GraphEdge<>(nodeB, nodeD, true, 5));
        graph.addEdge(new GraphEdge<>(nodeB, nodeE, true, -4));
        graph.addEdge(new GraphEdge<>(nodeB, nodeC, true, 8));
        graph.addEdge(new GraphEdge<>(nodeC, nodeD, true, -3));
        graph.addEdge(new GraphEdge<>(nodeC, nodeE, true, 9));
        graph.addEdge(new GraphEdge<>(nodeD, nodeB, true, -2));
        graph.addEdge(new GraphEdge<>(nodeE, nodeD, true, 7));
        graph.addEdge(new GraphEdge<>(nodeE, nodeA, true, 2));
        BellmanFordShortestPathComputer<String> alg = new BellmanFordShortestPathComputer<>(
                graph);
        assertThrows(IllegalStateException.class, () -> alg.getLastSource());
        alg.computeShortestPathsFrom(nodeA);
        assertEquals(nodeA, alg.getLastSource());
    }

    @Test
    final void testGetGraph() {
        Graph<String> graph = new AdjacencyMatrixDirectedGraph<>();
        GraphNode<String> nodeA = new GraphNode<>("A");
        graph.addNode(nodeA);
        GraphNode<String> nodeB = new GraphNode<>("B");
        graph.addNode(nodeB);
        GraphNode<String> nodeC = new GraphNode<>("C");
        graph.addNode(nodeC);
        GraphNode<String> nodeD = new GraphNode<>("D");
        graph.addNode(nodeD);
        GraphNode<String> nodeE = new GraphNode<>("E");
        graph.addNode(nodeE);

        graph.addEdge(new GraphEdge<>(nodeA, nodeB, true, 6));
        graph.addEdge(new GraphEdge<>(nodeA, nodeC, true, 7));
        graph.addEdge(new GraphEdge<>(nodeB, nodeD, true, 5));
        graph.addEdge(new GraphEdge<>(nodeB, nodeE, true, -4));
        graph.addEdge(new GraphEdge<>(nodeB, nodeC, true, 8));
        graph.addEdge(new GraphEdge<>(nodeC, nodeD, true, -3));
        graph.addEdge(new GraphEdge<>(nodeC, nodeE, true, 9));
        graph.addEdge(new GraphEdge<>(nodeD, nodeB, true, -2));
        graph.addEdge(new GraphEdge<>(nodeE, nodeD, true, 7));
        graph.addEdge(new GraphEdge<>(nodeE, nodeA, true, 2));
        BellmanFordShortestPathComputer<String> alg = new BellmanFordShortestPathComputer<>(
                graph);
        alg.computeShortestPathsFrom(nodeA);
        assertEquals(graph, alg.getGraph());
    }

    @Test
    final void testGetShortestPathTo() {
        Graph<String> graph = new AdjacencyMatrixDirectedGraph<>();
        GraphNode<String> nodeA = new GraphNode<>("A");
        graph.addNode(nodeA);
        GraphNode<String> nodeB = new GraphNode<>("B");
        graph.addNode(nodeB);
        GraphNode<String> nodeC = new GraphNode<>("C");
        graph.addNode(nodeC);
        GraphNode<String> nodeD = new GraphNode<>("D");
        graph.addNode(nodeD);
        GraphNode<String> nodeE = new GraphNode<>("E");
        graph.addNode(nodeE);

        GraphEdge<String> edgeAB = (new GraphEdge<>(nodeA, nodeB, true, 6));
        graph.addEdge(edgeAB);
        GraphEdge<String> edgeAC = (new GraphEdge<>(nodeA, nodeC, true, 7));
        graph.addEdge(edgeAC);
        GraphEdge<String> edgeBD = (new GraphEdge<>(nodeB, nodeD, true, 5));
        graph.addEdge(edgeBD);
        GraphEdge<String> edgeBE = (new GraphEdge<>(nodeB, nodeE, true, -4));
        graph.addEdge(edgeBE);
        GraphEdge<String> edgeBC = (new GraphEdge<>(nodeB, nodeC, true, 8));
        graph.addEdge(edgeBC);
        GraphEdge<String> edgeCD = (new GraphEdge<>(nodeC, nodeD, true, -3));
        graph.addEdge(edgeCD);
        GraphEdge<String> edgeCE = (new GraphEdge<>(nodeC, nodeE, true, 9));
        graph.addEdge(edgeCE);
        GraphEdge<String> edgeDB = (new GraphEdge<>(nodeD, nodeB, true, -2));
        graph.addEdge(edgeDB);
        GraphEdge<String> edgeED = (new GraphEdge<>(nodeE, nodeD, true, 7));
        graph.addEdge(edgeED);
        GraphEdge<String> edgeEA = (new GraphEdge<>(nodeE, nodeA, true, 2));
        graph.addEdge(edgeEA);

        BellmanFordShortestPathComputer<String> alg = new BellmanFordShortestPathComputer<>(
                graph);
        alg.computeShortestPathsFrom(nodeA);
        List<GraphEdge<String>> pathA = new ArrayList<>();
        assertEquals(pathA, alg.getShortestPathTo(nodeA));
        List<GraphEdge<String>> pathB = new ArrayList<>();
        pathB.add(edgeAC);
        pathB.add(edgeCD);
        pathB.add(edgeDB);
        assertEquals(pathB, alg.getShortestPathTo(nodeB));
        List<GraphEdge<String>> pathC = new ArrayList<>();
        pathC.add(edgeAC);
        assertEquals(pathC, alg.getShortestPathTo(nodeC));
        List<GraphEdge<String>> pathD = new ArrayList<>();
        pathD.add(edgeAC);
        pathD.add(edgeCD);
        assertEquals(pathD, alg.getShortestPathTo(nodeD));
        List<GraphEdge<String>> pathE = new ArrayList<>();
        pathE.add(edgeAC);
        pathE.add(edgeCD);
        pathE.add(edgeDB);
        pathE.add(edgeBE);
        assertEquals(pathE, alg.getShortestPathTo(nodeE));
    }

    // TODO inserire ulteriori test con esempi di grafi diversi

    @Test
    final void testComputeShortestPathsFrom1() {
        Graph<String> graph = new AdjacencyMatrixDirectedGraph<>();
        GraphNode<String> nodeA = new GraphNode<>("a");
        GraphNode<String> nodeB = new GraphNode<>("b");
        GraphNode<String> nodeC = new GraphNode<>("c");
        graph.addNode(nodeA);
        graph.addNode(nodeB);
        graph.addNode(nodeC);
        GraphEdge<String> ab = new GraphEdge<>(nodeA, nodeB, true, 3);
        GraphEdge<String> bc = new GraphEdge<>(nodeB, nodeC, true, -8);
        GraphEdge<String> ca = new GraphEdge<>(nodeC, nodeA, true, 2);
        graph.addEdge(ab);
        graph.addEdge(bc);
        graph.addEdge(ca);
        BellmanFordShortestPathComputer<String> c = new BellmanFordShortestPathComputer<>(graph);

        assertThrows(NullPointerException.class, () -> c.computeShortestPathsFrom(null));
        assertThrows(IllegalArgumentException.class, () -> c.computeShortestPathsFrom(new GraphNode<>("g")));
        assertThrows(IllegalStateException.class, () -> c.computeShortestPathsFrom(nodeA));


        assertThrows(NullPointerException.class, () -> c.computeShortestPathsFrom(null));
        assertThrows(IllegalArgumentException.class, () -> c.computeShortestPathsFrom(new GraphNode<>("h")));
        assertThrows(IllegalStateException.class, () -> c.computeShortestPathsFrom(nodeB));


    }

    @Test
    final void testGetShortestPathTo1()
    {
        Graph<String> graph = new AdjacencyMatrixDirectedGraph<>();
        GraphNode<String> nodeA = new GraphNode<>("a");
        GraphNode<String> nodeB = new GraphNode<>("b");
        graph.addNode(nodeA);
        graph.addNode(nodeB);
        GraphEdge<String> edge1 = new GraphEdge<>(nodeA, nodeB, true, 10);
        graph.addEdge(edge1);
        GraphEdge<String> edge2 = new GraphEdge<>(nodeB, nodeA, true, 4);
        graph.addEdge(edge2);


        SingleSourceShortestPathComputer<String> calc = new BellmanFordShortestPathComputer<>(graph);

        assertThrows(IllegalStateException.class, () -> calc.getShortestPathTo(nodeA));
        calc.computeShortestPathsFrom(nodeA);
        assertThrows(NullPointerException.class, () -> calc.getShortestPathTo(null));
        assertThrows(IllegalArgumentException.class, () -> calc.getShortestPathTo(new GraphNode<>("d")));

        List<GraphEdge<String>> shortestPathToB = calc.getShortestPathTo(nodeB);
        List<GraphEdge<String>> edgeList = new ArrayList<>();
        edgeList.add(edge1);
        assertEquals(edgeList, shortestPathToB);
    }

}
