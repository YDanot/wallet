package wallet;

import java.util.ArrayList;
import java.util.List;


public final class ChangeRatesBuilder {
    private List<ChangeRate> changeRates;

    private ChangeRatesBuilder() {
        changeRates = new ArrayList<>();
    }

    public static ChangeRatesBuilder aChangeRates() {
        return new ChangeRatesBuilder();
    }

    public ChangeRatesBuilder addRate(ChangeRate changeRate) {
        this.changeRates.add(changeRate);
        return this;
    }

    public ChangeRates build() {
        ChangeRates changeRates = new ChangeRates(this.changeRates);
        return changeRates;
    }
}
