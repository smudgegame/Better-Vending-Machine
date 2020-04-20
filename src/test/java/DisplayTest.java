import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class DisplayTest {

    @Test
    public void defaultDisplayState() {
        Map<Coin, Integer> valueMap = new HashMap<>();
        Map<Coin, Integer> coinsHolding = new HashMap<>();
        Map<Coin, Integer> coinReturn = new HashMap<>();
        CoinManager coinManager = new CoinManager(valueMap, coinsHolding, coinReturn);
        Display display = new Display(coinManager);
        assertEquals("INSERT COIN", display.show());
    }

    @Test
    public void displaySum() {
        Map<Coin, Integer> valueMap = new HashMap<>();
        Map<Coin, Integer> coinsHolding = new HashMap<>();
        Map<Coin, Integer> coinReturn = new HashMap<>();
        CoinManager coinManager = new CoinManager(valueMap, coinsHolding, coinReturn);
        coinManager.receiveCoin(Coin.QUARTER);
        Display display = new Display(coinManager);
        assertEquals("$0.25", display.show());
    }

    @Test
    public void displayFormat() {
        Map<Coin, Integer> valueMap = new HashMap<>();
        Map<Coin, Integer> coinsHolding = new HashMap<>();
        Map<Coin, Integer> coinReturn = new HashMap<>();
        CoinManager coinManager = new CoinManager(valueMap, coinsHolding, coinReturn);
        coinManager.receiveCoin(Coin.DIME);
        Display display = new Display(coinManager);
        assertEquals("$0.10", display.show());
    }
}
