package com.eberrabello.workshopmongo.resources.util;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.time.*;
import java.time.format.DateTimeParseException;

public class URL {

    public static String decodeParam(String text) {
        return URLDecoder.decode(text, StandardCharsets.UTF_8);
    }

    public static Instant convertDate(String textDate, Instant defaultValue, boolean isMin) {
        try {
            LocalDate date = LocalDate.parse(textDate);
            Instant instantDate;
            if (isMin) {
                instantDate = date.atStartOfDay(ZoneId.of("UTC")).toInstant();
            } else {
                instantDate = date.atTime(LocalTime.MAX).atZone(ZoneId.of("UTC")).toInstant();
            }
            return instantDate;
        } catch (DateTimeParseException e) {
            return defaultValue;
        }
    }
}
