package bearmaps.hw4;

import bearmaps.proj2ab.ArrayHeapMinPQ;
import bearmaps.proj2ab.DoubleMapPQ;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class AStarSolver2<Vertex> implements ShortestPathsSolver<Vertex> {
    private DoubleMapPQ<Vertex> fringe;
    private HashMap<Vertex, Double> distTo;
    private List<Vertex> solution;
    private HashMap<Vertex, Vertex> edgeTo;
    private HashSet<Vertex> visited;

    /**
     * Constructor which finds the solution, computing everything necessary for all other methods to
     * return their results in constant time. Note that timeout passed in is in seconds
     */
    public AStarSolver2(AStarGraph<Vertex> input, Vertex start, Vertex end, double timeout) {
        double startTime = System.currentTimeMillis();
        fringe = new DoubleMapPQ<>();
        distTo = new HashMap<>();
        solution = new ArrayList<>();
        visited = new HashSet<>();

        fringe.add(start, input.estimatedDistanceToGoal(start, end));
        distTo.put(start, 0.0);

        while (fringe.size() != 0) {
            if (fringe.getSmallest() == end) {
                Vertex currentVertex = fringe.removeSmallest();
                solution.add(currentVertex);
                solutionSize += edgeLengthCurrent(currentVertex);
                break;
            }
            Vertex currentVertex = fringe.removeSmallest();
            solution.add(currentVertex);
            visited.add(currentVertex);
            solutionSize += edgeLengthCurrent(currentVertex);
            numDequeues += 1;
            for (WeightedEdge<Vertex> edge : input.neighbors(currentVertex)) {
                    relax(input, edge, currentVertex, end);
                }
            }
    }

    private void relax(AStarGraph<Vertex> input, WeightedEdge<Vertex> edge, Vertex current, Vertex end) {
        Vertex q = edge.to(); //destination vertex from source vertex
        Vertex p = edge.from();
        double w = edge.weight();
        double heuristic = input.estimatedDistanceToGoal(q, end);
        double newDist = distTo.get(p) + w;
        if(!distTo.containsKey(q) || newDist < distTo.get(q)){
            distTo.put(q, newDist);
            edgeTo.put(q,p);
            if (fringe.contains(q)) {
                fringe.changePriority(q, newDist + heuristic);
            } else {
                fringe.add(q, newDist + heuristic);
            }
        }
    }
}
