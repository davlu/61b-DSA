package bearmaps.hw4;

import bearmaps.proj2ab.ArrayHeapMinPQ;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

public class AStarSolver<Vertex> implements ShortestPathsSolver<Vertex> {
    private int result;
    private int numDequeues;
    private int timeSeconds;
    private ArrayHeapMinPQ<Vertex> fringe;
    private HashMap<Vertex, Double> distTo;
    private List<Vertex> solution;
    private double solutionSize;

    /**
     * Constructor which finds the solution, computing everything necessary for all other methods to
     * return their results in constant time. Note that timeout passed in is in seconds
     */
    public AStarSolver(AStarGraph<Vertex> input, Vertex start, Vertex end, double timeout) {
        int startTime = (int) System.currentTimeMillis();
        timeSeconds = ((int) System.currentTimeMillis() - startTime) / 1000;
        fringe = new ArrayHeapMinPQ();  //create fringe PQ with start = 0 dist
        distTo = new HashMap<>();
        solution = new ArrayList<Vertex>();
        fringe.add(start, 0.0);
        while (fringe.size() != 0 && fringe.getSmallest() != end && result != 1) {
            Vertex currentVertex = fringe.removeSmallest();
            numDequeues += 1;
            for (WeightedEdge<Vertex> edge : input.neighbors(currentVertex)) {
                if (timeSeconds < timeout) {
                    relax(input, edge, currentVertex, end);
                    timeSeconds = ((int) System.currentTimeMillis() - startTime) / 1000;
                } else {
                    result = 1;
                    break;
                }
            }
        }
        if (fringe.size() == 0) {
            result = 2;
        } else {
            result = 0;
        }
    }

    private void relax(AStarGraph<Vertex> input,
                       WeightedEdge<Vertex> edge,
                       Vertex currentVertex, Vertex end) {
        Vertex next = edge.to(); //destination vertex from source vertex
        Vertex current = edge.from();
        double edgeWeight = edge.weight();
        double heuristic = input.estimatedDistanceToGoal(next, end);
        double currentPlusEdge = distTo.get(current) + edgeWeight;
        if (!distTo.containsKey(next)) { //if distTo doesn't contain nextVertex..
            //add distance from source + weight
            distTo.put(next, edge.weight() + distTo.get(currentVertex));
        }
        if (currentPlusEdge < distTo.get(next)) {
            distTo.put(next, currentPlusEdge);
            if (distTo.containsKey(next)) {
                fringe.changePriority(next, distTo.get(next) + heuristic);
            } else {
                fringe.add(next, distTo.get(next) + heuristic);
            }
            solution.add(next);
            solutionSize += edgeWeight;
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
            solution = new ArrayList<>();
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
