package com.tyty.daily.wtf;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * @author zhangty
 * @version 1.0
 * @date 2021/9/3 11:36
 */
public class LocalTimeTest {

    public static DateTimeFormatter TIME_FORMATTER_PRECISION = DateTimeFormatter.ofPattern("HH:mm:ss");

    public static void main(String[] args) {
        //LocalTime lt = LocalTime.of(0, 5, 0, 0);
        //LocalTime lt2 = LocalTime.of(0, 5, 59, 0);
        //Duration between = Duration.between(lt, lt2);
        //System.out.println(between.toMinutes());
        //
        LocalDate end = LocalDate.of(2021, 10, 18);
        LocalDate now = LocalDate.of(2021, 10, 19);
//		LocalDateTime now2 = LocalDateTime.of(2021, 10, 18, 10, 48, 59);
//		Duration duration = Duration.between(now, end);
        //Duration duration2 = Duration.between(now2, end);
        System.out.println(ChronoUnit.DAYS.between(end, now));
        //System.out.println(duration2.toMinutes());
        //String format = TIME_FORMATTER_PRECISION.format(lt);
        //System.out.println(format);

        //try{
        //	throw new NullPointerException();
        //}catch (Exception e){
        //	System.out.println("eeeee");
        //}
        //System.out.println("ddddd");

        //System.out.println(OffsetDateTime.parse(LocalDateTime.now().withNano(0).toString()+"+08:00"));
//		String time = "2021-11-05T15:01:45+08:00";
//		String replace = time.replace("+", " ");
//		System.out.println(replace);

//		LocalDateTime t1 = LocalDateTime.of(2022,1,1,0,0,0);
//		LocalDateTime t2 = LocalDateTime.of(2022,1,1,1,0,0);
//		long days = ChronoUnit.DAYS.between(t1, t2);
//		System.out.println(days);
    }
}

