package scramble.model.common.impl;

import scramble.model.common.api.Pair;

public class PairImpl<X,Y> implements Pair<X,Y>{

    private X firstElement;
    private Y secondElement;

    public PairImpl(X firstElement, Y secondElement){
        this.firstElement = firstElement;
        this.secondElement = secondElement;
    }

    @Override
    public X getFirstElement() {
        return this.firstElement;
    }

    @Override
    public Y getSecondElement() {
        return secondElement;
    }

    @Override
    public void setFirstElement(X firstElement) {
        this.firstElement = firstElement;
    }

    @Override
    public void setSecondElement(Y secondElement) {
        this.secondElement = secondElement;
    }
    
}
