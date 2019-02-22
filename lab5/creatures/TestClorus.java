package creatures;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.HashMap;
import java.awt.Color;
import huglife.Direction;
import huglife.Action;
import huglife.Occupant;
import huglife.Impassible;
import huglife.Empty;

public class TestClorus {
    @Test
    public void testBasics(){
        Clorus testClorus = new Clorus(3.0);
        assertEquals(3.0, testClorus.energy(), 0.01);
        assertEquals(new Color(34, 0 ,231), testClorus.color());
        Clorus testReplicant = testClorus.replicate();
        assertEquals(1.5, testClorus.energy(), 0.01);
        assertEquals(2.5, testReplicant.energy(), 0.01);
    }

    @Test
    public void testChoose(){
        Clorus testClorus = new Clorus(4.0);
        assertEquals(4.0, testClorus.energy(), 0.01);
        assertEquals(new Color(34, 0 ,231), testClorus.color());

        HashMap<Direction, Occupant> surrounded = new HashMap<Direction, Occupant>();
        surrounded.put(Direction.TOP, new Impassible());
        surrounded.put(Direction.BOTTOM, new Impassible());
        surrounded.put(Direction.LEFT, new Impassible());
        surrounded.put(Direction.RIGHT, new Impassible());
        assertEquals(new Action(Action.ActionType.STAY), testClorus.chooseAction(surrounded));

        testClorus = new Clorus(4.0);
        HashMap<Direction, Occupant> plipSeen = new HashMap<Direction, Occupant>();
        plipSeen.put(Direction.TOP, new Plip());
        plipSeen.put(Direction.BOTTOM, new Impassible());
        plipSeen.put(Direction.LEFT, new Impassible());
        plipSeen.put(Direction.RIGHT, new Empty());
        assertEquals(new Action(Action.ActionType.ATTACK, Direction.TOP), testClorus.chooseAction(plipSeen));

        testClorus = new Clorus(4.0);
        HashMap<Direction, Occupant> replicateEnergy = new HashMap<Direction, Occupant>();
        replicateEnergy.put(Direction.TOP, new Empty());
        replicateEnergy.put(Direction.BOTTOM, new Impassible());
        replicateEnergy.put(Direction.LEFT, new Impassible());
        replicateEnergy.put(Direction.RIGHT, new Impassible());
        assertEquals(new Action(Action.ActionType.REPLICATE, Direction.TOP), testClorus.chooseAction(replicateEnergy));

        testClorus = new Clorus(0);
        HashMap<Direction, Occupant> basicMove = new HashMap<Direction, Occupant>();
        basicMove.put(Direction.TOP, new Empty());
        basicMove.put(Direction.BOTTOM, new Impassible());
        basicMove.put(Direction.LEFT, new Impassible());
        basicMove.put(Direction.RIGHT, new Impassible());
        assertEquals(new Action(Action.ActionType.MOVE, Direction.TOP), testClorus.chooseAction(basicMove));
    }
}
