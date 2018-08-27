package wallet;

import wallet.stock.Stock;
import wallet.stock.Wallet;

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

    public WalletBuilder withStock(Stock stock) {

        this.stockList.add(stock);
        return this;
    }

    public Wallet build() {
        Wallet wallet = new Wallet(stockList);
        return wallet;
    }
}
