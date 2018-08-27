package wallet.estimation.domain;

import wallet.stock.domain.Wallet;

import java.math.BigDecimal;

public class Estimation {

    private final Converter converter;

    private final Wallet wallet;

    public Estimation(Wallet wallet, Converter converter) {
        this.converter = converter;
        this.wallet = wallet;
    }

    public Money in(Currency currency) {
        return this.wallet.stockList().stream()
                .map(stock -> converter.convert(stock, currency))
                .reduce(Money::add)
                .orElse(Money.of(BigDecimal.ZERO, currency));
    }

}
