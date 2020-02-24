package dijkstra;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Node {
    private final int val;
    private final List<Edge> edges;

    public Node(int val) {
        this.val = val;
        this.edges = new ArrayList<>();
    }

    public int getVal() {
        return val;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return val == node.val;
    }

    @Override
    public int hashCode() {
        return Objects.hash(val);
    }

    @Override
    public String toString() {
        return String.valueOf(val);
    }
}