package bearmaps;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class NaiveMinPQTest {
    @Test
    public void addRemoveGenericTest(){
        NaiveMinPQ testPQ = new NaiveMinPQ();
        testPQ.add("Long Sword", 350.0);
        assertTrue(testPQ.contains("Long Sword"));

        testPQ.add("Ruby Crystal", 400.0);
        assertEquals(2, testPQ.size());
        assertEquals("Long Sword", testPQ.getSmallest());

        testPQ.add("Phage", 1250.0);
        testPQ.add("Sapphire Crystal", 350.0);
        assertEquals("Long Sword", testPQ.removeSmallest());

        testPQ.add("Sheen", 1050.0);
        testPQ.add("Tri Force", 3733.0);
        testPQ.removeSmallest();
        testPQ.removeSmallest();
        testPQ.removeSmallest();
        testPQ.removeSmallest();
        assertEquals("Tri Force", testPQ.removeSmallest());
        assertEquals(0, testPQ.size());

    }

    @Test
    public void samePriorityTest(){
        NaiveMinPQ testPQ = new NaiveMinPQ();
        testPQ.add("Long Sword", 350.0);
        testPQ.removeSmallest();
    }

    @Test
    public void changePriorityTest(){
        NaiveMinPQ testPQ = new NaiveMinPQ();
        testPQ.add("Long Sword", 350.0);
        assertTrue(testPQ.contains("Long Sword"));

        testPQ.add("Ruby Crystal", 400.0);
        assertEquals(2, testPQ.size());
        testPQ.changePriority("Long Sword", 400);
        assertEquals("Long Sword", testPQ.getSmallest());
        testPQ.changePriority("Long Sword", 450);
        assertEquals("Ruby Crystal", testPQ.getSmallest());
    }
}
