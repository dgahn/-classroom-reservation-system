package me.dgahn.crs.account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import me.dgahn.crs.accountreservation.AccountReservation;
import me.dgahn.crs.reservation.Reservation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Table(name = "account")
public class Account {

  @Id @GeneratedValue
  @Column(name = "account_id", nullable = false)
  private Long accountId;

  @Column(nullable = false, unique = true, name = "id")
  private String id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String password;

  @Column(nullable = false)
  private String email;

  @Column(nullable = false, columnDefinition = "boolean default false")
  private boolean isSuperUser;

  @OneToMany(mappedBy = "account")
  private List<Reservation> reservations = new ArrayList<>();

  @OneToMany(mappedBy = "account")
  private List<AccountReservation> accountReservations = new ArrayList<>();

}
