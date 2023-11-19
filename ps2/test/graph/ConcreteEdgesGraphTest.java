package graph;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests for ConcreteEdgesGraph.
 * 
 * This class runs the GraphInstanceTest tests against ConcreteEdgesGraph, as
 * well as tests for that particular implementation.
 * 
 * Tests against the Graph spec should be in GraphInstanceTest.
 */
public class ConcreteEdgesGraphTest extends GraphInstanceTest {
    
    @Override
    public Graph<String> emptyInstance() {
        return new ConcreteEdgesGraph();
    }
    
    // Tests for ConcreteEdgesGraph.toString()
    @Test
    public void testToStringEmptyGraph() {
        Graph<String> graph = emptyInstance();
        assertEquals("Vertices: [], Edges: []\n", graph.toString());
    }
    
    @Test
    public void testToStringNonEmptyGraph() {
        Graph<String> graph = empty
