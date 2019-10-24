package me.dgahn.crs.account;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


public class AccountDto {

  @Getter
  @Setter
  @ToString
  @NoArgsConstructor
  public static class AccountRegisterFormDto {
    private String id;
    private String password;
    private String name;
    private String email;
  }

}
