package me.dgahn.crs.classroom;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;


public class ClassroomDto {

  @Getter
  @Setter
  @ToString
  @Builder
  public static class ClassroomResponseDto {
    private Long classroomId;
    private int floor;
    private int number;
    private int capacity;
  }

}
