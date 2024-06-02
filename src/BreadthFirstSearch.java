import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstSearch extends Search {
    public BreadthFirstSearch(WeightedGraph graph, Vertex startVertex) {
        super(graph, startVertex);
    }

    @Override
    public void search() {
        Queue<Vertex> queue = new LinkedList<>();
        queue.add(startVertex);
        visitedVertices.add(startVertex);
        System.out.print(startVertex + " ");

        while (!queue.isEmpty()) {
            Vertex current = queue.poll();
            for (Vertex neighbor : current.getAdjacentVertices().keySet()) {
                if (!visitedVertices.contains(neighbor)) {
                    queue.add(neighbor);
                    visitedVertices.add(neighbor);
                    System.out.print(neighbor + " ");
                }
            }
        }
        System.out.println();
    }
}

