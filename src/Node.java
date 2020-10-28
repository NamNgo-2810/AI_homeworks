import java.util.ArrayList;
import java.util.List;

public class Node {

    public final String name;
    public double pathCost;
    public List<Neighbour> neighbours;
    public Node parent;

    public Node(String name) {
        this.name = name;
        this.neighbours = new ArrayList<>();
    }

}

