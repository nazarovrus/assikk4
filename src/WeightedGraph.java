import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class WeightedGraph {
    private Map<String, Vertex> vertices;

    public WeightedGraph() {
        vertices = new HashMap<>();
    }

    public void addVertex(String data) {
        vertices.put(data, new Vertex(data));
    }

    public Vertex getVertex(String data) {
        return vertices.get(data);
    }

    public void addEdge(String sourceData, String destData, double weight) {
        Vertex source = getVertex(sourceData);
        Vertex dest = getVertex(destData);
        if (source != null && dest != null) {
            source.addAdjacentVertex(dest, weight);
        }
    }

    public Collection<Vertex> getVertices() {
        return vertices.values();
    }
}

