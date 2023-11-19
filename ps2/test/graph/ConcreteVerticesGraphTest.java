package graph;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests for ConcreteVerticesGraph.
 * 
 * This class runs the GraphInstanceTest tests against ConcreteVerticesGraph, as
 * well as tests for that particular implementation.
 * 
 * Tests against the Graph spec should be in GraphInstanceTest.
 */
public class ConcreteVerticesGraphTest extends GraphInstanceTest {
    
    @Override
    public Graph<String> emptyInstance() {
        return new ConcreteVerticesGraph();
    }
    
    // Tests for ConcreteVerticesGraph.toString()
    @Test
    public void testToStringEmptyGraph() {
        Graph<String> graph = emptyInstance();
        assertEquals("Vertices: []\n", graph.toString());
    }
    
    @Test
    public void testToStringNonEmptyGraph() {
        Graph<String> graph = emptyInstance();
        graph.add("A");
        graph.add("B");
        assertEquals("Vertices: [A, B]\n", graph.toString());
    }
    
    // Additional tests for ConcreteVerticesGraph methods can be added here
}
