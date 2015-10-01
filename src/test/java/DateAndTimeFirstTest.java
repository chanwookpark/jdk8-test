import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;

/**
 * @author chanwook
 */
public class DateAndTimeFirstTest {

    @Test
    public void createObject() throws Exception {

        // Case 1
        LocalDateTime timePoint = LocalDateTime.now();
        System.out.println("Now is..." + timePoint);

        // Case 2
        final LocalDate date1 = LocalDate.of(2012, Month.DECEMBER, 15);
        assert date1.getYear() == 2012;
        assert date1.getMonth().getValue() == 12;
        assert date1.getDayOfMonth() == 15;

        // Case 3
        final LocalDate date2 = LocalDate.ofEpochDay(150);
        System.out.println("EpochDay is ...? " + date2);

        // Case 4
        final LocalTime time1 = LocalTime.of(15, 30, 10);// hour, minute, second
        assert time1.getHour() == 15;
        assert time1.getMinute() == 30;
        assert time1.getSecond() == 10;

        // Case 5
        final LocalTime time2 = LocalTime.parse("05:04:20"); // parsing. '0'을 포맷에 맞춰 넣어야함..
        assert time2.getHour() == 5;
        assert time2.getMinute() == 4;
        assert time2.getSecond() == 20;
    }

    @Test
    public void monthAndDay() throws Exception {
        final LocalDateTime now = LocalDateTime.now();

        final LocalDateTime second = now.withDayOfMonth(10).withYear(2015);
        System.out.println("second: " + second);

        assert second.getYear() == 2015;
        assert second.getDayOfMonth() == 10;
        assert second.getHour() == now.getHour();
        assert second.getMinute() == now.getMinute();
        assert second.getSecond() == now.getSecond();

        final LocalDateTime third = second.plusWeeks(3).plus(10, ChronoUnit.DAYS); // +31day
        System.out.println("third: " + third);

        assert third.getMonth().getValue() == 10;
        assert third.getDayOfMonth() == 11;
    }

    @Test
    public void temporalAdjusters() throws Exception {
        final LocalDateTime now = LocalDateTime.now();

        // lastDayOfMonth
        final LocalDateTime lastDayOfMonth = now.with(TemporalAdjusters.lastDayOfMonth());
        System.out.println("lastDayOfMonth: " + lastDayOfMonth);

        assert lastDayOfMonth.getMonth().getValue() == 9;
        assert lastDayOfMonth.getDayOfMonth() == 30;

        // previousOrSame
        final LocalDateTime previousOrSame = now.with(TemporalAdjusters.previousOrSame(DayOfWeek.WEDNESDAY));
        System.out.println("previousOrSame: " + previousOrSame);

        assert previousOrSame.getDayOfMonth() == 9;
        assert previousOrSame.getDayOfWeek() == DayOfWeek.WEDNESDAY;
    }

    @Test
    public void truncation() throws Exception {
        final LocalTime now = LocalTime.now();
        final LocalTime truncatedTime = now.truncatedTo(ChronoUnit.HOURS);

        System.out.println("now: " + now);
        System.out.println("truncated: " + truncatedTime);

        assert truncatedTime.getHour() == now.getHour();
        assert truncatedTime.getMinute() == 0;
        assert truncatedTime.getSecond() == 0;
    }

    @Test
    public void timeZone() throws Exception {
        final ZoneId id = ZoneId.of("Asia/Shanghai");
        final ZonedDateTime zoned = ZonedDateTime.of(LocalDateTime.now(), id);
        System.out.println("zoned: " + zoned);

        assert ZoneId.from(zoned).equals(id);

        //FIXME 왜 안되나??
//        final ZoneOffset offset = ZoneOffset.of("2:00");
//        System.out.println("offset: " + offset);
    }

    @Test
    public void timeZoneClass() throws Exception {

        final ZonedDateTime zonedDateTime = ZonedDateTime.parse("2007-12-03T10:15:30+01:00[Europe/Paris]");
        System.out.println("zoned: " + zonedDateTime);

        final OffsetTime time1 = OffsetTime.now();
        System.out.println("base time: " + time1);

        final OffsetTime time2 = time1.withOffsetSameInstant(ZoneOffset.ofHours(1));
        System.out.println("offset +1 : " + time2);
    }

    @Test
    public void periods() throws Exception {
        final Period period = Period.of(3, 2, 1);// 3 years, 2 months, 1 day

        final LocalDateTime oldDate = LocalDateTime.now();
        final LocalDateTime newDate = oldDate.plus(period);

        System.out.println("old: " + oldDate);
        System.out.println("new: " + newDate);

        assert newDate.getYear() == oldDate.getYear() + 3;
        assert newDate.getMonth().getValue() == oldDate.getMonth().getValue() + 2;
        assert newDate.getDayOfMonth() == oldDate.getDayOfMonth() + 1;
    }

    @Test
    public void durations() throws Exception {
        final Duration duration = Duration.ofSeconds(3);
        System.out.println(duration);

        final LocalDateTime today = LocalDateTime.now();
        final LocalDateTime yesterday = today.minusDays(1);
        final Duration oneDay = Duration.between(today, yesterday);

        System.out.printf("d: " + oneDay.toHours());
        assert oneDay.toHours() == -24l;
    }

    @Test
    public void format() throws Exception {
        final LocalDateTime dateTime = LocalDateTime.now();
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm");
        final String formatted = dateTime.format(formatter);
        System.out.printf("formatted: " + formatted);

        assert "2015-10-02 07:29".equals(formatted);

    }
}
