package exercise.connectedComponents;

import java.util.ArrayList;
import java.util.List;

public class Vertex {
    Integer value;
    List<Vertex> neighbours;

    public Vertex(Integer value) {
        this.value = value;
        this.neighbours = new ArrayList<>();
    }

    public Integer getValue() {
        return value;
    }

    public Vertex setValue(Integer value) {
        this.value = value;
        return this;
    }

    public List<Vertex> getNeighbours() {
        return neighbours;
    }

    public Vertex setNeighbours(List<Vertex> neighbours) {
        this.neighbours = neighbours;
        return this;
    }
}
