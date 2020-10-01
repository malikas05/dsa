package array;

public class StockPrices {

    // 8, 6, 5, 4, 3, 2, 1
    public static Tuple findBuySellStockPrices(int[] array) {
        if (array == null || array.length < 2) {
            return null;
        }

        int currentBuy = array[0];
        int globalSell = array[1];
        int globalProfit = globalSell - currentBuy;

        int currentProfit;

        for (int i = 1; i < array.length; i++) {
            currentProfit = array[i] - currentBuy;

            if (currentProfit > globalProfit) {
                globalProfit = currentProfit;
                globalSell = array[i];
            }

            if (currentBuy > array[i]) {
                currentBuy = array[i];
            }
        }

        Tuple<Integer, Integer> result = new Tuple<>(globalSell - globalProfit, globalSell);
        return result;
    }

    static class Tuple<X, Y> {
        public X x;
        public Y y;

        public Tuple(X x, Y y) {
            this.x = x;
            this.y = y;
        }
    }
}
