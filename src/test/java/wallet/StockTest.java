package wallet;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;

import static wallet.Currency.EUR;
import static wallet.StockType.BITCOIN;
import static wallet.StockType.US_DOLLAR;

public class StockTest {

    private Stock btcStock = Stock.of(BigDecimal.ZERO, BITCOIN);
    private Stock usdStock = Stock.of(BigDecimal.ZERO, US_DOLLAR);

    private Rate btcEurRate;
    private Rate usdEurRate;

    private Money finalValue;

    @Test
    public void should_compute_value_in_EUR() throws IOException {
        given_a_BTC_stock_of(2);
        and_BTC_to_EUR_rate_is(5000);
        when_I_compute_value_in_euro();
        computed_euro_value_should_be(10000);

        given_a_BTC_stock_of(2);
        and_BTC_to_EUR_rate_is(2000);
        when_I_compute_value_in_euro();
        computed_euro_value_should_be(4000);

        given_a_USD_stock_of(10);
        and_USD_to_EUR_rate_is(0.90);
        when_I_compute_value_in_euro();
        computed_euro_value_should_be(9);
    }

    private void given_a_BTC_stock_of(int value) {
        btcStock = Stock.of(BigDecimal.valueOf(value), BITCOIN);
    }

    private void and_BTC_to_EUR_rate_is(int value) {
        btcEurRate = Rate.of(BigDecimal.valueOf(value), BITCOIN, EUR);
    }

    private void given_a_USD_stock_of(int value) {
        usdStock = Stock.of(BigDecimal.valueOf(value), US_DOLLAR);
    }

    private void and_USD_to_EUR_rate_is(double rate) {
        usdEurRate = Rate.of(BigDecimal.valueOf(rate), US_DOLLAR, EUR);
    }

    private void when_I_compute_value_in_euro() {
        if (usdStock.value().doubleValue() > 0)
            finalValue = Money.of(usdEurRate.apply(usdStock.value()), EUR);
        else {
            finalValue = Money.of(btcEurRate.apply(btcStock.value()), EUR);
        }
    }

    private void computed_euro_value_should_be(int value) {
        Assertions.assertThat(finalValue.value()).isEqualByComparingTo(BigDecimal.valueOf(value));
    }

}
