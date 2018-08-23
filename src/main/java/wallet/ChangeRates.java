package wallet;


import java.util.List;

public class ChangeRates {

    private final List<ChangeRate> changeRates;

    public ChangeRates(List<ChangeRate> changeRates) {
        this.changeRates = changeRates;
    }

    public ChangeRate find(StockType from, Currency to){
        return changeRates.stream().filter(r -> r.from().equals(from) && r.to().equals(to)).findAny().orElseThrow(IllegalStateException::new);
    }
}
