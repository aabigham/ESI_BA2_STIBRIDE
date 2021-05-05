package atl.stibride.model.dijkstra;

import atl.stibride.repo.dto.StationDto;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * This class represents the metro station map.
 */
public class Graph {

    /**
     * The nodes of the graph.
     */
    private Set<Node> nodes;

    /**
     * Constructor of graph.
     */
    public Graph() {
        this.nodes = new HashSet<>();
    }

    /**
     * Initializes the graph and every nodes inside.
     * Also add the distance between each node to each other.
     *
     * @param allStations all of the stations to make the graph off of it.
     */
    public void initialize(List<StationDto> allStations) {
        // Adding nodes to graph
        for (StationDto currStation : allStations) {
            nodes.add(new Node(currStation));
        }
        // Destinations according to stations neighbors
        for (Node currNode : nodes) {
            for (Integer neighbor : currNode.getStationDto().getNeighbors()) {
                currNode.addDestination(getNode(neighbor), 1);
            }
        }
    }

    /* Getters and setters */

    /**
     * Gets a node in the graph according to its key in parameter.
     *
     * @param key the key of the node we want to find.
     * @return the node corresponding to the key in parameter.
     */
    public Node getNode(Integer key) {
        return nodes.stream()
                .filter(node -> node.getStationDto().getKey().equals(key))
                .findAny()
                .orElseThrow();
    }
}
