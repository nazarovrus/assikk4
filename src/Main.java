import java.util.*;

public class Main {
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
