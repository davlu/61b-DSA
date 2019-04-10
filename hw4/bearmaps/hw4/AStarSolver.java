package bearmaps.hw4;

import bearmaps.proj2ab.DoubleMapPQ;

import java.util.List;
import java.util.LinkedList;
import java.util.HashMap;

public class AStarSolver<Vertex> implements ShortestPathsSolver<Vertex> {

    private int result;
    private int numDequeues;
    private double timeSeconds;
    private double solutionSize;
    private DoubleMapPQ<Vertex> fringe;
    private HashMap<Vertex, Double> distTo;
    private List<Vertex> solution;
    private HashMap<Vertex, WeightedEdge<Vertex>> edgeTo;

    /**
     * Constructor which finds the solution, computing everything necessary for all other methods to
     * return their results in constant time. Note that timeout passed in is in seconds
     */
    public AStarSolver(AStarGraph<Vertex> input, Vertex start, Vertex end, double timeout) {
        double startTime = System.currentTimeMillis();
        fringe = new DoubleMapPQ<>();
        distTo = new HashMap<>();
        solution = new LinkedList<>();
        edgeTo = new HashMap<>();

        fringe.add(start, input.estimatedDistanceToGoal(start, end));
        distTo.put(start, 0.0);

        while (fringe.size() != 0 && result != 1) {
            timeSeconds = ((System.currentTimeMillis() - startTime) / 1000);
            if (!(timeSeconds < timeout)) {
                result = 1;
                break;
            }
            if (fringe.getSmallest().equals(end)) {
                break;
            }
            Vertex currentVertex = fringe.removeSmallest();
            numDequeues += 1;
            for (WeightedEdge<Vertex> edge : input.neighbors(currentVertex)) {
                relax(input, edge, end);
            }

        }
        if (fringe.size() == 0) {
            result = 2;
        } else if (timeSeconds > timeout) {
            result = 1;
        } else {
            Vertex v = end;
            while (!v.equals(start)) {
                solution.add(0, v);
                v = edgeTo.get(v).from();
            }
            solution.add(0, start);
            solutionSize = distTo.get(end);
            timeSeconds = ((System.currentTimeMillis() - startTime) / 1000);
            result = 0;
        }
    }

    private void relax(AStarGraph<Vertex> input, WeightedEdge<Vertex> edge, Vertex end) {
        Vertex q = edge.to(); //destination vertex from source vertex
        Vertex p = edge.from();
        double w = edge.weight();
        double heuristic = input.estimatedDistanceToGoal(q, end);
        double newDist = distTo.get(p) + w;
        if (!distTo.containsKey(q) || newDist < distTo.get(q)) {
            distTo.put(q, newDist);
            edgeTo.put(q, edge);
            if (fringe.contains(q)) {
                fringe.changePriority(q, newDist + heuristic);
            } else {
                fringe.add(q, newDist + heuristic);
            }
        }
    }

    /**
     * Returns one of SolverOutcome.SOLVED, SolverOutcome.TIMEOUT, or SolverOutcome.UNSOLVABLE
     */
    public SolverOutcome outcome() {
        if (result == 0) {
            return SolverOutcome.SOLVED;
        } else if (result == 1) {
            return SolverOutcome.TIMEOUT;
        }
        return SolverOutcome.UNSOLVABLE;
    }

    /**
     * A list of vertices corresponding to a solution.
     * Should be empty if result was TIMEOUT or UNSOLVABLE.
     */
    public List<Vertex> solution() {
        if (result == 1 || result == 2) {
            solution = new LinkedList<>();
        }
        return solution;
    }

    /**
     * The total weight of the given solution, taking into account edge weights.
     * Should be 0 if result was TIMEOUT or UNSOLVABLE.
     */
    public double solutionWeight() {
        if (result == 1 || result == 2) {
            return 0;
        }
        return solutionSize;
    }

    /**
     * The total number of priority queue dequeue operations.
     */
    public int numStatesExplored() {
        return numDequeues;
    }

    /**
     * The total time spent in seconds by the constructor.
     */
    public double explorationTime() {
        return timeSeconds;
    }
}
