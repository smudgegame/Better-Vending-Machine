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
        Map<Coin, Integer> coinReturn = new HashMap<>();
        CoinManager coinManager = new CoinManager(valueMap, coinsHolding, coinReturn);
        assertEquals(10, coinManager.getValue(Coin.DIME));
        assertEquals(5, coinManager.getValue(Coin.NICKEL));
        assertEquals(25, coinManager.getValue(Coin.QUARTER));
    }

    @Test
    public void coinsHolding() {
        Map<Coin, Integer> valueMap = new HashMap<>();
        Map<Coin, Integer> coinsHolding = new HashMap<>();
        Map<Coin, Integer> coinReturn = new HashMap<>();
        CoinManager coinManager = new CoinManager(valueMap, coinsHolding, coinReturn);
        coinManager.receiveCoin(Coin.QUARTER);
        int holding = coinsHolding.get(Coin.QUARTER);
        assertEquals(1, holding);
    }

    @Test
    public void holdsMultipleCoins() {
        Map<Coin, Integer> valueMap = new HashMap<>();
        Map<Coin, Integer> coinsHolding = new HashMap<>();
        Map<Coin, Integer> coinReturn = new HashMap<>();
        CoinManager coinManager = new CoinManager(valueMap, coinsHolding, coinReturn);
        coinManager.receiveCoin(Coin.QUARTER);
        coinManager.receiveCoin(Coin.QUARTER);
        int holding = coinsHolding.get(Coin.QUARTER);
        assertEquals(2, holding);
    }

    @Test
    public void currentSum() {
        Map<Coin, Integer> valueMap = new HashMap<>();
        Map<Coin, Integer> coinsHolding = new HashMap<>();
        Map<Coin, Integer> coinReturn = new HashMap<>();
        CoinManager coinManager = new CoinManager(valueMap, coinsHolding, coinReturn);
        coinManager.receiveCoin(Coin.QUARTER);
        coinManager.receiveCoin(Coin.QUARTER);
        coinManager.receiveCoin(Coin.PENNY);
        assertEquals(50, coinManager.currentSum());
    }

    @Test
    public void coinReturn() {
        Map<Coin, Integer> valueMap = new HashMap<>();
        Map<Coin, Integer> coinsHolding = new HashMap<>();
        Map<Coin, Integer> coinReturn = new HashMap<>();
        CoinManager coinManager = new CoinManager(valueMap, coinsHolding, coinReturn);
        coinManager.receiveCoin(Coin.PENNY);
        int inReturn = coinReturn.get(Coin.PENNY);
        assertEquals(1, inReturn);
    }

    @Test
    public void makeChange() {
        Map<Coin, Integer> valueMap = new HashMap<>();
        Map<Coin, Integer> coinsHolding = new HashMap<>();
        Map<Coin, Integer> coinReturn = new HashMap<>();
        CoinManager coinManager = new CoinManager(valueMap, coinsHolding, coinReturn);
        for (int i = 0; i < 4; i++) coinManager.receiveCoin(Coin.QUARTER);
        int productPrice = 60;
        coinManager.makeChange(productPrice);
        int dimes = coinReturn.getOrDefault(Coin.DIME, 0);
        int nickels = coinReturn.getOrDefault(Coin.NICKEL, 0);
        int quarters = coinReturn.getOrDefault(Coin.QUARTER, 0);
        assertEquals(1, dimes);
        assertEquals(1, nickels);
        assertEquals(1, quarters);
    }
}
