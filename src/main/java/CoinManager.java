import java.util.Map;

public class CoinManager {

    private static Map<Coin, Integer> valueMap;
    private static Map<Coin, Integer> coinsHolding;
    private static Map<Coin, Integer> coinReturn;

    public CoinManager(Map<Coin, Integer> valueMap, Map<Coin, Integer> coinsHolding, Map<Coin, Integer> coinReturn) {
        CoinManager.valueMap = valueMap;
        CoinManager.coinsHolding = coinsHolding;
        CoinManager.coinReturn = coinReturn;
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
        if (isValid(coin)) {
            coinsHolding.put(coin, coinsHolding.getOrDefault(coin, 0) + 1);
        } else toReturn(coin);
    }

    public int currentSum() {
        int sum = 0;
        for (Coin coin : coinsHolding.keySet()) {
            sum += getValue(coin) * coinsHolding.get(coin);
        }
        return sum;
    }

    public void toReturn(Coin coin) {
        coinReturn.put(coin, coinReturn.getOrDefault(coin, 0) + 1);
    }
}
