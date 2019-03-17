package bearmaps;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.lang.Math;

public class ArrayHeapMinPQTest<T, PriorityNode> {
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
        for(int i =0; i<1000000;i ++){
            testPQ.add(i, i);
        }
        for(int i= 0; i<1000000; i++){
            assertEquals(i, testPQ.removeSmallest());
        }
    }

    @Test
    public void hugeTest(){
        ArrayHeapMinPQ testPQ = new ArrayHeapMinPQ();
        NaiveMinPQ testNaivePQ = new NaiveMinPQ();
        ArrayList<Integer> addItemList = new ArrayList<>();
        ArrayList<Double> addPriorityList = new ArrayList<>();
        ArrayList<Integer> knownValues = new ArrayList<>();
        for(int i =0 ; i<100; i++){  /** adding a shit ton of #s**/
            int randomItem = (int) (Math.random()*5);
            double randomPriority = Math.random()*10;
            if(!testPQ.contains(randomItem)){
                testPQ.add(randomItem, randomPriority);
                testNaivePQ.add(randomItem, randomPriority);
                knownValues.add(randomItem);
                addItemList.add(randomItem);
                addPriorityList.add(randomPriority);
            }
        }

        ArrayList<Integer> targetItem = new ArrayList<>();
        ArrayList<Double> targetPriority = new ArrayList<>();
        for(int i = 0; i<10; i++) { //randomly getSmallest + changePriority
            int randomItem = knownValues.get((int) (Math.random() * knownValues.size()));
            double randomPriority = Math.random() * 10;
            if (testPQ.contains(randomItem)) {
                testPQ.changePriority(randomItem, randomPriority);
                testNaivePQ.changePriority(randomItem, randomPriority);
                targetItem.add(randomItem);
                targetPriority.add(randomPriority);
            }
        }
        T smallestPQ = (T) testPQ.getSmallest();
        T smallestNaivePQ = (T) testNaivePQ.getSmallest();
        System.out.println(smallestPQ);
        System.out.println(smallestNaivePQ);
        for(int i = 0; i<testPQ.size()-1;i++){
            Double testPQRemovedPriority = testPQ.returnItemPriority(testPQ.getSmallest());
            T testPQRemoved = (T) testPQ.removeSmallest();
            PriorityNode naivePQRemoved =  (PriorityNode) testNaivePQ.getSmallest();
            if(!testPQRemoved.equals(naivePQRemoved)){
                assertEquals(testPQRemovedPriority, testNaivePQ.getSmallestPriority(), 0.01);
            }
            assertEquals(testPQRemoved, testNaivePQ.removeSmallest());
        }
    }


    @Test
    public void changePriorityTest(){
        ArrayHeapMinPQ testPQ = new ArrayHeapMinPQ();
        NaiveMinPQ testNaivePQ = new NaiveMinPQ();
        testPQ.add(0, 1.9691);
        testPQ.add(2,0.2059);
        testPQ.add(4, 3.5868817);
        testPQ.add(1, 2.6041056);
        testPQ.add(3, 0.516);
        testNaivePQ.add(0, 1.9691);
        testNaivePQ.add(2,0.2059);
        testNaivePQ.add(4, 3.5868817);
        testNaivePQ.add(1, 2.6041056);
        testNaivePQ.add(3, 0.516);

        testPQ.changePriority(0,4.4036737519);
        testPQ.changePriority(2, 7.62839);
        testPQ.changePriority(2,8.8811505);
        testPQ.changePriority(1,2.4136770649);
        testPQ.changePriority(1, 8.814589);
        testPQ.changePriority(3, 6.8848364);
        testPQ.changePriority(2, 4.979542723);
        testPQ.changePriority(3, 4.5923866);
        testPQ.changePriority(4, 9.7865739);
        testPQ.changePriority(4, 6.626749327);
        testNaivePQ.add(0,4.4036737519);
        testNaivePQ.add(2, 7.62839);
        testNaivePQ.add(2,8.8811505);
        testNaivePQ.add(1,2.4136770649);
        testNaivePQ.add(1, 8.814589);
        testNaivePQ.add(3, 6.8848364);
        testNaivePQ.add(2, 4.979542723);
        testNaivePQ.add(3, 4.5923866);
        testNaivePQ.add(4, 9.7865739);
        testNaivePQ.add(4, 6.626749327);

    }
    @Test
    public void timeTest(){
        ArrayHeapMinPQ testPQ = new ArrayHeapMinPQ();
        NaiveMinPQ testNaivePQ = new NaiveMinPQ();
        PriorityQueue javaPQ = new PriorityQueue();
        int million = 1000000;

        int startTime = (int) System.currentTimeMillis();
        for(int i =0; i < million; i++){
            testPQ.add(i,3.0);
        }
        int difference = (int) System.currentTimeMillis()-startTime;
        System.out.println("Time taken for your heap implementation: " + difference);

        int startTime1 = (int) System.currentTimeMillis();
        for(int i= 0; i < million; i++){
            testNaivePQ.add(i,3.0);
        }
        int difference1 = (int) System.currentTimeMillis() - startTime1;
        System.out.println("Time taken for Naive implementation: " + difference1);

        int startTime2 = (int) System.currentTimeMillis();
        System.out.println(startTime2);
        for(int i= 0; i < million; i++){
            javaPQ.add(i);
        }
        System.out.println((int) System.currentTimeMillis());
        int difference2 = (int) System.currentTimeMillis() - startTime2;
        System.out.println("Time taken for JAVA implementation: " + difference2);
    }
}
