package wallet.stock;


import java.math.BigDecimal;

public class Stock {

    private final BigDecimal value;
    private final StockType stockType;

    private Stock(BigDecimal value, StockType stockType) {
        this.value = value;
        this.stockType = stockType;
    }

    public static Stock of(BigDecimal value, StockType stockType) {
        return new Stock(value, stockType);
    }

    public BigDecimal value() {
        return value;
    }

    public StockType stockType() {
        return stockType;
    }
}
