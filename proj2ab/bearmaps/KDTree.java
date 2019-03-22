package bearmaps;

import java.util.List;

public class KDTree {
    private Node root;
    private List<Point> listPoints;

    public KDTree(List<Point> points) {
        this.listPoints = points;
        Node firstNode = new Node(listPoints.get(0), null, null);
        this.root = firstNode;
        for (int i = 1; i < listPoints.size(); i++) {
            Node newNode = new Node(listPoints.get(i), null, null);
            this.root.add(newNode);
        }
    }

    private static class Node implements Comparable<Point> {
        private Point p;
        private boolean orientation = true; //false splits horizontally true splits vertically
        Node greater;
        Node lesser;

        Node(Point point, Node greater, Node lesser) {
            this.p = point;
            this.greater = greater;
            this.lesser = lesser;
        }

        public int compareTo(Point p1) {
            //if our node is vertically split(true), check for left/right X
            if (this.orientation) {
                return Double.compare(this.p.getX(), p1.getX());
                //compare our point TO p1-  X
            }
            return Double.compare(this.p.getY(), p1.getY());
            //compare our point TO p1- Y
        }

        public void add(Node n) {
            //if we are deciding among the vertical split(left right - X)
            if (orientation) {
                if ((this.p.getX() == n.p.getX()) && (this.p.getY() == n.p.getY())) {
                    return;
                } else if (this.p.getX() > n.p.getX()) {
                    if (this.lesser != null) { //if there is already an existing node
                        n.orientation = !n.orientation;
                        this.lesser.add(n);
                    } else {
                        n.orientation = !n.orientation;
                        this.lesser = n;
                    }
                } else { //current node has smaller X than node that we are adding
                    if (this.greater != null) {
                        n.orientation = !n.orientation;  //recursively add onto
                        this.greater.add(n);             //the 'greater' node
                    } else {
                        n.orientation = !n.orientation;
                        this.greater = n;
                    }
                }
            } else {  //we are deciding among the horizontal split(up down - Y)
                if ((this.p.getX() == n.p.getX()) && (this.p.getY() == n.p.getY())) {
                    return;
                } else if (this.p.getY() > n.p.getY()) {
                    if (this.lesser != null) {
                        n.orientation = !n.orientation;
                        this.lesser.add(n);
                    } else {
                        n.orientation = !n.orientation;
                        this.lesser = n;
                    }
                } else {   //if our Y is less than added node's Y
                    if (this.greater != null) {
                        n.orientation = !n.orientation;
                        this.greater.add(n);
                    } else {
                        n.orientation = !n.orientation;
                        this.greater = n;
                    }
                }
            }
        }
    }

    public Point nearest(double x, double y) {
        Point pointOfInterest = new Point(x, y); //make point of interest
        return helperFunction(pointOfInterest, this.root, this.root).p;
    }

    private Node helperFunction(Point pointOfInterest, Node currentNode, Node bestNode) {
        if (currentNode == null) { //currentNode is node we are inspecting.
            return bestNode;
        }
        Node goodSide = null;
        Node badSide = null;
        Double newDistance = Point.distance(pointOfInterest, currentNode.p);
        Double shortestD = Point.distance(pointOfInterest, bestNode.p);
        if (newDistance < shortestD) {
            bestNode = currentNode;
        }
        if (currentNode.compareTo(pointOfInterest) < 0) {
            //if our inspected node is LESS than POI
            goodSide = currentNode.greater;
            badSide = currentNode.lesser;
        } else { //if our inspected node is GREATER than POI
            badSide = currentNode.greater;
            goodSide = currentNode.lesser;
        }
        bestNode = helperFunction(pointOfInterest, goodSide, bestNode);
        if (bestPossibleDistance(pointOfInterest, currentNode)
                < Point.distance(pointOfInterest, bestNode.p)) {
            bestNode = helperFunction(pointOfInterest, badSide, bestNode);
        }
        return bestNode;
    }

    private double bestPossibleDistance(Point pointOfInterest, Node otherPoint) {
        if (otherPoint.orientation) {
            //if other point orientation splits horizontally(looking at delta Y
            return Math.pow(pointOfInterest.getX() - otherPoint.p.getX(), 2);
        }
        return Math.pow(pointOfInterest.getY() - otherPoint.p.getY(), 2);
        //if other point splits vertically
    }
}
