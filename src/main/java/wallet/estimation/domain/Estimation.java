package wallet.estimation.domain;

import wallet.stock.domain.Wallet;

public class Estimation {

    private final Converter converter;

    private final Wallet wallet;

    public Estimation(Wallet wallet, Converter converter) {
        this.converter = converter;
        this.wallet = wallet;
    }

    public Money in(Currency currency) {
        return this.wallet.evaluate(stock -> converter.convert(stock, currency));
    }

}
