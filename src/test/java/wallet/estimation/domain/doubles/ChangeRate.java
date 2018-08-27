package wallet.estimation.domain.doubles;

import wallet.estimation.domain.Currency;
import wallet.stock.domain.StockType;

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

    private BigDecimal value() {
        return value;
    }

    BigDecimal apply(BigDecimal value) {
        return value().multiply(value);
    }


    boolean sameConversion(StockType from, Currency to){
        return this.from.equals(from) && this.to.equals(to);
    }
}
