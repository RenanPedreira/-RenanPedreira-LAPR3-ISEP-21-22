package lapr.project.tree;

import java.util.List;

public class TreeUtils {

    /**
     * Empty constructor
     */
    private TreeUtils(){

    }

    /**
     * Method that returns a BST sorted
     * @param listUnsorted
     * @param <E>
     * @return BST sorted
     */
    public static <E extends Comparable<E>> Iterable<E> sortByBST(List<E> listUnsorted) {
        BST<E> tree = new BST<>();
        for (E e : listUnsorted) {
            tree.insert(e);
        }
        return tree.inOrder();
    }
}