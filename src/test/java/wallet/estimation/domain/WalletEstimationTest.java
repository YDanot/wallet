package wallet.estimation.domain;

import org.junit.After;
import org.junit.Test;
import wallet.estimation.domain.doubles.WalletTestHelper;

import java.io.IOException;

import static wallet.estimation.domain.doubles.WalletTestHelper.*;
import static wallet.stock.domain.StockType.BITCOIN;
import static wallet.stock.domain.StockType.US_DOLLAR;

public class WalletEstimationTest {


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

    @After
    public void tearDown(){
        WalletTestHelper.tearDown();
    }

}
