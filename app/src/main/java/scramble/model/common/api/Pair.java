package scramble.model.common.api;

public interface Pair<X,Y> {

    void setPair(X firstElement, Y secondElement);

    void setFirstElement(X firstElement);
    void setSecondElement(Y secondElement);

    X getFirstElement();
    Y getSecondElement();
}
