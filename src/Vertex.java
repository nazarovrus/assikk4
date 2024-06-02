import java.util.HashMap;
import java.util.Map;

public class Vertex {
    private String data;
    private Map<Vertex, Double> adjacentVertices;

    public Vertex(String data) {
        this.data = data;
        this.adjacentVertices = new HashMap<>();
    }

    public void addAdjacentVertex(Vertex destination, double weight) {
        adjacentVertices.put(destination, weight);
    }

    public Map<Vertex, Double> getAdjacentVertices() {
        return adjacentVertices;
    }

    @Override
    public String toString() {
        return data;
    }
}
