package atl.stibride.model;

import atl.stibride.dto.StationDto;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Graph {

    private Set<Node> nodes;

    public Graph() {
        this.nodes = new HashSet<>();
    }

    public void initialize(List<StationDto> allStations) {
        // Nodes
        for (StationDto currStation : allStations) {
            nodes.add(new Node(currStation));
        }
        // Destinations
        for (Node currNode : nodes) {
            for (Integer neighbor : currNode.getStationDto().getNeighbors()) {
                currNode.addDestination(getNode(neighbor), 1);
            }
        }
    }

    public void addNode(Node nodeA) {
        nodes.add(nodeA);
    }

    // getters and setters
    public Node getNode(Integer key) {
        return nodes.stream()
                .filter(node -> node.getStationDto().getKey().equals(key))
                .findAny()
                .orElseThrow();
    }

    public Set<Node> getNodes() {
        return nodes;
    }

    public void setNodes(Set<Node> nodes) {
        this.nodes = nodes;
    }
}
