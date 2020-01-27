package currencyexchangecalculator;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * POJO for storing JSON data from API
 * Keeps a Map with currency code for key and exchange rate as value.
 */
public class ExchangeRateData {
    private Map<String, BigDecimal> rates;
    private String base;
    private Date date;

    public BigDecimal getRate(String currencyCode) {
        return rates.get(currencyCode);
    }

    public Map<String, BigDecimal> getRates() {
        return rates;
    }

    public void setRates(Map<String, BigDecimal> rates) {
        this.rates = rates;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
