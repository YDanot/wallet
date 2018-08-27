package wallet.estimation;

import wallet.stock.StockType;

import java.math.BigDecimal;

public class ChangeRate {

    private final BigDecimal value;
    private final StockType from;
    private final Currency to;

    private ChangeRate(BigDecimal value, StockType from, Currency to) {
        this.value = value;
        this.from = from;
        this.to = to;
    }

    public static ChangeRate of(BigDecimal value, StockType from, Currency to) {
        return new ChangeRate(value, from, to);
    }

    public BigDecimal value() {
        return value;
    }

    public BigDecimal apply(BigDecimal value) {
        return value().multiply(value);
    }


    public boolean sameConversion(StockType from, Currency to){
        return this.from.equals(from) && this.to.equals(to);
    }
}
