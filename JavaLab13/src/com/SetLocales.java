package com;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * This is a functional class
 * It contains the method for displaying info about a locale
 */
public class SetLocales {

    /**
     * This method prints the info about the current locale
     */
    public static void setLocale(Locale newLocale) {
        Locale.setDefault(newLocale);
        Object[] arguments = {Locale.getDefault()};
        String baseName = "res.Messages";
        ResourceBundle messages = ResourceBundle.getBundle(baseName, Locale.getDefault());
        String pattern = messages.getString("locale.set");
        String setMessage = new MessageFormat(pattern).format(arguments);
        System.out.println(setMessage);
    }
}
