package event;

public class ChangeSearchAlgorithm {
    private Object algorithm;
    public ChangeSearchAlgorithm(Object algorithm){
        this.algorithm = algorithm;
    }

    public String toString(){
        return "Event: ChangeSearchAlgorithm";
    }

    public Object getAlgorithm() {
        return algorithm;
    }
}
