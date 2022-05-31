/**
 * 
 */
package it.unicam.cs.asdl2122.pt1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.ArrayList;

/**
 * Classe di test per AVLTree.
 * 
 * @author Luca Tesei
 *
 */
public class AVLTreeTest {

    @Test
    public final void testAVLTree() {
        AVLTree<Integer> t = new AVLTree<Integer>();
        assertEquals(true, t.isEmpty());
        assertEquals(0, t.getSize());
        assertEquals(0, t.getNumberOfNodes());
    }

    @Test
    public final void testAVLTreeENull() {
        assertThrows(NullPointerException.class,
                () -> new AVLTree<Integer>(null));
    }

    @Test
    public final void testAVLTreeE() {
        AVLTree<Integer> t = new AVLTree<Integer>(15);
        assertFalse(t.isEmpty());
        assertTrue(1 == t.getSize());
        assertTrue(1 == t.getNumberOfNodes());
        assertTrue(t.getRoot().getEl().equals(15));
        assertTrue(1 == t.getCount(15));
    }

    @Test
    public final void testIsEmpty() {
        AVLTree<Integer> t = new AVLTree<Integer>(2);
        assertFalse(t.isEmpty());
    }

    @Test
    public final void testGetSize() {
        AVLTree<Integer> t = new AVLTree<Integer>(15);
        assertEquals(1, t.getSize());
        t.insert(16);
        assertEquals(2, t.getSize());
        t.insert(15);
        assertEquals(3, t.getSize());
    }

    @Test
    public final void testGetNumberOfNodes() {
        AVLTree<Integer> t = new AVLTree<Integer>(15);
        assertEquals(1, t.getNumberOfNodes());
        t.insert(16);
        assertEquals(2, t.getNumberOfNodes());
        t.insert(15);
        assertEquals(2, t.getNumberOfNodes());
    }

    @Test
    public final void testGetHeight() {
        AVLTree<Integer> t = new AVLTree<Integer>(15);
        assertEquals(0, t.getHeight());
        t.insert(16);
        assertEquals(1, t.getHeight());
        t.insert(13);
        assertEquals(1, t.getHeight());
        t.insert(14);
        assertEquals(2, t.getHeight());
        t.insert(17);
        assertEquals(2, t.getHeight());
        t.insert(18);
        assertEquals(2, t.getHeight());
    }

    @Test
    public final void testGetRoot() {
        AVLTree<Integer> t = new AVLTree<Integer>(15);
        AVLTree<Integer>.AVLTreeNode n = t.getRoot();
        assertTrue(n.getEl() == 15);
        assert (n.getLeft() == null);
        assertTrue(n.getRight() == null);
    }

    @Test
    public final void testIsBalanced() {
        AVLTree<Integer> t = new AVLTree<Integer>(15);
        t.insert(16);
        t.insert(13);
        t.insert(14);
        t.insert(17);
        t.insert(18);
        assertEquals(true, t.isBalanced());
    }

    @Test
    public final void testInsertDDS() {
        // Rotazione DD Semplice
        AVLTree<Integer> t = new AVLTree<Integer>(15);
        t.insert(16);
        t.insert(13);
        t.insert(14);
        t.insert(17);
        t.insert(18);
        assertEquals(2, t.getHeight());
        List<Integer> l = t.inOrderVisit();
        List<Integer> ll = new ArrayList<Integer>();
        ll.add(13);
        ll.add(14);
        ll.add(15);
        ll.add(16);
        ll.add(17);
        ll.add(18);
        assertEquals(true, l.equals(ll));

    }

    @Test
    public final void testInsertDDNS() {
        // Rotazione DD non Semplice
        AVLTree<Integer> t = new AVLTree<Integer>(20);
        t.insert(16);
        t.insert(30);
        t.insert(25);
        t.insert(40);
        t.insert(15);
        t.insert(35);
        t.insert(45);
        t.insert(17);
        t.insert(24);
        t.insert(26);
        t.insert(50);
        assertEquals(3, t.getHeight());
        assertEquals(true, t.getRoot().getEl().equals(30));
        List<Integer> l = t.inOrderVisit();
        List<Integer> ll = new ArrayList<Integer>();
        ll.add(15);
        ll.add(16);
        ll.add(17);
        ll.add(20);
        ll.add(24);
        ll.add(25);
        ll.add(26);
        ll.add(30);
        ll.add(35);
        ll.add(40);
        ll.add(45);
        ll.add(50);
        assertEquals(true, l.equals(ll));
    }

    @Test
    public final void testInsertSSS() {
        // Rotazione SS Semplice
        AVLTree<Integer> t = new AVLTree<Integer>(40);
        t.insert(30);
        t.insert(50);
        t.insert(20);
        t.insert(15);
        assertEquals(2, t.getHeight());
        assertEquals(true, t.getRoot().getEl().equals(40));
        List<Integer> l = t.inOrderVisit();
        List<Integer> ll = new ArrayList<Integer>();
        ll.add(15);
        ll.add(20);
        ll.add(30);
        ll.add(40);
        ll.add(50);
        assertEquals(true, l.equals(ll));
    }

    @Test
    public final void testInsertSSNS() {
        // Rotazione SS Non Semplice
        AVLTree<Integer> t = new AVLTree<Integer>(40);
        t.insert(30);
        t.insert(50);
        t.insert(20);
        t.insert(35);
        t.insert(55);
        t.insert(34);
        t.insert(36);
        t.insert(15);
        t.insert(25);
        t.insert(10);
        assertEquals(3, t.getHeight());
        assertEquals(true, t.getRoot().getEl().equals(30));
        List<Integer> l = t.inOrderVisit();
        List<Integer> ll = new ArrayList<Integer>();
        ll.add(10);
        ll.add(15);
        ll.add(20);
        ll.add(25);
        ll.add(30);
        ll.add(34);
        ll.add(35);
        ll.add(36);
        ll.add(40);
        ll.add(50);
        ll.add(55);
        assertEquals(true, l.equals(ll));
    }

    @Test
    public final void testInsertSD() {
        // Rotazione SD
        AVLTree<Integer> t = new AVLTree<Integer>(40);
        t.insert(20);
        t.insert(50);
        t.insert(30);
        t.insert(10);
        t.insert(55);
        t.insert(35);
        t.insert(25);
        t.insert(15);
        t.insert(5);
        t.insert(36);
        assertEquals(3, t.getHeight());
        assertEquals(true, t.getRoot().getEl().equals(30));
        List<Integer> l = t.inOrderVisit();
        List<Integer> ll = new ArrayList<Integer>();
        ll.add(5);
        ll.add(10);
        ll.add(15);
        ll.add(20);
        ll.add(25);
        ll.add(30);
        ll.add(35);
        ll.add(36);
        ll.add(40);
        ll.add(50);
        ll.add(55);
        assertEquals(true, l.equals(ll));
    }

    @Test
    public final void testInsertDS() {
        // Rotazione DS
        AVLTree<Integer> t = new AVLTree<Integer>(40);
        t.insert(20);
        t.insert(60);
        t.insert(30);
        t.insert(10);
        t.insert(30);
        t.insert(50);
        t.insert(70);
        t.insert(65);
        t.insert(75);
        t.insert(64);
        assertEquals(3, t.getHeight());
        assertEquals(true, t.getRoot().getRight().getEl().equals(65));
        List<Integer> l = t.inOrderVisit();
        List<Integer> ll = new ArrayList<Integer>();
        ll.add(10);
        ll.add(20);
        ll.add(30);
        ll.add(30);
        ll.add(40);
        ll.add(50);
        ll.add(60);
        ll.add(64);
        ll.add(65);
        ll.add(70);
        ll.add(75);
        assertEquals(true, l.equals(ll));
    }

    @Test
    public final void testContains() {
        AVLTree<Integer> t = new AVLTree<Integer>(40);
        t.insert(20);
        t.insert(60);
        t.insert(10);
        t.insert(15);
        t.insert(30);
        t.insert(50);
        t.insert(60);
        t.insert(65);
        t.insert(75);
        t.insert(64);
        assertEquals(true, t.contains(50));
        assertEquals(true, t.contains(60));
        assertEquals(false, t.contains(5));
    }

    @Test
    public final void testContainsNull() {
        AVLTree<Integer> t = new AVLTree<Integer>(40);
        t.insert(20);
        t.insert(60);
        t.insert(10);
        t.insert(15);
        t.insert(30);
        t.insert(50);
        t.insert(60);
        t.insert(65);
        t.insert(75);
        assertThrows(NullPointerException.class, () -> t.insert(null));
    }

    @Test
    public final void testGetNodeOf() {
        AVLTree<Integer> t = new AVLTree<Integer>(40);
        t.insert(20);
        t.insert(60);
        t.insert(25);
        t.insert(65);
        t.insert(60);
        AVLTree<Integer>.AVLTreeNode n = t.getNodeOf(60);
        assertNotEquals(null, n);
        assertEquals(true, n.getEl().equals(60));
        assertEquals(2, n.getCount());
        assertEquals(true, n.getParent().getEl().equals(40));
        assertEquals(null, n.getLeft());
        assertEquals(true, n.getRight().getEl().equals(65));
        n = t.getNodeOf(10);
        assertEquals(null, n);
    }

    @Test
    public final void testGetNodeOfNull() {
        AVLTree<Integer> t = new AVLTree<Integer>(40);
        t.insert(20);
        t.insert(60);
        t.insert(25);
        t.insert(65);
        t.insert(60);
        assertThrows(NullPointerException.class, () -> t.getNodeOf(null));

    }

    @Test
    public final void testGetCount() {
        AVLTree<Integer> t = new AVLTree<Integer>(40);
        t.insert(20);
        t.insert(60);
        t.insert(25);
        t.insert(65);
        assertEquals(0, t.getCount(0));
        assertEquals(1, t.getCount(60));
        t.insert(60);
        assertEquals(2, t.getCount(60));
    }

    @Test
    public final void testInOrderVisit() {
        AVLTree<Integer> t = new AVLTree<Integer>(40);
        t.insert(20);
        t.insert(60);
        t.insert(10);
        t.insert(15);
        t.insert(30);
        t.insert(50);
        t.insert(60);
        t.insert(65);
        t.insert(75);
        t.insert(64);
        List<Integer> l = t.inOrderVisit();
        List<Integer> ll = new ArrayList<Integer>();
        ll.add(10);
        ll.add(15);
        ll.add(20);
        ll.add(30);
        ll.add(40);
        ll.add(50);
        ll.add(60);
        ll.add(60);
        ll.add(64);
        ll.add(65);
        ll.add(75);
        assertEquals(true, l.equals(ll));
    }

    @Test
    public final void testGetMinimum() {
        AVLTree<Integer> t = new AVLTree<Integer>(40);
        t.insert(20);
        t.insert(60);
        t.insert(10);
        t.insert(15);
        t.insert(30);
        t.insert(50);
        t.insert(60);
        t.insert(65);
        t.insert(75);
        t.insert(64);
        assertEquals(true, t.getMinimum().equals(10));
    }

    @Test
    public final void testGetMinimumMaximumNull() {
        AVLTree<Integer> t = new AVLTree<Integer>();
        assertEquals(null, t.getMinimum());
        assertEquals(null, t.getMaximum());
    }

    @Test
    public final void testGetMaximum() {
        AVLTree<Integer> t = new AVLTree<Integer>(40);
        t.insert(20);
        t.insert(60);
        t.insert(10);
        t.insert(15);
        t.insert(30);
        t.insert(50);
        t.insert(60);
        t.insert(65);
        t.insert(75);
        t.insert(64);
        assertEquals(true, t.getMaximum().equals(75));
    }

    @Test
    public final void testGetSuccessorNull() {
        AVLTree<Integer> t = new AVLTree<Integer>(40);
        t.insert(20);
        assertThrows(NullPointerException.class, () -> t.getSuccessor(null));
    }

    @Test
    public final void testGetSuccessorIllegal() {
        AVLTree<Integer> t = new AVLTree<Integer>(40);
        t.insert(20);
        assertThrows(IllegalArgumentException.class, () -> t.getSuccessor(25));
    }

    @Test
    public final void testGetSuccessor() {
        AVLTree<Integer> t = new AVLTree<Integer>(40);
        t.insert(20);
        t.insert(60);
        t.insert(10);
        t.insert(25);
        t.insert(50);
        t.insert(65);
        t.insert(60);
        t.insert(65);
        t.insert(75);
        t.insert(64);
        assertEquals(true, t.getSuccessor(60).equals(64));
        assertEquals(true, t.getSuccessor(25).equals(40));
        assertEquals(true, t.getSuccessor(40).equals(50));
    }

    @Test
    public final void testGetSuccessorMaximum() {
        AVLTree<Integer> t = new AVLTree<Integer>(40);
        t.insert(20);
        t.insert(60);
        t.insert(10);
        t.insert(25);
        t.insert(50);
        t.insert(65);
        t.insert(60);
        t.insert(65);
        t.insert(75);
        t.insert(64);
        assertEquals(null, t.getSuccessor(75));
    }

    @Test
    public final void testGetPredecessorNull() {
        AVLTree<Integer> t = new AVLTree<Integer>(40);
        t.insert(20);
        assertThrows(NullPointerException.class, () -> t.getPredecessor(null));
    }

    @Test
    public final void testGetPredecessorIllegal() {
        AVLTree<Integer> t = new AVLTree<Integer>(40);
        t.insert(20);
        assertThrows(IllegalArgumentException.class,
                () -> t.getPredecessor(25));
    }

    @Test
    public final void testGetPredecessor() {
        AVLTree<Integer> t = new AVLTree<Integer>(40);
        t.insert(20);
        t.insert(60);
        t.insert(10);
        t.insert(25);
        t.insert(50);
        t.insert(65);
        t.insert(60);
        t.insert(65);
        t.insert(75);
        t.insert(64);
        assertEquals(true, t.getPredecessor(25).equals(20));
        assertEquals(true, t.getPredecessor(50).equals(40));
        assertEquals(true, t.getPredecessor(40).equals(25));
    }

    @Test
    public final void testGetPredecessorMinimum() {
        AVLTree<Integer> t = new AVLTree<Integer>(40);
        t.insert(20);
        t.insert(60);
        t.insert(10);
        t.insert(25);
        t.insert(50);
        t.insert(65);
        t.insert(60);
        t.insert(65);
        t.insert(75);
        t.insert(64);
        assertEquals(null, t.getPredecessor(10));
    }

}
