package scramble.model.common.api;

public interface Pair<X,Y> {

    void setPair(X firstElement, Y secondElement);

    void setFirstElement();
    void setSecondElement();

    X getFirstElement();
    Y getSecondElement();
}
