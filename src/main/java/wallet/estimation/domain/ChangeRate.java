package wallet.estimation.domain;

import wallet.stock.domain.StockType;

import java.math.BigDecimal;

public class ChangeRate {

    private final BigDecimal value;
    private final StockType from;
    private final Currency to;

    public ChangeRate(BigDecimal value, StockType from, Currency to) {
        this.value = value;
        this.from = from;
        this.to = to;
    }

    public static ChangeRate of(BigDecimal value, StockType from, Currency to) {
        return new ChangeRate(value, from, to);
    }

    private BigDecimal value() {
        return value;
    }

    public Money apply(BigDecimal value) {
        return Money.of(value().multiply(value), to);
    }


    public boolean sameConversion(StockType from, Currency to){
        return this.from.equals(from) && this.to.equals(to);
    }
}
