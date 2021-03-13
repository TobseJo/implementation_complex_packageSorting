package event;

public class ChangeSearchAlgorithm {
    private String algorithm;

    public ChangeSearchAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public String toString() {
        return "Event: ChangeSearchAlgorithm";
    }

    public String getAlgorithm() {
        return algorithm;
    }
}
