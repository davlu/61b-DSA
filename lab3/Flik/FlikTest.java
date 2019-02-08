import static org.junit.Assert.*;
import org.junit.Test;
public class FlikTest {
    @Test
    public void testEquivalence() {
        boolean b = Flik.isSameNumber(128,128);
        assertTrue(Flik.isSameNumber(128, 128));
    }
}
