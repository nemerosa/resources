package net.nemerosa.resources.json.jsr310;

import com.fasterxml.jackson.databind.module.SimpleModule;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;

public class JSR310Module extends SimpleModule {

    public JSR310Module() {
        super("jsr310");

        // LocalDateTime
        addSerializer(LocalDateTime.class, new JDKLocalDateTimeSerializer());
        addDeserializer(LocalDateTime.class, new JDKLocalDateTimeDeserializer());
        // LocalTime
        addSerializer(LocalTime.class, new JDKLocalTimeSerializer());
        addDeserializer(LocalTime.class, new JDKLocalTimeDeserializer());
        // LocalDate
        addSerializer(LocalDate.class, new JDKLocalDateSerializer());
        addDeserializer(LocalDate.class, new JDKLocalDateDeserializer());
        // YearMonth
        addSerializer(YearMonth.class, new JDKYearMonthSerializer());
        addDeserializer(YearMonth.class, new JDKYearMonthDeserializer());
    }
}
