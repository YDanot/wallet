package wallet;

import java.math.BigDecimal;

public class Rate {
    private final BigDecimal value;

    public Rate(BigDecimal value) {
        this.value = value;
    }

    public BigDecimal value() {
        return value;
    }
}
