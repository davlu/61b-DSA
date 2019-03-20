package bearmaps;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.List;

public class KDTest {
    @Test
    public void sanityCheckTest(){
        Point p1 = new Point(2,3);
        Point p2 = new Point(4,2);
        Point p3 = new Point(4,2);
        Point p4 = new Point(4,5);
        Point p5 = new Point(3, 3);
        Point p6 = new Point(1, 5);
        Point p7 = new Point(4, 4);
        KDTree nn = new KDTree(List.of(p1, p2, p3,p4,p5,p6,p7));
        /**
        Point ret = nn.nearest(3.0, 4.0); // returns p2
        assertEquals(new Point(3.3, 4.4), ret);
        assertEquals(3.3, ret.getX(), 0.01); // evaluates to 3.3
        assertEquals(4.4, ret.getY(), 0.01); // evaluates to 4.4
         **/
    }
}
