package scramble1981_remastered.model.common.api;

public interface Pair<X,Y> {
    X getFirstElement();
    Y getSecondElement();

    void setFirstElement(X secondElement);
    void setSecondElement(Y secondElement);
}
