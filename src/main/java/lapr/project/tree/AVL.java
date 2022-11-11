package lapr.project.tree;

/**
 * @author Luís Araújo
 */
public class AVL<E extends Comparable<E>> extends BST<E> {

    /**
     * Returns the Node containing a specific Element, or null otherwise.
     *
     * @param element the element to find
     * @return the Node that contains the Element, or null otherwise
     * <p>
     * This method despite not being essential is very useful. It is written
     * here in order to be used by this class and its subclasses avoiding
     * recoding. So its access level is protected
     */

    public E findElement(E element) {
        Node<E> node = find(element);
        if (node == null)
            return null;
        return node.getElement();
    }

    /**
     * Method that returns the balance factor of the tree
     *
     * @param node
     * @return balance factor of the tree
     * @throws NullPointerException
     */
    private int balanceFactor(Node<E> node){

        if (node == null) {
            return 0;
        }

        return height(node.getRight()) - height(node.getLeft());
    }

    /**
     * Method that does the right rotation of a tree
     *
     * @param node
     * @return the node
     */
    private Node<E> rightRotation(Node<E> node) {
        //apanho o primeiro nó da esquerda
        Node<E> leftSon = node.getLeft();

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
    private Node<E> leftRotation(Node<E> node) {
        //apanho o primeiro nó da direita
        Node<E> rightSon = node.getRight();

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
    private Node<E> twoRotations(Node<E> node) throws NullPointerException {
        //se for menor que 0 vamos redefinir o nó da esquerda
        if (balanceFactor(node) < 0) {
            //rotação do nó da esquerda para a esquerda
            node.setLeft(leftRotation(node.getLeft()));
            //rotação para a direita do node
            node = rightRotation(node);
        } else {
            //rotação do nó da direita para a direita
            node.setRight(rightRotation(node.getRight()));
            //rotação para a esquerda do node
            node = leftRotation(node);
        }

        return node;
    }

    /**
     * if(balanceFactor(node)<-1)
     * if(balanceFactor(node.getLeft()<=0)
     * ...
     * else
     * ...
     * <p>
     * if(balanceFactor(node)>1)
     * if(balanceFactor(node.getRight()>=0)
     * ...
     * else
     * ...
     *
     * @param node
     * @return
     */

    /**
     * Method that balances a certain node
     *
     * @param node
     * @return the node
     * @throws NullPointerException
     */
    private Node<E> balanceNode(Node<E> node) throws NullPointerException {
        if (balanceFactor(node) < -1) {
            if (balanceFactor(node.getLeft()) <= 0)
                node = rightRotation(node);
            else
                node = twoRotations(node);
        }
        if (balanceFactor(node) > 1) {
            if (balanceFactor(node.getRight()) >= 0)
                node = leftRotation(node);
            else
                node = twoRotations(node);
        }
        if (Math.abs(balanceFactor(node)) > 1)
            balanceNode(node);
        return node;
    }

    /**
     * Method that does the insert of an element in the tree by calling the method "insert2"
     *
     * @param element
     */
    @Override
    public void insert(E element) {
        root = insert2(element, root);
    }

    /**
     * Method that does the insert of an element in the tree
     *
     * @param element
     * @param node
     * @return the node
     */
    private Node<E> insert2(E element, Node<E> node) {
        if (node == null) {
            return new Node(element, null, null);
        }

        if (node.getElement() == element) {
            node.setElement(element);
        } else {
            if (node.getElement().compareTo(element) > 0) {
                node.setLeft(insert2(element, node.getLeft()));
                node = balanceNode(node);
            } else {
                node.setRight(insert2(element, node.getRight()));
                node = balanceNode(node);
            }
        }

        return node;
    }

    /**
     * Method that does the removal of an element off the tree by calling the method "remove2"
     *
     * @param element
     */
    @Override
    public void remove(E element) {
        root = remove2(element, root());
    }

    /**
     * Method that does the removal of an element off the tree
     *
     * @param element
     * @param node
     * @return the node
     */
    private Node<E> remove2(E element, BST.Node<E> node) {
        if (node == null) {
            return null;
        }

        if (node.getElement() == element) {
            if (node.getLeft() == null && node.getRight() == null) {
                return null;
            }
            if (node.getLeft() == null) {
                return node.getRight();
            }
            if (node.getRight() == null) {
                return node.getLeft();
            }
            E min = smallestElement(node.getRight());
            node.setElement(min);
            node.setRight(remove2(min, node.getRight()));
        } else if (node.getElement().compareTo(element) > 0) {
            node.setLeft(remove2(element, node.getLeft()));
        } else {
            node.setRight(remove2(element, node.getRight()));
        }

        return node;
    }

    /**
     * Method that verifies if two nodes are equal
     *
     * @param otherObj
     * @return true if they are equal and false otherwise
     */
    @Override
    public boolean equals(Object otherObj) {

        if (this == otherObj) {
            return true;
        }

        if (otherObj == null || this.getClass() != otherObj.getClass()) {
            return false;
        }

        AVL<E> second = (AVL<E>) otherObj;
        return equals(root, second.root);
    }

    /**
     * Method that verifies if two nodes are equal
     *
     * @param root1
     * @param root2
     * @return true if they are equal and false otherwise
     */
    public boolean equals(Node<E> root1, Node<E> root2) {
        if (root1 == null && root2 == null) {
            return true;
        } else if (root1 != null && root2 != null) {
            if (root1.getElement().compareTo(root2.getElement()) == 0) {
                return equals(root1.getLeft(), root2.getLeft())
                        && equals(root1.getRight(), root2.getRight());
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
