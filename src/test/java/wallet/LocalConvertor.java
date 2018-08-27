package wallet;


import org.assertj.core.util.Lists;
import wallet.estimation.ChangeRate;
import wallet.estimation.Convertor;
import wallet.estimation.Currency;
import wallet.estimation.Money;
import wallet.stock.Stock;
import wallet.stock.StockType;

import java.util.ArrayList;
import java.util.List;

public class LocalConvertor implements Convertor {


    private final List<ChangeRate> changeRates;

    private LocalConvertor(List<ChangeRate> changeRates) {
        this.changeRates = changeRates;
    }

    LocalConvertor() {
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

    LocalConvertor addRate(ChangeRate changeRate) {
        ArrayList<ChangeRate> changeRates = Lists.newArrayList(this.changeRates);
        changeRates.add(changeRate);
        return new LocalConvertor(changeRates);
    }

}
