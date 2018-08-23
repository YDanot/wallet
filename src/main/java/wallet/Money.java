package wallet;

import java.math.BigDecimal;

public class Money {
    private final BigDecimal value;
    private final Currency currency;

    private Money(BigDecimal value, Currency currency) {
        this.value = value;
        this.currency = currency;
    }

    public static Money of(BigDecimal value, Currency currency) {
        return new Money(value, currency);
    }

    public BigDecimal value() {
        return value;
    }

    public Money add(Money money) {
        return Money.of(value().add(money.value()), currency);
    }
}
