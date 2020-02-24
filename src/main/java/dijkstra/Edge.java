package dijkstra;

import java.util.Arrays;
import java.util.List;

public class Edge {
    Node u;
    Node v;
    int weight;

    public Edge(Node u, Node v, int weight) {
        this.u = u;
        this.v = v;
        this.weight = weight;
    }

    public List<Node> getNodesOfEdge() {
        return Arrays.asList(u, v);
    }

    public int getWeight() {
        return this.weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void addToWeight(int amount) {
        this.weight += amount;
    }

    public Node getOtherNode(Node node) {
        return node.equals(u) ? v : u;
    }

}
