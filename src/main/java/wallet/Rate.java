package wallet;

import java.math.BigDecimal;

public class Rate {
    private final BigDecimal value;

    private Rate(BigDecimal value) {
        this.value = value;
    }

    public static Rate of(BigDecimal value) {
        return new Rate(value);
    }

    public BigDecimal value() {
        return value;
    }
}
