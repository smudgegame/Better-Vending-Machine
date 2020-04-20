import java.util.Map;

public class CoinManager {

    private static Map<Coin, Integer> valueMap;
    private static Map<Coin, Integer> coinsHolding;

    public CoinManager(Map<Coin, Integer> valueMap, Map<Coin, Integer> coinsHolding) {
        CoinManager.valueMap = valueMap;
        CoinManager.coinsHolding = coinsHolding;
        valueMap.put(Coin.DIME, 10);
        valueMap.put(Coin.NICKEL, 5);
        valueMap.put(Coin.QUARTER, 25);
    }

    public static boolean isValid(Coin coin) {
        return !coin.equals(Coin.PENNY);
    }

    public static int getValue(Coin coin) {
        return valueMap.get(coin);
    }

    public void receiveCoin(Coin coin) {
        if (isValid(coin))
            coinsHolding.put(coin, coinsHolding.getOrDefault(coin, 0) + 1);
    }

    public int currentSum() {
        int sum = 0;
        for (Coin coin : coinsHolding.keySet()) {
            sum += getValue(coin) * coinsHolding.get(coin);
        }
        return sum;
    }
}
