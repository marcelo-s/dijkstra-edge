package dijkstra;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class DijkstraEdgesTest {


    // See graph in http://andrewd.ces.clemson.edu/courses/cpsc212/f06/labs/lab09.html
    // Changed from directed graph to undirected graph
    // Changed edge weight of Node 1 to Node 2, from 3 to 4 to eliminate the ambiguity of paths
    @Test
    public void findShortestPathsDistance() {
        int[][] edges = {
                {1, 2, 4},
                {1, 3, 1},
                {2, 3, 2},
                {2, 4, 3},
                {2, 5, 6},
                {3, 5, 2},
                {5, 6, 1},
                {6, 4, 1},

        };

        DijkstraEdges dijkstraEdges = new DijkstraEdges(edges);
        int source = 1;
        dijkstraEdges.findShortestPathsFrom(source);
        Map<Node, Integer> distanceMap = dijkstraEdges.getDistanceMap();

        Map<Integer, Integer> distancesExpected = new HashMap<>();
        distancesExpected.put(1, 0);
        distancesExpected.put(2, 3);
        distancesExpected.put(3, 1);
        distancesExpected.put(4, 5);
        distancesExpected.put(5, 3);
        distancesExpected.put(6, 4);

        distanceMap.forEach((node, distance) -> {
            assertEquals("Failed distance for node : " + node.getVal(), distancesExpected.get(node.getVal()), distance);
        });
    }

    @Test
    public void findShortestPathsParents() {
        int[][] edges = {
                {1, 2, 4},
                {1, 3, 1},
                {2, 3, 2},
                {2, 4, 3},
                {2, 5, 6},
                {3, 5, 2},
                {5, 6, 1},
                {6, 4, 1},
        };

        DijkstraEdges dijkstraEdges = new DijkstraEdges(edges);
        int source = 1;
        dijkstraEdges.findShortestPathsFrom(source);
        Map<Node, Node> parentsMap = dijkstraEdges.getParentsMap();

        Map<Integer, Integer> parentsExpected = new HashMap<>();
        parentsExpected.put(1, 0);
        parentsExpected.put(2, 3);
        parentsExpected.put(3, 1);
        parentsExpected.put(4, 6);
        parentsExpected.put(5, 3);
        parentsExpected.put(6, 5);

        parentsMap.forEach((node, parentNode) -> {
            if (node.getVal() == source) {
                assertNull(parentNode);
            } else {
                assertEquals("Failed parent for node : " + node.getVal(),
                        parentsExpected.get(node.getVal()).intValue(),
                        parentNode.getVal()
                );
            }
        });
    }

    @Test
    public void getAllPaths() {
        int[][] edges = {
                {1, 2, 4},
                {1, 3, 1},
                {2, 3, 2},
                {2, 4, 3},
                {2, 5, 6},
                {3, 5, 2},
                {5, 6, 1},
                {6, 4, 1},
        };

        DijkstraEdges dijkstraEdges = new DijkstraEdges(edges);
        int source = 1;
        dijkstraEdges.findShortestPathsFrom(source);
        Map<Node, List<Node>> allShortestPaths = dijkstraEdges.getAllShortestPaths();

        Map<Node, List<Node>> pathsExpected = new HashMap<>();
        pathsExpected.put(new Node(1), Arrays.asList(new Node(1)));
        pathsExpected.put(new Node(2), Arrays.asList(new Node(1), new Node(3), new Node(2)));
        pathsExpected.put(new Node(3), Arrays.asList(new Node(1), new Node(3)));
        pathsExpected.put(new Node(4), Arrays.asList(new Node(1), new Node(3), new Node(5), new Node(6), new Node(4)));
        pathsExpected.put(new Node(5), Arrays.asList(new Node(1), new Node(3), new Node(5)));
        pathsExpected.put(new Node(6), Arrays.asList(new Node(1), new Node(3), new Node(5), new Node(6)));

        allShortestPaths.forEach((node, list) -> {
            assertEquals("Path failed for node : " + node, pathsExpected.get(node), list);
        });


    }

    // See graph defined in : https://www.geeksforgeeks.org/dijkstras-shortest-path-algorithm-greedy-algo-7/
    @Test
    public void findShortestPathsParents2() {
        int[][] edges = {
                {0, 1, 4},
                {0, 7, 8},
                {1, 7, 11},
                {1, 2, 8},
                {7, 8, 7},
                {7, 6, 1},
                {2, 8, 2},
                {2, 3, 7},
                {2, 5, 4},
                {8, 6, 6},
                {6, 5, 2},
                {3, 4, 9},
                {3, 5, 14},
                {5, 4, 10},

        };

        DijkstraEdges dijkstraEdges = new DijkstraEdges(edges);
        int source = 0;
        dijkstraEdges.findShortestPathsFrom(source);
        Map<Node, Integer> distanceMap = dijkstraEdges.getDistanceMap();

        Map<Integer, Integer> distancesExpected = new HashMap<>();
        distancesExpected.put(0, 0);
        distancesExpected.put(1, 4);
        distancesExpected.put(2, 12);
        distancesExpected.put(3, 19);
        distancesExpected.put(4, 21);
        distancesExpected.put(5, 11);
        distancesExpected.put(6, 9);
        distancesExpected.put(7, 8);
        distancesExpected.put(8, 14);

        distanceMap.forEach((node, distance) -> {
            assertEquals("Failed distance for node : " + node.getVal(), distancesExpected.get(node.getVal()), distance);
        });
    }
}