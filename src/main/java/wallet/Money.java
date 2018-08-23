package wallet;

import java.math.BigDecimal;

public class Money {
    private final BigDecimal value;
    private final Currency currency;

    public Money(BigDecimal value, Currency currency) {
        this.value = value;
        this.currency = currency;
    }

    public BigDecimal value() {
        return value;
    }

    public Currency currency() {
        return currency;
    }
}
