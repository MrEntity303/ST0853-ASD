package it.unicam.cs.asdl2122.pt1;



import java.util.ArrayList;
import java.util.List;



/**
 * Un AVLTree è un albero binario di ricerca che si mantiene sempre bilanciato.
 * In questa particolare classe si possono inserire elementi ripetuti di tipo
 * {@code E} e non si possono inserire elementi {@code null}.
 * 
 * @author Luca Tesei (Template)
 * 
 * @param <E>
 *              Il tipo degli elementi che possono essere inseriti in questo
 *              AVLTree. La classe {@code E} deve avere un ordinamento naturale
 *              definito tra gli elementi.
 *
 */
public class AVLTree<E extends Comparable<E>> {

    // puntatore al nodo radice, se questo puntatore è null allora questo
    // AVLTree è vuoto
    private AVLTreeNode root;

    // Numero di elementi inseriti in questo AVLTree, comprese le ripetizioni
    private int size;

    // Numero di nodi in questo AVLTree
    private int numberOfNodes;

    /**
     * Costruisce un AVLTree vuoto.
     */
    public AVLTree() {
        root = null;
    }

    /**
     * Costruisce un AVLTree che consiste solo di un nodo radice.
     * 
     * @param rootElement
     *                        l'informazione associata al nodo radice
     * @throws NullPointerException
     *                                  se l'elemento passato è null
     */
    public AVLTree(E rootElement) {
        if(rootElement == null)
            throw new NullPointerException();
        this.setRoot(new AVLTreeNode(rootElement));
        this.size = size + 1;
        this.numberOfNodes++;
    }

    /**
     * Determina se questo AVLTree è vuoto.
     * 
     * @return true, se questo AVLTree è vuoto.
     */
    public boolean isEmpty() {
        return (this.root == null);
    }

    /**
     * Restituisce il numero di elementi contenuti in questo AVLTree. In caso di
     * elementi ripetuti essi vengono contati più volte.
     * 
     * @return il numero di elementi di tipo {@code E} presenti in questo
     *         AVLTree.
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Restituisce il numero di nodi in questo AVLTree.
     * 
     * @return il numero di nodi in questo AVLTree.
     */
    public int getNumberOfNodes() {
        return this.numberOfNodes;
    }

    /**
     * Restituisce l'altezza di questo AVLTree. Se questo AVLTree è vuoto viene
     * restituito il valore -1.
     * 
     * @return l'altezza di questo AVLTree, -1 se questo AVLTree è vuoto.
     */
    public int getHeight() {
        return this.getRoot() != null ? this.getRoot().getHeight() : -1;
    }

    /**
     * @return the root
     */
    public AVLTreeNode getRoot() {
        return root;
    }

    /**
     * @param root
     *                 the root to set
     */
    public void setRoot(AVLTreeNode root) {
        this.root = root;
    }

    /**
     * Determina se questo AVLTree è bilanciato. L'albero è bilanciato se tutti
     * i nodi hanno un fattore di bilanciamento compreso tra -1 e +1. Il fattore
     * di bilanciamento di un nodo si definisce come l'altezza del sott'albero
     * sinistro meno l'altezza del sott'albero destro.
     * 
     * @return true, se il fattore di bilanciamento di tutti i nodi dell'albero
     *         è compreso tra -1 e +1.
     */
    public boolean isBalanced() {return root.isBalanced();}

    /**
     * Inserisce un nuovo elemento in questo AVLTree. Se l'elemento è già
     * presente viene incrementato il suo numero di occorrenze.
     * 
     * @param el
     *               l'elemento da inserire.
     * @return il numero di confronti tra elementi della classe {@code E}
     *         effettuati durante l'inserimento
     * @throws NullPointerException
     *                                  se l'elemento {@code el} è null
     */
    public int insert(E el) {
        int tmp;
        if(el == null)
            throw new NullPointerException();
        if((tmp = this.getRoot().insert(el)) == -1) {
            this.size = this.getSize() + 1;
        }
        else {
            this.size = this.getSize() + 1;
            this.numberOfNodes = this.getNumberOfNodes() + 1;
        }
        return tmp;
    }

    /**
     * Determina se questo AVLTree contiene un certo elemento.
     * 
     * @param el
     *               l'elemento da cercare
     * @return true se l'elemento è presente in questo AVLTree, false
     *         altrimenti.
     * @throws NullPointerException
     *                                  se l'elemento {@code el} è null
     */
    public boolean contains(E el) {
        // AVLTreeNode
        if(el == null)
            throw new NullPointerException();

        return this.getRoot().search(el) != null;
    }

    /**
     * Determina se un elemento è presente in questo AVLTree e ne restituisce il
     * relativo nodo.
     * 
     * @param el
     *               l'elemento da cercare
     * @return il nodo di questo AVLTree che contiene l'elemento {@code el} e la
     *         sua molteplicità, oppure {@code null} se l'elemento {@code el}
     *         non è presente in questo AVLTree.
     * @throws NullPointerException
     *                                  se l'elemento {@code el} è null
     * 
     */
    public AVLTreeNode getNodeOf(E el) {
        AVLTreeNode tmp;
        if(el == null)
            throw new NullPointerException();
        return (tmp = this.getRoot().search(el)) != null ? tmp : null;
    }

    /**
     * Determina il numero di occorrenze di un certo elemento in questo AVLTree.
     * 
     * @param el
     *               l'elemento di cui determinare il numero di occorrenze
     * @return il numero di occorrenze dell'elemento {@code el} in questo
     *         AVLTree, zero se non è presente.
     * @throws NullPointerException
     *                                  se l'elemento {@code el} è null
     */
    public int getCount(E el) {
        if(el == null)
            throw new NullPointerException();
        return this.getRoot().search(el) == null ? 0 : this.getRoot().search(el).getCount();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        String descrizione = "AVLTree [root=" + root.el.toString() + ", size=" + size
                + ", numberOfNodes=" + numberOfNodes + "]\n";
        return descrizione + this.root.toString();
    }

    /**
     * Restituisce la lista degli elementi contenuti in questo AVLTree secondo
     * l'ordine determinato dalla visita in-order. Per le proprietà dell'albero
     * AVL la lista ottenuta conterrà gli elementi in ordine crescente rispetto
     * all'ordinamento naturale della classe {@code E}. Nel caso di elementi
     * ripetuti, essi appaiono più volte nella lista consecutivamente.
     * 
     * @return la lista ordinata degli elementi contenuti in questo AVLTree,
     *         tenendo conto della loro molteplicità.
     */
    public List<E> inOrderVisit() {
        List<E> array = new ArrayList<>();
        inOrderTraversal(this.getRoot(), array);
        return array;
    }

    /**
     * Restituisce l'elemento minimo presente in questo AVLTree.
     * 
     * @return l'elemento minimo in questo AVLTree, {@code null} se questo
     *         AVLTree è vuoto.
     */
    public E getMinimum() {
        return this.getRoot() != null ? this.getRoot().getMinimum().getEl() : null;
    }

    /**
     * Restituisce l'elemento massimo presente in questo AVLTree.
     * 
     * @return l'elemento massimo in questo AVLTree, {@code null} se questo
     *         AVLTree è vuoto.
     */
    public E getMaximum() {
        return this.getRoot() != null ? this.getRoot().getMaximum().getEl() : null;
    }

    /**
     * Restituisce l'elemento <b>strettamente</b> successore, in questo AVLTree,
     * di un dato elemento. Si richiede che l'elemento passato sia presente
     * nell'albero.
     * 
     * @param el
     *               l'elemento di cui si chiede il successore
     * @return l'elemento <b>strettamente</b> successore, rispetto
     *         all'ordinamento naturale della classe {@code E}, di {@code el} in
     *         questo AVLTree, oppure {@code null} se {@code el} è l'elemento
     *         massimo.
     * @throws NullPointerException
     *                                      se l'elemento {@code el} è null
     * @throws IllegalArgumentException
     *                                      se l'elemento {@code el} non è
     *                                      presente in questo AVLTree.
     */
    public E getSuccessor(E el) {
        if(el == null)
            throw new NullPointerException();
        AVLTreeNode tmp = this.getRoot().search(el);
        if(tmp == null)
            throw new IllegalArgumentException();
        return (tmp = tmp.getSuccessor()) == null ? null : tmp.getEl();

    }

    /**
     * Restituisce l'elemento <b>strettamente</b> predecessore, in questo
     * AVLTree, di un dato elemento. Si richiede che l'elemento passato sia
     * presente nell'albero.
     * 
     * @param el
     *               l'elemento di cui si chiede il predecessore
     * @return l'elemento <b>strettamente</b> predecessore rispetto
     *         all'ordinamento naturale della classe {@code E}, di {@code el} in
     *         questo AVLTree, oppure {@code null} se {@code el} è l'elemento
     *         minimo.
     * @throws NullPointerException
     *                                      se l'elemento {@code el} è null
     * @throws IllegalArgumentException
     *                                      se l'elemento {@code el} non è
     *                                      presente in questo AVLTree.
     */
    public E getPredecessor(E el) {
        if(el == null)
            throw new NullPointerException();
        AVLTreeNode tmp = this.getRoot().search(el);
        if(tmp == null)
            throw new IllegalArgumentException();

        return (tmp = tmp.getPredecessor()) == null? null : tmp.getEl() ;

    }

    /*
    * metodo inOrderTraversal per implementazione
    * */
    private void inOrderTraversal(AVLTreeNode x, List<E> array)
    {
        if(x != null)
        {
            inOrderTraversal(x.getLeft(), array);
            for (int i = 0; i < x.getCount(); i++)
                array.add(x.getEl());
            inOrderTraversal(x.getRight(), array);
        }
    }

    /**
     * Gli elementi di questa classe sono i nodi di un AVLTree, che è la classe
     * "involucro" della struttura dati.
     * 
     * @author Luca Tesei (Template)
     *
     */
    public class AVLTreeNode {
        // etichetta del nodo
        private E el;

        // molteplicità dell'elemento el
        private int count;

        // sott'albero sinistro
        private AVLTreeNode left;

        // sott'albero destro
        private AVLTreeNode right;

        // genitore del nodo, null se questo nodo è la radice dell' AVLTree
        private AVLTreeNode parent;

        // altezza del sott'albero avente questo nodo come radice
        private int height;

        /**
         * Create an AVLTreeNode as a root leaf
         * 
         * @param el
         *               the element
         */
        public AVLTreeNode(E el) {
            this.el = el;
            this.count = 1;
            this.left = null;
            this.right = null;
            this.parent = null;
            this.height = 0;
        }

        /**
         * Create an AVLTreeNode node containing one element to be considered
         * child of the given parent.
         * 
         * @param el
         *                   the element
         * @param parent
         *                   the parent of the node
         */
        public AVLTreeNode(E el, AVLTreeNode parent) {
            this.el = el;
            this.count = 1;
            this.left = null;
            this.right = null;
            this.parent = parent;
            this.height = 0;
        }

        /**
         * Restituisce il nodo predecessore di questo nodo. Si suppone che
         * esista un nodo predecessore, cioè che questo nodo non contenga
         * l'elemento minimo del sott'albero di cui è radice.
         * 
         * @return il nodo predecessore
         */
        public AVLTreeNode getPredecessor(){
            //Se esiste un figlio sinistro allora prendo il massimo di quel figlio
            if (this.getLeft() != null) {
                return this.getLeft().getMaximum();
            }
            /*
             * Prendo il parent di un nodo e il nodo stesso e li metto in una variabile. Tramite una ricerca ricorsiva salgo l'albero
             * finché il parent non è diverso da null OPPURE il nodo non è più figlio sinistro
             */

            AVLTreeNode parent = this.getParent();
            AVLTreeNode node = this;
            while (parent != null && node == parent.getLeft()) {
                node = parent;
                parent = node.getParent();
            }

            //Se il parent è diverso da null allora lo ritorno altrimenti non esiste un predecessore

            return parent;
        }

        /**
         * Restituisce il nodo successore di questo nodo. Si suppone che esista
         * un nodo successore, cioè che questo nodo non contenga l'elemento
         * massimo del sott'albero di cui è radice.
         * 
         * @return il nodo successore
         */
        public AVLTreeNode getSuccessor() {
            //Se esiste un figlio destro allora prendo il minimo di quel figlio
            if (this.getRight() != null) {
                return this.getRight().getMinimum();
            }
            /*
             * Prendo il parent di un nodo e il nodo stesso e li metto in una variabile. Tramite una ricerca ricorsiva salgo l'albero
             * finché il parent non è diverso da null OPPURE il nodo non è più figlio destro
             */
            AVLTreeNode parent = this.getParent();
            AVLTreeNode node = this;

            while (parent != null && node == parent.getRight()) {
                node = parent;
                parent = node.getParent();
            }
            //Se il parent è diverso da null allora lo ritorno altrimenti non esiste un predecessore
            if (parent != null) {
                return parent;
            }

            return null;
        }

        /**
         * Restituisce il nodo contenente l'elemento massimo del sott'albero di
         * cui questo nodo è radice.
         * 
         * @return il nodo contenente l'elemento massimo del sott'albero di cui
         *         questo nodo è radice.
         */
        public AVLTreeNode getMaximum() {
            AVLTreeNode tmp = this;
            while(tmp.getRight() != null)
                tmp = tmp.getRight();
            return tmp;
        }

        /**
         * Restituisce il nodo contenente l'elemento minimo del sott'albero di
         * cui questo nodo è radice.
         * 
         * @return il nodo contenente l'elemento minimo del sott'albero di cui
         *         questo nodo è radice.
         */
        public AVLTreeNode getMinimum() {
            AVLTreeNode tmp = this;
            while(tmp.getLeft() != null)
               tmp = tmp.getLeft();
            return tmp;

        }

        /**
         * Determina se questo è un nodo foglia.
         * 
         * @return true se questo nodo non ha figli, false altrimenti.
         */
        public boolean isLeaf() {
            return this.getRight() == null && this.getLeft() == null;
        }

        /**
         * Restituisce l'altezza del sott'albero la cui radice è questo nodo.
         * 
         * @return l'altezza del sotto albero la cui radice è questo nodo.
         */
        public int getHeight() {
            return this.height;
        }

        /**
         * Aggiorna l'altezza del sott'albero la cui radice è questo nodo
         * supponendo che l'altezza dei nodi figli sia già stata aggiornata.
         */
        public void updateHeight() {
            //Se esiste il figlio sinistro ma non il destro allora l'altezza è filgio sinistro + 1
            if (this.getLeft() != null && this.getRight() == null) {
                this.setHeight(this.getLeft().getHeight() + 1);
                //Se esiste figlio destro ma non il sinistro allora l'altezza è figlio destro + 1
            } else if (this.getLeft() == null && this.getRight() != null) {
                this.setHeight(this.getRight().getHeight() + 1);
                //Se esistono entrambi i figli allora l'altezza è il massimo delle due + 1. Utilizzo la funzione Math.max per prendere il massimo
            } else if (this.getLeft() != null && this.getRight() != null) {
                this.setHeight(Math.max(this.getLeft().getHeight(), this.getRight().getHeight()) + 1);
            } else {
                //Se è una foglia allora altezza 0. Questo else è utile quando si ricalcolano le altezze dopo le rotazioni
                this.setHeight(0);
            }
        }

        /**
         * Determina il fattore di bilanciamento di questo nodo. Se il nodo è
         * una foglia il fattore di bilanciamento è 0. Se il nodo ha solo il
         * figlio sinistro allora il fattore di bilanciamento è l'altezza del
         * figlio sinistro + 1. Se il nodo ha solo il figlio destro allora il
         * fattore di bilanciamento è l'altezza del figlio destro + 1,
         * moltiplicata per -1. Se il nodo ha entrambi i figli il fattore di
         * bilanciamento è l'altezza del figlio sinistro meno l'altezza del
         * figlio destro.
         * 
         * @return il fattore di bilanciamento di questo nodo.
         */
        public int getBalanceFactor() {
            //Se il nodo è foglia fattore il fattore di bilanciamento è 0
            if (this.isLeaf()) {
                return 0;
            }
            //Se esiste figlio sinistro ma non destro allora il fattore è altezza figlio sinistro +1
            if (this.getLeft() != null && this.getRight() == null) {
                return this.getLeft().getHeight() + 1;
            }
            //Se esiste figlio destro ma non il sinistro allora il fattore di
            // bilanciamento è l'altezza del figlio destro + 1, moltiplicato per -1
            if (this.getRight() != null && this.getLeft() == null) {
                return (this.getRight().getHeight() + 1) * -1;
            }
            //Se esistono entrambi i figli allora è l'altezza figlio sinistro - altezza figlio destro
            return this.getLeft().getHeight() - this.getRight().getHeight();
        }

        /**
         * Determina se questo nodo e tutti i suoi discendenti hanno un fattore
         * di bilanciamento compreso tra -1 e 1.
         * 
         * @return true se questo nodo e tutti i suoi discendenti sono
         *         bilanciati, false altrimenti.
         */
        public boolean isBalanced() {
            int fattoreBilanciamento = this.getBalanceFactor();
            //Se il fattore di bilanciamento è compreso tra -1 e 1 allora facciamo i vari controlli,
            //altrimenti è inutile andare a vedere i figli se questo nodo è già sbilanciato
            //quindi in caso negativo ritorniamo direttamente false
            //si effettuano chiamate ricorsive al metodo isBalanced nei figli
            if (fattoreBilanciamento >= -1 && fattoreBilanciamento <= 1) {
                AVLTreeNode left = this.getLeft();
                AVLTreeNode right = this.getRight();
                //Se esiste il figlio sinistro ma non il destro allora controlliamo se il figlio sinistro è bilanciato
                //Se esiste il figlio sinistro ma anc
                if (left != null) {
                    if (right == null) {
                        return left.isBalanced();
                    } else {
                        return left.isBalanced() && right.isBalanced();
                    }
                }
                //Se esiste il figlio destro ma non il sinistro allora controlliamo se il figlio destro è bilanciato
                if (right != null) {
                    return right.isBalanced();
                }

                //Se il nodo non ha figli allora ritorniamo che il nodo è bilanciato;
                return true;
            }
            return false;
    }

        /**
         * @return the el
         */
        public E getEl() {
            return el;
        }

        /**
         * @param el
         *               the el to set
         */
        public void setEl(E el) {
            this.el = el;
        }

        /**
         * @return the count
         */
        public int getCount() {
            return count;
        }

        /**
         * @param count
         *                  the count to set
         */
        public void setCount(int count) {
            this.count = count;
        }

        /**
         * @return the left
         */
        public AVLTreeNode getLeft() {
            return left;
        }

        /**
         * @param left
         *                 the left to set
         */
        public void setLeft(AVLTreeNode left) {
            this.left = left;
        }

        /**
         * @return the right
         */
        public AVLTreeNode getRight() {
            return right;
        }

        /**
         * @param right
         *                  the right to set
         */
        public void setRight(AVLTreeNode right) {
            this.right = right;
        }

        /**
         * @return the parent
         */
        public AVLTreeNode getParent() {
            return parent;
        }

        /**
         * @param parent
         *                   the parent to set
         */
        public void setParent(AVLTreeNode parent) {
            this.parent = parent;
        }

        /**
         * @param height
         *                   the height to set
         */
        public void setHeight(int height) {
            this.height = height;
        }

        /*
         * (non-Javadoc)
         * 
         * @see java.lang.Object#toString()
         */
        @Override
        public String toString() {
            StringBuffer s = new StringBuffer();
            s.append("(");
            s.append(this.el);
            s.append(", ");
            if (this.left == null)
                s.append("()");
            else
                s.append(this.left.toString());
            s.append(", ");
            if (this.right == null)
                s.append("()");
            else
                s.append(this.right.toString());
            s.append(")");
            return s.toString();
        }

        /**
         * Ricerca un elemento a partire da questo nodo.
         * 
         * @param el
         *               the element to search for
         * 
         * @return the node containing the element or null if the element is not
         *         found
         */
        public AVLTreeNode search(E el) {


            AVLTreeNode tmp = this;
            while(tmp != null){
                if(tmp.getEl() == el)
                    return tmp;
                tmp = el.compareTo(tmp.getEl()) < 0 ? tmp.getLeft() : tmp.getRight();
            }
            return null;
        }

        /**
         * Inserisce un elemento nell'albero AVL a partire da questo nodo.
         * Se l'elemento è già presente ne aumenta semplicemente la molteplicità di
         * uno. Se l'elemento non è presente aggiunge un nodo nella opportuna
         * posizione e poi procede al ri-bilanciamento dell'albero se
         * l'inserimento del nuovo nodo provoca uno sbilanciamento in almeno un
         * nodo.
         * 
         * @param el
         *               l'elemento da inserire
         * 
         * @return il numero di confronti tra elementi della classe {@code E}
         *         effettuati durante l'inserimento.
         */
        public int insert(E el) {
            int count = 0;
            AVLTreeNode tmp = this;
            while(true)
            {
                if(el.compareTo(tmp.getEl()) == 0)
                {
                    tmp.setCount(tmp.getCount() + 1);
                    return -1;
                }
                else if (el.compareTo(tmp.getEl()) < 0)
                {
                    count++;
                    if(tmp.getLeft() == null)
                    {
                        tmp.setLeft(new AVLTreeNode(el, tmp));
                        tmp=tmp.getLeft();
                        //return count;
                        break;
                    }

                    else
                        tmp = tmp.getLeft();
                }
                else if(el.compareTo(tmp.getEl()) > 0)
                {
                    count++;
                    if(tmp.getRight() == null)
                    {
                        tmp.setRight(new AVLTreeNode(el, tmp));
                        //return count;
                        tmp = tmp.getRight();
                        break;
                    }
                    else
                        tmp = tmp.getRight();
                }
            }
            this.scanningTree(tmp);
            this.rebalance(tmp);
            return count;
        }

        // TODO inserire i metodi per i quattro tipi di rotazioni
        // sinistra-sinistra, sinistra-destra, destra-destra e destra-sinistra
        // come metodi private con gli opportuni parametri.
        private AVLTreeNode rightRotate(AVLTreeNode x) {
//            AVLTreeNode tmp =  x.getLeft();
//            x.setLeft(tmp.getRight());
//            if(x.getRight() != null)
//                x.setRight(x.getParent());
           /* AVLTreeNode leftChild = x.getLeft();

            x.setLeft(leftChild.getRight());
            leftChild.setRight(x);

            x.updateHeight();
            leftChild.updateHeight();

            return leftChild;*/
            return null;

        }
        private AVLTreeNode leftRotate(AVLTree<E> tree, AVLTreeNode node) {
//            AVLTreeNode tmp =  x.getLeft();
//            x.setLeft(tmp.getRight());
//            if(x.getRight() != null)
//                x.setRight(x.getParent());
            /*AVLTreeNode rightChild = x.getRight();

            x.setRight(rightChild.getLeft());
            rightChild.setLeft(x);

            x.updateHeight();
            rightChild.updateHeight();

            return rightChild;*/

            AVLTreeNode toReturn = node.getRight();
            node.setRight(toReturn.getLeft());
            if (toReturn.getLeft() != null)
                toReturn.getLeft().setParent(node.getParent());
            toReturn.setParent(node.getParent());

            if(node.getParent().equals(tree.getRoot()))
                tree.setRoot(toReturn);
            else if (node.equals(node.getParent().getLeft()))
                node.getParent().setLeft(toReturn);
            else
                node.getParent().setLeft(toReturn);

            toReturn.setLeft(node);
            node.setParent(toReturn);

            return toReturn;
        }

        private void scanningTree(AVLTreeNode node)
        {
            AVLTreeNode tmp = node;
            while(tmp.getParent() != null)
            {
                //tmp.updateHeight();
                tmp = tmp.getParent();
                tmp.updateHeight();
            }
            //tmp.updateHeight();

        }
        private AVLTreeNode rebalance(AVLTreeNode x) {
            int balanceFactor = x.getBalanceFactor();

            // Left-heavy?
            if (balanceFactor < -1) {
                if (x.getLeft().getBalanceFactor() <=0) {    // Case 1
                    // Rotate right
                    x.setLeft(rightRotate( x.getLeft()));
                } else {                                // Case 2
                    // Rotate left-right
                    x.setLeft(leftRotate(AVLTree.this, x.getLeft()));
                }
                x = rightRotate(x);
            }

            // Right-heavy?
            if (balanceFactor > 1) {
                if (x.getRight().getBalanceFactor() >= 0) {    // Case 3
                    // Rotate left
                    x.setLeft(leftRotate(AVLTree.this, x.getLeft()));
                } else {                                 // Case 4
                    // Rotate right-left
                    x.setRight(rightRotate(x.getRight()));
                }
                x = leftRotate(AVLTree.this, x);
            }

            return x;
        }
    }
}
