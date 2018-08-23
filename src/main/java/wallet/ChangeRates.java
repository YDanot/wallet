package wallet;


import java.util.List;

public class ChangeRates {

    private final List<Rate> rates;

    public ChangeRates(List<Rate> rates) {
        this.rates = rates;
    }

    public Rate find(StockType from, Currency to){
        return rates.stream().filter(r -> r.from().equals(from) && r.to().equals(to)).findAny().orElseThrow(IllegalStateException::new);
    }
}
