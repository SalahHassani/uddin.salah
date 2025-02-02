package dates;

import com.nix.jtc.dates.DateUtilJava8;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DatesUtilsImp implements DateUtilJava8 {

    private String dateToString(int dateValue, String type) {
        if(dateValue != 0) {
            type = dateValue + " " + type;
            if(dateValue != 1) {
                type += "s";
            }
            type += " ";
        }
        else {
            type = "";
        }

        return type;
    }

    /**
     * @param date1 date1
     * @param date2 date2
     * @return string representation of time period between two dates.
     */
    @Override
    public String between(LocalDate date1, LocalDate date2) {

        if(date1.isAfter(date2)) {
            LocalDate date = date1;
            date1 = date2;
            date2 = date;
        }

        Period period = Period.between(date1, date2);

        int year = period.getYears();
        int month = period.getMonths();
        int day = period.getDays();

        String date = dateToString(year, "year");
        date += dateToString(month, "month");
        date += dateToString(day, "day");

        return date;
    }

    /**
     * @param instant date
     * @return array of dates with each date falling on Monday.
     */
    @Override
    public LocalDate[] mondays(Instant instant) {

        LocalDate date = instant.atZone(ZoneId.systemDefault()).toLocalDate();

        int year = date.getYear();
        int month = date.getMonthValue();

        LocalDate firstDayOfMonth = LocalDate.of(year, month, 1);
        while(firstDayOfMonth.getDayOfWeek() != DayOfWeek.MONDAY) {
            firstDayOfMonth = firstDayOfMonth.plusDays(1);
        }

        List<LocalDate> mondays = new ArrayList<>();
        while(firstDayOfMonth.getMonthValue() == month) {
            mondays.add(firstDayOfMonth);
            firstDayOfMonth = firstDayOfMonth.plusWeeks(1);
        }

        return mondays.toArray(new LocalDate[0]);
    }

    /**
     * @param date date
     * @return true if the specified date is Friday, 13th, false otherwise.
     */
    @Override
    public boolean isFridays13(LocalDate date) {
        return date.getDayOfMonth() == 13 && date.getDayOfWeek() == DayOfWeek.FRIDAY;
    }

    /**
     * @param date     date
     * @param language ISO 639 two-letter or three-letter language code
     * @return string containing the formatted date.
     */
    @Override
    public String formatFullJava8(ZonedDateTime date, String language) {

        Locale locale = new Locale(language);
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL).withLocale(locale);

        return date.format(formatter);
    }
}
