package exercise;

import java.util.ArrayList;
import java.util.List;

public class Node {
    Integer value;
    List<Node> neighbours;

    public Node(Integer value) {
        this.value = value;
        this.neighbours = new ArrayList<>();
    }

    public Integer getValue() {
        return value;
    }

    public Node setValue(Integer value) {
        this.value = value;
        return this;
    }

    public List<Node> getNeighbours() {
        return neighbours;
    }

    public Node setNeighbours(List<Node> neighbours) {
        this.neighbours = neighbours;
        return this;
    }
}
