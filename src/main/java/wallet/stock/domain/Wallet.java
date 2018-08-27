package wallet.stock.domain;

import org.assertj.core.util.Lists;

import java.util.List;

public class Wallet {

    private final List<Stock> stockList;

    public Wallet(List<Stock> stockList) {
        this.stockList = stockList;
    }

    public List<Stock> stockList() {
        return Lists.newArrayList(stockList);
    }
}
