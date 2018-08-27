package wallet.estimation.domain.doubles;

import org.assertj.core.api.Assertions;
import wallet.estimation.domain.Estimation;
import wallet.estimation.domain.Money;
import wallet.estimation.domain.doubles.ChangeRate;
import wallet.estimation.domain.doubles.LocalConverter;
import wallet.estimation.helper.WalletBuilder;

import java.math.BigDecimal;

import static wallet.estimation.domain.Currency.EURO;
import static wallet.stock.domain.StockType.BITCOIN;
import static wallet.stock.domain.StockType.US_DOLLAR;

public class WalletTestHelper {

    private static Money finalValue;
    private static WalletBuilder walletBuilder;
    private static LocalConverter localConverter = new LocalConverter();


    public static WalletBuilder given_a_wallet() {
        walletBuilder = WalletBuilder.aWallet();
        return walletBuilder;
    }

    public static void and_BTC_to_EUR_rate_is(int value) {
        localConverter = localConverter.addRate(ChangeRate.of(BigDecimal.valueOf(value), BITCOIN, EURO));
    }

    public static void and_USD_to_EUR_rate_is(double rate) {
        localConverter = localConverter.addRate(ChangeRate.of(BigDecimal.valueOf(rate), US_DOLLAR, EURO));
    }


    public static void when_I_compute_value_in_euro() {
        finalValue = new Estimation(walletBuilder.build(), localConverter).in(EURO);
    }

    public static void computed_euro_value_should_be(int value) {
        Assertions.assertThat(finalValue.value()).isEqualByComparingTo(BigDecimal.valueOf(value));
    }

    public static void tearDown() {
        finalValue = null;
        walletBuilder = null;
        localConverter = new LocalConverter();
    }
}