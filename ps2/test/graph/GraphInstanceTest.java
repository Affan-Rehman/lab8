package graph;

import static org.junit.Assert.*;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import org.junit.Test;

/**
 * Tests for instance methods of Graph.
 * 
 * <p>PS2 instructions: you MUST NOT add constructors, fields, or non-@Test
 * methods to this class, or change the spec of {@link #emptyInstance()}.
 * Your tests MUST only obtain Graph instances by calling emptyInstance().
 * Your tests MUST NOT refer to specific concrete implementations.
 */
public abstract class GraphInstanceTest {
    
    // Testing strategy
    //   add(String vertex):
    //     - Test adding a single vertex.
    //     - Test adding multiple vertices.
    //     - Test adding duplicate vertices (should return false).
    //     - Test adding vertices with different labels.
    //
    //   set(String source, String target, int weight):
    //     - Test setting the weight for an existing edge.
    //     - Test setting the weight for a non-existing edge.
    //     - Test setting the weight to zero (should remove the edge).
    //     - Test setting the weight with one or both vertices not existing.
    //
    //   remove(String vertex):
    //     - Test removing an existing vertex.
    //     - Test removing a non-existing vertex.
    //     - Test removing a vertex with incoming or outgoing edges.
    //
    //   vertices():
    //     - Test getting vertices from an empty graph.
    //     - Test getting vertices from a graph with one or more vertices.
    //
    //   sources(String target):
    //     - Test getting sources for a target with incoming edges.
    //     - Test getting sources for a target with no incoming edges.
    //     - Test getting sources for a non-existing target.
    //
    //   targets(String source):
    //     - Test getting targets for a source with outgoing edges.
    //     - Test getting targets for a source with no outgoing edges.
    //     - Test getting targets for a non-existing source.

    /**
     * Overridden by implementation-specific test classes.
     * 
     * @return a new empty graph of the particular implementation being tested
     */
    public abstract Graph<String> emptyInstance();
    
    @Test(expected = AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    @Test
    public void testAddSingleVertex() {
        Graph<String> graph = emptyInstance();
        assertTrue(graph.add("A"));
        assertEquals(Set.of("A"), graph.vertices());
    }
    
    @Test
    public void testAddMultipleVertices() {
        Graph<String> graph = emptyInstance();
        assertTrue(graph.add("A"));
        assertTrue(graph.add("B"));
        assertEquals(Set.of("A", "B"), graph.vertices());
    }
    
    @Test
    public void testAddDuplicateVertices() {
        Graph<String> graph = emptyInstance();
        assertTrue(graph.add("A"));
        assertFalse(graph.add("A"));
        assertEquals(Set.of("A"), graph.vertices());
    }
    
    @Test
    public void testAddVerticesWithDifferentLabels() {
        Graph<String> graph = emptyInstance();
        assertTrue(graph.add("A"));
        assertTrue(graph.add("B"));
        assertFalse(graph.add("A")); // Duplicate label, different vertex
        assertEquals(Set.of("A", "B"), graph.vertices());
    }
    
    @Test
    public void testSetWeightForExistingEdge() {
        Graph<String> graph = emptyInstance();
        graph.add("A");
        graph.add("B");
        graph.set("A", "B", 5);
        assertEquals(5, graph.set("A", "B", 10));
    }

    @Test
    public void testSetWeightForNonExistingEdge() {
        Graph<String> graph = emptyInstance();
        graph.add("A");
        graph.add("B");
        assertEquals(0, graph.set("A", "B", 10));
    }

    @Test
    public void testSetWeightToZero() {
        Graph<String> graph = emptyInstance();
        graph.add("A");
        graph.add("B");
        graph.set("A", "B", 5);
        assertEquals(5, graph.set("A", "B", 0));
    }

    @Test
    public void testSetWeightWithNonExistingVertices() {
        Graph<String> graph = emptyInstance();
        assertEquals(0, graph.set("A", "B", 10));
    }

    @Test
    public void testRemoveExistingVertex() {
        Graph<String> graph = emptyInstance();
        graph.add("A");
        assertTrue(graph.remove("A"));
        assertEquals(Collections.emptySet(), graph.vertices());
    }

    @Test
    public void testRemoveNonExistingVertex() {
        Graph<String> graph = emptyInstance();
        assertFalse(graph.remove("A"));
        assertEquals(Collections.emptySet(), graph.vertices());
    }

    @Test
    public void testRemoveVertexWithEdges() {
        Graph<String> graph = emptyInstance();
        graph.add("A");
        graph.add("B");
        graph.set("A", "B", 5);
        assertTrue(graph.remove("A"));
        assertEquals(Collections.singleton("B"), graph.vertices());
    }

    @Test
    public void testVerticesFromEmptyGraph() {
        Graph<String> graph = emptyInstance();
        assertEquals(Collections.emptySet(), graph.vertices());
    }

    @Test
    public void testVerticesFromNonEmptyGraph() {
        Graph<String> graph = emptyInstance();
        graph.add("A");
        graph.add("B");
        assertEquals(Set.of("A", "B"), graph.vertices());
    }

    @Test
    public void testSourcesForTargetWithIncomingEdges() {
        Graph<String> graph = emptyInstance();
        graph.add("A");
        graph.add("B");
        graph.set("A", "B", 5);
        assertEquals(Map.of("A", 5), graph.sources("B"));
    }

    @Test
    public void testSourcesForTargetWithNoIncomingEdges() {
        Graph<String> graph = emptyInstance();
        graph.add("A");
        graph.add("B");
        assertEquals(Collections.emptyMap(), graph.sources("B"));
    }

    @Test
    public void testSourcesForNonExistingTarget() {
        Graph<String> graph = emptyInstance();
        assertEquals(Collections.emptyMap(), graph.sources("B"));
    }

    @Test
    public void testTargetsForSourceWithOutgoingEdges() {
        Graph<String> graph = emptyInstance();
        graph.add("A");
        graph.add("B");
        graph.set("A", "B", 5);
        assertEquals(Map.of("B", 5), graph.targets("A"));
    }

    @Test
    public void testTargetsForSourceWithNoOutgoingEdges() {
        Graph<String> graph = emptyInstance();
        graph.add("A");
        graph.add("B");
        assertEquals(Collections.emptyMap(), graph.targets("A"));
    }

    @Test
    public void testTargetsForNonExistingSource() {
        Graph<String> graph = emptyInstance();
        assertEquals(Collections.emptyMap(), graph.targets("A"));
    }
}
