package lapr.project.tree;

import lapr.project.model.Calculator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author Luís Araújo && Renan Oliveira
 */
public class KDTree<T extends Comparable<T>> {

    /**
     * Method that compares two nodes in the x level
     */
    private final Comparator<Node<T>> cmpX = new Comparator<Node<T>>() {
        @Override
        public int compare(Node<T> p1, Node<T> p2) {
            return Double.compare(p1.getCoords().getX(), p2.getCoords().getX());
        }
    };

    /**
     * Method that compares two nodes in the y level
     */
    //----------- end of nested Node class -----------
    private final Comparator<Node<T>> cmpY = new Comparator<Node<T>>() {
        @Override
        public int compare(Node<T> p1, Node<T> p2) {
            return Double.compare(p1.getCoords().getY(), p2.getCoords().getY());
        }
    };
    private Node<T> root = null;     // root of the tree
    private int size;
    private final List<Node<T>> nodes = new ArrayList<>();

    /* Constructs an empty KD tree. */
    public KDTree() {
        root = null;
        size = 0;
    }

    /**
     * Constructor that calls the method that builds a 2D-tree
     *
     * @param nodes
     */
    public KDTree(List<Node<T>> nodes) {
        buildTree(nodes);
    }

    /**
     * Method that return the list of nodes
     *
     * @return list of nodes
     */
    public List<Node<T>> getNodes() {
        return new ArrayList<>(nodes);
    }

    /**
     * Method that builds a 2D-tree
     *
     * @param nodes
     */
    public void buildTree(List<Node<T>> nodes) {
        root = new Object() {
            Node<T> buildTree(boolean divX, List<Node<T>> nodes) {
                if (nodes == null || nodes.isEmpty())
                    return null;
                //sort all the ports in ascending order comparing the coordinates of the elements by the x
                Collections.sort(nodes, divX ? cmpX : cmpY);
                //shifts all the bits to the right one position -> divides all the nodes by 2 getting the median
                int mid = nodes.size() >> 1;
                //new node is the median node
                Node<T> node = new Node<>(nodes.get(mid).getInfo(), null, null, nodes.get(mid).coords.x, nodes.get(mid).coords.y);
                //gets the coords of the median
                node.coords = nodes.get(mid).coords;
                //gets the info of the median
                node.info = nodes.get(mid).info;
                //builds the left subtree by comparing the y and going from the start of the list to the mid of it
                node.left = buildTree(!divX, nodes.subList(0, mid));
                //if the list is not over builds the right subtree
                if (mid + 1 < nodes.size() - 1)
                    //builds the right subtree by comparing the y and going from the mid of the list to the end of it
                    node.right = buildTree(!divX, nodes.subList(mid + 1, nodes.size()));
                return node;
            }
        }.buildTree(true, nodes);
    }

    /**
     * Method that calls the method that finds the nearest neighbour
     *
     * @param x
     * @param y
     * @return the type of node
     */
    public T findNearestNeighbour(double x, double y) {
        return findNearestNeighbour(root, x, y, true);
    }

    /**
     * Method that finds the nearest neighbour
     *
     * @param x
     * @param y
     * @return the type of node
     */
    private T findNearestNeighbour(Node<T> fromNode, final double x, final double y, boolean divX) {
        return new Object() {

            double closestDist = Double.POSITIVE_INFINITY;

            T closestNode = null;

            T findNearestNeighbour(Node<T> node, boolean divX) {
                if (node == null)
                    return null;
                double d = Calculator.calculateDistance(node.coords.x, node.coords.y, x, y);
                if (closestDist > d) {
                    closestDist = d;
                    closestNode = node.info;
                }
                double delta = divX ? x - node.coords.x : y - node.coords.y;
                double delta2 = delta * delta;
                Node<T> node1 = delta < 0 ? node.left : node.right;
                Node<T> node2 = delta < 0 ? node.right : node.left;
                findNearestNeighbour(node1, !divX);
                if (delta2 > closestDist) {
                    findNearestNeighbour(node2, !divX);
                }
                return closestNode;
            }
        }.findNearestNeighbour(fromNode, divX);
    }


    /**
     * @return root Node of the tree (or null if tree is empty)
     */
    public Node<T> root() {
        return root;
    }

    /**
     * Method that calls the method that calculates the size
     *
     * @return the size
     */
    public int size() {
        return size(root);
    }

    /**
     * Method that calculates the size of a node
     *
     * @param node
     * @return the size
     */
    private int size(Node<T> node) {
        if (node == null) return 0;
        else return size(node.getLeft()) + size(node.getRight()) + 1;
    }

    /**
     * Verifies if the tree is empty
     *
     * @return true if the tree is empty, false otherwise
     */
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * Method that inserts an element in the tree by calling the other insert method
     *
     * @param element
     * @param x
     * @param y
     */
    public void insert(T element, double x, double y) {
        Node<T> node = new Node<>(element, null, null, x, y);

        if (root == null) {
            root = node;
            nodes.add(node);
        } else
            insert(root, node, true);

        balanceNode2(root);
    }

    /**
     * Method that inserts an element in the tree
     *
     * @param currentNode
     * @param node
     * @param divX
     * @return the node
     */
    private Node<T> insert(Node<T> currentNode, Node<T> node, boolean divX) {
        if (currentNode == null) {
            return node;
        }

        if (node.coords.equals(currentNode.coords)) {
            return node;
        }

        int cmpResult = (divX ? cmpX : cmpY).compare(node, currentNode);

        if (cmpResult == -1) {
            if (currentNode.left == null) {
                nodes.add(node);
                return currentNode.left = node;
            } else return insert(currentNode.left, node, !divX);
        } else {
            if (currentNode.right == null) {
                nodes.add(node);
                return currentNode.right = node;
            } else return insert(currentNode.right, node, !divX);
        }
    }

    /**
     * Method that calls the method that calculates the size
     *
     * @return the size
     */
    public int height2() {
        return height2(root);
    }

    /**
     * Returns the height of the subtree rooted at Node node.
     *
     * @param node A valid Node within the tree
     * @return height
     */
    private int height2(Node<T> node) {
        if (node == null) {
            return -1;
        }

        int leftHeight = height2(node.getLeft());
        int rightHeight = height2(node.getRight());

        if (leftHeight > rightHeight) {
            return 1 + leftHeight;
        }

        return 1 + rightHeight;
    }

    /**
     * Returns an iterable collection of elements of the tree, reported in in-order.
     *
     * @return iterable collection of the tree's elements reported in in-order
     */
    public Iterable<T> inOrder() {
        List<T> snapshot = new ArrayList<>();
        if (root != null) {
            inOrderSubtree(root, snapshot);   // fill the snapshot recursively
        }
        return snapshot;
    }

    /**
     * Adds elements of the subtree rooted at Node node to the given snapshot
     * using an in-order traversal
     *
     * @param node     Node serving as the root of a subtree
     * @param snapshot a list to which results are appended
     */
    private void inOrderSubtree(Node<T> node, List<T> snapshot) {
        if (node == null) {
            return;
        }
        inOrderSubtree(node.getLeft(), snapshot);
        snapshot.add(node.getInfo());
        inOrderSubtree(node.getRight(), snapshot);
    }

    /**
     * Method that returns the balance factor of the tree
     *
     * @param node
     * @return balance factor of the tree
     * @throws NullPointerException
     */
    public int balanceFactor2(Node<T> node) throws NullPointerException {
        if (node == null) {
            return 0;
        }

        return height2(node.getRight()) - height2(node.getLeft());
    }

    /**
     * Method that does the right rotation of a tree
     *
     * @param node
     * @return the node
     */
    private Node<T> rightRotation2(Node<T> node) {
        //apanho o primeiro nó da esquerda
        Node<T> leftSon = node.getLeft();

        //filho direito do nó da esquerda irá ser o filho da esquerda da raiz inicial
        node.setLeft(leftSon.getRight());
        //raiz vai ser o filho direito do antigo filho da esquerda da raiz
        leftSon.setRight(node);

        node = leftSon;

        return node;
    }

    /**
     * Method that does the left rotation of a tree
     *
     * @param node
     * @return the node
     */
    private Node<T> leftRotation2(Node<T> node) {
        //apanho o primeiro nó da direita
        Node<T> rightSon = node.getRight();

        //filho esquerdo do nó da direita irá ser o filho da direita da raiz inicial
        node.setRight(rightSon.getLeft());
        //raiz vai ser o filho esquerdo do antigo filho da direita da raiz
        rightSon.setLeft(node);

        node = rightSon;

        return node;
    }

    /**
     * Method that does both left and right rotations of a tree
     *
     * @param node
     * @return the node
     */
    private Node<T> twoRotations2(Node<T> node) throws NullPointerException {
        //se for menor que 0 vamos redefinir o nó da esquerda
        if (balanceFactor2(node) < 0) {
            //rotação do nó da esquerda para a esquerda
            node.setLeft(leftRotation2(node.getLeft()));
            //rotação para a direita do node
            node = rightRotation2(node);
        } else {
            //rotação do nó da direita para a direita
            node.setRight(rightRotation2(node.getRight()));
            //rotação para a esquerda do node
            node = leftRotation2(node);
        }

        return node;
    }

    /**
     * Method that balances a certain node
     *
     * @param node
     * @return the node
     * @throws NullPointerException
     */
    private Node<T> balanceNode2(Node<T> node) throws NullPointerException {
        if (balanceFactor2(node) < -1) {
            if (balanceFactor2(node.getLeft()) <= 0)
                node = rightRotation2(node);
            else
                node = twoRotations2(node);
        }
        if (balanceFactor2(node) > 1) {
            if (balanceFactor2(node.getRight()) >= 0)
                node = leftRotation2(node);
            else
                node = twoRotations2(node);
        }
        if (Math.abs(balanceFactor2(node)) > 1)
            balanceNode2(node);
        return node;
    }
}
