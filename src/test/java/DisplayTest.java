import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DisplayTest {

    @Test
    public void defaultDisplayState() {
        Display display = new Display();
        assertEquals("INSERT COIN", display.show());
    }
}
