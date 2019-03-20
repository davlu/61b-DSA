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
        private static boolean orientation; //false is horizontal x, true is vertical y
        Node greater;
        Node lesser;
        public Node(Point point, Node greater, Node lesser){
            this.p = point;
            this.greater = greater;
            this.lesser = lesser;
        }
        public void add(Node n){
            if(orientation == false){
                if(this.p.getX() > n.p.getX()){
                    if(this.lesser != null){
                        this.lesser.add(n);
                    }
                    else{
                        this.lesser = n;
                    }
                }
                else{
                    if(this.greater != null){
                        this.greater.add(n);
                    }
                    else{
                        this.greater = n;
                    }
                }
            }
            else{
                if(this.p.getY() > n.p.getY()){
                    if(this.lesser != null){
                        this.lesser.add(n);
                    }
                    else{
                        this.lesser = n;
                    }
                }
                else{
                    if(this.greater != null){
                        this.greater.add(n);
                    }
                    else{
                        this.greater = n;
                    }
                }
            }
        }
    }
    /**
    public Point nearest(double x, double y){
    }**/
}
