package me.dgahn.crs.reservation;

import lombok.Getter;
import lombok.ToString;
import me.dgahn.crs.account.Account;
import me.dgahn.crs.account.AccountRole;
import me.dgahn.crs.accountreservation.AccountReservation;
import me.dgahn.crs.classroom.Classroom;

import javax.persistence.CascadeType;
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


@Getter
@Entity
@ToString
public class Reservation {

  @Id
  @GeneratedValue
  private Long reservationId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "account_id")
  private Account account;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "classroom_id")
  private Classroom classroom;

  @Column(nullable = false)
  private LocalDateTime startDate;

  @Column(nullable = false)
  private LocalDateTime endDate;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private ReservationStatus reservationStatus;

  @Column(nullable = false)
  private String title;

  @OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL)
  private List<AccountReservation> accountReservations = new ArrayList<>();

  public void setStartDate(final LocalDateTime startDate) {
    this.startDate = startDate;
  }

  public void setEndDate(final LocalDateTime endDate) {
    this.endDate = endDate;
  }

  public void addAccountReservation(AccountReservation accountReservation) {
    accountReservations.add(accountReservation);
    accountReservation.setReservation(this);
  }

  public void setReservationStatus(AccountRole role) {
    if(role.equals(AccountRole.ADMIN)) {
      this.reservationStatus = ReservationStatus.APPROVAL;
    } else {
      this.reservationStatus = ReservationStatus.WAITING;
    }
  }


  public void setTitle(final String title) {
    this.title = title;
  }

  public void setAccount(Account account) {
    this.account = account;
    account.getReservations()
           .add(this);
  }

  public void setClassroom(Classroom classroom) {
    this.classroom = classroom;
    classroom.getReservations()
             .add(this);
  }

  public static Reservation createReservation(Account subscriber,
                                              Classroom classroom,
                                              LocalDateTime startDate,
                                              LocalDateTime endDate,
                                              String title,
                                              List<AccountReservation> accountReservations) {
    Reservation reservation = new Reservation();
    reservation.setAccount(subscriber);
    reservation.setClassroom(classroom);
    reservation.setStartDate(startDate);
    reservation.setEndDate(endDate);
    reservation.setTitle(title);
    reservation.setReservationStatus(subscriber.getRole());
    accountReservations.forEach(reservation::addAccountReservation);
    return reservation;
  }

}
