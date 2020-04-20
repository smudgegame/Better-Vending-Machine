import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class CoinManagerTest {

    @Test
    public void isValid() {
        assertTrue(CoinManager.isValid(Coin.DIME));
    }

    @Test
    public void inNotValid() {
        assertFalse(CoinManager.isValid(Coin.PENNY));
    }

    @Test
    public void coinValue() {
        Map<Coin, Integer> valueMap = new HashMap<>();
        Map<Coin, Integer> coinsHolding = new HashMap<>();
        CoinManager coinManager = new CoinManager(valueMap, coinsHolding);
        assertEquals(10, coinManager.getValue(Coin.DIME));
        assertEquals(5, coinManager.getValue(Coin.NICKEL));
        assertEquals(25, coinManager.getValue(Coin.QUARTER));
    }

    @Test
    public void coinsHolding() {
        Map<Coin, Integer> valueMap = new HashMap<>();
        Map<Coin, Integer> coinsHolding = new HashMap<>();
        CoinManager coinManager = new CoinManager(valueMap, coinsHolding);
        coinManager.receiveCoin(Coin.QUARTER);
        int holding = coinsHolding.get(Coin.QUARTER);
        assertEquals(1, holding);
    }
}
