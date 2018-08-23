package wallet;

import java.math.BigDecimal;

public class WalletValue {

    private final ChangeRates changeRates;

    public WalletValue(ChangeRates changeRates) {
        this.changeRates = changeRates;
    }

    public Money estimate(Wallet wallet, Currency currency) {
        Money money = Money.of(BigDecimal.ZERO, currency);
        for (Stock stock : wallet.stockList()) {
            money = money.add(Money.of(changeRates.find(stock.stockType(), currency).apply(stock.value()), currency));
        }

        return money;
    }
}
