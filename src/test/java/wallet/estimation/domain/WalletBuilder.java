package wallet.estimation.domain;

import wallet.stock.domain.Stock;
import wallet.stock.domain.StockType;
import wallet.stock.domain.Wallet;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public final class WalletBuilder {
    private List<Stock> stockList;

    private WalletBuilder() {
        stockList = new ArrayList<>();
    }

    public static WalletBuilder aWallet() {
        return new WalletBuilder();
    }

    private WalletBuilder withStock(Stock stock) {

        this.stockList.add(stock);
        return this;
    }

    public Wallet build() {
        return new Wallet(stockList);
    }

    public WalletBuilder withStockOf(double quantity, StockType type) {
        return withStock(Stock.of(BigDecimal.valueOf(quantity),type));
    }
}
