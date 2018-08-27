package wallet.estimation;

import wallet.stock.Stock;

public interface Convertor {

    Money convert(Stock stock, Currency currency);
}
