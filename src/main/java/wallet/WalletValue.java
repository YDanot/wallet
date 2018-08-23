package wallet;

import java.math.BigDecimal;

import static wallet.Currency.EUR;

public class WalletValue {

    private final ChangeRates changeRates;

    public WalletValue(ChangeRates changeRates) {
        this.changeRates = changeRates;
    }

    public Money estimate(Wallet wallet) {
        Money money = Money.of(BigDecimal.ZERO, EUR);
        for (Stock stock : wallet.stockList()) {
            money = money.add(Money.of(changeRates.find(stock.stockType(), EUR).apply(stock.value()), EUR));
        }

        return money;
    }
}
