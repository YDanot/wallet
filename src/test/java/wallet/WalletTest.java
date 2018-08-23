package wallet;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;

import static wallet.Currency.EUR;

public class WalletTest {

    private Stock btcStock = new Stock(BigDecimal.ZERO, StockType.BITCOIN);
    private Stock usdStock = new Stock(BigDecimal.ZERO, StockType.US_DOLLAR);

    private Rate btcEurValue;
    private Rate usdEurRate;

    private Money finalValue;

    @Test
    public void should_compute_value_in_EUR() throws IOException {
        given_a_BTC_stock_of(2);
        and_bitcoin_euro_value_is(5000);
        when_I_compute_value_in_euro();
        computed_euro_value_should_be(10000);

        given_a_BTC_stock_of(2);
        and_bitcoin_euro_value_is(2000);
        when_I_compute_value_in_euro();
        computed_euro_value_should_be(4000);

        given_a_USD_stock_of(10);
        and_USD_euro_value_is(0.90);
        when_I_compute_value_in_euro();
        computed_euro_value_should_be(9);
    }

    private void and_USD_euro_value_is(double rate) {
        usdEurRate = new Rate(BigDecimal.valueOf(rate));
    }

    private void given_a_USD_stock_of(int value) {
        usdStock = new Stock(BigDecimal.valueOf(value), StockType.US_DOLLAR);
    }

    private void when_I_compute_value_in_euro() {
        if (usdStock.value().doubleValue() > 0)
            finalValue = new Money(usdStock.value().multiply(usdEurRate.value()), EUR);
        else {
            finalValue = new Money(btcStock.value().multiply(btcEurValue.value()), EUR);
        }
    }

    private void given_a_BTC_stock_of(int value) {
        btcStock = new Stock(BigDecimal.valueOf(value), StockType.BITCOIN);
    }

    private void and_bitcoin_euro_value_is(int value) {
        btcEurValue = new Rate(BigDecimal.valueOf(value));
    }

    private void computed_euro_value_should_be(int value) {
        Assertions.assertThat(finalValue.value()).isEqualByComparingTo(BigDecimal.valueOf(value));
    }

}
