package wallet;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.io.IOException;

public class WalletTest {

    private int btcStock;
    private int btcEurValue;
    private double finalValue;
    private int usdStock;
    private double usdEurRate;

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
        usdEurRate = rate;
    }

    private void given_a_USD_stock_of(int value) {
        usdStock = value;
    }

    private void when_I_compute_value_in_euro() {
        if(usdStock > 0)
            finalValue = usdStock * usdEurRate;
        else {
            finalValue = btcStock * btcEurValue;
        }
    }

    private void given_a_BTC_stock_of(int value) {
        btcStock = value;
    }

    private void and_bitcoin_euro_value_is(int value) {
        btcEurValue = value;
    }

    private void computed_euro_value_should_be(int value) {
        Assertions.assertThat(finalValue).isEqualTo(value);
    }

}
