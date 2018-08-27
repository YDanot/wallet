package wallet.estimation;

import wallet.stock.Wallet;

import java.math.BigDecimal;

public class WalletValue {

    private final Convertor convertor;

    public WalletValue(Convertor convertor) {
        this.convertor = convertor;
    }

    public Money estimate(Wallet wallet, Currency currency) {
        return wallet.stockList().stream()
                .map(stock -> convertor.convert(stock, currency))
                .reduce(Money::add)
                .orElse(Money.of(BigDecimal.ZERO, currency));
    }

}
