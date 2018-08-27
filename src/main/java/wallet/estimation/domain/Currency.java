package wallet.estimation.domain;

import java.util.Locale;

public enum Currency {
    EURO("EUR", Locale.FRANCE),
    US_DOLLAR("USD", Locale.US),
    POUND_STERLING("GBP", Locale.UK);

    private final String iso4217;
    private final Locale locale;

    Currency(String iso4217, Locale locale) {
        this.iso4217 = iso4217;
        this.locale = locale;
    }

    public String iso4217() {
        return iso4217;
    }

    public Locale locale() {
        return this.locale;
    }
}
