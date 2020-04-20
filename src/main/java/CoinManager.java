import java.util.Map;

public class CoinManager {

    private static Map<Coin, Integer> valueMap;

    public CoinManager(Map<Coin, Integer> valueMap) {
        this.valueMap = valueMap;
        valueMap.put(Coin.DIME, 10);
        valueMap.put(Coin.NICKEL, 5);
        valueMap.put(Coin.QUARTER, 25);
    }

    public static boolean isValid(Coin coin) {
        if (coin.equals(Coin.PENNY)) return false;
        return true;
    }

    public static int getValue(Coin coin) {
        return valueMap.get(coin);
    }
}
