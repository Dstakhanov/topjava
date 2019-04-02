
package ru.javawebinar.topjava.util;

import org.springframework.format.Formatter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import java.util.Locale;

import static ru.javawebinar.topjava.util.DateTimeUtil.*;

public final class Formatters {
    private static class myLocalDate implements Formatter<LocalDate> {

        @Override
        public LocalDate parse(String text, Locale locale) {
            return parseLocalDate(text);
        }

        @Override
        public String print(LocalDate localDate, Locale locale) {
            return localDate.format(DateTimeFormatter.ISO_LOCAL_DATE);
        }
    }

    public static class myLocalTime implements Formatter<LocalTime> {

        @Override
        public LocalTime parse(String text, Locale locale) {
            return parseLocalTime(text);
        }

        @Override
        public String print(LocalTime localTime, Locale locale) {
            return localTime.format(DateTimeFormatter.ISO_LOCAL_TIME);
        }
    }

}