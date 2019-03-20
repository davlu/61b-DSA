package bearmaps;
import java.util.List;

public class KDTree {
    private Node root;
    private List<Point> listPoints;
    public KDTree(List<Point> points){
        this.listPoints = points;
        Node firstNode = new Node(listPoints.get(0), null, null);
        this.root = firstNode;
        for(int i =1; i<listPoints.size(); i++){
            Node newNode = new Node(listPoints.get(i), null, null );
            this.root.add(newNode);
            newNode.orientation = !(newNode.orientation);
        }
    }
    private static class Node{
        private Point p;
        private boolean orientation = true; //false is vertical y, true is horizontal x
        Node greater;
        Node lesser;
        public Node(Point point, Node greater, Node lesser){
            this.p = point;
            this.greater = greater;
            this.lesser = lesser;
        }
        public void add(Node n){
            if(orientation == true){
                if((this.p.getX() == n.p.getX()) && (this.p.getY() == n.p.getY())){
                    return;
                }
                else if(this.p.getX() > n.p.getX()){
                    if(this.lesser != null){
                        n.orientation = !n.orientation;
                        this.lesser.add(n);
                    }
                    else{
                        this.lesser = n;
                    }
                }
                else{
                    if(this.greater != null){
                        n.orientation = !n.orientation;
                        this.greater.add(n);
                    }
                    else{
                        this.greater = n;
                    }
                }
            }
            else{
                if((this.p.getX() == n.p.getX()) && (this.p.getY() == n.p.getY())){
                    return;
                }
                else if(this.p.getY() > n.p.getY()){
                    if(this.lesser != null){
                        n.orientation = !n.orientation;
                        this.lesser.add(n);
                    }
                    else{
                        this.lesser = n;
                    }
                }
                else{
                    if(this.greater != null){
                        n.orientation = !n.orientation;
                        this.greater.add(n);
                    }
                    else{
                        this.greater = n;
                    }
                }
            }
        }
    }
    public Point nearest(double x, double y){
        Point pointOfInterest = new Point(x,y);
        return helperFunction(pointOfInterest, this.root, 100000.0);
    }

    private Point helperFunction(Point pointOfInterest, Node root, Double shortestDist){
        if(root.lesser == null && root.greater == null){
            return root.p;
        }
        Double shorter = shortestDist;
        if(Point.distance(root.p, pointOfInterest) < shortestDist){
            shorter = Point.distance(root.p, pointOfInterest);
        }
        if(root.lesser.p!=null && distX(pointOfInterest, root.lesser.p) < distX(pointOfInterest, root.greater.p)){
            return helperFunction(pointOfInterest, root.lesser, shorter);
        }
    }
    private double bestPossibleDistance(Point pointOfInterest, Node otherPoint){ //looks on the infinite line for best
        if(otherPoint.orientation == false){
            return Math.abs(pointOfInterest.getX()-otherPoint.p.getX());
        }
        return Math.abs(pointOfInterest.getY()-otherPoint.p.getY());
    }

    private double distX(Point pointOfInterest, Point otherPoint){
        return Math.abs(pointOfInterest.getX() - otherPoint.getX());
    }
    private double distY(Point pointOfInterest, Point otherPoint){
        return Math.abs(pointOfInterest.getY() - otherPoint.getY());
    }
}
