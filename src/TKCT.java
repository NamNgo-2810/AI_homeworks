import java.io.IOException;
import java.util.*;

public class TKCT {

    public static void main(String[] args) throws IOException {
        Node BTL = new Node("Bắc Từ Liêm");

        Node NTL = new Node("Nam Từ Liêm");

        Node TH = new Node("Tây Hồ");

        Node LB = new Node("Long Biên");

        Node CG = new Node("Cầu Giấy");

        Node BD = new Node("Ba Đình");

        Node HD = new Node("Hà Đông");

        Node TX = new Node("Thanh Xuân");

        Node HM = new Node("Hoàng Mai");

        Node HBT = new Node("Hai Bà Trưng");

        Node DD = new Node("Đống Đa");

        Node HK = new Node("Hoàn Kiếm");

        Node HCM = new Node("Tp. HCM");

        BTL.neighbours.add(new Neighbour(TH,8));

        BTL.neighbours.add(new Neighbour(CG,7));

        BTL.neighbours.add(new Neighbour(NTL,10));

        NTL.neighbours.add(new Neighbour(BTL,10));

        NTL.neighbours.add(new Neighbour(CG,5));

        NTL.neighbours.add(new Neighbour(TX,6));

        NTL.neighbours.add(new Neighbour(HD,8));

        TH.neighbours.add(new Neighbour(BTL,8));

        TH.neighbours.add(new Neighbour(CG,7));

        TH.neighbours.add(new Neighbour(BD,7));

        TH.neighbours.add(new Neighbour(LB,18));

        LB.neighbours.add(new Neighbour(TH,18));

        LB.neighbours.add(new Neighbour(BD,11));

        LB.neighbours.add(new Neighbour(HK,9));

        LB.neighbours.add(new Neighbour(HBT,10));

        CG.neighbours.add(new Neighbour(TH,7));

        CG.neighbours.add(new Neighbour(BTL,7));

        CG.neighbours.add(new Neighbour(NTL,5));

        CG.neighbours.add(new Neighbour(TX,8));

        CG.neighbours.add(new Neighbour(DD,7));

        CG.neighbours.add(new Neighbour(BD,3));

        BD.neighbours.add(new Neighbour(TH,7));

        BD.neighbours.add(new Neighbour(BD,3));

        BD.neighbours.add(new Neighbour(DD,5));

        BD.neighbours.add(new Neighbour(HK,6));

        BD.neighbours.add(new Neighbour(LB,11));

        HD.neighbours.add(new Neighbour(NTL,8));

        HD.neighbours.add(new Neighbour(TX,7));

        TX.neighbours.add(new Neighbour(HD,7));

        TX.neighbours.add(new Neighbour(NTL,6));

        TX.neighbours.add(new Neighbour(CG,8));

        TX.neighbours.add(new Neighbour(DD,4));

        TX.neighbours.add(new Neighbour(HBT,7));

        TX.neighbours.add(new Neighbour(HM,8));

        HM.neighbours.add(new Neighbour(HBT,4));

        HM.neighbours.add(new Neighbour(TX,8));

        HBT.neighbours.add(new Neighbour(HM,4));

        HBT.neighbours.add(new Neighbour(TX,7));

        HBT.neighbours.add(new Neighbour(DD,4));

        HBT.neighbours.add(new Neighbour(HK,4));

        HBT.neighbours.add(new Neighbour(LB,10));

        DD.neighbours.add(new Neighbour(BD,5));

        DD.neighbours.add(new Neighbour(CG,7));

        DD.neighbours.add(new Neighbour(TX,4));

        DD.neighbours.add(new Neighbour(HBT,4));

        HK.neighbours.add(new Neighbour(LB,9));

        HK.neighbours.add(new Neighbour(BD,6));

        HK.neighbours.add(new Neighbour(HBT,4));

        TKCT(HD, HCM);
    }

    public static void TKCT(Node root, Node target) {

        root.pathCost = 0;
        PriorityQueue<Node> MO = new PriorityQueue<>((i, j) -> {
                    if (i.pathCost > j.pathCost) return 1;
                    else if (i.pathCost < j.pathCost) return -1;
                    else return 0;
                });

        MO.add(root);
        Set<Node> DONG = new HashSet<>();
        boolean found = false;

        while (!MO.isEmpty()) {
            Node current = MO.poll();
            DONG.add(current);

            if (current.name.equals(target.name)) {
                    found = true;
                    break;
            }
            for (Neighbour n: current.neighbours) {
                Node child = n.target;
                double cost = n.cost;

                if (!DONG.contains(child) && !MO.contains(child)) {
                    child.pathCost = current.pathCost + cost;
                    child.parent = current;
                    MO.add(child);
                }
                else if (MO.contains(child) && (child.pathCost > (current.pathCost + cost))) {
                    child.parent = current;
                    child.pathCost = current.pathCost + cost;
                    MO.remove(child);
                    MO.add(child);
                }
            }
        }
        if (!found) {
            System.out.println("Không tìm thấy đường đi từ " + root.name + " đến " + target.name);
            return;
        }
        List<String> path = printPath(target);

        System.out.println("Đường đi: " + path);
        System.out.println("Chi phí đi từ " + root.name + " đến " + target.name + " là " + target.pathCost);
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
