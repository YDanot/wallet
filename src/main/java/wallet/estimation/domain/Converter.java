package wallet.estimation.domain;

import wallet.stock.domain.Stock;

public interface Converter {

    Money convert(Stock stock, Currency currency);
}
