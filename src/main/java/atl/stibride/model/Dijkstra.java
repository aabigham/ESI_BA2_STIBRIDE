package atl.stibride.model;

import atl.stibride.dto.StationDto;

import java.util.*;

public class Dijkstra {

    public static List<StationDto> computePath(List<StationDto> stations,
                                               StationDto start, StationDto end) {
        // graph
        Graph graph = new Graph();
        graph.initialize(stations);
        graph = calculateShortestPathFromSource(graph, graph.getNode(start.getKey()));

        List<StationDto> result = new ArrayList<>();
        /*for (Node node : graph.getNodes()) {
            //System.out.println(node.getStationDto().getKey() + " = " + node.getShortestPath().toString());
            if (node.getStationDto().equals(end)) {
                node.getShortestPath()
                        .forEach(nodePath -> result.add(nodePath.getStationDto()));
                break;
            }
        }*/
        /*graph.getNodes()
                .stream()
                .filter(node -> node.getStationDto().equals(end))
                .findAny()
                .orElseThrow()
                .getShortestPath()
                .forEach(nodePath -> result.add(nodePath.getStationDto()));*/

        graph.getNode(end.getKey())
                .getShortestPath()
                .forEach(nodePath -> result.add(nodePath.getStationDto()));

        result.add(end); // Destination station
        return result;
    }

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
