package me.dgahn.crs.reservation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


public class ReservationDto {

  @Getter
  @Setter
  @ToString
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class ReservationRegisterFormDto {

    private Long classroomId;
    private String title;
    private String startDate;
    private String startHour;
    private String startMinute;
    private String endDate;
    private String endHour;
    private String endMinute;
    private String assistants;

  }

}
