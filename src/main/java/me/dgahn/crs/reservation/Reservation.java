package me.dgahn.crs.reservation;

import me.dgahn.crs.account.Account;
import me.dgahn.crs.accountreservation.AccountReservation;
import me.dgahn.crs.classroom.ClassRoom;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Reservation {

  @Id @GeneratedValue
  private Long reservationId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "account_id")
  private Account account;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "classroom_id")
  private ClassRoom classRoom;

  @Column(nullable = false)
  private LocalDateTime startDate;

  @Column(nullable = false)
  private LocalDateTime endDate;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private ReservationStatus reservationStatus;

  @Column(nullable = false)
  private String title;

  @OneToMany(mappedBy = "reservation")
  private List<AccountReservation> accountReservations = new ArrayList<>();

}
