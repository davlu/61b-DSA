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
            newNode.orientation = !(newNode.orientation);
        }
    }

    private static class Node implements Comparable<Point> {
        private Point p;
        private boolean orientation = true; //false is vertical y, true is horizontal x
        Node greater;
        Node lesser;

        Node(Point point, Node greater, Node lesser) {
            this.p = point;
            this.greater = greater;
            this.lesser = lesser;
        }

        public int compareTo(Point p1) {
            if (this.orientation) {
                return (int) (this.p.getX() - p1.getX());
            }
            return (int) (this.p.getY() - p1.getY());
        }

        public void add(Node n) {
            if (orientation) {
                if ((this.p.getX() == n.p.getX()) && (this.p.getY() == n.p.getY())) {
                    return;
                } else if (this.p.getX() > n.p.getX()) {
                    if (this.lesser != null) {
                        n.orientation = !n.orientation;
                        this.lesser.add(n);
                    } else {
                        this.lesser = n;
                    }
                } else {
                    if (this.greater != null) {
                        n.orientation = !n.orientation;
                        this.greater.add(n);
                    } else {
                        this.greater = n;
                    }
                }
            } else {
                if ((this.p.getX() == n.p.getX()) && (this.p.getY() == n.p.getY())) {
                    return;
                } else if (this.p.getY() > n.p.getY()) {
                    if (this.lesser != null) {
                        n.orientation = !n.orientation;
                        this.lesser.add(n);
                    } else {
                        this.lesser = n;
                    }
                } else {
                    if (this.greater != null) {
                        n.orientation = !n.orientation;
                        this.greater.add(n);
                    } else {
                        this.greater = n;
                    }
                }
            }
        }
    }

    public Point nearest(double x, double y) {
        Point pointOfInterest = new Point(x, y);
        return helperFunction(pointOfInterest, this.root, this.root).p;
    }

    private Node helperFunction(Point pointOfInterest, Node currentNode, Node bestNode) {
        if (currentNode == null) {
            return bestNode;
        }
        Double newDistance = Point.distance(pointOfInterest, currentNode.p);
        Double shortestD = Point.distance(pointOfInterest, bestNode.p);
        if (newDistance < shortestD) {
            bestNode = currentNode;
        }
        if (currentNode.compareTo(pointOfInterest) < 0) {
            Node best = helperFunction(pointOfInterest, currentNode.greater, bestNode);
            if (currentNode.lesser != null
                    && bestPossibleDistance(pointOfInterest, currentNode.lesser)
                    < Point.distance(best.p, pointOfInterest)) {
                best = helperFunction(pointOfInterest, currentNode.lesser, best);
            }
            return best;
        } else {
            Node best = helperFunction(pointOfInterest, currentNode.lesser, bestNode);
            if (currentNode.greater != null
                    && bestPossibleDistance(pointOfInterest, currentNode.greater)
                    < Point.distance(best.p, pointOfInterest)) {
                best = helperFunction(pointOfInterest, currentNode.greater, best);
            }
            return best;
        }
    }

    private double bestPossibleDistance(Point pointOfInterest, Node otherPoint) {
        if (!otherPoint.orientation) {
            return Math.pow(Math.abs(pointOfInterest.getX() - otherPoint.p.getX()), 2);
        }
        return Math.pow(Math.abs(pointOfInterest.getY() - otherPoint.p.getY()), 2);
    }
}
