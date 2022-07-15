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
class FloydWarshallAllPairsShortestPathComputerTest {

    @Test
    final void testFloydWarshallAllPairsShortestPathComputer() {
        Graph<String> adj = new AdjacencyMatrixDirectedGraph<>();
        GraphNode<String> g = new GraphNode<>("a");
        adj.addNode(g);
        GraphNode<String> h = new GraphNode<>("b");
        adj.addNode(h);
        adj.addEdge(new GraphEdge<>(h, g, true));
        // null
        assertThrows(NullPointerException.class,
                () -> new FloydWarshallAllPairsShortestPathComputer<String>(
                        null));
        // peso
        assertThrows(IllegalArgumentException.class,
                () -> new FloydWarshallAllPairsShortestPathComputer<>(adj));
    }

    @Test
    final void testComputeShortestPaths() {
        Graph<String> adj = new AdjacencyMatrixDirectedGraph<>();
        GraphNode<String> g = new GraphNode<>("a");
        adj.addNode(g);
        GraphNode<String> h = new GraphNode<>("b");
        adj.addNode(h);
        adj.addEdge(new GraphEdge<>(h, g, true, -1));
        adj.addEdge(new GraphEdge<>(g, h, true, -1));
        FloydWarshallAllPairsShortestPathComputer<String> a = new FloydWarshallAllPairsShortestPathComputer<>(
                adj);

        assertThrows(IllegalStateException.class,
                () -> a.computeShortestPaths());

    }

    @Test
    final void testIsComputed() {
        Graph<String> adj = new AdjacencyMatrixDirectedGraph<>();
        GraphNode<String> g = new GraphNode<>("a");
        adj.addNode(g);
        GraphNode<String> h = new GraphNode<>("b");
        adj.addNode(h);
        adj.addEdge(new GraphEdge<>(h, g, true, 1));
        FloydWarshallAllPairsShortestPathComputer<String> a = new FloydWarshallAllPairsShortestPathComputer<>(
                adj);
        assertFalse(a.isComputed());
        a.computeShortestPaths();
        assertTrue(a.isComputed());
    }

    @Test
    final void testGetGraph() {
        Graph<String> adj = new AdjacencyMatrixDirectedGraph<>();
        GraphNode<String> g = new GraphNode<>("a");
        GraphNode<String> h = new GraphNode<>("b");
        adj.addNode(g);
        adj.addNode(h);
        adj.addEdge(new GraphEdge<>(h, g, true, 1));
        FloydWarshallAllPairsShortestPathComputer<String> a = new FloydWarshallAllPairsShortestPathComputer<>(
                adj);
        assertEquals(a.getGraph(), adj);
    }

    @Test
    final void testGetShortestPath() {
        Graph<String> adj = new AdjacencyMatrixDirectedGraph<>();
        GraphNode<String> g = new GraphNode<>("1");
        GraphNode<String> h = new GraphNode<>("2");
        GraphNode<String> d = new GraphNode<>("3");
        GraphNode<String> p = new GraphNode<>("4");
        adj.addNode(g);
        adj.addNode(h);
        adj.addNode(d);
        adj.addNode(p);
        GraphEdge<String> appEdge1 = new GraphEdge<>(p, d, true, 2);
        GraphEdge<String> appEdge2 = new GraphEdge<>(d, h, true, 1);
        adj.addEdge(new GraphEdge<>(h, g, true, 2));
        adj.addEdge(new GraphEdge<>(g, h, true, 3));
        adj.addEdge(new GraphEdge<>(g, p, true, 5));
        adj.addEdge(new GraphEdge<>(h, p, true, 4));
        adj.addEdge(appEdge1);
        adj.addEdge(appEdge2);
        FloydWarshallAllPairsShortestPathComputer<String> a = new FloydWarshallAllPairsShortestPathComputer<>(
                adj);
        a.computeShortestPaths();
        List<GraphEdge<String>> getShortestPath = new ArrayList<>();
        getShortestPath.add(appEdge1);
        getShortestPath.add(appEdge2);
        assertEquals(a.getShortestPath(p, h), getShortestPath);
        getShortestPath.add(new GraphEdge<>(d, h, true, 3));
        assertNotEquals(a.getShortestPath(p, h), getShortestPath);
    }

    @Test
    final void testGetShortestPathCost() {
        Graph<String> adj = new AdjacencyMatrixDirectedGraph<>();
        GraphNode<String> g = new GraphNode<>("1");
        adj.addNode(g);
        GraphNode<String> h = new GraphNode<>("2");
        adj.addNode(h);
        adj.addEdge(new GraphEdge<>(h, g, true, 2));
        adj.addEdge(new GraphEdge<>(g, h, true, 3));
        GraphNode<String> p = new GraphNode<>("4");
        adj.addNode(p);
        adj.addEdge(new GraphEdge<>(g, p, true, 5));
        adj.addEdge(new GraphEdge<>(h, p, true, 4));
        GraphNode<String> d = new GraphNode<>("3");
        adj.addNode(d);
        GraphEdge<String> appEdge1 = new GraphEdge<>(p, d, true, 2);
        adj.addEdge(appEdge1);
        GraphEdge<String> appEdge2 = new GraphEdge<>(d, h, true, 1);
        adj.addEdge(appEdge2);
        FloydWarshallAllPairsShortestPathComputer<String> a = new FloydWarshallAllPairsShortestPathComputer<>(
                adj);
        a.computeShortestPaths();
        assertEquals(a.getShortestPathCost(p, h), 3);
    }

    @Test
    final void testPrintPath() {
        Graph<String> adj = new AdjacencyMatrixDirectedGraph<>();
        GraphNode<String> g = new GraphNode<>("1");
        GraphNode<String> h = new GraphNode<>("2");
        GraphNode<String> d = new GraphNode<>("3");
        GraphNode<String> p = new GraphNode<>("4");
        adj.addNode(g);
        adj.addNode(h);
        adj.addNode(d);
        adj.addNode(p);
        GraphEdge<String> appEdge1 = new GraphEdge<>(p, d, true, 2);
        GraphEdge<String> appEdge2 = new GraphEdge<>(d, h, true, 1);
        adj.addEdge(new GraphEdge<>(h, g, true, 2));
        adj.addEdge(new GraphEdge<>(g, h, true, 3));
        adj.addEdge(new GraphEdge<>(g, p, true, 5));
        adj.addEdge(new GraphEdge<>(h, p, true, 4));
        adj.addEdge(appEdge1);
        adj.addEdge(appEdge2);
        FloydWarshallAllPairsShortestPathComputer<String> a = new FloydWarshallAllPairsShortestPathComputer<>(
                adj);
        a.computeShortestPaths();
        List<GraphEdge<String>> getShortestPath = new ArrayList<>();
        getShortestPath.add(appEdge1);
        getShortestPath.add(appEdge2);
        assertEquals(a.printPath(a.getShortestPath(p, h)),
                a.printPath(getShortestPath));
    }

    @Test
    final void testGetCostMatrix() {
        Graph<String> adj = new AdjacencyMatrixDirectedGraph<>();
        GraphNode<String> g = new GraphNode<>("1");
        adj.addNode(g);
        GraphNode<String> h = new GraphNode<>("2");
        adj.addNode(h);
        adj.addEdge(new GraphEdge<>(h, g, true, 2));
        adj.addEdge(new GraphEdge<>(g, h, true, 3));
        GraphNode<String> p = new GraphNode<>("4");
        adj.addNode(p);
        adj.addEdge(new GraphEdge<>(g, p, true, 5));
        adj.addEdge(new GraphEdge<>(h, p, true, 4));
        GraphNode<String> d = new GraphNode<>("3");
        adj.addNode(d);
        GraphEdge<String> appEdge1 = new GraphEdge<>(p, d, true, 2);
        adj.addEdge(appEdge1);
        GraphEdge<String> appEdge2 = new GraphEdge<>(d, h, true, 1);
        adj.addEdge(appEdge2);
        FloydWarshallAllPairsShortestPathComputer<String> a = new FloydWarshallAllPairsShortestPathComputer<>(
                adj);
        a.computeShortestPaths();
        assertEquals(a.getCostMatrix()[3][3], 0);
        assertEquals(a.getCostMatrix()[1][0], 2);
    }

    @Test
    final void testGetPredecessorMatrix() {
        Graph<String> adj = new AdjacencyMatrixDirectedGraph<>();
        GraphNode<String> g = new GraphNode<>("1");
        GraphNode<String> h = new GraphNode<>("2");
        GraphNode<String> d = new GraphNode<>("3");
        GraphNode<String> p = new GraphNode<>("4");
        adj.addNode(g);
        adj.addNode(h);
        adj.addNode(d);
        adj.addNode(p);
        GraphEdge<String> appEdge1 = new GraphEdge<>(p, d, true, 2);
        GraphEdge<String> appEdge2 = new GraphEdge<>(d, h, true, 1);
        adj.addEdge(new GraphEdge<>(h, g, true, 2));
        adj.addEdge(new GraphEdge<>(g, h, true, 3));
        adj.addEdge(new GraphEdge<>(g, p, true, 5));
        adj.addEdge(new GraphEdge<>(h, p, true, 4));
        adj.addEdge(appEdge1);
        adj.addEdge(appEdge2);
        FloydWarshallAllPairsShortestPathComputer<String> a = new FloydWarshallAllPairsShortestPathComputer<>(
                adj);
        a.computeShortestPaths();
        assertEquals(-1, a.getPredecessorMatrix()[0][2]);
    }




    @Test
    final void testFloydWarshallAllPairsShortestPathComputer1() {
        Graph<String> graph = new AdjacencyMatrixDirectedGraph<>();
        GraphNode<String> nodeA = new GraphNode<>("A");
        graph.addNode(nodeA);
        GraphNode<String> nodeB = new GraphNode<>("B");
        graph.addNode(nodeB);
        GraphNode<String> nodeC = new GraphNode<>("C");
        graph.addNode(nodeC);
        GraphNode<String> nodeD = new GraphNode<>("D");
        graph.addNode(nodeD);


        graph.addEdge(new GraphEdge<>(nodeA, nodeB, true, 6));
        graph.addEdge(new GraphEdge<>(nodeB, nodeC, true, 8));
        graph.addEdge(new GraphEdge<>(nodeC, nodeD, true, -3));
        graph.addEdge(new GraphEdge<>(nodeD, nodeA, true));
        // null
        assertThrows(NullPointerException.class,
                () -> new FloydWarshallAllPairsShortestPathComputer<String>(
                        null));
        // peso
        assertThrows(IllegalArgumentException.class,
                () -> new FloydWarshallAllPairsShortestPathComputer<>(graph));
    }

    @Test
    final void testComputeShortestPaths1() {
        Graph<String> graph = new AdjacencyMatrixDirectedGraph<>();
        GraphNode<String> nodeA = new GraphNode<>("A");
        graph.addNode(nodeA);
        GraphNode<String> nodeB = new GraphNode<>("B");
        graph.addNode(nodeB);
        GraphNode<String> nodeC = new GraphNode<>("C");
        graph.addNode(nodeC);
        GraphNode<String> nodeD = new GraphNode<>("D");
        graph.addNode(nodeD);


        graph.addEdge(new GraphEdge<>(nodeA, nodeB, true, 6));
        graph.addEdge(new GraphEdge<>(nodeB, nodeC, true, -8));
        graph.addEdge(new GraphEdge<>(nodeC, nodeD, true, -3));
        graph.addEdge(new GraphEdge<>(nodeD, nodeA, true, -2));
        FloydWarshallAllPairsShortestPathComputer<String> a = new FloydWarshallAllPairsShortestPathComputer<>(
                graph);

        assertThrows(IllegalStateException.class,
                () -> a.computeShortestPaths());

    }

    @Test
    final void testIsComputed1() {
        Graph<String> graph = new AdjacencyMatrixDirectedGraph<>();
        GraphNode<String> nodeA = new GraphNode<>("A");
        graph.addNode(nodeA);
        GraphNode<String> nodeB = new GraphNode<>("B");
        graph.addNode(nodeB);
        GraphNode<String> nodeC = new GraphNode<>("C");
        graph.addNode(nodeC);
        GraphNode<String> nodeD = new GraphNode<>("D");
        graph.addNode(nodeD);


        graph.addEdge(new GraphEdge<>(nodeA, nodeB, true, 6));
        graph.addEdge(new GraphEdge<>(nodeB, nodeC, true, 8));
        graph.addEdge(new GraphEdge<>(nodeC, nodeD, true, -3));
        graph.addEdge(new GraphEdge<>(nodeD, nodeA, true, -2));
        FloydWarshallAllPairsShortestPathComputer<String> a = new FloydWarshallAllPairsShortestPathComputer<>(
                graph);
        assertFalse(a.isComputed());
        a.computeShortestPaths();
        assertTrue(a.isComputed());
    }

    @Test
    final void testGetGraph1() {
        Graph<String> graph = new AdjacencyMatrixDirectedGraph<>();
        GraphNode<String> nodeA = new GraphNode<>("A");
        graph.addNode(nodeA);
        GraphNode<String> nodeB = new GraphNode<>("B");
        graph.addNode(nodeB);
        GraphNode<String> nodeC = new GraphNode<>("C");
        graph.addNode(nodeC);
        GraphNode<String> nodeD = new GraphNode<>("D");
        graph.addNode(nodeD);


        graph.addEdge(new GraphEdge<>(nodeA, nodeB, true, 6));
        graph.addEdge(new GraphEdge<>(nodeB, nodeC, true, 8));
        graph.addEdge(new GraphEdge<>(nodeC, nodeD, true, -3));
        graph.addEdge(new GraphEdge<>(nodeD, nodeA, true, -2));
        FloydWarshallAllPairsShortestPathComputer<String> a = new FloydWarshallAllPairsShortestPathComputer<>(
                graph);
        assertEquals(a.getGraph(), graph);
    }

    @Test
    final void testGetShortestPath1() {
        Graph<String> graph = new AdjacencyMatrixDirectedGraph<>();
        GraphNode<String> nodeA = new GraphNode<>("A");
        graph.addNode(nodeA);
        GraphNode<String> nodeB = new GraphNode<>("B");
        graph.addNode(nodeB);
        GraphNode<String> nodeC = new GraphNode<>("C");
        graph.addNode(nodeC);
        GraphNode<String> nodeD = new GraphNode<>("D");
        graph.addNode(nodeD);


        graph.addEdge(new GraphEdge<>(nodeA, nodeB, true, 6));
        graph.addEdge(new GraphEdge<>(nodeB, nodeC, true, 8));
        graph.addEdge(new GraphEdge<>(nodeC, nodeD, true, -3));
        graph.addEdge(new GraphEdge<>(nodeD, nodeA, true, -2));
        FloydWarshallAllPairsShortestPathComputer<String> a = new FloydWarshallAllPairsShortestPathComputer<>(
                graph);
        a.computeShortestPaths();
        List<GraphEdge<String>> getShortestPath = new ArrayList<>();
        getShortestPath.add(new GraphEdge<>(nodeB, nodeC, true, 8));
        getShortestPath.add(new GraphEdge<>(nodeC, nodeD, true, -3));
        assertEquals(a.getShortestPath(nodeB, nodeD), getShortestPath);
        getShortestPath.add(new GraphEdge<>(nodeA, nodeB, true, 3));
        assertNotEquals(a.getShortestPath(nodeB, nodeD), getShortestPath);
    }

    @Test
    final void testGetShortestPathCost1() {
        Graph<String> graph = new AdjacencyMatrixDirectedGraph<>();
        GraphNode<String> nodeA = new GraphNode<>("A");
        graph.addNode(nodeA);
        GraphNode<String> nodeB = new GraphNode<>("B");
        graph.addNode(nodeB);
        GraphNode<String> nodeC = new GraphNode<>("C");
        graph.addNode(nodeC);
        GraphNode<String> nodeD = new GraphNode<>("D");
        graph.addNode(nodeD);


        graph.addEdge(new GraphEdge<>(nodeA, nodeB, true, 6));
        graph.addEdge(new GraphEdge<>(nodeB, nodeC, true, 8));
        graph.addEdge(new GraphEdge<>(nodeC, nodeD, true, -3));
        graph.addEdge(new GraphEdge<>(nodeD, nodeA, true, -2));
        FloydWarshallAllPairsShortestPathComputer<String> a = new FloydWarshallAllPairsShortestPathComputer<>(
                graph);
        a.computeShortestPaths();
        assertEquals(a.getShortestPathCost(nodeB, nodeD), 5);
    }

    @Test
    final void testPrintPath1() {
        Graph<String> graph = new AdjacencyMatrixDirectedGraph<>();
        GraphNode<String> nodeA = new GraphNode<>("A");
        graph.addNode(nodeA);
        GraphNode<String> nodeB = new GraphNode<>("B");
        graph.addNode(nodeB);
        GraphNode<String> nodeC = new GraphNode<>("C");
        graph.addNode(nodeC);
        GraphNode<String> nodeD = new GraphNode<>("D");
        graph.addNode(nodeD);


        graph.addEdge(new GraphEdge<>(nodeA, nodeB, true, 6));
        graph.addEdge(new GraphEdge<>(nodeB, nodeC, true, 8));
        graph.addEdge(new GraphEdge<>(nodeC, nodeD, true, -3));
        graph.addEdge(new GraphEdge<>(nodeD, nodeA, true, -2));
        FloydWarshallAllPairsShortestPathComputer<String> a = new FloydWarshallAllPairsShortestPathComputer<>(
                graph);
        a.computeShortestPaths();
        List<GraphEdge<String>> getShortestPath = new ArrayList<>();
        getShortestPath.add(new GraphEdge<>(nodeB, nodeC, true, 8));
        getShortestPath.add(new GraphEdge<>(nodeC, nodeD, true, -3));
        assertEquals(a.printPath(a.getShortestPath(nodeB, nodeD)),
                a.printPath(getShortestPath));
    }

    @Test
    final void testGetCostMatrix1() {
        Graph<String> graph = new AdjacencyMatrixDirectedGraph<>();
        GraphNode<String> nodeA = new GraphNode<>("A");
        graph.addNode(nodeA);
        GraphNode<String> nodeB = new GraphNode<>("B");
        graph.addNode(nodeB);
        GraphNode<String> nodeC = new GraphNode<>("C");
        graph.addNode(nodeC);
        GraphNode<String> nodeD = new GraphNode<>("D");
        graph.addNode(nodeD);


        graph.addEdge(new GraphEdge<>(nodeA, nodeB, true, 6));
        graph.addEdge(new GraphEdge<>(nodeB, nodeC, true, 8));
        graph.addEdge(new GraphEdge<>(nodeC, nodeD, true, -3));
        graph.addEdge(new GraphEdge<>(nodeD, nodeA, true, -2));
        FloydWarshallAllPairsShortestPathComputer<String> a = new FloydWarshallAllPairsShortestPathComputer<>(
                graph);
        a.computeShortestPaths();
        assertEquals(a.getCostMatrix()[3][3], 0);
        assertEquals(a.getCostMatrix()[1][2], 8);
    }

    @Test
    final void testGetPredecessorMatrix1() {
        Graph<String> graph = new AdjacencyMatrixDirectedGraph<>();
        GraphNode<String> nodeA = new GraphNode<>("A");
        graph.addNode(nodeA);
        GraphNode<String> nodeB = new GraphNode<>("B");
        graph.addNode(nodeB);
        GraphNode<String> nodeC = new GraphNode<>("C");
        graph.addNode(nodeC);
        GraphNode<String> nodeD = new GraphNode<>("D");
        graph.addNode(nodeD);


        graph.addEdge(new GraphEdge<>(nodeA, nodeB, true, 6));
        graph.addEdge(new GraphEdge<>(nodeB, nodeC, true, 8));
        graph.addEdge(new GraphEdge<>(nodeC, nodeD, true, -3));
        graph.addEdge(new GraphEdge<>(nodeD, nodeA, true, -2));
        FloydWarshallAllPairsShortestPathComputer<String> a = new FloydWarshallAllPairsShortestPathComputer<>(
                graph);
        a.computeShortestPaths();
        assertEquals(-1, a.getPredecessorMatrix()[0][0]);
    }

}
