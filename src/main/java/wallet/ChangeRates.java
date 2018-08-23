package wallet;


import java.util.List;

public class ChangeRates {

    private final List<ChangeRate> changeRates;

    public ChangeRates(List<ChangeRate> changeRates) {
        this.changeRates = changeRates;
    }

    public ChangeRate find(StockType from, Currency to){
        return changeRates.stream().filter(r -> r.sameConversion(from,to)).findAny()
                .orElseThrow(() -> new IllegalArgumentException("There is no rate existing to convert from: "+from+" to: "+to));
    }
}
