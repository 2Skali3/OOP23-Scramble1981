package scramble.model.common.impl;

import scramble.model.common.api.Pair;

/**
 * Implementation of the Pair interface.
 * @param <X> type of the first element stored in PairImpl
 * @param <Y> type of the second element stored in PairImpl
 */
public class PairImpl<X, Y> implements Pair<X, Y> {

    private X firstElement;
    private Y secondElement;

    /**
     * Costructor of the class PairImpl.
     * @param firstElement
     * @param secondElement
     */
    public PairImpl(final X firstElement, final Y secondElement) {
        this.firstElement = firstElement;
        this.secondElement = secondElement;
    }
    /**
     * Getter for the first element of the PairImpl class.
     * @return the first element of the PairImpl
     */
    @Override
    public X getFirstElement() {
        return this.firstElement;
    }
    /**
     * Getter for the second element of the PairImpl class.
     * @return the first element of the Pair
     */
    @Override
    public Y getSecondElement() {
        return secondElement;
    }
    /**
     * Setter for the first element of the PairImpl class.
     * @param firstElement new value of the first element
     */
    @Override
    public void setFirstElement(final X firstElement) {
        this.firstElement = firstElement;
    }
    /**
     * Setter for the second element of the PairImpl class.
     * @param secondElement new value of the second element
     */
    @Override
    public void setSecondElement(final Y secondElement) {
        this.secondElement = secondElement;
    }
}