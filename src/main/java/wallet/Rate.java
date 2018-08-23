package wallet;

import java.math.BigDecimal;

public class Rate {
    private final BigDecimal value;
    private final StockType from;
    private final Currency to;

    private Rate(BigDecimal value, StockType from, Currency to) {
        this.value = value;
        this.from = from;
        this.to = to;
    }

    public static Rate of(BigDecimal value, StockType from, Currency to) {
        return new Rate(value, from, to);
    }

    public BigDecimal value() {
        return value;
    }

    public BigDecimal apply(BigDecimal value) {
        return value().multiply(value);
    }
}
