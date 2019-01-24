package wallet.estimation.infra;

import wallet.FinanceApi;
import wallet.estimation.domain.ChangeRate;
import wallet.estimation.domain.Converter;
import wallet.estimation.domain.Currency;
import wallet.estimation.domain.Money;
import wallet.stock.domain.Stock;

import java.io.IOException;
import java.math.BigDecimal;

public class FinanceApiConverter implements Converter {

    @Override
    public Money convert(Stock stock, Currency currency) {
        try {
            String rate = new FinanceApi().convert(stock.stockType().iso4217(), currency.iso4217(), String.valueOf(1));
            return new ChangeRate(new BigDecimal(rate), stock.stockType(), currency).apply(stock.value());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
