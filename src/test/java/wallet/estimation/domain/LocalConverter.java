package wallet.estimation.domain;


import org.assertj.core.util.Lists;
import wallet.stock.domain.Stock;
import wallet.stock.domain.StockType;

import java.util.ArrayList;
import java.util.List;

public class LocalConverter implements Converter {


    private final List<ChangeRate> changeRates;

    private LocalConverter(List<ChangeRate> changeRates) {
        this.changeRates = changeRates;
    }

    LocalConverter() {
        this.changeRates = new ArrayList<>();
    }

    @Override
    public Money convert(Stock stock, Currency currency) {
        ChangeRate changeRate = find(stock.stockType(), currency);
        return Money.of(changeRate.apply(stock.value()), currency);
    }

    private ChangeRate find(StockType from, Currency to){
        return changeRates.stream().filter(r -> r.sameConversion(from,to)).findAny()
                .orElseThrow(() -> new IllegalArgumentException("There is no rate existing to convert from: "+from+" to: "+to));
    }

    LocalConverter addRate(ChangeRate changeRate) {
        ArrayList<ChangeRate> changeRates = Lists.newArrayList(this.changeRates);
        changeRates.add(changeRate);
        return new LocalConverter(changeRates);
    }

}
