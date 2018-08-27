package wallet.estimation.domain;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;

import static wallet.estimation.domain.Currency.EURO;
import static wallet.stock.domain.StockType.BITCOIN;
import static wallet.stock.domain.StockType.US_DOLLAR;

public class WalletEstimationTest {

    private Money finalValue;

    private WalletBuilder walletBuilder = WalletBuilder.aWallet();
    private LocalConverter localConvertor = new LocalConverter();

    @Test
    public void should_convert_BTC_to_EUR() throws IOException {
        given_a_wallet()
                .withStockOf(2, BITCOIN);
        and_BTC_to_EUR_rate_is(5000);
        when_I_compute_value_in_euro();
        computed_euro_value_should_be(10000);
    }

    @Test
    public void should_convert_USD_to_EUR() {
        given_a_wallet()
                .withStockOf(10, US_DOLLAR);
        and_USD_to_EUR_rate_is(0.90);
        when_I_compute_value_in_euro();
        computed_euro_value_should_be(9);
    }

    @Test
    public void should_compute_total_value_of_a_wallet() throws IOException {
        given_a_wallet()
                .withStockOf(2, BITCOIN)
                .withStockOf(10, US_DOLLAR);
        and_BTC_to_EUR_rate_is(2000);
        and_USD_to_EUR_rate_is(0.80);

        when_I_compute_value_in_euro();
        computed_euro_value_should_be(4000 + 8);

    }

    private WalletBuilder given_a_wallet() {
        return walletBuilder;
    }

    private void and_BTC_to_EUR_rate_is(int value) {
        localConvertor = localConvertor.addRate(ChangeRate.of(BigDecimal.valueOf(value), BITCOIN, EURO));
    }

    private void and_USD_to_EUR_rate_is(double rate) {
        localConvertor = localConvertor.addRate(ChangeRate.of(BigDecimal.valueOf(rate), US_DOLLAR, EURO));
    }

    private void when_I_compute_value_in_euro() {
        finalValue = new Estimation(walletBuilder.build(), localConvertor).in(EURO);
    }

    private void computed_euro_value_should_be(int value) {
        Assertions.assertThat(finalValue.value()).isEqualByComparingTo(BigDecimal.valueOf(value));
    }

}
