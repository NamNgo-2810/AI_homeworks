import java.io.IOException;
import java.util.*;

public class TKCT {

    public static void main(String[] args) throws IOException {
        Node a = new Node("A");
        Node b = new Node("B");
        Node c = new Node("C");
        Node d = new Node("D");
        Node f = new Node("F");
        Node g = new Node("G");
        Node h = new Node("H");
        Node i = new Node("I");

        a.neighbours.add(new Neighbour(b, 1));
        a.neighbours.add(new Neighbour(c, 3));
        a.neighbours.add(new Neighbour(f, 6));
        c.neighbours.add(new Neighbour(d, 5));
        c.neighbours.add(new Neighbour(g, 2));
        f.neighbours.add(new Neighbour(h, 1));
        f.neighbours.add(new Neighbour(i, 7));

        TKCT(a, b);
    }

    public static void TKCT(Node root, Node target) {

        root.pathCost = 0;
        PriorityQueue<Node> MO = new PriorityQueue<>(20,
                (i, j) -> {
                    if (i.pathCost > j.pathCost) return 1;
                    else if (i.pathCost < j.pathCost) return -1;
                    else return 0;
                });

        MO.add(root);
        Set<Node> DONG = new HashSet<>();
        boolean found = false;

        do {
            Node current = MO.poll();
            DONG.add(current);

            if (current.name.equals(target.name)) {
                    found = true;
                    break;
            }
            if (current.neighbours.size() == 0) continue;
            for (Neighbour n : current.neighbours) {
                Node child = n.target;
                double cost = n.cost;

                if (!DONG.contains(child) && !MO.contains(child)) {
                    child.pathCost = current.pathCost + cost;
                    child.parent = current;
                    MO.add(child);
                } else if (MO.contains(child) && (child.pathCost > (current.pathCost + cost))) {
                    child.parent = current;
                    child.pathCost = current.pathCost + cost;
                    MO.remove(child);
                    MO.add(child);
                }
            }
        } while (!MO.isEmpty() && !found);
        if (!found) {
            System.out.println("Can not find path from " + root.name + " to " + target.name);
            return;
        }
        List<String> path = printPath(target);

        System.out.println("Path: " + path);
        System.out.println("Path cost from " + root.name + " to " + target.name + " is " + target.pathCost);
    }

    public static List<String> printPath(Node target) {
        List<String> path = new ArrayList<>();
        for (Node node = target; node != null; node = node.parent) {
            path.add(node.name);
        }

        Collections.reverse(path);

        return path;
    }
}
