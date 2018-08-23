package wallet;


import java.math.BigDecimal;

public class Stock {

    private final BigDecimal value;
    private final StockType stockType;

    public Stock(BigDecimal value, StockType stockType) {
        this.value = value;
        this.stockType = stockType;
    }

    public BigDecimal value() {
        return value;
    }

    public StockType stockType() {
        return stockType;
    }
}
