package com.gigigo.kbase.presentation.utils.format;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Juan Godínez Vera - 7/19/2017.
 */
public class FormatExtensions {
    private SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
    private SimpleDateFormat outputDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    public void setInputDateFormat(SimpleDateFormat inputDateFormat) {
        this.inputDateFormat = inputDateFormat;
    }

    public void setOutputDateFormat(SimpleDateFormat outputDateFormat) {
        this.outputDateFormat = outputDateFormat;
    }

    public String toCurrencyString(Object value) {
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance();
        return numberFormat.format(value);
    }

    /**
     *
     * @param currency en fotmato $0.00
     * @return
     */
    public double toDoubleCurrencyString(String currency) {
        String cleanString = currency.replaceAll("[$,.]", "");

        BigDecimal parsed = new BigDecimal(cleanString)
                .setScale(2, BigDecimal.ROUND_FLOOR)
                .divide(new BigDecimal(100), BigDecimal.ROUND_FLOOR);

        return parsed.doubleValue();
    }

    public String toDateString(String value) {
        try {
            Date date = inputDateFormat.parse(value);
            return outputDateFormat.format(date);
        } catch (ParseException e) {
            return value;
        }
    }

    public Date toDate(String value, String format) {
        try {
            Date date = new SimpleDateFormat(format).parse(value);
            return date;
        } catch (ParseException e) {
            return null;
        }
    }
}
