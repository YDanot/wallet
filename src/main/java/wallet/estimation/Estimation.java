package wallet.estimation;

import wallet.stock.Wallet;

import java.math.BigDecimal;

public class Estimation {

    private final Convertor convertor;

    private final Wallet wallet;

    public Estimation(Wallet wallet, Convertor convertor) {
        this.convertor = convertor;
        this.wallet = wallet;
    }


    public Money in(Currency currency) {
        return this.wallet.stockList().stream()
                .map(stock -> convertor.convert(stock, currency))
                .reduce(Money::add)
                .orElse(Money.of(BigDecimal.ZERO, currency));
    }

}
