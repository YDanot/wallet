package wallet.stock.domain;

import wallet.estimation.domain.Money;

import java.util.List;
import java.util.function.Function;

public class Wallet {

    private final List<Stock> stockList;

    public Wallet(List<Stock> stockList) {
        this.stockList = stockList;
    }

    public Money evaluate(Function<Stock, Money> conversionFunction) {
        return stockList.stream()
                .map(conversionFunction)
                .reduce(Money::add).orElseThrow(IllegalArgumentException::new);
    }
}
