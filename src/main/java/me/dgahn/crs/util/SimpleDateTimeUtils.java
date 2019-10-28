package me.dgahn.crs.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class SimpleDateTimeUtils {

  public static LocalDateTime getLocalDateTime(String dateTime, String hour, String minute) {
    List<Integer> s = Arrays.stream(dateTime.split("-")).map(Integer::parseInt).collect(Collectors.toList());
    return LocalDateTime.of(s.get(0), s.get(1), s.get(2), Integer.parseInt(hour), Integer.parseInt(minute));
  }

}
