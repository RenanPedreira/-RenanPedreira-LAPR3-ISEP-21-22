package lapr.project.utils;

public class Pair<T, U> {
    /**
     * First element of the pair
     */
    T firstElement;
    /**
     * Second element of the pair
     */
    U secondElement;

    /**
     * Constructor that creates a pair with the first and second element
     * @param first
     * @param second
     */
    public Pair(T first, U second){
        this.firstElement = first;
        this.secondElement = second;
    }

    /**
     * Method that returns the first element of the pair
     * @return first element of the pair
     */
    public T getFirst(){
        return this.firstElement;
    }

    /**
     * Method that returns the second element of the pair
     * @return second element of the pair
     */
    public U getSecond(){
        return this.secondElement;
    }

    /**
     * Method that adds a pair
     * @param integerIntegerPair
     */
    public void add(Pair<U, U> integerIntegerPair) {
        throw new UnsupportedOperationException("Not implemented yet.");
    }
}
