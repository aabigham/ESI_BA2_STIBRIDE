package atl.stibride.model;

import atl.stibride.dto.StationDto;

import java.util.*;


public class Dijkstra {

    private static class Node {

        private StationDto stationDto;
        private List<Node> shortestPath = new LinkedList<>();
        private Integer distance = Integer.MAX_VALUE;
        Map<Node, Integer> adjacentNodes = new HashMap<>();

        public void addDestination(Node destination, int distance) {
            adjacentNodes.put(destination, distance);
        }

        public Node(StationDto stationDto) {
            this.stationDto = stationDto;
        }

        // getters and setters
        public StationDto getStationDto() {
            return stationDto;
        }

        public void setStationDto(StationDto stationDto) {
            this.stationDto = stationDto;
        }

        public List<Node> getShortestPath() {
            return shortestPath;
        }

        public void setShortestPath(List<Node> shortestPath) {
            this.shortestPath = shortestPath;
        }

        public Integer getDistance() {
            return distance;
        }

        public void setDistance(Integer distance) {
            this.distance = distance;
        }

        public Map<Node, Integer> getAdjacentNodes() {
            return adjacentNodes;
        }

        @Override
        public String toString() {
            return "Node{" + stationDto.toString() + " }";
        }
    }

    private static class Graph {

        private Set<Node> nodes = new HashSet<>();

        public void addNode(Node nodeA) {
            nodes.add(nodeA);
        }

        // getters and setters
        public Set<Node> getNodes() {
            return nodes;
        }

        public void setNodes(Set<Node> nodes) {
            this.nodes = nodes;
        }
    }


    /**
     * Computes the best path to take between the two stations.
     *
     * @param start the starting station.
     * @param end   the end station.
     * @return the best path to take between the two stations.
     * @throws Exception if an error occurred.
     */
    public static List<StationDto> computePath(
            List<StationDto> allStations, StationDto start, StationDto end) throws Exception {
        // TODO better algorithm
        if (start.equals(end))
            throw new IllegalArgumentException("Start station and destination station must be different.");

        //List<StationDto> allStations = repoManager.getAllStations();
        int id_start = start.getKey();
        int id_end = end.getKey();

        List<StationDto> path = new ArrayList<>();
        path.add(start);
        Map<Integer, Boolean> visited = new HashMap<>();

        List<StationDto> queue = new LinkedList<>();

        visited.put(id_start, true);
        queue.add(start);

        while (!queue.isEmpty()) {
            StationDto currDto = queue.remove(0);
            Iterator<Integer> it = currDto.getNeighbors().listIterator();
            while (it.hasNext()) {
                Integer currNeighborKey = it.next();
                StationDto currNeighborDto = allStations.stream()
                        .filter(stationDto -> stationDto.getKey().equals(currNeighborKey))
                        .findAny()
                        .orElseThrow();

                if (!visited.containsKey(currNeighborKey)) {
                    visited.put(currNeighborKey, true);
                    path.add(currNeighborDto);
                    queue.add(currNeighborDto);
                    if (currNeighborKey == id_end) {
                        return path;
                    }
                }
            }

        }

        return path;
    }

    public static void computePath2(List<StationDto> allStations, StationDto start) throws Exception {
        // Nodes
        List<Node> nodes = new ArrayList<>();
        for (StationDto currStation : allStations) {
            nodes.add(new Node(currStation));
        }
        // Destinations
        for (Node currNode : nodes) {
            for (Integer neighbor : currNode.getStationDto().getNeighbors()) {
                currNode.addDestination(
                        nodes.stream()
                                .filter(node -> node.getStationDto().getKey().equals(neighbor))
                                .findAny()
                                .orElseThrow(),
                        1
                );
            }
        }
        //
        Graph graph = new Graph();
        for (Node currNode : nodes) {
            graph.addNode(currNode);
        }
        graph = calculateShortestPathFromSource(graph,
                nodes.stream()
                        .filter(node -> node.stationDto.getKey().equals(start.getKey()))
                        .findAny()
                        .orElseThrow());

        for (Node node : graph.getNodes()) {
            System.out.println(node.getStationDto().getKey()
                    + " = " + node.getShortestPath().toString());
        }
        //return null;
    }

    public static Graph calculateShortestPathFromSource(Graph graph, Node source) {

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
}
