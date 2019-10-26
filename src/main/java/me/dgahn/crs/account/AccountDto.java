package me.dgahn.crs.account;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.List;


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

  @Getter
  @Setter
  @ToString
  @NoArgsConstructor
  public static class AccountLogInFormDto {

    private String id;
    private String password;
    private String message;

  }

  @Getter
  @ToString
  public static class AccountSecurityDto extends User {

    private static final String ROLE_PREFIX = "ROLE_";
    private static final long serialVersionUID = 1L;

    public AccountSecurityDto(Account account) {
      super(account.getId(), account.getPassword(), makeGrantedAuthority(account.getRole()));
    }

    private static List<GrantedAuthority> makeGrantedAuthority(AccountRole role) {
      List<GrantedAuthority> list = new ArrayList<>();
      list.add(new SimpleGrantedAuthority(ROLE_PREFIX + role));
      return list;
    }

  }

}
