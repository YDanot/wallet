package wallet;

import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;

public class WalletValue {

    private final ChangeRates changeRates;

    public WalletValue(ChangeRates changeRates) {
        this.changeRates = changeRates;
    }

    public Money estimate(Wallet wallet, Currency currency) {
        return wallet.stockList().stream()
                .map(stock -> estimate(stock, currency))
                .reduce(Money::add)
                .orElse(Money.of(BigDecimal.ZERO, currency));
    }

    @NotNull
    private Money estimate(Stock stock, Currency currency) {
        ChangeRate changeRate = changeRates.find(stock.stockType(), currency);
        return Money.of(changeRate.apply(stock.value()), currency);
    }
}
