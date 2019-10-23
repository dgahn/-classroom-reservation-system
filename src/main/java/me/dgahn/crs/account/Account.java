package me.dgahn.crs.account;

import me.dgahn.crs.accountreservation.AccountReservation;
import me.dgahn.crs.reservation.Reservation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Account {

  @Id @GeneratedValue
  @Column(name = "account_id", nullable = false)
  private Long accountId;

  @Column(nullable = false)
  private String id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String password;

  @Column(nullable = false)
  private String email;

  @Column(nullable = false)
  private boolean isSuperUser;

  @OneToMany(mappedBy = "account")
  private List<Reservation> reservations = new ArrayList<>();

  @OneToMany(mappedBy = "account")
  private List<AccountReservation> accountReservations = new ArrayList<>();

}
