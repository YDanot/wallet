package wallet.estimation.domain;

import java.math.BigDecimal;
import java.text.NumberFormat;

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

    Money add(Money money) {
        return Money.of(value().add(money.value()), currency);
    }

    @Override
    public String toString() {
        return NumberFormat.getCurrencyInstance(currency.locale()).format(value.doubleValue());
    }
}