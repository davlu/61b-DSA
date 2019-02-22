package creatures;

import huglife.*;


import java.awt.Color;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;


public class Clorus extends Creature{
    private int r;
    private int b;
    private int g;
    public Clorus(double e){
        super("clorus");
        r  = 0;
        b = 0;
        g = 0;
        energy = e;
    }

    public Clorus(){
        this(1);
    }

    public Color color(){
        return color(34, 0 , 231);
    }

    public void move(){
        this.energy -= 0.03;
    }
    public void  stay(){
        this.energy -= 0.01;
    }
    public void attack(Creature c){
        this.energy += c.energy();
    }

    public Clorus replicate(){
        Clorus replicant = new Clorus();
        double energy = this.energy / 2;
        replicant.energy += energy;
        this.energy -= energy;
        return replicant;
    }

    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        Deque<Direction> emptyNeighbors = new ArrayDeque<Direction>();
        Deque<Direction> plipNeighbors = new ArrayDeque<Direction>();
        boolean plipSeen = false;
        for(Direction d : neighbors.keySet()){
            if(neighbors.get(d).name()== "empty"){
                emptyNeighbors.add(d);
            }
            else if(neighbors.get(d).name() == "plip"){
                plipSeen = true;
                plipNeighbors.add(d);
            }
        }
        if(emptyNeighbors.isEmpty()){
            return new Action(Action.ActionType.STAY);
        }
        else if(plipSeen){
            Direction d = HugLifeUtils.randomEntry(plipNeighbors);
            return new Action(Action.ActionType.ATTACK, d);
        }

        else if(this.energy()>1.0){
            Direction d = HugLifeUtils.randomEntry(emptyNeighbors);
            return new Action(Action.ActionType.REPLICATE, d);
        }
        else{
            Direction d = HugLifeUtils.randomEntry(emptyNeighbors);
            return new Action(Action.ActionType.MOVE, d);
        }
    }
}
