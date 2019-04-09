package bearmaps.hw4;

import bearmaps.proj2ab.ArrayHeapMinPQ;
import java.util.List;

public class AStarSolver<Vertex> implements ShortestPathsSolver<Vertex> {

    /**Constructor which finds the solution, computing everything necessary for all other methods to
     return their results in constant time. Note that timeout passed in is in seconds*/
    public AStarSolver(AStarGraph<Vertex> input, Vertex start, Vertex end, double timeout){
        int startTime = (int) System.currentTimeMillis();
        ArrayHeapMinPQ<Vertex> fringe = new ArrayHeapMinPQ();
        fringe.add(start, 0.0);
        Vertex currentVertex = start;
        for(WeightedEdge<Vertex> edge : input.neighbors(start)){

        }
    }

    /**Returns one of SolverOutcome.SOLVED, SolverOutcome.TIMEOUT, or SolverOutcome.UNSOLVABLE*/
    public SolverOutcome outcome(){

    }

    /**A list of vertices corresponding to a solution. Should be empty if result was TIMEOUT or UNSOLVABLE.*/
    public List<Vertex> solution(){

    }

    /**The total weight of the given solution, taking into account edge weights.
      Should be 0 if result was TIMEOUT or UNSOLVABLE. */
    public double solutionWeight(){

    }

    /**The total number of priority queue dequeue operations.*/
    public int numStatesExplored(){

    }

    /**The total time spent in seconds by the constructor.*/
    public double explorationTime(){

    }
}
