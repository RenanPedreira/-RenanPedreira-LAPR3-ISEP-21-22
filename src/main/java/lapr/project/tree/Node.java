package lapr.project.tree;

import java.awt.geom.Point2D;

/**
 * Nested static class for 2D tree node.
 */
public class Node<T> {
    /**
     * Node´ coords
     */
    protected Point2D.Double coords;

    /**
     * Node´s information
     */
    protected T info;

    /**
     * Node´s left child
     */
    protected Node<T> left;

    /**
     * Node´s right child
     */
    protected Node<T> right;

    /**
     * Constructor that creates a node with their children and their coordinates
     * @param e
     * @param leftChild
     * @param rightChild
     * @param x
     * @param y
     */
    public Node(T e, Node<T> leftChild, Node<T> rightChild, double x, double y) {
        info = e;
        coords = new Point2D.Double(x, y);
        left = leftChild;
        right = rightChild;
    }

    // accessor methods
    public T getInfo() {
        return info;
    }

    // update methods
    public void setInfo(T e) {
        info = e;
    }

    public Node<T> getLeft() {
        return left;
    }

    public void setLeft(Node<T> leftChild) {
        left = leftChild;
    }

    public Node<T> getRight() {
        return right;
    }

    public void setRight(Node<T> rightChild) {
        right = rightChild;
    }

    public Point2D.Double getCoords() {
        return this.coords;
    }

    public void setCoords(double x, double y) {
        coords.setLocation(x, y);
    }
}
