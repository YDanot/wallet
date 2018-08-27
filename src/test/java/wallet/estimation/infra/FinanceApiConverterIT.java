package wallet.estimation.infra;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import wallet.estimation.domain.Currency;
import wallet.estimation.domain.Estimation;
import wallet.estimation.domain.Money;
import wallet.estimation.helper.WalletBuilder;

import java.io.IOException;

import static wallet.estimation.domain.Currency.POUND_STERLING;
import static wallet.stock.domain.StockType.BITCOIN;

public class FinanceApiConverterIT {

    private Money finalValue;
    private WalletBuilder walletBuilder = WalletBuilder.aWallet();
    private FinanceApiConverter converter = new FinanceApiConverter();

    @Test
    public void should_convert_BTC_to_EUR() throws IOException {
        given_a_wallet()
                .withStockOf(2, BITCOIN);
        when_I_compute_value_in(POUND_STERLING);
        computed_value_should_be_positive();
    }

    private WalletBuilder given_a_wallet() {
        return walletBuilder;
    }

    private void when_I_compute_value_in(Currency currency) {
        finalValue = new Estimation(walletBuilder.build(), converter).in(currency);
    }

    private void computed_value_should_be_positive() {
        Assertions.assertThat(finalValue.value()).isPositive();
    }
}
