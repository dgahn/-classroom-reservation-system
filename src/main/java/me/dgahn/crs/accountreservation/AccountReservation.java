package me.dgahn.crs.accountreservation;


import lombok.Getter;
import me.dgahn.crs.account.Account;
import me.dgahn.crs.reservation.Reservation;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Getter
@Entity
public class AccountReservation {

  @Id @GeneratedValue
  private Long accountReservationId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "account_id")
  private Account account;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "reservation_id")
  private Reservation reservation;

  public void setAccount(final Account account) {
    this.account = account;
  }

  public void setReservation(Reservation reservation) {
    this.reservation = reservation;
  }

  public static AccountReservation createAccountReservation(Account account) {
    AccountReservation accountReservation = new AccountReservation();
    accountReservation.setAccount(account);

    return accountReservation;
  }

}
