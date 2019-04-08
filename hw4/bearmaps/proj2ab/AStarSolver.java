package bearmaps.proj2ab;

public class AStarSolver<Vertex> implements ShortestPathsSolver<Vertex> {
    public AStarSolver(AStarGraph<Vertex> input, Vertex start, Vertex end, double timeout){
        ArrayHeapMinPQ<Vertex> fringe = new ArrayHeapMinPQ();
        fringe.add(start, 0.0);
        Vertex currentVertex = start;
        for(T edge : input.neighbors(start)){

        }
    }
    public SolverOutcome outcome(){

    }
    public List<Vertex> solution(){

    }
    public double solutionWeight(){

    }
    public int numStatesExplored(){

    }
    public double explorationTime(){

    }
}
