import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CoinManagerTest {

    @Test
    public void isValid() {
        assertTrue(CoinManager.isValid(Coin.DIME));
    }

    @Test
    public void inNotValid() {
        assertFalse(CoinManager.isValid(Coin.PENNY));
    }
}
