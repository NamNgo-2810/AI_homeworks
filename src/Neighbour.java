public class Neighbour {

    public final double cost;
    public final Node target;

    public Neighbour(Node target, double cost) {
        this.cost = cost;
        this.target = target;
    }
}

