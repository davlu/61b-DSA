package bearmaps;
import java.util.List;

public class NaivePointSet implements PointSet {
    private List<Point> listPoints;
    public NaivePointSet(List<Point> points){
        listPoints = points;
    }
    public Point nearest(double x, double y){
        Point pointOfInterest = new Point(x,y);
        Point smallest = this.listPoints.get(0);
        Double smallestDist = Integer.MAX_VALUE * 1.0;
        for(Point p : this.listPoints){
            if(Point.distance(pointOfInterest,p) < smallestDist){
                smallest = p;
                smallestDist = Point.distance(pointOfInterest, p);
            }
        }
        return smallest;
    }
}
