package com;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * This is a functional class
 * It contains the method for displaying available locales
 */
public class DisplayLocales {

    /**
     * This method prints the available locales
     */
    public static void display(){
        String baseName = "res.Messages";
        ResourceBundle messages = ResourceBundle.getBundle(baseName, Locale.getDefault());
        System.out.println(messages.getString("locales"));

        Locale available[] = Locale.getAvailableLocales();
        for(Locale locale : available) {
            System.out.println(locale.getDisplayCountry() + "\t" + locale.getDisplayLanguage(locale));
        }
    }
}
