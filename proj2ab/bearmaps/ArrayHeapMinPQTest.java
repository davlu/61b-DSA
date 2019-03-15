package bearmaps;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.PriorityQueue;
import java.lang.Math;

public class ArrayHeapMinPQTest {
    @Test
    public void addRemoveGenericTest() {
        ArrayHeapMinPQ testPQ = new ArrayHeapMinPQ();
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
        assertTrue(testPQ.contains("Sapphire Crystal"));
        testPQ.removeSmallest();
        assertTrue(!testPQ.contains("Sapphire Crystal"));
        testPQ.removeSmallest();
        testPQ.removeSmallest();
        testPQ.removeSmallest();
        assertEquals("Tri Force", testPQ.removeSmallest());
        assertEquals(0, testPQ.size());

    }

    @Test
    public void sizeTest() {
        ArrayHeapMinPQ testPQ = new ArrayHeapMinPQ();
        for(int i =0; i< 342; i++){
            testPQ.add("Long" + i, 1);
        }
        assertEquals(342, testPQ.size());
    }

    @Test
    public void addRemoveTest(){
        ArrayHeapMinPQ testPQ = new ArrayHeapMinPQ();
        for(int i =0; i<1000;i ++){
            testPQ.add(i, i);
        }
        for(int i= 0; i<1000; i++){
            assertEquals(i, testPQ.removeSmallest());
        }
    }

    @Test
    public void changePriorityTest(){
        ArrayHeapMinPQ testPQ = new ArrayHeapMinPQ();
        testPQ.add("Long Sword", 350.0);
        assertTrue(testPQ.contains("Long Sword"));

        testPQ.add("Ruby Crystal", 400.0);
        assertEquals(2, testPQ.size());
        testPQ.changePriority("Long Sword", 400);
        assertEquals("Long Sword", testPQ.getSmallest());
        testPQ.changePriority("Long Sword", 450);
        assertEquals("Ruby Crystal", testPQ.getSmallest());
    }

    /** assumes everything works @Test
    public void hugeTest(){
        ArrayHeapMinPQ testPQ = new ArrayHeapMinPQ();
        NaiveMinPQ testNaivePQ = new NaiveMinPQ();
        for(int i = 0; i<1000000; i++){  if they have the same priority then this is a testing difficulty
            int move = (int) (Math.random()*5);
            if(move ==0){
                testPQ.add();
            }
            else if(move == 1){

            }
            else if(move == 2){

            }
            else if(move == 3){

            }
            else if(move == 4){

            }
        }
    } **/

    /** assumes everything works @Test
    public void timeTest(){
        ArrayHeapMinPQ testPQ = new ArrayHeapMinPQ();
        NaiveMinPQ testNaivePQ = new NaiveMinPQ();
        PriorityQueue javaPQ = new PriorityQueue();

        int startTime = (int) System.currentTimeMillis();
        for(int i =0; i < 10000000; i++){
            testPQ.add(i,3.0);
        }
        int difference = (int) System.currentTimeMillis()-startTime;
        System.out.println("Time taken for your heap implementation: " + difference);

        int startTime1 = (int) System.currentTimeMillis();
        for(int i= 0; i < 10000000; i++){
            testNaivePQ.add(i,3.0);
        }
        int difference1 = (int) System.currentTimeMillis() - startTime1;
        System.out.println("Time taken for Naive implementation: " + difference1);

        int startTime2 = (int) System.currentTimeMillis();
        for(int i= 0; i < 10000000; i++){
            javaPQ.add(i);
        }
        int difference2 = (int) System.currentTimeMillis() - startTime2;
        System.out.println("Time taken for JAVA implementation: " + difference2);
    }**/
}
