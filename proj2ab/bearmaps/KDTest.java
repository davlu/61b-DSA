package bearmaps;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.ArrayList;

public class KDTest {
    @Test
    public void sanityCheckTest() {
        Point p1 = new Point(2, 3);
        Point p2 = new Point(4, 2);
        Point p3 = new Point(4, 2);
        Point p4 = new Point(4, 5);
        Point p5 = new Point(3, 3);
        Point p6 = new Point(1, 5);
        Point p7 = new Point(4, 4);
        KDTree nn = new KDTree(List.of(p1, p2, p3, p4, p5, p6, p7));
        /**
         Point ret = nn.nearest(3.0, 4.0); // returns p2
         assertEquals(new Point(3.3, 4.4), ret);
         assertEquals(3.3, ret.getX(), 0.01); // evaluates to 3.3
         assertEquals(4.4, ret.getY(), 0.01); // evaluates to 4.4
         **/
    }

    @Test
    public void testNearest() {
        Point p1 = new Point(2, 3);
        Point p2 = new Point(4, 2);
        Point p3 = new Point(4, 2);
        Point p4 = new Point(4, 5);
        Point p5 = new Point(3, 3);
        Point p6 = new Point(1, 5);
        Point p7 = new Point(4, 4);
        KDTree nn = new KDTree(List.of(p1, p2, p3, p4, p5, p6, p7));
        Point nearest = nn.nearest(0, 7);
        assertEquals(nearest, new Point(1, 5));
    }

    @Test
    public void hugeNearest() {
        List<Point> pointList = new ArrayList<>();
        List<Point> findPoints = new ArrayList<>();
        int rounds = 100000;
        for (int i = 0; i < rounds; i++) {                  //generate test values
            double randPointX = Math.random() * 1000;
            double randPointY = Math.random() * 1000;
            pointList.add(new Point(randPointX, randPointY));
        }
        for (int i = 0; i < 10000; i++) {
            double randPointX = Math.random() * 1000;
            double randPointY = Math.random() * 1000;
            findPoints.add(new Point(randPointX, randPointY));
        }
        NaivePointSet naive = new NaivePointSet(pointList);
        KDTree kdTree = new KDTree(pointList);
        for (Point p : findPoints) {
            assertEquals(naive.nearest(p.getX(), p.getY()), kdTree.nearest(p.getX(), p.getY()));
        }
    }

    @Test
    public void timeTest() {
        List<Point> pointList = new ArrayList<>();
        List<Point> findPoints = new ArrayList<>();
        int rounds = 100000;
        for (int i = 0; i < rounds; i++) {                  //generate test values
            double randPointX = Math.random() * 1000;
            double randPointY = Math.random() * 1000;
            pointList.add(new Point(randPointX, randPointY));
        }
        for (int i = 0; i < 10000; i++) {
            double randPointX = Math.random() * 1000;
            double randPointY = Math.random() * 1000;
            findPoints.add(new Point(randPointX, randPointY));
        }
        NaivePointSet naive = new NaivePointSet(pointList);
        KDTree kdTree = new KDTree(pointList);

        int startTime = (int) System.currentTimeMillis();
        for (int i = 0; i < findPoints.size(); i++) {
            naive.nearest(findPoints.get(i).getX(), findPoints.get(i).getY());
        }
        int difference = (int) System.currentTimeMillis() - startTime;
        System.out.println("Time taken for naive heap implementation: " + difference);

        int startTime1 = (int) System.currentTimeMillis();
        for (int i = 0; i < findPoints.size(); i++) {
            kdTree.nearest(findPoints.get(i).getX(), findPoints.get(i).getY());
        }
        int difference1 = (int) System.currentTimeMillis() - startTime1;
        System.out.println("Time taken for your heap implementation: " + difference1);
    }
}
