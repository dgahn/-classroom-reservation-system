package me.dgahn.crs.account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import me.dgahn.crs.account.AccountDto.AccountRegisterFormDto;
import me.dgahn.crs.accountreservation.AccountReservation;
import me.dgahn.crs.reservation.Reservation;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private AccountRole role;

  @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
  private List<Reservation> reservations = new ArrayList<>();

  @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
  private List<AccountReservation> accountReservations = new ArrayList<>();

  public static Account createSaveAccount(AccountRegisterFormDto formDto) {
    return Account.builder()
                  .id(formDto.getId())
                  .password(formDto.getPassword())
                  .name(formDto.getName())
                  .email(formDto.getEmail())
                  .role(AccountRole.BASIC)
                  .build();
  }

}
