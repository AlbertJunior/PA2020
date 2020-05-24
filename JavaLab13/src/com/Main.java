package com;

import java.util.Locale;

public class Main {

	public static void main(String[] args) {
		DisplayLocales.display();
		Locale locale = new Locale("ro", "RO");
		SetLocales.setLocale(locale);
		Info.displayInfoCurrentLocale();
	}
}
