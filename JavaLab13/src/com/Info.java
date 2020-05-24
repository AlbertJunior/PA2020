package com;

import optional.GETRequest;

import java.io.IOException;
import java.text.DateFormatSymbols;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Currency;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * This is a functional class
 * It contains the method for displaying info about a locale
 */
public class Info {

    /**
     * This method invokes all the methods for displays info about the current locale
     */
    public static void displayInfoCurrentLocale() {
        Locale locale = Locale.getDefault();
        Object[] arguments = {locale};
        String baseName = "res.Messages";
        ResourceBundle messages = ResourceBundle.getBundle(baseName, Locale.getDefault());
        String pattern = messages.getString("info");
        String setMessage = new MessageFormat(pattern).format(arguments);
        System.out.println(setMessage);

        displayCountry(locale);
        displayLanguage(locale);
        displayCurrency(locale);
        displayWeekDays(locale);
        displayMonths(locale);
        displayToday(locale);

    }

    /**
     * This class prints all months
     *
     * @param locale
     */
    private static void displayMonths(Locale locale) {

        String months[] = DateFormatSymbols.getInstance(locale).getMonths();
        String monthsString = "";
        for (String day : months) {
            monthsString = monthsString + day + " ";
        }
        System.out.println("Months: " + monthsString);
    }

    /**
     * This class prints the date
     *
     * @param locale
     */
    private static void displayToday(Locale locale) {
        LocalDateTime today = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter
                .ofLocalizedDate(FormatStyle.FULL)
                .withLocale(locale);
        System.out.println("Today: " + today.format(formatter));

    }

    /**
     * This class prints all week days
     *
     * @param locale
     */
    private static void displayWeekDays(Locale locale) {
        String wkdays[] = DateFormatSymbols.getInstance(locale).getWeekdays();
        String weekDays = "";
        for (String day : wkdays) {
            weekDays = weekDays + day + " ";
        }
        System.out.println("Week Days:" + weekDays);
    }

    /**
     * This class prints the currency
     *
     * @param locale
     */
    private static void displayCurrency(Locale locale) {
        System.out.println("Currency: " + Currency.getInstance(locale).getCurrencyCode());
    }

    /**
     * This class prints the language
     *
     * @param locale
     */
    private static void displayLanguage(Locale locale) {
        System.out.println("Language: " + locale.getDisplayLanguage());
    }

    /**
     * This class prints the country
     *
     * @param locale
     */
    private static void displayCountry(Locale locale) {
        System.out.println("Country: " + locale.getDisplayCountry());
        try {
            GETRequest.getRequest(locale.getISO3Country());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
