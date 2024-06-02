import java.util.HashSet;
import java.util.Set;

public abstract class Search {
    protected WeightedGraph graph;
    protected Vertex startVertex;
    protected Set<Vertex> visitedVertices;

    public Search(WeightedGraph graph, Vertex startVertex) {
        this.graph = graph;
        this.startVertex = startVertex;
        this.visitedVertices = new HashSet<>();
    }

    public abstract void search();
}
