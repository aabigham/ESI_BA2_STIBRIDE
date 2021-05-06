package atl.stibride.model.dijkstra;

import atl.stibride.jdbc.dto.StationDto;

import java.util.*;

/**
 * The Dijkstra algorithm is to continuously eliminate longer paths
 * between the starting node and all possible destinations.
 * <p>
 * In this case, it is required to find the best path between two metro stations.
 */
public class Dijkstra {

    /**
     * Computes the best path between to metro stations.
     *
     * @param stations    all of the available metro stations.
     * @param origin      the origin station.
     * @param destination the destination station.
     * @return the best path between the origin and destination station.
     */
    public static List<StationDto> computePath(List<StationDto> stations,
                                               StationDto origin, StationDto destination) {
        // Graph initialization
        Graph graph = new Graph();
        graph.initialize(stations);
        graph = calculateShortestPathFromSource(graph, graph.getNode(origin.getKey()));

        // Return
        List<StationDto> result = new ArrayList<>();
        graph.getNode(destination.getKey())
                .getShortestPath()
                .forEach(nodePath -> result.add(nodePath.getStationDto()));

        result.add(destination); // Destination station to the end
        return result;
    }

    /**
     * Gets the shortest path from the source node to every node in the graph.
     *
     * @param graph  the graph containing every nodes.
     * @param source the source node.
     * @return the shortest path from the source node to every node in the graph.
     */
    private static Graph calculateShortestPathFromSource(Graph graph, Node source) {
        source.setDistance(0);

        Set<Node> settledNodes = new HashSet<>();
        Set<Node> unsettledNodes = new HashSet<>();
        unsettledNodes.add(source);

        while (unsettledNodes.size() != 0) {
            Node currentNode = getLowestDistanceNode(unsettledNodes);
            unsettledNodes.remove(currentNode);
            for (Map.Entry<Node, Integer> adjacencyPair
                    : currentNode.getAdjacentNodes().entrySet()) {
                Node adjacentNode = adjacencyPair.getKey();
                Integer edgeWeigh = adjacencyPair.getValue();
                if (!settledNodes.contains(adjacentNode)) {
                    CalculateMinimumDistance(adjacentNode, edgeWeigh, currentNode);
                    unsettledNodes.add(adjacentNode);
                }
            }
            settledNodes.add(currentNode);
        }
        return graph;
    }

    /**
     * The getLowestDistanceNode() method, returns the node with the lowest
     * distance from the unsettled nodes set.
     *
     * @param unsettledNodes the set of unsettled nodes in the graph.
     * @return the node with the lowest distance from the unsettled nodes set.
     */
    private static Node getLowestDistanceNode(Set<Node> unsettledNodes) {
        Node lowestDistanceNode = null;
        int lowestDistance = Integer.MAX_VALUE;
        for (Node node : unsettledNodes) {
            int nodeDistance = node.getDistance();
            if (nodeDistance < lowestDistance) {
                lowestDistance = nodeDistance;
                lowestDistanceNode = node;
            }
        }
        return lowestDistanceNode;
    }

    /**
     * ThecalculateMinimumDistance() method compares the actual distance
     * with the newly calculated one while following the newly explored path.
     *
     * @param evaluationNode the current node to compare.
     * @param edgeWeigh      the amount of effort needed to travel from one place to another.
     * @param sourceNode     the source node.
     */
    private static void CalculateMinimumDistance(Node evaluationNode,
                                                 Integer edgeWeigh, Node sourceNode) {
        Integer sourceDistance = sourceNode.getDistance();
        if (sourceDistance + edgeWeigh < evaluationNode.getDistance()) {
            evaluationNode.setDistance(sourceDistance + edgeWeigh);
            LinkedList<Node> shortestPath = new LinkedList<>(sourceNode.getShortestPath());
            shortestPath.add(sourceNode);
            evaluationNode.setShortestPath(shortestPath);
        }
    }

}
