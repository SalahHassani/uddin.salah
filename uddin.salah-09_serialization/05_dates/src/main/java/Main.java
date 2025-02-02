import dates.DatesUtilsImp;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        DatesUtilsImp dateUtil = new DatesUtilsImp();

        LocalDate date1 = LocalDate.of(2015, 2, 17);
        LocalDate date2 = LocalDate.of(2020, 6, 2);
        System.out.println(dateUtil.between(date1, date2));

        Instant instant = Instant.now();
        LocalDate[] mondays = dateUtil.mondays(instant);

        System.out.println("\nAll Mondays in given dates month: ");
        for (LocalDate monday : mondays) {
            System.out.println(monday);
        }


        System.out.println("\nIs given date is Friday 13th?");
        System.out.println(dateUtil.isFridays13(date1));
        LocalDate friday13 = LocalDate.of(2024, 9, 13);
        System.out.println(dateUtil.isFridays13(friday13));

        ZonedDateTime now = ZonedDateTime.now();
        System.out.println("\nDate in English format: " + now);
        String formattedDate = dateUtil.formatFullJava8(now, "de");
        System.out.println("Date in German format: " + formattedDate);

    }
}

