package scramble.model.common.impl;

import scramble.model.common.api.Pair;

/**
 * Implementation of the generic class Pair.
 * 
 * @param <X> type of the firstElement
 * @param <Y> type of the secondElement
 */
public class PairImpl<X, Y> implements Pair<X, Y> {

    private X firstElement;
    private Y secondElement;

    /**
     * Class constructor.
     * 
     * @param firstElement
     * @param secondElement
     */
    public PairImpl(final X firstElement, final Y secondElement) {
        this.firstElement = firstElement;
        this.secondElement = secondElement;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public X getFirstElement() {
        return this.firstElement;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Y getSecondElement() {
        return secondElement;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setFirstElement(final X firstElement) {
        this.firstElement = firstElement;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSecondElement(final Y secondElement) {
        this.secondElement = secondElement;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPair(final Pair<X, Y> newPair) {
        setFirstElement(newPair.getFirstElement());
        setSecondElement(newPair.getSecondElement());
    }
}
