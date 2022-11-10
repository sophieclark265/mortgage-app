package input;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Currency;
public class NumberConverter {

    public static String convertToDollars(float input) {
        // need to be converted to double format
        double newInput = (double) input;
        // locale object determines geopolitical area
        Locale usa = new Locale("en", "US");
        Currency dollars = Currency.getInstance(usa);
        NumberFormat dollarFormat = NumberFormat.getCurrencyInstance(usa);

        return dollarFormat.format(newInput);
    }
}
