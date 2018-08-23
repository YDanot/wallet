package wallet;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;

import static wallet.Currency.EUR;
import static wallet.StockType.BITCOIN;
import static wallet.StockType.US_DOLLAR;

public class WalletTest {

    private Money finalValue;

    private WalletBuilder walletBuilder = WalletBuilder.aWallet();
    private ChangeRatesBuilder changeRatesBuilder = ChangeRatesBuilder.aChangeRates();

    @Test
    public void should_convert_BTC_to_EUR() throws IOException {
        given_a_wallet()
                .withStock(Stock.of(BigDecimal.valueOf(2), BITCOIN));
        and_BTC_to_EUR_rate_is(5000);
        when_I_compute_value_in_euro();
        computed_euro_value_should_be(10000);
    }

    @Test
    public void should_convert_USD_to_EUR() {
        given_a_wallet()
                .withStock(Stock.of(BigDecimal.valueOf(10), US_DOLLAR));
        and_USD_to_EUR_rate_is(0.90);
        when_I_compute_value_in_euro();
        computed_euro_value_should_be(9);
    }

    @Test
    public void should_compute_total_value_of_a_wallet() throws IOException {
        given_a_wallet()
                .withStock(Stock.of(BigDecimal.valueOf(2), BITCOIN))
                .withStock(Stock.of(BigDecimal.valueOf(10), US_DOLLAR));
        and_BTC_to_EUR_rate_is(2000);
        and_USD_to_EUR_rate_is(0.90);

        when_I_compute_value_in_euro();
        computed_euro_value_should_be(4000 + 9);

    }

    private WalletBuilder given_a_wallet() {
        walletBuilder = WalletBuilder.aWallet();
        return walletBuilder;
    }

    private void and_BTC_to_EUR_rate_is(int value) {
        changeRatesBuilder.addRate(ChangeRate.of(BigDecimal.valueOf(value), BITCOIN, EUR));
    }

    private void and_USD_to_EUR_rate_is(double rate) {
        changeRatesBuilder.addRate(ChangeRate.of(BigDecimal.valueOf(rate), US_DOLLAR, EUR));
    }

    private void when_I_compute_value_in_euro() {
        finalValue = new WalletValue(changeRatesBuilder.build()).estimate(walletBuilder.build(), EUR);
    }

    private void computed_euro_value_should_be(int value) {
        Assertions.assertThat(finalValue.value()).isEqualByComparingTo(BigDecimal.valueOf(value));
    }

}
