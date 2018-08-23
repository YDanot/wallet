package wallet;

import java.util.ArrayList;
import java.util.List;


public final class ChangeRatesBuilder {
    private List<Rate> rates;

    private ChangeRatesBuilder() {
        rates = new ArrayList<>();
    }

    public static ChangeRatesBuilder aChangeRates() {
        return new ChangeRatesBuilder();
    }

    public ChangeRatesBuilder addRate(Rate rate) {
        this.rates.add(rate);
        return this;
    }

    public ChangeRates build() {
        ChangeRates changeRates = new ChangeRates(rates);
        return changeRates;
    }
}
