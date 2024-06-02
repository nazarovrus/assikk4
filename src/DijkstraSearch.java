import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class DijkstraSearch extends Search {
    private Map<Vertex, Double> distances;
    private Map<Vertex, Vertex> predecessors;

    public DijkstraSearch(WeightedGraph graph, Vertex startVertex) {
        super(graph, startVertex);
        distances = new HashMap<>();
        predecessors = new HashMap<>();
        for (Vertex v : graph.getVertices()) {
            distances.put(v, Double.MAX_VALUE);
        }
        distances.put(startVertex, 0.0);
    }

    @Override
    public void search() {
        PriorityQueue<Vertex> priorityQueue = new PriorityQueue<>(Comparator.comparing(distances::get));
        priorityQueue.add(startVertex);

        while (!priorityQueue.isEmpty()) {
            Vertex current = priorityQueue.poll();
            visitedVertices.add(current);

            for (Map.Entry<Vertex, Double> entry : current.getAdjacentVertices().entrySet()) {
                Vertex neighbor = entry.getKey();
                double edgeWeight = entry.getValue();
                if (!visitedVertices.contains(neighbor)) {
                    double newDist = distances.get(current) + edgeWeight;
                    if (newDist < distances.get(neighbor)) {
                        distances.put(neighbor, newDist);
                        predecessors.put(neighbor, current);
                        priorityQueue.remove(neighbor);
                        priorityQueue.add(neighbor);
                    }
                }
            }
        }

        for (Vertex v : distances.keySet()) {
            System.out.println("Distance from " + startVertex + " to " + v + " is " + distances.get(v));
        }
    }
}

