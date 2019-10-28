package me.dgahn.crs.classroom;

import lombok.Getter;
import me.dgahn.crs.reservation.Reservation;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
public class Classroom {

  @Id @GeneratedValue
  private Long classroomId;

  @Column(nullable = false)
  private String number;

  @Column(nullable = false)
  private int floor;

  @Column(nullable = false)
  private int capacity;

  @OneToMany(mappedBy = "classroom", cascade = CascadeType.ALL)
  private List<Reservation> reservations = new ArrayList<>();

}
