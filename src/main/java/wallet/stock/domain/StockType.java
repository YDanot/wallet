package wallet.stock.domain;

public enum StockType {
    BITCOIN("BTC"),
    US_DOLLAR("USD");

    private final String iso4217;

    StockType(String iso4217) {
        this.iso4217 = iso4217;
    }

    public String iso4217() {
        return iso4217;
    }
}
