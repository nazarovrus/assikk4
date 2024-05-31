import java.util.*;

public class Main {
    public static class Vertex {
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

    public static class WeightedGraph {
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

    public abstract static class Search {
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

    public static class BreadthFirstSearch extends Search {
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

    public static class DijkstraSearch extends Search {
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

    public static void main(String[] args) {
        WeightedGraph graph = new WeightedGraph();
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addVertex("E");
        graph.addEdge("A", "B", 1);
        graph.addEdge("A", "C", 3);
        graph.addEdge("B", "C", 1);
        graph.addEdge("B", "D", 6);
        graph.addEdge("C", "D", 2);
        graph.addEdge("D", "E", 1);

        System.out.println("BFS:");
        BreadthFirstSearch bfs = new BreadthFirstSearch(graph, graph.getVertex("A"));
        bfs.search();

        System.out.println("Dijkstra:");
        DijkstraSearch dijkstra = new DijkstraSearch(graph, graph.getVertex("A"));
        dijkstra.search();
    }
}
