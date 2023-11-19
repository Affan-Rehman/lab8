package graph;

import java.util.*;
import java.util.stream.Collectors;

/**
 * An implementation of Graph.
 * 
 * <p>PS2 instructions: you MUST use the provided rep.
 */
public class ConcreteVerticesGraph implements Graph<String> {
    
    private final List<Vertex> vertices = new ArrayList<>();
    
    // Abstraction function:
    // Represents a weighted directed graph with string labels using vertices.
    
    // Representation invariant:
    // The list of vertices is valid, and each vertex has distinct labels.
    
    // Safety from rep exposure:
    // The vertices list is private and final. It is not exposed directly, preventing rep exposure.
    
    // Constructor:
    public ConcreteVerticesGraph() {
        // No specific initialization needed for now
    }
    
    // checkRep method:
    private void checkRep() {
        Set<String> labels = new HashSet<>();
        for (Vertex vertex : vertices) {
            assert labels.add(vertex.getLabel()) : "Duplicate vertex label";
        }
    }
    
    @Override
    public boolean add(String vertex) {
        checkRep();
        boolean added = vertices.add(new Vertex(vertex));
        checkRep();
        return added;
    }
    
    @Override
    public int set(String source, String target, int weight) {
        checkRep();
        // Implementation of the set method for ConcreteVerticesGraph.
        // This method is specific to ConcreteVerticesGraph and handles the addition or removal of vertices.
        int previousWeight = 0;
        
        // Check if the source and target vertices exist
        Vertex sourceVertex = new Vertex(source);
        Vertex targetVertex = new Vertex(target);
        
        int sourceIndex = vertices.indexOf(sourceVertex);
        int targetIndex = vertices.indexOf(targetVertex);
        
        if (sourceIndex != -1 && targetIndex != -1) {
            // Vertices exist, update the weight
            previousWeight = sourceVertex.getOutgoingEdges().getOrDefault(target, 0);
            sourceVertex.getOutgoingEdges().put(target, weight);
        } else if (weight != 0) {
            // Vertices do not exist, add them along with the edge
            vertices.add(sourceVertex);
            vertices.add(targetVertex);
            sourceVertex.getOutgoingEdges().put(target, weight);
        }
        
        checkRep();
        return previousWeight;
    }
    
    @Override
    public boolean remove(String vertex) {
        checkRep();
        Vertex removedVertex = new Vertex(vertex);
        boolean removed = vertices.remove(removedVertex);
        
        // Remove edges associated with the removed vertex
        vertices.forEach(v -> v.getOutgoingEdges().remove(vertex));
        
        checkRep();
        return removed;
    }
    
    @Override
    public Set<String> vertices() {
        checkRep();
        return vertices.stream().map(Vertex::getLabel).collect(Collectors.toSet());
    }
    
    @Override
    public Map<String, Integer> sources(String target) {
        checkRep();
        Map<String, Integer> sources = new HashMap<>();
        
        // Collect sources for the target vertex
        vertices.forEach(v -> {
            int weight = v.getOutgoingEdges().getOrDefault(target, 0);
            if (weight != 0) {
                sources.put(v.getLabel(), weight);
            }
        });
        
        return sources;
    }
    
    @Override
    public Map<String, Integer> targets(String source) {
        checkRep();
        // Implementation of targets method for ConcreteVerticesGraph.
        // This method is specific to ConcreteVerticesGraph and collects the targets for a given source vertex.
        Map<String, Integer> targets = new HashMap<>();
        
        Vertex sourceVertex = new Vertex(source);
        int sourceIndex = vertices.indexOf(sourceVertex);
        
        if (sourceIndex != -1) {
            // Source vertex exists, collect targets
            targets = vertices.get(sourceIndex).getOutgoingEdges();
        }
        
        return targets;
    }
    
    @Override
    public String toString() {
        checkRep();
        StringBuilder sb = new StringBuilder();
        sb.append("Vertices: ").append(vertices).append("\n");
        return sb.toString();
    }
}

// Vertex class:
class Vertex {
    private final String label;
    private final Map<String, Integer> outgoingEdges;
    
    // Abstraction function:
    // Represents a vertex in the weighted directed graph.
    
    // Representation invariant:
    // Label must not be null.
    
    // Safety from rep exposure:
    // The label and outgoingEdges are private and final. No mutators provided.
    
    // Constructor:
    public Vertex(String label) {
        this.label = label;
        this.outgoingEdges = new HashMap<>();
        checkRep();
    }
    
    // checkRep method:
    private void checkRep() {
        assert label != null : "Vertex label is null";
    }
    
    // Methods:
    public String getLabel() {
        return label;
    }
    
    public Map<String, Integer> getOutgoingEdges() {
        return new HashMap<>(outgoingEdges); // Return a copy to prevent modification outside the class
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Vertex vertex = (Vertex) obj;
        return label.equals(vertex.label);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(label);
    }
    
    @Override
    public String toString() {
        return label;
    }
}
