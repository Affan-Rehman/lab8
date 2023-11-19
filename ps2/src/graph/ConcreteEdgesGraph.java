package graph;

import java.util.*;
import java.util.stream.Collectors;

/**
 * An implementation of Graph.
 * 
 * <p>PS2 instructions: you MUST use the provided rep.
 */
public class ConcreteEdgesGraph implements Graph<String> {
    
    private final Set<String> vertices = new HashSet<>();
    private final List<Edge> edges = new ArrayList<>();
    
    // Abstraction function:
    // Represents a weighted directed graph with string labels using edges.
    
    // Representation invariant:
    // The set of vertices is valid, and the list of edges corresponds to the actual edges in the graph.
    // Each edge's source and target vertices must be present in the vertices set.
    
    // Safety from rep exposure:
    // Both vertices and edges are private final fields.
    // They are not exposed directly, preventing rep exposure.
    
    // Constructor:
    public ConcreteEdgesGraph() {
        // No specific initialization needed for now
    }
    
    // checkRep method:
    private void checkRep() {
        for (Edge edge : edges) {
            assert vertices.contains(edge.getSource()) : "Edge source not in vertices set";
            assert vertices.contains(edge.getTarget()) : "Edge target not in vertices set";
        }
    }
    
    @Override
    public boolean add(String vertex) {
        checkRep();
        boolean added = vertices.add(vertex);
        checkRep();
        return added;
    }
    
    @Override
    public int set(String source, String target, int weight) {
        checkRep();
        Edge newEdge = new Edge(source, target, weight);
        int previousWeight = 0;
        
        // Check if the edge already exists
        int existingEdgeIndex = edges.indexOf(newEdge);
        if (existingEdgeIndex != -1) {
            previousWeight = edges.get(existingEdgeIndex).getWeight();
            edges.set(existingEdgeIndex, newEdge);
        } else {
            edges.add(newEdge);
        }
        
        checkRep();
        return previousWeight;
    }
    
    @Override
    public boolean remove(String vertex) {
        checkRep();
        boolean removed = vertices.remove(vertex);
        
        // Remove edges associated with the removed vertex
        edges.removeIf(edge -> edge.getSource().equals(vertex) || edge.getTarget().equals(vertex));
        
        checkRep();
        return removed;
    }
    
    @Override
    public Set<String> vertices() {
        checkRep();
        return new HashSet<>(vertices); // Return a copy to prevent modification outside the class
    }
    
    @Override
    public Map<String, Integer> sources(String target) {
        checkRep();
        return edges.stream()
                .filter(edge -> edge.getTarget().equals(target))
                .collect(Collectors.toMap(Edge::getSource, Edge::getWeight));
    }
    
    @Override
    public Map<String, Integer> targets(String source) {
        checkRep();
        return edges.stream()
                .filter(edge -> edge.getSource().equals(source))
                .collect(Collectors.toMap(Edge::getTarget, Edge::getWeight));
    }
    
    @Override
    public String toString() {
        checkRep();
        StringBuilder sb = new StringBuilder();
        sb.append("Vertices: ").append(vertices).append("\n");
        sb.append("Edges: ").append(edges).append("\n");
        return sb.toString();
    }
}

// Edge class:
class Edge {
    private final String source;
    private final String target;
    private final int weight;
    
    // Abstraction function:
    // Represents an edge in the weighted directed graph.
    
    // Representation invariant:
    // Source and target vertices must not be null.
    
    // Safety from rep exposure:
    // Fields are private and final. No mutators provided.
    
    // Constructor:
    public Edge(String source, String target, int weight) {
        this.source = source;
        this.target = target;
        this.weight = weight;
        checkRep();
    }
    
    // checkRep method:
    private void checkRep() {
        assert source != null : "Source vertex is null";
        assert target != null : "Target vertex is null";
    }
    
    // Methods:
    public String getSource() {
        return source;
    }
    
    public String getTarget() {
        return target;
    }
    
    public int getWeight() {
        return weight;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Edge edge = (Edge) obj;
        return source.equals(edge.source) && target.equals(edge.target);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(source, target);
    }
    
    @Override
    public String toString() {
        return "(" + source + " -> " + target + ", weight=" + weight + ")";
    }
}
