package dijkstra;

import java.util.*;

public class DijkstraEdges {

    private Map<Node, Node> parentsMap;
    private Map<Node, Integer> distanceMap;

    private final int[][] edges;
    private final Map<Integer, Node> nodes = new HashMap<>();

    public DijkstraEdges(int[][] edges) {
        this.edges = edges;
        createGraph();
    }

    private void createGraph() {
        for (int[] edge : edges) {
            int uVal = edge[0];
            int vVal = edge[1];
            int weight = edge[2];

            Node u = getNode(uVal);
            Node v = getNode(vVal);
            createEdge(u, v, weight);
        }
    }

    private Node getNode(int uVal) {
        if (nodes.containsKey(uVal)) return nodes.get(uVal);
        else {
            return createNode(uVal);
        }
    }

    private Node createNode(int uVal) {
        Node node = new Node(uVal);
        nodes.put(uVal, node);
        return node;
    }

    private void createEdge(Node u, Node v, int weight) {
        Edge edge = new Edge(u, v, weight);
        u.getEdges().add(edge);
        v.getEdges().add(edge);
    }

    public void findShortestPathsFrom(int source) {
        if (!nodes.containsKey(source)) throw new IllegalArgumentException("There is no source node : " + source);

        PriorityQueue<Edge> minHeap = new PriorityQueue<>(Comparator.comparingInt(Edge::getWeight));
        Node startNode = nodes.get(source);
        minHeap.addAll(startNode.getEdges());

        Set<Node> visitedNodes = new HashSet<>();
        visitedNodes.add(startNode);

        parentsMap = new HashMap<>();
        distanceMap = new HashMap<>();
        // startNode has no parent
        parentsMap.put(startNode, null);
        // start node has distance 0 to itself
        distanceMap.put(startNode, 0);

        while (!minHeap.isEmpty()) {
            Edge minEdge = minHeap.remove();
            Node targetNode = getTargetNode(visitedNodes, minEdge);
            if (targetNode != null) {
                distanceMap.put(targetNode, minEdge.weight);
                parentsMap.put(targetNode, minEdge.getOtherNode(targetNode));
                updateEdges(targetNode, minEdge.weight);
                minHeap.addAll(targetNode.getEdges());
                visitedNodes.add(targetNode);
            }
        }
    }

    private void updateEdges(Node targetNode, int weight) {
        targetNode.getEdges().forEach(edge -> edge.addToWeight(weight));
    }

    // Returns the un-visited node of the minEdge
    private Node getTargetNode(Set<Node> visitedNodes, Edge minEdge) {
        List<Node> nodes = minEdge.getEdges();
        if (visitedNodes.containsAll(nodes)) return null;
        return visitedNodes.contains(nodes.get(0)) ? nodes.get(1) : nodes.get(0);
    }

    public Map<Node, Node> getParentsMap() {
        if (parentsMap == null) throw new IllegalStateException("Shortest path operation was not performed");
        return parentsMap;
    }

    public Map<Node, Integer> getDistanceMap() {
        if (distanceMap == null) throw new IllegalStateException("Shortest path operation was not performed");
        return distanceMap;
    }

    public Map<Node, List<Node>> getAllShortestPaths() {
        Map<Node, List<Node>> allShortestPaths = new HashMap<>();
        parentsMap.forEach((node, parentNode) -> {
            addShortestPath(node, parentNode, allShortestPaths);
        });
        return allShortestPaths;
    }

    private void addShortestPath(Node node, Node parentNode, Map<Node, List<Node>> allShortestPaths) {
        Deque<Node> stack = new ArrayDeque<>();
        stack.addFirst(node);
        while(parentNode != null) {
            stack.addFirst(parentNode);
            parentNode = parentsMap.get(parentNode);
        }
        allShortestPaths.put(node, reverseStack(stack));
    }

    private List<Node> reverseStack(Deque<Node> stack) {
        List<Node> list = new ArrayList<>();
        while(!stack.isEmpty()) {
            list.add(stack.removeFirst());
        }
        return list;
    }
}
