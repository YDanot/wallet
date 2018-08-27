package wallet.stock.domain;


import java.math.BigDecimal;

public class Stock {

    private final BigDecimal quantity;
    private final StockType stockType;

    private Stock(BigDecimal quantity, StockType stockType) {
        this.quantity = quantity;
        this.stockType = stockType;
    }

    public static Stock of(BigDecimal quantity, StockType stockType) {
        return new Stock(quantity, stockType);
    }

    public BigDecimal value() {
        return quantity;
    }

    public StockType stockType() {
        return stockType;
    }
}
