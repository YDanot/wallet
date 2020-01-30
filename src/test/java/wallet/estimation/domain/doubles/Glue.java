package wallet.estimation.domain.doubles;

import org.assertj.core.api.Assertions;
import wallet.estimation.domain.Estimation;
import wallet.estimation.domain.Money;
import wallet.estimation.domain.ChangeRate;
import wallet.estimation.helper.WalletBuilder;

import java.math.BigDecimal;

import static wallet.estimation.domain.Currency.EURO;
import static wallet.stock.domain.StockType.BITCOIN;
import static wallet.stock.domain.StockType.US_DOLLAR;

public class Glue {

    private Money finalValue;
    private WalletBuilder walletBuilder;
    private LocalConverter localConverter = new LocalConverter();


    public WalletBuilder given_a_wallet() {
        walletBuilder = WalletBuilder.aWallet();
        return walletBuilder;
    }

    public void and_BTC_to_EUR_rate_is(int value) {
        localConverter = localConverter.addRate(ChangeRate.of(BigDecimal.valueOf(value), BITCOIN, EURO));
    }

    public void and_USD_to_EUR_rate_is(double rate) {
        localConverter = localConverter.addRate(ChangeRate.of(BigDecimal.valueOf(rate), US_DOLLAR, EURO));
    }


    public void when_I_compute_value_in_euro() {
        finalValue = new Estimation(walletBuilder.build(), localConverter).in(EURO);
    }

    public void computed_euro_value_should_be(int value) {
        Assertions.assertThat(finalValue.value()).isEqualByComparingTo(BigDecimal.valueOf(value));
    }

    public void tearDown() {
        finalValue = null;
        walletBuilder = null;
        localConverter = new LocalConverter();
    }
}