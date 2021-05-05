package atl.stibride.model.dijkstra;

import atl.stibride.repo.dto.StationDto;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * A node in the graph, contains a metro station.
 */
public class Node {
    /* The metro station */
    private StationDto stationDto;
    /* The shortest path to this node */
    private List<Node> shortestPath;
    /* The distance of this node from the source */
    private Integer distance;
    /* Adjacent nodes to this one */
    Map<Node, Integer> adjacentNodes;

    /**
     * Constructor of a node.
     *
     * @param stationDto the metro station to made this node off of it.
     */
    public Node(StationDto stationDto) {
        this.stationDto = stationDto;
        this.shortestPath = new LinkedList<>();
        this.distance = Integer.MAX_VALUE;
        this.adjacentNodes = new HashMap<>();
    }

    /**
     * Adds a destination from this node to another one.
     *
     * @param destination the destination node.
     * @param distance    the distance between the two nodes.
     */
    public void addDestination(Node destination, int distance) {
        adjacentNodes.put(destination, distance);
    }

    /* getters and setters */

    /**
     * Gets the metro station of this node.
     *
     * @return the metro station of this node.
     */
    public StationDto getStationDto() {
        return stationDto;
    }

    /**
     * Sets the metro station of this node with the one in parameter.
     *
     * @param stationDto the new node.
     */
    public void setStationDto(StationDto stationDto) {
        this.stationDto = stationDto;
    }

    /**
     * Gets shortest path to this node.
     *
     * @return shortest path to this node.
     */
    public List<Node> getShortestPath() {
        return shortestPath;
    }

    /**
     * Sets the shortest path to this node.
     *
     * @param shortestPath the shortest path to this node.
     */
    public void setShortestPath(List<Node> shortestPath) {
        this.shortestPath = shortestPath;
    }

    /**
     * Gets the distance of the node from the source.
     *
     * @return the distance of the node from the source.
     */
    public Integer getDistance() {
        return distance;
    }

    /**
     * Sets the distance of the node from the source.
     *
     * @param distance the new distance of the node from the source.
     */
    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    /**
     * Gets every adjacent node to this one.
     *
     * @return every adjacent node to this one.
     */
    public Map<Node, Integer> getAdjacentNodes() {
        return adjacentNodes;
    }

    /**
     * Gets a String representation of this object.
     *
     * @return a String representation of this object.
     */
    @Override
    public String toString() {
        return "Node{" + stationDto.toString() + " }";
    }
}

