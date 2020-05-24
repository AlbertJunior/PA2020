package app;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

/**
 * Inside this class, we read commands from the keyboard and execute them.
 */
public class LocaleExplore {
    private static String[] commands;

    /**
     * This method contains the loop of this app
     * It will read the commands end invoke the methods for them
     * @param args
     */
    public static void main(String[] args) {
        String baseName = "res.Messages";
        ResourceBundle messages = ResourceBundle.getBundle(baseName, Locale.getDefault());
        getCommands();

        Scanner scan = new Scanner(System.in);
        while (true) {
            System.out.println(messages.getString("prompt"));
            String text = scan.nextLine();

            if (text.compareTo(commands[0]) == 0) {
                displayLocales();
            } else if (text.compareTo(commands[1]) == 0) {
                displayInfo();
            } else if (text.compareTo(commands[2]) == 0) {
                executeSetCommand(scan);
            } else {
                System.out.println(messages.getString("invalid"));
            }
        }
    }

    /**
     * This method contains the logic of setting a new Locale
     * We need to specify a Language and a Country to create one
     * @param scan
     */
    private static void executeSetCommand(Scanner scan) {
        System.out.println("Language: ");
        String locale = scan.nextLine();
        System.out.println("Country: ");
        String localeLen = scan.nextLine();
        setLocales(new Locale(locale, localeLen));
    }

    /**
     * This method is for the optional part
     * It maps the command names to corresponding classes using an external file Commands.properties
     */
    private static void getCommands() {
        String command;
        commands = new String[10];
        String baseName = "res.Commands";
        ResourceBundle messages = ResourceBundle.getBundle(baseName, Locale.getDefault());

        command = messages.getString("display-locales.command");
        commands[0] = command;

        command = messages.getString("info.command");
        commands[1] = command;

        command = messages.getString("set-locale.command");
        commands[2] = command;

    }

    /**
     * Sets a new Locale
     * This method invokes the method from the class specified in the Commands.properties
     * @param locale
     */
    private static void setLocales(Locale locale) {
        String baseName = "res.Commands";
        ResourceBundle messages = ResourceBundle.getBundle(baseName, Locale.getDefault());
        String pattern = messages.getString("set-locale.impl");

        Class<?> cls = null;
        try {
            cls = Class.forName(pattern);
            Method m = cls.getMethod("setLocale", Locale.class);
            m.invoke(null, locale);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }


    /**
     * displays info about the current locale
     * This method invokes the method from the class specified in the Commands.properties
     */
    private static void displayInfo() {
        String baseName = "res.Commands";
        ResourceBundle messages = ResourceBundle.getBundle(baseName, Locale.getDefault());
        String pattern = messages.getString("info.impl");

        Class<?> cls = null;
        try {
            cls = Class.forName(pattern);
            Method m = cls.getMethod("displayInfoCurrentLocale", null);
            m.invoke(null, null);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }


    /**
     * displays all the locales available
     * This method invokes the method from the class specified in the Commands.properties
     */
    private static void displayLocales() {
        String baseName = "res.Commands";
        ResourceBundle messages = ResourceBundle.getBundle(baseName, Locale.getDefault());
        String pattern = messages.getString("display-locales.impl");

        Class<?> cls = null;
        try {
            cls = Class.forName(pattern);
            Method m = cls.getMethod("display", null);
            m.invoke(null, null);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }
}
